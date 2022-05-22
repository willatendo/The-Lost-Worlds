package lostworlds.server.biome;

import static lostworlds.server.biome.LostWorldsConfiguredFeatures.register;

import com.google.common.collect.ImmutableList;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.util.UniformInt;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;

public class DisksFeatures {
	public static final ConfiguredFeature<?, ?> MUD_DISK = register("mud_disk", Feature.DISK.configured(new DiskConfiguration(LostWorldsBlocks.MUD.getDefaultState(), UniformInt.of(2, 1), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.MUD.getDefaultState()))).decorated(Features.Decorators.TOP_SOLID_HEIGHTMAP_SQUARE));

	public static void init() {
	}
}
