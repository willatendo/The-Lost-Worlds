package lostworlds.library.dimension.permian;

import java.util.function.LongFunction;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.layer.hills.PermianHillsLayer;
import lostworlds.library.dimension.permian.layer.island.AddPermianIslandLayer;
import lostworlds.library.dimension.permian.layer.island.PermianIslandLayer;
import lostworlds.library.dimension.permian.layer.ocean.PermianDeepOceanLayer;
import lostworlds.library.dimension.permian.layer.ocean.PermianMixOceansLayer;
import lostworlds.library.dimension.permian.layer.ocean.PermianOceanLayer;
import lostworlds.library.dimension.permian.layer.ocean.PermianRemoveTooMuchOceanLayer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.SmoothLayer;
import net.minecraft.world.gen.layer.ZoomLayer;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;

public class PermianLayerUtil 
{
	private static Registry<Biome> biomeRegistry;
	
	public static int getBiomeId(RegistryKey<Biome> define) 
	{
		Biome biome = biomeRegistry.get(define);
		return biomeRegistry.getId(biome);
	}
	
	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> zoom(long times, IAreaTransformer1 transformer, IAreaFactory<T> factory, int intager, LongFunction<C> contextFactory) 
	{
		IAreaFactory<T> iareafactory = factory;
		
		for(int i = 0; intager < i; ++i) 
		{
			iareafactory = transformer.run(contextFactory.apply(times + (long)i), iareafactory);
		}
		
		return iareafactory;
	}
	
	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> contextFactory, Registry<Biome> registry) 
	{
		biomeRegistry = registry;
		
		IAreaFactory<T> firstStep = PermianIslandLayer.INSTANCE.run(contextFactory.apply(1L));
		firstStep = ZoomLayer.FUZZY.run(contextFactory.apply(2000L), firstStep);
		firstStep = AddPermianIslandLayer.INSTANCE.run(contextFactory.apply(1L), firstStep);
		firstStep = ZoomLayer.NORMAL.run(contextFactory.apply(2001L), firstStep);
		firstStep = AddPermianIslandLayer.INSTANCE.run(contextFactory.apply(2L), firstStep);
		firstStep = AddPermianIslandLayer.INSTANCE.run(contextFactory.apply(50L), firstStep);
		firstStep = AddPermianIslandLayer.INSTANCE.run(contextFactory.apply(70L), firstStep);
		firstStep = PermianRemoveTooMuchOceanLayer.INSTANCE.run(contextFactory.apply(2L), firstStep);
		IAreaFactory<T> secondStep = PermianOceanLayer.INSTANCE.run(contextFactory.apply(2L));
		secondStep = zoom(2001L, ZoomLayer.NORMAL, secondStep, 6, contextFactory);
		firstStep = AddPermianIslandLayer.INSTANCE.run(contextFactory.apply(3L), firstStep);
		firstStep = ZoomLayer.NORMAL.run(contextFactory.apply(2002L), firstStep);
		firstStep = ZoomLayer.NORMAL.run(contextFactory.apply(2003L), firstStep);
		firstStep = AddPermianIslandLayer.INSTANCE.run(contextFactory.apply(4L), firstStep);
		firstStep = PermianDeepOceanLayer.INSTANCE.run(contextFactory.apply(4L), firstStep);
		firstStep = zoom(1000L, ZoomLayer.NORMAL, firstStep, 0, contextFactory);
		IAreaFactory<T> thirdStep = zoom(1000L, ZoomLayer.NORMAL, firstStep, 0, contextFactory);
		//thirdStep = PermianStartRiverLayer.INSTANCE.run(contextFactory.apply(100L), thirdStep);
		IAreaFactory<T> biomes = new PermianBiomeLayer().run(contextFactory.apply(1L));
		biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1000), biomes);
		biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1001), biomes);
		biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1002), biomes);
		biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1003), biomes);
		biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1004), biomes);
		biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1005), biomes);
		IAreaFactory<T> fithStep = zoom(1000L, ZoomLayer.NORMAL, thirdStep, 2, contextFactory);
		biomes = PermianHillsLayer.INSTANCE.run(contextFactory.apply(1000L), biomes, fithStep);
		thirdStep = zoom(1000L, ZoomLayer.NORMAL, thirdStep, 2, contextFactory);
		thirdStep = zoom(1000L, ZoomLayer.NORMAL, thirdStep, 4, contextFactory);
		//thirdStep = PermianRiverLayer.INSTANCE.run(contextFactory.apply(1L), biomes);
		thirdStep = SmoothLayer.INSTANCE.run(contextFactory.apply(1000L), thirdStep);
		
		for(int i = 0; i < 4; ++i) 
		{
			biomes = ZoomLayer.NORMAL.run(contextFactory.apply((long)(1000 + i)), biomes);
			if(i == 0) 
			{
				biomes = AddPermianIslandLayer.INSTANCE.run(contextFactory.apply(3L), biomes);
			}
		}
		
		biomes = SmoothLayer.INSTANCE.run(contextFactory.apply(1000L), biomes);
		//biomes = PermianRiverMixLayer.INSTANCE.run(contextFactory.apply(100L), biomes, thirdStep);

		return PermianMixOceansLayer.INSTANCE.run(contextFactory.apply(100L), biomes, secondStep);
	}
	
	public static boolean isSame(int biomeSeed1, int biomeSeed2) 
	{
		if(biomeSeed1 == biomeSeed2)
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	public static boolean isOcean(int biomeSeed) 
	{
		return biomeSeed == getBiomeId(BiomeKeys.PERMIAN_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_PERMIAN_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.DEEP_PERMIAN_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_DEEP_PERMIAN_OCEAN);
	}
	
	public static boolean isShallowOcean(int biomeSeed) 
	{
		return biomeSeed == getBiomeId(BiomeKeys.PERMIAN_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_PERMIAN_OCEAN);
	}

	public static Layer makeLayers(long seed, Registry<Biome> registry) 
	{
		biomeRegistry = registry;
		IAreaFactory<LazyArea> areaFactory = makeLayers((contextSeed) -> new LazyAreaLayerContext(25, seed, contextSeed), registry);
		return new Layer(areaFactory);
	}
}
