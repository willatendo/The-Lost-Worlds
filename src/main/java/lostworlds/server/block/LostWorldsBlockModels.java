package lostworlds.server.block;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.block.Block;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.HayBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
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

	public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> softStone() {
		return (block, provider) -> provider.getVariantBuilder(block.get()).forAllStatesExcept(state -> {
			Damage damage = state.getValue(SoftStoneBlock.DAMAGE);
			PotentialPart potentialPart = state.getValue(SoftStoneBlock.POTENTIAL_PART);
			if (potentialPart != PotentialPart.NONE) {
				if (damage != Damage.NONE) {
					return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_fossil_" + damage.toString().toLowerCase(), provider.modLoc("block/stone_fossil_" + damage.toString().toLowerCase()))).build();
				} else {
					return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_fossil", provider.modLoc("block/stone_fossil"))).build();
				}
			} else {
				if (damage == Damage.NONE) {
					return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName(), "block/stone")).build();
				} else {
					return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_" + damage.toString().toLowerCase(), provider.modLoc("block/stone_" + damage.toString().toLowerCase()))).build();
				}
			}
		}, SoftStoneBlock.POTENTIAL_CREATURE, SoftStoneBlock.ERA);
	}

	public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> plantFossil() {
		return (block, provider) -> provider.getVariantBuilder(block.get()).forAllStatesExcept(state -> {
			Damage damage = state.getValue(SoftStoneBlock.DAMAGE);
			if (damage != Damage.NONE) {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_fossil_" + damage.toString().toLowerCase(), provider.modLoc("block/stone_fossil_" + damage.toString().toLowerCase()))).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_fossil", provider.modLoc("block/stone_fossil"))).build();
			}
		}, PlantFossilBlock.POTENTIAL_PLANT, PlantFossilBlock.ERA);
	}

	public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> alethopteris() {
		return (block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER) {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName(), provider.modLoc("block/" + block.getName() + "_stem"))).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_top", provider.modLoc("block/" + block.getName() + "_leaves"))).build();

			}
		});
	}

	public static <T extends CalamtiesSuckowiiBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> calamitesSuckowii() {
		return (block, provider) -> provider.getMultipartBuilder(block.get()).part().modelFile(provider.models().withExistingParent("calamites_suckowii1_age0", provider.mcLoc("block/bamboo1_age0")).texture("all", provider.modLoc("block/calamites_suckowii_stalk")).texture("particle", provider.modLoc("block/calamites_suckowii_stalk"))).modelFile(provider.models().withExistingParent("calamites_suckowii2_age0", provider.mcLoc("block/bamboo2_age0")).texture("all", provider.modLoc("block/calamites_suckowii_stalk")).texture("particle", provider.modLoc("block/calamites_suckowii_stalk"))).modelFile(provider.models().withExistingParent("calamites_suckowii3_age0", provider.mcLoc("block/bamboo3_age0")).texture("all", provider.modLoc("block/calamites_suckowii_stalk")).texture("particle", provider.modLoc("block/calamites_suckowii_stalk"))).modelFile(provider.models().withExistingParent("calamites_suckowii4_age0", provider.mcLoc("block/bamboo4_age0")).texture("all", provider.modLoc("block/calamites_suckowii_stalk")).texture("particle", provider.modLoc("block/calamites_suckowii_stalk"))).addModel().condition(CalamtiesSuckowiiBlock.AGE, 0).end().part().modelFile(provider.models().withExistingParent("calamites_suckowii1_age1", provider.mcLoc("block/bamboo1_age1")).texture("all", provider.modLoc("block/calamites_suckowii_stalk")).texture("particle", provider.modLoc("block/calamites_suckowii_stalk"))).modelFile(provider.models().withExistingParent("calamites_suckowii2_age1", provider.mcLoc("block/bamboo2_age1")).texture("all", provider.modLoc("block/calamites_suckowii_stalk")).texture("particle", provider.modLoc("block/calamites_suckowii_stalk"))).modelFile(provider.models().withExistingParent("calamites_suckowii3_age1", provider.mcLoc("block/bamboo3_age1")).texture("all", provider.modLoc("block/calamites_suckowii_stalk")).texture("particle", provider.modLoc("block/calamites_suckowii_stalk"))).modelFile(provider.models().withExistingParent("calamites_suckowii4_age1", provider.mcLoc("block/bamboo4_age1")).texture("all", provider.modLoc("block/calamites_suckowii_stalk")).texture("particle", provider.modLoc("block/calamites_suckowii_stalk"))).addModel().condition(CalamtiesSuckowiiBlock.AGE, 1).end();
	}

	public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> diffTextureMC(String texture) {
		return (block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().withExistingParent(block.getName(), new ResourceLocation("block/cube_all")).texture("all", new ResourceLocation("block/" + texture))));
	}

	public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> diffTexture(String texture) {
		return (block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().withExistingParent(block.getName(), new ResourceLocation("block/cube_all")).texture("all", LostWorldsUtils.rL("block/" + texture))));
	}

	public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> parent(String parent) {
		return (block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().withExistingParent(block.getName(), LostWorldsUtils.rL("block/" + parent))));
	}

	public static <T extends MachineBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> machine() {
		return (block, provider) -> machine(provider.models().withExistingParent(block.getName(), provider.modLoc("block/" + block.getName() + "_on")), provider.models().withExistingParent(block.getName(), provider.modLoc("block/" + block.getName())));
	}

	private static <T extends MachineBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> machine(ModelFile on, ModelFile off) {
		return (block, provider) -> provider.horizontalBlock(block.get(), state -> state.getValue(MachineBlock.ON) ? on : off);
	}

	public static <T extends HayBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> thatch(ResourceLocation side, ResourceLocation top) {
		return (block, provider) -> provider.axisBlock(block.get(), side, top);
	}

	public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> parentMc(String parent, String key, String texture) {
		return (block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().withExistingParent(block.getName(), new ResourceLocation("block/" + parent)).texture(key, LostWorldsUtils.rL("block/" + texture))));
	}

	public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> model(String parent, String[] key, String[] texture) {
		return (block, provider) -> {
			BlockModelBuilder model = provider.models().withExistingParent(block.getName(), LostWorldsUtils.rL("block/" + parent));
			for (int i = 0; i < key.length; i++) {
				model.texture(key[i], LostWorldsUtils.rL("block/" + texture[i]));
			}
			provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(model));
		};
	}

	public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> modelMc(String parent, String[] key, String[] texture) {
		return (block, provider) -> {
			BlockModelBuilder model = provider.models().withExistingParent(block.getName(), new ResourceLocation("block/" + parent));
			for (int i = 0; i < key.length; i++) {
				model.texture(key[i], LostWorldsUtils.rL("block/" + texture[i]));
			}
			provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(model));
		};
	}

	public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> modelMcTexMc(String parent, String[] key, String[] texture) {
		return (block, provider) -> {
			BlockModelBuilder model = provider.models().withExistingParent(block.getName(), new ResourceLocation(parent));
			for (int i = 0; i < key.length; i++) {
				model.texture(key[i], new ResourceLocation(texture[i]));
			}
			provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(model));
		};
	}
}
