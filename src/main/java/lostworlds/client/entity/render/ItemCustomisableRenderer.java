package lostworlds.client.entity.render;

import lostworlds.client.entity.model.CustomisableModel;
import lostworlds.server.item.FossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class ItemCustomisableRenderer extends GeoItemRenderer<FossilItem> {
	public ItemCustomisableRenderer(String modelAndTexture) {
		super(new CustomisableModel(modelAndTexture));
	}

	public ItemCustomisableRenderer(String model, String texture) {
		super(new CustomisableModel(model, texture));
	}
}
