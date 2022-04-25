package lostworlds.data;

import java.util.function.Consumer;

import lostworlds.data.providers.DimensionBuilder;
import lostworlds.data.providers.DimensionProvider;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.data.DataGenerator;

public class LostWorldsDimensionProvider extends DimensionProvider {
	public LostWorldsDimensionProvider(DataGenerator generator) {
		super(generator, LostWorldsUtils.ID);
	}

	@Override
	public void makeDimensions(Consumer<DimensionBuilder> consumer) {
//		consumer.accept(new DimensionBuilder("cretaceous", false, true, 1.0F, true, false, 0, false, true, false, false, 256, BlockTags.INFINIBURN_OVERWORLD, "lostworlds:cretaceous_render").dimensionTypeStuff(-10, 0, 63, Blocks.STONE, Fluids.WATER));
//		consumer.accept(new DimensionBuilder("jurassic", false, true, 1.0F, true, false, 0, false, true, false, false, 256, BlockTags.INFINIBURN_OVERWORLD, "lostworlds:jurassic_render").dimensionTypeStuff(-10, 0, 63, LostWorldsBlocks.JURASSIC_STONE.get(), Fluids.WATER));
//		consumer.accept(new DimensionBuilder("permian", false, true, 1.0F, true, false, 0, false, true, false, false, 256, BlockTags.INFINIBURN_OVERWORLD, "lostworlds:permian_render").dimensionTypeStuff(-10, 0, 63, LostWorldsBlocks.PERMIAN_STONE.get(), Fluids.WATER));
	}
}
