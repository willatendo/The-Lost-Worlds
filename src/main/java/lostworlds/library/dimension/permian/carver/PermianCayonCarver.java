package lostworlds.library.dimension.permian.carver;

import com.mojang.serialization.Codec;

import lostworlds.content.ModUtils;
import net.minecraft.world.gen.carver.CanyonWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class PermianCayonCarver extends CanyonWorldCarver
{
	public PermianCayonCarver(Codec<ProbabilityConfig> codec) 
	{
		super(codec);
		this.replaceableBlocks = ModUtils.permianCarverBlocks();
	}
}
