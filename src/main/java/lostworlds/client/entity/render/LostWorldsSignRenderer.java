package lostworlds.client.entity.render;

import com.google.common.collect.ImmutableMap;

import lostworlds.server.block.LostWorldsWoodTypes;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SignRenderer;

public class LostWorldsSignRenderer extends SignRenderer {
	public LostWorldsSignRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
		this.signModels = LostWorldsWoodTypes.getWoodTypes().stream().collect(ImmutableMap.toImmutableMap((woodType) -> {
			return woodType;
		}, (woodType) -> {
			return new SignRenderer.SignModel(context.bakeLayer(ModelLayers.createSignModelName(woodType)));
		}));
	}
}
