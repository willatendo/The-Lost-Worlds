package lostworlds.server;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lostworlds.client.LostWorldsClientConfigs;
import lostworlds.client.LostWorldsConfig;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.item.LostWorldsEnchantments;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.util.Version;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.fml.ModList;

public class LostWorldsUtils {
	public static final Logger LOGGER = LogManager.getLogger(LostWorldsUtils.ID);

	public static final String ID = "lostworlds";
	public static final String NAME = "Lost Worlds";
	public static final String VERSION = "a11.1_dev";
	public static final Version VERSION_PARSER = Version.getVersion(VERSION);

	public static boolean modLoaded(String id) {
		return ModList.get().isLoaded(id);
	}

	public static ResourceLocation rL(String location) {
		return new ResourceLocation(ID, location);
	}

	public static Component tTC(String type, String key) {
		return Component.translatable(type + "." + ID + "." + key);
	}

	public static Component tTCA(String type, String key, String args) {
		return Component.translatable(type + "." + ID + "." + key, args);
	}

	public static Component cTC(String type, String key, ChatFormatting... format) {
		return Component.translatable(type + "." + ID + "." + key).withStyle(format);
	}

	public static Component cTCA(String type, String key, String args, ChatFormatting... format) {
		return Component.translatable(type + "." + ID + "." + key, args).withStyle(format);
	}

	public static Component gTCA(String type, String key, String args) {
		return cTCA(type, key, args, ChatFormatting.GRAY);
	}

	public static Component gTC(String type, String key) {
		return cTC(type, key, ChatFormatting.GRAY);
	}

	public static final LostWorldsCommonConfig SERVER_CONFIG = LostWorldsConfig.COMMON_CONFIG;
	public static final LostWorldsClientConfigs CLIENT_CONFIG = LostWorldsConfig.CLIENT_CONFIG;

	public static final CreativeModeTab ITEMS = (new CreativeModeTab("lostworlds.items") {
		@Override
		public ItemStack makeIcon() {
			return LostWorldsItems.LOST_WORLDS_LEXICON.asStack();
		}

		@Override
		public void fillItemList(NonNullList<ItemStack> itemStacks) {
			super.fillItemList(itemStacks);
			itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(LostWorldsEnchantments.PRECISION.get(), 1)));
			itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(LostWorldsEnchantments.PRECISION.get(), 2)));
			itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(LostWorldsEnchantments.PRECISION.get(), 3)));
			itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(LostWorldsEnchantments.CURSE_OF_BREAKING.get(), 1)));
		}
	}).setRecipeFolderName("lostworlds_items");
	public static final CreativeModeTab BLOCKS = (new CreativeModeTab("lostworlds.blocks") {
		@Override
		public ItemStack makeIcon() {
			return LostWorldsBlocks.PLASTERED_FOSSILIZED_TRACK.asStack();
		}
	}).setRecipeFolderName("lostworlds_blocks");

	public static void translateToWaves(String name, EntityType<? extends Raider> type, List<? extends Integer> list) {
		Raid.RaiderType.create(name, type, new int[] { list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7) });
	}
}
