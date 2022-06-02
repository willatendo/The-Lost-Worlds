package lostworlds.client.book.data.content;

import java.util.ArrayList;

import lostworlds.client.book.data.SectionData;
import lostworlds.client.book.data.BookData;
import lostworlds.client.book.screen.book.BookScreen;
import lostworlds.client.book.screen.book.element.BookElement;
import lostworlds.client.book.screen.book.element.SelectionElement;

public class SectionListContent extends PageContent {
	protected ArrayList<SectionData> sections = new ArrayList<>();

	public boolean addSection(SectionData data) {
		return this.sections.size() < 12 && this.sections.add(data);
	}

	@Override
	public void build(BookData book, ArrayList<BookElement> list, boolean rightSide) {
		int columns = book.appearance.drawFourColumnIndex ? 4 : 3;
		int width = (SelectionElement.WIDTH + 5) * columns - 5;
		int height = (SelectionElement.HEIGHT + 5) * 3 - 5;

		int ox = (BookScreen.PAGE_WIDTH - width) / 2;
		int oy = (BookScreen.PAGE_HEIGHT - height) / 2 - 5;

		int sectionRange = Math.min(columns * 3, this.sections.size());
		for (int i = 0; i < sectionRange; i++) {
			int ix = i % columns;
			int iy = i / columns;

			int x = ox + ix * (SelectionElement.WIDTH + 5);
			int y = oy + iy * (SelectionElement.HEIGHT + 5);

			list.add(new SelectionElement(x, y, this.sections.get(i)));
		}
	}
}
