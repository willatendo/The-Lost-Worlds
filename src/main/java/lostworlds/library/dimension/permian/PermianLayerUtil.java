package lostworlds.library.dimension.permian;

import java.util.function.LongFunction;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.layer.PermianAddInlandLayer;
import lostworlds.library.dimension.permian.layer.PermianAddIslandLayer;
import lostworlds.library.dimension.permian.layer.PermianAddSubBiomeLayer;
import lostworlds.library.dimension.permian.layer.PermianAddWeightedSubBiomeLayer;
import lostworlds.library.dimension.permian.layer.PermianIslandLayer;
import lostworlds.library.dimension.permian.layer.PermianLookupLayer;
import lostworlds.library.dimension.permian.layer.PermianRiverInitLayer;
import lostworlds.library.dimension.permian.layer.PermianRiverLayer;
import lostworlds.library.dimension.permian.layer.PermianRiverMixLayer;
import lostworlds.library.dimension.permian.layer.PermianShoreLayer;
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
	
	public static Layer buildPermian(long seed, Registry<Biome> registry) 
	{
		biomeRegistry = registry;
		
		final IAreaFactory<LazyArea> noiseLayer = makeLayers(procedure -> new LazyAreaLayerContext(25, seed, procedure), registry);
		return new PermianLookupLayer(noiseLayer);
	}

	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> context, Registry<Biome> registry) 
	{
		IAreaFactory<T> islandLayer = new PermianIslandLayer().run(context.apply(1));
		IAreaFactory<T> fuzzyZoomLayer = ZoomLayer.FUZZY.run(context.apply(2000), islandLayer);
		IAreaFactory<T> addIslandLayer = PermianAddIslandLayer.desert3().run(context.apply(3), fuzzyZoomLayer);
		IAreaFactory<T> zoomLayer = ZoomLayer.NORMAL.run(context.apply(2000), addIslandLayer);

		IAreaFactory<T> oceanLayer = new PermianAddInlandLayer(20).run(context.apply(9), zoomLayer);
		oceanLayer = ZoomLayer.NORMAL.run(context.apply(9), oceanLayer);
		addIslandLayer = PermianAddIslandLayer.mountains().run(context.apply(6), oceanLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2001), addIslandLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2004), zoomLayer);
		addIslandLayer = PermianAddIslandLayer.desert2().run(context.apply(8), zoomLayer);

		IAreaFactory<T> biomeLayerGen = new PermianBiomeLayer().run(context.apply(15), addIslandLayer);
		IAreaFactory<T> oceanLayerGen = PermianAddWeightedSubBiomeLayer.ocean().run(context.apply(16), biomeLayerGen);
		IAreaFactory<T> coniferForest = PermianAddSubBiomeLayer.coniferForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), coniferForest);
		IAreaFactory<T> desert = PermianAddSubBiomeLayer.desert().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), desert);
		IAreaFactory<T> driedPlains = PermianAddSubBiomeLayer.driedPlains().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), driedPlains);
		IAreaFactory<T> floodBasalts = PermianAddSubBiomeLayer.floodBasalts().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), floodBasalts);
		IAreaFactory<T> ginkgoForest = PermianAddSubBiomeLayer.ginkgoForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), ginkgoForest);
		IAreaFactory<T> plains = PermianAddSubBiomeLayer.plains().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), plains);

		IAreaFactory<T> riverLayer = zoomLayer;
		riverLayer = new PermianRiverInitLayer().run(context.apply(12), riverLayer);
		riverLayer = magnify(2007, ZoomLayer.NORMAL, riverLayer, 5, context);
		riverLayer = new PermianRiverLayer().run(context.apply(13), riverLayer);
		riverLayer = SmoothLayer.INSTANCE.run(context.apply(2008L), riverLayer);

		IAreaFactory<T> magnifyLayer = magnify(2007L, ZoomLayer.NORMAL, zoomLayer, 3, context);
		IAreaFactory<T> biomeLayer = new PermianShoreLayer().run(context.apply(20), magnifyLayer);
		biomeLayer = magnify(20, ZoomLayer.NORMAL, biomeLayer, 2, context);

		biomeLayer = SmoothLayer.INSTANCE.run(context.apply(17L), biomeLayer);
		biomeLayer = new PermianRiverMixLayer().run(context.apply(17), biomeLayer, riverLayer);

		return biomeLayer;
	}

	public static boolean isSame(int biomeSeed1, int biomeSeed2) 
	{
		if(biomeSeed1 == biomeSeed2) 
		{
			return true;
		} else {
			return false;
		}
	}

	public static boolean isOcean(int biomeSeed) 
	{
		return biomeSeed == getBiomeId(BiomeKeys.PERMIAN_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_PERMIAN_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.DEEP_PERMIAN_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_DEEP_PERMIAN_OCEAN);
	}
	
	public static boolean isRiver(int biomeSeed) 
	{
		return biomeSeed == getBiomeId(BiomeKeys.PERMIAN_RIVER);
	}

	public static boolean isLand(int biomeSeed) 
	{
		return biomeSeed == getBiomeId(BiomeKeys.PERMIAN_ASHY_MEDOWS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_DESERT) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_DESERT_HILLS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_DRIED_PLAINS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_DRIED_PLAINS_HILLS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_FLOOD_BASALTS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_GINKGO_FOREST) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_GINKGO_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_MARSH) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_MOUNTAINS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_SHORE) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_STONE_SHORE);
	}
	
	private static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> magnify(final long seed, final IAreaTransformer1 zoomLayer, final IAreaFactory<T> layer, final int count, final LongFunction<C> context) 
	{
		IAreaFactory<T> result = layer;
		for(int i = 0; i < count; i++) 
		{
			result = zoomLayer.run(context.apply(seed + i), result);
		}
		return result;
	}
}
