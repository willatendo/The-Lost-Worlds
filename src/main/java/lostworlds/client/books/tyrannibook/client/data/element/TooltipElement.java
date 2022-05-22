package lostworlds.client.books.tyrannibook.client.data.element;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

public class TooltipElement extends SizedTyrannobookElement 
{
	private final List<Component> tooltips;

	public TooltipElement(List<Component> tooltip, int x, int y, int width, int height) 
	{
		super(x, y, width, height);

		this.tooltips = tooltip;
	}

	@Override
	public void draw(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) { }

	@Override
	public void drawOverlay(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) 
	{
		if(this.isHovered(mouseX, mouseY)) 
		{
			this.drawHoveringText(matrixStack, this.tooltips, mouseX, mouseY, fontRenderer);
		}
	}
}
