package lostworlds.client.book.data.content;

import java.util.ArrayList;

import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.element.ImageData;
import lostworlds.client.book.screen.book.BookScreen;
import lostworlds.client.book.screen.book.element.BookElement;
import lostworlds.client.book.screen.book.element.ImageElement;
import lostworlds.client.book.screen.book.element.TextElement;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContentImageText2 extends ImageTextContent {
	public static final transient ResourceLocation ID = LostWorldsUtils.rL("image_text_2");

	@Override
	public void build(BookData book, ArrayList<BookElement> list, boolean rightSide) {
		int y = TITLE_HEIGHT;

		if (this.title == null || this.title.isEmpty()) {
			y = 0;
		} else {
			this.addTitle(list, this.title);
		}

		if (this.image != null && this.image.location != null) {
			int x = (BookScreen.PAGE_HEIGHT - this.image.width) / 2;
			list.add(new ImageElement(x, y, -1, -1, this.image));
			y += this.image.height;
		} else {
			list.add(new ImageElement(0, y, 32, 32, ImageData.MISSING));
		}

		if (this.text != null && this.text.length > 0) {
			y += 5;
			list.add(new TextElement(0, y, BookScreen.PAGE_WIDTH, BookScreen.PAGE_HEIGHT - y, this.text));
		}
	}
}
