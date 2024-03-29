package lostworlds.client.books.tyrannibook.client.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import lostworlds.client.books.tyrannibook.TyrannoUtils;
import lostworlds.client.books.tyrannibook.client.data.element.TextData;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class TextDataRenderer {
	@Nullable
	public static String drawText(MatrixStack stack, int x, int y, int boxWidth, int boxHeight, TextData[] data, int mouseX, int mouseY, FontRenderer fr) {
		List<ITextComponent> tooltip = new ArrayList<ITextComponent>();
		String action = drawText(stack, x, y, boxWidth, boxHeight, data, mouseX, mouseY, fr, tooltip);

		if (tooltip.size() > 0) {
			drawTooltip(stack, tooltip, mouseX, mouseY, fr);
		}

		return action;
	}

	public static String drawText(MatrixStack stack, int x, int y, int boxWidth, int boxHeight, TextData[] data, int mouseX, int mouseY, FontRenderer fr, List<ITextComponent> tooltip) {
		String action = "";

		int atX = x;
		int atY = y;

		float prevScale = 1.F;

		for (TextData item : data) {
			int box1X, box1Y, box1W = 9999, box1H = y + fr.lineHeight;
			int box2X, box2Y = 9999, box2W, box2H;
			int box3X = 9999, box3Y = 9999, box3W, box3H;

			if (item == null || item.text == null || item.text.isEmpty()) {
				continue;
			}
			if (item.text.equals("\n")) {
				atX = x;
				atY += fr.lineHeight;
				continue;
			}

			if (item.paragraph) {
				atX = x;
				atY += fr.lineHeight * 2 * prevScale;
			}

			prevScale = item.scale;

			String modifiers = "";

			if (item.useOldColor) {
				modifiers += TextFormatting.getByName(item.color);
			}

			if (item.bold) {
				modifiers += TextFormatting.BOLD;
			}
			if (item.italic) {
				modifiers += TextFormatting.ITALIC;
			}
			if (item.underlined) {
				modifiers += TextFormatting.UNDERLINE;
			}
			if (item.strikethrough) {
				modifiers += TextFormatting.STRIKETHROUGH;
			}
			if (item.obfuscated) {
				modifiers += TextFormatting.OBFUSCATED;
			}

			String text = translateString(item.text);

			String[] split = cropStringBySize(text, modifiers, boxWidth, boxHeight - (atY - y), boxWidth - (atX - x), fr, item.scale);

			box1X = atX;
			box1Y = atY;
			box2X = x;
			box2W = x + boxWidth;

			for (int i = 0; i < split.length; i++) {
				if (i == split.length - 1) {
					box3X = atX;
					box3Y = atY;
				}

				String s = split[i];
				drawScaledString(stack, fr, modifiers + s, atX, atY, item.rgbColor, item.dropshadow, item.scale);

				if (i < split.length - 1) {
					atY += fr.lineHeight;
					atX = x;
				}

				if (i == 0) {
					box2Y = atY;

					if (atX == x) {
						box1W = x + boxWidth;
					} else {
						box1W = atX;
					}
				}
			}

			box2H = atY;

			atX += fr.width(split[split.length - 1]) * item.scale;
			if (atX - x >= boxWidth) {
				atX = x;
				atY += fr.lineHeight * item.scale;
			}

			box3W = atX;
			box3H = (int) (atY + fr.lineHeight * item.scale);

			if (item.tooltip != null && item.tooltip.length > 0) {
				if ((mouseX >= box1X && mouseX <= box1W && mouseY >= box1Y && mouseY <= box1H && box1X != box1W && box1Y != box1H) || (mouseX >= box2X && mouseX <= box2W && mouseY >= box2Y && mouseY <= box2H && box2X != box2W && box2Y != box2H) || (mouseX >= box3X && mouseX <= box3W && mouseY >= box3Y && mouseY <= box3H && box3X != box3W && box1Y != box3H)) {
					tooltip.addAll(Arrays.asList(item.tooltip));
				}
			}

			if (item.action != null && !item.action.isEmpty()) {
				if ((mouseX >= box1X && mouseX <= box1W && mouseY >= box1Y && mouseY <= box1H && box1X != box1W && box1Y != box1H) || (mouseX >= box2X && mouseX <= box2W && mouseY >= box2Y && mouseY <= box2H && box2X != box2W && box2Y != box2H) || (mouseX >= box3X && mouseX <= box3W && mouseY >= box3Y && mouseY <= box3H && box3X != box3W && box1Y != box3H)) {
					action = item.action;
				}
			}

			if (atY >= y + boxHeight) {
				if (item.dropshadow) {
					fr.drawShadow(stack, "...", atX, atY, 0);
				} else {
					fr.draw(stack, "...", atX, atY, 0);
				}
				break;
			}
			y = atY;
		}

		if (TyrannobookScreen.debug && action != null && !action.isEmpty()) {
			tooltip.add(StringTextComponent.EMPTY);
			tooltip.add(new StringTextComponent("Action: " + action).withStyle(TextFormatting.GRAY));
		}

		return action;
	}

	public static String translateString(String s) {
		s = s.replace("$$(", "$\0(").replace(")$$", ")\0$");

		while (s.contains("$(") && s.contains(")$") && s.indexOf("$(") < s.indexOf(")$")) {
			String loc = s.substring(s.indexOf("$(") + 2, s.indexOf(")$"));
			s = s.replace("$(" + loc + ")$", I18n.get(loc));
		}

		if (s.indexOf("$(") > s.indexOf(")$") || s.contains(")$")) {
			TyrannoUtils.LOGGER.error("[Books] [TextDataRenderer] Detected unbalanced localization symbols \"$(\" and \")$\" in string: \"" + s + "\".");
		}

		return s.replace("$\0(", "$(").replace(")\0$", ")$");
	}

	public static String[] cropStringBySize(String s, String modifiers, int width, int height, FontRenderer fr, float scale) {
		return cropStringBySize(s, modifiers, width, height, width, fr, scale);
	}

	public static String[] cropStringBySize(String s, String modifiers, int width, int height, int firstWidth, FontRenderer fr, float scale) {
		int curWidth = 0;
		int curHeight = (int) (fr.lineHeight * scale);

		for (int i = 0; i < s.length(); i++) {
			curWidth += fr.width(modifiers + s.charAt(i)) * scale;

			if (s.charAt(i) == '\n' || (curHeight == (int) (fr.lineHeight * scale) && curWidth > firstWidth) || (curHeight != (int) (fr.lineHeight * scale) && curWidth > width)) {
				int oldI = i;
				if (s.charAt(i) != '\n') {
					while (i >= 0 && s.charAt(i) != ' ') {
						i--;
					}
					if (i <= 0) {
						i = oldI;
					}
				} else {
					oldI++;
				}

				s = s.substring(0, i) + "\r" + StringUtils.stripStart(s.substring(i + (i == oldI ? 0 : 1)), " ");

				i++;
				curWidth = 0;
				curHeight += fr.lineHeight * scale;

				if (curHeight >= height) {
					return s.substring(0, i).split("\r");
				}
			}
		}

		return s.split("\r");
	}

	public static void drawTooltip(MatrixStack stack, List<ITextComponent> textLines, int mouseX, int mouseY, FontRenderer font) {
		GuiUtils.drawHoveringText(stack, textLines, mouseX, mouseY, TyrannobookScreen.PAGE_WIDTH, TyrannobookScreen.PAGE_HEIGHT, TyrannobookScreen.PAGE_WIDTH, font);
		RenderHelper.turnOff();
	}

	public static void drawScaledString(MatrixStack stack, FontRenderer font, String text, float x, float y, int color, boolean dropShadow, float scale) {
		RenderSystem.pushMatrix();
		RenderSystem.translatef(x, y, 0);
		RenderSystem.scalef(scale, scale, 1F);
		if (dropShadow) {
			font.drawShadow(stack, text, 0, 0, color);
		} else {
			font.draw(stack, text, 0, 0, color);
		}
		RenderSystem.popMatrix();
	}

	@SuppressWarnings("unused")
	private static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
		float f = (float) (startColor >> 24 & 255) / 255.0F;
		float f1 = (float) (startColor >> 16 & 255) / 255.0F;
		float f2 = (float) (startColor >> 8 & 255) / 255.0F;
		float f3 = (float) (startColor & 255) / 255.0F;
		float f4 = (float) (endColor >> 24 & 255) / 255.0F;
		float f5 = (float) (endColor >> 16 & 255) / 255.0F;
		float f6 = (float) (endColor >> 8 & 255) / 255.0F;
		float f7 = (float) (endColor & 255) / 255.0F;
		RenderSystem.disableTexture();
		RenderSystem.disableAlphaTest();
		RenderSystem.blendFuncSeparate(770, 771, 1, 0);
		RenderSystem.shadeModel(7425);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexBuffer = tessellator.getBuilder();
		vertexBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
		vertexBuffer.vertex((double) right, (double) top, 0D).color(f1, f2, f3, f).endVertex();
		vertexBuffer.vertex((double) left, (double) top, 0D).color(f1, f2, f3, f).endVertex();
		vertexBuffer.vertex((double) left, (double) bottom, 0D).color(f5, f6, f7, f4).endVertex();
		vertexBuffer.vertex((double) right, (double) bottom, 0D).color(f5, f6, f7, f4).endVertex();
		tessellator.end();
		RenderSystem.shadeModel(7424);
		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();
	}
}
