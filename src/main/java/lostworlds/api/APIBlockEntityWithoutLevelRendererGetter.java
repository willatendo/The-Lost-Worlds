package lostworlds.api;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;

public interface APIBlockEntityWithoutLevelRendererGetter {
	BlockEntityWithoutLevelRenderer getISTER();

	BlockEntityWithoutLevelRenderer getISTER(String part);
}
