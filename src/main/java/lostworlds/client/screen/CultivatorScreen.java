package lostworlds.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.menu.CultivatorMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CultivatorScreen extends AbstractContainerScreen<CultivatorMenu> {
	private static final ResourceLocation TEXTURE = LostWorldsUtils.rL("textures/gui/machines/cultivator.png");

	public CultivatorScreen(CultivatorMenu container, Inventory playerInv, Component text) {
		super(container, playerInv, text);
	}

	@Override
	public void render(PoseStack stack, int i1, int i2, float f) {
		this.renderBackground(stack);
		super.render(stack, i1, i2, f);
		this.renderTooltip(stack, i1, i2);
	}

	@Override
	protected void renderBg(PoseStack stack, float partialTicks, int x, int y) {
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int leftPos = this.leftPos;
		int topPos = this.topPos;
		this.blit(stack, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);

		int progress = this.menu.getProgress();
		this.blit(stack, this.leftPos + 76, this.topPos + 34, 176, 0, progress + 1, 16);
	}
}
