package lostworlds.repack.tyrannotitanlib.tyrannibook.client;

import java.util.HashMap;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lostworlds.repack.tyrannotitanlib.tyrannibook.client.action.StringActionProcessor;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.action.protocol.ProtocolGoToPage;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.TyrannobookData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentBlank;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentBlockInteraction;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentCrafting;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentImage;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentImageText;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentSmelting;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentSmithing;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentStructure;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentText;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentTextImage;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentTextLeftImage;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentTextRightImage;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.PageContent;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.deserialiser.HexStringDeserialiser;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.repository.TyrannobookRepository;
import lostworlds.repack.tyrannotitanlib.tyranninetwork.Tyranninetwork;
import lostworlds.repack.tyrannotitanlib.tyranninetwork.packets.UpdateHeldPagePacket;
import lostworlds.repack.tyrannotitanlib.tyranninetwork.packets.UpdateLecturnPagePacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;

@OnlyIn(Dist.CLIENT)
public class TyrannobookLoader implements ISelectiveResourceReloadListener {
	public static final Gson GSON = new GsonBuilder().registerTypeAdapter(int.class, new HexStringDeserialiser()).create();

	private static final HashMap<String, Class<? extends PageContent>> typeToContentMap = new HashMap<>();

	private static final HashMap<String, TyrannobookData> books = new HashMap<>();

	public TyrannobookLoader() {
		registerPageType("blank", ContentBlank.class);
		registerPageType("text", ContentText.class);
		registerPageType("image", ContentImage.class);
		registerPageType("image with text below", ContentImageText.class);
		registerPageType("text with image below", ContentTextImage.class);
		registerPageType("text with left image etch", ContentTextLeftImage.class);
		registerPageType("text with right image etch", ContentTextRightImage.class);
		registerPageType("crafting", ContentCrafting.class);
		registerPageType("smelting", ContentSmelting.class);
		registerPageType("smithing", ContentSmithing.class);
		registerPageType("block interaction", ContentBlockInteraction.class);
		registerPageType(ContentStructure.ID, ContentStructure.class);

		StringActionProcessor.registerProtocol(new ProtocolGoToPage());
		StringActionProcessor.registerProtocol(new ProtocolGoToPage(true, ProtocolGoToPage.GO_TO_RTN));
	}

	public static void registerPageType(String name, Class<? extends PageContent> clazz) {
		if (typeToContentMap.containsKey(name)) {
			throw new IllegalArgumentException("Page type " + name + " already in use.");
		}

		typeToContentMap.put(name, clazz);
	}

	@Nullable
	public static Class<? extends PageContent> getPageType(String name) {
		return typeToContentMap.get(name);
	}

	public static TyrannobookData registerBook(String name, TyrannobookRepository repositories) {
		return registerBook(name, true, true, repositories);
	}

	public static TyrannobookData registerBook(String name, boolean appendIndex, boolean appendContentTable, TyrannobookRepository... repositories) {
		TyrannobookData info = new TyrannobookData(repositories);

		books.put(name.contains(":") ? name : ModLoadingContext.get().getActiveContainer().getNamespace() + ":" + name, info);

		if (appendIndex) {
			info.addTransformer(TyrannobookTransformer.indexTranformer());
		}
		if (appendContentTable) {
			info.addTransformer(TyrannobookTransformer.contentTableTransformer());
		}

		return info;
	}

	public static void updateSavedPage(@Nullable PlayerEntity player, Hand hand, String page) {
		if (player != null) {
			ItemStack item = player.getItemInHand(hand);
			if (!item.isEmpty()) {
				TyrannobookHelper.writeSavedPageToBook(item, page);
				Tyranninetwork.INSTANCE.network.sendToServer(new UpdateHeldPagePacket(hand, page));
			}
		}
	}

	public static void updateSavedPage(BlockPos pos, String page) {
		Tyranninetwork.INSTANCE.network.sendToServer(new UpdateLecturnPagePacket(pos, page));
	}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
		books.forEach((s, bookData) -> bookData.reset());
	}
}
