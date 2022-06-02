package lostworlds.client.book.data.content;

import java.util.ArrayList;

import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.element.TextData;
import lostworlds.client.book.screen.book.BookScreen;
import lostworlds.client.book.screen.book.element.BookElement;
import lostworlds.client.book.screen.book.element.TextElement;
import net.minecraft.client.Minecraft;

public class TableOfContentsContent extends PageContent {
	public String title;
	public TextData[] data;

	public TableOfContentsContent(String title, TextData... contents) {
		this.title = title;
		this.data = contents;
	}

	@Override
	public void build(BookData book, ArrayList<BookElement> list, boolean rightSide) {
		int y = 0;

		if (this.title != null && !this.title.trim().isEmpty()) {
			this.addTitle(list, this.title);
			y += getTitleHeight();
		}

		for (int i = 0; i < this.data.length; i++) {
			TextData text = this.data[i];
			list.add(new TextElement(0, y + i * (int) (Minecraft.getInstance().font.lineHeight * text.scale), BookScreen.PAGE_WIDTH, Minecraft.getInstance().font.lineHeight, text));
		}
	}
}
