package lostworlds.content.client.entity.render.bone;

import java.util.concurrent.Callable;

import lostworlds.content.client.entity.model.skeleton.SkeletonModel;
import lostworlds.library.item.FossilItem;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationItemRenderer;

@OnlyIn(Dist.CLIENT)
public class SkeletonRenderer extends TyrannomationItemRenderer<FossilItem> implements Callable<ItemStackTileEntityRenderer>
{
	public SkeletonRenderer(String modelAndTexture) 
	{
		super(new SkeletonModel(modelAndTexture));
	}
	
	public SkeletonRenderer(String model, String texture) 
	{
		super(new SkeletonModel(model, texture));
	}

	@Override
	public ItemStackTileEntityRenderer call() throws Exception 
	{
		return this;
	}
}
