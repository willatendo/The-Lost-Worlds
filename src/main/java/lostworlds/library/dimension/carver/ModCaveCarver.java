package lostworlds.library.dimension.carver;

import com.mojang.serialization.Codec;

import lostworlds.content.ModUtils;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ModCaveCarver extends CaveWorldCarver
{
	public ModCaveCarver(Codec<ProbabilityConfig> codec, int probability) 
	{
		super(codec, probability);
		this.replaceableBlocks = ModUtils.carverBlocks();
	}
}
