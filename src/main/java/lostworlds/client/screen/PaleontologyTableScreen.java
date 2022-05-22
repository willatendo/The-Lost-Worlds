package lostworlds.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

import lostworlds.server.container.PaleontologyTableContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PaleontologyTableScreen extends AbstractContainerScreen<PaleontologyTableContainer> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/crafting_table.png");

	public PaleontologyTableScreen(PaleontologyTableContainer container, Inventory playerInv, Component text) {
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
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(TEXTURE);
		int leftPos = this.leftPos;
		int topPos = this.topPos;
		this.blit(stack, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
	}
}
