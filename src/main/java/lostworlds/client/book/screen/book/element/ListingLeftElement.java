package lostworlds.client.book.screen.book.element;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.book.data.element.TextData;
import net.minecraft.client.gui.Font;

public class ListingLeftElement extends TextElement {
	private final boolean isClickable;
	private final int textStart;

	public ListingLeftElement(int x, int y, int width, int height, boolean subSection, TextData... text) {
		super(x, y, width, height, text);
		boolean isClickable = false;
		for (TextData data : text) {
			if (!data.action.isEmpty()) {
				isClickable = true;
				break;
			}
		}
		this.isClickable = isClickable;
		this.textStart = subSection ? 0 : 1;
		if (!subSection) {
			this.text = Lists.asList(new TextData(), this.text).toArray(new TextData[this.text.length + 1]);
			this.text[0].color = "dark red";
			this.text[0].text = "- ";
		}
	}

	@Override
	public void draw(PoseStack stack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) {
		if (isClickable) {
			if (this.isHovered(mouseX, mouseY)) {
				if (textStart == 1) {
					this.text[0].text = "> ";
				}
				for (int i = textStart; i < this.text.length; i++) {
					this.text[i].color = "dark red";
					this.text[i].underlined = true;
				}
			} else {
				if (textStart == 1) {
					this.text[0].text = "- ";
				}
				for (int i = textStart; i < this.text.length; i++) {
					this.text[i].color = "black";
					this.text[i].underlined = false;
				}
			}
		}

		super.draw(stack, mouseX, mouseY, partialTicks, fontRenderer);
	}
}
