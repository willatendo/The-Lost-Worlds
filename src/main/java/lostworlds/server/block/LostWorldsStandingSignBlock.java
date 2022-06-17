package lostworlds.server.block;

import lostworlds.server.block.entity.LostWorldsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class LostWorldsStandingSignBlock extends StandingSignBlock implements LostWorldsSign {
	public LostWorldsStandingSignBlock(Properties properties, WoodType type) {
		super(properties, type);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return LostWorldsBlockEntities.LOST_WORLDS_SIGN.create(pos, state);
	}
}
