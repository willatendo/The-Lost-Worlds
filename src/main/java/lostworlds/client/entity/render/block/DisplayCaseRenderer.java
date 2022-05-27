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
	public void render(DisplayCaseBlockEntity tile, float partialTicks, PoseStack stack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		Direction direction = tile.getBlockState().getValue(DisplayCaseBlock.HORIZONTAL_FACING);
		DisplayCaseInventory inventory = tile.handler;
		ItemStack item = inventory.getStackInSlot(0);

		if (item != ItemStack.EMPTY) {
			stack.pushPose();
			if (direction == Direction.NORTH) {
				stack.mulPose(ClientUtils.Y0);
				stack.translate(0.5, 0.6, 0.3);
				stack.mulPose(ClientUtils.X65);
			} else if (direction == Direction.EAST) {
				stack.mulPose(ClientUtils.Y90);
				stack.translate(-0.5, 0.6, 0.7);
				stack.mulPose(ClientUtils.NX65);
			} else if (direction == Direction.SOUTH) {
				stack.mulPose(ClientUtils.Y180);
				stack.translate(-0.5, 0.6, -0.7);
				stack.mulPose(ClientUtils.X65);
			} else if (direction == Direction.WEST) {
				stack.mulPose(ClientUtils.Y270);
				stack.translate(0.5, 0.6, -0.3);
				stack.mulPose(ClientUtils.NX65);
			}
			Minecraft.getInstance().getItemRenderer().renderStatic(item, ItemTransforms.TransformType.GROUND, combinedLight, combinedOverlay, stack, buffer, combinedOverlay);
			stack.popPose();
		}
	}
}
