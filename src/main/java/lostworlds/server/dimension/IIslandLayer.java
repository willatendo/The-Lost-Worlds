package lostworlds.server.dimension;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public interface IIslandLayer extends IAreaTransformer0 {
	int land();

	int ocean();

	@Override
	default int applyPixel(INoiseRandom rand, int x, int y) {
		return rand.nextRandom(3) == 0 ? this.land() : this.ocean();
	}
}
