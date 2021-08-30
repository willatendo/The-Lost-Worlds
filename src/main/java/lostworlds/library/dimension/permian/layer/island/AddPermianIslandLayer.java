package lostworlds.library.dimension.permian.layer.island;

import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public enum AddPermianIslandLayer implements IBishopTransformer {
	INSTANCE;

	public int apply(INoiseRandom noise, int up, int left, int down, int right, int mid) 
	{
		if(!PermianLayerUtil.isShallowOcean(mid) || PermianLayerUtil.isShallowOcean(right) && PermianLayerUtil.isShallowOcean(down) && PermianLayerUtil.isShallowOcean(up) && PermianLayerUtil.isShallowOcean(left)) 
		{
			if(!PermianLayerUtil.isShallowOcean(mid) && (PermianLayerUtil.isShallowOcean(right) || PermianLayerUtil.isShallowOcean(up) || PermianLayerUtil.isShallowOcean(down) || PermianLayerUtil.isShallowOcean(left)) && noise.nextRandom(5) == 0) 
			{
				if(PermianLayerUtil.isShallowOcean(right)) 
				{
					return mid == 4 ? 4 : right;
				}

				if(PermianLayerUtil.isShallowOcean(up)) 
				{
					return mid == 4 ? 4 : up;
				}

				if(PermianLayerUtil.isShallowOcean(down)) 
				{
					return mid == 4 ? 4 : down;
				}

				if(PermianLayerUtil.isShallowOcean(left)) 
				{
					return mid == 4 ? 4 : left;
				}
			}

			return mid;
		} 
		else 
		{
			int i = 1;
			int j = 1;
			if(!PermianLayerUtil.isShallowOcean(right) && noise.nextRandom(i++) == 0) 
			{
				j = right;
			}

			if(!PermianLayerUtil.isShallowOcean(down) && noise.nextRandom(i++) == 0) 
			{
				j = down;
			}

			if(!PermianLayerUtil.isShallowOcean(up) && noise.nextRandom(i++) == 0) 
			{
				j = up;
			}

			if(!PermianLayerUtil.isShallowOcean(left) && noise.nextRandom(i++) == 0) 
			{
				j = left;
			}

			if(noise.nextRandom(3) == 0) 
			{
				return j;
			} 
			else 
			{
				return j == 4 ? 4 : mid;
			}
		}
	}
}
