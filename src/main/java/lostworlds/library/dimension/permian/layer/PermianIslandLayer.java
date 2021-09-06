package lostworlds.library.dimension.permian.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public class PermianIslandLayer implements IAreaTransformer0 
{
	public PermianIslandLayer() { }

	@Override
	public int applyPixel(INoiseRandom random, int x, int y) 
	{
		if (x == 0 && y == 0) 
		{
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT);
		}

		return random.nextRandom(3) == 0 ? PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT) : PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN);
	}
}
