package lostworlds.library.dimension.jurassic;

import java.util.function.LongFunction;

import lostworlds.library.dimension.permian.layer.PermianLookupLayer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.ZoomLayer;

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
		return new PermianLookupLayer(noiseLayer);
	}

	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> context, Registry<Biome> registry) 
	{
		IAreaFactory<T> biomes = new JurassicBiomeLayer().run(context.apply(1L));

        biomes = ZoomLayer.NORMAL.run(context.apply(1000), biomes);
        biomes = ZoomLayer.NORMAL.run(context.apply(1001), biomes);
        biomes = ZoomLayer.NORMAL.run(context.apply(1002), biomes);
        biomes = ZoomLayer.NORMAL.run(context.apply(1003), biomes);
        biomes = ZoomLayer.NORMAL.run(context.apply(1004), biomes);
        biomes = ZoomLayer.NORMAL.run(context.apply(1005), biomes);

        biomes = LayerUtil.zoom(1000L, ZoomLayer.NORMAL, biomes, 1, context);

		return biomes;
	}
}
