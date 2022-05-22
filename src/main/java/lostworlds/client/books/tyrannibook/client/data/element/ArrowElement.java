package lostworlds.client.books.tyrannibook.client.data.element;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.books.tyrannibook.client.screen.ArrowButton;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button.OnPress;

public class ArrowElement extends ButtonElement {
	protected final ArrowButton button;

	public ArrowElement(int x, int y, ArrowButton.ArrowType arrowType, int arrowColor, int arrowColorHover, OnPress iPressable) {
		super(x, y, arrowType.w, arrowType.h);

		this.button = new ArrowButton(x, y, arrowType, arrowColor, arrowColorHover, iPressable);
	}

	@Override
	public void draw(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) {
		this.button.renderButton(matrixStack, mouseX, mouseY, partialTicks);
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
		if (this.button != null && this.isHovered(mouseX, mouseY)) {
			this.button.onPress();
		}
	}
}
