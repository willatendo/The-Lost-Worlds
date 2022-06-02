package lostworlds.client.book.data.content;

import java.util.ArrayList;

import lostworlds.client.book.data.PageData;
import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.element.TextData;
import lostworlds.client.book.repository.BookRepository;
import lostworlds.client.book.screen.book.BookScreen;
import lostworlds.client.book.screen.book.element.BookElement;
import lostworlds.client.book.screen.book.element.TextElement;

public abstract class PageContent {
	public static final transient int TITLE_HEIGHT = 16;
	public static final transient int LARGE_TITLE_HEIGHT = 20;

	public transient PageData parent;
	public transient BookRepository source;

	private Boolean centerTitle;
	private Boolean largeTitle;

	public String getTitle() {
		return "";
	}

	public void load() {
	}

	public abstract void build(BookData book, ArrayList<BookElement> list, boolean rightSide);

	private boolean isLarge() {
		if (this.largeTitle != null) {
			return this.largeTitle;
		} else if (this.parent != null && this.parent.parent != null && this.parent.parent.parent != null) {
			return this.parent.parent.parent.appearance.largePageTitles;
		}
		return false;
	}

	private boolean isCentered() {
		if (this.centerTitle != null) {
			return this.centerTitle;
		} else if (this.parent != null && this.parent.parent != null && this.parent.parent.parent != null) {
			return this.parent.parent.parent.appearance.centerPageTitles;
		}
		return false;
	}

	protected int getTitleHeight() {
		return this.isLarge() ? LARGE_TITLE_HEIGHT : TITLE_HEIGHT;
	}

	public void addTitle(ArrayList<BookElement> list, String titleText) {
		this.addTitle(list, titleText, false);
	}

	public void addTitle(ArrayList<BookElement> list, String titleText, boolean dropShadow) {
		this.addTitle(list, titleText, dropShadow, 0, 0);
	}

	public void addTitle(ArrayList<BookElement> list, String titleText, boolean dropShadow, int color) {
		this.addTitle(list, titleText, dropShadow, color, 0);
	}

	public void addTitle(ArrayList<BookElement> list, String titleText, boolean dropShadow, int color, int y) {
		TextData title = new TextData(titleText);

		boolean isLarge = isLarge();
		title.scale = isLarge ? 1.2f : 1.0f;
		title.underlined = true;
		title.dropshadow = dropShadow;

		if (color != 0) {
			title.useOldColor = false;
			title.rgbColor = color;
		}

		int x = 0;
		int w = BookScreen.PAGE_WIDTH;
		if (isCentered()) {
			w = (int) Math.ceil(this.parent.parent.parent.fontRenderer.width(titleText) * title.scale) + 1;
			x = (BookScreen.PAGE_WIDTH - w) / 2;
		}
		list.add(new TextElement(x, y, w, isLarge ? 11 : 9, title));
	}

	public void addText(ArrayList<BookElement> list, String subText, boolean dropShadow) {
		this.addText(list, subText, dropShadow, 0, 0);
	}

	public void addText(ArrayList<BookElement> list, String subText, boolean dropShadow, int color) {
		this.addText(list, subText, dropShadow, color, 0);
	}

	public int addText(ArrayList<BookElement> list, String text, boolean dropShadow, int color, int y) {
		TextData subText = new TextData(text);
		subText.dropshadow = dropShadow;
		if (color != 0) {
			subText.useOldColor = false;
			subText.rgbColor = color;
		}
		int height = this.parent.parent.parent.fontRenderer.wordWrapHeight(text, BookScreen.PAGE_WIDTH) * 12 / 9;
		list.add(new TextElement(5, y, BookScreen.PAGE_WIDTH, height, subText));
		return height;
	}

	public Boolean getCenterTitle() {
		return this.centerTitle;
	}

	public void setCenterTitle(Boolean centerTitle) {
		this.centerTitle = centerTitle;
	}

	public Boolean getLargeTitle() {
		return this.largeTitle;
	}

	public void setLargeTitle(Boolean largeTitle) {
		this.largeTitle = largeTitle;
	}
}
