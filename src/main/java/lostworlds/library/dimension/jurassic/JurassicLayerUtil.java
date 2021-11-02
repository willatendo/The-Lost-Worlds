package lostworlds.library.dimension.jurassic;

import java.util.function.LongFunction;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.jurassic.layer.JurassicAddInlandLayer;
import lostworlds.library.dimension.jurassic.layer.JurassicAddIslandLayer;
import lostworlds.library.dimension.jurassic.layer.JurassicAddSubBiomeLayer;
import lostworlds.library.dimension.jurassic.layer.JurassicAddWeightedSubBiomeLayer;
import lostworlds.library.dimension.jurassic.layer.JurassicIslandLayer;
import lostworlds.library.dimension.jurassic.layer.JurassicLookupLayer;
import lostworlds.library.dimension.jurassic.layer.JurassicRiverInitLayer;
import lostworlds.library.dimension.jurassic.layer.JurassicRiverLayer;
import lostworlds.library.dimension.jurassic.layer.JurassicRiverMixLayer;
import lostworlds.library.dimension.jurassic.layer.JurassicShoreLayer;
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

public class JurassicLayerUtil 
{
	private static Registry<Biome> biomeRegistry;

	public static int getBiomeId(RegistryKey<Biome> define) 
	{
		Biome biome = biomeRegistry.get(define);
		return biomeRegistry.getId(biome);
	}
	
	public static Layer buildJurassic(long seed, Registry<Biome> registry) 
	{
		biomeRegistry = registry;
		
		final IAreaFactory<LazyArea> noiseLayer = makeLayers(procedure -> new LazyAreaLayerContext(25, seed, procedure), registry);
		return new JurassicLookupLayer(noiseLayer);
	}

	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> context, Registry<Biome> registry) 
	{
		IAreaFactory<T> islandLayer = new JurassicIslandLayer().run(context.apply(1));
		IAreaFactory<T> fuzzyZoomLayer = ZoomLayer.FUZZY.run(context.apply(2000), islandLayer);
		IAreaFactory<T> addIslandLayer = JurassicAddIslandLayer.forest3().run(context.apply(3), fuzzyZoomLayer);
		IAreaFactory<T> zoomLayer = ZoomLayer.NORMAL.run(context.apply(2000), addIslandLayer);

		IAreaFactory<T> oceanLayer = new JurassicAddInlandLayer(20).run(context.apply(9), zoomLayer);
		oceanLayer = ZoomLayer.NORMAL.run(context.apply(9), oceanLayer);
		addIslandLayer = JurassicAddIslandLayer.mountains().run(context.apply(6), oceanLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2001), addIslandLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2004), zoomLayer);
		addIslandLayer = JurassicAddIslandLayer.forest2().run(context.apply(8), zoomLayer);

		IAreaFactory<T> biomeLayerGen = new JurassicBiomeLayer().run(context.apply(15), addIslandLayer);
		IAreaFactory<T> oceanLayerGen = JurassicAddWeightedSubBiomeLayer.ocean().run(context.apply(16), biomeLayerGen);
		IAreaFactory<T> araucariaForest = JurassicAddSubBiomeLayer.araucariaForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), araucariaForest);
		IAreaFactory<T> coniferForest = JurassicAddSubBiomeLayer.coniferForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), coniferForest);
		IAreaFactory<T> ginkgoForest = JurassicAddSubBiomeLayer.ginkgoForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), ginkgoForest);
		IAreaFactory<T> redwoodsForest = JurassicAddSubBiomeLayer.redwoodsForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), redwoodsForest);
		IAreaFactory<T> desert = JurassicAddSubBiomeLayer.desert().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), desert);
		IAreaFactory<T> plains = JurassicAddSubBiomeLayer.plains().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), plains);

		IAreaFactory<T> riverLayer = zoomLayer;
		riverLayer = new JurassicRiverInitLayer().run(context.apply(12), riverLayer);
		riverLayer = magnify(2007, ZoomLayer.NORMAL, riverLayer, 5, context);
		riverLayer = new JurassicRiverLayer().run(context.apply(13), riverLayer);
		riverLayer = SmoothLayer.INSTANCE.run(context.apply(2008L), riverLayer);

		IAreaFactory<T> magnifyLayer = magnify(2007L, ZoomLayer.NORMAL, zoomLayer, 3, context);
		IAreaFactory<T> biomeLayer = new JurassicShoreLayer().run(context.apply(20), magnifyLayer);
		biomeLayer = magnify(20, ZoomLayer.NORMAL, biomeLayer, 2, context);
		
		biomeLayer = SmoothLayer.INSTANCE.run(context.apply(17L), biomeLayer);
		biomeLayer = new JurassicRiverMixLayer().run(context.apply(17), biomeLayer, riverLayer);

		return biomeLayer;
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
		return biomeSeed == getBiomeId(BiomeKeys.JURASSIC_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_JURASSIC_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.DEEP_JURASSIC_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_DEEP_JURASSIC_OCEAN);
	}
	
	public static boolean isRiver(int biomeSeed) 
	{
		return biomeSeed == getBiomeId(BiomeKeys.JURASSIC_RIVER);
	}

	public static boolean isLand(int biomeSeed) 
	{
		return biomeSeed == getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_BOG) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_FEN) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_SWAMP) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_MARSH) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_CONIFER_FOREST) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_CONIFER_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_DESERT) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_DESERT_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_GINKGO_FOREST) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_GINKGO_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_REDWOODS_FOREST) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_REDWOODS_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_PLAINS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_PLAINS_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_MOUNTAINS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_ERRODED_MOUNTAINS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_VOLCANIC_RANGE);
	}
	
	private static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> magnify(long seed, IAreaTransformer1 zoomLayer, IAreaFactory<T> layer, int count, LongFunction<C> context) 
	{
		IAreaFactory<T> result = layer;
		for(int i = 0; i < count; i++) 
		{
			result = zoomLayer.run(context.apply(seed + i), result);
		}
		return result;
	}
}
