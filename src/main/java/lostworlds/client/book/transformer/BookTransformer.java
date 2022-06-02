package lostworlds.client.book.transformer;

import java.util.List;

import lostworlds.client.book.data.PageData;
import lostworlds.client.book.data.SectionData;
import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.content.TableOfContentsContent;
import lostworlds.client.book.data.content.PaddingContent.PaddingBookTransformer;
import lostworlds.client.book.data.element.TextData;

public abstract class BookTransformer {
	public static BookTransformer indexTranformer() {
		return IndexTransformer.INSTANCE;
	}

	public static BookTransformer contentTableTransformer() {
		return ContentTableTransformer.INSTANCE;
	}

	public static BookTransformer contentTableTransformerForSection(String sectionName) {
		return new ContentTableTransformer(sectionName);
	}

	public static BookTransformer paddingTransformer() {
		return PaddingBookTransformer.INSTANCE;
	}

	public abstract void transform(BookData book);

	protected static class ContentTableTransformer extends BookTransformer {

		public static final ContentTableTransformer INSTANCE = new ContentTableTransformer();

		private final String sectionToTransform;

		public ContentTableTransformer(String sectionToTransform) {
			this.sectionToTransform = sectionToTransform;
		}

		public ContentTableTransformer() {
			this.sectionToTransform = null;
		}

		@Override
		public void transform(BookData book) {
			final int ENTRIES_PER_PAGE = 24;

			for (SectionData section : book.sections) {
				if (section.name.equals("index")) {
					continue;
				}
				if (this.sectionToTransform != null && !section.name.equals(this.sectionToTransform)) {
					continue;
				}

				List<PageData> sectionPages = IndexTransformer.filterHiddenPages(section.pages);

				int genPages = (int) Math.ceil(sectionPages.size() * 1.F / ENTRIES_PER_PAGE);

				if (genPages == 0) {
					continue;
				}

				PageData[] pages = new PageData[genPages];

				for (int i = 0; i < pages.length; i++) {
					pages[i] = new PageData(true);
					pages[i].name = "tableofcontents" + i;
					TextData[] text = new TextData[i > pages.length - 1 ? ENTRIES_PER_PAGE : sectionPages.size() - (genPages - 1) * ENTRIES_PER_PAGE];

					for (int j = 0; j < text.length; j++) {
						text[j] = new TextData((i * ENTRIES_PER_PAGE + j + 1) + ". " + sectionPages.get(i * ENTRIES_PER_PAGE + j).getTitle());
						text[j].action = "mantle:go-to-page-rtn " + section.name + "." + sectionPages.get(i * ENTRIES_PER_PAGE + j).name;
					}

					pages[i].content = new TableOfContentsContent(i == 0 ? section.getTitle() : "", text);
				}

				for (int i = pages.length - 1; i >= 0; i--) {
					section.pages.add(0, pages[i]);
				}
			}
		}
	}
}
