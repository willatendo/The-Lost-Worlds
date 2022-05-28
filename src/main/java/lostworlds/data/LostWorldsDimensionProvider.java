package lostworlds.data;

import java.util.function.Consumer;

import lostworlds.data.custom.DimensionBiome;
import lostworlds.data.custom.DimensionBuilder;
import lostworlds.data.custom.DimensionProvider;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

public class LostWorldsDimensionProvider extends DimensionProvider {
	public LostWorldsDimensionProvider(DataGenerator generator) {
		super(generator, LostWorldsUtils.ID);
	}

	@Override
	public void makeDimensions(Consumer<DimensionBuilder> consumer) {
		this.addDimension(new DimensionBuilder("cretaceous", false, true, 1.0F, true, false, 0, false, true, false, false, 384, 384, -64, BlockTags.INFINIBURN_OVERWORLD, "lostworlds:cretaceous", new DimensionBiome(Biomes.PLAINS, Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F))), consumer);
	}
}
