package lostworlds.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableSet;

import lostworlds.client.ClientConfigs;
import lostworlds.client.LostWorldsConfig;
import lostworlds.server.tab.ModTab;
import lostworlds.server.util.Version;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.raid.Raid;
import net.minecraftforge.fml.ModList;

//Random Constantly Called Things
public class LostWorldsUtils {
	public static final Logger LOGGER = LogManager.getLogger(LostWorldsUtils.ID);

	public static final String ID = "lostworlds";
	public static final String NAME = "Lost Worlds";
	public static final String VERSION = "a11_dev";
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

	public static final LostWorldsCommonConfig SERVER_CONFIG = LostWorldsConfig.SERVER_CONFIG;
	public static final ClientConfigs CLIENT_CONFIG = LostWorldsConfig.CLIENT_CONFIG;

	public static final ModTab ITEMS = new ModTab("items");
	public static final ModTab BLOCKS = new ModTab("blocks");

	public static final HashSet<Biome.Category> SIMPLE_SPAWNABLE_BIOME_CATEGORIES = Stream.of(Biome.Category.FOREST, Biome.Category.JUNGLE, Biome.Category.DESERT, Biome.Category.PLAINS, Biome.Category.SAVANNA).collect(Collectors.toCollection(HashSet::new));
	public static final HashSet<Biome.Category> FOSSIL_BIOMES = Stream.of(Biome.Category.FOREST, Biome.Category.EXTREME_HILLS, Biome.Category.DESERT, Biome.Category.PLAINS, Biome.Category.SAVANNA, Biome.Category.MUSHROOM, Biome.Category.SWAMP).collect(Collectors.toCollection(HashSet::new));

	public static Set<Block> carverBlocks() {
		// LostWorldsBlocks.PERMIAN_SAND, LostWorldsBlocks.PERMIAN_STONE,
		// LostWorldsBlocks.PERMIAN_COBBLESTONE, LostWorldsBlocks.JURASSIC_STONE,
		// LostWorldsBlocks.JURASSIC_COBBLESTONE, LostWorldsBlocks.LATERLITE,
		// LostWorldsBlocks.RAW_MARBLE, LostWorldsBlocks.LIMESTONE,
		// LostWorldsBlocks.MOSSY_SOIL, LostWorldsBlocks.DRIED_SOIL,
		// LostWorldsBlocks.CRACKED_SOIL, LostWorldsBlocks.VOLCANIC_ASH,
		// LostWorldsBlocks.VOLCANIC_ASH_LAYER, Blocks.BASALT, Blocks.BLACKSTONE,
		// Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.MAGMA_BLOCK,
		// Blocks.STONE, Blocks.SNOW_BLOCK);
		return ImmutableSet.of(Blocks.STONE);
	}

	public static void translateToWaves(EntityType<? extends AbstractRaiderEntity> type, List<? extends Integer> list) {
		Raid.WaveMember.create(type.getRegistryName().toString(), type, new int[] { list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7) });
	}
}
