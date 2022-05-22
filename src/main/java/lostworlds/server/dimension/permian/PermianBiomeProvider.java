package lostworlds.server.dimension.permian;

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

public class PermianBiomeProvider extends BiomeSource {
	public static final Codec<PermianBiomeProvider> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codec.LONG.fieldOf("seed").orElse(PermianChunkGenerator.hackSeed).forGetter((obj) -> obj.seed), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((obj) -> obj.registry)).apply(instance, instance.stable(PermianBiomeProvider::new)));

	private final long seed;
	private final Registry<Biome> registry;
	private final Layer genBiomes;
	private static final List<ResourceKey<Biome>> POSSIBLE_BIOMES = ImmutableList.of(BiomeKeys.DEEP_PERMIAN_OCEAN, BiomeKeys.PERMIAN_ASHY_MEDOWS, BiomeKeys.PERMIAN_CONIFER_FOREST, BiomeKeys.PERMIAN_CONIFER_FOREST_HILLS, BiomeKeys.PERMIAN_DESERT, BiomeKeys.PERMIAN_DESERT_HILLS, BiomeKeys.PERMIAN_DRIED_PLAINS, BiomeKeys.PERMIAN_DRIED_PLAINS_HILLS, BiomeKeys.PERMIAN_FLOOD_BASALTS, BiomeKeys.PERMIAN_GINKGO_FOREST, BiomeKeys.PERMIAN_GINKGO_FOREST_HILLS, BiomeKeys.PERMIAN_MARSH, BiomeKeys.PERMIAN_MOUNTAINS, BiomeKeys.PERMIAN_OCEAN, BiomeKeys.PERMIAN_PLAINS, BiomeKeys.PERMIAN_PLAINS_HILLS, BiomeKeys.PERMIAN_RIVER, BiomeKeys.PERMIAN_SHORE, BiomeKeys.WARM_DEEP_PERMIAN_OCEAN, BiomeKeys.WARM_PERMIAN_OCEAN);

	public PermianBiomeProvider(long seed, Registry<Biome> registry) {
		super(POSSIBLE_BIOMES.stream().map(define -> () -> registry.getOrThrow(define)));
		this.seed = WorldSeedHolder.getSeed();
		this.registry = registry;
		this.genBiomes = PermianLayerUtil.buildPermian(WorldSeedHolder.getSeed(), registry);
	}

	@Override
	public BiomeSource withSeed(long seed) {
		return new PermianBiomeProvider(seed, registry);
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
