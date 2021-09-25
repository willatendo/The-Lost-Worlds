package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.block.AnalyzerBlock;
import lostworlds.library.block.ArchaeologyTable;
import lostworlds.library.block.BrazileaBlock;
import lostworlds.library.block.CalamtiesSuckowiiBlock;
import lostworlds.library.block.CalamtiesSuckowiiSaplingBlock;
import lostworlds.library.block.ColouredDecorationBlock;
import lostworlds.library.block.ColouredGlassBlock;
import lostworlds.library.block.ColouredGlassPaneBlock;
import lostworlds.library.block.ConnectedTextureBlock;
import lostworlds.library.block.CultivatorBlock;
import lostworlds.library.block.DNAExtractorBlock;
import lostworlds.library.block.DNAInjectorBlock;
import lostworlds.library.block.DeadSpongeColonyBlock;
import lostworlds.library.block.DriedSoilBlock;
import lostworlds.library.block.FossilCleanerBlock;
import lostworlds.library.block.FossilGrinderBlock;
import lostworlds.library.block.GeyserBlock;
import lostworlds.library.block.GroundFernsBlock;
import lostworlds.library.block.ModBushBlock;
import lostworlds.library.block.ModDoublePlantBlock;
import lostworlds.library.block.ModOreBlock;
import lostworlds.library.block.ModOreRotatedPillerBlock;
import lostworlds.library.block.ModSaplingBlock;
import lostworlds.library.block.ModStandingSignBlock;
import lostworlds.library.block.ModWallSignBlock;
import lostworlds.library.block.ModWoodType;
import lostworlds.library.block.MossySoilBlock;
import lostworlds.library.block.NautilusShellBlock;
import lostworlds.library.block.PetrifiedWoodBlock;
import lostworlds.library.block.PlantFossilBlock;
import lostworlds.library.block.SoftStoneBlock;
import lostworlds.library.block.SpongeColonyBlock;
import lostworlds.library.block.SticksBlock;
import lostworlds.library.block.TimeMachineBlock;
import lostworlds.library.block.VolcanicAshBlock;
import lostworlds.library.block.VolcanicAshLayerBlock;
import lostworlds.library.block.builder.BlockAndItemBuilder;
import lostworlds.library.block.builder.BlockBuilder;
import lostworlds.library.entity.ModBoatType;
import lostworlds.library.item.ModBoatItem;
import lostworlds.library.item.tool.ModMaterials;
import lostworlds.library.tree.CalamitesTree;
import lostworlds.library.tree.ConiferTree;
import lostworlds.library.tree.GinkgoTree;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
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
import net.minecraft.block.StoneButtonBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.SignItem;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;

public class BlockInit 
{		
	//Soils
	public static final Block DRIED_SOIL = BlockAndItemBuilder.create("dried_soil", new DriedSoilBlock(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.75F).sound(SoundType.GRAVEL).randomTicks()));
	public static final Block CRACKED_SOIL = BlockAndItemBuilder.create("cracked_soil", new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.75F).sound(SoundType.GRAVEL)));

	public static final Block MOSSY_SOIL = MossySoilBlock.create();
	public static final Block MUD = BlockAndItemBuilder.create("mud", new Block(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.6F).sound(SoundType.GRAVEL)));
	public static final Block SILT = BlockAndItemBuilder.create("silt", new Block(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.6F).sound(SoundType.GRAVEL)));

	public static final Block VOLCANIC_ASH = BlockAndItemBuilder.create("volcanic_ash", new VolcanicAshBlock());
	public static final Block VOLCANIC_ASH_LAYER = BlockAndItemBuilder.create("volcanic_ash_layer", new VolcanicAshLayerBlock());

	public static final Block PERMIAN_SAND = BlockAndItemBuilder.create("permian_sand", new SandBlock(0xaa915c, AbstractBlock.Properties.of(Material.SAND, MaterialColor.SAND).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND)));
	
	//Stones
	public static final Block PERMIAN_STONE = BlockAndItemBuilder.create("permian_stone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_STAIRS = BlockAndItemBuilder.create("permian_stone_stairs", new StairsBlock(() -> BlockInit.PERMIAN_STONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_SLAB = BlockAndItemBuilder.create("permian_stone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_PRESSURE_PLATE = BlockAndItemBuilder.create("permian_stone_pressure_plate", new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)));
	public static final Block PERMIAN_STONE_BUTTON = BlockAndItemBuilder.create("permian_stone_button", new StoneButtonBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)));

	public static final Block PERMIAN_COPPER_ORE = BlockAndItemBuilder.create("permian_copper_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final Block PERMIAN_GOLD_ORE = BlockAndItemBuilder.create("permian_gold_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final Block PERMIAN_IRON_ORE = BlockAndItemBuilder.create("permian_iron_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final Block PERMIAN_COAL_ORE = BlockAndItemBuilder.create("permian_coal_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final Block PERMIAN_LAPIS_ORE = BlockAndItemBuilder.create("permian_lapis_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));	
	public static final Block PERMIAN_DIAMOND_ORE = BlockAndItemBuilder.create("permian_diamond_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));	
	public static final Block PERMIAN_REDSTONE_ORE = BlockAndItemBuilder.create("permian_redstone_ore", new RedstoneOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));	
	public static final Block PERMIAN_EMERALD_ORE = BlockAndItemBuilder.create("permian_emerald_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	
	public static final Block PERMIAN_COBBLESTONE = BlockAndItemBuilder.create("permian_cobblestone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_COBBLESTONE_STAIRS = BlockAndItemBuilder.create("permian_cobblestone_stairs", new StairsBlock(() -> BlockInit.PERMIAN_STONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_COBBLESTONE_SLAB = BlockAndItemBuilder.create("permian_cobblestone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_COBBLESTONE_WALL = BlockAndItemBuilder.create("permian_cobblestone_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	
	public static final Block PERMIAN_STONE_BRICKS = BlockAndItemBuilder.create("permian_stone_bricks", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_BRICK_STAIRS = BlockAndItemBuilder.create("permian_stone_brick_stairs", new StairsBlock(() -> BlockInit.PERMIAN_STONE_BRICKS.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_BRICK_SLAB = BlockAndItemBuilder.create("permian_stone_brick_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_BRICK_WALL = BlockAndItemBuilder.create("permian_stone_brick_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final Block METEORIC_STONE = BlockAndItemBuilder.create("meteoric_stone", new Block(AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
	public static final Block METEORIC_STONE_STAIRS = BlockAndItemBuilder.create("meteoric_stone_stairs", new StairsBlock(() -> BlockInit.METEORIC_STONE.defaultBlockState(), AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
	public static final Block METEORIC_STONE_SLAB = BlockAndItemBuilder.create("meteoric_stone_slab", new SlabBlock(AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
	public static final Block METEORIC_STONE_WALL = BlockAndItemBuilder.create("meteoric_stone_wall", new WallBlock(AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
	
	public static final Block VOLCANIC_ROCK = BlockAndItemBuilder.create("volcanic_rock", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block VOLCANIC_ROCK_STAIRS = BlockAndItemBuilder.create("volcanic_rock_stairs", new StairsBlock(VOLCANIC_ROCK.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block VOLCANIC_ROCK_SLAB = BlockAndItemBuilder.create("volcanic_rock_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block VOLCANIC_ROCK_WALL = BlockAndItemBuilder.create("volcanic_rock_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	
	public static final Block POLISHED_VOLCANIC_ROCK = BlockAndItemBuilder.create("polished_volcanic_rock", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block POLISHED_VOLCANIC_ROCK_STAIRS = BlockAndItemBuilder.create("polished_volcanic_rock_stairs", new StairsBlock(POLISHED_VOLCANIC_ROCK.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block POLISHED_VOLCANIC_ROCK_SLAB = BlockAndItemBuilder.create("polished_volcanic_rock_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	
	public static final Block SOFT_STONE = BlockAndItemBuilder.create("soft_stone", new SoftStoneBlock());
	public static final Block PLANT_FOSSIL = BlockAndItemBuilder.create("plant_fossil", new PlantFossilBlock());
		
	//Overworld Ores
	public static final Block COPPER_ORE = BlockAndItemBuilder.create("copper_ore", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.STONE)));

	public static final Block BASALT_DIAMOND_ORE = BlockAndItemBuilder.create("basalt_diamond_ore", new ModOreRotatedPillerBlock(AbstractBlock.Properties.copy(Blocks.BASALT)));

	//Machines
	public static final Block FOSSIL_CLEANER = FossilCleanerBlock.create();
	public static final Block FOSSIL_GRINDER = FossilGrinderBlock.create();
	public static final Block DNA_EXTRACTOR = DNAExtractorBlock.create();
	public static final Block ANALYZER = AnalyzerBlock.create();
	public static final Block DNA_INJECTOR = DNAInjectorBlock.create();
	public static final Block CULTIVATOR = CultivatorBlock.create();
		
	public static final Block ARCHAEOLOGY_TABLE = ArchaeologyTable.create();
		
	public static final Block TIME_MACHINE = TimeMachineBlock.create();
			
	//Wood
	
	//Araucaria
	public static final Block ARAUCARIA_LOG = BlockAndItemBuilder.create("araucaria_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_ARAUCARIA_LOG = BlockAndItemBuilder.create("stripped_araucaria_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_WOOD = BlockAndItemBuilder.create("araucaria_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_ARAUCARIA_WOOD = BlockAndItemBuilder.create("stripped_araucaria_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_LEAVES = BlockAndItemBuilder.create("araucaria_leaves", new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.COLOR_GREEN).strength(0.3F).noOcclusion().sound(SoundType.GRASS)));
	public static final Block ARAUCARIA_SAPLING = BlockAndItemBuilder.create("araucaria_sapling", new ModSaplingBlock(null, AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block ARAUCARIA_PLANKS = BlockAndItemBuilder.create("araucaria_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_STAIRS = BlockAndItemBuilder.create("araucaria_stairs", new StairsBlock(() -> BlockInit.ARAUCARIA_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_SLAB = BlockAndItemBuilder.create("araucaria_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_FENCE = BlockAndItemBuilder.create("araucaria_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_FENCE_GATE = BlockAndItemBuilder.create("araucaria_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_PRESSURE_PLATE = BlockAndItemBuilder.create("araucaria_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_BUTTON = BlockAndItemBuilder.create("araucaria_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_TRAPDOOR = BlockAndItemBuilder.create("araucaria_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_DOOR = BlockAndItemBuilder.create("araucaria_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_SIGN = BlockBuilder.create("araucaria_sign", new ModStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.ARAUCARIA));
	public static final Block ARAUCARIA_WALL_SIGN = BlockBuilder.create("araucaria_wall_sign", new ModWallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.ARAUCARIA_SIGN), ModWoodType.ARAUCARIA));
	public static final Item ARAUCARIA_SIGN_ITEM = ModRegistry.register("araucaria_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), ARAUCARIA_SIGN, ARAUCARIA_WALL_SIGN));
	public static final Item ARAUCARIA_BOAT = ModRegistry.register("araucaria_boat", new ModBoatItem(ModBoatType.ARAUCARIA));
	
	public static final Block ARAUCARIA_STICKS = BlockAndItemBuilder.create("araucaria_sticks", new SticksBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).instabreak().noCollission().noOcclusion().sound(SoundType.WOOD)));
	public static final Block PETRIFIED_ARAUCARIA_LOG = BlockAndItemBuilder.create("petrified_araucaria_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND), ItemInit.ARAUCARIA_BARK_SAMPLE));
	public static final Block STRIPPED_PETRIFIED_ARAUCARIA_LOG = BlockAndItemBuilder.create("stripped_petrified_araucaria_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND)));
	
	//Calamite
	public static final Block CALAMITES_LOG = BlockAndItemBuilder.create("calamites_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CALAMITES_LOG = BlockAndItemBuilder.create("stripped_calamites_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_WOOD = BlockAndItemBuilder.create("calamites_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CALAMITES_WOOD = BlockAndItemBuilder.create("stripped_calamites_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_LEAVES = BlockAndItemBuilder.create("calamites_leaves", new LeavesBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(0.3F).noOcclusion().sound(SoundType.GRASS)));
	public static final Block CALAMITES_SAPLING = BlockAndItemBuilder.create("calamites_sapling", new ModSaplingBlock(new CalamitesTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block CALAMITES_PLANKS = BlockAndItemBuilder.create("calamites_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_STAIRS = BlockAndItemBuilder.create("calamites_stairs", new StairsBlock(() -> BlockInit.CONIFER_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_SLAB = BlockAndItemBuilder.create("calamites_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_FENCE = BlockAndItemBuilder.create("calamites_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_FENCE_GATE = BlockAndItemBuilder.create("calamites_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_PRESSURE_PLATE = BlockAndItemBuilder.create("calamites_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CALAMITES_BUTTON = BlockAndItemBuilder.create("calamites_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CALAMITES_TRAPDOOR = BlockAndItemBuilder.create("calamites_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CALAMITES_DOOR = BlockAndItemBuilder.create("calamites_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CALAMITES_SIGN = BlockBuilder.create("calamites_sign", new ModStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.CALAMITES));
	public static final Block CALAMITES_WALL_SIGN = BlockBuilder.create("calamites_wall_sign", new ModWallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.CALAMITES_SIGN), ModWoodType.CALAMITES));
	public static final Item CALAMITES_SIGN_ITEM = ModRegistry.register("calamites_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), CALAMITES_SIGN, CALAMITES_WALL_SIGN));
	public static final Item CALAMITES_BOAT = ModRegistry.register("calamites_boat", new ModBoatItem(ModBoatType.CALAMITES));
	
	public static final Block CALAMITES_STICKS = BlockAndItemBuilder.create("calamites_sticks", new SticksBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).instabreak().noCollission().noOcclusion().sound(SoundType.WOOD)));
	public static final Block PETRIFIED_CALAMITES_LOG = BlockAndItemBuilder.create("petrified_calamites_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND), ItemInit.CALAMITES_BARK_SAMPLE));
	public static final Block STRIPPED_PETRIFIED_CALAMITES_LOG = BlockAndItemBuilder.create("stripped_petrified_calamites_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND)));
	
	//Conifer
	public static final Block CONIFER_LOG = BlockAndItemBuilder.create("conifer_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CONIFER_LOG = BlockAndItemBuilder.create("stripped_conifer_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_WOOD = BlockAndItemBuilder.create("conifer_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CONIFER_WOOD = BlockAndItemBuilder.create("stripped_conifer_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_LEAVES = BlockAndItemBuilder.create("conifer_leaves", new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.COLOR_GREEN).strength(0.3F).noOcclusion().sound(SoundType.GRASS)));
	public static final Block CONIFER_SAPLING = BlockAndItemBuilder.create("conifer_sapling", new ModSaplingBlock(new ConiferTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block CONIFER_PLANKS = BlockAndItemBuilder.create("conifer_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_STAIRS = BlockAndItemBuilder.create("conifer_stairs", new StairsBlock(() -> BlockInit.CONIFER_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_SLAB = BlockAndItemBuilder.create("conifer_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_FENCE = BlockAndItemBuilder.create("conifer_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_FENCE_GATE = BlockAndItemBuilder.create("conifer_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_PRESSURE_PLATE = BlockAndItemBuilder.create("conifer_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CONIFER_BUTTON = BlockAndItemBuilder.create("conifer_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CONIFER_TRAPDOOR = BlockAndItemBuilder.create("conifer_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CONIFER_DOOR = BlockAndItemBuilder.create("conifer_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CONIFER_SIGN = BlockBuilder.create("conifer_sign", new ModStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.CONIFER));
	public static final Block CONIFER_WALL_SIGN = BlockBuilder.create("conifer_wall_sign", new ModWallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.CONIFER_SIGN), ModWoodType.CONIFER));
	public static final Item CONIFER_SIGN_ITEM = ModRegistry.register("conifer_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), CONIFER_SIGN, CONIFER_WALL_SIGN));
	public static final Item CONIFER_BOAT = ModRegistry.register("conifer_boat", new ModBoatItem(ModBoatType.CONIFER));
	
	public static final Block CONIFER_STICKS = BlockAndItemBuilder.create("conifer_sticks", new SticksBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).instabreak().noCollission().noOcclusion().sound(SoundType.WOOD)));
	public static final Block PETRIFIED_CONIFER_LOG = BlockAndItemBuilder.create("petrified_conifer_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND), ItemInit.CONIFER_BARK_SAMPLE));
	public static final Block STRIPPED_PETRIFIED_CONIFER_LOG = BlockAndItemBuilder.create("stripped_petrified_conifer_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND)));
	
	//Cycad
	
	//Dicksonia
	
	//Ginkgo
	public static final Block GINKGO_LOG = BlockAndItemBuilder.create("ginkgo_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_GINKGO_LOG = BlockAndItemBuilder.create("stripped_ginkgo_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_WOOD = BlockAndItemBuilder.create("ginkgo_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_GINKGO_WOOD = BlockAndItemBuilder.create("stripped_ginkgo_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_LEAVES = BlockAndItemBuilder.create("ginkgo_leaves", new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.COLOR_GREEN).strength(0.3F).noOcclusion().sound(SoundType.GRASS)));
	public static final Block GINKGO_SAPLING = BlockAndItemBuilder.create("ginkgo_sapling", new ModSaplingBlock(new GinkgoTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block GINKGO_PLANKS = BlockAndItemBuilder.create("ginkgo_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_STAIRS = BlockAndItemBuilder.create("ginkgo_stairs", new StairsBlock(() -> BlockInit.CONIFER_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_SLAB = BlockAndItemBuilder.create("ginkgo_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_FENCE = BlockAndItemBuilder.create("ginkgo_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_FENCE_GATE = BlockAndItemBuilder.create("ginkgo_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_PRESSURE_PLATE = BlockAndItemBuilder.create("ginkgo_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block GINKGO_BUTTON = BlockAndItemBuilder.create("ginkgo_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block GINKGO_TRAPDOOR = BlockAndItemBuilder.create("ginkgo_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block GINKGO_DOOR = BlockAndItemBuilder.create("ginkgo_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block GINKGO_SIGN = BlockBuilder.create("ginkgo_sign", new ModStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.GINKGO));
	public static final Block GINKGO_WALL_SIGN = BlockBuilder.create("ginkgo_wall_sign", new ModWallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.GINKGO_SIGN), ModWoodType.GINKGO));
	public static final Item GINKGO_SIGN_ITEM = ModRegistry.register("ginkgo_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), GINKGO_SIGN, GINKGO_WALL_SIGN));
	public static final Item GINKGO_BOAT = ModRegistry.register("ginkgo_boat", new ModBoatItem(ModBoatType.GINKGO));
	
	public static final Block GINKGO_STICKS = BlockAndItemBuilder.create("ginkgo_sticks", new SticksBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).instabreak().noCollission().noOcclusion().sound(SoundType.WOOD)));
	public static final Block PETRIFIED_GINKGO_LOG = BlockAndItemBuilder.create("petrified_ginkgo_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND), ItemInit.GINKGO_BARK_SAMPLE));
	public static final Block STRIPPED_PETRIFIED_GINKGO_LOG = BlockAndItemBuilder.create("stripped_petrified_ginkgo_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND)));
	
	//Scorched
	public static final Block SCORCHED_LOG = BlockAndItemBuilder.create("scorched_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_SCORCHED_LOG = BlockAndItemBuilder.create("stripped_scorched_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_WOOD = BlockAndItemBuilder.create("scorched_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_SCORCHED_WOOD = BlockAndItemBuilder.create("stripped_scorched_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_PLANKS = BlockAndItemBuilder.create("scorched_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD))); 
	public static final Block SCORCHED_STAIRS = BlockAndItemBuilder.create("scorched_stairs", new StairsBlock(() -> BlockInit.SCORCHED_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_SLAB = BlockAndItemBuilder.create("scorched_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_FENCE = BlockAndItemBuilder.create("scorched_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_FENCE_GATE = BlockAndItemBuilder.create("scorched_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_PRESSURE_PLATE = BlockAndItemBuilder.create("scorched_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block SCORCHED_BUTTON = BlockAndItemBuilder.create("scorched_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block SCORCHED_TRAPDOOR = BlockAndItemBuilder.create("scorched_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_DOOR = BlockAndItemBuilder.create("scorched_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SCORCHED_SIGN = BlockBuilder.create("scorched_sign", new ModStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.SCORCHED));
	public static final Block SCORCHED_WALL_SIGN = BlockBuilder.create("scorched_wall_sign", new ModWallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.SCORCHED_SIGN), ModWoodType.SCORCHED));
	public static final Item SCORCHED_SIGN_ITEM = ModRegistry.register("scorched_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), SCORCHED_SIGN, SCORCHED_WALL_SIGN));
	public static final Item SCORCHED_BOAT = ModRegistry.register("scorched_boat", new ModBoatItem(ModBoatType.SCORCHED));

	public static final Block SCORCHED_STICKS = BlockAndItemBuilder.create("scorched_sticks", new SticksBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).instabreak().noCollission().noOcclusion().sound(SoundType.WOOD)));
	
	//Sigillaria
	
	//Plants
	public static final Block ALETHOPTERIS = BlockAndItemBuilder.create("alethopteris", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block BRAZILEA = BrazileaBlock.create("brazilea", new BrazileaBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block CEPHALOTAXUS = BlockAndItemBuilder.create("cephalotaxus", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block CALAMITES_SUCKOWII_SAPLING = BlockBuilder.create("calamites_suckowii_sapling", new CalamtiesSuckowiiSaplingBlock(AbstractBlock.Properties.of(Material.BAMBOO_SAPLING).randomTicks().instabreak().noCollission().strength(1.0F).sound(SoundType.BAMBOO_SAPLING)));
	public static final Block CALAMITES_SUCKOWII = BlockAndItemBuilder.create("calamites_suckowii", new CalamtiesSuckowiiBlock(AbstractBlock.Properties.of(Material.BAMBOO, MaterialColor.PLANT).randomTicks().instabreak().strength(1.0F).sound(SoundType.BAMBOO).noOcclusion()));
	public static final Block DILLHOFFIA = BlockAndItemBuilder.create("dillhoffia", new FlowerBlock(Effects.BLINDNESS, 7, AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block POTTED_DILLHOFFIA = BlockBuilder.create("potted_dillhoffia", new FlowerPotBlock(DILLHOFFIA, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
	public static final Block DUISBERGIA = BlockAndItemBuilder.create("duisbergia", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block GROUND_FERNS = BlockAndItemBuilder.create("ground_ferns", new GroundFernsBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block OSMUNDA = BlockAndItemBuilder.create("osmunda", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block PERMIAN_DESERT_FERNS = BlockAndItemBuilder.create("permian_desert_ferns", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block PERMIAN_DESERT_SHRUB = BlockAndItemBuilder.create("permian_desert_shrub", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_BROWN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	//public static final Block TEMPSKYA = BlockAndItemBuilder.create("tempskya", new QuintuplePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block WILLIAMSONIA = BlockAndItemBuilder.create("williamsonia", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block ZAMITES = BlockAndItemBuilder.create("zamites", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	
	//Natural Blocks
	public static final Block DIICTODON_BURROW = BlockAndItemBuilder.create("diictodon_burrow", new Block(AbstractBlock.Properties.of(Material.SAND, MaterialColor.SAND).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND)));
	public static final Block TUNNELED_SOIL = BlockAndItemBuilder.create("tunneled_soil", new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));

	public static final Block NAUTILUS_SHELL = BlockBuilder.create("nautilus_shell", new NautilusShellBlock(AbstractBlock.Properties.of(Material.SHULKER_SHELL).instabreak().noOcclusion().sound(SoundType.ANVIL)));
	
	public static final Block GEYSER_BLOCK = BlockAndItemBuilder.create("geyser_block", new GeyserBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.75F).noOcclusion()));
	
	public static final Block NESTING_BLOCK = BlockAndItemBuilder.create("nesting_block", new Block(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.3F).sound(SoundType.GRASS)));
	
	public static final Block DEAD_SPONGE_COLONY = BlockAndItemBuilder.create("dead_sponge_colony", new DeadSpongeColonyBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.STONE).noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block SPONGE_COLONY = BlockAndItemBuilder.create("sponge_colony", new SpongeColonyBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().noOcclusion().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK), () -> BlockInit.DEAD_SPONGE_COLONY));
	
	//Jurassic Blocks
	public static final Block JURASSIC_STONE = BlockAndItemBuilder.create("jurassic_stone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_STONE_STAIRS = BlockAndItemBuilder.create("jurassic_stone_stairs", new StairsBlock(() -> BlockInit.JURASSIC_STONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_STONE_SLAB = BlockAndItemBuilder.create("jurassic_stone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	//public static final Block JURASSIC_STONE_PRESSURE_PLATE = BlockAndItemBuilder.create("jurassic_stone_pressure_plate", new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_STONE_BUTTON = BlockAndItemBuilder.create("jurassic_stone_pressure_plate", new StoneButtonBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));

	public static final Block MOSSY_JURASSIC_STONE = BlockAndItemBuilder.create("mossy_jurassic_stone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().randomTicks().strength(3.0F)));
	public static final Block MOSSY_JURASSIC_STONE_STAIRS = BlockAndItemBuilder.create("mossy_jurassic_stone_stairs", new StairsBlock(() -> BlockInit.MOSSY_JURASSIC_STONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block MOSSY_JURASSIC_STONE_SLAB = BlockAndItemBuilder.create("mossy_jurassic_stone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));

	public static final Block JURASSIC_COBBLESTONE = BlockAndItemBuilder.create("jurassic_cobblestone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_COBBLESTONE_STAIRS = BlockAndItemBuilder.create("jurassic_cobblestone_stairs", new StairsBlock(() -> BlockInit.JURASSIC_COBBLESTONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_COBBLESTONE_SLAB = BlockAndItemBuilder.create("jurassic_cobblestone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_COBBLESTONE_WALL = BlockAndItemBuilder.create("jurassic_cobblestone_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	
	public static final Block MOSSY_JURASSIC_COBBLESTONE = BlockAndItemBuilder.create("mossy_jurassic_cobblestone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block MOSSY_JURASSIC_COBBLESTONE_STAIRS = BlockAndItemBuilder.create("mossy_jurassic_cobblestone_stairs", new StairsBlock(() -> BlockInit.MOSSY_JURASSIC_COBBLESTONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block MOSSY_JURASSIC_COBBLESTONE_SLAB = BlockAndItemBuilder.create("mossy_jurassic_cobblestone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block MOSSY_JURASSIC_COBBLESTONE_WALL = BlockAndItemBuilder.create("mossy_jurassic_cobblestone_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	
	public static final Block JURASSIC_STONE_BRICKS = BlockAndItemBuilder.create("jurassic_stone_bricks", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block JURASSIC_STONE_BRICK_STAIRS = BlockAndItemBuilder.create("jurassic_stone_brick_stairs", new StairsBlock(() -> BlockInit.JURASSIC_STONE_BRICKS.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block JURASSIC_STONE_BRICK_SLAB = BlockAndItemBuilder.create("jurassic_stone_brick_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block JURASSIC_STONE_BRICK_WALL = BlockAndItemBuilder.create("jurassic_stone_brick_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	
	public static final Block MOSSY_JURASSIC_STONE_BRICKS = BlockAndItemBuilder.create("mossy_jurassic_stone_bricks", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block MOSSY_JURASSIC_STONE_BRICK_STAIRS = BlockAndItemBuilder.create("mossy_jurassic_stone_brick_stairs", new StairsBlock(() -> BlockInit.MOSSY_JURASSIC_STONE_BRICKS.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block MOSSY_JURASSIC_STONE_BRICK_SLAB = BlockAndItemBuilder.create("mossy_jurassic_stone_brick_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block MOSSY_JURASSIC_STONE_BRICK_WALL = BlockAndItemBuilder.create("mossy_jurassic_stone_brick_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	
	public static final Block JURASSIC_COPPER_ORE = BlockAndItemBuilder.create("jurassic_copper_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final Block JURASSIC_GOLD_ORE = BlockAndItemBuilder.create("jurassic_gold_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final Block JURASSIC_IRON_ORE = BlockAndItemBuilder.create("jurassic_iron_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final Block JURASSIC_COAL_ORE = BlockAndItemBuilder.create("jurassic_coal_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
	public static final Block JURASSIC_LAPIS_ORE = BlockAndItemBuilder.create("jurassic_lapis_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));	
	public static final Block JURASSIC_DIAMOND_ORE = BlockAndItemBuilder.create("jurassic_diamond_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));	
	public static final Block JURASSIC_REDSTONE_ORE = BlockAndItemBuilder.create("jurassic_redstone_ore", new RedstoneOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));	
	public static final Block JURASSIC_EMERALD_ORE = BlockAndItemBuilder.create("jurassic_emerald_ore", new ModOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));	
	
	//Jurassic Park Building Blocks
	public static final Block LIGHT_CONCRETE = BlockAndItemBuilder.create("light_concrete", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_STAIRS = BlockAndItemBuilder.create("light_concrete_stairs", new StairsBlock(() -> BlockInit.LIGHT_CONCRETE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_SLAB = BlockAndItemBuilder.create("light_concrete_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_WALL = BlockAndItemBuilder.create("light_concrete_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_PRESSURE_PLATE = BlockAndItemBuilder.create("light_concrete_pressure_plate", new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_BUTTON = BlockAndItemBuilder.create("light_concrete_button", new StoneButtonBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)));

	public static final ConnectedTextureBlock POLISHED_LIGHT_CONCRETE = BlockAndItemBuilder.create("polished_light_concrete", new ConnectedTextureBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE), "polished_light_concrete", true));
	
	public static final Block ACCENT_LIGHT_CONCRETE = BlockAndItemBuilder.create("accent_light_concrete", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE)));	
	public static final Block ACCENT_LIGHT_CONCRETE_STAIRS = BlockAndItemBuilder.create("accent_light_concrete_stairs", new StairsBlock(() -> BlockInit.LIGHT_CONCRETE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE)));
	public static final Block ACCENT_LIGHT_CONCRETE_SLAB = BlockAndItemBuilder.create("accent_light_concrete_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE)));

	public static final Block WOODEN_PLANKS = BlockAndItemBuilder.create("wooden_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_STAIRS = BlockAndItemBuilder.create("wooden_planks_stairs", new StairsBlock(() -> BlockInit.WOODEN_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_SLAB = BlockAndItemBuilder.create("wooden_planks_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_FENCE = BlockAndItemBuilder.create("wooden_planks_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_FENCE_GATE = BlockAndItemBuilder.create("wooden_planks_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_PRESSURE_PLATE = BlockAndItemBuilder.create("wooden_planks_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).noCollission().sound(SoundType.WOOD)));
	public static final Block WOODEN_PLANKS_BUTTON = BlockAndItemBuilder.create("wooden_planks_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).noCollission().sound(SoundType.WOOD)));

	public static final Block METAL_FENCE = BlockAndItemBuilder.create("metal_fence", new FenceBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_RED).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(4.5F, 6.0F).sound(SoundType.METAL)));

	public static final Block THATCH_BUNDLE = BlockAndItemBuilder.create("thatch_bundle", new HayBlock(AbstractBlock.Properties.copy(Blocks.HAY_BLOCK)));

	public static final Block OUTDOOR_TOILET_DOOR = BlockAndItemBuilder.create("outdoor_toilet_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
	public static final Block VISITOR_CENTER_DOOR = BlockAndItemBuilder.create("visitor_center_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));	
	public static final Block SECURITY_DOOR = BlockAndItemBuilder.create("security_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.IRON_DOOR)));
	
	//Jurassic World Building Blocks
	public static final Block DARK_CONCRETE = BlockAndItemBuilder.create("dark_concrete", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)));
	public static final Block DARK_CONCRETE_STAIRS = BlockAndItemBuilder.create("dark_concrete_stairs", new StairsBlock(() -> BlockInit.DARK_CONCRETE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)));
	public static final Block DARK_CONCRETE_SLAB = BlockAndItemBuilder.create("dark_concrete_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)));
	public static final Block DARK_CONCRETE_WALL = BlockAndItemBuilder.create("dark_concrete_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)));
	public static final Block DARK_CONCRETE_PRESSURE_PLATE = BlockAndItemBuilder.create("dark_concrete_pressure_plate", new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).noCollission().sound(SoundType.STONE)));
	public static final Block DARK_CONCRETE_BUTTON = BlockAndItemBuilder.create("dark_concrete_button", new StoneButtonBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).noCollission().sound(SoundType.STONE)));
	
	public static final ConnectedTextureBlock POLISHED_DARK_CONCRETE = BlockAndItemBuilder.create("polished_dark_concrete", new ConnectedTextureBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE), "polished_dark_concrete", true));

	public static final Block ACCENT_DARK_CONCRETE = BlockAndItemBuilder.create("accent_dark_concrete", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.5F, 10.5F).sound(SoundType.STONE)));
	public static final Block ACCENT_DARK_CONCRETE_STAIRS = BlockAndItemBuilder.create("accent_dark_concrete_stairs", new StairsBlock(() -> BlockInit.DARK_CONCRETE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.5F, 10.5F).sound(SoundType.STONE)));
	public static final Block ACCENT_DARK_CONCRETE_SLAB = BlockAndItemBuilder.create("accent_dark_concrete_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.5F, 10.5F).sound(SoundType.STONE)));

	public static final Block REFINED_WOODEN_PLANKS = BlockAndItemBuilder.create("refined_wooden_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_STAIRS = BlockAndItemBuilder.create("refined_wooden_planks_stairs", new StairsBlock(() -> BlockInit.REFINED_WOODEN_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_SLAB = BlockAndItemBuilder.create("refined_wooden_planks_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_FENCE = BlockAndItemBuilder.create("refined_wooden_planks_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_FENCE_GATE = BlockAndItemBuilder.create("refined_wooden_planks_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_PRESSURE_PLATE = BlockAndItemBuilder.create("refined_wooden_planks_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).noCollission().sound(SoundType.WOOD)));
	public static final Block REFINED_WOODEN_PLANKS_BUTTON = BlockAndItemBuilder.create("refined_wooden_planks_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).noCollission().sound(SoundType.WOOD)));

	public static final Block TILE = BlockAndItemBuilder.create("tile", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block TILE_STAIRS = BlockAndItemBuilder.create("tile_stairs", new StairsBlock(() -> BlockInit.TILE.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	public static final Block TILE_SLAB = BlockAndItemBuilder.create("tile_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)));
	
	public static final Block GLASS_SIGN = BlockBuilder.create("glass_sign", new ModStandingSignBlock(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_BLACK).instabreak().noOcclusion().noCollission().sound(SoundType.GLASS), ModWoodType.GLASS));
	public static final Block GLASS_WALL_SIGN = BlockBuilder.create("glass_wall_sign", new ModWallSignBlock(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_BLACK).instabreak().noOcclusion().noCollission().sound(SoundType.GLASS).dropsLike(BlockInit.GLASS_SIGN), ModWoodType.GLASS));
	public static final Item GLASS_SIGN_ITEM = ModRegistry.register("glass_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), GLASS_SIGN, GLASS_WALL_SIGN));

	public static final Block GLASS_SHOP_DOOR = BlockAndItemBuilder.create("glass_shop_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));	
	public static final Block INNOVATION_CENTER_DOOR = BlockAndItemBuilder.create("innovation_center_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
	public static final Block BACK_DOOR = BlockAndItemBuilder.create("back_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.IRON_DOOR)));

	//Mixed Park
	public static final Block PAVEMENT = BlockAndItemBuilder.create("pavement", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.STONE)));
	public static final Block PAVEMENT_SLOPE = BlockAndItemBuilder.create("pavement_slope", new StairsBlock(() -> BlockInit.PAVEMENT.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.STONE)));
	public static final Block RAISED_PAVEMENT = BlockAndItemBuilder.create("raised_pavement", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
	
	public static final Block GRAVEL_ROAD = BlockAndItemBuilder.create("gravel_road", new Block(AbstractBlock.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.SHOVEL).harvestLevel(0).strength(1.5F).sound(SoundType.SAND)));
	public static final Block RAISED_GRAVEL_ROAD = BlockAndItemBuilder.create("raised_gravel_road", new SlabBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.SHOVEL).harvestLevel(0).strength(1.5F).sound(SoundType.SAND)));
	
	public static final Block DIRT_ROAD = BlockAndItemBuilder.create("dirt_road", new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).harvestTool(ToolType.SHOVEL).harvestLevel(0).strength(1.5F).sound(SoundType.GRASS)));
	public static final Block RAISED_DIRT_ROAD = BlockAndItemBuilder.create("raised_dirt_road", new SlabBlock(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).harvestTool(ToolType.SHOVEL).harvestLevel(0).strength(1.5F).sound(SoundType.GRASS)));
	
	public static final Block PAVED_ROAD = BlockAndItemBuilder.create("paved_road", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.STONE)));
	public static final Block RAISED_PAVED_ROAD = BlockAndItemBuilder.create("raised_paved_road", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.STONE)));
	
	public static void init() 
	{
		ModUtils.LOGGER.debug("Registering Mod Blocks"); 
		
		ColouredGlassBlock.create();
		ColouredGlassPaneBlock.create();
		ColouredDecorationBlock.create();
	}
}
