package lostworlds.client.book.screen.book.element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.book.action.StringActionProcessor;
import lostworlds.client.book.data.element.TextData;
import lostworlds.client.book.screen.book.TextDataRenderer;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

public class TextElement extends SizedBookElement {

	public TextData[] text;
	private final List<Component> tooltip = new ArrayList<Component>();

	private transient String lastAction = "";

	public TextElement(int x, int y, int width, int height, String text) {
		this(x, y, width, height, new TextData(text));
	}

	public TextElement(int x, int y, int width, int height, Collection<TextData> text) {
		this(x, y, width, height, text.toArray(new TextData[0]));
	}

	public TextElement(int x, int y, int width, int height, TextData... text) {
		super(x, y, width, height);

		this.text = text;
	}

	@Override
	public void draw(PoseStack stack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) {
		this.lastAction = TextDataRenderer.drawText(stack, this.x, this.y, this.width, this.height, this.text, mouseX, mouseY, fontRenderer, this.tooltip);
	}

	@Override
	public void drawOverlay(PoseStack stack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) {
		if (this.tooltip.size() > 0) {
			drawTooltip(stack, this.tooltip, mouseX, mouseY, fontRenderer);
			this.tooltip.clear();
		}
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
		if (mouseButton == 0 && !this.lastAction.isEmpty()) {
			StringActionProcessor.process(this.lastAction, this.parent);
		}
	}
}
