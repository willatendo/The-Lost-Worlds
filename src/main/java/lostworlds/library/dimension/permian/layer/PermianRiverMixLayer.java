package lostworlds.library.dimension.permian.layer;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum PermianRiverMixLayer implements IAreaTransformer2, IDimOffset0Transformer
{
	INSTANCE;

	PermianRiverMixLayer() { }

	@Override
	public int applyPixel(INoiseRandom random, IArea area1, IArea area2, int val1, int val2) 
	{
		int i = area1.get(this.getParentX(val1), this.getParentY(val2));
		int j = area2.get(this.getParentX(val1), this.getParentY(val2));
		if(j == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_RIVER)) 
		{
			return j;
		} 
		else 
		{
			return i;
		}
	}
}
