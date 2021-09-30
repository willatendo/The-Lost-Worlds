package lostworlds.content.client.entity.render.bone;

import java.util.concurrent.Callable;

import lostworlds.content.client.entity.model.skeleton.PartialSkeletonModel;
import lostworlds.library.item.FossilItem;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class PartialSkeletonRenderer extends GeoItemRenderer<FossilItem> implements Callable<ItemStackTileEntityRenderer>
{
	public PartialSkeletonRenderer(String modelAndTexture) 
	{
		super(new PartialSkeletonModel(modelAndTexture));
	}

	@Override
	public ItemStackTileEntityRenderer call() throws Exception 
	{
		return this;
	}
}
