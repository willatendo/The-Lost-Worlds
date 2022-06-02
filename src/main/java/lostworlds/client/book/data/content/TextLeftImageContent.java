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

public class TextLeftImageContent extends PageContent {
	public static final ResourceLocation ID = LostWorldsUtils.rL("text_left_image");

	public String title = null;
	public ImageData image;
	public TextData[] text1;
	public TextData[] text2;

	@Override
	public void build(BookData book, ArrayList<BookElement> list, boolean rightSide) {
		int y = getTitleHeight();

		if (this.title == null || this.title.isEmpty()) {
			y = 0;
		} else {
			this.addTitle(list, this.title);
		}

		if (this.image != null && this.image.location != null) {
			list.add(new ImageElement(0, y, 50, 50, this.image));
		} else {
			list.add(new ImageElement(0, y, 50, 50, ImageData.MISSING));
		}

		if (this.text1 != null && this.text1.length > 0) {
			list.add(new TextElement(55, y, BookScreen.PAGE_WIDTH - 55, 50, this.text1));
		}

		if (this.text2 != null && this.text2.length > 0) {
			list.add(new TextElement(0, y + 55, BookScreen.PAGE_WIDTH, BookScreen.PAGE_HEIGHT - 55 - y, this.text2));
		}
	}
}
