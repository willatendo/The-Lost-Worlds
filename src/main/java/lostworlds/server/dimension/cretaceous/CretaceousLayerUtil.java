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

public class CretaceousLayerUtil {
	private static Registry<Biome> biomeRegistry;

	public static int getBiomeId(RegistryKey<Biome> define) {
		Biome biome = biomeRegistry.get(define);
		return biomeRegistry.getId(biome);
	}

	public static Layer buildCretaceous(long seed, Registry<Biome> registry) {
		biomeRegistry = registry;

		final IAreaFactory<LazyArea> noiseLayer = makeLayers(procedure -> new LazyAreaLayerContext(25, seed, procedure), registry);
		return new CretaceousLookupLayer(noiseLayer);
	}

	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> context, Registry<Biome> registry) {
		IAreaFactory<T> islandLayer = new CretaceousIslandLayer().run(context.apply(1));
		IAreaFactory<T> fuzzyZoomLayer = ZoomLayer.FUZZY.run(context.apply(2000), islandLayer);
		IAreaFactory<T> addIslandLayer = CretaceousAddIslandLayer.forest3().run(context.apply(3), fuzzyZoomLayer);
		IAreaFactory<T> zoomLayer = ZoomLayer.NORMAL.run(context.apply(2000), addIslandLayer);

		IAreaFactory<T> oceanLayer = new CretaceousAddInlandLayer(20).run(context.apply(9), zoomLayer);
		oceanLayer = ZoomLayer.NORMAL.run(context.apply(9), oceanLayer);
		addIslandLayer = CretaceousAddIslandLayer.mountains().run(context.apply(6), oceanLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2001), addIslandLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2004), zoomLayer);
		addIslandLayer = CretaceousAddIslandLayer.forest2().run(context.apply(8), zoomLayer);

		IAreaFactory<T> biomeLayerGen = new CretaceousBiomeLayer().run(context.apply(15), addIslandLayer);
		IAreaFactory<T> oceanLayerGen = CretaceousAddWeightedSubBiomeLayer.ocean().run(context.apply(16), biomeLayerGen);
		IAreaFactory<T> araucariaForest = CretaceousAddSubBiomeLayer.araucariaForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), araucariaForest);
		IAreaFactory<T> coniferForest = CretaceousAddSubBiomeLayer.coniferForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), coniferForest);
		IAreaFactory<T> ginkgoForest = CretaceousAddSubBiomeLayer.ginkgoForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), ginkgoForest);
		IAreaFactory<T> frozenForest = CretaceousAddSubBiomeLayer.frozenForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), frozenForest);
		IAreaFactory<T> desert = CretaceousAddSubBiomeLayer.desert().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), desert);
		IAreaFactory<T> redDesert = CretaceousAddSubBiomeLayer.redDesert().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), redDesert);
		IAreaFactory<T> plains = CretaceousAddSubBiomeLayer.plains().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), plains);
		IAreaFactory<T> arctic = CretaceousAddSubBiomeLayer.arctic().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), arctic);

		IAreaFactory<T> riverLayer = zoomLayer;
		riverLayer = new CretaceousRiverInitLayer().run(context.apply(12), riverLayer);
		riverLayer = magnify(2007, ZoomLayer.NORMAL, riverLayer, 5, context);
		riverLayer = new CretaceousRiverLayer().run(context.apply(13), riverLayer);
		riverLayer = SmoothLayer.INSTANCE.run(context.apply(2008L), riverLayer);

		IAreaFactory<T> magnifyLayer = magnify(2007L, ZoomLayer.NORMAL, zoomLayer, 3, context);
		IAreaFactory<T> biomeLayer = new CretaceousShoreLayer().run(context.apply(20), magnifyLayer);
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

	private static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> magnify(long seed, IAreaTransformer1 zoomLayer, IAreaFactory<T> layer, int count, LongFunction<C> context) {
		IAreaFactory<T> result = layer;
		for (int i = 0; i < count; i++) {
			result = zoomLayer.run(context.apply(seed + i), result);
		}
		return result;
	}
}
