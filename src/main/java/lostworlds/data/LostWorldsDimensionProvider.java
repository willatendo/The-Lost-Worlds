package lostworlds.data;

import java.util.function.Consumer;

import lostworlds.data.custom.AddBiome;
import lostworlds.data.custom.AddPreset;
import lostworlds.data.custom.ClimateData;
import lostworlds.data.custom.DimensionBuilder;
import lostworlds.data.custom.DimensionProvider;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomeKeys;
import lostworlds.server.dimension.LostWorldsNoise;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class LostWorldsDimensionProvider extends DimensionProvider {
	public LostWorldsDimensionProvider(DataGenerator generator) {
		super(generator, LostWorldsUtils.ID);
	}

	@Override
	public void makeDimensions(Consumer<DimensionBuilder> consumer) {
		this.addDimension(new DimensionBuilder("cretaceous", false, true, 1.0F, true, false, 0, false, true, false, false, 384, 384, -64, BlockTags.INFINIBURN_OVERWORLD, "lostworlds:cretaceous", new AddBiome(LostWorldsBiomeKeys.CRETACEOUS_ARAUCARIA_FOREST, new ClimateData(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F))), consumer);
		this.addDimension(new DimensionBuilder("jurassic", false, true, 1.0F, true, false, 0, false, true, false, false, 384, 384, -64, BlockTags.INFINIBURN_OVERWORLD, "lostworlds:jurassic", new AddBiome(LostWorldsBiomeKeys.JURASSIC_ARAUCARIA_FOREST, new ClimateData(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.JURASSIC_CONIFER_FOREST, new ClimateData(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.JURASSIC_GINKGO_FOREST, new ClimateData(0.4F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.JURASSIC_REDWOODS_FOREST, new ClimateData(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.375F)), new AddBiome(LostWorldsBiomeKeys.JURASSIC_PLAINS, new ClimateData(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.175F))), consumer);
		this.addDimension(new DimensionBuilder("permian", false, true, 1.0F, true, false, 0, false, true, false, false, 384, 384, -64, BlockTags.INFINIBURN_OVERWORLD, "lostworlds:permian", new AddPreset(LostWorldsNoise.PERMIAN)), consumer);
	}
}
