package lostworlds.server;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lostworlds.client.LostWorldsClientConfigs;
import lostworlds.client.LostWorldsConfig;
import lostworlds.server.tab.ModTab;
import lostworlds.server.util.Version;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.entity.raid.Raid;
import net.minecraftforge.fml.ModList;

//Random Constantly Called Things
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

	public static TranslatableComponent tTC(String type, String key) {
		return new TranslatableComponent(type + "." + ID + "." + key);
	}

	public static TranslatableComponent tTCA(String type, String key, String args) {
		return new TranslatableComponent(type + "." + ID + "." + key, args);
	}

	public static TranslatableComponent cTC(String type, String key, ChatFormatting... format) {
		TranslatableComponent text = tTC(type, key);
		text.withStyle(format);
		return text;
	}

	public static TranslatableComponent cTCA(String type, String key, String args, ChatFormatting... format) {
		TranslatableComponent text = tTCA(type, key, args);
		text.withStyle(format);
		return text;
	}

	public static TranslatableComponent gTC(String type, String key) {
		TranslatableComponent text = tTC(type, key);
		text.withStyle(ChatFormatting.GRAY);
		return text;
	}

	public static final LostWorldsCommonConfig SERVER_CONFIG = LostWorldsConfig.COMMON_CONFIG;
	public static final LostWorldsClientConfigs CLIENT_CONFIG = LostWorldsConfig.CLIENT_CONFIG;

	public static final ModTab ITEMS = new ModTab("items");
	public static final ModTab BLOCKS = new ModTab("blocks");

	public static final HashSet<Biome.BiomeCategory> SIMPLE_SPAWNABLE_BIOME_CATEGORIES = Stream.of(Biome.BiomeCategory.FOREST, Biome.BiomeCategory.JUNGLE, Biome.BiomeCategory.DESERT, Biome.BiomeCategory.PLAINS, Biome.BiomeCategory.SAVANNA).collect(Collectors.toCollection(HashSet::new));
	public static final HashSet<Biome.BiomeCategory> FOSSIL_BIOMES = Stream.of(Biome.BiomeCategory.FOREST, Biome.BiomeCategory.EXTREME_HILLS, Biome.BiomeCategory.DESERT, Biome.BiomeCategory.PLAINS, Biome.BiomeCategory.SAVANNA, Biome.BiomeCategory.MUSHROOM, Biome.BiomeCategory.SWAMP).collect(Collectors.toCollection(HashSet::new));

	public static void translateToWaves(EntityType<? extends Raider> type, List<? extends Integer> list) {
		Raid.RaiderType.create(type.getRegistryName().toString(), type, new int[] { list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7) });
	}
}
