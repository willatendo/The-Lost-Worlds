package lostworlds.client.books.tyrannibook.client.data.content;

import java.util.ArrayList;

import lostworlds.client.books.tyrannibook.client.data.TyrannobookData;
import lostworlds.client.books.tyrannibook.client.data.element.ImageData;
import lostworlds.client.books.tyrannibook.client.data.element.ImageElement;
import lostworlds.client.books.tyrannibook.client.data.element.TyrannobookElement;
import lostworlds.client.books.tyrannibook.client.screen.TyrannobookScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContentImage extends PageContent {
	public String title = null;
	public ImageData image;

	@Override
	public void build(TyrannobookData book, ArrayList<TyrannobookElement> list, boolean rightSide) {
		int y = TITLE_HEIGHT;

		if (this.title == null || this.title.isEmpty()) {
			y = 0;
		} else {
			this.addTitle(list, this.title);
		}

		if (this.image != null && this.image.location != null) {
			list.add(new ImageElement(0, y, TyrannobookScreen.PAGE_WIDTH, TyrannobookScreen.PAGE_HEIGHT - y, this.image));
		} else {
			list.add(new ImageElement(ImageData.MISSING));
		}
	}
}