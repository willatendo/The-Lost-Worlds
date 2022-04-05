package lostworlds.repack.tyrannotitanlib.tyrannibook.client;

import java.util.Iterator;

import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.PageData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.SectionData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.TyrannobookData;

public class PaddingBookTransformer extends TyrannobookTransformer {
	public static final PaddingBookTransformer INSTANCE = new PaddingBookTransformer();

	private PaddingBookTransformer() {
	}

	@Override
	public void transform(TyrannobookData bookData) {
		boolean isLeft = false;
		for (SectionData section : bookData.sections) {
			Iterator<PageData> pageIterator = section.pages.iterator();
			while (pageIterator.hasNext()) {
				PageData data = pageIterator.next();
				if (data.content instanceof ContentPadding && ((ContentPadding) data.content).isLeft() == isLeft) {
					pageIterator.remove();
				} else {
					isLeft = !isLeft;
				}
			}
		}
	}
}
