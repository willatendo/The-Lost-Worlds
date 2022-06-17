package lostworlds.server.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LostWorldsSignBlockEntity extends SignBlockEntity {
	private final BlockEntityType<?> blockEntityEntry;

	public LostWorldsSignBlockEntity(BlockEntityType<?> blockEntityEntry, BlockPos pos, BlockState state) {
		super(pos, state);
		this.blockEntityEntry = blockEntityEntry;
	}

	@Override
	public BlockEntityType<?> getType() {
		return this.blockEntityEntry;
	}
}
