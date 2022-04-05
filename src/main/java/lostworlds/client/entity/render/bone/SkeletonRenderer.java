package lostworlds.client.entity.render.bone;

import java.util.concurrent.Callable;

import lostworlds.client.entity.model.skeleton.SkeletonModel;
import lostworlds.server.item.FossilItem;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class SkeletonRenderer extends GeoItemRenderer<FossilItem> implements Callable<ItemStackTileEntityRenderer> {
	public SkeletonRenderer(String modelAndTexture) {
		super(new SkeletonModel(modelAndTexture));
	}

	public SkeletonRenderer(String model, String texture) {
		super(new SkeletonModel(model, texture));
	}

	@Override
	public ItemStackTileEntityRenderer call() throws Exception {
		return this;
	}
}
