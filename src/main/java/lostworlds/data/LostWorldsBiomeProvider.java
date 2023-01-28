package lostworlds.data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lostworlds.data.custom.BiomeBuilder;
import lostworlds.data.custom.BiomeEffectsBuilder;
import lostworlds.data.custom.BiomeFeaturesBuilder;
import lostworlds.data.custom.BiomeSpawnersBuilder;
import lostworlds.data.custom.BiomeCavesBuilder;
import lostworlds.server.biome.features.placed.LostWorldsPlacedFeatures;
import lostworlds.server.biome.features.placed.PlacedOreFeatures;
import lostworlds.server.biome.features.placed.PlacedPlantPatchFeatures;
import lostworlds.server.biome.features.placed.PlacedWaterFeatures;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;

public class LostWorldsBiomeProvider implements DataProvider {
	protected static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	protected final DataGenerator dataGenerator;
	protected final String modid;

	public static final int BASE_WATER_COLOUR = 4159204;
	public static final int BASE_WATER_FOG_COLOUR = 329011;

	public static final int OCEAN_WATER_COLOUR = 4159204;
	public static final int OCEAN_WATER_FOG_COLOUR = 4159204;

	public static final int LUKE_WARM_OCEAN_WATER_COLOUR = 4566514;
	public static final int LUKE_WARM_OCEAN_WATER_FOG_COLOUR = 267827;

	public static final int WARM_OCEAN_WATER_COLOUR = 4445678;
	public static final int WARM_OCEAN_WATER_FOG_COLOUR = 270131;

	public static final int COLD_OCEAN_WATER_COLOUR = 4020182;
	public static final int COLD_OCEAN_WATER_FOG_COLOUR = 329011;

	public static final int BASE_FOG_COLOUR = 12638463;

	public LostWorldsBiomeProvider(DataGenerator dataGenerator, String modid) {
		this.dataGenerator = dataGenerator;
		this.modid = modid;
	}

	@Override
	public void run(HashCache hashCache) throws IOException {
		Path path = this.dataGenerator.getOutputFolder();
		this.makeAllBiomes((biomeBuilder) -> {
			this.saveToJson(hashCache, biomeBuilder.build(), path.resolve("data/" + this.modid + "/worldgen/biome/" + biomeBuilder.id() + ".json"));
		});

	}

	public void makeAllBiomes(Consumer<BiomeBuilder> consumer) {
		consumer.accept(new BiomeBuilder("permain_desert", 0.0F, 3.0F, Precipitation.NONE, Optional.empty(), BiomeCategory.DESERT, new BiomeEffectsBuilder(Optional.empty(), BASE_FOG_COLOUR, BASE_WATER_COLOUR, BASE_WATER_FOG_COLOUR, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 6000, 8, 2), Optional.empty(), Optional.empty(), Optional.empty()), new BiomeSpawnersBuilder(List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of()), Optional.of(new BiomeCavesBuilder(List.of(Carvers.CAVE, Carvers.CAVE_EXTRA_UNDERGROUND, Carvers.CANYON))), new BiomeFeaturesBuilder(List.of(), List.of(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND, MiscOverworldPlacements.LAKE_LAVA_SURFACE), List.of(CavePlacements.AMETHYST_GEODE, LostWorldsPlacedFeatures.PERMIAN_ROCK.getHolder().get()), List.of(), List.of(), List.of(), List.of(PlacedOreFeatures.PERMIAN_COAL_ORE_LOWER.getHolder().get(), PlacedOreFeatures.PERMIAN_COAL_ORE_UPPER.getHolder().get(), PlacedOreFeatures.PERMIAN_COPPER_ORE.getHolder().get(), PlacedOreFeatures.PERMIAN_COPPER_ORE_LARGE.getHolder().get(), PlacedOreFeatures.PERMIAN_DIAMOND_ORE.getHolder().get(), PlacedOreFeatures.PERMIAN_DIAMOND_ORE_BURIED.getHolder().get(), PlacedOreFeatures.PERMIAN_DIAMOND_ORE_LARGE.getHolder().get(), PlacedOreFeatures.PERMIAN_DIRT_ORE.getHolder().get(), PlacedOreFeatures.PERMIAN_EMERALD_ORE.getHolder().get(), PlacedOreFeatures.PERMIAN_GOLD_ORE.getHolder().get(), PlacedOreFeatures.PERMIAN_GOLD_ORE_LOWER.getHolder().get(), PlacedOreFeatures.PERMIAN_GRAVEL_ORE.getHolder().get(), PlacedOreFeatures.PERMIAN_IRON_ORE_MIDDLE.getHolder().get(), PlacedOreFeatures.PERMIAN_IRON_ORE_SMALL.getHolder().get(), PlacedOreFeatures.PERMIAN_IRON_ORE_UPPER.getHolder().get(), PlacedOreFeatures.PERMIAN_LAPIS_ORE.getHolder().get(), PlacedOreFeatures.PERMIAN_LAPIS_ORE_BURIED.getHolder().get(), PlacedOreFeatures.PERMIAN_LATERLITE_ORE.getHolder().get(), PlacedOreFeatures.PERMIAN_LIMESTONE_ORE.getHolder().get(), PlacedOreFeatures.PERMIAN_RAW_MARBLE_ORE.getHolder().get(), PlacedOreFeatures.PERMIAN_REDSTONE_ORE.getHolder().get(), PlacedOreFeatures.PERMIAN_REDSTONE_ORE_LOWER.getHolder().get()), List.of(), List.of(PlacedWaterFeatures.ANCIENT_SPRING.getHolder().get()), List.of(PlacedPlantPatchFeatures.PERMIAN_DESERT_SHRUB_PATCH.getHolder().get(), PlacedPlantPatchFeatures.PERMIAN_DESERT_FERNS_PATCH.getHolder().get(), VegetationPlacements.PATCH_LARGE_FERN, PlacedPlantPatchFeatures.FERN_PATCH.getHolder().get()), List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))));
	}

	@Override
	public String getName() {
		return "Biomes";
	}

	protected void saveToJson(HashCache cache, JsonObject json, Path path) {
		try {
			String s = GSON.toJson((JsonElement) json);
			String s1 = SHA1.hashUnencodedChars(s).toString();
			if (!Objects.equals(cache.getHash(path), s1) || !Files.exists(path)) {
				Files.createDirectories(path.getParent());

				try (BufferedWriter bufferedwriter = Files.newBufferedWriter(path)) {
					bufferedwriter.write(s);
				}
			}
			cache.putNew(path, s1);
		} catch (IOException ioexception) {
		}
	}
}
