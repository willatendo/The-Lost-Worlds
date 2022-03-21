package lostworlds.client.book.transformer;

import tyrannotitanlib.library.tyrannobook.client.TyrannobookTransformer;
import tyrannotitanlib.library.tyrannobook.client.data.PageData;
import tyrannotitanlib.library.tyrannobook.client.data.SectionData;
import tyrannotitanlib.library.tyrannobook.client.data.TyrannobookData;
import tyrannotitanlib.library.tyrannobook.client.data.content.PageContent;

public abstract class SectionTransformer extends TyrannobookTransformer {
	protected final String sectionName;

	public SectionTransformer(String sectionName) {
		this.sectionName = sectionName;
	}

	@Override
	public final void transform(TyrannobookData book) {
		SectionData data = null;
		for (SectionData section : book.sections) {
			if (sectionName.equals(section.name)) {
				data = section;
				break;
			}
		}

		if (data != null) {
			transform(book, data);
		}
	}

	public abstract void transform(TyrannobookData book, SectionData section);

	protected PageData addPage(SectionData data, String name, String type, PageContent content) {
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
