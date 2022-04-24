package lostworlds.data;

import java.util.function.Consumer;

import lostworlds.data.providers.DimensionBuilder;
import lostworlds.data.providers.DimensionProvider;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.fluid.Fluids;
import net.minecraft.tags.BlockTags;

public class LostWorldsDimensionProvider extends DimensionProvider {
	public LostWorldsDimensionProvider(DataGenerator generator) {
		super(generator, LostWorldsUtils.ID);
	}

	@Override
	public void makeDimensions(Consumer<DimensionBuilder> consumer) {
		consumer.accept(new DimensionBuilder("permian", false, true, 0, true, false, 0, false, true, false, false, 256, BlockTags.INFINIBURN_OVERWORLD, "lostworlds:permian_render").dimensionTypeStuff(-10, 0, 63, LostWorldsBlocks.PERMIAN_STONE.get(), Fluids.WATER));
	}
}
