package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.block.AnalyzerBlock;
import lostworlds.library.block.ArchaeologyTableBlock;
import lostworlds.library.block.BrazileaBlock;
import lostworlds.library.block.CalamtiesSuckowiiBlock;
import lostworlds.library.block.CalamtiesSuckowiiSaplingBlock;
import lostworlds.library.block.ColouredDecorationBlock;
import lostworlds.library.block.ColouredGlassBlock;
import lostworlds.library.block.ColouredGlassPaneBlock;
import lostworlds.library.block.CultivatorBlock;
import lostworlds.library.block.CycadBlock;
import lostworlds.library.block.DNAExtractorBlock;
import lostworlds.library.block.DNAInjectorBlock;
import lostworlds.library.block.DeadSpongeColonyBlock;
import lostworlds.library.block.DiictodonBurrowBlock;
import lostworlds.library.block.DisplayCaseBlock;
import lostworlds.library.block.DriedSoilBlock;
import lostworlds.library.block.FeedingTroughBlock;
import lostworlds.library.block.FogBlock;
import lostworlds.library.block.FossilCleanerBlock;
import lostworlds.library.block.FossilGrinderBlock;
import lostworlds.library.block.FossilizedTrackBlock;
import lostworlds.library.block.GeyserBlock;
import lostworlds.library.block.GroundFernsBlock;
import lostworlds.library.block.LargeFossilisedEggBlock;
import lostworlds.library.block.LargePlasteredFossilizedEggBlock;
import lostworlds.library.block.MediumFossilisedEggBlock;
import lostworlds.library.block.MediumPlasteredFossilizedEggBlock;
import lostworlds.library.block.ModBushBlock;
import lostworlds.library.block.ModDoublePlantBlock;
import lostworlds.library.block.ModOreRotatedPillerBlock;
import lostworlds.library.block.ModWoodType;
import lostworlds.library.block.MossySoilBlock;
import lostworlds.library.block.NautilusShellBlock;
import lostworlds.library.block.PaleobotanyTableBlock;
import lostworlds.library.block.PaleontologyTableBlock;
import lostworlds.library.block.PetrifiedWoodBlock;
import lostworlds.library.block.PlantFossilBlock;
import lostworlds.library.block.PlasteredBlock;
import lostworlds.library.block.SmallFossilizedEggBlock;
import lostworlds.library.block.SmallPlasteredFossilizedEggBlock;
import lostworlds.library.block.SoftDirtBlock;
import lostworlds.library.block.SoftStoneBlock;
import lostworlds.library.block.SpongeColonyBlock;
import lostworlds.library.block.TimeMachineBlock;
import lostworlds.library.block.TinyFossilizedEggBlock;
import lostworlds.library.block.TinyPlasteredFossilizedEggBlock;
import lostworlds.library.block.VolcanicAshBlock;
import lostworlds.library.block.VolcanicAshLayerBlock;
import lostworlds.library.block.WaterPlantBlock;
import lostworlds.library.block.builder.BlockAndItemBuilder;
import lostworlds.library.block.builder.BlockBuilder;
import lostworlds.library.block.tree.AraucariaTree;
import lostworlds.library.block.tree.CalamitesTree;
import lostworlds.library.block.tree.ConiferTree;
import lostworlds.library.block.tree.CypressTree;
import lostworlds.library.block.tree.GinkgoTree;
import lostworlds.library.block.tree.SequoiaTree;
import lostworlds.library.entity.utils.enums.DinoTypes;
import lostworlds.library.entity.utils.enums.ModBoatType;
import lostworlds.library.item.ModBoatItem;
import lostworlds.library.item.tool.ModMaterials;
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

public class BlockInit 
{		
	//Soils
	public static final Block DRIED_SOIL = BlockAndItemBuilder.create("dried_soil", new DriedSoilBlock(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.75F).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL).randomTicks()));
	public static final Block CRACKED_SOIL = BlockAndItemBuilder.create("cracked_soil", new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.75F).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL)));

	public static final Block MOSSY_SOIL = MossySoilBlock.create();
	public static final Block MUD = BlockAndItemBuilder.create("mud", new Block(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.6F).sound(SoundType.GRAVEL)));
	public static final Block SILT = BlockAndItemBuilder.create("silt", new Block(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.6F).sound(SoundType.GRAVEL)));

	public static final Block VOLCANIC_ASH = BlockAndItemBuilder.create("volcanic_ash", new VolcanicAshBlock());
	public static final Block VOLCANIC_ASH_LAYER = BlockAndItemBuilder.create("volcanic_ash_layer", new VolcanicAshLayerBlock());

	public static final Block PERMIAN_SAND = BlockAndItemBuilder.create("permian_sand", new SandBlock(0xaa915c, AbstractBlock.Properties.of(Material.SAND, MaterialColor.SAND).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND)));

	public static final Block ROCKY_SOIL = BlockAndItemBuilder.create("rocky_soil", new SandBlock(0x8a8a8e, AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).harvestTool(ToolType.SHOVEL).requiresCorrectToolForDrops().strength(1.5F)));

	public static final Block FOG = BlockAndItemBuilder.create("fog", new FogBlock(AbstractBlock.Properties.of(Material.STONE).instabreak().noOcclusion().noCollission().sound(SoundType.WOOL)));

	public static final Block PERMAFROST = BlockAndItemBuilder.create("permafrost", new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.ICE).strength(0.5F).sound(SoundType.GRAVEL)));

	//Stones
	public static final Block PERMIAN_STONE = BlockAndItemBuilder.create("permian_stone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_STAIRS = BlockAndItemBuilder.create("permian_stone_stairs", new StairsBlock(() -> BlockInit.PERMIAN_STONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_SLAB = BlockAndItemBuilder.create("permian_stone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_PRESSURE_PLATE = BlockAndItemBuilder.create("permian_stone_pressure_plate", new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)));
	public static final Block PERMIAN_STONE_BUTTON = BlockAndItemBuilder.create("permian_stone_button", new StoneButtonBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)));

	public static final Block PERMIAN_COPPER_ORE = BlockAndItemBuilder.create("permian_copper_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block PERMIAN_GOLD_ORE = BlockAndItemBuilder.create("permian_gold_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block PERMIAN_IRON_ORE = BlockAndItemBuilder.create("permian_iron_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block PERMIAN_COAL_ORE = BlockAndItemBuilder.create("permian_coal_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 2));
	public static final Block PERMIAN_LAPIS_ORE = BlockAndItemBuilder.create("permian_lapis_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 2, 5));	
	public static final Block PERMIAN_DIAMOND_ORE = BlockAndItemBuilder.create("permian_diamond_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 3, 7));	
	public static final Block PERMIAN_REDSTONE_ORE = BlockAndItemBuilder.create("permian_redstone_ore", new RedstoneOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));	
	public static final Block PERMIAN_EMERALD_ORE = BlockAndItemBuilder.create("permian_emerald_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 3, 7));
	
	public static final Block PERMIAN_COBBLESTONE = BlockAndItemBuilder.create("permian_cobblestone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_COBBLESTONE_STAIRS = BlockAndItemBuilder.create("permian_cobblestone_stairs", new StairsBlock(() -> BlockInit.PERMIAN_STONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_COBBLESTONE_SLAB = BlockAndItemBuilder.create("permian_cobblestone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_COBBLESTONE_WALL = BlockAndItemBuilder.create("permian_cobblestone_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	
	public static final Block PERMIAN_STONE_BRICKS = BlockAndItemBuilder.create("permian_stone_bricks", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_BRICK_STAIRS = BlockAndItemBuilder.create("permian_stone_brick_stairs", new StairsBlock(() -> BlockInit.PERMIAN_STONE_BRICKS.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_BRICK_SLAB = BlockAndItemBuilder.create("permian_stone_brick_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block PERMIAN_STONE_BRICK_WALL = BlockAndItemBuilder.create("permian_stone_brick_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final Block JURASSIC_STONE = BlockAndItemBuilder.create("jurassic_stone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_STONE_STAIRS = BlockAndItemBuilder.create("jurassic_stone_stairs", new StairsBlock(() -> BlockInit.JURASSIC_STONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_STONE_SLAB = BlockAndItemBuilder.create("jurassic_stone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_STONE_PRESSURE_PLATE = BlockAndItemBuilder.create("jurassic_stone_pressure_plate", new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)));
	public static final Block JURASSIC_STONE_BUTTON = BlockAndItemBuilder.create("jurassic_stone_button", new StoneButtonBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)));
	
	public static final Block JURASSIC_COPPER_ORE = BlockAndItemBuilder.create("jurassic_copper_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block JURASSIC_GOLD_ORE = BlockAndItemBuilder.create("jurassic_gold_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block JURASSIC_IRON_ORE = BlockAndItemBuilder.create("jurassic_iron_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 0));
	public static final Block JURASSIC_COAL_ORE = BlockAndItemBuilder.create("jurassic_coal_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 0, 2));
	public static final Block JURASSIC_LAPIS_ORE = BlockAndItemBuilder.create("jurassic_lapis_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 2, 5));	
	public static final Block JURASSIC_DIAMOND_ORE = BlockAndItemBuilder.create("jurassic_diamond_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 3, 7));	
	public static final Block JURASSIC_REDSTONE_ORE = BlockAndItemBuilder.create("jurassic_redstone_ore", new RedstoneOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));	
	public static final Block JURASSIC_EMERALD_ORE = BlockAndItemBuilder.create("jurassic_emerald_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), 3, 7));	
	
	public static final Block JURASSIC_COBBLESTONE = BlockAndItemBuilder.create("jurassic_cobblestone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_COBBLESTONE_STAIRS = BlockAndItemBuilder.create("jurassic_cobblestone_stairs", new StairsBlock(() -> BlockInit.JURASSIC_COBBLESTONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_COBBLESTONE_SLAB = BlockAndItemBuilder.create("jurassic_cobblestone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	public static final Block JURASSIC_COBBLESTONE_WALL = BlockAndItemBuilder.create("jurassic_cobblestone_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)));
	
	public static final Block JURASSIC_STONE_BRICKS = BlockAndItemBuilder.create("jurassic_stone_bricks", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block JURASSIC_STONE_BRICK_STAIRS = BlockAndItemBuilder.create("jurassic_stone_brick_stairs", new StairsBlock(() -> BlockInit.JURASSIC_STONE_BRICKS.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block JURASSIC_STONE_BRICK_SLAB = BlockAndItemBuilder.create("jurassic_stone_brick_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final Block JURASSIC_STONE_BRICK_WALL = BlockAndItemBuilder.create("jurassic_stone_brick_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	
	public static final Block METEORIC_STONE = BlockAndItemBuilder.create("meteoric_stone", new Block(AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
	public static final Block METEORIC_STONE_STAIRS = BlockAndItemBuilder.create("meteoric_stone_stairs", new StairsBlock(() -> BlockInit.METEORIC_STONE.defaultBlockState(), AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
	public static final Block METEORIC_STONE_SLAB = BlockAndItemBuilder.create("meteoric_stone_slab", new SlabBlock(AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
	public static final Block METEORIC_STONE_WALL = BlockAndItemBuilder.create("meteoric_stone_wall", new WallBlock(AbstractBlock.Properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)));
	
	public static final Block LATERLITE = BlockAndItemBuilder.create("laterlite", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LATERLITE_STAIRS = BlockAndItemBuilder.create("laterlite_stairs", new StairsBlock(() -> BlockInit.LATERLITE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LATERLITE_SLAB = BlockAndItemBuilder.create("laterlite_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LATERLITE_WALL = BlockAndItemBuilder.create("laterlite_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	
	public static final Block POLISHED_LATERLITE = BlockAndItemBuilder.create("polished_laterlite", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block POLISHED_LATERLITE_STAIRS = BlockAndItemBuilder.create("polished_laterlite_stairs", new StairsBlock(() -> BlockInit.LATERLITE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block POLISHED_LATERLITE_SLAB = BlockAndItemBuilder.create("polished_laterlite_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

	public static final Block RAW_MARBLE = BlockAndItemBuilder.create("raw_marble", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block RAW_MARBLE_STAIRS = BlockAndItemBuilder.create("raw_marble_stairs", new StairsBlock(() -> BlockInit.RAW_MARBLE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block RAW_MARBLE_SLAB = BlockAndItemBuilder.create("raw_marble_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block RAW_MARBLE_WALL = BlockAndItemBuilder.create("raw_marble_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	
	public static final Block MARBLE = BlockAndItemBuilder.create("marble", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block MARBLE_STAIRS = BlockAndItemBuilder.create("marble_stairs", new StairsBlock(() -> BlockInit.MARBLE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block MARBLE_SLAB = BlockAndItemBuilder.create("marble_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	
	public static final Block LIMESTONE = BlockAndItemBuilder.create("limestone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LIMESTONE_STAIRS = BlockAndItemBuilder.create("limestone_stairs", new StairsBlock(() -> BlockInit.LIMESTONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LIMESTONE_SLAB = BlockAndItemBuilder.create("limestone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block LIMESTONE_WALL = BlockAndItemBuilder.create("limestone_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	
	public static final Block POLISHED_LIMESTONE = BlockAndItemBuilder.create("polished_limestone", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block POLISHED_LIMESTONE_STAIRS = BlockAndItemBuilder.create("polished_limestone_stairs", new StairsBlock(() -> BlockInit.POLISHED_LIMESTONE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final Block POLISHED_LIMESTONE_SLAB = BlockAndItemBuilder.create("polished_limestone_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	
	public static final Block VOLCANIC_ROCK = BlockAndItemBuilder.create("volcanic_rock", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block VOLCANIC_ROCK_STAIRS = BlockAndItemBuilder.create("volcanic_rock_stairs", new StairsBlock(VOLCANIC_ROCK.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block VOLCANIC_ROCK_SLAB = BlockAndItemBuilder.create("volcanic_rock_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block VOLCANIC_ROCK_WALL = BlockAndItemBuilder.create("volcanic_rock_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	
	public static final Block POLISHED_VOLCANIC_ROCK = BlockAndItemBuilder.create("polished_volcanic_rock", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block POLISHED_VOLCANIC_ROCK_STAIRS = BlockAndItemBuilder.create("polished_volcanic_rock_stairs", new StairsBlock(POLISHED_VOLCANIC_ROCK.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	public static final Block POLISHED_VOLCANIC_ROCK_SLAB = BlockAndItemBuilder.create("polished_volcanic_rock_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)));
	
	public static final Block SOFT_STONE = BlockAndItemBuilder.create("soft_stone", new SoftStoneBlock());
	public static final Block SOFT_DIRT = BlockAndItemBuilder.create("soft_dirt", new SoftDirtBlock());
	public static final Block PLANT_FOSSIL = BlockAndItemBuilder.create("plant_fossil", new PlantFossilBlock());
		
	public static final Block FOSSILIZED_TRACK = BlockAndItemBuilder.create("fossilized_track", new FossilizedTrackBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F), Blocks.STONE));
	public static final Block PLASTERED_FOSSILIZED_TRACK = BlockAndItemBuilder.create("plastered_fossilized_track", new PlasteredBlock(AbstractBlock.Properties.of(Material.STONE).instabreak().sound(SoundType.WOOL), FOSSILIZED_TRACK));
	
	public static final Block TINY_FOSSILISED_EGG = BlockAndItemBuilder.create("tiny_fossilized_egg", new TinyFossilizedEggBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()));
	public static final Block TINY_PLASTERED_FOSSILISED_EGG = BlockAndItemBuilder.create("tiny_plastered_fossilized_egg", new TinyPlasteredFossilizedEggBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()));
	public static final Block SMALL_FOSSILISED_EGG = BlockAndItemBuilder.create("small_fossilized_egg", new SmallFossilizedEggBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()));
	public static final Block SMALL_PLASTERED_FOSSILISED_EGG = BlockAndItemBuilder.create("small_plastered_fossilized_egg", new SmallPlasteredFossilizedEggBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()));
	public static final Block MEDIUM_FOSSILISED_EGG = BlockAndItemBuilder.create("medium_fossilized_egg", new MediumFossilisedEggBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()));
	public static final Block MEDIUM_PLASTERED_FOSSILISED_EGG = BlockAndItemBuilder.create("medium_plastered_fossilized_egg", new MediumPlasteredFossilizedEggBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()));
	public static final Block LARGE_FOSSILISED_EGG = BlockAndItemBuilder.create("large_fossilized_egg", new LargeFossilisedEggBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()));
	public static final Block LARGE_PLASTERED_FOSSILISED_EGG = BlockAndItemBuilder.create("large_plastered_fossilized_egg", new LargePlasteredFossilizedEggBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()));
	
	//Overworld Ores
	public static final Block AMBER_ORE = BlockAndItemBuilder.create("amber_ore", new TyrannoOreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F), 0, 0));
	public static final Block BASALT_DIAMOND_ORE = BlockAndItemBuilder.create("basalt_diamond_ore", new ModOreRotatedPillerBlock(AbstractBlock.Properties.copy(Blocks.BASALT)));

	//Machines
	public static final Block FOSSIL_CLEANER = FossilCleanerBlock.create();
	public static final Block FOSSIL_GRINDER = FossilGrinderBlock.create();
	public static final Block DNA_EXTRACTOR = DNAExtractorBlock.create();
	public static final Block ANALYZER = AnalyzerBlock.create();
	public static final Block DNA_INJECTOR = DNAInjectorBlock.create();
	public static final Block CULTIVATOR = CultivatorBlock.create();
		
	public static final Block ARCHAEOLOGY_TABLE = ArchaeologyTableBlock.create("oak");
	
	public static final Block OAK_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("oak");
	public static final Block SPRUCE_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("spruce");
	public static final Block BIRCH_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("birch");
	public static final Block JUNGLE_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("jungle");
	public static final Block ACACIA_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("acacia");
	public static final Block DARK_OAK_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("dark_oak");
	public static final Block CRIMSON_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("crimson");
	public static final Block WARPED_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("warped");
	public static final Block ARAUCARIA_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("araucaria");
	public static final Block CALAMITES_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("calamites");
	public static final Block CONIFER_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("conifer");
	public static final Block CYPRESS_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("cypress");
	public static final Block GINKGO_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("ginkgo");
	public static final Block SCORCHED_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("scorched");
	public static final Block SEQUOIA_PALEONTOLOGY_TABLE = PaleontologyTableBlock.create("sequoia");

	public static final Block PALEOBOTANY_TABLE = PaleobotanyTableBlock.create();

	public static final Block TIME_MACHINE = TimeMachineBlock.create();
			
	public static final Block FEEDING_TROUGH = BlockAndItemBuilder.create("feeding_trough", new FeedingTroughBlock(AbstractBlock.Properties.of(Material.WOOD).harvestTool(ToolType.AXE).strength(2.5F).sound(SoundType.WOOD)));
	
	//Museum Blocks
	public static final Block DISPLAY_CASE = BlockAndItemBuilder.create("display_case", new DisplayCaseBlock(AbstractBlock.Properties.of(Material.STONE).harvestLevel(1).requiresCorrectToolForDrops().noOcclusion().strength(4.0F, 5.0F).sound(SoundType.GLASS)));
	
	//Wood
	
	//Araucaria
	public static final Block ARAUCARIA_LOG = BlockAndItemBuilder.create("araucaria_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_ARAUCARIA_LOG = BlockAndItemBuilder.create("stripped_araucaria_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_WOOD = BlockAndItemBuilder.create("araucaria_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_ARAUCARIA_WOOD = BlockAndItemBuilder.create("stripped_araucaria_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_LEAVES = BlockAndItemBuilder.create("araucaria_leaves", leaves());
	public static final Block ARAUCARIA_SAPLING = BlockAndItemBuilder.create("araucaria_sapling", new TyrannoSaplingBlock(new AraucariaTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block ARAUCARIA_PLANKS = BlockAndItemBuilder.create("araucaria_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_STAIRS = BlockAndItemBuilder.create("araucaria_stairs", new StairsBlock(() -> BlockInit.ARAUCARIA_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_SLAB = BlockAndItemBuilder.create("araucaria_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_FENCE = BlockAndItemBuilder.create("araucaria_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_FENCE_GATE = BlockAndItemBuilder.create("araucaria_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_PRESSURE_PLATE = BlockAndItemBuilder.create("araucaria_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_BUTTON = BlockAndItemBuilder.create("araucaria_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_TRAPDOOR = BlockAndItemBuilder.create("araucaria_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_DOOR = BlockAndItemBuilder.create("araucaria_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block ARAUCARIA_SIGN = BlockBuilder.create("araucaria_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.ARAUCARIA));
	public static final Block ARAUCARIA_WALL_SIGN = BlockBuilder.create("araucaria_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.ARAUCARIA_SIGN), ModWoodType.ARAUCARIA));
	public static final Item ARAUCARIA_SIGN_ITEM = ModRegistry.register("araucaria_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), ARAUCARIA_SIGN, ARAUCARIA_WALL_SIGN));
	public static final Item ARAUCARIA_BOAT = ModRegistry.register("araucaria_boat", new ModBoatItem(ModBoatType.ARAUCARIA));
	
	public static final Block PETRIFIED_ARAUCARIA_LOG = BlockAndItemBuilder.create("petrified_araucaria_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), ItemInit.ARAUCARIA_BARK_SAMPLE));
	public static final Block STRIPPED_PETRIFIED_ARAUCARIA_LOG = BlockAndItemBuilder.create("stripped_petrified_araucaria_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));
	
	//Calamite
	public static final Block CALAMITES_LOG = BlockAndItemBuilder.create("calamites_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CALAMITES_LOG = BlockAndItemBuilder.create("stripped_calamites_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_WOOD = BlockAndItemBuilder.create("calamites_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CALAMITES_WOOD = BlockAndItemBuilder.create("stripped_calamites_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_LEAVES = BlockAndItemBuilder.create("calamites_leaves", leaves());
	public static final Block CALAMITES_SAPLING = BlockAndItemBuilder.create("calamites_sapling", new TyrannoSaplingBlock(new CalamitesTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block CALAMITES_PLANKS = BlockAndItemBuilder.create("calamites_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_STAIRS = BlockAndItemBuilder.create("calamites_stairs", new StairsBlock(() -> BlockInit.CONIFER_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_SLAB = BlockAndItemBuilder.create("calamites_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_FENCE = BlockAndItemBuilder.create("calamites_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_FENCE_GATE = BlockAndItemBuilder.create("calamites_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CALAMITES_PRESSURE_PLATE = BlockAndItemBuilder.create("calamites_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CALAMITES_BUTTON = BlockAndItemBuilder.create("calamites_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CALAMITES_TRAPDOOR = BlockAndItemBuilder.create("calamites_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CALAMITES_DOOR = BlockAndItemBuilder.create("calamites_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CALAMITES_SIGN = BlockBuilder.create("calamites_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.CALAMITES));
	public static final Block CALAMITES_WALL_SIGN = BlockBuilder.create("calamites_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.CALAMITES_SIGN), ModWoodType.CALAMITES));
	public static final Item CALAMITES_SIGN_ITEM = ModRegistry.register("calamites_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), CALAMITES_SIGN, CALAMITES_WALL_SIGN));
	public static final Item CALAMITES_BOAT = ModRegistry.register("calamites_boat", new ModBoatItem(ModBoatType.CALAMITES));
	
	public static final Block PETRIFIED_CALAMITES_LOG = BlockAndItemBuilder.create("petrified_calamites_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), ItemInit.CALAMITES_BARK_SAMPLE));
	public static final Block STRIPPED_PETRIFIED_CALAMITES_LOG = BlockAndItemBuilder.create("stripped_petrified_calamites_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));
	
	//Conifer
	public static final Block CONIFER_LOG = BlockAndItemBuilder.create("conifer_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CONIFER_LOG = BlockAndItemBuilder.create("stripped_conifer_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_WOOD = BlockAndItemBuilder.create("conifer_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CONIFER_WOOD = BlockAndItemBuilder.create("stripped_conifer_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_LEAVES = BlockAndItemBuilder.create("conifer_leaves", leaves());
	public static final Block CONIFER_SAPLING = BlockAndItemBuilder.create("conifer_sapling", new TyrannoSaplingBlock(new ConiferTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block CONIFER_PLANKS = BlockAndItemBuilder.create("conifer_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_STAIRS = BlockAndItemBuilder.create("conifer_stairs", new StairsBlock(() -> BlockInit.CONIFER_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_SLAB = BlockAndItemBuilder.create("conifer_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_FENCE = BlockAndItemBuilder.create("conifer_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_FENCE_GATE = BlockAndItemBuilder.create("conifer_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CONIFER_PRESSURE_PLATE = BlockAndItemBuilder.create("conifer_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CONIFER_BUTTON = BlockAndItemBuilder.create("conifer_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CONIFER_TRAPDOOR = BlockAndItemBuilder.create("conifer_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CONIFER_DOOR = BlockAndItemBuilder.create("conifer_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CONIFER_SIGN = BlockBuilder.create("conifer_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.CONIFER));
	public static final Block CONIFER_WALL_SIGN = BlockBuilder.create("conifer_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.CONIFER_SIGN), ModWoodType.CONIFER));
	public static final Item CONIFER_SIGN_ITEM = ModRegistry.register("conifer_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), CONIFER_SIGN, CONIFER_WALL_SIGN));
	public static final Item CONIFER_BOAT = ModRegistry.register("conifer_boat", new ModBoatItem(ModBoatType.CONIFER));
	
	public static final Block PETRIFIED_CONIFER_LOG = BlockAndItemBuilder.create("petrified_conifer_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), ItemInit.CONIFER_BARK_SAMPLE));
	public static final Block STRIPPED_PETRIFIED_CONIFER_LOG = BlockAndItemBuilder.create("stripped_petrified_conifer_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));

	//Cypress
	public static final Block CYPRESS_LOG = BlockAndItemBuilder.create("cypress_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CYPRESS_LOG = BlockAndItemBuilder.create("stripped_cypress_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_WOOD = BlockAndItemBuilder.create("cypress_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_CYPRESS_WOOD = BlockAndItemBuilder.create("stripped_cypress_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_LEAVES = BlockAndItemBuilder.create("cypress_leaves", leaves());
	public static final Block CYPRESS_SAPLING = BlockAndItemBuilder.create("cypress_sapling", new TyrannoSaplingBlock(new CypressTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block CYPRESS_PLANKS = BlockAndItemBuilder.create("cypress_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_STAIRS = BlockAndItemBuilder.create("cypress_stairs", new StairsBlock(() -> BlockInit.CYPRESS_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_SLAB = BlockAndItemBuilder.create("cypress_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_FENCE = BlockAndItemBuilder.create("cypress_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_FENCE_GATE = BlockAndItemBuilder.create("cypress_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block CYPRESS_PRESSURE_PLATE = BlockAndItemBuilder.create("cypress_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CYPRESS_BUTTON = BlockAndItemBuilder.create("cypress_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block CYPRESS_TRAPDOOR = BlockAndItemBuilder.create("cypress_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CYPRESS_DOOR = BlockAndItemBuilder.create("cypress_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block CYPRESS_SIGN = BlockBuilder.create("cypress_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.CYPRESS));
	public static final Block CYPRESS_WALL_SIGN = BlockBuilder.create("cypress_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.CYPRESS_SIGN), ModWoodType.CYPRESS));
	public static final Item CYPRESS_SIGN_ITEM = ModRegistry.register("cypress_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), CYPRESS_SIGN, CYPRESS_WALL_SIGN));
	public static final Item CYPRESS_BOAT = ModRegistry.register("cypress_boat", new ModBoatItem(ModBoatType.CYPRESS));
	
	public static final Block PETRIFIED_CYPRESS_LOG = BlockAndItemBuilder.create("petrified_cypress_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), ItemInit.CYPRESS_BARK_SAMPLE));
	public static final Block STRIPPED_PETRIFIED_CYPRESS_LOG = BlockAndItemBuilder.create("stripped_petrified_cypress_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));
	
	//Ginkgo
	public static final Block GINKGO_LOG = BlockAndItemBuilder.create("ginkgo_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_GINKGO_LOG = BlockAndItemBuilder.create("stripped_ginkgo_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_WOOD = BlockAndItemBuilder.create("ginkgo_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_GINKGO_WOOD = BlockAndItemBuilder.create("stripped_ginkgo_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_LEAVES = BlockAndItemBuilder.create("ginkgo_leaves", leaves());
	public static final Block GINKGO_SAPLING = BlockAndItemBuilder.create("ginkgo_sapling", new TyrannoSaplingBlock(new GinkgoTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block GINKGO_PLANKS = BlockAndItemBuilder.create("ginkgo_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_STAIRS = BlockAndItemBuilder.create("ginkgo_stairs", new StairsBlock(() -> BlockInit.CONIFER_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_SLAB = BlockAndItemBuilder.create("ginkgo_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_FENCE = BlockAndItemBuilder.create("ginkgo_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_FENCE_GATE = BlockAndItemBuilder.create("ginkgo_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block GINKGO_PRESSURE_PLATE = BlockAndItemBuilder.create("ginkgo_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block GINKGO_BUTTON = BlockAndItemBuilder.create("ginkgo_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block GINKGO_TRAPDOOR = BlockAndItemBuilder.create("ginkgo_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block GINKGO_DOOR = BlockAndItemBuilder.create("ginkgo_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block GINKGO_SIGN = BlockBuilder.create("ginkgo_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.GINKGO));
	public static final Block GINKGO_WALL_SIGN = BlockBuilder.create("ginkgo_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.GINKGO_SIGN), ModWoodType.GINKGO));
	public static final Item GINKGO_SIGN_ITEM = ModRegistry.register("ginkgo_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), GINKGO_SIGN, GINKGO_WALL_SIGN));
	public static final Item GINKGO_BOAT = ModRegistry.register("ginkgo_boat", new ModBoatItem(ModBoatType.GINKGO));
	
	public static final Block PETRIFIED_GINKGO_LOG = BlockAndItemBuilder.create("petrified_ginkgo_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), ItemInit.GINKGO_BARK_SAMPLE));
	public static final Block STRIPPED_PETRIFIED_GINKGO_LOG = BlockAndItemBuilder.create("stripped_petrified_ginkgo_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));
	
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
	public static final Block SCORCHED_TRAPDOOR = BlockAndItemBuilder.create("scorched_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block SCORCHED_DOOR = BlockAndItemBuilder.create("scorched_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block SCORCHED_SIGN = BlockBuilder.create("scorched_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.SCORCHED));
	public static final Block SCORCHED_WALL_SIGN = BlockBuilder.create("scorched_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.SCORCHED_SIGN), ModWoodType.SCORCHED));
	public static final Item SCORCHED_SIGN_ITEM = ModRegistry.register("scorched_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), SCORCHED_SIGN, SCORCHED_WALL_SIGN));
	public static final Item SCORCHED_BOAT = ModRegistry.register("scorched_boat", new ModBoatItem(ModBoatType.SCORCHED));
	
	//Sequoia
	public static final Block SEQUOIA_LOG = BlockAndItemBuilder.create("sequoia_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_SEQUOIA_LOG = BlockAndItemBuilder.create("stripped_sequoia_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_WOOD = BlockAndItemBuilder.create("sequoia_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block STRIPPED_SEQUOIA_WOOD = BlockAndItemBuilder.create("stripped_sequoia_wood", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_LEAVES = BlockAndItemBuilder.create("sequoia_leaves", leaves());
	public static final Block SEQUOIA_SAPLING = BlockAndItemBuilder.create("sequoia_sapling", new TyrannoSaplingBlock(new SequoiaTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final Block SEQUOIA_PLANKS = BlockAndItemBuilder.create("sequoia_planks", new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_STAIRS = BlockAndItemBuilder.create("sequoia_stairs", new StairsBlock(() -> BlockInit.CONIFER_PLANKS.defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_SLAB = BlockAndItemBuilder.create("sequoia_slab", new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_FENCE = BlockAndItemBuilder.create("sequoia_fence", new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_FENCE_GATE = BlockAndItemBuilder.create("sequoia_fence_gate", new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final Block SEQUOIA_PRESSURE_PLATE = BlockAndItemBuilder.create("sequoia_pressure_plate", new PressurePlateBlock(Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block SEQUOIA_BUTTON = BlockAndItemBuilder.create("sequoia_button", new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)));
	public static final Block SEQUOIA_TRAPDOOR = BlockAndItemBuilder.create("sequoia_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block SEQUOIA_DOOR = BlockAndItemBuilder.create("sequoia_door", new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	public static final Block SEQUOIA_SIGN = BlockBuilder.create("sequoia_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD), ModWoodType.SEQUOIA));
	public static final Block SEQUOIA_WALL_SIGN = BlockBuilder.create("sequoia_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD).dropsLike(BlockInit.SEQUOIA_SIGN), ModWoodType.SEQUOIA));
	public static final Item SEQUOIA_SIGN_ITEM = ModRegistry.register("sequoia_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), SEQUOIA_SIGN, SEQUOIA_WALL_SIGN));
	public static final Item SEQUOIA_BOAT = ModRegistry.register("sequoia_boat", new ModBoatItem(ModBoatType.SEQUOIA));
	
	public static final Block PETRIFIED_SEQUOIA_LOG = BlockAndItemBuilder.create("petrified_sequoia_log", new PetrifiedWoodBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE), ItemInit.SEQUOIA_BARK_SAMPLE));
	public static final Block STRIPPED_PETRIFIED_SEQUOIA_LOG = BlockAndItemBuilder.create("stripped_petrified_sequoia_log", new RotatedPillarBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));
	
	//Sigillaria
	
	//Plants
	public static final Block ARCHAEFRUTUS = WaterPlantBlock.create("archaefrutus", new WaterPlantBlock(Effects.DAMAGE_BOOST, 3, AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block POTTED_ARCHAEFRUTUS = BlockBuilder.create("potted_archaefrutus", new FlowerPotBlock(ARCHAEFRUTUS, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
	public static final Block ALETHOPTERIS = BlockAndItemBuilder.create("alethopteris", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block BRAZILEA = BrazileaBlock.create("brazilea");
	public static final Block CALAMITES_SUCKOWII_SAPLING = BlockBuilder.create("calamites_suckowii_sapling", new CalamtiesSuckowiiSaplingBlock(AbstractBlock.Properties.of(Material.BAMBOO_SAPLING).randomTicks().instabreak().noCollission().strength(1.0F).sound(SoundType.BAMBOO_SAPLING)));
	public static final Block CALAMITES_SUCKOWII = BlockAndItemBuilder.create("calamites_suckowii", new CalamtiesSuckowiiBlock(AbstractBlock.Properties.of(Material.BAMBOO, MaterialColor.PLANT).randomTicks().instabreak().strength(1.0F).sound(SoundType.BAMBOO).noOcclusion()));
	public static final Block CEPHALOTAXUS = BlockAndItemBuilder.create("cephalotaxus", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block CYCAD = BlockAndItemBuilder.create("cycad", new CycadBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(1.0F, 1.5F).randomTicks().noOcclusion().sound(SoundType.WOOD)));
	public static final Block DICKSONIA = BlockAndItemBuilder.create("dicksonia", new CycadBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(1.0F, 1.5F).randomTicks().noOcclusion().sound(SoundType.WOOD)));
	public static final Block DILLHOFFIA = BlockAndItemBuilder.create("dillhoffia", new FlowerBlock(Effects.BLINDNESS, 7, AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block POTTED_DILLHOFFIA = BlockBuilder.create("potted_dillhoffia", new FlowerPotBlock(DILLHOFFIA, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
	public static final Block DUISBERGIA = BlockAndItemBuilder.create("duisbergia", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block EUDICOTS = BlockAndItemBuilder.create("eudicots", new FlowerBlock(Effects.HUNGER, 10, AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block POTTED_EUDICOTS = BlockBuilder.create("potted_eudicots", new FlowerPotBlock(EUDICOTS, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
	public static final Block GROUND_FERNS = BlockAndItemBuilder.create("ground_ferns", new GroundFernsBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block MAGNOLIA = BlockAndItemBuilder.create("magnolia", new FlowerBlock(Effects.MOVEMENT_SLOWDOWN, 8, AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block POTTED_MAGNOLIA = BlockBuilder.create("potted_magnolia", new FlowerPotBlock(MAGNOLIA, AbstractBlock.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
	public static final Block OSMUNDA = BlockAndItemBuilder.create("osmunda", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block PERMIAN_DESERT_FERNS = BlockAndItemBuilder.create("permian_desert_ferns", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block PERMIAN_DESERT_SHRUB = BlockAndItemBuilder.create("permian_desert_shrub", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_BROWN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	//public static final Block TEMPSKYA = BlockAndItemBuilder.create("tempskya", new QuintuplePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block WILLIAMSONIA = BlockAndItemBuilder.create("williamsonia", new ModDoublePlantBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	public static final Block ZAMITES = BlockAndItemBuilder.create("zamites", new ModBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)));
	
	//Natural Blocks
	public static final Block DIICTODON_BURROW = BlockAndItemBuilder.create("diictodon_burrow", new DiictodonBurrowBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.SAND).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND).randomTicks()));
	public static final Block TUNNELED_SOIL = BlockAndItemBuilder.create("tunneled_soil", new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL)));

	public static final Block NAUTILUS_SHELL = BlockBuilder.create("nautilus_shell", new NautilusShellBlock(AbstractBlock.Properties.of(Material.SHULKER_SHELL).instabreak().noOcclusion().sound(SoundType.ANVIL)));
	
	public static final Block GEYSER_BLOCK = BlockAndItemBuilder.create("geyser_block", new GeyserBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.75F).noOcclusion()));
	
	public static final Block NESTING_BLOCK = BlockAndItemBuilder.create("nesting_block", new Block(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.3F).noOcclusion().sound(SoundType.GRASS)));
	
	public static final Block DEAD_SPONGE_COLONY = BlockAndItemBuilder.create("dead_sponge_colony", new DeadSpongeColonyBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.STONE).noOcclusion().strength(1.5F, 6.0F).sound(SoundType.GRASS)));
	public static final Block SPONGE_COLONY = BlockAndItemBuilder.create("sponge_colony", new SpongeColonyBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().noOcclusion().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK), () -> BlockInit.DEAD_SPONGE_COLONY));
	
	//Jurassic Park Building Blocks
	public static final Block LIGHT_CONCRETE = BlockAndItemBuilder.create("light_concrete", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_STAIRS = BlockAndItemBuilder.create("light_concrete_stairs", new StairsBlock(() -> BlockInit.LIGHT_CONCRETE.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_SLAB = BlockAndItemBuilder.create("light_concrete_slab", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_WALL = BlockAndItemBuilder.create("light_concrete_wall", new WallBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_PRESSURE_PLATE = BlockAndItemBuilder.create("light_concrete_pressure_plate", new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)));
	public static final Block LIGHT_CONCRETE_BUTTON = BlockAndItemBuilder.create("light_concrete_button", new StoneButtonBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)));

	public static final TyrannoConnectedTextureBlock POLISHED_LIGHT_CONCRETE = BlockAndItemBuilder.create("polished_light_concrete", new TyrannoConnectedTextureBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE), ModUtils.ID, "polished_dark_concrete", true));
	
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
	
	public static final TyrannoConnectedTextureBlock POLISHED_DARK_CONCRETE = BlockAndItemBuilder.create("polished_dark_concrete", new TyrannoConnectedTextureBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE), ModUtils.ID, "polished_dark_concrete", true));

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
	
	public static final Block GLASS_SIGN = BlockBuilder.create("glass_sign", new StandingSignBlock(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_BLACK).instabreak().noOcclusion().noCollission().sound(SoundType.GLASS), ModWoodType.GLASS));
	public static final Block GLASS_WALL_SIGN = BlockBuilder.create("glass_wall_sign", new WallSignBlock(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_BLACK).instabreak().noOcclusion().noCollission().sound(SoundType.GLASS).dropsLike(BlockInit.GLASS_SIGN), ModWoodType.GLASS));
	public static final Item GLASS_SIGN_ITEM = ModRegistry.register("glass_sign", new SignItem(new Properties().tab(ModUtils.BLOCKS).stacksTo(16), GLASS_SIGN, GLASS_WALL_SIGN));

	public static final Block GLASS_SHOP_DOOR = BlockAndItemBuilder.create("glass_shop_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));	
	public static final Block INNOVATION_CENTER_DOOR = BlockAndItemBuilder.create("innovation_center_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)));
	public static final Block BACK_DOOR = BlockAndItemBuilder.create("back_door", new DoorBlock(AbstractBlock.Properties.copy(Blocks.IRON_DOOR)));

	//Mixed Park
	public static final Block PAVEMENT = BlockAndItemBuilder.create("pavement", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.STONE)));
	public static final Block PAVEMENT_SLOPE = BlockAndItemBuilder.create("pavement_slope", new StairsBlock(() -> BlockInit.PAVEMENT.defaultBlockState(), AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.STONE)));
	public static final Block RAISED_PAVEMENT = BlockAndItemBuilder.create("raised_pavement", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
	
	public static final Block GRAVEL_ROAD = BlockAndItemBuilder.create("gravel_road", new Block(AbstractBlock.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND)));
	public static final Block RAISED_GRAVEL_ROAD = BlockAndItemBuilder.create("raised_gravel_road", new SlabBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND)));
	
	public static final Block DIRT_ROAD = BlockAndItemBuilder.create("dirt_road", new Block(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.GRASS)));
	public static final Block RAISED_DIRT_ROAD = BlockAndItemBuilder.create("raised_dirt_road", new SlabBlock(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.DIRT).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.GRASS)));
	
	public static final Block PAVED_ROAD = BlockAndItemBuilder.create("paved_road", new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.STONE)));
	public static final Block RAISED_PAVED_ROAD = BlockAndItemBuilder.create("raised_paved_road", new SlabBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.STONE)));
	
	private static Boolean ocelotOrParrot(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) 
	{
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}
	
	private static boolean never(BlockState state, IBlockReader reader, BlockPos pos) 
	{
		return false;
	}
	
	private static LeavesBlock leaves() 
	{
		return new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).harvestTool(ToolType.HOE).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(BlockInit::ocelotOrParrot).isSuffocating(BlockInit::never) .isViewBlocking(BlockInit::never));
	}
	
	public static void init() 
	{
		ModUtils.LOGGER.debug("Registering Mod Blocks"); 
		
		TyrannoSignManager.registerSignBlock(() -> BlockInit.ARAUCARIA_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.ARAUCARIA_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.CALAMITES_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.CALAMITES_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.CONIFER_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.CONIFER_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.CYPRESS_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.CYPRESS_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.GINKGO_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.GINKGO_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.GLASS_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.GLASS_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.SCORCHED_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.SCORCHED_WALL_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.SEQUOIA_SIGN);
		TyrannoSignManager.registerSignBlock(() -> BlockInit.SEQUOIA_WALL_SIGN);
		
		ColouredGlassBlock.create();
		ColouredGlassPaneBlock.create();
		ColouredDecorationBlock.create();
		
		for(DinoTypes types : DinoTypes.eggLaying())
		{
			Block egg = BlockAndItemBuilder.createEgg(types.getId() + "_egg", types.getEgg(types.getEntityType()), ModUtils.tTC("entity", types.name().toLowerCase()));
			types.setEgg(egg);
		}
		
		for(DinoTypes types : DinoTypes.values())
		{
			if(types == DinoTypes.NAUTILUS)
			{
				Block shell = BlockAndItemBuilder.create("fossilized_nautilus_shell", new NautilusShellBlock(AbstractBlock.Properties.of(Material.SHULKER_SHELL).instabreak().noOcclusion().sound(SoundType.ANVIL)));
				types.setExtraBlock(shell);
			}
		}
	}
}
