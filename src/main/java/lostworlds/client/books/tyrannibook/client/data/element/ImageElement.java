package lostworlds.client.books.tyrannibook.client.data.element;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;

public class ImageElement extends SizedTyrannobookElement {
	public ImageData image;
	public int colorMultiplier;

	private ItemElement itemElement;

	public ImageElement(ImageData image) {
		this(image, 0xFFFFFF);
	}

	public ImageElement(ImageData image, int colorMultiplier) {
		this(image.x, image.y, image.width, image.height, image, colorMultiplier);
	}

	public ImageElement(int x, int y, int width, int height, ImageData image) {
		this(x, y, width, height, image, image.colorMultiplier);
	}

	public ImageElement(int x, int y, int width, int height, ImageData image, int colorMultiplier) {
		super(x, y, width, height);

		this.image = image;

		if (image.x != -1) {
			x = image.x;
		}
		if (image.y != -1) {
			y = image.y;
		}
		if (image.width != -1) {
			width = image.width;
		}
		if (image.height != -1) {
			height = image.height;
		}
		if (image.colorMultiplier != 0xFFFFFF) {
			colorMultiplier = image.colorMultiplier;
		}

		this.x = x == -1 ? 0 : x;
		this.y = y == -1 ? 0 : y;
		this.width = width;
		this.height = height;
		this.colorMultiplier = colorMultiplier;

		if (image.item != null) {
			this.itemElement = new ItemElement(0, 0, 1F, image.item.getItems());
		}
	}

	@Override
	public void draw(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, FontRenderer fontRenderer) {
		float r = ((this.colorMultiplier >> 16) & 0xff) / 255.F;
		float g = ((this.colorMultiplier >> 8) & 0xff) / 255.F;
		float b = (this.colorMultiplier & 0xff) / 255.F;

		RenderSystem.color3f(r, g, b);

		if (this.image.item == null) {
			this.renderEngine.bind(this.image.location);

			blitRaw(matrixStack, this.x, this.y, this.width, this.height, this.image.u, this.image.u + this.image.uw, this.image.v, this.image.v + this.image.vh, this.image.texWidth, this.image.texHeight);
		} else {
			RenderSystem.pushMatrix();
			RenderSystem.translatef(this.x, this.y, 0F);
			RenderSystem.scalef(this.width / 16F, this.height / 16F, 1F);
			this.itemElement.draw(matrixStack, mouseX, mouseY, partialTicks, fontRenderer);
			RenderHelper.turnOff();
			RenderSystem.popMatrix();
		}
	}

	public static void blitRaw(MatrixStack matrixStack, int x, int y, int w, int h, int minU, int maxU, int minV, int maxV, float tw, float th) {
		innerBlit(matrixStack.last().pose(), x, x + w, y, y + h, 0, minU / tw, maxU / tw, minV / th, maxV / th);
	}
}
