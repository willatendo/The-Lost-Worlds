package lostworlds.server.util;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlockModels;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.Items;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class LostWorldsRegistrate extends AbstractRegistrate<LostWorldsRegistrate> {
	protected LostWorldsRegistrate(String modid) {
		super(modid);

		this.addDataGenerator(ProviderType.BLOCK_TAGS, provider -> provider.tag(LostWorldsTags.ModBlockTags.CALAMITES_PLACEABLES).addTag(BlockTags.SAND).addTag(Tags.Blocks.DIRT).add(LostWorldsBlocks.CALAMITES_SUCKOWII.get()).add(LostWorldsBlocks.CALAMITES_SUCKOWII_SAPLING.get()).add(Blocks.GRAVEL));
		this.addDataGenerator(ProviderType.BLOCK_TAGS, provider -> provider.tag(LostWorldsTags.ModBlockTags.DINO_SPAWNABLES).addTag(Tags.Blocks.SAND).addTag(Tags.Blocks.GRAVEL).addTag(Tags.Blocks.DIRT).addTag(Tags.Blocks.STONE));
		this.addDataGenerator(ProviderType.BLOCK_TAGS, provider -> provider.tag(LostWorldsTags.ModBlockTags.JURASSIC_PARK_ERA).addTag(LostWorldsTags.ModBlockTags.LIGHT_CONCRETE).addTag(LostWorldsTags.ModBlockTags.WOODEN_PLANKS));
		this.addDataGenerator(ProviderType.BLOCK_TAGS, provider -> provider.tag(LostWorldsTags.ModBlockTags.JURASSIC_WORLD_ERA).addTag(LostWorldsTags.ModBlockTags.DARK_CONCRETE).addTag(LostWorldsTags.ModBlockTags.REFINED_WOODEN_PLANKS));

		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.tag(ItemTags.STONE_TOOL_MATERIALS).add(LostWorldsBlocks.JURASSIC_COBBLESTONE.asStack().getItem()).add(LostWorldsBlocks.PERMIAN_COBBLESTONE.asStack().getItem()));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.tag(ItemTags.STONE_CRAFTING_MATERIALS).add(LostWorldsBlocks.JURASSIC_COBBLESTONE.asStack().getItem()).add(LostWorldsBlocks.PERMIAN_COBBLESTONE.asStack().getItem()));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.tag(LostWorldsTags.ModItemTags.TIME_BOOK_FUEL).add(Items.REDSTONE));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.ARAUCARIA_LOGS, LostWorldsTags.ModItemTags.ARAUCARIA_LOGS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.ANCIENT_SAPLINGS, LostWorldsTags.ModItemTags.ANCIENT_SAPLINGS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.CALAMITES_LOGS, LostWorldsTags.ModItemTags.CALAMITES_LOGS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.CONIFER_LOGS, LostWorldsTags.ModItemTags.CONIFER_LOGS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.CYPRESS_LOGS, LostWorldsTags.ModItemTags.CYPRESS_LOGS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.DARK_CONCRETE, LostWorldsTags.ModItemTags.DARK_CONCRETE));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.DECORATIVE_BLOCKS, LostWorldsTags.ModItemTags.DECORATIVE_BLOCKS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.DECORATIVE_DOORS, LostWorldsTags.ModItemTags.DECORATIVE_DOORS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.EGGS, LostWorldsTags.ModItemTags.EGGS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.FOSSILS, LostWorldsTags.ModItemTags.FOSSILS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.GINKGO_LOGS, LostWorldsTags.ModItemTags.GINKGO_LOGS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.JURASSIC_PARK_ERA, LostWorldsTags.ModItemTags.JURASSIC_PARK_ERA));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.JURASSIC_WORLD_ERA, LostWorldsTags.ModItemTags.JURASSIC_WORLD_ERA));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.LIGHT_CONCRETE, LostWorldsTags.ModItemTags.LIGHT_CONCRETE));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.PAVEMENT, LostWorldsTags.ModItemTags.PAVEMENT));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.PETRIFIED_LOGS, LostWorldsTags.ModItemTags.PETRIFIED_LOGS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.REFINED_WOODEN_PLANKS, LostWorldsTags.ModItemTags.REFINED_WOODEN_PLANKS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.ROADS, LostWorldsTags.ModItemTags.ROADS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.SCORCHED_LOGS, LostWorldsTags.ModItemTags.SCORCHED_LOGS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.SEQUOIA_LOGS, LostWorldsTags.ModItemTags.SEQUOIA_LOGS));
		this.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.copy(LostWorldsTags.ModBlockTags.WOODEN_PLANKS, LostWorldsTags.ModItemTags.WOODEN_PLANKS));

//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("itemGroup.lostworlds.items", "Lost Worlds Items"));
//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("itemGroup.lostworlds.blocks", "Lost Worlds Blocks"));
//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("icon.lostworlds.lost_worlds_lexicon.desc", "An Everything-You-Need-to-Know Book!"));
//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("icon.lostworlds.field_guide.desc", "Willatendo - Volume 3"));
//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("icon.lostworlds.music_disc_ascented.desc", "Willatendo - Ascented"));
//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("icon.lostworlds.collectible.desc", "It's a collectible!"));
//		for (DyeColor color : DyeColor.values()) {
//			this.addDataGenerator(ProviderType.LANG, provider -> provider.add("block.minecraft.banner.scarab." + color.getName(), Arrays.stream(color.getName().toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")) + " Scarab"));
//		}
	}

	public static NonNullSupplier<LostWorldsRegistrate> lazy(String modid) {
		return NonNullSupplier.of(() -> new LostWorldsRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus()));
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockAndItem(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).simpleItem();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> leaves(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().color(() -> LostWorldsBlocks::getGrassyItemColour).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemModel(String name, String parent, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.withExistingParent(item.getName(), provider.modLoc("block/" + parent))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemFlat(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("item/" + name))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemFlat(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("item/" + texture))).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockItemFlatColoured(String name, String texture, DinoTypes types, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("item/" + texture))).color(() -> () -> LostWorldsBlocks.getEggItem(types)).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> plantColoured(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName(), provider.modLoc("block/template_two_part_cross")).texture("stem", "block/" + block.getName() + "_stem").texture("leaves", "block/" + block.getName() + "_leaves")).build();
		})).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name))).build();
	}

	public <T extends DoublePlantBlock> BlockBuilder<T, LostWorldsRegistrate> doublePlantColoured(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER) {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_stem", provider.modLoc("block/template_two_part_cross")).texture("stem", "block/" + block.getName() + "_stem_bottom").texture("leaves", "block/" + block.getName() + "_leaves_bottom")).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_stem", provider.modLoc("block/template_two_part_cross")).texture("stem", "block/" + block.getName() + "_branch_stem").texture("leaves", "block/" + block.getName() + "_branch_leaves")).build();
			}
		})).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name))).build();
	}

	public <T extends DoublePlantBlock> BlockBuilder<T, LostWorldsRegistrate> doublePlant(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER) {
				return ConfiguredModel.builder().modelFile(provider.models().cross(block.getName() + "_bottom", provider.modLoc("block/" + block.getName() + "_bottom"))).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().cross(block.getName() + "_top", provider.modLoc("block/" + block.getName() + "_top"))).build();

			}
		})).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name + "_top"))).build();
	}

	public <T extends DoublePlantBlock> BlockBuilder<T, LostWorldsRegistrate> cephalotaxus(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).forAllStates(state -> {
			if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.LOWER) {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_bottom", provider.modLoc("block/dense_cross")).texture("plant", provider.modLoc("block/cephalotaxus_bottom"))).build();
			} else {
				return ConfiguredModel.builder().modelFile(provider.models().withExistingParent(block.getName() + "_top", provider.modLoc("block/dense_cross")).texture("plant", provider.modLoc("block/cephalotaxus_top"))).build();
			}
		})).item().model((item, provider) -> provider.generated(() -> item.get(), provider.modLoc("block/" + name + "_top"))).color(() -> LostWorldsBlocks::getGrassyItemColour).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> plant(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().cross(block.getName(), new ResourceLocation(block.get().getRegistryName().getNamespace(), "block/" + block.getName()))))).simpleItem();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> parentName(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().withExistingParent(block.getName(), LostWorldsUtils.rL(block.getName()))))).item().color(() -> LostWorldsBlocks::getGrassyItemColour).build();
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> parentNameNoItem(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().withExistingParent(block.getName(), LostWorldsUtils.rL(block.getName())))));
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> pottedBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		if (texture == "archaefrutus") {
			return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().singleTexture(block.getName(), new ResourceLocation("block/flower_pot_cross"), "plant", new ResourceLocation(block.get().getRegistryName().getNamespace(), "block/" + texture))))).tag(BlockTags.FLOWER_POTS);
		} else {
			return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().setModels(new ConfiguredModel(provider.models().singleTexture(block.getName(), LostWorldsUtils.rL("block/water_flower_pot_cross"), "plant", new ResourceLocation(block.get().getRegistryName().getNamespace(), "block/" + texture))))).tag(BlockTags.FLOWER_POTS);
		}
	}

	public <T extends RotatedPillarBlock> BlockBuilder<T, LostWorldsRegistrate> rotatedBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.logBlock(block.get())).tag(BlockTags.LOGS_THAT_BURN, BlockTags.LOGS).simpleItem();
	}

	public <T extends RotatedPillarBlock> BlockBuilder<T, LostWorldsRegistrate> rotatedWoodBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.axisBlock(block.get(), provider.modLoc("block/" + texture), provider.modLoc("block/" + texture))).tag(BlockTags.LOGS_THAT_BURN, BlockTags.LOGS).simpleItem();
	}

	public <T extends StairsBlock> BlockBuilder<T, LostWorldsRegistrate> stairBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.stairsBlock((StairsBlock) block.get(), provider.modLoc("block/" + texture))).simpleItem();
	}

	public <T extends SlabBlock> BlockBuilder<T, LostWorldsRegistrate> slabBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.slabBlock((SlabBlock) block.get(), provider.modLoc("block/" + texture), provider.modLoc("block/" + texture))).simpleItem();
	}

	public <T extends WallBlock> BlockBuilder<T, LostWorldsRegistrate> wallBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.wallBlock((WallBlock) block.get(), provider.modLoc("block/" + texture))).item().model(LostWorldsBlockModels.wallInv(texture)).build().tag(BlockTags.WALLS);
	}

	public <T extends FenceBlock> BlockBuilder<T, LostWorldsRegistrate> fenceBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.fenceBlock((FenceBlock) block.get(), provider.modLoc("block/" + texture))).item().model(LostWorldsBlockModels.fenceInv(texture)).build();
	}

	public <T extends FenceGateBlock> BlockBuilder<T, LostWorldsRegistrate> fenceGateBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.fenceGateBlock((FenceGateBlock) block.get(), provider.modLoc("block/" + texture))).tag(BlockTags.FENCE_GATES).simpleItem();
	}

	public <T extends AbstractButtonBlock> BlockBuilder<T, LostWorldsRegistrate> buttonBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> LostWorldsBlockModels.button(block.get(), texture, provider)).item().model((item, provider) -> LostWorldsBlockModels.buttonInv(item.get(), provider)).build();
	}

	public <T extends PressurePlateBlock> BlockBuilder<T, LostWorldsRegistrate> pressurePlateBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> LostWorldsBlockModels.pressurePlate(block.get(), texture, provider)).simpleItem();
	}

	public <T extends TrapDoorBlock> BlockBuilder<T, LostWorldsRegistrate> trapdoorBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.trapdoorBlock((TrapDoorBlock) block.get(), provider.modLoc("block/" + name), true)).item().model((item, provider) -> provider.withExistingParent(name, provider.modLoc("block/" + name + "_bottom"))).build();
	}

	public <T extends DoorBlock> BlockBuilder<T, LostWorldsRegistrate> doorBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.doorBlock((DoorBlock) block.get(), provider.modLoc("block/" + name + "_bottom"), provider.modLoc("block/" + name + "_top"))).item().model((item, provider) -> provider.itemTexture(() -> item.get())).build();
	}

	public <T extends AbstractSignBlock> BlockBuilder<T, LostWorldsRegistrate> signBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().getBuilder(name).texture("particle", provider.modLoc("block/" + texture))))).lang(provider -> "block.lostworlds." + name + ".disabled", "Sign");
	}
}
