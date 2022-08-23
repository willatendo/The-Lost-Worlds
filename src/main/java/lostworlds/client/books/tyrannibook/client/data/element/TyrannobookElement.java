package lostworlds.client.books.tyrannibook.client.data.element;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.client.books.tyrannibook.client.screen.TyrannobookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;

public abstract class TyrannobookElement extends AbstractGui {
	public TyrannobookScreen parent;

	protected Minecraft mc = Minecraft.getInstance();
	protected TextureManager renderEngine = this.mc.textureManager;

	public int x, y;

	public TyrannobookElement(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void draw(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, FontRenderer fontRenderer);

	public void drawOverlay(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, FontRenderer fontRenderer) {
	}

	public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
	}

	@Deprecated
	public void mouseClickMove(double mouseX, double mouseY, int clickedMouseButton) {
	}

	public void mouseReleased(double mouseX, double mouseY, int clickedMouseButton) {
	}

	public void mouseDragged(double clickX, double clickY, double mx, double my, double lastX, double lastY, int button) {
	}

	public void renderToolTip(MatrixStack matrixStack, FontRenderer fontRenderer, ItemStack stack, int x, int y) {
		List<ITextComponent> list = stack.getTooltipLines(this.mc.player, this.mc.options.advancedItemTooltips ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL);

		FontRenderer font = stack.getItem().getFontRenderer(stack);
		if (font == null) {
			font = fontRenderer;
		}

		this.drawHoveringText(matrixStack, list, x, y, font);
		RenderHelper.turnOff();
	}

	public void drawHoveringText(MatrixStack matrixStack, List<ITextComponent> textLines, int x, int y, FontRenderer font) {
		GuiUtils.drawHoveringText(matrixStack, textLines, x, y, this.parent.width, this.parent.height, -1, font);
		RenderHelper.turnOff();
	}
}
