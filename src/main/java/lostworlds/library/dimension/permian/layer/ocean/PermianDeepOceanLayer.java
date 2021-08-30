package lostworlds.library.dimension.permian.layer.ocean;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum PermianDeepOceanLayer implements ICastleTransformer 
{
	INSTANCE;

	@Override
	public int apply(INoiseRandom iNoiseRandom, int up, int left, int down, int right, int mid) 
	{
		if(PermianLayerUtil.isShallowOcean(mid)) 
		{
			int chance = 0;
			if(PermianLayerUtil.isShallowOcean(up)) 
			{
				++chance;
			}

			if(PermianLayerUtil.isShallowOcean(left)) 
			{
				++chance;
			}

			if(PermianLayerUtil.isShallowOcean(right)) 
			{
				++chance;
			}

			if(PermianLayerUtil.isShallowOcean(down)) 
			{
				++chance;
			}

			if(chance > 3) 
			{
				if(mid == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN)) 
				{
					return PermianLayerUtil.getBiomeId(BiomeKeys.DEEP_PERMIAN_OCEAN);
				}

				if(mid == PermianLayerUtil.getBiomeId(BiomeKeys.WARM_PERMIAN_OCEAN)) 
				{
					return PermianLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_PERMIAN_OCEAN);
				}

				return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN);
			}
		}

		return mid;
	}
}
