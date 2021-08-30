package lostworlds.library.dimension.permian.layer.ocean;

import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.dimension.permian.PermianLayerUtil;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.ImprovedNoiseGenerator;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public enum PermianOceanLayer implements IAreaTransformer0
{
	INSTANCE;
	
	public int applyPixel(INoiseRandom noise, int biomeSeed1, int biomeSeed2) 
	{
		ImprovedNoiseGenerator improvednoisegenerator = noise.getBiomeNoise();
		double d0 = improvednoisegenerator.noise((double)biomeSeed1 / 8.0D, (double)biomeSeed2 / 8.0D, 0.0D, 0.0D, 0.0D);
		return d0 > 0.4D ? PermianLayerUtil.getBiomeId(BiomeKeys.WARM_PERMIAN_OCEAN) : PermianLayerUtil.getBiomeId(BiomeKeys.PERMIAN_OCEAN);
	}
}
