package lostworlds.client.book.screen.book.element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.book.action.StringActionProcessor;
import lostworlds.client.book.data.element.TextComponentData;
import lostworlds.client.book.screen.book.TextComponentDataRenderer;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

public class TextComponentElement extends SizedBookElement {
	public TextComponentData[] text;
	private final List<Component> tooltip = new ArrayList<Component>();

	private transient String lastAction = "";

	public TextComponentElement(int x, int y, int width, int height, String text) {
		this(x, y, width, height, new TextComponent(text));
	}

	public TextComponentElement(int x, int y, int width, int height, Component text) {
		this(x, y, width, height, new TextComponentData(text));
	}

	public TextComponentElement(int x, int y, int width, int height, Collection<TextComponentData> text) {
		this(x, y, width, height, text.toArray(new TextComponentData[0]));
	}

	public TextComponentElement(int x, int y, int width, int height, TextComponentData... text) {
		super(x, y, width, height);

		this.text = text;
	}

	@Override
	public void draw(PoseStack stack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) {
		this.lastAction = TextComponentDataRenderer.drawText(stack, this.x, this.y, this.width, this.height, this.text, mouseX, mouseY, fontRenderer, this.tooltip);
	}

	@Override
	public void drawOverlay(PoseStack stack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) {
		if (this.tooltip.size() > 0) {
			this.drawTooltip(stack, this.tooltip, mouseX, mouseY, fontRenderer);
			this.tooltip.clear();
		}
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
		if (mouseButton == 0 && !lastAction.isEmpty()) {
			StringActionProcessor.process(lastAction, this.parent);
		}
	}
}
