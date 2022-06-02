package lostworlds.client.book.screen.book.element;

import java.util.List;
import java.util.Optional;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.book.screen.book.BookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.client.RenderProperties;

public abstract class BookElement extends GuiComponent {
	public BookScreen parent;

	protected Minecraft minecraft = Minecraft.getInstance();
	protected TextureManager renderEngine = this.minecraft.textureManager;

	public int x, y;

	public BookElement(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void draw(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks, Font fontRenderer);

	public void drawOverlay(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) {
	}

	public void mouseClicked(double mouseX, double mouseY, int mouseButton) {

	}

	public void mouseReleased(double mouseX, double mouseY, int clickedMouseButton) {

	}

	public void mouseDragged(double clickX, double clickY, double mx, double my, double lastX, double lastY, int button) {

	}

	public void renderToolTip(PoseStack matrixStack, Font fontRenderer, ItemStack stack, int x, int y) {
		List<Component> list = stack.getTooltipLines(this.minecraft.player, this.minecraft.options.advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL);

		Font font = RenderProperties.get(stack).getFont(stack);
		if (font == null) {
			font = fontRenderer;
		}

		this.drawTooltip(matrixStack, list, x, y, font);
	}

	public void drawTooltip(PoseStack matrixStack, List<Component> textLines, int x, int y, Font font) {
		int oldWidth = parent.width;
		int oldHeight = parent.height;
		parent.width = BookScreen.PAGE_WIDTH;
		parent.height = BookScreen.PAGE_HEIGHT;
		parent.renderTooltip(matrixStack, textLines, Optional.empty(), x, y, font);
		parent.width = oldWidth;
		parent.height = oldHeight;
	}
}
