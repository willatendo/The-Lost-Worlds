package lostworlds.server.biome;

import static lostworlds.server.biome.LostWorldsConfiguredFeatures.register;

import com.google.common.collect.ImmutableList;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.core.Holder;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;

public class DisksFeatures {
	public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> MUD_DISK = register("mud_disk", Feature.DISK, new DiskConfiguration(LostWorldsBlocks.MUD.getDefaultState(), UniformInt.of(2, 1), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.MUD.getDefaultState())));

	public static void init() {
	}
}
