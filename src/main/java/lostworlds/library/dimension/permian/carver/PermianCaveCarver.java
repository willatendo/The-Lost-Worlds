package lostworlds.library.dimension.permian.carver;

import com.mojang.serialization.Codec;

import lostworlds.content.ModUtils;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class PermianCaveCarver extends CaveWorldCarver
{
	public PermianCaveCarver(Codec<ProbabilityConfig> codec, int probability) 
	{
		super(codec, probability);
		this.replaceableBlocks = ModUtils.permianCarverBlocks();
	}
}
