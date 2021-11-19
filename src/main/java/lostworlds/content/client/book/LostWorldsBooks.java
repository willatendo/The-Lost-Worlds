package lostworlds.content.client.book;

import lostworlds.content.ModUtils;
import lostworlds.content.client.book.content.ContentArchaeology;
import lostworlds.content.client.book.content.ContentImageText2;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannobook.client.FileRepository;
import tyrannotitanlib.library.tyrannobook.client.PaddingBookTransformer;
import tyrannotitanlib.library.tyrannobook.client.TyrannobookLoader;
import tyrannotitanlib.library.tyrannobook.client.TyrannobookTransformer;
import tyrannotitanlib.library.tyrannobook.client.data.TyrannobookData;

@OnlyIn(Dist.CLIENT)
public class LostWorldsBooks extends TyrannobookData
{
	private static final ResourceLocation LOST_WORLDS_LEXICON_ID = ModUtils.rL("lost_worlds_lexicon");
	public static final TyrannobookData LOST_WORLDS_LEXICON = TyrannobookLoader.registerBook(LOST_WORLDS_LEXICON_ID.toString(), false, false);
	private static final ResourceLocation FIELD_GUIDE_ID = ModUtils.rL("field_guide");
	public static final TyrannobookData FIELD_GUIDE = TyrannobookLoader.registerBook(FIELD_GUIDE_ID.toString(), false, false);
	
	public static void initBooks() 
	{
		TyrannobookLoader.registerPageType(ContentArchaeology.ID, ContentArchaeology.class);
		TyrannobookLoader.registerPageType(ContentImageText2.ID, ContentImageText2.class);
				
		addStandardData(LOST_WORLDS_LEXICON, LOST_WORLDS_LEXICON_ID);
		addStandardData(FIELD_GUIDE, FIELD_GUIDE_ID);
	}
	
	private static void addStandardData(TyrannobookData book, ResourceLocation id) 
	{
		book.addRepository(new FileRepository(id.getNamespace() + ":tyrannobook/" + id.getPath()));
		book.addTransformer(TyrannobookTransformer.indexTranformer());
		book.addTransformer(PaddingBookTransformer.INSTANCE);
	}
}
