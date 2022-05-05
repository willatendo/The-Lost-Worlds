package lostworlds.server.biome;

import static lostworlds.server.biome.LostWorldsConfiguredFeatures.register;

import com.google.common.collect.ImmutableList;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.SphereReplaceConfig;

public class DisksFeatures {
	public static final ConfiguredFeature<?, ?> MUD_DISK = register("mud_disk", Feature.DISK.configured(new SphereReplaceConfig(LostWorldsBlocks.MUD.getDefaultState(), FeatureSpread.of(2, 1), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.MUD.getDefaultState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));

	public static void init() {
	}
}
