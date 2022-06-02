package lostworlds.client.book.screen.book;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.book.screen.book.element.BookElement;
import net.minecraft.client.gui.Font;

public interface ILayerRenderFunction {
	void draw(BookElement element, PoseStack stack, int mouseX, int mouseY, float partialTicks, Font fontRenderer);
}
