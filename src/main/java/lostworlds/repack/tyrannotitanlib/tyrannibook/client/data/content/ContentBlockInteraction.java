package lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content;

import java.util.ArrayList;

import lostworlds.repack.tyrannotitanlib.tyrannibook.client.TyrannibookTextures;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.TyrannobookData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.ImageData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.ImageElement;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.ItemElement;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.ItemStackData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.TextData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.TextElement;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.TyrannobookElement;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.screen.TyrannobookScreen;

public class ContentBlockInteraction extends PageContent {
	public static final transient int TEX_SIZE = 512;
	public static final transient ImageData IMG_SMITHING = new ImageData(TyrannibookTextures.TEX_MISC, 0, 0, 88, 55, TEX_SIZE, TEX_SIZE);

	public static final transient int INPUT_X = 6;
	public static final transient int INPUT_Y = 18;
	public static final transient int BLOCK_X = 40;
	public static final transient int BLOCK_Y = 26;

	public static final transient float ITEM_SCALE = 2.0F;
	public static final transient float BLOCK_SCALE = 5.0F;

	public String title = "Block Interaction";
	public ItemStackData input;
	public ItemStackData block;
	public TextData[] description;

	@Override
	public void build(TyrannobookData book, ArrayList<TyrannobookElement> list, boolean rightSide) {
		int x = TyrannobookScreen.PAGE_WIDTH / 2 - IMG_SMITHING.width / 2 - 10;
		int y = TITLE_HEIGHT;

		if (this.title == null || this.title.isEmpty()) {
			y = 0;
		} else {
			this.addTitle(list, this.title);
		}

		list.add(new ImageElement(x, y, IMG_SMITHING.width, IMG_SMITHING.height, IMG_SMITHING, book.appearance.slotColor));

		if (this.input != null && !this.input.getItems().isEmpty()) {
			list.add(new ItemElement(x + INPUT_X, y + INPUT_Y, ITEM_SCALE, this.input.getItems(), this.input.action));
		}

		if (this.block != null && !this.block.getItems().isEmpty()) {
			list.add(new ItemElement(x + BLOCK_X, y + BLOCK_Y, BLOCK_SCALE, this.block.getItems(), this.block.action));
		}

		if (this.description != null && this.description.length > 0) {
			list.add(new TextElement(0, IMG_SMITHING.height + y + 50, TyrannobookScreen.PAGE_WIDTH, TyrannobookScreen.PAGE_HEIGHT - IMG_SMITHING.height - y - 50, this.description));
		}
	}
}
