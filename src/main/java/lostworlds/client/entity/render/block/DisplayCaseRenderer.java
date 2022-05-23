package lostworlds.client.entity.render.block;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.ClientUtils;
import lostworlds.server.block.DisplayCaseBlock;
import lostworlds.server.block.entity.DisplayCaseBlockEntity;
import lostworlds.server.menu.inventory.DisplayCaseInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DisplayCaseRenderer implements BlockEntityRenderer<DisplayCaseBlockEntity> {
	public DisplayCaseRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(DisplayCaseBlockEntity tile, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
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
			Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.GROUND, combinedLight, combinedOverlay, matrix, buffer, combinedOverlay);
			matrix.popPose();
		}
	}
}
