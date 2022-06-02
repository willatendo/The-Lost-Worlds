package lostworlds.client.book.transformer;

import javax.annotation.Nullable;

import lostworlds.client.book.data.PageData;
import lostworlds.client.book.data.SectionData;
import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.content.ListingContent;

public class ContentListingSectionTransformer extends SectionTransformer {
	private final Boolean largeTitle;
	private final Boolean centerTitle;

	public ContentListingSectionTransformer(String sectionName, @Nullable Boolean largeTitle, @Nullable Boolean centerTitle) {
		super(sectionName);
		this.largeTitle = largeTitle;
		this.centerTitle = centerTitle;
	}

	public ContentListingSectionTransformer(String sectionName) {
		this(sectionName, null, null);
	}

	@Override
	public void transform(BookData book, SectionData data) {
		ListingContent listing = new ListingContent();
		listing.setLargeTitle(largeTitle);
		listing.setCenterTitle(centerTitle);
		listing.title = book.translate(sectionName);
		String subtextKey = sectionName + ".subtext";
		if (book.strings.containsKey(subtextKey)) {
			listing.subText = book.translate(subtextKey);
		}

		PageData listingPage = new PageData(true);
		listingPage.name = sectionName;
		listingPage.source = data.source;
		listingPage.parent = data;
		listingPage.content = listing;

		data.pages.removeIf(sectionPage -> !processPage(book, listing, sectionPage));
		if (listing.hasEntries()) {
			listingPage.load();
			data.pages.add(0, listingPage);
		}
	}

	protected boolean processPage(BookData book, ListingContent listing, PageData page) {
		if (!IndexTransformer.isPageHidden(page) && !page.name.equals("hidden")) {
			listing.addEntry(page.getTitle(), page);
		}
		return true;
	}
}
