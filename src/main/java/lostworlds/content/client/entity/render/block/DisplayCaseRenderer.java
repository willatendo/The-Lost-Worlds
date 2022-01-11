package lostworlds.content.client.entity.render.block;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.content.ClientUtils;
import lostworlds.library.block.DisplayCaseBlock;
import lostworlds.library.block.entity.DisplayCaseTileEntity;
import lostworlds.library.container.inventory.DisplayCaseInventory;
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
public class DisplayCaseRenderer extends TileEntityRenderer<DisplayCaseTileEntity> {
	public DisplayCaseRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(DisplayCaseTileEntity tile, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		Direction direction = tile.getBlockState().getValue(DisplayCaseBlock.HORIZONTAL_FACING);
		DisplayCaseInventory inventory = tile.handler;
		ItemStack stack = inventory.getStackInSlot(0);

		if (stack != ItemStack.EMPTY) {
			matrix.pushPose();
			if (direction == Direction.NORTH) {
				matrix.mulPose(ClientUtils.Y0);
				matrix.translate(0.5, 0.6, 0.3);
				matrix.mulPose(ClientUtils.X65);
			} else if (direction == Direction.EAST) {
				matrix.mulPose(ClientUtils.Y90);
				matrix.translate(-0.5, 0.6, 0.7);
				matrix.mulPose(ClientUtils.NX65);
			} else if (direction == Direction.SOUTH) {
				matrix.mulPose(ClientUtils.Y180);
				matrix.translate(-0.5, 0.6, -0.7);
				matrix.mulPose(ClientUtils.X65);
			} else if (direction == Direction.WEST) {
				matrix.mulPose(ClientUtils.Y270);
				matrix.translate(0.5, 0.6, -0.3);
				matrix.mulPose(ClientUtils.NX65);
			}
			Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemCameraTransforms.TransformType.GROUND, combinedLight, combinedOverlay, matrix, buffer);
			matrix.popPose();
		}
	}
}
