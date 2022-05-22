package lostworlds.server.dimension;

import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer0;

public interface IIslandLayer extends AreaTransformer0 {
	int land();

	int ocean();

	@Override
	default int applyPixel(Context rand, int x, int y) {
		return rand.nextRandom(3) == 0 ? land() : ocean();
	}
}
