package lostworlds.server.biome;

import static lostworlds.server.biome.ModConfiguredFeatures.register;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;

public class OverworldFeatures {
	public static final Supplier<ConfiguredFeature<?, ?>> MUD_DISK = () -> register("mud_disk", Feature.DISK.configured(new SphereReplaceConfig(LostWorldsBlocks.MUD.getDefaultState(), FeatureSpread.of(2, 1), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.MUD.getDefaultState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
	public static final Supplier<ConfiguredFeature<?, ?>> SILT_PATCH = () -> register("silt_patch", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, LostWorldsBlocks.SILT.getDefaultState(), LostWorldsConfig.SERVER_CONFIG.siltVeinSize.get())).range(LostWorldsConfig.SERVER_CONFIG.siltRange.get()).squared().count(LostWorldsConfig.SERVER_CONFIG.siltCountPerChunk.get()));
}
