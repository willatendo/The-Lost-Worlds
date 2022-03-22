package lostworlds.server.block;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.builder.BlockBuilder;
import lostworlds.server.block.builder.BlockUtils;
import lostworlds.server.block.tree.AraucariaTree;
import lostworlds.server.block.tree.CalamitesTree;
import lostworlds.server.block.tree.ConiferTree;
import lostworlds.server.block.tree.CypressTree;
import lostworlds.server.block.tree.GinkgoTree;
import lostworlds.server.block.tree.SequoiaTree;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.entity.utils.enums.ModBoatType;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.item.ModBoatItem;
import lostworlds.server.item.tool.ModMaterials;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.HayBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.PressurePlateBlock.Sensitivity;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.StoneButtonBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.SignItem;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import tyrannotitanlib.library.base.block.TyrannoConnectedTextureBlock;
import tyrannotitanlib.library.base.block.TyrannoOreBlock;
import tyrannotitanlib.library.base.block.TyrannoSaplingBlock;
import tyrannotitanlib.library.base.block.TyrannoSignManager;

public class LostWorldsBlocks {
	// Soils
	public static final Block DRIED_SOIL = BlockUtils.create("dried_soil", new DriedSoilBlock(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.75F).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL).randomTicks()));
	public static final Block CRACKED_SOIL = BlockUtils.create("cracked_soil", new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.75F).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL)));

	public static final Block MOSSY_SOIL = BlockUtils.create("mossy_soil", new MossySoilBlock(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).harvestTool(ToolType.SHOVEL).randomTicks().sound(SoundType.GRAVEL)));
	public static final Block MUD = BlockUtils.create("mud", new MudBlock(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.6F).sound(SoundType.GRAVEL)));
	public static final Block SILT = BlockUtils.create("silt", new Block(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.6F).sound(SoundType.GRAVEL)));

	public static final Block VOLCANIC_ASH = BlockUtils.create("volcanic_ash", new SandBlock(0x888988, AbstractBlock.Properties.of(Material.SAND, MaterialColor.COLOR_GRAY).harvestTool(ToolType.SHOVEL).harvestLevel(1).strength(0.5F).sound(SoundType.SAND)));
	public static final Block VOLCANIC_ASH_LAYER = BlockUtils.create("volcanic_ash_layer", new VolcanicAshLayerBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.COLOR_GRAY).harvestTool(ToolType.SHOVEL).harvestLevel(1).strength(0.5F).sound(SoundType.SAND)));

	public static final Block PERMIAN_SAND = BlockUtils.create("permian_sand", new SandBlock(0xaa915c, AbstractBlock.Properties.of(Material.SAND, MaterialColor.SAND).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND)));

	public static final Block ROCKY_SOIL = BlockUtils.create("rocky_soil", new SandBlock(0x8a8a8e, AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).harvestTool(ToolType.SHOVEL).requiresCorrectToolForDrops().strength(1.5F)));

	public static final Block PERMAFROST = BlockUtils.create("permafrost", new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.ICE).strength(0.5F).sound(SoundType.GRAVEL)));

	// Stones
	public static final Block PERMIAN_STONE = BlockUtils.create("permian_stone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_STAIRS = BlockUtils.create("permian_stone_stairs", new StairsBlock(() -> LostWorldsBlocks.PERMIAN_STONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_SLAB = BlockUtils.create("permian_stone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_PRESSURE_PLATE = BlockUtils.create("permian_stone_pressure_plate", new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)));
	public static final Block PERMIAN_STONE_BUTTON = BlockUtils.create("permian_stone_button", new StoneButtonBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)));

	public static final Block PERMIAN_COPPER_ORE = BlockUtils.create("permian_copper_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block PERMIAN_GOLD_ORE = BlockUtils.create("permian_gold_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block PERMIAN_IRON_ORE = BlockUtils.create("permian_iron_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block PERMIAN_COAL_ORE = BlockUtils.create("permian_coal_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 2));
	public static final Block PERMIAN_LAPIS_ORE = BlockUtils.create("permian_lapis_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 2, 5));
	public static final Block PERMIAN_DIAMOND_ORE = BlockUtils.create("permian_diamond_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 3, 7));
	public static final Block PERMIAN_REDSTONE_ORE = BlockUtils.create("permian_redstone_ore", new RedstoneOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final Block PERMIAN_EMERALD_ORE = BlockUtils.create("permian_emerald_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 3, 7));

	public static final Block PERMIAN_COBBLESTONE = BlockUtils.create("permian_cobblestone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_COBBLESTONE_STAIRS = BlockUtils.create("permian_cobblestone_stairs", new StairsBlock(() -> LostWorldsBlocks.PERMIAN_STONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_COBBLESTONE_SLAB = BlockUtils.create("permian_cobblestone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_COBBLESTONE_WALL = BlockUtils.create("permian_cobblestone_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final Block PERMIAN_STONE_BRICKS = BlockUtils.create("permian_stone_bricks", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_BRICK_STAIRS = BlockUtils.create("permian_stone_brick_stairs", new StairsBlock(() -> LostWorldsBlocks.PERMIAN_STONE_BRICKS.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_BRICK_SLAB = BlockUtils.create("permian_stone_brick_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_BRICK_WALL = BlockUtils.create("permian_stone_brick_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final Block JURASSIC_STONE = BlockUtils.create("jurassic_stone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_STONE_STAIRS = BlockUtils.create("jurassic_stone_stairs", new StairsBlock(() -> LostWorldsBlocks.JURASSIC_STONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_STONE_SLAB = BlockUtils.create("jurassic_stone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_STONE_PRESSURE_PLATE = BlockUtils.create("jurassic_stone_pressure_plate", new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)));
	public static final Block JURASSIC_STONE_BUTTON = BlockUtils.create("jurassic_stone_button", new StoneButtonBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)));

	public static final Block JURASSIC_COPPER_ORE = BlockUtils.create("jurassic_copper_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block JURASSIC_GOLD_ORE = BlockUtils.create("jurassic_gold_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block JURASSIC_IRON_ORE = BlockUtils.create("jurassic_iron_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block JURASSIC_COAL_ORE = BlockUtils.create("jurassic_coal_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 2));
	public static final Block JURASSIC_LAPIS_ORE = BlockUtils.create("jurassic_lapis_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 2, 5));
	public static final Block JURASSIC_DIAMOND_ORE = BlockUtils.create("jurassic_diamond_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 3, 7));
	public static final Block JURASSIC_REDSTONE_ORE = BlockUtils.create("jurassic_redstone_ore", new RedstoneOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final Block JURASSIC_EMERALD_ORE = BlockUtils.create("jurassic_emerald_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 3, 7));

	public static final Block JURASSIC_COBBLESTONE = BlockUtils.create("jurassic_cobblestone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_COBBLESTONE_STAIRS = BlockUtils.create("jurassic_cobblestone_stairs", new StairsBlock(() -> LostWorldsBlocks.JURASSIC_COBBLESTONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_COBBLESTONE_SLAB = BlockUtils.create("jurassic_cobblestone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_COBBLESTONE_WALL = BlockUtils.create("jurassic_cobblestone_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));

	public static final Block JURASSIC_STONE_BRICKS = BlockUtils.create("jurassic_stone_bricks", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block JURASSIC_STONE_BRICK_STAIRS = BlockUtils.create("jurassic_stone_brick_stairs", new StairsBlock(() -> LostWorldsBlocks.JURASSIC_STONE_BRICKS.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block JURASSIC_STONE_BRICK_SLAB = BlockUtils.create("jurassic_stone_brick_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block JURASSIC_STONE_BRICK_WALL = BlockUtils.create("jurassic_stone_brick_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final Block METEORIC_STONE = BlockUtils.create("meteoric_stone", new Block(AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
	public static final Block METEORIC_STONE_STAIRS = BlockUtils.create("meteoric_stone_stairs", new StairsBlock(() -> LostWorldsBlocks.METEORIC_STONE.defaultBlockState(), AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
	public static final Block METEORIC_STONE_SLAB = BlockUtils.create("meteoric_stone_slab", new SlabBlock(AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
	public static final Block METEORIC_STONE_WALL = BlockUtils.create("meteoric_stone_wall", new WallBlock(AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));

	public static final Block LATERLITE = BlockUtils.create("laterlite", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LATERLITE_STAIRS = BlockUtils.create("laterlite_stairs", new StairsBlock(() -> LostWorldsBlocks.LATERLITE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LATERLITE_SLAB = BlockUtils.create("laterlite_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LATERLITE_WALL = BlockUtils.create("laterlite_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

	public static final Block POLISHED_LATERLITE = BlockUtils.create("polished_laterlite", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block POLISHED_LATERLITE_STAIRS = BlockUtils.create("polished_laterlite_stairs", new StairsBlock(() -> LostWorldsBlocks.LATERLITE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block POLISHED_LATERLITE_SLAB = BlockUtils.create("polished_laterlite_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

	public static final Block RAW_MARBLE = BlockUtils.create("raw_marble", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block RAW_MARBLE_STAIRS = BlockUtils.create("raw_marble_stairs", new StairsBlock(() -> LostWorldsBlocks.RAW_MARBLE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block RAW_MARBLE_SLAB = BlockUtils.create("raw_marble_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block RAW_MARBLE_WALL = BlockUtils.create("raw_marble_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

	public static final Block MARBLE = BlockUtils.create("marble", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block MARBLE_STAIRS = BlockUtils.create("marble_stairs", new StairsBlock(() -> LostWorldsBlocks.MARBLE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block MARBLE_SLAB = BlockUtils.create("marble_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

	public static final Block LIMESTONE = BlockUtils.create("limestone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LIMESTONE_STAIRS = BlockUtils.create("limestone_stairs", new StairsBlock(() -> LostWorldsBlocks.LIMESTONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LIMESTONE_SLAB = BlockUtils.create("limestone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LIMESTONE_WALL = BlockUtils.create("limestone_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

	public static final Block POLISHED_LIMESTONE = BlockUtils.create("polished_limestone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block POLISHED_LIMESTONE_STAIRS = BlockUtils.create("polished_limestone_stairs", new StairsBlock(() -> LostWorldsBlocks.POLISHED_LIMESTONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block POLISHED_LIMESTONE_SLAB = BlockUtils.create("polished_limestone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

	public static final Block VOLCANIC_ROCK = BlockUtils.create("volcanic_rock", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block VOLCANIC_ROCK_STAIRS = BlockUtils.create("volcanic_rock_stairs", new StairsBlock(VOLCANIC_ROCK.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block VOLCANIC_ROCK_SLAB = BlockUtils.create("volcanic_rock_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block VOLCANIC_ROCK_WALL = BlockUtils.create("volcanic_rock_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));

	public static final Block POLISHED_VOLCANIC_ROCK = BlockUtils.create("polished_volcanic_rock", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block POLISHED_VOLCANIC_ROCK_STAIRS = BlockUtils.create("polished_volcanic_rock_stairs", new StairsBlock(POLISHED_VOLCANIC_ROCK.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block POLISHED_VOLCANIC_ROCK_SLAB = BlockUtils.create("polished_volcanic_rock_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));

	public static final Block SOFT_STONE = BlockUtils.create("soft_stone", new SoftStoneBlock());
	public static final Block SOFT_DIRT = BlockUtils.create("soft_dirt", new SoftDirtBlock());
	public static final Block PLANT_FOSSIL = BlockUtils.create("plant_fossil", new PlantFossilBlock());

	public static final Block FOSSILIZED_TRACK = BlockUtils.create("fossilized_track", new FossilizedTrackBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F), Blocks.STONE));
	public static final Block PLASTERED_FOSSILIZED_TRACK = BlockUtils.create("plastered_fossilized_track", new PlasteredBlock(AbstractBlock.Properties.of(Material.STONE).instabreak().sound(SoundType.WOOL), FOSSILIZED_TRACK));

	public static final Block TINY_FOSSILISED_EGG = BlockUtils.create("tiny_fossilized_egg", new TinyFossilizedEggBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()));
	public static final Block TINY_PLASTERED_FOSSILISED_EGG = BlockUtils.create("tiny_plastered_fossilized_egg", new TinyPlasteredFossilizedEggBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()));
	public static final Block SMALL_FOSSILISED_EGG = BlockUtils.create("small_fossilized_egg", new SmallFossilizedEggBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()));
	public static final Block SMALL_PLASTERED_FOSSILISED_EGG = BlockUtils.create("small_plastered_fossilized_egg", new SmallPlasteredFossilizedEggBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()));
	public static final Block MEDIUM_FOSSILISED_EGG = BlockUtils.create("medium_fossilized_egg", new MediumFossilisedEggBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()));
	public static final Block MEDIUM_PLASTERED_FOSSILISED_EGG = BlockUtils.create("medium_plastered_fossilized_egg", new MediumPlasteredFossilizedEggBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()));
	public static final Block LARGE_FOSSILISED_EGG = BlockUtils.create("large_fossilized_egg", new LargeFossilisedEggBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()));
	public static final Block LARGE_PLASTERED_FOSSILISED_EGG = BlockUtils.create("large_plastered_fossilized_egg", new LargePlasteredFossilizedEggBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()));

	// Overworld Ores
	public static final Block AMBER_ORE = BlockUtils.create("amber_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F), 0, 0));
	public static final Block BASALT_DIAMOND_ORE = BlockUtils.create("basalt_diamond_ore", new ModOreRotatedPillerBlock(AbstractBlock.Properties.copy(Blocks.BASALT)));

	// Machines
	public static final Block FOSSIL_CLEANER = FossilCleanerBlock.create();
	public static final Block FOSSIL_GRINDER = FossilGrinderBlock.create();
	public static final Block DNA_EXTRACTOR = DNAExtractorBlock.create();
	public static final Block ANALYZER = AnalyzerBlock.create();
	public static final Block DNA_INJECTOR = DNAInjectorBlock.create();
	public static final Block CULTIVATOR = CultivatorBlock.create();

	public static final Block ARCHAEOLOGY_TABLE = ArchaeologyTableBlock.create("oak");

	public static final Block PALEONTOLOGY_TABLE = BlockUtils.create("paleontology_table", new PaleontologyTableBlock());

	public static final Block PALEOBOTANY_TABLE = BlockUtils.create("paleobotany_table", new PaleobotanyTableBlock());

	public static final Block TIME_MACHINE = TimeMachineBlock.create();

	public static final Block FEEDING_TROUGH = BlockUtils.create("feeding_trough", new FeedingTroughBlock(AbstractBlock.Properties.of(Material.WOOD).harvestTool(ToolType.AXE).strength(2.5F).sound(SoundType.WOOD)));

	// Museum Blocks
	public static final Block DISPLAY_CASE = BlockUtils.create("display_case", new DisplayCaseBlock(AbstractBlock.Properties.of(Material.STONE).harvestLevel(1).requiresCorrectToolForDrops().noOcclusion().strength(4.0F, 5.0F).sound(SoundType.GLASS)));

	// Wood

	// Araucaria
	public static final Block ARAUCARIA_LOG = BlockUtils.create("araucaria_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_ARAUCARIA_LOG = BlockUtils.create("stripped_araucaria_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_WOOD = BlockUtils.create("araucaria_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_ARAUCARIA_WOOD = BlockUtils.create("stripped_araucaria_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_LEAVES = BlockUtils.create("araucaria_leaves", leaves());
	public static final Block ARAUCARIA_SAPLING = BlockUtils.create("araucaria_sapling", new TyrannoSaplingBlock(new AraucariaTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block ARAUCARIA_PLANKS = BlockUtils.create("araucaria_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_STAIRS = BlockUtils.create("araucaria_stairs", new StairsBlock(() -> LostWorldsBlocks.ARAUCARIA_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_SLAB = BlockUtils.create("araucaria_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_FENCE = BlockUtils.create("araucaria_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_FENCE_GATE = BlockUtils.create("araucaria_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_PRESSURE_PLATE = BlockUtils.create("araucaria_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_BUTTON = BlockUtils.create("araucaria_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_TRAPDOOR = BlockUtils.create("araucaria_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_DOOR = BlockUtils.create("araucaria_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_SIGN = BlockBuilder.create("araucaria_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.ARAUCARIA));
	public static final Block ARAUCARIA_WALL_SIGN = BlockBuilder.create("araucaria_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(LostWorldsBlocks.ARAUCARIA_SIGN), ModWoodType.ARAUCARIA));
	public static final Item ARAUCARIA_SIGN_ITEM = LostWorldsRegistry.register("araucaria_sign", new SignItem(new Properties().tab(LostWorldsUtils.BLOCKS).stacksTo(16), ARAUCARIA_SIGN, ARAUCARIA_WALL_SIGN));
	public static final Item ARAUCARIA_BOAT = LostWorldsRegistry.register("araucaria_boat", new ModBoatItem(ModBoatType.ARAUCARIA));

	public static final Block PETRIFIED_ARAUCARIA_LOG = BlockUtils.create("petrified_araucaria_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), () -> LostWorldsItems.ARAUCARIA_BARK_SAMPLE.get()));
	public static final Block STRIPPED_PETRIFIED_ARAUCARIA_LOG = BlockUtils.create("stripped_petrified_araucaria_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));

	// Calamite
	public static final Block CALAMITES_LOG = BlockUtils.create("calamites_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CALAMITES_LOG = BlockUtils.create("stripped_calamites_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_WOOD = BlockUtils.create("calamites_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CALAMITES_WOOD = BlockUtils.create("stripped_calamites_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_LEAVES = BlockUtils.create("calamites_leaves", leaves());
	public static final Block CALAMITES_SAPLING = BlockUtils.create("calamites_sapling", new TyrannoSaplingBlock(new CalamitesTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block CALAMITES_PLANKS = BlockUtils.create("calamites_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_STAIRS = BlockUtils.create("calamites_stairs", new StairsBlock(() -> LostWorldsBlocks.CONIFER_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_SLAB = BlockUtils.create("calamites_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_FENCE = BlockUtils.create("calamites_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_FENCE_GATE = BlockUtils.create("calamites_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_PRESSURE_PLATE = BlockUtils.create("calamites_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CALAMITES_BUTTON = BlockUtils.create("calamites_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CALAMITES_TRAPDOOR = BlockUtils.create("calamites_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CALAMITES_DOOR = BlockUtils.create("calamites_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CALAMITES_SIGN = BlockBuilder.create("calamites_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.CALAMITES));
	public static final Block CALAMITES_WALL_SIGN = BlockBuilder.create("calamites_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(LostWorldsBlocks.CALAMITES_SIGN), ModWoodType.CALAMITES));
	public static final Item CALAMITES_SIGN_ITEM = LostWorldsRegistry.register("calamites_sign", new SignItem(new Properties().tab(LostWorldsUtils.BLOCKS).stacksTo(16), CALAMITES_SIGN, CALAMITES_WALL_SIGN));
	public static final Item CALAMITES_BOAT = LostWorldsRegistry.register("calamites_boat", new ModBoatItem(ModBoatType.CALAMITES));

	public static final Block PETRIFIED_CALAMITES_LOG = BlockUtils.create("petrified_calamites_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), () -> LostWorldsItems.CALAMITES_BARK_SAMPLE.get()));
	public static final Block STRIPPED_PETRIFIED_CALAMITES_LOG = BlockUtils.create("stripped_petrified_calamites_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));

	// Conifer
	public static final Block CONIFER_LOG = BlockUtils.create("conifer_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CONIFER_LOG = BlockUtils.create("stripped_conifer_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_WOOD = BlockUtils.create("conifer_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CONIFER_WOOD = BlockUtils.create("stripped_conifer_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_LEAVES = BlockUtils.create("conifer_leaves", leaves());
	public static final Block CONIFER_SAPLING = BlockUtils.create("conifer_sapling", new TyrannoSaplingBlock(new ConiferTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block CONIFER_PLANKS = BlockUtils.create("conifer_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_STAIRS = BlockUtils.create("conifer_stairs", new StairsBlock(() -> LostWorldsBlocks.CONIFER_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_SLAB = BlockUtils.create("conifer_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_FENCE = BlockUtils.create("conifer_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_FENCE_GATE = BlockUtils.create("conifer_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_PRESSURE_PLATE = BlockUtils.create("conifer_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CONIFER_BUTTON = BlockUtils.create("conifer_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CONIFER_TRAPDOOR = BlockUtils.create("conifer_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CONIFER_DOOR = BlockUtils.create("conifer_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CONIFER_SIGN = BlockBuilder.create("conifer_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.CONIFER));
	public static final Block CONIFER_WALL_SIGN = BlockBuilder.create("conifer_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(LostWorldsBlocks.CONIFER_SIGN), ModWoodType.CONIFER));
	public static final Item CONIFER_SIGN_ITEM = LostWorldsRegistry.register("conifer_sign", new SignItem(new Properties().tab(LostWorldsUtils.BLOCKS).stacksTo(16), CONIFER_SIGN, CONIFER_WALL_SIGN));
	public static final Item CONIFER_BOAT = LostWorldsRegistry.register("conifer_boat", new ModBoatItem(ModBoatType.CONIFER));

	public static final Block PETRIFIED_CONIFER_LOG = BlockUtils.create("petrified_conifer_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), () -> LostWorldsItems.CONIFER_BARK_SAMPLE.get()));
	public static final Block STRIPPED_PETRIFIED_CONIFER_LOG = BlockUtils.create("stripped_petrified_conifer_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));

	// Cypress
	public static final Block CYPRESS_LOG = BlockUtils.create("cypress_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CYPRESS_LOG = BlockUtils.create("stripped_cypress_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_WOOD = BlockUtils.create("cypress_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CYPRESS_WOOD = BlockUtils.create("stripped_cypress_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_LEAVES = BlockUtils.create("cypress_leaves", leaves());
	public static final Block CYPRESS_SAPLING = BlockUtils.create("cypress_sapling", new TyrannoSaplingBlock(new CypressTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block CYPRESS_PLANKS = BlockUtils.create("cypress_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_STAIRS = BlockUtils.create("cypress_stairs", new StairsBlock(() -> LostWorldsBlocks.CYPRESS_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_SLAB = BlockUtils.create("cypress_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_FENCE = BlockUtils.create("cypress_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_FENCE_GATE = BlockUtils.create("cypress_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_PRESSURE_PLATE = BlockUtils.create("cypress_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CYPRESS_BUTTON = BlockUtils.create("cypress_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CYPRESS_TRAPDOOR = BlockUtils.create("cypress_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CYPRESS_DOOR = BlockUtils.create("cypress_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CYPRESS_SIGN = BlockBuilder.create("cypress_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.CYPRESS));
	public static final Block CYPRESS_WALL_SIGN = BlockBuilder.create("cypress_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(LostWorldsBlocks.CYPRESS_SIGN), ModWoodType.CYPRESS));
	public static final Item CYPRESS_SIGN_ITEM = LostWorldsRegistry.register("cypress_sign", new SignItem(new Properties().tab(LostWorldsUtils.BLOCKS).stacksTo(16), CYPRESS_SIGN, CYPRESS_WALL_SIGN));
	public static final Item CYPRESS_BOAT = LostWorldsRegistry.register("cypress_boat", new ModBoatItem(ModBoatType.CYPRESS));

	public static final Block PETRIFIED_CYPRESS_LOG = BlockUtils.create("petrified_cypress_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), () -> LostWorldsItems.CYPRESS_BARK_SAMPLE.get()));
	public static final Block STRIPPED_PETRIFIED_CYPRESS_LOG = BlockUtils.create("stripped_petrified_cypress_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));

	// Ginkgo
	public static final Block GINKGO_LOG = BlockUtils.create("ginkgo_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_GINKGO_LOG = BlockUtils.create("stripped_ginkgo_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_WOOD = BlockUtils.create("ginkgo_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_GINKGO_WOOD = BlockUtils.create("stripped_ginkgo_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_LEAVES = BlockUtils.create("ginkgo_leaves", leaves());
	public static final Block GINKGO_SAPLING = BlockUtils.create("ginkgo_sapling", new TyrannoSaplingBlock(new GinkgoTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block GINKGO_PLANKS = BlockUtils.create("ginkgo_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_STAIRS = BlockUtils.create("ginkgo_stairs", new StairsBlock(() -> LostWorldsBlocks.CONIFER_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_SLAB = BlockUtils.create("ginkgo_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_FENCE = BlockUtils.create("ginkgo_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_FENCE_GATE = BlockUtils.create("ginkgo_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_PRESSURE_PLATE = BlockUtils.create("ginkgo_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block GINKGO_BUTTON = BlockUtils.create("ginkgo_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block GINKGO_TRAPDOOR = BlockUtils.create("ginkgo_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block GINKGO_DOOR = BlockUtils.create("ginkgo_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block GINKGO_SIGN = BlockBuilder.create("ginkgo_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.GINKGO));
	public static final Block GINKGO_WALL_SIGN = BlockBuilder.create("ginkgo_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(LostWorldsBlocks.GINKGO_SIGN), ModWoodType.GINKGO));
	public static final Item GINKGO_SIGN_ITEM = LostWorldsRegistry.register("ginkgo_sign", new SignItem(new Properties().tab(LostWorldsUtils.BLOCKS).stacksTo(16), GINKGO_SIGN, GINKGO_WALL_SIGN));
	public static final Item GINKGO_BOAT = LostWorldsRegistry.register("ginkgo_boat", new ModBoatItem(ModBoatType.GINKGO));

	public static final Block PETRIFIED_GINKGO_LOG = BlockUtils.create("petrified_ginkgo_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), () -> LostWorldsItems.GINKGO_BARK_SAMPLE.get()));
	public static final Block STRIPPED_PETRIFIED_GINKGO_LOG = BlockUtils.create("stripped_petrified_ginkgo_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));

	// Scorched
	public static final Block SCORCHED_LOG = BlockUtils.create("scorched_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_SCORCHED_LOG = BlockUtils.create("stripped_scorched_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_WOOD = BlockUtils.create("scorched_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_SCORCHED_WOOD = BlockUtils.create("stripped_scorched_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_PLANKS = BlockUtils.create("scorched_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_STAIRS = BlockUtils.create("scorched_stairs", new StairsBlock(() -> LostWorldsBlocks.SCORCHED_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_SLAB = BlockUtils.create("scorched_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_FENCE = BlockUtils.create("scorched_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_FENCE_GATE = BlockUtils.create("scorched_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_PRESSURE_PLATE = BlockUtils.create("scorched_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block SCORCHED_BUTTON = BlockUtils.create("scorched_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block SCORCHED_TRAPDOOR = BlockUtils.create("scorched_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block SCORCHED_DOOR = BlockUtils.create("scorched_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block SCORCHED_SIGN = BlockBuilder.create("scorched_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.SCORCHED));
	public static final Block SCORCHED_WALL_SIGN = BlockBuilder.create("scorched_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(LostWorldsBlocks.SCORCHED_SIGN), ModWoodType.SCORCHED));
	public static final Item SCORCHED_SIGN_ITEM = LostWorldsRegistry.register("scorched_sign", new SignItem(new Properties().tab(LostWorldsUtils.BLOCKS).stacksTo(16), SCORCHED_SIGN, SCORCHED_WALL_SIGN));
	public static final Item SCORCHED_BOAT = LostWorldsRegistry.register("scorched_boat", new ModBoatItem(ModBoatType.SCORCHED));

	// Sequoia
	public static final Block SEQUOIA_LOG = BlockUtils.create("sequoia_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_SEQUOIA_LOG = BlockUtils.create("stripped_sequoia_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_WOOD = BlockUtils.create("sequoia_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_SEQUOIA_WOOD = BlockUtils.create("stripped_sequoia_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_LEAVES = BlockUtils.create("sequoia_leaves", leaves());
	public static final Block SEQUOIA_SAPLING = BlockUtils.create("sequoia_sapling", new TyrannoSaplingBlock(new SequoiaTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block SEQUOIA_PLANKS = BlockUtils.create("sequoia_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_STAIRS = BlockUtils.create("sequoia_stairs", new StairsBlock(() -> LostWorldsBlocks.CONIFER_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_SLAB = BlockUtils.create("sequoia_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_FENCE = BlockUtils.create("sequoia_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_FENCE_GATE = BlockUtils.create("sequoia_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_PRESSURE_PLATE = BlockUtils.create("sequoia_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block SEQUOIA_BUTTON = BlockUtils.create("sequoia_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block SEQUOIA_TRAPDOOR = BlockUtils.create("sequoia_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block SEQUOIA_DOOR = BlockUtils.create("sequoia_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block SEQUOIA_SIGN = BlockBuilder.create("sequoia_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.SEQUOIA));
	public static final Block SEQUOIA_WALL_SIGN = BlockBuilder.create("sequoia_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(LostWorldsBlocks.SEQUOIA_SIGN), ModWoodType.SEQUOIA));
	public static final Item SEQUOIA_SIGN_ITEM = LostWorldsRegistry.register("sequoia_sign", new SignItem(new Properties().tab(LostWorldsUtils.BLOCKS).stacksTo(16), SEQUOIA_SIGN, SEQUOIA_WALL_SIGN));
	public static final Item SEQUOIA_BOAT = LostWorldsRegistry.register("sequoia_boat", new ModBoatItem(ModBoatType.SEQUOIA));

	public static final Block PETRIFIED_SEQUOIA_LOG = BlockUtils.create("petrified_sequoia_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), () -> LostWorldsItems.SEQUOIA_BARK_SAMPLE.get()));
	public static final Block STRIPPED_PETRIFIED_SEQUOIA_LOG = BlockUtils.create("stripped_petrified_sequoia_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));

	// Sigillaria

	// Plants
	public static final Block ARCHAEFRUTUS = WaterPlantBlock.create("archaefrutus", new WaterPlantBlock(Effects.DAMAGE_BOOST, 3, AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block POTTED_ARCHAEFRUTUS = BlockBuilder.create("potted_archaefrutus", new FlowerPotBlock(ARCHAEFRUTUS, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
	public static final Block ALETHOPTERIS = BlockUtils.create("alethopteris", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block BRAZILEA = BrazileaBlock.create("brazilea");
	public static final Block CALAMITES_SUCKOWII_SAPLING = BlockBuilder.create("calamites_suckowii_sapling", new CalamtiesSuckowiiSaplingBlock(AbstractBlock.Properties.of(Material.BAMBOO_SAPLING).randomTicks().instabreak().noCollission().strength(1.0F).sound(SoundType.BAMBOO_SAPLING)));
	public static final Block CALAMITES_SUCKOWII = BlockUtils.create("calamites_suckowii", new CalamtiesSuckowiiBlock(AbstractBlock.Properties.of(Material.BAMBOO, MaterialColor.PLANT).randomTicks().instabreak().strength(1.0F).sound(SoundType.BAMBOO).noOcclusion()));
	public static final Block CEPHALOTAXUS = BlockUtils.create("cephalotaxus", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block CYCAD = BlockUtils.create("cycad", new CycadBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(1.0F, 1.5F).randomTicks().noOcclusion().sound(SoundType.WOOD)));
	public static final Block DICKSONIA = BlockUtils.create("dicksonia", new CycadBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(1.0F, 1.5F).randomTicks().noOcclusion().sound(SoundType.WOOD)));
	public static final Block DILLHOFFIA = BlockUtils.create("dillhoffia", new FlowerBlock(Effects.BLINDNESS, 7, AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block POTTED_DILLHOFFIA = BlockBuilder.create("potted_dillhoffia", new FlowerPotBlock(DILLHOFFIA, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
	public static final Block DUISBERGIA = BlockUtils.create("duisbergia", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block EUDICOTS = BlockUtils.create("eudicots", new FlowerBlock(Effects.HUNGER, 10, AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block POTTED_EUDICOTS = BlockBuilder.create("potted_eudicots", new FlowerPotBlock(EUDICOTS, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
	public static final Block GROUND_FERNS = BlockUtils.create("ground_ferns", new GroundFernsBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block MAGNOLIA = BlockUtils.create("magnolia", new FlowerBlock(Effects.MOVEMENT_SLOWDOWN, 8, AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block POTTED_MAGNOLIA = BlockBuilder.create("potted_magnolia", new FlowerPotBlock(MAGNOLIA, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
	public static final Block OSMUNDA = BlockUtils.create("osmunda", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block PERMIAN_DESERT_FERNS = BlockUtils.create("permian_desert_ferns", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block PERMIAN_DESERT_SHRUB = BlockUtils.create("permian_desert_shrub", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_BROWN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	// public static final Block TEMPSKYA = BlockUtils.create("tempskya",
	// new
	// QuintuplePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT,
	// MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block WILLIAMSONIA = BlockUtils.create("williamsonia", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block ZAMITES = BlockUtils.create("zamites", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));

	// Natural Blocks
	public static final Block DIICTODON_BURROW = BlockUtils.create("diictodon_burrow", new DiictodonBurrowBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.SAND).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND).randomTicks()));
	public static final Block TUNNELED_SOIL = BlockUtils.create("tunneled_soil", new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL)));

	public static final Block NAUTILUS_SHELL = BlockBuilder.create("nautilus_shell", new NautilusShellBlock(AbstractBlock.Properties.of(Material.SHULKER_SHELL).instabreak().noOcclusion().sound(SoundType.ANVIL)));

	public static final Block GEYSER_BLOCK = BlockUtils.create("geyser_block", new GeyserBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.75F).noOcclusion()));

	public static final Block NESTING_BLOCK = BlockUtils.create("nesting_block", new Block(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.3F).noOcclusion().sound(SoundType.GRASS)));

	public static final Block DEAD_SPONGE_COLONY = BlockUtils.create("dead_sponge_colony", new DeadSpongeColonyBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.STONE).noOcclusion().strength(1.5F, 6.0F).sound(SoundType.GRASS)));
	public static final Block SPONGE_COLONY = BlockUtils.create("sponge_colony", new SpongeColonyBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().noOcclusion().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK), () -> LostWorldsBlocks.DEAD_SPONGE_COLONY));

	// Jurassic Park Building Blocks
	public static final Block LIGHT_CONCRETE = BlockUtils.create("light_concrete", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_STAIRS = BlockUtils.create("light_concrete_stairs", new StairsBlock(() -> LostWorldsBlocks.LIGHT_CONCRETE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_SLAB = BlockUtils.create("light_concrete_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_WALL = BlockUtils.create("light_concrete_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_PRESSURE_PLATE = BlockUtils.create("light_concrete_pressure_plate", new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_BUTTON = BlockUtils.create("light_concrete_button", new StoneButtonBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)));

	public static final TyrannoConnectedTextureBlock POLISHED_LIGHT_CONCRETE = BlockUtils.create("polished_light_concrete", new TyrannoConnectedTextureBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE), LostWorldsUtils.ID, "polished_dark_concrete", true));

	public static final Block ACCENT_LIGHT_CONCRETE = BlockUtils.create("accent_light_concrete", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE)));
	public static final Block ACCENT_LIGHT_CONCRETE_STAIRS = BlockUtils.create("accent_light_concrete_stairs", new StairsBlock(() -> LostWorldsBlocks.LIGHT_CONCRETE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE)));
	public static final Block ACCENT_LIGHT_CONCRETE_SLAB = BlockUtils.create("accent_light_concrete_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE)));

	public static final Block WOODEN_PLANKS = BlockUtils.create("wooden_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_STAIRS = BlockUtils.create("wooden_planks_stairs", new StairsBlock(() -> LostWorldsBlocks.WOODEN_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_SLAB = BlockUtils.create("wooden_planks_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_FENCE = BlockUtils.create("wooden_planks_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_FENCE_GATE = BlockUtils.create("wooden_planks_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_PRESSURE_PLATE = BlockUtils.create("wooden_planks_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).noCollission().sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_BUTTON = BlockUtils.create("wooden_planks_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).noCollission().sound(SoundType.WOOD)));

	public static final Block METAL_FENCE = BlockUtils.create("metal_fence", new FenceBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_RED).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(4.5F, 6.0F).sound(SoundType.METAL)));

	public static final Block THATCH_BUNDLE = BlockUtils.create("thatch_bundle", new HayBlock(AbstractBlock.Properties.copy(Blocks.HAY_BLOCK)));

	public static final Block OUTDOOR_TOILET_DOOR = BlockUtils.create("outdoor_toilet_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
	public static final Block VISITOR_CENTER_DOOR = BlockUtils.create("visitor_center_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
	public static final Block SECURITY_DOOR = BlockUtils.create("security_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.IRON_DOOR)));

	// Jurassic World Building Blocks
	public static final Block DARK_CONCRETE = BlockUtils.create("dark_concrete", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)));
	public static final Block DARK_CONCRETE_STAIRS = BlockUtils.create("dark_concrete_stairs", new StairsBlock(() -> LostWorldsBlocks.DARK_CONCRETE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)));
	public static final Block DARK_CONCRETE_SLAB = BlockUtils.create("dark_concrete_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)));
	public static final Block DARK_CONCRETE_WALL = BlockUtils.create("dark_concrete_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)));
	public static final Block DARK_CONCRETE_PRESSURE_PLATE = BlockUtils.create("dark_concrete_pressure_plate", new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).noCollission().sound(SoundType.STONE)));
	public static final Block DARK_CONCRETE_BUTTON = BlockUtils.create("dark_concrete_button", new StoneButtonBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).noCollission().sound(SoundType.STONE)));

	public static final TyrannoConnectedTextureBlock POLISHED_DARK_CONCRETE = BlockUtils.create("polished_dark_concrete", new TyrannoConnectedTextureBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE), LostWorldsUtils.ID, "polished_dark_concrete", true));

	public static final Block ACCENT_DARK_CONCRETE = BlockUtils.create("accent_dark_concrete", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.5F, 10.5F).sound(SoundType.STONE)));
	public static final Block ACCENT_DARK_CONCRETE_STAIRS = BlockUtils.create("accent_dark_concrete_stairs", new StairsBlock(() -> LostWorldsBlocks.DARK_CONCRETE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.5F, 10.5F).sound(SoundType.STONE)));
	public static final Block ACCENT_DARK_CONCRETE_SLAB = BlockUtils.create("accent_dark_concrete_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.5F, 10.5F).sound(SoundType.STONE)));

	public static final Block REFINED_WOODEN_PLANKS = BlockUtils.create("refined_wooden_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_STAIRS = BlockUtils.create("refined_wooden_planks_stairs", new StairsBlock(() -> LostWorldsBlocks.REFINED_WOODEN_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_SLAB = BlockUtils.create("refined_wooden_planks_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_FENCE = BlockUtils.create("refined_wooden_planks_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_FENCE_GATE = BlockUtils.create("refined_wooden_planks_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_PRESSURE_PLATE = BlockUtils.create("refined_wooden_planks_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).noCollission().sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_BUTTON = BlockUtils.create("refined_wooden_planks_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).noCollission().sound(SoundType.WOOD)));

	public static final Block TILE = BlockUtils.create("tile", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block TILE_STAIRS = BlockUtils.create("tile_stairs", new StairsBlock(() -> LostWorldsBlocks.TILE.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block TILE_SLAB = BlockUtils.create("tile_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));

	public static final Block GLASS_SIGN = BlockBuilder.create("glass_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_BLACK).instabreak().noOcclusion().noCollission().sound(SoundType.GLASS), ModWoodType.GLASS));
	public static final Block GLASS_WALL_SIGN = BlockBuilder.create("glass_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_BLACK).instabreak().noOcclusion().noCollission().sound(SoundType.GLASS).dropsLike(LostWorldsBlocks.GLASS_SIGN), ModWoodType.GLASS));
	public static final Item GLASS_SIGN_ITEM = LostWorldsRegistry.register("glass_sign", new SignItem(new Properties().tab(LostWorldsUtils.BLOCKS).stacksTo(16), GLASS_SIGN, GLASS_WALL_SIGN));

	public static final Block GLASS_SHOP_DOOR = BlockUtils.create("glass_shop_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
	public static final Block INNOVATION_CENTER_DOOR = BlockUtils.create("innovation_center_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
	public static final Block BACK_DOOR = BlockUtils.create("back_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.IRON_DOOR)));

	// Mixed Park
	public static final Block PAVEMENT = BlockUtils.create("pavement", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.STONE)));
	public static final Block PAVEMENT_SLOPE = BlockUtils.create("pavement_slope", new StairsBlock(() -> LostWorldsBlocks.PAVEMENT.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.STONE)));
	public static final Block RAISED_PAVEMENT = BlockUtils.create("raised_pavement", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)));

	public static final Block GRAVEL_ROAD = BlockUtils.create("gravel_road", new Block(AbstractBlock.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND)));
	public static final Block RAISED_GRAVEL_ROAD = BlockUtils.create("raised_gravel_road", new SlabBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND)));

	public static final Block DIRT_ROAD = BlockUtils.create("dirt_road", new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.GRASS)));
	public static final Block RAISED_DIRT_ROAD = BlockUtils.create("raised_dirt_road", new SlabBlock(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.GRASS)));

	public static final Block PAVED_ROAD = BlockUtils.create("paved_road", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.STONE)));
	public static final Block RAISED_PAVED_ROAD = BlockUtils.create("raised_paved_road", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.STONE)));

	private static Boolean ocelotOrParrot(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}

	private static boolean never(BlockState state, IBlockReader reader, BlockPos pos) {
		return false;
	}

	private static LeavesBlock leaves() {
		return new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).harvestTool(ToolType.HOE).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(LostWorldsBlocks::ocelotOrParrot).isSuffocating(LostWorldsBlocks::never).isViewBlocking(LostWorldsBlocks::never));
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Blocks");

		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.ARAUCARIA_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.ARAUCARIA_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.CALAMITES_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.CALAMITES_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.CONIFER_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.CONIFER_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.CYPRESS_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.CYPRESS_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.GINKGO_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.GINKGO_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.GLASS_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.GLASS_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.SCORCHED_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.SCORCHED_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.SEQUOIA_SIGN);
		TyrannoSignManager.registerSignBlock(() -> LostWorldsBlocks.SEQUOIA_WALL_SIGN);

		ColouredGlassBlock.create();
		ColouredGlassPaneBlock.create();
		ColouredDecorationBlock.create();

		for (DinoTypes types : DinoTypes.eggLaying()) {
			Block egg = BlockUtils.createEgg(types.getId() + "_egg", types.getEgg(types.getEntityType()), LostWorldsUtils.tTC("entity", types.name().toLowerCase()));
			types.setEgg(egg);
		}

		for (DinoTypes types : DinoTypes.values()) {
			if (types == DinoTypes.NAUTILUS) {
				Block shell = BlockUtils.create("fossilized_nautilus_shell", new NautilusShellBlock(AbstractBlock.Properties.of(Material.SHULKER_SHELL).instabreak().noOcclusion().sound(SoundType.ANVIL)));
				types.setExtraBlock(shell);
			}
		}
	}
}
