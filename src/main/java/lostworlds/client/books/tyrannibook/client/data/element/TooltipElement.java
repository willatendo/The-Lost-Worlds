package lostworlds.client.books.tyrannibook.client.data.element;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;

public class TooltipElement extends SizedTyrannobookElement 
{
	private final List<ITextComponent> tooltips;

	public TooltipElement(List<ITextComponent> tooltip, int x, int y, int width, int height) 
	{
		super(x, y, width, height);

		this.tooltips = tooltip;
	}

	@Override
	public void draw(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, FontRenderer fontRenderer) { }

	@Override
	public void drawOverlay(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, FontRenderer fontRenderer) 
	{
		if(this.isHovered(mouseX, mouseY)) 
		{
			this.drawHoveringText(matrixStack, this.tooltips, mouseX, mouseY, fontRenderer);
		}
	}
}
