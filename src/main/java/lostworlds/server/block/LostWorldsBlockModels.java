package lostworlds.server.block;

import com.tterrag.registrate.providers.RegistrateBlockstateProvider;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.block.Block;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;

public class LostWorldsBlockModels {
	public static VariantBlockStateBuilder mossySoil(Block block, RegistrateBlockstateProvider provider) {
		return provider.getVariantBuilder(block).partialState().setModels(new ConfiguredModel(provider.models().cubeBottomTop(block.getRegistryName().getPath(), provider.modLoc("block/mossy_soil_side"), provider.mcLoc("block/dirt"), provider.modLoc("block/mossy_soil_top"))));
	}

	public static VariantBlockStateBuilder volcanicAshLayer(Block block, RegistrateBlockstateProvider provider) {
		return provider.getVariantBuilder(block).forAllStates(state -> {
			Integer layer = state.getValue(VolcanicAshLayerBlock.LAYERS);
			switch (layer) {
			case 1:
				return ConfiguredModel.builder().modelFile(provider.models().getExistingFile(LostWorldsUtils.rL("block/volcanic_ash_layer_height2"))).build();
			case 2:
				return ConfiguredModel.builder().modelFile(provider.models().getExistingFile(LostWorldsUtils.rL("block/volcanic_ash_layer_height4"))).build();
			case 3:
				return ConfiguredModel.builder().modelFile(provider.models().getExistingFile(LostWorldsUtils.rL("block/volcanic_ash_layer_height6"))).build();
			case 4:
				return ConfiguredModel.builder().modelFile(provider.models().getExistingFile(LostWorldsUtils.rL("block/volcanic_ash_layer_height8"))).build();
			case 5:
				return ConfiguredModel.builder().modelFile(provider.models().getExistingFile(LostWorldsUtils.rL("block/volcanic_ash_layer_height10"))).build();
			case 6:
				return ConfiguredModel.builder().modelFile(provider.models().getExistingFile(LostWorldsUtils.rL("block/volcanic_ash_layer_height12"))).build();
			case 7:
				return ConfiguredModel.builder().modelFile(provider.models().getExistingFile(LostWorldsUtils.rL("block/volcanic_ash_layer_height14"))).build();
			default:
				return ConfiguredModel.builder().modelFile(provider.models().getExistingFile(LostWorldsUtils.rL("block/volcanic_ash"))).build();
			}
		});
	}
}
