package lostworlds.data.custom;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;

public abstract class DimensionProvider implements DataProvider {
	protected static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	protected final DataGenerator generator;
	protected final String modid;

	public DimensionProvider(DataGenerator generator, String modid) {
		this.generator = generator;
		this.modid = modid;
	}

	@Override
	public void run(CachedOutput cachedOutput) throws IOException {
		Path path = this.generator.getOutputFolder();
		this.makeDimensions((dimensionBuilder) -> {
			// dimension_type.json
			try {
				DataProvider.saveStable(cachedOutput, dimensionBuilder.serializeDimensionType(this.modid), path.resolve("data/" + this.modid + "/dimension_type/" + dimensionBuilder.id() + ".json"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public abstract void makeDimensions(Consumer<DimensionBuilder> consumer);

	public void addDimension(DimensionBuilder dimensionBuilder, Consumer<DimensionBuilder> consumer) {
		consumer.accept(dimensionBuilder);
	}

	@Override
	public String getName() {
		return "Dimension Types / Dimensions";
	}
}
