package lostworlds.library.dimension.permian.layer.ocean;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum PermianMixOceansLayer implements IAreaTransformer2, IDimOffset0Transformer 
{
	INSTANCE;

	public int applyPixel(INoiseRandom noise, IArea area1, IArea area2, int biomeSeed1, int biomeSeed2) 
	{
		int i = area1.get(this.getParentX(biomeSeed1), this.getParentY(biomeSeed2));
		int j = area2.get(this.getParentX(biomeSeed1), this.getParentY(biomeSeed2));
		if(!PermianLayerUtil.isOcean(i)) 
		{
			return i;
		} 
		else 
		{
			for(int i1 = -8; i1 <= 8; i1 += 4) 
			{
				for(int j1 = -8; j1 <= 8; j1 += 4) 
				{
					int k1 = area1.get(this.getParentX(biomeSeed1 + i1), this.getParentY(biomeSeed2 + j1));
					if(!PermianLayerUtil.isOcean(k1)) 
					{
						if(j == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN)) 
						{
							return PermianLayerUtil.getBiomeId(BiomeKeys.WARM_PERMIAN_OCEAN);
						}
					}
				}
			}

			if(i == PermianLayerUtil.getBiomeId(BiomeKeys.DEEP_PERMIAN_OCEAN)) 
			{
				if(j == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN)) 
				{
					return PermianLayerUtil.getBiomeId(BiomeKeys.DEEP_PERMIAN_OCEAN);
				}

				if(j == PermianLayerUtil.getBiomeId(BiomeKeys.WARM_PERMIAN_OCEAN)) 
				{
					return PermianLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_PERMIAN_OCEAN);
				}
			}

			return j;
		}
	}
}
