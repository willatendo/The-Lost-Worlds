package lostworlds.server.dimension.cretaceous;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.WorldSeedHolder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.newbiome.layer.Layer;

public class CretaceousBiomeProvider extends BiomeSource {
	public static final Codec<CretaceousBiomeProvider> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codec.LONG.fieldOf("seed").orElse(CretaceousChunkGenerator.hackSeed).forGetter((obj) -> obj.seed), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((obj) -> obj.registry)).apply(instance, instance.stable(CretaceousBiomeProvider::new)));

	private final long seed;
	private final Registry<Biome> registry;
	private final Layer genBiomes;
	private static final List<ResourceKey<Biome>> POSSIBLE_BIOMES = ImmutableList.of(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST, BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST_HILLS, BiomeKeys.CRETACEOUS_ARCTIC, BiomeKeys.CRETACEOUS_ARCTIC_HILLS, BiomeKeys.CRETACEOUS_ARCTIC_SPIRES, BiomeKeys.CRETACEOUS_BOG, BiomeKeys.CRETACEOUS_CONIFER_FOREST, BiomeKeys.CRETACEOUS_CONIFER_FOREST_HILLS, BiomeKeys.CRETACEOUS_DESERT, BiomeKeys.CRETACEOUS_DESERT_HILLS, BiomeKeys.CRETACEOUS_ERRODED_MOUNTAINS, BiomeKeys.CRETACEOUS_FEN, BiomeKeys.CRETACEOUS_FLOOD_BASALTS, BiomeKeys.CRETACEOUS_FROZEN_FOREST, BiomeKeys.CRETACEOUS_FROZEN_FOREST_HILLS, BiomeKeys.CRETACEOUS_GAME_TRAIL, BiomeKeys.CRETACEOUS_GINKGO_FOREST, BiomeKeys.CRETACEOUS_GINKGO_FOREST_HILLS, BiomeKeys.CRETACEOUS_MARSH, BiomeKeys.CRETACEOUS_MEDOW, BiomeKeys.CRETACEOUS_MOUNTAINS, BiomeKeys.CRETACEOUS_OCEAN, BiomeKeys.CRETACEOUS_PLAINS, BiomeKeys.CRETACEOUS_PLAINS_HILLS, BiomeKeys.CRETACEOUS_RED_DESERT, BiomeKeys.CRETACEOUS_RED_DESERT_HILLS, BiomeKeys.CRETACEOUS_RIVER, BiomeKeys.CRETACEOUS_SHORE, BiomeKeys.CRETACEOUS_SWAMP, BiomeKeys.COLD_CRETACEOUS_OCEAN, BiomeKeys.COLD_DEEP_CRETACEOUS_OCEAN, BiomeKeys.DEEP_CRETACEOUS_OCEAN, BiomeKeys.WARM_CRETACEOUS_OCEAN, BiomeKeys.WARM_DEEP_CRETACEOUS_OCEAN);

	public CretaceousBiomeProvider(long seed, Registry<Biome> registry) {
		super(POSSIBLE_BIOMES.stream().map(define -> () -> registry.getOrThrow(define)));
		this.seed = WorldSeedHolder.getSeed();
		this.registry = registry;
		this.genBiomes = CretaceousLayerUtil.buildCretaceous(WorldSeedHolder.getSeed(), registry);
	}

	@Override
	public BiomeSource withSeed(long seed) {
		return new CretaceousBiomeProvider(seed, registry);
	}

	@Override
	protected Codec<? extends BiomeSource> codec() {
		return CODEC;
	}

	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return this.genBiomes.get(this.registry, x, z);
	}
}
