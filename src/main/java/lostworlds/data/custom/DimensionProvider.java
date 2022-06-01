package lostworlds.data.custom;

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
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;

public abstract class DimensionProvider implements DataProvider {
	protected static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	protected final DataGenerator generator;
	protected final String modid;

	public DimensionProvider(DataGenerator generator, String modid) {
		this.generator = generator;
		this.modid = modid;
	}

	@Override
	public void run(HashCache cache) throws IOException {
		Path path = this.generator.getOutputFolder();
		this.makeDimensions((dimensionBuilder) -> {
			// dimension.json
			this.saveToJson(cache, dimensionBuilder.serializeDimension(this.modid), path.resolve("data/" + this.modid + "/dimension/" + dimensionBuilder.id() + ".json"));
			// dimension_type.json
			this.saveToJson(cache, dimensionBuilder.serializeDimensionType(this.modid), path.resolve("data/" + this.modid + "/dimension_type/" + dimensionBuilder.id() + ".json"));
		});
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

	public abstract void makeDimensions(Consumer<DimensionBuilder> consumer);

	public void addDimension(DimensionBuilder dimensionBuilder, Consumer<DimensionBuilder> consumer) {
		consumer.accept(dimensionBuilder);
	}

	@Override
	public String getName() {
		return "Dimension Types / Noise Settings / Dimensions";
	}
}
