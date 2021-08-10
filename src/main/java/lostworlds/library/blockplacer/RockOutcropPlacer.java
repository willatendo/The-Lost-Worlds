package lostworlds.library.blockplacer;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.content.server.init.BlockPlacerInit;
import lostworlds.library.block.RockOutcropBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;

public class RockOutcropPlacer extends BlockPlacer
{
	public static final Codec<RockOutcropPlacer> CODEC;
	public static final RockOutcropPlacer INSTANCE = new RockOutcropPlacer();

	@Override
	public void place(IWorld world, BlockPos pos, BlockState state, Random rand) 
	{
		((RockOutcropBlock)state.getBlock()).placeAt(world, pos, 2);
	}

	@Override
	protected BlockPlacerType<?> type() 
	{
		return BlockPlacerInit.ROCK_OUTCROP_PLACER;
	}

	static
	{
		CODEC = Codec.unit(() -> 
		{
			return INSTANCE;
		});
	}
}
