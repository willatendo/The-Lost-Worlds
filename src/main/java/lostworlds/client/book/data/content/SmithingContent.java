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

public class SmithingContent extends PageContent {
	public static final ResourceLocation ID = LostWorldsUtils.rL("smithing");

	public static final transient int TEX_SIZE = 512;
	public static final transient ImageData IMG_SMITHING = new ImageData(BookTextures.MISC_TEXTURE, 88, 0, 210, 42, TEX_SIZE, TEX_SIZE);

	public static final transient int INPUT_X = 5;
	public static final transient int INPUT_Y = 5;
	public static final transient int MODIFIER_X = 89;
	public static final transient int MODIFIER_Y = 5;
	public static final transient int RESULT_X = 173;
	public static final transient int RESULT_Y = 5;

	public static final transient float ITEM_SCALE = 2.0F;

	public String title = "Smithing";
	public IngredientData input;
	public IngredientData modifier;
	public IngredientData result;
	public TextData[] description;

	@Override
	public void build(BookData book, ArrayList<BookElement> list, boolean rightSide) {
		int x = BookScreen.PAGE_WIDTH / 2 - IMG_SMITHING.width / 2;
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

		if (this.modifier != null && !this.modifier.getItems().isEmpty()) {
			list.add(new ItemElement(x + MODIFIER_X, y + MODIFIER_Y, ITEM_SCALE, this.modifier.getItems(), this.modifier.action));
		}

		if (this.result != null && !this.result.getItems().isEmpty()) {
			list.add(new ItemElement(x + RESULT_X, y + RESULT_Y, ITEM_SCALE, this.result.getItems(), this.result.action));
		}

		if (this.description != null && this.description.length > 0) {
			list.add(new TextElement(0, IMG_SMITHING.height + y + 5, BookScreen.PAGE_WIDTH, BookScreen.PAGE_HEIGHT - IMG_SMITHING.height - y - 5, this.description));
		}
	}
}
