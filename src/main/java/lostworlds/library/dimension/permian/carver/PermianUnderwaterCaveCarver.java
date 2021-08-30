package lostworlds.library.dimension.permian.carver;

import com.mojang.serialization.Codec;

import lostworlds.library.util.ModUtils;
import net.minecraft.world.gen.carver.UnderwaterCanyonWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class PermianUnderwaterCaveCarver extends UnderwaterCanyonWorldCarver
{
	public PermianUnderwaterCaveCarver(Codec<ProbabilityConfig> codec) 
	{
		super(codec);
		this.replaceableBlocks = ModUtils.permianCarverBlocks();
	}
}
