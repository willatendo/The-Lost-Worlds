package lostworlds.server.placement;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.SimplePlacement;

public class Height55To60 extends SimplePlacement<NoPlacementConfig> {
	public Height55To60(Codec<NoPlacementConfig> codec) {
		super(codec);
	}

	public Stream<BlockPos> place(Random rand, NoPlacementConfig config, BlockPos pos) {
		int i = 54 + rand.nextInt(6);
		return IntStream.range(0, i).mapToObj((intager) -> {
			int j = rand.nextInt(16) + pos.getX();
			int k = rand.nextInt(16) + pos.getZ();
			int l = rand.nextInt(56) + 4;
			return new BlockPos(j, l, k);
		});
	}
}