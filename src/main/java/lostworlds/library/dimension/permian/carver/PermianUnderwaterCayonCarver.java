package lostworlds.library.dimension.permian.carver;

import com.mojang.serialization.Codec;

import lostworlds.content.ModUtils;
import net.minecraft.world.gen.carver.UnderwaterCaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class PermianUnderwaterCayonCarver extends UnderwaterCaveWorldCarver
{
	public PermianUnderwaterCayonCarver(Codec<ProbabilityConfig> codec) 
	{
		super(codec);
		this.replaceableBlocks = ModUtils.permianCarverBlocks();
	}
}
