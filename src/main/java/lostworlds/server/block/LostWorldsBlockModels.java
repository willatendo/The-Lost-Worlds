package lostworlds.server.block;

import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
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

	public static VariantBlockStateBuilder pressurePlate(Block block, String texture, RegistrateBlockstateProvider provider) {
		return provider.getVariantBuilder(block).forAllStates(state -> {
			boolean powered = state.getValue(PressurePlateBlock.POWERED);
			if (!powered) {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getRegistryName().getPath(), provider.mcLoc("block/pressure_plate_up")).texture("texture", provider.modLoc("block/" + texture))).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getRegistryName().getPath() + "_down", provider.mcLoc("block/pressure_plate_down")).texture("texture", provider.modLoc("block/" + texture))).build();
			}
		});
	}

	public static VariantBlockStateBuilder button(Block block, String texture, RegistrateBlockstateProvider provider) {
		ModelFile button = provider.models().singleTexture(block.getRegistryName().getPath(), provider.mcLoc("block/button"), provider.modLoc("block/" + texture));
		ModelFile buttonPressed = provider.models().singleTexture(block.getRegistryName().getPath() + "_pressed", provider.mcLoc("block/button_pressed"), provider.modLoc("block/" + texture));
		ModelFile buttonInventory = provider.models().singleTexture(block.getRegistryName().getPath() + "_inventory", provider.mcLoc("block/button_inventory"), provider.modLoc("block/" + texture));
		int angleOffset = 180;
		ConfiguredModel.builder().modelFile(buttonInventory).build();
		return provider.getVariantBuilder(block).forAllStates(state -> {
			boolean powered = state.getValue(WoodButtonBlock.POWERED);
			return ConfiguredModel.builder().modelFile(powered == true ? buttonPressed : button).rotationX(state.getValue(BlockStateProperties.ATTACH_FACE).ordinal() * 90).rotationY((((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + angleOffset) + (state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.CEILING ? 180 : 0)) % 360).build();
		});
	}

	public static ItemModelBuilder buttonInv(BlockItem block, RegistrateItemModelProvider provider) {
		return provider.withExistingParent(block.getRegistryName().getPath(), provider.modLoc("block/" + block.getRegistryName().getPath() + "_inventory"));
	}
}
