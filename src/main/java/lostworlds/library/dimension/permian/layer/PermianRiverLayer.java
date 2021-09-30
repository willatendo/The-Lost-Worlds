package lostworlds.library.dimension.permian.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum PermianRiverLayer implements ICastleTransformer 
{
	INSTANCE;

	PermianRiverLayer() { }

	@Override
	public int apply(INoiseRandom random, int north, int west, int south, int east, int center) 
	{
		if(shouldRiver(center, west, south, east, north)) 
		{
			return PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_RIVER);
		} 
		else 
		{
			return -1;
		}
	}

	boolean shouldRiver(int mid, int left, int down, int right, int up) 
	{
		return shouldRiver(mid, left) || shouldRiver(mid, right) || shouldRiver(mid, down) || shouldRiver(mid, up);
	}

	boolean shouldRiver(int id1, int id2) 
	{
		if(id1 == id2)
			return false;
		if(id1 == id2)
			return false;
		return true;
	}
}
