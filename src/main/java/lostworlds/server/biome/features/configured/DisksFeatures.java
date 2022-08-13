package lostworlds.server.biome.features.configured;

import static lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures.register;

import com.google.common.collect.ImmutableList;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraftforge.registries.RegistryObject;

public class DisksFeatures {
	public static final RegistryObject<ConfiguredFeature<?, ?>> MUD_DISK = register("mud_disk", Feature.DISK, new DiskConfiguration(LostWorldsBlocks.MUD.getDefaultState(), UniformInt.of(2, 3), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), LostWorldsBlocks.MUD.getDefaultState())));

	public static void init() {
	}
}
