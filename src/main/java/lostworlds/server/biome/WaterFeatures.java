package lostworlds.server.biome;

import static lostworlds.server.biome.ModConfiguredFeatures.register;
import static lostworlds.server.util.BlockGetter.getWhenCan;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableSet;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class WaterFeatures {
	// Permian
	public static final Supplier<ConfiguredFeature<?, ?>> ANCIENT_SPRING = () -> register("ancient_spring", Feature.SPRING.configured(new LiquidsConfig(Fluids.WATER.defaultFluidState(), true, 4, 1, ImmutableSet.of(getWhenCan(LostWorldsBlocks.PERMIAN_STONE), LostWorldsBlocks.JURASSIC_STONE, Blocks.STONE))).decorated(Placement.RANGE_BIASED.configured(new TopSolidRangeConfig(8, 8, 256))).squared().count(50));
}
