package lostworlds.client.book.transformer;

import lostworlds.client.book.data.PageData;
import lostworlds.client.book.data.SectionData;
import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.content.PageContent;
import net.minecraft.resources.ResourceLocation;

public abstract class SectionTransformer extends BookTransformer {
	protected final String sectionName;

	public SectionTransformer(String sectionName) {
		this.sectionName = sectionName;
	}

	@Override
	public final void transform(BookData book) {
		SectionData data = null;
		for (SectionData section : book.sections) {
			if (this.sectionName.equals(section.name)) {
				data = section;
				break;
			}
		}

		if (data != null) {
			transform(book, data);
		}
	}

	public abstract void transform(BookData book, SectionData section);

	protected PageData addPage(SectionData data, String name, ResourceLocation type, PageContent content) {
		PageData page = new PageData(true);
		page.source = data.source;
		page.parent = data;
		page.name = name;
		page.type = type;
		page.content = content;
		page.load();

		data.pages.add(page);

		return page;
	}
}
