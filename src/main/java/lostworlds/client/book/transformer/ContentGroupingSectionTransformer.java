package lostworlds.client.book.transformer;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import lostworlds.client.book.data.PageData;
import lostworlds.client.book.data.SectionData;
import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.content.ListingContent;
import lostworlds.client.book.data.content.PaddingContent.ContentRightPadding;
import lostworlds.client.book.screen.book.BookScreen;

public class ContentGroupingSectionTransformer extends SectionTransformer {
	private final Boolean largeTitle;
	private final Boolean centerTitle;

	public ContentGroupingSectionTransformer(String sectionName, @Nullable Boolean largeTitle, @Nullable Boolean centerTitle) {
		super(sectionName);
		this.largeTitle = largeTitle;
		this.centerTitle = centerTitle;
	}

	public ContentGroupingSectionTransformer(String sectionName) {
		this(sectionName, null, null);
	}

	@Override
	public void transform(BookData book, SectionData data) {
		String title = book.translate(this.sectionName);
		String subtextKey = this.sectionName + ".subtext";
		String subText = null;
		if (book.strings.containsKey(subtextKey)) {
			subText = book.translate(subtextKey);
		}

		GroupingBuilder builder = new GroupingBuilder(data, title, subText, this.largeTitle, this.centerTitle);
		data.pages.removeIf(sectionPage -> !processPage(book, builder, sectionPage));

		if (builder.hasEntries()) {
			int i = 0;
			List<ListingContent> finishedListings = builder.getFinishedListings();
			if (finishedListings.size() % 2 == 0) {
				PageData padding = new PageData(true);
				padding.source = data.source;
				padding.parent = data;
				padding.content = new ContentRightPadding();
				padding.load();
				int sectionIndex = data.parent.sections.indexOf(data);
				if (sectionIndex > 0) {
					data.parent.sections.get(sectionIndex - 1).pages.add(padding);
				} else {
					data.pages.add(padding);
					i++;
				}
			}

			for (ListingContent listing : finishedListings) {
				PageData listingPage = new PageData(true);
				listingPage.name = this.sectionName;
				listingPage.source = data.source;
				listingPage.parent = data;
				listingPage.content = listing;
				listingPage.load();
				data.pages.add(i, listingPage);
				i++;
			}
		}
	}

	protected boolean processPage(BookData book, GroupingBuilder builder, PageData page) {
		if (!IndexTransformer.isPageHidden(page) && !page.name.equals("hidden")) {
			builder.addPage(page.getTitle(), page);
		}
		return true;
	}

	public static class GroupingBuilder {
		private static final int COLUMN_WIDTH = BookScreen.PAGE_WIDTH / 3;

		private final SectionData section;
		private int columns = 1;
		private int entriesInColumn = 0;
		private int maxInColumn;
		private ListingContent currentListing = new ListingContent();
		private final List<ListingContent> finishedListings = Lists.newArrayList(currentListing);

		public GroupingBuilder(SectionData section, @Nullable String title, @Nullable String subText, @Nullable Boolean largeTitle, @Nullable Boolean centerTitle) {
			this.section = section;
			this.currentListing.title = title;
			this.currentListing.subText = subText;
			this.currentListing.setLargeTitle(largeTitle);
			this.currentListing.setCenterTitle(centerTitle);
			this.maxInColumn = currentListing.getEntriesInColumn(section);
		}

		public boolean hasEntries() {
			return this.entriesInColumn > 0;
		}

		private void incrementColumns(String text) {
			this.entriesInColumn += this.section.parent.fontRenderer.wordWrapHeight(text, COLUMN_WIDTH) / 9;
		}

		private void startNewColumn(boolean forceBreak) {
			if (columns == 3) {
				this.currentListing = new ListingContent();
				ListingContent firstListing = this.finishedListings.get(0);
				this.currentListing.title = firstListing.title;
				this.currentListing.setLargeTitle(firstListing.getLargeTitle());
				this.currentListing.setCenterTitle(firstListing.getCenterTitle());
				this.maxInColumn = currentListing.getEntriesInColumn(section);
				this.finishedListings.add(currentListing);
				this.columns = 1;
			} else {
				this.columns++;
				if (forceBreak) {
					this.currentListing.addColumnBreak();
				}
			}
			this.entriesInColumn = 0;
		}

		public void addGroup(String name, @Nullable PageData data) {
			if (this.entriesInColumn != 0) {
				this.startNewColumn(true);
			}

			this.incrementColumns(name);
			this.currentListing.addEntry(name, data, true);
		}

		public void addPage(String name, PageData data) {
			if (this.entriesInColumn == this.maxInColumn) {
				this.startNewColumn(false);
			}
			this.incrementColumns("- " + name);
			this.currentListing.addEntry(name, data, false);
		}

		public List<ListingContent> getFinishedListings() {
			return this.finishedListings;
		}
	}
}
