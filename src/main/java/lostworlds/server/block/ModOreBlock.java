package lostworlds.server.block;

import java.util.Random;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

public class ModOreBlock extends OreBlock {
	public final int minXPDrop;
	public final int maxXPDrop;

	public ModOreBlock(Properties properties, int minXPDrop, int maxXPDrop) {
		super(properties);
		this.minXPDrop = minXPDrop;
		this.maxXPDrop = maxXPDrop;
	}

	@Override
	protected int xpOnDrop(Random rand) {
		return MathHelper.nextInt(rand, minXPDrop, maxXPDrop);
	}
}
