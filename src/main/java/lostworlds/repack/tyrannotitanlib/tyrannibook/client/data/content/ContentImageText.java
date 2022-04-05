package lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content;

import java.util.ArrayList;

import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.TyrannobookData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.ImageData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.ImageElement;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.TextData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.TextElement;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.TyrannobookElement;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.screen.TyrannobookScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContentImageText extends PageContent {
	public String title = null;
	public ImageData image;
	public TextData[] text;

	@Override
	public void build(TyrannobookData book, ArrayList<TyrannobookElement> list, boolean rightSide) {
		int y = TITLE_HEIGHT;

		if (this.title == null || this.title.isEmpty()) {
			y = 0;
		} else {
			this.addTitle(list, this.title);
		}

		if (this.image != null && this.image.location != null) {
			list.add(new ImageElement(0, y, TyrannobookScreen.PAGE_WIDTH, 100, this.image));
		} else {
			list.add(new ImageElement(0, y, 32, 32, ImageData.MISSING));
		}

		if (this.text != null && this.text.length > 0) {
			list.add(new TextElement(0, y + 105, TyrannobookScreen.PAGE_WIDTH, TyrannobookScreen.PAGE_HEIGHT - 105 - y, this.text));
		}
	}
}