package lostworlds.client.book.data.content;

import java.util.Iterator;

import lostworlds.client.book.data.PageData;
import lostworlds.client.book.data.SectionData;
import lostworlds.client.book.data.BookData;
import lostworlds.client.book.transformer.BookTransformer;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.resources.ResourceLocation;

public abstract class PaddingContent extends BlankContent {
	public static final ResourceLocation LEFT_ID = LostWorldsUtils.rL("left_padding");
	public static final ResourceLocation RIGHT_ID = LostWorldsUtils.rL("right_padding");

	/** If true, this page is padding the left side, false pads the right side */
	public abstract boolean isLeft();

	/** Left variant */
	public static class ContentLeftPadding extends PaddingContent {
		@Override
		public boolean isLeft() {
			return true;
		}
	}

	/** Right variant */
	public static class ContentRightPadding extends PaddingContent {
		@Override
		public boolean isLeft() {
			return false;
		}
	}

	/** Transformer to make this page type work */
	public static class PaddingBookTransformer extends BookTransformer {
		/** Regular transformer considering indexes */
		public static final PaddingBookTransformer INSTANCE = new PaddingBookTransformer();

		private PaddingBookTransformer() {
		}

		@Override
		public void transform(BookData bookData) {
			// first page is on the right side
			boolean previousLeft = true;

			// iterate through all pages, tracking whehter we are left or right
			for (SectionData section : bookData.sections) {
				Iterator<PageData> pageIterator = section.pages.iterator();
				while (pageIterator.hasNext()) {
					PageData data = pageIterator.next();
					// if its left and the current page is odd, or its right and the current page is
					// even, skip
					if (data.content instanceof PaddingContent && ((PaddingContent) data.content).isLeft() == previousLeft) {
						pageIterator.remove();
					} else {
						previousLeft = !previousLeft;
					}
				}
			}
		}
	}
}
