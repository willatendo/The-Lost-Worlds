package lostworlds.library.dimension.jurassic;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import lostworlds.library.biome.BiomeKeys;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.layer.Layer;
import tyrannotitanlib.library.base.dimension.WorldSeedHolder;

public class JurassicBiomeProvider extends BiomeProvider
{
	public static final Codec<JurassicBiomeProvider> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codec.LONG.fieldOf("seed").orElse(JurassicChunkGenerator.hackSeed).forGetter((obj) -> obj.seed), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((obj) -> obj.registry)).apply(instance, instance.stable(JurassicBiomeProvider::new)));

	private final long seed;
	private final Registry<Biome> registry;
	private final Layer genBiomes;
	private static final List<RegistryKey<Biome>> POSSIBLE_BIOMES = ImmutableList.of
	(
		BiomeKeys.JURASSIC_DESERT,
		BiomeKeys.JURASSIC_DESERT_HILLS,
		BiomeKeys.JURASSIC_ARAUCARIA_FOREST, 
		BiomeKeys.JURASSIC_ARAUCARIA_FOREST_HILLS, 
		BiomeKeys.JURASSIC_CONIFER_FOREST, 
		BiomeKeys.JURASSIC_CONIFER_FOREST_HILLS, 
		BiomeKeys.JURASSIC_GINKGO_FOREST,
		BiomeKeys.JURASSIC_GINKGO_FOREST_HILLS,
		BiomeKeys.JURASSIC_PLAINS, 
		BiomeKeys.JURASSIC_PLAINS_HILLS, 
		BiomeKeys.JURASSIC_MARSH, 
		BiomeKeys.JURASSIC_SWAMP, 
		BiomeKeys.JURASSIC_FEN,
		BiomeKeys.JURASSIC_BOG, 
		BiomeKeys.JURASSIC_MOUNTAINS, 
		BiomeKeys.JURASSIC_REDWOODS_FOREST,
		BiomeKeys.JURASSIC_REDWOODS_FOREST_HILLS,
		BiomeKeys.JURASSIC_ERRODED_MOUNTAINS, 
		BiomeKeys.JURASSIC_VOLCANIC_RANGE,
		BiomeKeys.PERMIAN_OCEAN,
		BiomeKeys.DEEP_PERMIAN_OCEAN,
		BiomeKeys.WARM_PERMIAN_OCEAN,
		BiomeKeys.WARM_DEEP_PERMIAN_OCEAN,
		BiomeKeys.JURASSIC_RIVER
	);
	
	public JurassicBiomeProvider(long seed, Registry<Biome> registry) 
	{		
		super(POSSIBLE_BIOMES.stream().map(define -> () -> registry.getOrThrow(define)));
		this.seed = WorldSeedHolder.getSeed();
		this.registry = registry;
		this.genBiomes = JurassicLayerUtil.buildJurassic(WorldSeedHolder.getSeed(), registry);
	}
	
	@Override
	public BiomeProvider withSeed(long seed) 
	{
		return new JurassicBiomeProvider(seed, registry);
	}
	
	@Override
	protected Codec<? extends BiomeProvider> codec() 
	{
		return CODEC;
	}
	
	@Override
	public Biome getNoiseBiome(int x, int y, int z) 
	{
		return this.genBiomes.get(this.registry, x, z);
	}
}
