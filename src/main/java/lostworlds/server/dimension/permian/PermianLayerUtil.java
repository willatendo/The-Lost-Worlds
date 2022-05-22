package lostworlds.server.dimension.permian;

import java.util.function.LongFunction;

import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.dimension.permian.layer.PermianAddInlandLayer;
import lostworlds.server.dimension.permian.layer.PermianAddIslandLayer;
import lostworlds.server.dimension.permian.layer.PermianAddSubBiomeLayer;
import lostworlds.server.dimension.permian.layer.PermianAddWeightedSubBiomeLayer;
import lostworlds.server.dimension.permian.layer.PermianIslandLayer;
import lostworlds.server.dimension.permian.layer.PermianLookupLayer;
import lostworlds.server.dimension.permian.layer.PermianRiverInitLayer;
import lostworlds.server.dimension.permian.layer.PermianRiverLayer;
import lostworlds.server.dimension.permian.layer.PermianRiverMixLayer;
import lostworlds.server.dimension.permian.layer.PermianShoreLayer;
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

public class PermianLayerUtil {
	private static Registry<Biome> biomeRegistry;

	public static int getBiomeId(ResourceKey<Biome> define) {
		Biome biome = biomeRegistry.get(define);
		return biomeRegistry.getId(biome);
	}

	public static Layer buildPermian(long seed, Registry<Biome> registry) {
		biomeRegistry = registry;

		final AreaFactory<LazyArea> noiseLayer = makeLayers(procedure -> new LazyAreaContext(25, seed, procedure), registry);
		return new PermianLookupLayer(noiseLayer);
	}

	public static <T extends Area, C extends BigContext<T>> AreaFactory<T> makeLayers(LongFunction<C> context, Registry<Biome> registry) {
		AreaFactory<T> islandLayer = new PermianIslandLayer().run(context.apply(1));
		AreaFactory<T> fuzzyZoomLayer = ZoomLayer.FUZZY.run(context.apply(2000), islandLayer);
		AreaFactory<T> addIslandLayer = PermianAddIslandLayer.desert3().run(context.apply(3), fuzzyZoomLayer);
		AreaFactory<T> zoomLayer = ZoomLayer.NORMAL.run(context.apply(2000), addIslandLayer);

		AreaFactory<T> oceanLayer = new PermianAddInlandLayer(20).run(context.apply(9), zoomLayer);
		oceanLayer = ZoomLayer.NORMAL.run(context.apply(9), oceanLayer);
		addIslandLayer = PermianAddIslandLayer.mountains().run(context.apply(6), oceanLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2001), addIslandLayer);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2004), zoomLayer);
		addIslandLayer = PermianAddIslandLayer.desert2().run(context.apply(8), zoomLayer);

		AreaFactory<T> biomeLayerGen = new PermianBiomeLayer().run(context.apply(15), addIslandLayer);
		AreaFactory<T> oceanLayerGen = PermianAddWeightedSubBiomeLayer.ocean().run(context.apply(16), biomeLayerGen);
		AreaFactory<T> coniferForest = PermianAddSubBiomeLayer.coniferForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), coniferForest);
		AreaFactory<T> desert = PermianAddSubBiomeLayer.desert().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), desert);
		AreaFactory<T> driedPlains = PermianAddSubBiomeLayer.driedPlains().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), driedPlains);
		AreaFactory<T> floodBasalts = PermianAddSubBiomeLayer.floodBasalts().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), floodBasalts);
		AreaFactory<T> ginkgoForest = PermianAddSubBiomeLayer.ginkgoForest().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), ginkgoForest);
		AreaFactory<T> plains = PermianAddSubBiomeLayer.plains().run(context.apply(17), oceanLayerGen);
		zoomLayer = ZoomLayer.NORMAL.run(context.apply(2002), plains);

		AreaFactory<T> riverLayer = zoomLayer;
		riverLayer = new PermianRiverInitLayer().run(context.apply(12), riverLayer);
		riverLayer = magnify(2007, ZoomLayer.NORMAL, riverLayer, 5, context);
		riverLayer = new PermianRiverLayer().run(context.apply(13), riverLayer);
		riverLayer = SmoothLayer.INSTANCE.run(context.apply(2008L), riverLayer);

		AreaFactory<T> magnifyLayer = magnify(2007L, ZoomLayer.NORMAL, zoomLayer, 3, context);
		AreaFactory<T> biomeLayer = new PermianShoreLayer().run(context.apply(20), magnifyLayer);
		biomeLayer = magnify(20, ZoomLayer.NORMAL, biomeLayer, 2, context);

		biomeLayer = SmoothLayer.INSTANCE.run(context.apply(17L), biomeLayer);
		biomeLayer = new PermianRiverMixLayer().run(context.apply(17), biomeLayer, riverLayer);

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
		return biomeSeed == getBiomeId(BiomeKeys.PERMIAN_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_PERMIAN_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.DEEP_PERMIAN_OCEAN) || biomeSeed == getBiomeId(BiomeKeys.WARM_DEEP_PERMIAN_OCEAN);
	}

	public static boolean isRiver(int biomeSeed) {
		return biomeSeed == getBiomeId(BiomeKeys.PERMIAN_RIVER);
	}

	public static boolean isLand(int biomeSeed) {
		return biomeSeed == getBiomeId(BiomeKeys.PERMIAN_ASHY_MEDOWS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_DESERT) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_DESERT_HILLS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_DRIED_PLAINS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_DRIED_PLAINS_HILLS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_FLOOD_BASALTS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_GINKGO_FOREST) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_GINKGO_FOREST_HILLS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_MARSH) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_MOUNTAINS) || biomeSeed == getBiomeId(BiomeKeys.PERMIAN_SHORE);
	}

	private static <T extends Area, C extends BigContext<T>> AreaFactory<T> magnify(long seed, AreaTransformer1 zoomLayer, AreaFactory<T> layer, int count, LongFunction<C> context) {
		AreaFactory<T> result = layer;
		for (int i = 0; i < count; i++) {
			result = zoomLayer.run(context.apply(seed + i), result);
		}
		return result;
	}
}
