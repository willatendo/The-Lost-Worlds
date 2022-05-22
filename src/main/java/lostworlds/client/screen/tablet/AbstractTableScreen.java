package lostworlds.client.screen.tablet;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

public abstract class AbstractTableScreen extends Screen {
	public static final ResourceLocation TEXTURE = LostWorldsUtils.rL("textures/gui/tablet.png");
	public static final int FONT_COLOUR = 0x000000;

	public final int texWidth;
	public final int texHeight;
	protected int titleLabelX;
	protected int titleLabelY;
	int left;
	int top;
	public float xMouse;
	public float yMouse;

	protected AbstractTableScreen(Component component) {
		super(component);

		this.titleLabelX = 22;
		this.titleLabelY = 20;

		this.texWidth = 255;
		this.texHeight = 192;
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		this.xMouse = (float) mouseX;
		this.yMouse = (float) mouseY;
		renderBackgroundElements(stack);
		this.font.draw(stack, this.title, (float) this.titleLabelX, (float) this.titleLabelY, FONT_COLOUR);
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderComponentHoverEffect(stack, Style.EMPTY, mouseX, mouseY);
	}

	public void renderBackgroundElements(PoseStack stack) {
		this.renderBackground(stack, 0);
		Minecraft.getInstance().getTextureManager().bind(TEXTURE);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		stack.translate((this.width / 2) - (this.texWidth / 2), (this.height / 2) - (this.texHeight / 2), 0);
		blit(stack, 0, 0, 0, 0, this.texWidth, this.texHeight);
	}
}
