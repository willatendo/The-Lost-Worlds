package lostworlds.client.books.tyrannibook.client.data.content;

import java.util.ArrayList;

import lostworlds.client.books.tyrannibook.client.data.TyrannobookData;
import lostworlds.client.books.tyrannibook.client.data.element.ImageData;
import lostworlds.client.books.tyrannibook.client.data.element.ImageElement;
import lostworlds.client.books.tyrannibook.client.data.element.TextData;
import lostworlds.client.books.tyrannibook.client.data.element.TextElement;
import lostworlds.client.books.tyrannibook.client.data.element.TyrannobookElement;
import lostworlds.client.books.tyrannibook.client.screen.TyrannobookScreen;

public class ContentTextLeftImage extends PageContent {
	public String title = null;
	public ImageData image;
	public TextData[] text1;
	public TextData[] text2;

	@Override
	public void build(TyrannobookData book, ArrayList<TyrannobookElement> list, boolean rightSide) {
		int y = TITLE_HEIGHT;

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
			list.add(new TextElement(55, y, TyrannobookScreen.PAGE_WIDTH - 55, 50, this.text1));
		}

		if (this.text2 != null && this.text2.length > 0) {
			list.add(new TextElement(0, y + 55, TyrannobookScreen.PAGE_WIDTH, TyrannobookScreen.PAGE_HEIGHT - 55 - y, this.text2));
		}
	}
}
