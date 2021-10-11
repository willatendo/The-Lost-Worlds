package lostworlds.library.dimension.carver;

import com.mojang.serialization.Codec;

import lostworlds.content.ModUtils;
import net.minecraft.world.gen.carver.UnderwaterCaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ModUnderwaterCayonCarver extends UnderwaterCaveWorldCarver
{
	public ModUnderwaterCayonCarver(Codec<ProbabilityConfig> codec) 
	{
		super(codec);
		this.replaceableBlocks = ModUtils.carverBlocks();
	}
}
