package lostworlds.server.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.server.feature.config.ModReplaceBlockConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

public class ModReplaceBlockFeature extends Feature<ModReplaceBlockConfig> {
	public ModReplaceBlockFeature(Codec<ModReplaceBlockConfig> config) {
		super(config);
	}

	@Override
	public boolean place(WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos, ModReplaceBlockConfig config) {
		if (reader.getBlockState(pos).is(config.target.get().getBlock())) {
			reader.setBlock(pos, config.state.get(), 2);
		}

		return true;
	}
}
