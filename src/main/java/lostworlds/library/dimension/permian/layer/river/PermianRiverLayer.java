package lostworlds.library.dimension.permian.layer.river;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum PermianRiverLayer implements ICastleTransformer
{
	INSTANCE;
		
	@Override
	public int apply(INoiseRandom iNoiseRandom, int up, int left, int down, int right, int mid) 
	{
		int i = riverFilter(mid);
		return i == riverFilter(right) && i == riverFilter(up) && i == riverFilter(left) && i == riverFilter(down) ? -1 : 7;
	}
	
	private static int riverFilter(int biomeSeed) 
	{
		return biomeSeed >= 2 ? 2 + (biomeSeed & 1) : biomeSeed;
	}
}
