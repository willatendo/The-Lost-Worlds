package lostworlds.library.dimension.cretaceous.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.cretaceous.CretaceousLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public class CretaceousAddInlandLayer implements IBishopTransformer 
{
	private final int chance;

	public CretaceousAddInlandLayer(int chance) 
	{
		this.chance = chance;
	}

	@Override
	public int apply(INoiseRandom random, int ne, int se, int sw, int nw, int center) 
	{
		if(CretaceousLayerUtil.isLand(nw) && CretaceousLayerUtil.isLand(sw) && CretaceousLayerUtil.isLand(ne) && CretaceousLayerUtil.isLand(se) && CretaceousLayerUtil.isLand(center) && random.nextRandom(chance) == 0) 
		{
			return CretaceousLayerUtil.getBiomeId(BiomeKeys.CRETACEOUS_ARAUCARIA_FOREST);
		}

		return center;
	}
}
