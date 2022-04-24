package lostworlds.data.providers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;

public abstract class DimensionProvider implements IDataProvider {
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	private final DataGenerator generator;
	private final String id;

	public DimensionProvider(DataGenerator generator, String id) {
		this.generator = generator;
		this.id = id;
	}

	@Override
	public void run(DirectoryCache cache) throws IOException {
		Path path = this.generator.getOutputFolder();
		this.makeDimensions((dimensionBuilder) -> {
			this.saveToDimension(cache, dimensionBuilder.serializeDimension(), path.resolve("data/" + this.id + "/dimension/" + dimensionBuilder.dimensionName + ".json"));
			this.saveToDimension(cache, dimensionBuilder.serializeDimensionType(), path.resolve("data/" + this.id + "/dimension_type/" + dimensionBuilder.dimensionName + ".json"));
		});
	}

	private void saveToDimension(DirectoryCache cache, JsonObject json, Path path) {
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

	public abstract void makeDimensions(Consumer<DimensionBuilder> consumer);

	@Override
	public String getName() {
		return "Dimensions";
	}
}
