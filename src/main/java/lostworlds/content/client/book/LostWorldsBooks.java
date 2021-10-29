package lostworlds.content.client.book;

import lostworlds.content.ModUtils;
import lostworlds.content.client.book.content.ContentArchaeology;
import lostworlds.content.client.book.content.ContentImageText2;
import net.minecraft.util.ResourceLocation;
import tyrannotitanlib.library.tyrannobook.client.FileRepository;
import tyrannotitanlib.library.tyrannobook.client.PaddingBookTransformer;
import tyrannotitanlib.library.tyrannobook.client.TyrannobookLoader;
import tyrannotitanlib.library.tyrannobook.client.TyrannobookTransformer;
import tyrannotitanlib.library.tyrannobook.client.data.TyrannobookData;

public class LostWorldsBooks extends TyrannobookData
{
	private static final ResourceLocation LOST_WORLDS_LEXICON_ID = ModUtils.rL("lost_worlds_lexicon");
	public static final TyrannobookData LOST_WORLDS_LEXICON = TyrannobookLoader.registerBook(LOST_WORLDS_LEXICON_ID.toString(), false, false);
	
	public static void initBooks() 
	{
		TyrannobookLoader.registerPageType("archaeology", ContentArchaeology.class);
		TyrannobookLoader.registerPageType(ContentImageText2.ID, ContentImageText2.class);
		
		addStandardData(LOST_WORLDS_LEXICON, LOST_WORLDS_LEXICON_ID);
	}
	
	private static void addStandardData(TyrannobookData book, ResourceLocation id) 
	{
		book.addRepository(new FileRepository(id.getNamespace() + ":tyrannobook/" + id.getPath()));
		book.addTransformer(TyrannobookTransformer.indexTranformer());
		book.addTransformer(PaddingBookTransformer.INSTANCE);
	}
}
