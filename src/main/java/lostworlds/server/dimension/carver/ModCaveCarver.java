package lostworlds.server.dimension.carver;

import static lostworlds.server.util.GeneralGetter.getWhenCan;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ModCaveCarver extends CaveWorldCarver {
	private final Supplier<ImmutableSet<Block>> blocks;

	public ModCaveCarver(Codec<ProbabilityConfig> codec, int probability) {
		super(codec, probability);
		this.blocks = () -> ImmutableSet.of(getWhenCan(LostWorldsBlocks.PERMIAN_SAND), getWhenCan(LostWorldsBlocks.PERMIAN_STONE), getWhenCan(LostWorldsBlocks.PERMIAN_COBBLESTONE), getWhenCan(LostWorldsBlocks.JURASSIC_STONE), getWhenCan(LostWorldsBlocks.JURASSIC_COBBLESTONE), getWhenCan(LostWorldsBlocks.LATERLITE), getWhenCan(LostWorldsBlocks.RAW_MARBLE), getWhenCan(LostWorldsBlocks.LIMESTONE), getWhenCan(LostWorldsBlocks.MOSSY_SOIL), getWhenCan(LostWorldsBlocks.DRIED_SOIL), getWhenCan(LostWorldsBlocks.CRACKED_SOIL), getWhenCan(LostWorldsBlocks.VOLCANIC_ASH), getWhenCan(LostWorldsBlocks.VOLCANIC_ASH_LAYER), Blocks.BASALT, Blocks.BLACKSTONE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.MAGMA_BLOCK, Blocks.STONE, Blocks.SNOW_BLOCK);
		this.replaceableBlocks = ImmutableSet.copyOf(this.blocks.get());
	}
}
