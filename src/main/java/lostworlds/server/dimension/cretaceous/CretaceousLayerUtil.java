package lostworlds.server.dimension.cretaceous;

import java.util.function.LongFunction;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.cretaceous.layer.CretaceousAddInlandLayer;
import lostworlds.server.dimension.cretaceous.layer.CretaceousAddIslandLayer;
import lostworlds.server.dimension.cretaceous.layer.CretaceousAddSubBiomeLayer;
import lostworlds.server.dimension.cretaceous.layer.CretaceousAddWeightedSubBiomeLayer;
import lostworlds.server.dimension.cretaceous.layer.CretaceousIslandLayer;
import lostworlds.server.dimension.cretaceous.layer.CretaceousLookupLayer;
import lostworlds.server.dimension.cretaceous.layer.CretaceousRiverInitLayer;
import lostworlds.server.dimension.cretaceous.layer.CretaceousRiverLayer;
import lostworlds.server.dimension.cretaceous.layer.CretaceousRiverMixLayer;
import lostworlds.server.dimension.cretaceous.layer.CretaceousShoreLayer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.newbiome.context.BigContext;
import net.minecraft.world.level.newbiome.context.LazyAreaContext;
import net.minecraft.world.level.newbiome.area.Area;
import net.minecraft.world.level.newbiome.area.AreaFactory;
import net.minecraft.world.level.newbiome.area.LazyArea;
import net.minecraft.world.level.newbiome.layer.Layer;
import net.minecraft.world.level.newbiome.layer.SmoothLayer;
import net.minecraft.world.level.newbiome.layer.ZoomLayer;
import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer1;

public class CretaceousLayerUtil {
	private static Registry<Biome> biomeRegistry;

	public static int getBiomeId(ResourceKey<Biome> define) {
		Biome biome = biomeRegistry.get(define);
		return biomeRegistry.getId(biome);
	}

	public static Layer buildCretaceous(long seed, Registry<Biome> registry) {
		biomeRegistry = registry;

		final AreaFactory<LazyArea> noiseLayer = makeLayers(procedure -> new LazyAreaContext(25, seed, procedure), registry);
		return new CretaceousLookupLayer(noiseLayer);
	}

	public static <T extends Area, C extends BigContext<T>> AreaFactory<T> makeLayers(LongFunction<C> context, Registry<Biome> registry) {
		AreaFactory<T> islandLayer = new CretaceousIslandLayer().run(context.apply(1));
		AreaFactory<T> fuzzyZoomLayer = ZoomLayer.FUZZY.run(context.apply(2000), islandLayer);
		AreaFactory<T> addIslandLayer = CretaceousAddIslandLayer.forest3().run(context.apply(3), fuzzyZoomLayer);
		AreaFactory<T> zoomLayer = ZoomLayer.NORMAL.run(context.apply(2000), addIslandLayer);

		AreaFactory<T> oceanLayer = new CretaceousAddInlandLayer(20).run(context.apply(9), zoomLayer);
		oceanLayer = ZoomLayer.NORMAL.run(context.apply(9), oceanLayer);
		addIslandLayer = CretaceousAddIslandLayer.mountains().run(context.apply(6), oceanLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2001), addIslandLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2004), zoomLayer);
		addIslandLayer = CretaceousAddIslandLayer.forest2().run(context.apply(8), zoomLayer);

		AreaFactory<T> biomeLayerGen = new CretaceousBiomeLayer().run(context.apply(15), addIslandLayer);
		AreaFactory<T> oceanLayerGen = CretaceousAddWeightedSubBiomeLayer.ocean().run(context.apply(16), biomeLayerGen);
		AreaFactory<T> araucariaForest = CretaceousAddSubBiomeLayer.araucariaForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), araucariaForest);
		AreaFactory<T> coniferForest = CretaceousAddSubBiomeLayer.coniferForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), coniferForest);
		AreaFactory<T> ginkgoForest = CretaceousAddSubBiomeLayer.ginkgoForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), ginkgoForest);
		AreaFactory<T> frozenForest = CretaceousAddSubBiomeLayer.frozenForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), frozenForest);
		AreaFactory<T> desert = CretaceousAddSubBiomeLayer.desert().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), desert);
		AreaFactory<T> redDesert = CretaceousAddSubBiomeLayer.redDesert().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), redDesert);
		AreaFactory<T> plains = CretaceousAddSubBiomeLayer.plains().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), plains);
		AreaFactory<T> arctic = CretaceousAddSubBiomeLayer.arctic().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), arctic);

		AreaFactory<T> riverLayer = zoomLayer;
		riverLayer = new CretaceousRiverInitLayer().run(context.apply(12), riverLayer);
		riverLayer = magnify(2007, ZoomLayer.NORMAL, riverLayer, 5, context);
		riverLayer = new CretaceousRiverLayer().run(context.apply(13), riverLayer);
		riverLayer = SmoothLayer.INSTANCE.run(context.apply(2008L), riverLayer);

		AreaFactory<T> magnifyLayer = magnify(2007L, ZoomLayer.NORMAL, zoomLayer, 3, context);
		AreaFactory<T> biomeLayer = new CretaceousShoreLayer().run(context.apply(20), magnifyLayer);
		biomeLayer = magnify(20, ZoomLayer.NORMAL, biomeLayer, 2, context);

		biomeLayer = SmoothLayer.INSTANCE.run(context.apply(17L), biomeLayer);
		biomeLayer = new CretaceousRiverMixLayer().run(context.apply(17), biomeLayer, riverLayer);

		return biomeLayer;
	}

	public static boolean isSame(int biomeSeed1, int biomeSeed2) {
		if (biomeSeed1 == biomeSeed2) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isOcean(int biomeSeed) {
		return biomeSeed == getBiomeId(BiomeKeys.CRETACEOUS_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_CRETACEOUS_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.COLD_CRETACEOUS_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.DEEP_CRETACEOUS_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_DEEP_CRETACEOUS_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.COLD_DEEP_CRETACEOUS_OCEAN);
	}

	public static boolean isRiver(int biomeSeed) {
		return biomeSeed == getBiomeId(BiomeKeys.CRETACEOUS_RIVER);
	}

	public static boolean isLand(int biomeSeed) {
		return biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST_HILLS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARCTIC) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARCTIC_HILLS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARCTIC_SPIRES) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_BOG) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_CONIFER_FOREST) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_CONIFER_FOREST_HILLS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_DESERT) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_DESERT_HILLS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ERRODED_MOUNTAINS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_FEN) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_FLOOD_BASALTS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_FROZEN_FOREST) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_FROZEN_FOREST_HILLS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_GAME_TRAIL) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_GINKGO_FOREST) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_GINKGO_FOREST_HILLS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_MARSH) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_MEDOW) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_MOUNTAINS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_PLAINS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_PLAINS_HILLS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_RED_DESERT) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_RED_DESERT_HILLS) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_RIVER) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_SHORE) || biomeSeed == CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_SWAMP);
	}

	private static <T extends Area, C extends BigContext<T>> AreaFactory<T> magnify(long seed, AreaTransformer1 zoomLayer, AreaFactory<T> layer, int count, LongFunction<C> context) {
		AreaFactory<T> result = layer;
		for (int i = 0; i < count; i++) {
			result = zoomLayer.run(context.apply(seed + i), result);
		}
		return result;
	}
}
