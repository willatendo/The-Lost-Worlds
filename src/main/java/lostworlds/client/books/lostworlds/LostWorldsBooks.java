package lostworlds.client.books.lostworlds;

import lostworlds.client.books.lostworlds.content.ContentArchaeology;
import lostworlds.client.books.lostworlds.content.ContentImageText2;
import lostworlds.client.books.tyrannibook.client.FileRepository;
import lostworlds.client.books.tyrannibook.client.PaddingBookTransformer;
import lostworlds.client.books.tyrannibook.client.TyrannobookLoader;
import lostworlds.client.books.tyrannibook.client.TyrannobookTransformer;
import lostworlds.client.books.tyrannibook.client.data.TyrannobookData;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LostWorldsBooks extends TyrannobookData {
	private static final ResourceLocation LOST_WORLDS_LEXICON_ID = LostWorldsUtils.rL("lost_worlds_lexicon");
	public static final TyrannobookData LOST_WORLDS_LEXICON = TyrannobookLoader.registerBook(LOST_WORLDS_LEXICON_ID.toString(), false, false);
	private static final ResourceLocation FIELD_GUIDE_ID = LostWorldsUtils.rL("field_guide");
	public static final TyrannobookData FIELD_GUIDE = TyrannobookLoader.registerBook(FIELD_GUIDE_ID.toString(), false, false);

	public static void initBooks() {
		TyrannobookLoader.registerPageType(ContentArchaeology.ID, ContentArchaeology.class);
		TyrannobookLoader.registerPageType(ContentImageText2.ID, ContentImageText2.class);

		addStandardData(LOST_WORLDS_LEXICON, LOST_WORLDS_LEXICON_ID);
		addStandardData(FIELD_GUIDE, FIELD_GUIDE_ID);
	}

	private static void addStandardData(TyrannobookData book, ResourceLocation id) {
		book.addRepository(new FileRepository(id.getNamespace() + ":tyrannobook/" + id.getPath()));
		book.addTransformer(TyrannobookTransformer.indexTranformer());
		book.addTransformer(PaddingBookTransformer.INSTANCE);
	}
}
