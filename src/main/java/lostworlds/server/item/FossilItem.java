package lostworlds.server.item;

import java.util.Objects;

import javax.annotation.Nullable;

import lostworlds.server.entity.fossil.FossilEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.NonNullSupplier;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.PlayState;
import tyrannotitanlib.library.tyrannomation.core.builder.TyrannomationBuilder;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class FossilItem extends Item implements ITyrannomatable {
	public static final String animation = "animation.skeleton.living";

	private TyrannomationFactory factory = new TyrannomationFactory(this);

	private <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event) {
		event.getController().setAnimation(new TyrannomationBuilder().addAnimation(this.animation, true));
		return PlayState.CONTINUE;
	}

	private final Lazy<? extends EntityType<?>> entityTypeSupplier;
	private final boolean isPlastered;

	public FossilItem(Properties properties, NonNullSupplier<? extends EntityType<FossilEntity>> entityTypeSupplier, boolean isPlastered) {
		super(properties);
		this.entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
		this.isPlastered = isPlastered;
	}

	@Override
	public void registerControllers(TyrannomationData data) {
		data.addAnimationController(new TyrannomationController<ITyrannomatable>(this, "controller", 0, this::predicate));
	}

	@Override
	public TyrannomationFactory getFactory() {
		return this.factory;
	}

	@Override
	public ActionResultType useOn(ItemUseContext cpmtext) {
		World world = cpmtext.getLevel();
		if (this.isPlastered) {
			return ActionResultType.FAIL;
		} else if (!(world instanceof ServerWorld)) {
			return ActionResultType.SUCCESS;
		} else {
			ItemStack itemstack = cpmtext.getItemInHand();
			BlockPos blockpos = cpmtext.getClickedPos();
			Direction direction = cpmtext.getClickedFace();
			BlockState blockstate = world.getBlockState(blockpos);
			BlockPos blockpos1;
			if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
				blockpos1 = blockpos;
			} else {
				blockpos1 = blockpos.relative(direction);
			}

			EntityType<?> entitytype = this.getType(itemstack.getTag());
			if (entitytype.spawn((ServerWorld) world, itemstack, cpmtext.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
				itemstack.shrink(1);
			}

			return ActionResultType.CONSUME;
		}
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		RayTraceResult raytraceresult = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (!this.isPlastered) {
			return ActionResult.fail(itemstack);
		} else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.pass(itemstack);
		} else if (!(world instanceof ServerWorld)) {
			return ActionResult.success(itemstack);
		} else {
			BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
			BlockPos blockpos = blockraytraceresult.getBlockPos();
			if (!(world.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
				return ActionResult.pass(itemstack);
			} else if (world.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos, blockraytraceresult.getDirection(), itemstack)) {
				EntityType<?> entitytype = this.getType(itemstack.getTag());
				if (entitytype.spawn((ServerWorld) world, itemstack, player, blockpos, SpawnReason.SPAWN_EGG, false, false) == null) {
					return ActionResult.pass(itemstack);
				} else {
					if (!player.abilities.instabuild) {
						itemstack.shrink(1);
					}

					player.awardStat(Stats.ITEM_USED.get(this));
					return ActionResult.consume(itemstack);
				}
			} else {
				return ActionResult.fail(itemstack);
			}
		}
	}

	public EntityType<?> getType(@Nullable CompoundNBT nbt) {
		if (nbt != null && nbt.contains("EntityTag", 10)) {
			CompoundNBT compoundnbt = nbt.getCompound("EntityTag");
			if (compoundnbt.contains("id", 8)) {
				return EntityType.byString(compoundnbt.getString("id")).orElse(this.entityTypeSupplier.get());
			}
		}

		return this.entityTypeSupplier.get();
	}
}
