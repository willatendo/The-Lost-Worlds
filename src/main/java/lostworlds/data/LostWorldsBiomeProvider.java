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
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
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
		consumer.accept(new BiomeBuilder("permain_desert", 0.0F, 3.0F, Precipitation.NONE, Optional.empty(), BiomeCategory.DESERT, new BiomeEffectsBuilder(Optional.empty(), BASE_FOG_COLOUR, BASE_WATER_COLOUR, BASE_WATER_FOG_COLOUR, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 6000, 8, 2), Optional.empty(), Optional.empty(), Optional.empty()), new BiomeSpawnersBuilder(List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of()), new BiomeFeaturesBuilder(List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of())));
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
