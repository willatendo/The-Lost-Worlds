package lostworlds.server.item;

import java.util.Objects;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import lostworlds.server.entity.fossil.Fossil;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.NonNullSupplier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SkeletonItem extends Item implements IAnimatable {
	public static final String animation = "animation.skeleton.living";

	private AnimationFactory factory = new AnimationFactory(this);

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation(this.animation, true));
		return PlayState.CONTINUE;
	}

	private final DinoTypes type;
	private final Lazy<? extends EntityType<?>> entityTypeSupplier;
	private final boolean isPlastered;

	public SkeletonItem(Properties properties, DinoTypes type, NonNullSupplier<? extends EntityType<Fossil>> entityTypeSupplier, boolean isPlastered) {
		super(properties);
		this.type = type;
		this.entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
		this.isPlastered = isPlastered;
	}

	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
		super.initializeClient(consumer);
		if (!this.isPlastered) {
			consumer.accept(new IItemRenderProperties() {
				@Override
				public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
					return SkeletonItem.this.type.getISTER();
				}
			});
		}
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<IAnimatable>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		if (this.isPlastered) {
			return InteractionResult.FAIL;
		} else if (!(world instanceof ServerLevel)) {
			return InteractionResult.SUCCESS;
		} else {
			ItemStack itemstack = context.getItemInHand();
			BlockPos blockpos = context.getClickedPos();
			Direction direction = context.getClickedFace();
			BlockState blockstate = world.getBlockState(blockpos);
			BlockPos blockpos1;
			if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
				blockpos1 = blockpos;
			} else {
				blockpos1 = blockpos.relative(direction);
			}

			EntityType<?> entitytype = this.getType(itemstack.getTag());
			if (entitytype.spawn((ServerLevel) world, itemstack, context.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
				itemstack.shrink(1);
			}

			return InteractionResult.CONSUME;
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		HitResult raytraceresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
		if (!this.isPlastered) {
			return InteractionResultHolder.fail(itemstack);
		} else if (raytraceresult.getType() != HitResult.Type.BLOCK) {
			return InteractionResultHolder.pass(itemstack);
		} else if (!(level instanceof ServerLevel)) {
			return InteractionResultHolder.success(itemstack);
		} else {
			BlockHitResult blockraytraceresult = (BlockHitResult) raytraceresult;
			BlockPos blockpos = blockraytraceresult.getBlockPos();
			if (!(level.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
				return InteractionResultHolder.pass(itemstack);
			} else if (level.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos, blockraytraceresult.getDirection(), itemstack)) {
				EntityType<?> entitytype = this.getType(itemstack.getTag());
				if (entitytype.spawn((ServerLevel) level, itemstack, player, blockpos, MobSpawnType.SPAWN_EGG, false, false) == null) {
					return InteractionResultHolder.pass(itemstack);
				} else {
					if (!player.isCreative()) {
						itemstack.shrink(1);
					}

					player.awardStat(Stats.ITEM_USED.get(this));
					return InteractionResultHolder.consume(itemstack);
				}
			} else {
				return InteractionResultHolder.fail(itemstack);
			}
		}
	}

	public EntityType<?> getType(@Nullable CompoundTag tag) {
		if (tag != null && tag.contains("EntityTag", 10)) {
			CompoundTag compoundnbt = tag.getCompound("EntityTag");
			if (compoundnbt.contains("id", 8)) {
				return EntityType.byString(compoundnbt.getString("id")).orElse(this.entityTypeSupplier.get());
			}
		}

		return this.entityTypeSupplier.get();
	}
}
