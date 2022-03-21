package lostworlds.server.dimension.carver;

import com.mojang.serialization.Codec;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.carver.UnderwaterCanyonWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ModUnderwaterCaveCarver extends UnderwaterCanyonWorldCarver {
	public ModUnderwaterCaveCarver(Codec<ProbabilityConfig> codec) {
		super(codec);
		this.replaceableBlocks = LostWorldsUtils.carverBlocks();
	}
}
