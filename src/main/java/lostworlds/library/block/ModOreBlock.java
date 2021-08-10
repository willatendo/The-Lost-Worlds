package lostworlds.library.block;

import java.util.Random;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

/*
 * Author: Willatendo
 * Date: July 2, 2021
 */

public class ModOreBlock extends OreBlock
{
	public ModOreBlock(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	protected int xpOnDrop(Random rand) 
	{
		if(this == BlockInit.PERMIAN_COAL_ORE) 
		{
			return MathHelper.nextInt(rand, 0, 2);
		}
		else if(this == BlockInit.PERMIAN_DIAMOND_ORE) 
		{
			return MathHelper.nextInt(rand, 3, 7);
		}
		else if(this == BlockInit.PERMIAN_EMERALD_ORE) 
		{
			return MathHelper.nextInt(rand, 3, 7);
		}
		else if(this == BlockInit.PERMIAN_LAPIS_ORE) 
		{
			return MathHelper.nextInt(rand, 2, 5);
		}
		else if(this == BlockInit.JURASSIC_COAL_ORE) 
		{
			return MathHelper.nextInt(rand, 0, 2);
		}
		else if(this == BlockInit.JURASSIC_DIAMOND_ORE) 
		{
			return MathHelper.nextInt(rand, 3, 7);
		}
		else if(this == BlockInit.JURASSIC_EMERALD_ORE) 
		{
			return MathHelper.nextInt(rand, 3, 7);
		}
		else if(this == BlockInit.JURASSIC_LAPIS_ORE) 
		{
			return MathHelper.nextInt(rand, 2, 5);
		}
		else
		{
			return MathHelper.nextInt(rand, 0, 0);
		}
	}
}
