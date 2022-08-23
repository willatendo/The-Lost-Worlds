package lostworlds.client.books.tyrannibook.client.data.content;

import java.util.ArrayList;

import lostworlds.client.books.tyrannibook.client.data.PageData;
import lostworlds.client.books.tyrannibook.client.data.TyrannobookData;
import lostworlds.client.books.tyrannibook.client.data.element.TextData;
import lostworlds.client.books.tyrannibook.client.data.element.TextElement;
import lostworlds.client.books.tyrannibook.client.data.element.TyrannobookElement;
import lostworlds.client.books.tyrannibook.client.repository.TyrannobookRepository;
import lostworlds.client.books.tyrannibook.client.screen.TyrannobookScreen;

public abstract class PageContent {
	public static final transient int TITLE_HEIGHT = 16;

	public transient PageData parent;
	public transient TyrannobookRepository source;

	public void load() {
	}

	public abstract void build(TyrannobookData book, ArrayList<TyrannobookElement> list, boolean rightSide);

	public void addTitle(ArrayList<TyrannobookElement> list, String title) {
		TextData tdTitle = new TextData(title);
		tdTitle.underlined = true;
		this.addTitle(list, new TextData[] { tdTitle });
	}

	public void addTitle(ArrayList<TyrannobookElement> list, TextData[] title) {
		list.add(new TextElement(0, 0, TyrannobookScreen.PAGE_WIDTH, 9, title));
	}
}
