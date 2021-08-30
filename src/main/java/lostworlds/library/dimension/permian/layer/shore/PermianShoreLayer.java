package lostworlds.library.dimension.permian.layer.shore;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum PermianShoreLayer implements ICastleTransformer 
{
	INSTANCE;

	public int apply(INoiseRandom noise, int p_202748_2_, int p_202748_3_, int p_202748_4_, int p_202748_5_, int mid) 
	{
		if(mid != PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_MOUNTAINS)) 
		{
			if(!PermianLayerUtil.isOcean(p_202748_2_) && !PermianLayerUtil.isOcean(p_202748_3_) && !PermianLayerUtil.isOcean(p_202748_4_) && !PermianLayerUtil.isOcean(p_202748_5_))
			{
				return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_SHORE);
			}
		} 
		else if(!PermianLayerUtil.isOcean(mid) && (PermianLayerUtil.isOcean(p_202748_2_) || PermianLayerUtil.isOcean(p_202748_3_) || PermianLayerUtil.isOcean(p_202748_4_) || PermianLayerUtil.isOcean(p_202748_5_))) 
		{
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_STONE_SHORE);
		}

		return mid;
	}
}
