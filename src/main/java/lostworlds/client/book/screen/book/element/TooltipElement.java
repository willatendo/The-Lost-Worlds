package lostworlds.client.book.screen.book.element;

import java.util.List;
import java.util.Optional;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

public class TooltipElement extends SizedBookElement {
	private final List<Component> tooltips;

	public TooltipElement(List<Component> tooltip, int x, int y, int width, int height) {
		super(x, y, width, height);

		this.tooltips = tooltip;
	}

	@Override
	public void draw(PoseStack stack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) {
	}

	@Override
	public void drawOverlay(PoseStack stack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) {
		if (this.isHovered(mouseX, mouseY)) {
			this.parent.renderTooltip(stack, this.tooltips, Optional.empty(), mouseX, mouseY, fontRenderer);
		}
	}
}
