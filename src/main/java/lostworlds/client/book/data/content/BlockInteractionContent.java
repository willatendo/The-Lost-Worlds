package lostworlds.client.book.data.content;

import java.util.ArrayList;

import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.element.ImageData;
import lostworlds.client.book.data.element.IngredientData;
import lostworlds.client.book.data.element.TextData;
import lostworlds.client.book.screen.book.BookScreen;
import lostworlds.client.book.screen.book.BookTextures;
import lostworlds.client.book.screen.book.element.BookElement;
import lostworlds.client.book.screen.book.element.ImageElement;
import lostworlds.client.book.screen.book.element.ItemElement;
import lostworlds.client.book.screen.book.element.TextElement;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.resources.ResourceLocation;

public class BlockInteractionContent extends PageContent {
	public static final ResourceLocation ID = LostWorldsUtils.rL("block_interaction");

	public static final transient int TEX_SIZE = 512;
	public static final transient ImageData IMG_SMITHING = new ImageData(BookTextures.BOOK_TEXTURE, 0, 0, 88, 55, TEX_SIZE, TEX_SIZE);

	public static final transient int INPUT_X = 6;
	public static final transient int INPUT_Y = 18;
	public static final transient int BLOCK_X = 40;
	public static final transient int BLOCK_Y = 26;

	public static final transient float ITEM_SCALE = 2.0F;
	public static final transient float BLOCK_SCALE = 5.0F;

	public String title = "Block Interaction";
	public IngredientData input;
	public IngredientData block;
	public TextData[] description;

	@Override
	public void build(BookData book, ArrayList<BookElement> list, boolean rightSide) {
		int x = BookScreen.PAGE_WIDTH / 2 - IMG_SMITHING.width / 2 - 10;
		int y = getTitleHeight();

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
			list.add(new TextElement(0, IMG_SMITHING.height + y + 50, BookScreen.PAGE_WIDTH, BookScreen.PAGE_HEIGHT - IMG_SMITHING.height - y - 50, this.description));
		}
	}
}
