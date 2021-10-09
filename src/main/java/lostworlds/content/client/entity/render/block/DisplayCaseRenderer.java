package lostworlds.content.client.entity.render.block;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.library.block.DisplayCaseBlock;
import lostworlds.library.block.entity.DisplayCaseTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DisplayCaseRenderer extends TileEntityRenderer<DisplayCaseTileEntity>
{
	public DisplayCaseRenderer(TileEntityRendererDispatcher dispatcher) 
	{
		super(dispatcher);
	}

	@Override
	public void render(DisplayCaseTileEntity tile, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) 
	{
		if(!tile.isEmpty())
		{
			ItemStack stack = tile.getDisplayedItem();
			matrix.pushPose();
			if(tile.getBlockState().getValue(DisplayCaseBlock.HORIZONTAL_FACING) == Direction.NORTH)
			{
				matrix.mulPose(RenderUtils.Y0);
				matrix.translate(0.5, 0.6, 0.3);
				matrix.mulPose(RenderUtils.X65);
			}
			else if(tile.getBlockState().getValue(DisplayCaseBlock.HORIZONTAL_FACING) == Direction.EAST)
			{
				matrix.mulPose(RenderUtils.Y90);
				matrix.translate(-0.5, 0.6, 0.7);
				matrix.mulPose(RenderUtils.NX65);
			}
			else if(tile.getBlockState().getValue(DisplayCaseBlock.HORIZONTAL_FACING) == Direction.SOUTH)
			{
				matrix.mulPose(RenderUtils.Y180);
				matrix.translate(-0.5, 0.6, -0.7);
				matrix.mulPose(RenderUtils.X65);
			}
			else if(tile.getBlockState().getValue(DisplayCaseBlock.HORIZONTAL_FACING) == Direction.WEST)
			{
				matrix.mulPose(RenderUtils.Y270);
				matrix.translate(0.5, 0.6, -0.3);
				matrix.mulPose(RenderUtils.NX65);
			}
			Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemCameraTransforms.TransformType.GROUND, combinedLight, combinedOverlay, matrix, buffer);
			matrix.popPose();                
		}
	}
}