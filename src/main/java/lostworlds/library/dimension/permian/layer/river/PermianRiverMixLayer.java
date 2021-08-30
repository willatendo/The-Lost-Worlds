package lostworlds.library.dimension.permian.layer.river;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum PermianRiverMixLayer implements IAreaTransformer2, IDimOffset0Transformer
{
	INSTANCE;
	
	public int applyPixel(INoiseRandom noise, IArea area1, IArea area2, int biomeSeed1, int biomeSeed2) 
	{
		int i = area1.get(this.getParentX(biomeSeed1), this.getParentY(biomeSeed2));
		int j = area2.get(this.getParentX(biomeSeed1), this.getParentY(biomeSeed2));
		if(PermianLayerUtil.isOcean(i)) 
		{
			return i;
		} 
		else if(j == PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_RIVER)) 
		{
			return i != 14 && i != 15 ? j & 255 : 15;
		} 
		else 
		{
			return i;
		}
	}
}
