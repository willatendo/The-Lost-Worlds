package lostworlds.client.book.data.content;

import java.util.ArrayList;

import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.element.ImageData;
import lostworlds.client.book.data.element.TextData;
import lostworlds.client.book.screen.book.BookScreen;
import lostworlds.client.book.screen.book.element.BookElement;
import lostworlds.client.book.screen.book.element.ImageElement;
import lostworlds.client.book.screen.book.element.TextElement;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.resources.ResourceLocation;

public class ImageTextContent extends PageContent {
	public static final ResourceLocation ID = LostWorldsUtils.rL("image_text");

	public String title = null;
	public ImageData image;
	public TextData[] text;
	public boolean centerImage = true;

	@Override
	public void build(BookData book, ArrayList<BookElement> list, boolean rightSide) {
		int y = getTitleHeight();

		if (this.title == null || this.title.isEmpty()) {
			y = 0;
		} else {
			this.addTitle(list, this.title);
		}

		if (this.image != null && this.image.location != null) {
			int x = 0;
			int width = BookScreen.PAGE_WIDTH;
			if (centerImage && this.image.width != -1) {
				x = (BookScreen.PAGE_WIDTH - this.image.width) / 2;
				width = this.image.width;
			}
			ImageElement element = new ImageElement(x, y, width, 100, this.image);
			list.add(element);
			y += element.height + 5;
		} else {
			list.add(new ImageElement(0, y, 32, 32, ImageData.MISSING));
			y += 37;
		}

		if (this.text != null && this.text.length > 0) {
			list.add(new TextElement(0, y, BookScreen.PAGE_WIDTH, BookScreen.PAGE_HEIGHT - y, this.text));
		}
	}
}
