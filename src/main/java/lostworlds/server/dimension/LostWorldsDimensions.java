package lostworlds.server.dimension;

import java.util.function.Supplier;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class LostWorldsDimensions {
	public static final ResourceKey<Level> CRETACEOUS_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("cretaceous"));
	public static final ResourceKey<LevelStem> CRETACEOUS_DIMENSION = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, LostWorldsUtils.rL("cretaceous"));
	public static final ResourceKey<DimensionType> CRETACEOUS_DIMENSION_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("cretaceous"));

	public static final ResourceKey<Level> JURASSIC_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("jurassic"));
	public static final ResourceKey<LevelStem> JURASSIC_DIMENSION = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, LostWorldsUtils.rL("jurassic"));
	public static final ResourceKey<DimensionType> JURASSIC_DIMENSION_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("jurassic"));

	public static final ResourceKey<Level> PERMIAN_LEVEL = ResourceKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("permian"));
	public static final ResourceKey<LevelStem> PERMIAN_DIMENSION = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, LostWorldsUtils.rL("permian"));
	public static final ResourceKey<DimensionType> PERMIAN_DIMENSION_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("permian"));

	public static LevelStem createDimension(ResourceKey<DimensionType> dimensionType, ResourceKey<NoiseGeneratorSettings> noiseGeneratorSettings, Registry<DimensionType> dimensionTypeRegistry, Registry<NormalNoise.NoiseParameters> noiseParametersRegistry, Registry<StructureSet> structureSetRegistry, Registry<Biome> biomeRegistry, Registry<NoiseGeneratorSettings> dimensionSettingsRegistry, long seed) {
		Supplier<NoiseGeneratorSettings> dimensionSettings = () -> {
			NoiseGeneratorSettings settings = dimensionSettingsRegistry.get(noiseGeneratorSettings);
			return settings != null ? settings : dimensionSettingsRegistry.getOrThrow(NoiseGeneratorSettings.OVERWORLD);
		};
		BiomeSource biomeSource = LostWorldsNoise.PERMIAN.biomeSource(biomeRegistry);
		ChunkGenerator generator = new NoiseBasedChunkGenerator(structureSetRegistry, noiseParametersRegistry, biomeSource, seed, dimensionSettingsRegistry.getHolderOrThrow(dimensionSettingsRegistry.getResourceKey(dimensionSettings.get()).get()));

		return new LevelStem(dimensionTypeRegistry.getHolderOrThrow(dimensionType), generator);
	}
}
