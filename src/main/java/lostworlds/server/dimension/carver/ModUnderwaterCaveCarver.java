package lostworlds.server.dimension.carver;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.carver.UnderwaterCanyonWorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class ModUnderwaterCaveCarver extends UnderwaterCanyonWorldCarver {
	private final ImmutableSet<Supplier<Block>> blocks = ImmutableSet.of(() -> LostWorldsBlocks.PERMIAN_SAND.get(), () -> LostWorldsBlocks.PERMIAN_STONE.get(), () -> LostWorldsBlocks.PERMIAN_COBBLESTONE.get(), () -> LostWorldsBlocks.JURASSIC_STONE.get(), () -> LostWorldsBlocks.JURASSIC_COBBLESTONE.get(), () -> LostWorldsBlocks.LATERLITE.get(), () -> LostWorldsBlocks.RAW_MARBLE.get(), () -> LostWorldsBlocks.LIMESTONE.get(), () -> LostWorldsBlocks.MOSSY_SOIL.get(), () -> LostWorldsBlocks.DRIED_SOIL.get(), () -> LostWorldsBlocks.CRACKED_SOIL.get(), () -> LostWorldsBlocks.VOLCANIC_ASH.get(), () -> LostWorldsBlocks.VOLCANIC_ASH_LAYER.get(), () -> Blocks.BASALT, () -> Blocks.BLACKSTONE, () -> Blocks.DIRT, () -> Blocks.SAND, () -> Blocks.COARSE_DIRT, () -> Blocks.GRAVEL, () -> Blocks.MAGMA_BLOCK, () -> Blocks.STONE, () -> Blocks.SNOW_BLOCK);

	public ModUnderwaterCaveCarver(Codec<ProbabilityFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	protected boolean canReplaceBlock(BlockState state) {
		boolean flag = false;
		for (Supplier<Block> blocks : this.blocks) {
			if (state.is(blocks.get())) {
				flag = true;
			}
		}
		return flag;
	}
}
