package lostworlds.client.books.lostworlds.content;

import java.util.ArrayList;

import lostworlds.client.books.tyrannibook.client.data.TyrannobookData;
import lostworlds.client.books.tyrannibook.client.data.content.ContentImageText;
import lostworlds.client.books.tyrannibook.client.data.element.ImageData;
import lostworlds.client.books.tyrannibook.client.data.element.ImageElement;
import lostworlds.client.books.tyrannibook.client.data.element.TextElement;
import lostworlds.client.books.tyrannibook.client.data.element.TyrannobookElement;
import lostworlds.client.books.tyrannibook.client.screen.TyrannobookScreen;

public class ContentImageText2 extends ContentImageText {
	public static final transient String ID = "imageText2";

	@Override
	public void build(TyrannobookData book, ArrayList<TyrannobookElement> list, boolean rightSide) {
		int y = TITLE_HEIGHT;

		if (this.title == null || this.title.isEmpty()) {
			y = 0;
		} else {
			this.addTitle(list, this.title);
		}

		if (this.image != null && this.image.location != null) {
			int x = (TyrannobookScreen.PAGE_HEIGHT - this.image.width) / 2;
			list.add(new ImageElement(x, y, -1, -1, this.image));
			y += this.image.height;
		} else {
			list.add(new ImageElement(0, y, 32, 32, ImageData.MISSING));
		}

		if (this.text != null && this.text.length > 0) {
			y += 5;
			list.add(new TextElement(0, y, TyrannobookScreen.PAGE_WIDTH, TyrannobookScreen.PAGE_HEIGHT - y, this.text));
		}
	}
}
