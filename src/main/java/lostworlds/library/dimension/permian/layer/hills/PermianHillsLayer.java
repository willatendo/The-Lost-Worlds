package lostworlds.library.dimension.permian.layer.hills;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import lostworlds.library.util.ModUtils;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset1Transformer;

public enum PermianHillsLayer implements IAreaTransformer2, IDimOffset1Transformer 
{
	INSTANCE;

	public int applyPixel(INoiseRandom noise, IArea area1, IArea area2, int biomeSeed1, int biomeSeed2) 
	{
		int i = area1.get(this.getParentX(biomeSeed1 + 1), this.getParentY(biomeSeed2 + 1));
		int j = area2.get(this.getParentX(biomeSeed1 + 1), this.getParentY(biomeSeed2 + 1));
		if(i > 255) 
		{
			ModUtils.LOGGER.debug("old! {}", (int) i);
		}

		int k = (j - 2) % 29;

		if(noise.nextRandom(3) == 0 || k == 0) 
		{
			int l = i;
			if(i == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT)) 
			{
				l = PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT_HILLS);
			} 
			else if(i == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST)) 
			{
				l = PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST_HILLS);
			}
			else if(i == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DRIED_PLAINS)) 
			{
				l = PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DRIED_PLAINS_HILLS);
			} 
			else if(i == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_GINKGO_FOREST)) 
			{
				l = PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_GINKGO_FOREST_HILLS);
			} 
			else if(i == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_PLAINS)) 
			{
				l = PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_PLAINS_HILLS);
			} 
			else if((i == PermianLayerUtil.getBiomeId(BiomeKeys.DEEP_PERMIAN_OCEAN) || i == PermianLayerUtil.getBiomeId(BiomeKeys.WARM_DEEP_PERMIAN_OCEAN)) && noise.nextRandom(3) == 0) 
			{
				l = noise.nextRandom(2) == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN) ? PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_DESERT) : PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_CONIFER_FOREST);
			}

			if(l != i) 
			{
				int i1 = 0;
				if(LayerUtil.isSame(area1.get(this.getParentX(biomeSeed1 + 1), this.getParentY(biomeSeed2 + 0)), i)) 
				{
					++i1;
				}

				if(LayerUtil.isSame(area1.get(this.getParentX(biomeSeed1 + 2), this.getParentY(biomeSeed2 + 1)), i)) 
				{
					++i1;
				}

				if(LayerUtil.isSame(area1.get(this.getParentX(biomeSeed1 + 0), this.getParentY(biomeSeed2 + 1)), i)) 
				{
					++i1;
				}

				if(LayerUtil.isSame(area1.get(this.getParentX(biomeSeed1 + 1), this.getParentY(biomeSeed2 + 2)), i)) 
				{
					++i1;
				}

				if(i1 >= 3) 
				{
					return l;
				}
			}
		}

		return i;

	}
}
