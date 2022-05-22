package lostworlds.server.dimension.jurassic;

import java.util.function.LongFunction;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.jurassic.layer.JurassicAddInlandLayer;
import lostworlds.server.dimension.jurassic.layer.JurassicAddIslandLayer;
import lostworlds.server.dimension.jurassic.layer.JurassicAddSubBiomeLayer;
import lostworlds.server.dimension.jurassic.layer.JurassicAddWeightedSubBiomeLayer;
import lostworlds.server.dimension.jurassic.layer.JurassicIslandLayer;
import lostworlds.server.dimension.jurassic.layer.JurassicLookupLayer;
import lostworlds.server.dimension.jurassic.layer.JurassicRiverInitLayer;
import lostworlds.server.dimension.jurassic.layer.JurassicRiverLayer;
import lostworlds.server.dimension.jurassic.layer.JurassicRiverMixLayer;
import lostworlds.server.dimension.jurassic.layer.JurassicShoreLayer;
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

public class JurassicLayerUtil {
	private static Registry<Biome> biomeRegistry;

	public static int getBiomeId(ResourceKey<Biome> define) {
		Biome biome = biomeRegistry.get(define);
		return biomeRegistry.getId(biome);
	}

	public static Layer buildJurassic(long seed, Registry<Biome> registry) {
		biomeRegistry = registry;

		final AreaFactory<LazyArea> noiseLayer = makeLayers(procedure -> new LazyAreaContext(25, seed, procedure), registry);
		return new JurassicLookupLayer(noiseLayer);
	}

	public static <T extends Area, C extends BigContext<T>> AreaFactory<T> makeLayers(LongFunction<C> context, Registry<Biome> registry) {
		AreaFactory<T> islandLayer = new JurassicIslandLayer().run(context.apply(1));
		AreaFactory<T> fuzzyZoomLayer = ZoomLayer.FUZZY.run(context.apply(2000), islandLayer);
		AreaFactory<T> addIslandLayer = JurassicAddIslandLayer.forest3().run(context.apply(3), fuzzyZoomLayer);
		AreaFactory<T> zoomLayer = ZoomLayer.NORMAL.run(context.apply(2000), addIslandLayer);

		AreaFactory<T> oceanLayer = new JurassicAddInlandLayer(20).run(context.apply(9), zoomLayer);
		oceanLayer = ZoomLayer.NORMAL.run(context.apply(9), oceanLayer);
		addIslandLayer = JurassicAddIslandLayer.mountains().run(context.apply(6), oceanLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2001), addIslandLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2004), zoomLayer);
		addIslandLayer = JurassicAddIslandLayer.forest2().run(context.apply(8), zoomLayer);

		AreaFactory<T> biomeLayerGen = new JurassicBiomeLayer().run(context.apply(15), addIslandLayer);
		AreaFactory<T> oceanLayerGen = JurassicAddWeightedSubBiomeLayer.ocean().run(context.apply(16), biomeLayerGen);
		AreaFactory<T> araucariaForest = JurassicAddSubBiomeLayer.araucariaForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), araucariaForest);
		AreaFactory<T> coniferForest = JurassicAddSubBiomeLayer.coniferForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), coniferForest);
		AreaFactory<T> ginkgoForest = JurassicAddSubBiomeLayer.ginkgoForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), ginkgoForest);
		AreaFactory<T> redwoodsForest = JurassicAddSubBiomeLayer.redwoodsForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), redwoodsForest);
		AreaFactory<T> desert = JurassicAddSubBiomeLayer.desert().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), desert);
		AreaFactory<T> plains = JurassicAddSubBiomeLayer.plains().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), plains);

		AreaFactory<T> riverLayer = zoomLayer;
		riverLayer = new JurassicRiverInitLayer().run(context.apply(12), riverLayer);
		riverLayer = magnify(2007, ZoomLayer.NORMAL, riverLayer, 5, context);
		riverLayer = new JurassicRiverLayer().run(context.apply(13), riverLayer);
		riverLayer = SmoothLayer.INSTANCE.run(context.apply(2008L), riverLayer);

		AreaFactory<T> magnifyLayer = magnify(2007L, ZoomLayer.NORMAL, zoomLayer, 3, context);
		AreaFactory<T> biomeLayer = new JurassicShoreLayer().run(context.apply(20), magnifyLayer);
		biomeLayer = magnify(20, ZoomLayer.NORMAL, biomeLayer, 2, context);

		biomeLayer = SmoothLayer.INSTANCE.run(context.apply(17L), biomeLayer);
		biomeLayer = new JurassicRiverMixLayer().run(context.apply(17), biomeLayer, riverLayer);

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
		return biomeSeed == getBiomeId(BiomeKeys.JURASSIC_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_JURASSIC_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.DEEP_JURASSIC_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_DEEP_JURASSIC_OCEAN);
	}

	public static boolean isRiver(int biomeSeed) {
		return biomeSeed == getBiomeId(BiomeKeys.JURASSIC_RIVER);
	}

	public static boolean isLand(int biomeSeed) {
		return biomeSeed == getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_BOG) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_FEN) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_SWAMP) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_MARSH) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_CONIFER_FOREST) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_CONIFER_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_DESERT) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_DESERT_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_GINKGO_FOREST) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_GINKGO_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_REDWOODS_FOREST) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_REDWOODS_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_PLAINS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_PLAINS_HILLS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_MOUNTAINS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_ERRODED_MOUNTAINS) || biomeSeed == getBiomeId(BiomeKeys.JURASSIC_VOLCANIC_RANGE);
	}

	private static <T extends Area, C extends BigContext<T>> AreaFactory<T> magnify(long seed, AreaTransformer1 zoomLayer, AreaFactory<T> layer, int count, LongFunction<C> context) {
		AreaFactory<T> result = layer;
		for (int i = 0; i < count; i++) {
			result = zoomLayer.run(context.apply(seed + i), result);
		}
		return result;
	}
}
