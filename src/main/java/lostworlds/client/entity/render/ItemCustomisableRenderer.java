package lostworlds.client.entity.render;

import java.util.concurrent.Callable;

import lostworlds.client.entity.model.CustomisableModel;
import lostworlds.server.item.FossilItem;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ItemCustomisableRenderer extends GeoItemRenderer<FossilItem> implements Callable<ItemStackTileEntityRenderer> {
	public ItemCustomisableRenderer(String modelAndTexture) {
		super(new CustomisableModel<FossilItem>(modelAndTexture));
	}

	public ItemCustomisableRenderer(String model, String texture) {
		super(new CustomisableModel<FossilItem>(model, texture));
	}

	@Override
	public ItemStackTileEntityRenderer call() throws Exception {
		return this;
	}
}
