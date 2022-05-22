package lostworlds.server.block;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public interface SimpleLavaloggedBlock extends BucketPickup, LiquidBlockContainer {
	public static final BooleanProperty LAVALOGGED = BooleanProperty.create("lavalogged");

	@Override
	default boolean canPlaceLiquid(BlockGetter reader, BlockPos pos, BlockState state, Fluid fluid) {
		return !state.getValue(BooleanProperty.create("lavalogged")) && fluid == Fluids.LAVA;
	}

	@Override
	default boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluid) {
		if (!state.getValue(LAVALOGGED) && fluid.getType() == Fluids.LAVA) {
			if (!level.isClientSide()) {
				level.setBlock(pos, state.setValue(LAVALOGGED, Boolean.valueOf(true)), 3);
				level.scheduleTick(pos, fluid.getType(), fluid.getType().getTickDelay(level));
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	default ItemStack pickupBlock(LevelAccessor level, BlockPos pos, BlockState state) {
		if (state.getValue(LAVALOGGED)) {
			level.setBlock(pos, state.setValue(LAVALOGGED, Boolean.valueOf(false)), 3);
			if (!state.canSurvive(level, pos)) {
				level.destroyBlock(pos, true);
			}

			return new ItemStack(Items.WATER_BUCKET);
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	default Optional<SoundEvent> getPickupSound() {
		return Fluids.LAVA.getPickupSound();
	}
}
