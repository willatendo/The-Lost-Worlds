package lostworlds.library.dimension.permian;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import lostworlds.library.biome.BiomeKeys;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.layer.Layer;

public class PermianBiomeProvider extends BiomeProvider
{
	public static final Codec<PermianBiomeProvider> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codec.LONG.fieldOf("seed").orElse(PermianChunkGenerator.hackSeed).forGetter((obj) -> obj.seed), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((obj) -> obj.registry)).apply(instance, instance.stable(PermianBiomeProvider::new)));

	private final long seed;
	private final Registry<Biome> registry;
	private final Layer genBiomes;
	private static final List<RegistryKey<Biome>> BIOMES = ImmutableList.of(BiomeKeys.PERMIAN_DESERT, BiomeKeys.PERMIAN_DESERT_HILLS, BiomeKeys.PERMIAN_DRIED_PLAINS, BiomeKeys.PERMIAN_DRIED_PLAINS_HILLS, BiomeKeys.PERMIAN_FLOOD_BASALTS, BiomeKeys.PERMIAN_ASHY_MEDOWS, BiomeKeys.PERMIAN_MOUNTAINS, BiomeKeys.PERMIAN_CONIFER_FOREST,BiomeKeys.PERMIAN_CONIFER_FOREST, BiomeKeys.PERMIAN_CONIFER_FOREST_HILLS, BiomeKeys.PERMIAN_GINKGO_FOREST, BiomeKeys.PERMIAN_GINKGO_FOREST_HILLS, BiomeKeys.PERMIAN_PLAINS, BiomeKeys.PERMIAN_PLAINS_HILLS, BiomeKeys.PERMIAN_OCEAN, BiomeKeys.PERMIAN_DEEP_OCEAN);
	
	public PermianBiomeProvider(long seed, Registry<Biome> registry) 
	{		
		super(BIOMES.stream().map(define -> () -> registry.getOrThrow(define)));
		this.seed = seed;
		this.registry = registry;
		this.genBiomes = PermianLayerUtil.makeLayers(seed, registry);
	}

	@Override
	public BiomeProvider withSeed(long seed) 
	{
		return new PermianBiomeProvider(seed, registry);
	}
	
	@Override
	protected Codec<? extends BiomeProvider> codec() 
	{
		return CODEC;
	}
	
	@Override
	public Biome getNoiseBiome(int x, int y, int z) 
	{
		return this.getBiomeFromPos(registry, x, z);
	}

	public Biome getBiomeFromPos(Registry<Biome> registry, int x, int z) 
	{
		int i = genBiomes.area.get(x, z);
		Biome biome = registry.byId(i);
		if(biome == null) 
		{
			if(SharedConstants.IS_RUNNING_IN_IDE) 
			{
				throw Util.pauseInIde(new IllegalStateException("Unknown biome id: " + i));
			} 
			else 
			{
				return registry.get(BiomeRegistry.byId(0));
			}
		} 
		else 
		{
			return biome;
		}
	}
}
