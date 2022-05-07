package lostworlds.server.util.registrate;

import java.util.ArrayList;
import java.util.function.Supplier;

import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.block.CustomTreeSaplingBlock;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.ModFlowerPotBlock;
import lostworlds.server.block.PetrifiedWoodBlock;
import lostworlds.server.block.tree.CustomTree;
import lostworlds.server.entity.utils.enums.ModBoatType;
import lostworlds.server.item.ModBoatItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.PressurePlateBlock.Sensitivity;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.trees.Tree;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SignItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;

public class WoodList {
	private final ArrayList<WoodTypes> typesToMake;
	private final LostWorldsRegistrate registrate;
	private ArrayList<Supplier<BlockEntry<? extends Block>>> blocks = new ArrayList<>();
	private ArrayList<Supplier<ItemEntry<? extends Item>>> items = new ArrayList<>();

	public WoodList(LostWorldsRegistrate registrate, ArrayList<WoodTypes> typesToMake) {
		this.registrate = registrate;
		this.typesToMake = typesToMake;
	}

	public WoodList register(String id, MaterialColor colour, MaterialColor logSideColour, WoodType type, ModBoatType boatType, ITag.INamedTag<Block> blockTags, INamedTag<Item> itemTags) {
		for (WoodTypes types : this.typesToMake) {
			if (types == WoodTypes.LOG) {
				BlockEntry<RotatedPillarBlock> log = this.registrate.rotatedBlock(id + "_" + types.toString().toLowerCase(), RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, (state) -> {
					return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? colour : logSideColour;
				}).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).register();
				this.blocks.add(() -> log);
			}

			if (types == WoodTypes.STRIPPED_LOG) {
				BlockEntry<RotatedPillarBlock> strippedLog = this.registrate.rotatedBlock("stripped_" + id + "_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).register();
				this.blocks.add(() -> strippedLog);
			}

			if (types == WoodTypes.WOOD) {
				BlockEntry<RotatedPillarBlock> wood = this.registrate.rotatedWoodBlock(id + "_" + types.toString().toLowerCase(), id + "_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get(), 3).group("bark").pattern("##").pattern("##").define('#', this.getBlock(WoodTypes.LOG).get().get()).unlockedBy("has_log", provider.hasItem(this.getBlock(WoodTypes.LOG).get().get())).save(provider)).register();
				this.blocks.add(() -> wood);
			}

			if (types == WoodTypes.STRIPPED_WOOD) {
				BlockEntry<RotatedPillarBlock> strippedWood = this.registrate.rotatedWoodBlock("stripped_" + id + "_wood", "stripped_" + id + "_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get(), 3).group("bark").pattern("##").pattern("##").define('#', this.getBlock(WoodTypes.STRIPPED_LOG).get().get()).unlockedBy("has_log", provider.hasItem(this.getBlock(WoodTypes.STRIPPED_LOG).get().get())).save(provider)).register();
				this.blocks.add(() -> strippedWood);
			}

			if (types == WoodTypes.PLANKS) {
				BlockEntry<Block> planks = this.registrate.blockAndItem(id + "_" + types.toString().toLowerCase(), Block::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.PLANKS).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get(), 4).group("planks").pattern("##").pattern("##").define('#', itemTags).unlockedBy("has_log", provider.hasItem(itemTags)).save(provider)).register();
				this.blocks.add(() -> planks);
			}

			if (types == WoodTypes.STAIRS) {
				BlockEntry<StairsBlock> stairs = this.registrate.stairBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", properties -> new StairsBlock(() -> this.getBlock(WoodTypes.PLANKS).get().get().defaultBlockState(), properties)).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_STAIRS).recipe((block, provider) -> provider.stairs(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_stairs", false)).register();
				this.blocks.add(() -> stairs);
			}

			if (types == WoodTypes.SLAB) {
				BlockEntry<SlabBlock> slab = this.registrate.slabBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_SLABS).recipe((block, provider) -> provider.slab(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_slab", false)).register();
				this.blocks.add(() -> slab);
			}

			if (types == WoodTypes.FENCE) {
				BlockEntry<FenceBlock> fence = this.registrate.fenceBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_FENCES).recipe((block, provider) -> provider.fence(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_fence")).register();
				this.blocks.add(() -> fence);
			}

			if (types == WoodTypes.FENCE_GATE) {
				BlockEntry<FenceGateBlock> fence = this.registrate.fenceGateBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.FENCE_GATES).recipe((block, provider) -> provider.fenceGate(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_fence_gate")).register();
				this.blocks.add(() -> fence);
			}

			if (types == WoodTypes.PRESSURE_PLATE) {
				BlockEntry<PressurePlateBlock> pressurePlate = this.registrate.pressurePlateBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", properties -> new PressurePlateBlock(Sensitivity.EVERYTHING, properties)).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_PRESSURE_PLATES).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get()).group("wooden_pressure_plate").pattern("##").define('#', this.getBlock(WoodTypes.PLANKS).get().get()).unlockedBy("has_planks", provider.hasItem(this.getBlock(WoodTypes.PLANKS).get().get())).save(provider)).register();
				this.blocks.add(() -> pressurePlate);
			}

			if (types == WoodTypes.BUTTON) {
				BlockEntry<WoodButtonBlock> button = this.registrate.buttonBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_BUTTONS).recipe((block, provider) -> ShapelessRecipeBuilder.shapeless(block.get()).group("wooden_button").requires(this.getBlock(WoodTypes.PLANKS).get().get()).unlockedBy("has_planks", provider.hasItem(this.getBlock(WoodTypes.PLANKS).get().get())).save(provider)).register();
				this.blocks.add(() -> button);
			}

			if (types == WoodTypes.TRAPDOOR) {
				BlockEntry<TrapDoorBlock> trapdoor = this.registrate.trapdoorBlock(id + "_" + types.toString().toLowerCase(), TrapDoorBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).tag(BlockTags.WOODEN_TRAPDOORS).addLayer(() -> RenderType::cutout).recipe((block, provider) -> provider.trapDoor(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get()), () -> block.get(), "wooden_trapdoor")).register();
				this.blocks.add(() -> trapdoor);
			}

			if (types == WoodTypes.DOOR) {
				BlockEntry<DoorBlock> door = this.registrate.doorBlock(id + "_" + types.toString().toLowerCase(), DoorBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).tag(BlockTags.WOODEN_DOORS).addLayer(() -> RenderType::cutout).recipe((block, provider) -> provider.door(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get()), () -> block.get(), "wooden_door")).loot((provider, block) -> provider.add(block, provider.createDoorTable(block))).register();
				this.blocks.add(() -> door);
			}

			if (types == WoodTypes.SIGN) {
				BlockEntry<StandingSignBlock> standing = this.registrate.signBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", properties -> new StandingSignBlock(properties, type)).properties(properties -> AbstractBlock.Properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
				BlockEntry<WallSignBlock> wall = this.registrate.signBlock(id + "_wall_" + types.toString().toLowerCase(), id + "_planks", properties -> new WallSignBlock(properties, type)).properties(properties -> AbstractBlock.Properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).lootFrom(() -> standing.get())).register();
				this.blocks.add(() -> standing);
				this.blocks.add(() -> wall);
				this.registrate.item(id + "_" + types.toString().toLowerCase(), properties -> new SignItem(properties, standing.get(), wall.get())).properties((properties) -> properties.stacksTo(16)).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get(), 3).group("sign").pattern("###").pattern("###").pattern(" $ ").define('#', this.getBlock(WoodTypes.PLANKS).get().get()).define('$', Items.STICK).unlockedBy("has_planks", provider.hasItem(this.getBlock(WoodTypes.PLANKS).get().get())).save(provider)).register();
				LostWorldsBlocks.registerSignBlock(() -> standing);
				LostWorldsBlocks.registerSignBlock(() -> wall);
			}

			if (types == WoodTypes.BOAT) {
				ItemEntry<ModBoatItem> boat = this.registrate.item(id + "_" + types.toString().toLowerCase(), properties -> new ModBoatItem(boatType, properties)).properties(properties -> properties.stacksTo(1)).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).group("boat").pattern("# #").pattern("###").define('#', this.getBlock(WoodTypes.PLANKS).get().get()).unlockedBy("in_water", provider.enteredBlock(Blocks.WATER)).save(provider)).register();
				this.items.add(() -> boat);
			}
		}
		return this;
	}

	public WoodList register(String id, MaterialColor colour, MaterialColor logSideColour, Tree tree, WoodType type, ModBoatType boatType, Supplier<Item> bark, ITag.INamedTag<Block> blockTags, INamedTag<Item> itemTags) {
		for (WoodTypes types : this.typesToMake) {
			if (types == WoodTypes.LOG) {
				BlockEntry<RotatedPillarBlock> log = this.registrate.rotatedBlock(id + "_" + types.toString().toLowerCase(), RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, (state) -> {
					return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? colour : logSideColour;
				}).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).register();
				this.blocks.add(() -> log);
			}

			if (types == WoodTypes.STRIPPED_LOG) {
				BlockEntry<RotatedPillarBlock> strippedLog = this.registrate.rotatedBlock("stripped_" + id + "_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).register();
				this.blocks.add(() -> strippedLog);
			}

			if (types == WoodTypes.WOOD) {
				BlockEntry<RotatedPillarBlock> wood = this.registrate.rotatedWoodBlock(id + "_" + types.toString().toLowerCase(), id + "_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get(), 3).group("bark").pattern("##").pattern("##").define('#', this.getBlock(WoodTypes.LOG).get().get()).unlockedBy("has_log", provider.hasItem(this.getBlock(WoodTypes.LOG).get().get())).save(provider)).register();
				this.blocks.add(() -> wood);
			}

			if (types == WoodTypes.STRIPPED_WOOD) {
				BlockEntry<RotatedPillarBlock> strippedWood = this.registrate.rotatedWoodBlock("stripped_" + id + "_wood", "stripped_" + id + "_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get(), 3).group("bark").pattern("##").pattern("##").define('#', this.getBlock(WoodTypes.STRIPPED_LOG).get().get()).unlockedBy("has_log", provider.hasItem(this.getBlock(WoodTypes.STRIPPED_LOG).get().get())).save(provider)).register();
				this.blocks.add(() -> strippedWood);
			}

			if (types == WoodTypes.SAPLING) {
				BlockEntry<SaplingBlock> sapling = this.registrate.sapling(id + "_" + types.toString().toLowerCase(), properties -> new SaplingBlock(tree, properties)).properties(properties -> properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).tag(LostWorldsTags.ModBlockTags.ANCIENT_SAPLINGS.tag, BlockTags.SAPLINGS).register();
				this.blocks.add(() -> sapling);
			}

			if (types == WoodTypes.POTTED_SAPLING) {
				BlockEntry<ModFlowerPotBlock> pottedSapling = this.registrate.pottedBlock("potted_" + id + "_sapling", id + "_sapling", properties -> new ModFlowerPotBlock(() -> this.getBlock(WoodTypes.SAPLING).get().get().delegate.get(), properties)).properties(properties -> properties.of(Material.DECORATION).instabreak().noOcclusion()).addLayer(() -> RenderType::cutout).loot((provider, block) -> provider.dropPottedContents(block)).register();
				this.blocks.add(() -> pottedSapling);
			}

			if (types == WoodTypes.LEAVES) {
				BlockEntry<LeavesBlock> leaves = this.registrate.leaves(id + "_" + types.toString().toLowerCase(), LeavesBlock::new).properties(properties -> properties.of(Material.LEAVES).strength(0.2F).harvestTool(ToolType.HOE).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(LostWorldsBlocks::ocelotOrParrot).isSuffocating(LostWorldsBlocks::never).isViewBlocking(LostWorldsBlocks::never)).addLayer(() -> RenderType::cutout).tag(BlockTags.LEAVES).loot((provider, block) -> provider.add(block, provider.droppingWithChancesAndSticks(block, this.getBlock(WoodTypes.SAPLING).get().get(), 0.05F, 0.0625F, 0.083333336F, 0.1F))).register();
				this.blocks.add(() -> leaves);
			}

			if (types == WoodTypes.PLANKS) {
				BlockEntry<Block> planks = this.registrate.blockAndItem(id + "_" + types.toString().toLowerCase(), Block::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.PLANKS).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get(), 4).group("planks").pattern("##").pattern("##").define('#', itemTags).unlockedBy("has_log", provider.hasItem(itemTags)).save(provider)).register();
				this.blocks.add(() -> planks);
			}

			if (types == WoodTypes.STAIRS) {
				BlockEntry<StairsBlock> stairs = this.registrate.stairBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", properties -> new StairsBlock(() -> this.getBlock(WoodTypes.PLANKS).get().get().defaultBlockState(), properties)).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_STAIRS).recipe((block, provider) -> provider.stairs(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_stairs", false)).register();
				this.blocks.add(() -> stairs);
			}

			if (types == WoodTypes.SLAB) {
				BlockEntry<SlabBlock> slab = this.registrate.slabBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_SLABS).recipe((block, provider) -> provider.slab(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_slab", false)).register();
				this.blocks.add(() -> slab);
			}

			if (types == WoodTypes.FENCE) {
				BlockEntry<FenceBlock> fence = this.registrate.fenceBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_FENCES).recipe((block, provider) -> provider.fence(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_fence")).register();
				this.blocks.add(() -> fence);
			}

			if (types == WoodTypes.FENCE_GATE) {
				BlockEntry<FenceGateBlock> fence = this.registrate.fenceGateBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.FENCE_GATES).recipe((block, provider) -> provider.fenceGate(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_fence_gate")).register();
				this.blocks.add(() -> fence);
			}

			if (types == WoodTypes.PRESSURE_PLATE) {
				BlockEntry<PressurePlateBlock> pressurePlate = this.registrate.pressurePlateBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", properties -> new PressurePlateBlock(Sensitivity.EVERYTHING, properties)).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_PRESSURE_PLATES).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get()).group("wooden_pressure_plate").pattern("##").define('#', this.getBlock(WoodTypes.PLANKS).get().get()).unlockedBy("has_planks", provider.hasItem(this.getBlock(WoodTypes.PLANKS).get().get())).save(provider)).register();
				this.blocks.add(() -> pressurePlate);
			}

			if (types == WoodTypes.BUTTON) {
				BlockEntry<WoodButtonBlock> button = this.registrate.buttonBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_BUTTONS).recipe((block, provider) -> ShapelessRecipeBuilder.shapeless(block.get()).group("wooden_button").requires(this.getBlock(WoodTypes.PLANKS).get().get()).unlockedBy("has_planks", provider.hasItem(this.getBlock(WoodTypes.PLANKS).get().get())).save(provider)).register();
				this.blocks.add(() -> button);
			}

			if (types == WoodTypes.TRAPDOOR) {
				BlockEntry<TrapDoorBlock> trapdoor = this.registrate.trapdoorBlock(id + "_" + types.toString().toLowerCase(), TrapDoorBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).tag(BlockTags.WOODEN_TRAPDOORS).addLayer(() -> RenderType::cutout).recipe((block, provider) -> provider.trapDoor(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get()), () -> block.get(), "wooden_trapdoor")).register();
				this.blocks.add(() -> trapdoor);
			}

			if (types == WoodTypes.DOOR) {
				BlockEntry<DoorBlock> door = this.registrate.doorBlock(id + "_" + types.toString().toLowerCase(), DoorBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).tag(BlockTags.WOODEN_DOORS).addLayer(() -> RenderType::cutout).recipe((block, provider) -> provider.door(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get()), () -> block.get(), "wooden_door")).register();
				this.blocks.add(() -> door);
			}

			if (types == WoodTypes.SIGN) {
				BlockEntry<StandingSignBlock> standing = this.registrate.signBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", properties -> new StandingSignBlock(properties, type)).properties(properties -> AbstractBlock.Properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
				BlockEntry<WallSignBlock> wall = this.registrate.signBlock(id + "_wall_" + types.toString().toLowerCase(), id + "_planks", properties -> new WallSignBlock(properties, type)).properties(properties -> AbstractBlock.Properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).lootFrom(() -> standing.get())).register();
				this.blocks.add(() -> standing);
				this.blocks.add(() -> wall);
				this.registrate.item(id + "_" + types.toString().toLowerCase(), properties -> new SignItem(properties, standing.get(), wall.get())).properties((properties) -> properties.stacksTo(16)).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get(), 3).group("sign").pattern("###").pattern("###").pattern(" $ ").define('#', this.getBlock(WoodTypes.PLANKS).get().get()).define('$', Items.STICK).unlockedBy("has_planks", provider.hasItem(this.getBlock(WoodTypes.PLANKS).get().get())).save(provider)).register();
				LostWorldsBlocks.registerSignBlock(() -> standing);
				LostWorldsBlocks.registerSignBlock(() -> wall);
			}

			if (types == WoodTypes.BOAT) {
				ItemEntry<ModBoatItem> boat = this.registrate.item(id + "_" + types.toString().toLowerCase(), properties -> new ModBoatItem(boatType, properties)).properties(properties -> properties.stacksTo(1)).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).group("boat").pattern("# #").pattern("###").define('#', this.getBlock(WoodTypes.PLANKS).get().get()).unlockedBy("in_water", provider.enteredBlock(Blocks.WATER)).save(provider)).register();
				this.items.add(() -> boat);
			}

			if (types == WoodTypes.PETRIFIED_LOG) {
				BlockEntry<PetrifiedWoodBlock> petrifiedLog = this.registrate.rotatedBlock("petrified_" + id + "_log", properties -> new PetrifiedWoodBlock(bark, properties)).properties(properties -> properties.of(Material.STONE, colour).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).tag(LostWorldsTags.ModBlockTags.PETRIFIED_LOGS.tag).register();
				this.blocks.add(() -> petrifiedLog);
			}

			if (types == WoodTypes.STRIPPED_PETRIFIED_LOG) {
				BlockEntry<RotatedPillarBlock> strippedPetrifiedLog = this.registrate.rotatedBlock("stripped_petrified_" + id + "_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.STONE, colour).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).tag(LostWorldsTags.ModBlockTags.PETRIFIED_LOGS.tag).register();
				this.blocks.add(() -> strippedPetrifiedLog);
			}
		}

		return this;
	}

	public WoodList register(String id, MaterialColor colour, MaterialColor logSideColour, CustomTree tree, WoodType type, ModBoatType boatType, Supplier<Item> bark, ITag.INamedTag<Block> blockTags, INamedTag<Item> itemTags) {
		for (WoodTypes types : this.typesToMake) {
			if (types == WoodTypes.LOG) {
				BlockEntry<RotatedPillarBlock> log = this.registrate.rotatedBlock(id + "_" + types.toString().toLowerCase(), RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, (state) -> {
					return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? colour : logSideColour;
				}).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).register();
				this.blocks.add(() -> log);
			}

			if (types == WoodTypes.STRIPPED_LOG) {
				BlockEntry<RotatedPillarBlock> strippedLog = this.registrate.rotatedBlock("stripped_" + id + "_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).register();
				this.blocks.add(() -> strippedLog);
			}

			if (types == WoodTypes.WOOD) {
				BlockEntry<RotatedPillarBlock> wood = this.registrate.rotatedWoodBlock(id + "_" + types.toString().toLowerCase(), id + "_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get(), 3).group("bark").pattern("##").pattern("##").define('#', this.getBlock(WoodTypes.LOG).get().get()).unlockedBy("has_log", provider.hasItem(this.getBlock(WoodTypes.LOG).get().get())).save(provider)).register();
				this.blocks.add(() -> wood);
			}

			if (types == WoodTypes.STRIPPED_WOOD) {
				BlockEntry<RotatedPillarBlock> strippedWood = this.registrate.rotatedWoodBlock("stripped_" + id + "_wood", "stripped_" + id + "_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(blockTags).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get(), 3).group("bark").pattern("##").pattern("##").define('#', this.getBlock(WoodTypes.STRIPPED_LOG).get().get()).unlockedBy("has_log", provider.hasItem(this.getBlock(WoodTypes.STRIPPED_LOG).get().get())).save(provider)).register();
				this.blocks.add(() -> strippedWood);
			}

			if (types == WoodTypes.SAPLING) {
				BlockEntry<CustomTreeSaplingBlock> sapling = this.registrate.sapling(id + "_" + types.toString().toLowerCase(), properties -> new CustomTreeSaplingBlock(tree, properties)).properties(properties -> properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).tag(LostWorldsTags.ModBlockTags.ANCIENT_SAPLINGS.tag, BlockTags.SAPLINGS).register();
				this.blocks.add(() -> sapling);
			}

			if (types == WoodTypes.POTTED_SAPLING) {
				BlockEntry<ModFlowerPotBlock> pottedSapling = this.registrate.pottedBlock("potted_" + id + "_sapling", id + "_sapling", properties -> new ModFlowerPotBlock(() -> this.getBlock(WoodTypes.SAPLING).get().get().delegate.get(), properties)).properties(properties -> properties.of(Material.DECORATION).instabreak().noOcclusion()).addLayer(() -> RenderType::cutout).loot((provider, block) -> provider.dropPottedContents(block)).register();
				this.blocks.add(() -> pottedSapling);
			}

			if (types == WoodTypes.LEAVES) {
				BlockEntry<LeavesBlock> leaves = this.registrate.leaves(id + "_" + types.toString().toLowerCase(), LeavesBlock::new).properties(properties -> properties.of(Material.LEAVES).strength(0.2F).harvestTool(ToolType.HOE).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(LostWorldsBlocks::ocelotOrParrot).isSuffocating(LostWorldsBlocks::never).isViewBlocking(LostWorldsBlocks::never)).addLayer(() -> RenderType::cutout).tag(BlockTags.LEAVES).loot((provider, block) -> provider.add(block, provider.droppingWithChancesAndSticks(block, this.getBlock(WoodTypes.SAPLING).get().get(), 0.05F, 0.0625F, 0.083333336F, 0.1F))).register();
				this.blocks.add(() -> leaves);
			}

			if (types == WoodTypes.PLANKS) {
				BlockEntry<Block> planks = this.registrate.blockAndItem(id + "_" + types.toString().toLowerCase(), Block::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.PLANKS).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get(), 4).group("planks").pattern("##").pattern("##").define('#', itemTags).unlockedBy("has_log", provider.hasItem(itemTags)).save(provider)).register();
				this.blocks.add(() -> planks);
			}

			if (types == WoodTypes.STAIRS) {
				BlockEntry<StairsBlock> stairs = this.registrate.stairBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", properties -> new StairsBlock(() -> this.getBlock(WoodTypes.PLANKS).get().get().defaultBlockState(), properties)).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_STAIRS).recipe((block, provider) -> provider.stairs(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_stairs", false)).register();
				this.blocks.add(() -> stairs);
			}

			if (types == WoodTypes.SLAB) {
				BlockEntry<SlabBlock> slab = this.registrate.slabBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_SLABS).recipe((block, provider) -> provider.slab(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_slab", false)).register();
				this.blocks.add(() -> slab);
			}

			if (types == WoodTypes.FENCE) {
				BlockEntry<FenceBlock> fence = this.registrate.fenceBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_FENCES).recipe((block, provider) -> provider.fence(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_fence")).register();
				this.blocks.add(() -> fence);
			}

			if (types == WoodTypes.FENCE_GATE) {
				BlockEntry<FenceGateBlock> fence = this.registrate.fenceGateBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.FENCE_GATES).recipe((block, provider) -> provider.fenceGate(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get().get()), () -> block.get(), "wooden_fence_gate")).register();
				this.blocks.add(() -> fence);
			}

			if (types == WoodTypes.PRESSURE_PLATE) {
				BlockEntry<PressurePlateBlock> pressurePlate = this.registrate.pressurePlateBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", properties -> new PressurePlateBlock(Sensitivity.EVERYTHING, properties)).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_PRESSURE_PLATES).recipe((block, provider) -> ShapedRecipeBuilder.shaped(block.get()).group("wooden_pressure_plate").pattern("##").define('#', this.getBlock(WoodTypes.PLANKS).get().get()).unlockedBy("has_planks", provider.hasItem(this.getBlock(WoodTypes.PLANKS).get().get())).save(provider)).register();
				this.blocks.add(() -> pressurePlate);
			}

			if (types == WoodTypes.BUTTON) {
				BlockEntry<WoodButtonBlock> button = this.registrate.buttonBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).tag(BlockTags.WOODEN_BUTTONS).recipe((block, provider) -> ShapelessRecipeBuilder.shapeless(block.get()).group("wooden_button").requires(this.getBlock(WoodTypes.PLANKS).get().get()).unlockedBy("has_planks", provider.hasItem(this.getBlock(WoodTypes.PLANKS).get().get())).save(provider)).register();
				this.blocks.add(() -> button);
			}

			if (types == WoodTypes.TRAPDOOR) {
				BlockEntry<TrapDoorBlock> trapdoor = this.registrate.trapdoorBlock(id + "_" + types.toString().toLowerCase(), TrapDoorBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).tag(BlockTags.WOODEN_TRAPDOORS).addLayer(() -> RenderType::cutout).recipe((block, provider) -> provider.trapDoor(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get()), () -> block.get(), "wooden_trapdoor")).register();
				this.blocks.add(() -> trapdoor);
			}

			if (types == WoodTypes.DOOR) {
				BlockEntry<DoorBlock> door = this.registrate.doorBlock(id + "_" + types.toString().toLowerCase(), DoorBlock::new).properties(properties -> properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).tag(BlockTags.WOODEN_DOORS).addLayer(() -> RenderType::cutout).recipe((block, provider) -> provider.door(DataIngredient.items(this.getBlock(WoodTypes.PLANKS).get()), () -> block.get(), "wooden_door")).register();
				this.blocks.add(() -> door);
			}

			if (types == WoodTypes.SIGN) {
				BlockEntry<StandingSignBlock> standing = this.registrate.signBlock(id + "_" + types.toString().toLowerCase(), id + "_planks", properties -> new StandingSignBlock(properties, type)).properties(properties -> AbstractBlock.Properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
				BlockEntry<WallSignBlock> wall = this.registrate.signBlock(id + "_wall_" + types.toString().toLowerCase(), id + "_planks", properties -> new WallSignBlock(properties, type)).properties(properties -> AbstractBlock.Properties.of(Material.WOOD, colour).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).lootFrom(() -> standing.get())).register();
				this.blocks.add(() -> standing);
				this.blocks.add(() -> wall);
				this.registrate.item(id + "_" + types.toString().toLowerCase(), properties -> new SignItem(properties, standing.get(), wall.get())).properties((properties) -> properties.stacksTo(16)).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get(), 3).group("sign").pattern("###").pattern("###").pattern(" $ ").define('#', this.getBlock(WoodTypes.PLANKS).get().get()).define('$', Items.STICK).unlockedBy("has_planks", provider.hasItem(this.getBlock(WoodTypes.PLANKS).get().get())).save(provider)).register();
				LostWorldsBlocks.registerSignBlock(() -> standing);
				LostWorldsBlocks.registerSignBlock(() -> wall);
			}

			if (types == WoodTypes.BOAT) {
				ItemEntry<ModBoatItem> boat = this.registrate.item(id + "_" + types.toString().toLowerCase(), properties -> new ModBoatItem(boatType, properties)).properties(properties -> properties.stacksTo(1)).recipe((item, provider) -> ShapedRecipeBuilder.shaped(item.get()).group("boat").pattern("# #").pattern("###").define('#', this.getBlock(WoodTypes.PLANKS).get().get()).unlockedBy("in_water", provider.enteredBlock(Blocks.WATER)).save(provider)).register();
				this.items.add(() -> boat);
			}

			if (types == WoodTypes.PETRIFIED_LOG) {
				BlockEntry<PetrifiedWoodBlock> petrifiedLog = this.registrate.rotatedBlock("petrified_" + id + "_log", properties -> new PetrifiedWoodBlock(bark, properties)).properties(properties -> properties.of(Material.STONE, colour).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).tag(LostWorldsTags.ModBlockTags.PETRIFIED_LOGS.tag).register();
				this.blocks.add(() -> petrifiedLog);
			}

			if (types == WoodTypes.STRIPPED_PETRIFIED_LOG) {
				BlockEntry<RotatedPillarBlock> strippedPetrifiedLog = this.registrate.rotatedBlock("stripped_petrified_" + id + "_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.STONE, colour).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).tag(LostWorldsTags.ModBlockTags.PETRIFIED_LOGS.tag).register();
				this.blocks.add(() -> strippedPetrifiedLog);
			}
		}

		return this;
	}

	private Supplier<BlockEntry<? extends Block>> getBlock(int block) {
		return this.blocks.get(block);
	}

	public Supplier<BlockEntry<? extends Block>> getBlock(WoodTypes wood) {
		switch (wood) {
		default:
		case LOG:
			return this.getBlock(0);
		case STRIPPED_LOG:
			return this.getBlock(1);
		case WOOD:
			return this.getBlock(2);
		case STRIPPED_WOOD:
			return this.getBlock(3);
		case SAPLING:
			return this.getBlock(4);
		case POTTED_SAPLING:
			return this.getBlock(5);
		case LEAVES:
			return this.getBlock(6);
		case PLANKS:
			return !this.typesToMake.contains(WoodTypes.LEAVES) ? this.getBlock(4) : this.getBlock(7);
		case STAIRS:
			return !this.typesToMake.contains(WoodTypes.LEAVES) ? this.getBlock(5) : this.getBlock(8);
		case SLAB:
			return !this.typesToMake.contains(WoodTypes.LEAVES) ? this.getBlock(6) : this.getBlock(9);
		case FENCE:
			return !this.typesToMake.contains(WoodTypes.LEAVES) ? this.getBlock(7) : this.getBlock(10);
		case FENCE_GATE:
			return !this.typesToMake.contains(WoodTypes.LEAVES) ? this.getBlock(8) : this.getBlock(11);
		case PRESSURE_PLATE:
			return !this.typesToMake.contains(WoodTypes.LEAVES) ? this.getBlock(9) : this.getBlock(12);
		case BUTTON:
			return !this.typesToMake.contains(WoodTypes.LEAVES) ? this.getBlock(10) : this.getBlock(13);
		case TRAPDOOR:
			return !this.typesToMake.contains(WoodTypes.LEAVES) ? this.getBlock(11) : this.getBlock(14);
		case DOOR:
			return !this.typesToMake.contains(WoodTypes.LEAVES) ? this.getBlock(12) : this.getBlock(15);
		case PETRIFIED_LOG:
			return !this.typesToMake.contains(WoodTypes.LEAVES) ? this.getBlock(15) : this.getBlock(18);
		case STRIPPED_PETRIFIED_LOG:
			return !this.typesToMake.contains(WoodTypes.LEAVES) ? this.getBlock(16) : this.getBlock(19);
		}
	}

	public Supplier<ItemEntry<? extends Item>> getItem(int item) {
		return this.items.get(item);
	}
}
