package lostworlds.client;

import lostworlds.client.book.BookLoader;
import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.content.ArchaeologyContent;
import lostworlds.client.book.data.content.ContentImageText2;
import lostworlds.client.book.data.content.PaddingContent.PaddingBookTransformer;
import lostworlds.client.book.repository.FileRepository;
import lostworlds.client.book.transformer.BookTransformer;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.resources.ResourceLocation;

public class LostWorldsBooks extends BookData {
	private static final ResourceLocation LOST_WORLDS_LEXICON_ID = LostWorldsUtils.rL("lost_worlds_lexicon");
	public static final BookData LOST_WORLDS_LEXICON = BookLoader.registerBook(LOST_WORLDS_LEXICON_ID, false, false);
	private static final ResourceLocation FIELD_GUIDE_ID = LostWorldsUtils.rL("field_guide");
	public static final BookData FIELD_GUIDE = BookLoader.registerBook(FIELD_GUIDE_ID, false, false);

	public static void initBooks() {
		BookLoader.registerPageType(ArchaeologyContent.ID, ArchaeologyContent.class);
		BookLoader.registerPageType(ContentImageText2.ID, ContentImageText2.class);

		addStandardData(LOST_WORLDS_LEXICON, LOST_WORLDS_LEXICON_ID);
		addStandardData(FIELD_GUIDE, FIELD_GUIDE_ID);
	}

	private static void addStandardData(BookData bookData, ResourceLocation id) {
		bookData.addRepository(new FileRepository(LostWorldsUtils.rL("book/" + id.getPath())));
		bookData.addTransformer(BookTransformer.indexTranformer());
		bookData.addTransformer(PaddingBookTransformer.INSTANCE);
	}
}
