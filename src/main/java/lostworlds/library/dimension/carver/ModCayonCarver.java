package lostworlds.library.dimension.carver;

import com.mojang.serialization.Codec;

import lostworlds.content.ModUtils;
import net.minecraft.world.gen.carver.CanyonWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ModCayonCarver extends CanyonWorldCarver
{
	public ModCayonCarver(Codec<ProbabilityConfig> codec) 
	{
		super(codec);
		this.replaceableBlocks = ModUtils.carverBlocks();
	}
}
