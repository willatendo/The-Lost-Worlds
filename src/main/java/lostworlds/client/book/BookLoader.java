package lostworlds.client.book;

import java.lang.reflect.Type;
import java.util.HashMap;

import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lostworlds.client.book.action.StringActionProcessor;
import lostworlds.client.book.action.protocol.ProtocolGoToPage;
import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.content.BlankContent;
import lostworlds.client.book.data.content.BlockInteractionContent;
import lostworlds.client.book.data.content.CraftingContent;
import lostworlds.client.book.data.content.ImageContent;
import lostworlds.client.book.data.content.ImageTextContent;
import lostworlds.client.book.data.content.IndexContent;
import lostworlds.client.book.data.content.PaddingContent;
import lostworlds.client.book.data.content.PageContent;
import lostworlds.client.book.data.content.ShowcaseContent;
import lostworlds.client.book.data.content.SmeltingContent;
import lostworlds.client.book.data.content.SmithingContent;
import lostworlds.client.book.data.content.StructureContent;
import lostworlds.client.book.data.content.TextContent;
import lostworlds.client.book.data.content.TextImageContent;
import lostworlds.client.book.data.content.TextLeftImageContent;
import lostworlds.client.book.data.content.TextRightImageContent;
import lostworlds.client.book.data.deserializer.ConditionDeserializer;
import lostworlds.client.book.data.deserializer.HexStringDeserializer;
import lostworlds.client.book.data.element.IngredientData;
import lostworlds.client.book.repository.BookRepository;
import lostworlds.client.book.transformer.IndexTransformer;
import lostworlds.client.book.transformer.BookTransformer;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.network.LostWorldsNetwork;
import lostworlds.server.network.book.UpdateHeldPagePacket;
import lostworlds.server.network.book.UpdateLecternPagePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.conditions.ICondition;

public class BookLoader implements ResourceManagerReloadListener {
	private static Gson gson;
	private static boolean gsonDirty = true;
	private static final HashMap<Type, Object> gsonTypeAdapters = new HashMap<>();

	private static final HashMap<ResourceLocation, Class<? extends PageContent>> typeToContentMap = new HashMap<>();

	private static final HashMap<ResourceLocation, BookData> books = new HashMap<>();

	public BookLoader() {
		registerPageType(BlankContent.ID, BlankContent.class);
		registerPageType(TextContent.ID, TextContent.class);
		registerPageType(PaddingContent.LEFT_ID, PaddingContent.ContentLeftPadding.class);
		registerPageType(PaddingContent.RIGHT_ID, PaddingContent.ContentRightPadding.class);
		registerPageType(ImageContent.ID, ImageContent.class);
		registerPageType(ImageTextContent.ID, ImageTextContent.class);
		registerPageType(TextImageContent.ID, TextImageContent.class);
		registerPageType(TextLeftImageContent.ID, TextLeftImageContent.class);
		registerPageType(TextRightImageContent.ID, TextRightImageContent.class);
		registerPageType(CraftingContent.ID, CraftingContent.class);
		registerPageType(SmeltingContent.ID, SmeltingContent.class);
		registerPageType(SmithingContent.ID, SmithingContent.class);
		registerPageType(BlockInteractionContent.ID, BlockInteractionContent.class);
		registerPageType(StructureContent.ID, StructureContent.class);
		registerPageType(IndexContent.ID, IndexContent.class);
		registerPageType(ShowcaseContent.ID, ShowcaseContent.class);

		StringActionProcessor.registerProtocol(LostWorldsUtils.rL("go-to-page"), new ProtocolGoToPage(false));
		StringActionProcessor.registerProtocol(LostWorldsUtils.rL("go-to-page-rtn"), new ProtocolGoToPage(true));

		registerGsonTypeAdapter(ResourceLocation.class, ResourceLocationSerializer.resourceLocation(LostWorldsUtils.ID));
		registerGsonTypeAdapter(int.class, new HexStringDeserializer());
		registerGsonTypeAdapter(ICondition.class, new ConditionDeserializer());
		registerGsonTypeAdapter(IngredientData.class, new IngredientData.Deserializer());

		IndexTransformer.addHiddenPageType(BlankContent.ID);
		IndexTransformer.addHiddenPageType(PaddingContent.LEFT_ID);
		IndexTransformer.addHiddenPageType(PaddingContent.RIGHT_ID);
		IndexTransformer.addHiddenPageType(IndexContent.ID);
	}

	public static void registerPageType(ResourceLocation id, Class<? extends PageContent> clazz) {
		if (typeToContentMap.containsKey(id)) {
			throw new IllegalArgumentException("Page type " + id + " already in use.");
		}

		typeToContentMap.put(id, clazz);
	}

	@Nullable
	public static Class<? extends PageContent> getPageType(ResourceLocation name) {
		return typeToContentMap.get(name);
	}

	public static BookData registerBook(ResourceLocation id, BookRepository... repositories) {
		return registerBook(id, true, true, repositories);
	}

	public static BookData registerBook(ResourceLocation id, boolean appendIndex, boolean appendContentTable, BookRepository... repositories) {
		BookData info = new BookData(repositories);

		if (appendIndex) {
			info.addTransformer(BookTransformer.indexTranformer());
		}
		if (appendContentTable) {
			info.addTransformer(BookTransformer.contentTableTransformer());
		}

		books.put(id, info);
		return info;
	}

	@Nullable
	public static BookData getBook(ResourceLocation id) {
		return books.getOrDefault(id, null);
	}

	public static void updateSavedPage(@Nullable Player player, InteractionHand hand, String page) {
		if (player != null) {
			ItemStack item = player.getItemInHand(hand);
			if (!item.isEmpty()) {
				BookHelper.writeSavedPageToBook(item, page);
				LostWorldsNetwork.INSTANCE.network.sendToServer(new UpdateHeldPagePacket(hand, page));
			}
		}
	}

	public static void updateSavedPage(BlockPos pos, String page) {
		LostWorldsNetwork.INSTANCE.network.sendToServer(new UpdateLecternPagePacket(pos, page));
	}

	public static Gson getGson() {
		if (gson == null || gsonDirty) {
			GsonBuilder builder = new GsonBuilder();

			for (Type type : gsonTypeAdapters.keySet()) {
				builder.registerTypeAdapter(type, gsonTypeAdapters.get(type));
			}

			gson = builder.create();
			gsonDirty = false;
		}

		return gson;
	}

	public static void registerGsonTypeAdapter(Type type, Object adapter) {
		gsonTypeAdapters.put(type, adapter);
		gsonDirty = true;
	}

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		books.forEach((s, bookData) -> bookData.reset());
	}
}
