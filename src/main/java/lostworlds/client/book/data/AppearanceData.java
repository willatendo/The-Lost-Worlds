package lostworlds.client.book.data;

import java.util.Objects;

import lostworlds.client.book.screen.book.BookTextures;
import net.minecraft.resources.ResourceLocation;

public class AppearanceData implements DataItem {
	private ResourceLocation coverTexture;
	public String title = "";
	public String subtitle = "";
	public int coverColor = 0x8B4631;
	private int coverTextColor = 0xAE8000;
	private ResourceLocation bookTexture;
	public int arrowColor = 0xFFFFD3;
	public int arrowColorHover = 0xFF541C;
	public int hoverColor = 0x77EE541C;
	private int pageTint = 0xFFFFFF;
	public boolean drawPageNumbers = true;
	public boolean drawSectionListText = false;
	public boolean drawFourColumnIndex = false;

	public boolean centerPageTitles = false;
	public boolean largePageTitles = false;

	public int slotColor = 0xFF844C;
	public int lockedSectionColor = 0x000000;
	public int structureButtonColor = 0xe3E3BC;
	public int structureButtonColorHovered = 0x76D1E8;
	public int structureButtonColorToggled = 0x67C768;

	public float scale = 0.5F;

	public ResourceLocation getCoverTexture() {
		return Objects.requireNonNullElse(coverTexture, BookTextures.BOOKFRONT_TEXTURE);
	}

	public ResourceLocation getBookTexture() {
		return Objects.requireNonNullElse(bookTexture, BookTextures.BOOK_TEXTURE);
	}

	public int getPageTint() {
		return this.pageTint;
	}

	public void setPageTint(int pageTint) {
		this.pageTint = pageTint;
	}

	public int getCoverTextColor() {
		return this.coverTextColor;
	}

	public void setCoverTextColor(int coverTextColor) {
		this.coverTextColor = coverTextColor;
	}

	@Override
	public void load() {
	}
}
