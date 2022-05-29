package lostworlds.data;

import java.util.function.Consumer;

import lostworlds.data.custom.AddBiome;
import lostworlds.data.custom.ClimateData;
import lostworlds.data.custom.DimensionBuilder;
import lostworlds.data.custom.DimensionProvider;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomeKeys;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class LostWorldsDimensionProvider extends DimensionProvider {
	public LostWorldsDimensionProvider(DataGenerator generator) {
		super(generator, LostWorldsUtils.ID);
	}

	@Override
	public void makeDimensions(Consumer<DimensionBuilder> consumer) {
		this.addDimension(new DimensionBuilder("cretaceous", false, true, 1.0F, true, false, 0, false, true, false, false, 384, 384, -64, BlockTags.INFINIBURN_OVERWORLD, "lostworlds:cretaceous", new AddBiome(LostWorldsBiomeKeys.CRETACEOUS_ARAUCARIA_FOREST, new ClimateData(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F))), consumer);
		this.addDimension(new DimensionBuilder("jurassic", false, true, 1.0F, true, false, 0, false, true, false, false, 384, 384, -64, BlockTags.INFINIBURN_OVERWORLD, "lostworlds:jurassic", new AddBiome(LostWorldsBiomeKeys.JURASSIC_ARAUCARIA_FOREST, new ClimateData(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F))), consumer);
		this.addDimension(new DimensionBuilder("permian", false, true, 1.0F, true, false, 0, false, true, false, false, 384, 384, -64, BlockTags.INFINIBURN_OVERWORLD, "lostworlds:permian", new AddBiome(LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST, new ClimateData(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.PERMIAN_GINKGO_FOREST, new ClimateData(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.PERMIAN_PLAINS, new ClimateData(0.4F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.PERMIAN_DRIED_PLAINS, new ClimateData(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.375F)), new AddBiome(LostWorldsBiomeKeys.PERMIAN_DESERT, new ClimateData(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.175F)), new AddBiome(LostWorldsBiomeKeys.PERMIAN_FLOOD_BASALTS, new ClimateData(0.0F, 0.0F, 0.5F, -0.375F, 0.0F, 0.0F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.PERMIAN_ASHY_MEDOWS, new ClimateData(0.5F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, new ClimateData(0.5F, -0.5F, 0.0F, 0.375F, 0.0F, 0.0F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.PERMIAN_MARSH, new ClimateData(0.0F, -0.75F, 0.0F, -0.375F, 0.0F, 0.3F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.PERMIAN_RIVER, new ClimateData(0.0F, 0.75F, 0.0F, -0.375F, 0.0F, 0.3F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.PERMIAN_OCEAN, new ClimateData(0.0F, 0.75F, 0.0F, 0.375F, 0.0F, -0.3F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.WARM_PERMIAN_OCEAN, new ClimateData(0.5F, -0.75F, 0.0F, -0.375F, 0.0F, -0.3F, 0.0F)), new AddBiome(LostWorldsBiomeKeys.PERMIAN_SHORE, new ClimateData(0.0F, -0.75F, 0.0F, -0.375F, 0.0F, 0.3F, 0.5F))), consumer);
	}
}
