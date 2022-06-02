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

public class TextImageContent extends PageContent {
	public static final ResourceLocation ID = LostWorldsUtils.rL("text_image");

	public String title = null;
	public TextData[] text;
	public ImageData image;

	@Override
	public void build(BookData book, ArrayList<BookElement> list, boolean rightSide) {
		int y = getTitleHeight();

		if (this.title == null || this.title.isEmpty()) {
			y = 0;
		} else {
			this.addTitle(list, this.title);
		}

		if (this.text != null && this.text.length > 0) {
			list.add(new TextElement(0, y, BookScreen.PAGE_WIDTH, BookScreen.PAGE_HEIGHT - 105, this.text));
		}

		if (this.image != null && this.image.location != null) {
			list.add(new ImageElement(0, y + BookScreen.PAGE_HEIGHT - 100, BookScreen.PAGE_WIDTH, 100 - y, this.image));
		} else {
			list.add(new ImageElement(0, y + BookScreen.PAGE_HEIGHT - 100, BookScreen.PAGE_WIDTH, 100 - y, ImageData.MISSING));
		}
	}
}
