package lostworlds.server.dimension.carver;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ModCaveCarver extends CaveWorldCarver {
	private final ImmutableSet<Supplier<Block>> blocks = ImmutableSet.of(() -> LostWorldsBlocks.PERMIAN_SAND.get(), () -> LostWorldsBlocks.PERMIAN_STONE.get(), () -> LostWorldsBlocks.PERMIAN_COBBLESTONE.get(), () -> LostWorldsBlocks.JURASSIC_STONE.get(), () -> LostWorldsBlocks.JURASSIC_COBBLESTONE.get(), () -> LostWorldsBlocks.LATERLITE.get(), () -> LostWorldsBlocks.RAW_MARBLE.get(), () -> LostWorldsBlocks.LIMESTONE.get(), () -> LostWorldsBlocks.MOSSY_SOIL.get(), () -> LostWorldsBlocks.DRIED_SOIL.get(), () -> LostWorldsBlocks.CRACKED_SOIL.get(), () -> LostWorldsBlocks.VOLCANIC_ASH.get(), () -> LostWorldsBlocks.VOLCANIC_ASH_LAYER.get(), () -> Blocks.BASALT, () -> Blocks.BLACKSTONE, () -> Blocks.DIRT, () -> Blocks.SAND, () -> Blocks.COARSE_DIRT, () -> Blocks.GRAVEL, () -> Blocks.MAGMA_BLOCK, () -> Blocks.STONE, () -> Blocks.SNOW_BLOCK);

	public ModCaveCarver(Codec<ProbabilityConfig> codec, int genHeight) {
		super(codec, genHeight);
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
