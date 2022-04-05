package lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content;

import java.util.ArrayList;

import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.TyrannobookData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.TextData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.TextElement;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.TyrannobookElement;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.screen.TyrannobookScreen;
import net.minecraft.client.Minecraft;

public class ContentTableOfContents extends PageContent {
	public String title;
	public TextData[] data;

	public ContentTableOfContents(String title, TextData... contents) {
		this.title = title;
		this.data = contents;
	}

	@Override
	public void build(TyrannobookData book, ArrayList<TyrannobookElement> list, boolean rightSide) {
		int y = 0;

		if (this.title != null && !this.title.trim().isEmpty()) {
			this.addTitle(list, this.title);
			y += TITLE_HEIGHT;
		}

		for (int i = 0; i < this.data.length; i++) {
			TextData text = this.data[i];
			list.add(new TextElement(0, y + i * (int) (Minecraft.getInstance().font.lineHeight * text.scale), TyrannobookScreen.PAGE_WIDTH, Minecraft.getInstance().font.lineHeight, text));
		}
	}
}
