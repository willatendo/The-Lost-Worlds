package lostworlds.client.books.tyrannibook.client.data.content;

import java.util.ArrayList;

import lostworlds.client.books.tyrannibook.client.TyrannibookTextures;
import lostworlds.client.books.tyrannibook.client.data.TyrannobookData;
import lostworlds.client.books.tyrannibook.client.data.element.ImageData;
import lostworlds.client.books.tyrannibook.client.data.element.ImageElement;
import lostworlds.client.books.tyrannibook.client.data.element.ItemElement;
import lostworlds.client.books.tyrannibook.client.data.element.ItemStackData;
import lostworlds.client.books.tyrannibook.client.data.element.TextData;
import lostworlds.client.books.tyrannibook.client.data.element.TextElement;
import lostworlds.client.books.tyrannibook.client.data.element.TyrannobookElement;
import lostworlds.client.books.tyrannibook.client.screen.TyrannobookScreen;

public class ContentSmithing extends PageContent {
	public static final transient int TEX_SIZE = 512;
	public static final transient ImageData IMG_SMITHING = new ImageData(TyrannibookTextures.TEX_MISC, 88, 0, 210, 42, TEX_SIZE, TEX_SIZE);

	public static final transient int INPUT_X = 5;
	public static final transient int INPUT_Y = 5;
	public static final transient int MODIFIER_X = 89;
	public static final transient int MODIFIER_Y = 5;
	public static final transient int RESULT_X = 173;
	public static final transient int RESULT_Y = 5;

	public static final transient float ITEM_SCALE = 2.0F;

	public String title = "Smithing";
	public ItemStackData input;
	public ItemStackData modifier;
	public ItemStackData result;
	public TextData[] description;

	@Override
	public void build(TyrannobookData book, ArrayList<TyrannobookElement> list, boolean rightSide) {
		int x = TyrannobookScreen.PAGE_WIDTH / 2 - IMG_SMITHING.width / 2;
		int y = TITLE_HEIGHT;

		if (this.title == null || this.title.isEmpty()) {
			y = 0;
		} else {
			this.addTitle(list, this.title);
		}

		list.add(new ImageElement(x, y, IMG_SMITHING.width, IMG_SMITHING.height, IMG_SMITHING, book.appearance.slotColor));

		if (this.input != null && !this.input.id.equals("")) {
			list.add(new ItemElement(x + INPUT_X, y + INPUT_Y, ITEM_SCALE, this.input.getItems(), this.input.action));
		}

		if (this.modifier != null && !this.modifier.id.equals("")) {
			list.add(new ItemElement(x + MODIFIER_X, y + MODIFIER_Y, ITEM_SCALE, this.modifier.getItems(), this.modifier.action));
		}

		if (this.result != null && !this.result.id.equals("")) {
			list.add(new ItemElement(x + RESULT_X, y + RESULT_Y, ITEM_SCALE, this.result.getItems(), this.result.action));
		}

		if (this.description != null && this.description.length > 0) {
			list.add(new TextElement(0, IMG_SMITHING.height + y + 5, TyrannobookScreen.PAGE_WIDTH, TyrannobookScreen.PAGE_HEIGHT - IMG_SMITHING.height - y - 5, this.description));
		}
	}
}
