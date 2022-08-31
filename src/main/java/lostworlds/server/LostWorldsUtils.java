package lostworlds.server;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lostworlds.client.LostWorldsClientConfigs;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.item.LostWorldsEnchantments;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.util.Version;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.raid.Raid;
import net.minecraftforge.fml.ModList;

public class LostWorldsUtils {
	public static final Logger LOGGER = LogManager.getLogger(LostWorldsUtils.ID);

	public static final String ID = "lostworlds";
	public static final String NAME = "Lost Worlds";
	public static final String VERSION = "a11";
	public static final Version VERSION_PARSER = Version.getVersion(VERSION);

	public static boolean modLoaded(String id) {
		return ModList.get().isLoaded(id);
	}

	public static ResourceLocation rL(String location) {
		return new ResourceLocation(ID, location);
	}

	public static TranslationTextComponent tTC(String type, String key) {
		return new TranslationTextComponent(type + "." + ID + "." + key);
	}

	public static TranslationTextComponent tTCA(String type, String key, String args) {
		return new TranslationTextComponent(type + "." + ID + "." + key, args);
	}

	public static TranslationTextComponent cTC(String type, String key, TextFormatting... format) {
		TranslationTextComponent text = tTC(type, key);
		text.withStyle(format);
		return text;
	}

	public static TranslationTextComponent cTCA(String type, String key, String args, TextFormatting... format) {
		TranslationTextComponent text = tTCA(type, key, args);
		text.withStyle(format);
		return text;
	}

	public static TranslationTextComponent gTC(String type, String key) {
		TranslationTextComponent text = tTC(type, key);
		text.withStyle(TextFormatting.GRAY);
		return text;
	}

	public static final LostWorldsCommonConfig SERVER_CONFIG = LostWorldsConfig.COMMON_CONFIG;
	public static final LostWorldsClientConfigs CLIENT_CONFIG = LostWorldsConfig.CLIENT_CONFIG;

	public static final ItemGroup ITEMS = new ItemGroup(LostWorldsUtils.ID + ".items") {
		@Override
		public ItemStack makeIcon() {
			return LostWorldsItems.LOST_WORLDS_LEXICON.asStack();
		}

		public void fillItemList(NonNullList<ItemStack> itemStacks) {
			super.fillItemList(itemStacks);
			itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentData(LostWorldsEnchantments.PRECISION.get(), 1)));
			itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentData(LostWorldsEnchantments.PRECISION.get(), 2)));
			itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentData(LostWorldsEnchantments.PRECISION.get(), 3)));
			itemStacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentData(LostWorldsEnchantments.CURSE_OF_BREAKING.get(), 1)));
		}
	};
	public static final ItemGroup BLOCKS = new ItemGroup(LostWorldsUtils.ID + ".blocks") {
		@Override
		public ItemStack makeIcon() {
			return LostWorldsBlocks.PLASTERED_FOSSILIZED_TRACK.asStack();
		}
	};

	public static final HashSet<Biome.Category> SIMPLE_SPAWNABLE_BIOME_CATEGORIES = Stream.of(Biome.Category.FOREST, Biome.Category.JUNGLE, Biome.Category.DESERT, Biome.Category.PLAINS, Biome.Category.SAVANNA).collect(Collectors.toCollection(HashSet::new));
	public static final HashSet<Biome.Category> FOSSIL_BIOMES = Stream.of(Biome.Category.FOREST, Biome.Category.EXTREME_HILLS, Biome.Category.DESERT, Biome.Category.PLAINS, Biome.Category.SAVANNA, Biome.Category.MUSHROOM, Biome.Category.SWAMP).collect(Collectors.toCollection(HashSet::new));

	public static void translateToWaves(EntityType<? extends AbstractRaiderEntity> type, List<? extends Integer> list) {
		Raid.WaveMember.create(type.getRegistryName().toString(), type, new int[] { list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7) });
	}
}
