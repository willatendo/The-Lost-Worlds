package lostworlds.library.item;

import java.util.Objects;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.NonNullSupplier;

public class SpawnItem extends ModItem {
	private final Lazy<? extends EntityType<?>> entityTypeSupplier;
	private final ITextComponent name;

	public SpawnItem(NonNullSupplier<? extends EntityType<? extends CreatureEntity>> entityTypeSupplier, ITextComponent name) {
		this.entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
		this.name = name;
	}

	@Override
	public ITextComponent getName(ItemStack stack) {
		return new TranslationTextComponent("item.lostworlds.spawn", this.name);
	}

	@Override
	public ActionResultType useOn(ItemUseContext cpmtext) {
		World world = cpmtext.getLevel();
		if (!(world instanceof ServerWorld)) {
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
		if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
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
