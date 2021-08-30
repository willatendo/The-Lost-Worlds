package lostworlds.library.dimension.permian.layer.island;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public enum PermianIslandLayer implements IAreaTransformer0 
{
	INSTANCE;

	public int applyPixel(INoiseRandom noise, int biomeSeed1, int biomeSeed2) 
	{
		if(biomeSeed1 == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN) && biomeSeed2 == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN)) 
		{
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT);
		} 
		else 
		{
			return noise.nextRandom(10) == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN) ? PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT) : PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN);
		}
	}
}
