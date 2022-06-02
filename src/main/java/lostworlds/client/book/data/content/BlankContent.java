package lostworlds.client.book.data.content;

import java.util.ArrayList;

import lostworlds.client.book.data.BookData;
import lostworlds.client.book.screen.book.element.BookElement;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.resources.ResourceLocation;

public class BlankContent extends PageContent {
	public static final ResourceLocation ID = LostWorldsUtils.rL("blank");

	@Override
	public void build(BookData book, ArrayList<BookElement> list, boolean rightSide) {
	}
}
