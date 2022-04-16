package lostworlds.server.block;

import static lostworlds.LostWorldsMod.getRegistrate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
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
import lostworlds.server.item.WaterPlantItem;
import lostworlds.server.item.block.BrazileaItem;
import lostworlds.server.item.tool.ModMaterials;
import lostworlds.server.item.tool.ModToolTypes;
import lostworlds.server.util.LostWorldsRegistrate;
import lostworlds.server.util.registrate.DyedBlockList;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.HayBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.PressurePlateBlock.Sensitivity;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SaplingBlock;
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
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsBlocks {
	private static final LostWorldsRegistrate REGISTRATE = getRegistrate().itemGroup(() -> LostWorldsUtils.BLOCKS);
	public static final List<Supplier<? extends Block>> SIGN_BLOCKS = new ArrayList<>();

	public static final BlockEntry<OreBlock> COPPER_ORE = REGISTRATE.block("copper_ore", OreBlock::new).initialProperties(() -> Blocks.COAL_ORE).register();

	// Soils
	public static final BlockEntry<DriedSoilBlock> DRIED_SOIL = REGISTRATE.blockAndItem("dried_soil", DriedSoilBlock::new).properties(properties -> properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.75F).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL).randomTicks()).register();
	public static final BlockEntry<Block> CRACKED_SOIL = REGISTRATE.blockAndItem("cracked_soil", Block::new).properties(properties -> properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.75F).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL)).register();

	public static final BlockEntry<MossySoilBlock> MOSSY_SOIL = REGISTRATE.blockAndItem("mossy_soil", MossySoilBlock::new).properties(properties -> properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).harvestTool(ToolType.SHOVEL).randomTicks().sound(SoundType.GRAVEL)).blockstate((block, provider) -> LostWorldsBlockModels.mossySoil(block.get(), provider)).register();
	public static final BlockEntry<MudBlock> MUD = REGISTRATE.blockAndItem("mud", MudBlock::new).properties(properties -> properties.of(Material.CLAY, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.6F).sound(SoundType.GRAVEL)).register();
	public static final BlockEntry<Block> SILT = REGISTRATE.blockAndItem("silt", Block::new).properties(properties -> properties.of(Material.CLAY, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.6F).sound(SoundType.GRAVEL)).register();

	public static final BlockEntry<SandBlock> VOLCANIC_ASH = REGISTRATE.blockAndItem("volcanic_ash", properties -> new SandBlock(0x888988, properties)).properties(properties -> properties.of(Material.SAND, MaterialColor.COLOR_GRAY).harvestTool(ToolType.SHOVEL).harvestLevel(1).strength(0.5F).sound(SoundType.SAND)).register();
	public static final BlockEntry<VolcanicAshLayerBlock> VOLCANIC_ASH_LAYER = REGISTRATE.block("volcanic_ash_layer", VolcanicAshLayerBlock::new).properties(properties -> properties.of(Material.SAND, MaterialColor.COLOR_GRAY).harvestTool(ToolType.SHOVEL).harvestLevel(1).strength(0.5F).sound(SoundType.SAND)).blockstate((block, provider) -> LostWorldsBlockModels.volcanicAshLayer(block.get(), provider)).item().model((item, provider) -> provider.withExistingParent(item.getName(), LostWorldsUtils.rL("block/volcanic_ash_layer_height2"))).build().register();

	public static final BlockEntry<SandBlock> PERMIAN_SAND = REGISTRATE.blockAndItem("permian_sand", properties -> new SandBlock(0xaa915c, properties)).properties(properties -> properties.of(Material.SAND, MaterialColor.SAND).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND)).register(),
			ROCKY_SOIL = REGISTRATE.blockAndItem("rocky_soil", properties -> new SandBlock(0x8a8a8e, properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).harvestTool(ToolType.SHOVEL).requiresCorrectToolForDrops().strength(1.5F)).register();

	public static final BlockEntry<Block> PERMAFROST = REGISTRATE.blockAndItem("permafrost", Block::new).properties(properties -> properties.of(Material.DIRT, MaterialColor.ICE).strength(0.5F).sound(SoundType.GRAVEL)).register();

	// Stones
	public static final BlockEntry<Block> PERMIAN_STONE = REGISTRATE.blockAndItem("permian_stone", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).tag(LostWorldsTags.ModBlockTags.BASE_STONE_PERMIAN).register();
	public static final BlockEntry<StairsBlock> PERMIAN_STONE_STAIRS = REGISTRATE.stairBlock("permian_stone_stairs", "permian_stone", properties -> new StairsBlock(() -> LostWorldsBlocks.PERMIAN_STONE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<SlabBlock> PERMIAN_STONE_SLAB = REGISTRATE.slabBlock("permian_stone_slab", "permian_stone", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<PressurePlateBlock> PERMIAN_STONE_PRESSURE_PLATE = REGISTRATE.pressurePlateBlock("permian_stone_pressure_plate", "permian_stone", properties -> new PressurePlateBlock(Sensitivity.MOBS, properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)).register();
	public static final BlockEntry<StoneButtonBlock> PERMIAN_STONE_BUTTON = REGISTRATE.buttonBlock("permian_stone_button", "permian_stone", StoneButtonBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)).register();

	public static final BlockEntry<ModOreBlock> PERMIAN_COPPER_ORE = REGISTRATE.blockAndItem("permian_copper_ore", properties -> new ModOreBlock(0, 0, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			PERMIAN_GOLD_ORE = REGISTRATE.blockAndItem("permian_gold_ore", properties -> new ModOreBlock(0, 0, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			PERMIAN_IRON_ORE = REGISTRATE.blockAndItem("permian_iron_ore", properties -> new ModOreBlock(0, 0, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			PERMIAN_COAL_ORE = REGISTRATE.blockAndItem("permian_coal_ore", properties -> new ModOreBlock(0, 2, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			PERMIAN_LAPIS_ORE = REGISTRATE.blockAndItem("permian_lapis_ore", properties -> new ModOreBlock(2, 5, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			PERMIAN_DIAMOND_ORE = REGISTRATE.blockAndItem("permian_diamond_ore", properties -> new ModOreBlock(3, 7, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			PERMIAN_EMERALD_ORE = REGISTRATE.blockAndItem("permian_emerald_ore", properties -> new ModOreBlock(3, 7, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register();
	public static final BlockEntry<RedstoneOreBlock> PERMIAN_REDSTONE_ORE = REGISTRATE.blockAndItem("permian_redstone_ore", RedstoneOreBlock::new).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register();

	public static final BlockEntry<Block> PERMIAN_COBBLESTONE = REGISTRATE.blockAndItem("permian_cobblestone", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<StairsBlock> PERMIAN_COBBLESTONE_STAIRS = REGISTRATE.stairBlock("permian_cobblestone_stairs", "permian_cobblestone", properties -> new StairsBlock(() -> LostWorldsBlocks.PERMIAN_COBBLESTONE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<SlabBlock> PERMIAN_COBBLESTONE_SLAB = REGISTRATE.slabBlock("permian_cobblestone_slab", "permian_cobblestone", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<WallBlock> PERMIAN_COBBLESTONE_WALL = REGISTRATE.wallBlock("permian_cobblestone_wall", "permian_cobblestone", WallBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();

	public static final BlockEntry<Block> PERMIAN_STONE_BRICKS = REGISTRATE.blockAndItem("permian_stone_bricks", Block::new).properties(properties -> AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<StairsBlock> PERMIAN_STONE_BRICK_STAIRS = REGISTRATE.stairBlock("permian_stone_brick_stairs", "permian_stone_bricks", properties -> new StairsBlock(() -> LostWorldsBlocks.PERMIAN_STONE_BRICKS.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<SlabBlock> PERMIAN_STONE_BRICK_SLAB = REGISTRATE.slabBlock("permian_stone_brick_slab", "permian_stone_bricks", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<WallBlock> PERMIAN_STONE_BRICK_WALL = REGISTRATE.wallBlock("permian_stone_brick_wall", "permian_stone_bricks", WallBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();

	public static final BlockEntry<Block> JURASSIC_STONE = REGISTRATE.blockAndItem("jurassic_stone", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)).tag(LostWorldsTags.ModBlockTags.BASE_STONE_JURASSIC).register();
	public static final BlockEntry<StairsBlock> JURASSIC_STONE_STAIRS = REGISTRATE.stairBlock("jurassic_stone_stairs", "jurassic_stone", properties -> new StairsBlock(() -> LostWorldsBlocks.JURASSIC_STONE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)).register();
	public static final BlockEntry<SlabBlock> JURASSIC_STONE_SLAB = REGISTRATE.slabBlock("jurassic_stone_slab", "jurassic_stone", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)).register();
	public static final BlockEntry<PressurePlateBlock> JURASSIC_STONE_PRESSURE_PLATE = REGISTRATE.pressurePlateBlock("jurassic_stone_pressure_plate", "jurassic_stone", properties -> new PressurePlateBlock(Sensitivity.MOBS, properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)).register();
	public static final BlockEntry<StoneButtonBlock> JURASSIC_STONE_BUTTON = REGISTRATE.buttonBlock("jurassic_stone_button", "jurassic_stone", StoneButtonBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().noCollission().strength(3.0F)).register();

	public static final BlockEntry<ModOreBlock> JURASSIC_COPPER_ORE = REGISTRATE.blockAndItem("jurassic_copper_ore", properties -> new ModOreBlock(0, 0, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			JURASSIC_GOLD_ORE = REGISTRATE.blockAndItem("jurassic_gold_ore", properties -> new ModOreBlock(0, 0, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			JURASSIC_IRON_ORE = REGISTRATE.blockAndItem("jurassic_iron_ore", properties -> new ModOreBlock(0, 0, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			JURASSIC_COAL_ORE = REGISTRATE.blockAndItem("jurassic_coal_ore", properties -> new ModOreBlock(0, 2, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			JURASSIC_LAPIS_ORE = REGISTRATE.blockAndItem("jurassic_lapis_ore", properties -> new ModOreBlock(2, 5, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			JURASSIC_DIAMOND_ORE = REGISTRATE.blockAndItem("jurassic_diamond_ore", properties -> new ModOreBlock(3, 7, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register(),
			JURASSIC_EMERALD_ORE = REGISTRATE.blockAndItem("jurassic_emerald_ore", properties -> new ModOreBlock(3, 7, properties)).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register();
	public static final BlockEntry<RedstoneOreBlock> JURASSIC_REDSTONE_ORE = REGISTRATE.blockAndItem("jurassic_redstone_ore", RedstoneOreBlock::new).properties(properties -> properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)).register();

	public static final BlockEntry<Block> JURASSIC_COBBLESTONE = REGISTRATE.blockAndItem("jurassic_cobblestone", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)).register();
	public static final BlockEntry<StairsBlock> JURASSIC_COBBLESTONE_STAIRS = REGISTRATE.stairBlock("jurassic_cobblestone_stairs", "jurassic_cobblestone", properties -> new StairsBlock(() -> LostWorldsBlocks.JURASSIC_COBBLESTONE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)).register();
	public static final BlockEntry<SlabBlock> JURASSIC_COBBLESTONE_SLAB = REGISTRATE.slabBlock("jurassic_cobblestone_slab", "jurassic_cobblestone", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)).register();
	public static final BlockEntry<WallBlock> JURASSIC_COBBLESTONE_WALL = REGISTRATE.wallBlock("jurassic_cobblestone_wall", "jurassic_cobblestone", WallBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(3.0F)).register();

	public static final BlockEntry<Block> JURASSIC_STONE_BRICKS = REGISTRATE.blockAndItem("jurassic_stone_bricks", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<StairsBlock> JURASSIC_STONE_BRICK_STAIRS = REGISTRATE.stairBlock("jurassic_stone_brick_stairs", "jurassic_stone_bricks", properties -> new StairsBlock(() -> LostWorldsBlocks.JURASSIC_STONE_BRICKS.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<SlabBlock> JURASSIC_STONE_BRICK_SLAB = REGISTRATE.slabBlock("jurassic_stone_brick_slab", "jurassic_stone_bricks", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<WallBlock> JURASSIC_STONE_BRICK_WALL = REGISTRATE.wallBlock("jurassic_stone_brick_wall", "jurassic_stone_bricks", WallBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)).register();

	public static final BlockEntry<Block> METEORIC_STONE = REGISTRATE.blockAndItem("meteoric_stone", Block::new).properties(properties -> properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)).register();
	public static final BlockEntry<StairsBlock> METEORIC_STONE_STAIRS = REGISTRATE.stairBlock("meteoric_stone_stairs", "meteoric_stone", properties -> new StairsBlock(() -> LostWorldsBlocks.METEORIC_STONE.getDefaultState(), properties)).properties(properties -> properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)).register();
	public static final BlockEntry<SlabBlock> METEORIC_STONE_SLAB = REGISTRATE.slabBlock("meteoric_stone_slab", "meteoric_stone", SlabBlock::new).properties(properties -> properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)).register();
	public static final BlockEntry<WallBlock> METEORIC_STONE_WALL = REGISTRATE.wallBlock("meteoric_stone_wall", "meteoric_stone", WallBlock::new).properties(properties -> properties.of(ModMaterials.OUT_OF_THIS_WORLD, MaterialColor.TERRACOTTA_BROWN).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)).register();

	public static final BlockEntry<Block> LATERLITE = REGISTRATE.blockAndItem("laterlite", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<StairsBlock> LATERLITE_STAIRS = REGISTRATE.stairBlock("laterlite_stairs", "laterlite", properties -> new StairsBlock(() -> LostWorldsBlocks.LATERLITE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<SlabBlock> LATERLITE_SLAB = REGISTRATE.slabBlock("laterlite_slab", "laterlite", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<WallBlock> LATERLITE_WALL = REGISTRATE.wallBlock("laterlite_wall", "laterlite", WallBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();

	public static final BlockEntry<Block> POLISHED_LATERLITE = REGISTRATE.blockAndItem("polished_laterlite", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<StairsBlock> POLISHED_LATERLITE_STAIRS = REGISTRATE.block("polished_laterlite_stairs", "polished_laterlite", properties -> new StairsBlock(() -> LostWorldsBlocks.LATERLITE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<SlabBlock> POLISHED_LATERLITE_SLAB = REGISTRATE.slabBlock("polished_laterlite_slab", "polished_laterlite", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();

	public static final BlockEntry<Block> RAW_MARBLE = REGISTRATE.blockAndItem("raw_marble", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<StairsBlock> RAW_MARBLE_STAIRS = REGISTRATE.stairBlock("raw_marble_stairs", "raw_marble", properties -> new StairsBlock(() -> LostWorldsBlocks.RAW_MARBLE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<SlabBlock> RAW_MARBLE_SLAB = REGISTRATE.slabBlock("raw_marble_slab", "raw_marble", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<WallBlock> RAW_MARBLE_WALL = REGISTRATE.block("raw_marble_wall", "raw_marble", WallBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();

	public static final BlockEntry<Block> MARBLE = REGISTRATE.blockAndItem("marble", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<StairsBlock> MARBLE_STAIRS = REGISTRATE.block("marble_stairs", "marble", properties -> new StairsBlock(() -> LostWorldsBlocks.MARBLE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<SlabBlock> MARBLE_SLAB = REGISTRATE.slabBlock("marble_slab", "marble", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();

	public static final BlockEntry<Block> LIMESTONE = REGISTRATE.blockAndItem("limestone", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<StairsBlock> LIMESTONE_STAIRS = REGISTRATE.stairBlock("limestone_stairs", "limestone", properties -> new StairsBlock(() -> LostWorldsBlocks.LIMESTONE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<SlabBlock> LIMESTONE_SLAB = REGISTRATE.slabBlock("limestone_slab", "limestone", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<WallBlock> LIMESTONE_WALL = REGISTRATE.block("limestone_wall", "limestone", WallBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();

	public static final BlockEntry<Block> POLISHED_LIMESTONE = REGISTRATE.blockAndItem("polished_limestone", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<StairsBlock> POLISHED_LIMESTONE_STAIRS = REGISTRATE.stairBlock("polished_limestone_stairs", "polished_limestone", properties -> new StairsBlock(() -> LostWorldsBlocks.POLISHED_LIMESTONE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<SlabBlock> POLISHED_LIMESTONE_SLAB = REGISTRATE.slabBlock("polished_limestone_slab", "polished_limestone", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();

	public static final BlockEntry<Block> VOLCANIC_ROCK = REGISTRATE.blockAndItem("volcanic_rock", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)).register();
	public static final BlockEntry<StairsBlock> VOLCANIC_ROCK_STAIRS = REGISTRATE.stairBlock("volcanic_rock_stairs", "volcanic_rock", properties -> new StairsBlock(VOLCANIC_ROCK.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)).register();
	public static final BlockEntry<SlabBlock> VOLCANIC_ROCK_SLAB = REGISTRATE.slabBlock("volcanic_rock_slab", "volcanic_rock", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)).register();
	public static final BlockEntry<WallBlock> VOLCANIC_ROCK_WALL = REGISTRATE.wallBlock("volcanic_rock_wall", "volcanic_rock", WallBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)).register();

	public static final BlockEntry<Block> POLISHED_VOLCANIC_ROCK = REGISTRATE.blockAndItem("polished_volcanic_rock", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)).register();
	public static final BlockEntry<StairsBlock> POLISHED_VOLCANIC_ROCK_STAIRS = REGISTRATE.stairBlock("polished_volcanic_rock_stairs", "polished_volcanic_rock", properties -> new StairsBlock(() -> LostWorldsBlocks.POLISHED_VOLCANIC_ROCK.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)).register();
	public static final BlockEntry<SlabBlock> POLISHED_VOLCANIC_ROCK_SLAB = REGISTRATE.slabBlock("polished_volcanic_rock_slab", "polished_volcanic_rock", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GILDED_BLACKSTONE)).register();

	public static final BlockEntry<SoftStoneBlock> SOFT_STONE = REGISTRATE.blockAndItem("soft_stone", SoftStoneBlock::new).properties(properties -> properties.of(ModMaterials.SOFT).harvestTool(ModToolTypes.HAMMER).strength(4.0F, 0.0F).noDrops().sound(SoundType.STONE)).register();
	public static final BlockEntry<SoftDirtBlock> SOFT_DIRT = REGISTRATE.blockAndItem("soft_dirt", SoftDirtBlock::new).properties(properties -> properties.of(ModMaterials.SOFT).harvestTool(ModToolTypes.BRUSH).strength(4.0F, 0.0F).noDrops().sound(SoundType.GRAVEL)).register();
	public static final BlockEntry<PlantFossilBlock> PLANT_FOSSIL = REGISTRATE.blockAndItem("plant_fossil", PlantFossilBlock::new).properties(properties -> properties.of(ModMaterials.SOFT).harvestTool(ModToolTypes.HAMMER).strength(4.0F, 0.0F).noDrops().sound(SoundType.STONE)).register();

	public static final BlockEntry<FossilizedTrackBlock> FOSSILIZED_TRACK = REGISTRATE.blockAndItem("fossilized_track", properties -> new FossilizedTrackBlock(() -> Blocks.STONE, properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<PlasteredBlock> PLASTERED_FOSSILIZED_TRACK = REGISTRATE.blockAndItem("plastered_fossilized_track", properties -> new PlasteredBlock(() -> LostWorldsBlocks.FOSSILIZED_TRACK.get(), properties)).properties(properties -> properties.of(Material.STONE).instabreak().sound(SoundType.WOOL)).register();

	public static final BlockEntry<TinyFossilizedEggBlock> TINY_FOSSILISED_EGG = REGISTRATE.blockAndItem("tiny_fossilized_egg", TinyFossilizedEggBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()).register();
	public static final BlockEntry<TinyPlasteredFossilizedEggBlock> TINY_PLASTERED_FOSSILISED_EGG = REGISTRATE.blockAndItem("tiny_plastered_fossilized_egg", TinyPlasteredFossilizedEggBlock::new).properties(properties -> properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()).register();
	public static final BlockEntry<SmallFossilizedEggBlock> SMALL_FOSSILISED_EGG = REGISTRATE.blockAndItem("small_fossilized_egg", SmallFossilizedEggBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()).register();
	public static final BlockEntry<SmallPlasteredFossilizedEggBlock> SMALL_PLASTERED_FOSSILISED_EGG = REGISTRATE.blockAndItem("small_plastered_fossilized_egg", SmallPlasteredFossilizedEggBlock::new).properties(properties -> properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()).register();
	public static final BlockEntry<MediumFossilisedEggBlock> MEDIUM_FOSSILISED_EGG = REGISTRATE.blockAndItem("medium_fossilized_egg", MediumFossilisedEggBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()).register();
	public static final BlockEntry<MediumPlasteredFossilizedEggBlock> MEDIUM_PLASTERED_FOSSILISED_EGG = REGISTRATE.blockAndItem("medium_plastered_fossilized_egg", MediumPlasteredFossilizedEggBlock::new).properties(properties -> properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()).register();
	public static final BlockEntry<LargeFossilisedEggBlock> LARGE_FOSSILISED_EGG = REGISTRATE.blockAndItem("large_fossilized_egg", LargeFossilisedEggBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(0.5F).noOcclusion()).register();
	public static final BlockEntry<LargePlasteredFossilizedEggBlock> LARGE_PLASTERED_FOSSILISED_EGG = REGISTRATE.blockAndItem("large_plastered_fossilized_egg", LargePlasteredFossilizedEggBlock::new).properties(properties -> properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).instabreak().noOcclusion()).register();

	// Overworld Ores
	public static final BlockEntry<ModOreBlock> AMBER_ORE = REGISTRATE.block("amber_ore", properties -> new ModOreBlock(0, 0, properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.DIRT).requiresCorrectToolForDrops().strength(1.5F, 6.0F)).register();
	public static final BlockEntry<ModOreRotatedPillerBlock> BASALT_DIAMOND_ORE = REGISTRATE.block("basalt_diamond_ore", ModOreRotatedPillerBlock::new).properties(properties -> properties.copy(Blocks.BASALT)).register();

	// Machines
	public static final BlockEntry<FossilCleanerBlock> FOSSIL_CLEANER = REGISTRATE.blockAndItem("fossil_cleaner", FossilCleanerBlock::new).properties(properties -> properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.0f, 6.0F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.METAL)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<FossilGrinderBlock> FOSSIL_GRINDER = REGISTRATE.blockAndItem("fossil_grinder", FossilGrinderBlock::new).properties(properties -> properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.0f, 6.0F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.METAL)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DNAExtractorBlock> DNA_EXTRACTOR = REGISTRATE.blockAndItem("dna_extractor", DNAExtractorBlock::new).properties(properties -> properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.0f, 6.0F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.METAL)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<AnalyzerBlock> ANALYZER = REGISTRATE.blockAndItem("analyzer", AnalyzerBlock::new).properties(properties -> properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.0f, 6.0F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.METAL)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DNAInjectorBlock> DNA_INJECTOR = REGISTRATE.blockAndItem("dna_injector", DNAInjectorBlock::new).properties(properties -> properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.0f, 6.0F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.METAL)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<CultivatorBlock> CULTIVATOR = REGISTRATE.blockAndItem("cultivator", CultivatorBlock::new).properties(properties -> properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.0f, 6.0F).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.METAL)).addLayer(() -> RenderType::cutout).register();

	public static final BlockEntry<ArchaeologyTableBlock> ARCHAEOLOGY_TABLE = REGISTRATE.blockAndItem("archaeology_table", ArchaeologyTableBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).register();

	public static final BlockEntry<PaleontologyTableBlock> PALEONTOLOGY_TABLE = REGISTRATE.blockAndItem("paleontology_table", PaleontologyTableBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).register();

	public static final BlockEntry<PaleobotanyTableBlock> PALEOBOTANY_TABLE = REGISTRATE.blockAndItem("paleobotany_table", PaleobotanyTableBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();

	public static final BlockEntry<TimeMachineBlock> TIME_MACHINE = REGISTRATE.blockAndItem("time_machine", TimeMachineBlock::new).properties(properties -> properties.of(ModMaterials.MAGIC).harvestTool(ToolType.PICKAXE).harvestLevel(5).requiresCorrectToolForDrops().strength(50.0F, 1200.0F)).register();

	public static final BlockEntry<FeedingTroughBlock> FEEDING_TROUGH = REGISTRATE.blockAndItem("feeding_trough", FeedingTroughBlock::new).properties(properties -> properties.of(Material.WOOD).harvestTool(ToolType.AXE).strength(2.5F).sound(SoundType.WOOD)).register();

	// Museum Blocks
	public static final BlockEntry<DisplayCaseBlock> DISPLAY_CASE = REGISTRATE.blockAndItem("display_case", DisplayCaseBlock::new).properties(properties -> properties.of(Material.STONE).harvestLevel(1).requiresCorrectToolForDrops().noOcclusion().strength(4.0F, 5.0F).sound(SoundType.GLASS)).addLayer(() -> RenderType::cutout).register();

	// Wood

	// Araucaria
	public static final BlockEntry<RotatedPillarBlock> ARAUCARIA_LOG = REGISTRATE.blockAndItem("araucaria_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_ARAUCARIA_LOG = REGISTRATE.blockAndItem("stripped_araucaria_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> ARAUCARIA_WOOD = REGISTRATE.blockAndItem("araucaria_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_ARAUCARIA_WOOD = REGISTRATE.blockAndItem("stripped_araucaria_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<LeavesBlock> ARAUCARIA_LEAVES = REGISTRATE.blockAndItem("araucaria_leaves", LostWorldsBlocks::leaves).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<SaplingBlock> ARAUCARIA_SAPLING = REGISTRATE.blockAndItem("araucaria_sapling", properties -> new SaplingBlock(new AraucariaTree(), properties)).properties(properties -> properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<Block> ARAUCARIA_PLANKS = REGISTRATE.blockAndItem("araucaria_planks", Block::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<StairsBlock> ARAUCARIA_STAIRS = REGISTRATE.stairBlock("araucaria_stairs", "araucaria_planks", properties -> new StairsBlock(() -> LostWorldsBlocks.ARAUCARIA_PLANKS.getDefaultState(), properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<SlabBlock> ARAUCARIA_SLAB = REGISTRATE.slabBlock("araucaria_slab", "araucaria_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceBlock> ARAUCARIA_FENCE = REGISTRATE.fenceBlock("araucaria_fence", "araucaria_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceGateBlock> ARAUCARIA_FENCE_GATE = REGISTRATE.fenceGateBlock("araucaria_fence_gate", "araucaria_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<PressurePlateBlock> ARAUCARIA_PRESSURE_PLATE = REGISTRATE.pressurePlateBlock("araucaria_pressure_plate", "araucaria_planks", properties -> new PressurePlateBlock(Sensitivity.EVERYTHING, properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WoodButtonBlock> ARAUCARIA_BUTTON = REGISTRATE.buttonBlock("araucaria_button", "araucaria_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<TrapDoorBlock> ARAUCARIA_TRAPDOOR = REGISTRATE.trapdoorBlock("araucaria_trapdoor", TrapDoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DoorBlock> ARAUCARIA_DOOR = REGISTRATE.doorBlock("araucaria_door", DoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<StandingSignBlock> ARAUCARIA_SIGN = REGISTRATE.signBlock("araucaria_sign", "araucaria_planks", properties -> new StandingSignBlock(properties, ModWoodType.ARAUCARIA)).properties(properties -> AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WallSignBlock> ARAUCARIA_WALL_SIGN = REGISTRATE.signBlock("araucaria_wall_sign", "araucaria_planks", properties -> new WallSignBlock(properties, ModWoodType.ARAUCARIA)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final ItemEntry<SignItem> ARAUCARIA_SIGN_ITEM = REGISTRATE.item("araucaria_sign", properties -> new SignItem(properties, ARAUCARIA_SIGN.get(), ARAUCARIA_WALL_SIGN.get())).properties((properties) -> properties.stacksTo(16)).register();
	public static final ItemEntry<ModBoatItem> ARAUCARIA_BOAT = REGISTRATE.item("araucaria_boat", properties -> new ModBoatItem(ModBoatType.ARAUCARIA, properties)).properties(properties -> properties.stacksTo(1)).register();

	public static final BlockEntry<PetrifiedWoodBlock> PETRIFIED_ARAUCARIA_LOG = REGISTRATE.blockAndItem("petrified_araucaria_log", properties -> new PetrifiedWoodBlock(() -> LostWorldsItems.ARAUCARIA_BARK_SAMPLE.get(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_PETRIFIED_ARAUCARIA_LOG = REGISTRATE.blockAndItem("stripped_petrified_araucaria_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();

	// Calamite
	public static final BlockEntry<RotatedPillarBlock> CALAMITES_LOG = REGISTRATE.blockAndItem("calamites_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_CALAMITES_LOG = REGISTRATE.blockAndItem("stripped_calamites_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> CALAMITES_WOOD = REGISTRATE.blockAndItem("calamites_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_CALAMITES_WOOD = REGISTRATE.blockAndItem("stripped_calamites_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<LeavesBlock> CALAMITES_LEAVES = REGISTRATE.blockAndItem("calamites_leaves", LostWorldsBlocks::leaves).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<SaplingBlock> CALAMITES_SAPLING = REGISTRATE.blockAndItem("calamites_sapling", properties -> new SaplingBlock(new CalamitesTree(), properties)).properties(properties -> properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<Block> CALAMITES_PLANKS = REGISTRATE.blockAndItem("calamites_planks", Block::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<StairsBlock> CALAMITES_STAIRS = REGISTRATE.stairBlock("calamites_stairs", "calamites_planks", properties -> new StairsBlock(() -> LostWorldsBlocks.CALAMITES_PLANKS.getDefaultState(), properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<SlabBlock> CALAMITES_SLAB = REGISTRATE.slabBlock("calamites_slab", "calamites_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceBlock> CALAMITES_FENCE = REGISTRATE.fenceBlock("calamites_fence", "calamites_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceGateBlock> CALAMITES_FENCE_GATE = REGISTRATE.fenceGateBlock("calamites_fence_gate", "calamites_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<PressurePlateBlock> CALAMITES_PRESSURE_PLATE = REGISTRATE.pressurePlateBlock("calamites_pressure_plate", "calamites_planks", properties -> new PressurePlateBlock(Sensitivity.EVERYTHING, properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WoodButtonBlock> CALAMITES_BUTTON = REGISTRATE.buttonBlock("calamites_button", "calamites_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<TrapDoorBlock> CALAMITES_TRAPDOOR = REGISTRATE.trapdoorBlock("calamites_trapdoor", TrapDoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DoorBlock> CALAMITES_DOOR = REGISTRATE.doorBlock("calamites_door", DoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<StandingSignBlock> CALAMITES_SIGN = REGISTRATE.signBlock("calamites_sign", "calamites_planks", properites -> new StandingSignBlock(properites, ModWoodType.CALAMITES)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WallSignBlock> CALAMITES_WALL_SIGN = REGISTRATE.signBlock("calamites_wall_sign", "calamites_planks", properties -> new WallSignBlock(properties, ModWoodType.CALAMITES)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final ItemEntry<SignItem> CALAMITES_SIGN_ITEM = REGISTRATE.item("calamites_sign", properties -> new SignItem(properties, CALAMITES_SIGN.get(), CALAMITES_WALL_SIGN.get())).properties((properties) -> properties.stacksTo(16)).register();
	public static final ItemEntry<ModBoatItem> CALAMITES_BOAT = REGISTRATE.item("calamites_boat", properties -> new ModBoatItem(ModBoatType.CALAMITES, properties)).properties(properties -> properties.stacksTo(1)).register();

	public static final BlockEntry<PetrifiedWoodBlock> PETRIFIED_CALAMITES_LOG = REGISTRATE.blockAndItem("petrified_calamites_log", properties -> new PetrifiedWoodBlock(() -> LostWorldsItems.CALAMITES_BARK_SAMPLE.get(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_PETRIFIED_CALAMITES_LOG = REGISTRATE.blockAndItem("stripped_petrified_calamites_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();

	// Conifer
	public static final BlockEntry<RotatedPillarBlock> CONIFER_LOG = REGISTRATE.blockAndItem("conifer_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_CONIFER_LOG = REGISTRATE.blockAndItem("stripped_conifer_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> CONIFER_WOOD = REGISTRATE.blockAndItem("conifer_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_CONIFER_WOOD = REGISTRATE.blockAndItem("stripped_conifer_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<LeavesBlock> CONIFER_LEAVES = REGISTRATE.blockAndItem("conifer_leaves", LostWorldsBlocks::leaves).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<SaplingBlock> CONIFER_SAPLING = REGISTRATE.blockAndItem("conifer_sapling", properties -> new SaplingBlock(new ConiferTree(), properties)).properties(properties -> properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<Block> CONIFER_PLANKS = REGISTRATE.blockAndItem("conifer_planks", Block::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<StairsBlock> CONIFER_STAIRS = REGISTRATE.stairBlock("conifer_stairs", "conifer_planks", properties -> new StairsBlock(() -> LostWorldsBlocks.CONIFER_PLANKS.getDefaultState(), properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<SlabBlock> CONIFER_SLAB = REGISTRATE.slabBlock("conifer_slab", "conifer_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceBlock> CONIFER_FENCE = REGISTRATE.fenceBlock("conifer_fence", "conifer_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceGateBlock> CONIFER_FENCE_GATE = REGISTRATE.fenceGateBlock("conifer_fence_gate", "conifer_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<PressurePlateBlock> CONIFER_PRESSURE_PLATE = REGISTRATE.pressurePlateBlock("conifer_pressure_plate", "conifer_planks", properties -> new PressurePlateBlock(Sensitivity.EVERYTHING, properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WoodButtonBlock> CONIFER_BUTTON = REGISTRATE.buttonBlock("conifer_button", "conifer_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<TrapDoorBlock> CONIFER_TRAPDOOR = REGISTRATE.trapdoorBlock("conifer_trapdoor", TrapDoorBlock::new).properties(properties -> AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DoorBlock> CONIFER_DOOR = REGISTRATE.doorBlock("conifer_door", DoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<StandingSignBlock> CONIFER_SIGN = REGISTRATE.signBlock("conifer_sign", "conifer_planks", properties -> new StandingSignBlock(properties, ModWoodType.CONIFER)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WallSignBlock> CONIFER_WALL_SIGN = REGISTRATE.signBlock("conifer_wall_sign", "conifer_planks", properties -> new WallSignBlock(properties, ModWoodType.CONIFER)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final ItemEntry<SignItem> CONIFER_SIGN_ITEM = REGISTRATE.item("conifer_sign", properties -> new SignItem(properties, CONIFER_SIGN.get(), CONIFER_WALL_SIGN.get())).properties((properties) -> properties.stacksTo(16)).register();
	public static final ItemEntry<ModBoatItem> CONIFER_BOAT = REGISTRATE.item("conifer_boat", properties -> new ModBoatItem(ModBoatType.CONIFER, properties)).properties(properties -> properties.stacksTo(1)).register();

	public static final BlockEntry<PetrifiedWoodBlock> PETRIFIED_CONIFER_LOG = REGISTRATE.blockAndItem("petrified_conifer_log", properties -> new PetrifiedWoodBlock(() -> LostWorldsItems.CONIFER_BARK_SAMPLE.get(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_PETRIFIED_CONIFER_LOG = REGISTRATE.blockAndItem("stripped_petrified_conifer_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();

	// Cypress
	public static final BlockEntry<RotatedPillarBlock> CYPRESS_LOG = REGISTRATE.blockAndItem("cypress_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_CYPRESS_LOG = REGISTRATE.blockAndItem("stripped_cypress_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> CYPRESS_WOOD = REGISTRATE.blockAndItem("cypress_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_CYPRESS_WOOD = REGISTRATE.blockAndItem("stripped_cypress_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<LeavesBlock> CYPRESS_LEAVES = REGISTRATE.blockAndItem("cypress_leaves", LostWorldsBlocks::leaves).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<SaplingBlock> CYPRESS_SAPLING = REGISTRATE.blockAndItem("cypress_sapling", properties -> new SaplingBlock(new CypressTree(), properties)).properties(properties -> properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<Block> CYPRESS_PLANKS = REGISTRATE.blockAndItem("cypress_planks", Block::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<StairsBlock> CYPRESS_STAIRS = REGISTRATE.stairBlock("cypress_stairs", "cypress_planks", properties -> new StairsBlock(() -> LostWorldsBlocks.CYPRESS_PLANKS.getDefaultState(), properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<SlabBlock> CYPRESS_SLAB = REGISTRATE.slabBlock("cypress_slab", "cypress_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceBlock> CYPRESS_FENCE = REGISTRATE.fenceBlock("cypress_fence", "cypress_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceGateBlock> CYPRESS_FENCE_GATE = REGISTRATE.fenceGateBlock("cypress_fence_gate", "cypress_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<PressurePlateBlock> CYPRESS_PRESSURE_PLATE = REGISTRATE.pressurePlateBlock("cypress_pressure_plate", "cypress_planks", properites -> new PressurePlateBlock(Sensitivity.EVERYTHING, properites)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WoodButtonBlock> CYPRESS_BUTTON = REGISTRATE.buttonBlock("cypress_button", "cypress_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<TrapDoorBlock> CYPRESS_TRAPDOOR = REGISTRATE.trapdoorBlock("cypress_trapdoor", TrapDoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DoorBlock> CYPRESS_DOOR = REGISTRATE.doorBlock("cypress_door", DoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<StandingSignBlock> CYPRESS_SIGN = REGISTRATE.signBlock("cypress_sign", "cypress_planks", properties -> new StandingSignBlock(properties, ModWoodType.CYPRESS)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WallSignBlock> CYPRESS_WALL_SIGN = REGISTRATE.signBlock("cypress_wall_sign", "cypress_planks", properties -> new WallSignBlock(properties, ModWoodType.CYPRESS)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final ItemEntry<SignItem> CYPRESS_SIGN_ITEM = REGISTRATE.item("cypress_sign", properties -> new SignItem(properties, CYPRESS_SIGN.get(), CYPRESS_WALL_SIGN.get())).properties(properties -> properties.stacksTo(16)).register();
	public static final ItemEntry<ModBoatItem> CYPRESS_BOAT = REGISTRATE.item("cypress_boat", properties -> new ModBoatItem(ModBoatType.CYPRESS, properties)).properties(properties -> properties.stacksTo(1)).register();

	public static final BlockEntry<PetrifiedWoodBlock> PETRIFIED_CYPRESS_LOG = REGISTRATE.blockAndItem("petrified_cypress_log", properties -> new PetrifiedWoodBlock(() -> LostWorldsItems.CYPRESS_BARK_SAMPLE.get(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_PETRIFIED_CYPRESS_LOG = REGISTRATE.blockAndItem("stripped_petrified_cypress_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();

	// Ginkgo
	public static final BlockEntry<RotatedPillarBlock> GINKGO_LOG = REGISTRATE.blockAndItem("ginkgo_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_GINKGO_LOG = REGISTRATE.blockAndItem("stripped_ginkgo_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> GINKGO_WOOD = REGISTRATE.blockAndItem("ginkgo_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_GINKGO_WOOD = REGISTRATE.blockAndItem("stripped_ginkgo_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<LeavesBlock> GINKGO_LEAVES = REGISTRATE.blockAndItem("ginkgo_leaves", LostWorldsBlocks::leaves).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<SaplingBlock> GINKGO_SAPLING = REGISTRATE.blockAndItem("ginkgo_sapling", properties -> new SaplingBlock(new GinkgoTree(), properties)).properties(properties -> properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<Block> GINKGO_PLANKS = REGISTRATE.blockAndItem("ginkgo_planks", Block::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<StairsBlock> GINKGO_STAIRS = REGISTRATE.stairBlock("ginkgo_stairs", "ginkgo_planks", properties -> new StairsBlock(() -> LostWorldsBlocks.GINKGO_PLANKS.getDefaultState(), properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<SlabBlock> GINKGO_SLAB = REGISTRATE.slabBlock("ginkgo_slab", "ginkgo_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceBlock> GINKGO_FENCE = REGISTRATE.fenceBlock("ginkgo_fence", "ginkgo_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceGateBlock> GINKGO_FENCE_GATE = REGISTRATE.fenceGateBlock("ginkgo_fence_gate", "ginkgo_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<PressurePlateBlock> GINKGO_PRESSURE_PLATE = REGISTRATE.block("ginkgo_pressure_plate", "ginkgo_planks", properties -> new PressurePlateBlock(Sensitivity.EVERYTHING, properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WoodButtonBlock> GINKGO_BUTTON = REGISTRATE.buttonBlock("ginkgo_button", "ginkgo_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<TrapDoorBlock> GINKGO_TRAPDOOR = REGISTRATE.trapdoorBlock("ginkgo_trapdoor", TrapDoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DoorBlock> GINKGO_DOOR = REGISTRATE.doorBlock("ginkgo_door", DoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<StandingSignBlock> GINKGO_SIGN = REGISTRATE.signBlock("ginkgo_sign", "ginkgo_planks", properties -> new StandingSignBlock(properties, ModWoodType.GINKGO)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WallSignBlock> GINKGO_WALL_SIGN = REGISTRATE.signBlock("ginkgo_wall_sign", "ginkgo_planks", properties -> new WallSignBlock(properties, ModWoodType.GINKGO)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final ItemEntry<SignItem> GINKGO_SIGN_ITEM = REGISTRATE.item("ginkgo_sign", properties -> new SignItem(properties, GINKGO_SIGN.get(), GINKGO_WALL_SIGN.get())).properties(properties -> properties.stacksTo(16)).register();
	public static final ItemEntry<ModBoatItem> GINKGO_BOAT = REGISTRATE.item("ginkgo_boat", properties -> new ModBoatItem(ModBoatType.GINKGO, properties)).properties(properties -> properties.stacksTo(1)).register();

	public static final BlockEntry<PetrifiedWoodBlock> PETRIFIED_GINKGO_LOG = REGISTRATE.blockAndItem("petrified_ginkgo_log", properties -> new PetrifiedWoodBlock(() -> LostWorldsItems.GINKGO_BARK_SAMPLE.get(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_PETRIFIED_GINKGO_LOG = REGISTRATE.blockAndItem("stripped_petrified_ginkgo_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();

	// Scorched
	public static final BlockEntry<RotatedPillarBlock> SCORCHED_LOG = REGISTRATE.blockAndItem("scorched_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_SCORCHED_LOG = REGISTRATE.blockAndItem("stripped_scorched_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> SCORCHED_WOOD = REGISTRATE.blockAndItem("scorched_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_SCORCHED_WOOD = REGISTRATE.blockAndItem("stripped_scorched_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<Block> SCORCHED_PLANKS = REGISTRATE.blockAndItem("scorched_planks", Block::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<StairsBlock> SCORCHED_STAIRS = REGISTRATE.stairBlock("scorched_stairs", "scorched_planks", properties -> new StairsBlock(() -> LostWorldsBlocks.SCORCHED_PLANKS.getDefaultState(), properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<SlabBlock> SCORCHED_SLAB = REGISTRATE.slabBlock("scorched_slab", "scorched_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceBlock> SCORCHED_FENCE = REGISTRATE.fenceBlock("scorched_fence", "scorched_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceGateBlock> SCORCHED_FENCE_GATE = REGISTRATE.fenceGateBlock("scorched_fence_gate", "scorched_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<PressurePlateBlock> SCORCHED_PRESSURE_PLATE = REGISTRATE.pressurePlateBlock("scorched_pressure_plate", "scorched_planks", properties -> new PressurePlateBlock(Sensitivity.EVERYTHING, properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WoodButtonBlock> SCORCHED_BUTTON = REGISTRATE.buttonBlock("scorched_button", "scorched_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<TrapDoorBlock> SCORCHED_TRAPDOOR = REGISTRATE.trapdoorBlock("scorched_trapdoor", TrapDoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DoorBlock> SCORCHED_DOOR = REGISTRATE.doorBlock("scorched_door", DoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<StandingSignBlock> SCORCHED_SIGN = REGISTRATE.signBlock("scorched_sign", "scorched_planks", properties -> new StandingSignBlock(properties, ModWoodType.SCORCHED)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WallSignBlock> SCORCHED_WALL_SIGN = REGISTRATE.signBlock("scorched_wall_sign", "scorched_planks", properties -> new WallSignBlock(properties, ModWoodType.SCORCHED)).properties(properties -> properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final ItemEntry<SignItem> SCORCHED_SIGN_ITEM = REGISTRATE.item("scorched_sign", properties -> new SignItem(properties, SCORCHED_SIGN.get(), SCORCHED_WALL_SIGN.get())).properties(properties -> properties.stacksTo(16)).register();
	public static final ItemEntry<ModBoatItem> SCORCHED_BOAT = REGISTRATE.item("scorched_boat", properties -> new ModBoatItem(ModBoatType.SCORCHED, properties)).properties(properties -> properties.stacksTo(1)).register();

	// Sequoia
	public static final BlockEntry<RotatedPillarBlock> SEQUOIA_LOG = REGISTRATE.blockAndItem("sequoia_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_SEQUOIA_LOG = REGISTRATE.blockAndItem("stripped_sequoia_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> SEQUOIA_WOOD = REGISTRATE.blockAndItem("sequoia_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_SEQUOIA_WOOD = REGISTRATE.blockAndItem("stripped_sequoia_wood", RotatedPillarBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<LeavesBlock> SEQUOIA_LEAVES = REGISTRATE.blockAndItem("sequoia_leaves", LostWorldsBlocks::leaves).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<SaplingBlock> SEQUOIA_SAPLING = REGISTRATE.blockAndItem("sequoia_sapling", properties -> new SaplingBlock(new SequoiaTree(), properties)).properties(properties -> properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<Block> SEQUOIA_PLANKS = REGISTRATE.blockAndItem("sequoia_planks", Block::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<StairsBlock> SEQUOIA_STAIRS = REGISTRATE.stairBlock("sequoia_stairs", "sequoia_planks", properties -> new StairsBlock(() -> LostWorldsBlocks.CONIFER_PLANKS.getDefaultState(), properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<SlabBlock> SEQUOIA_SLAB = REGISTRATE.slabBlock("sequoia_slab", "sequoia_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceBlock> SEQUOIA_FENCE = REGISTRATE.block("sequoia_fence", "sequoia_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceGateBlock> SEQUOIA_FENCE_GATE = REGISTRATE.block("sequoia_fence_gate", "sequoia_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<PressurePlateBlock> SEQUOIA_PRESSURE_PLATE = REGISTRATE.block("sequoia_pressure_plate", "sequoia_planks", properties -> new PressurePlateBlock(Sensitivity.EVERYTHING, properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WoodButtonBlock> SEQUOIA_BUTTON = REGISTRATE.block("sequoia_button", "sequoia_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<TrapDoorBlock> SEQUOIA_TRAPDOOR = REGISTRATE.trapdoorBlock("sequoia_trapdoor", TrapDoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DoorBlock> SEQUOIA_DOOR = REGISTRATE.blockAndItem("sequoia_door", DoorBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<StandingSignBlock> SEQUOIA_SIGN = REGISTRATE.signBlock("sequoia_sign", "sequoia_planks", properties -> new StandingSignBlock(properties, ModWoodType.SEQUOIA)).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WallSignBlock> SEQUOIA_WALL_SIGN = REGISTRATE.signBlock("sequoia_wall_sign", "sequoia_planks", properties -> new WallSignBlock(properties, ModWoodType.SEQUOIA)).properties(properties -> properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).noOcclusion().noCollission().sound(SoundType.WOOD)).register();
	public static final ItemEntry<SignItem> SEQUOIA_SIGN_ITEM = REGISTRATE.item("sequoia_sign", properties -> new SignItem(properties, SEQUOIA_SIGN.get(), SEQUOIA_WALL_SIGN.get())).properties(properties -> properties.stacksTo(16)).register();
	public static final ItemEntry<ModBoatItem> SEQUOIA_BOAT = REGISTRATE.item("sequoia_boat", properties -> new ModBoatItem(ModBoatType.SEQUOIA, properties)).properties(properties -> properties.stacksTo(1)).register();

	public static final BlockEntry<PetrifiedWoodBlock> PETRIFIED_SEQUOIA_LOG = REGISTRATE.blockAndItem("petrified_sequoia_log", properties -> new PetrifiedWoodBlock(() -> LostWorldsItems.SEQUOIA_BARK_SAMPLE.get(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();
	public static final BlockEntry<RotatedPillarBlock> STRIPPED_PETRIFIED_SEQUOIA_LOG = REGISTRATE.blockAndItem("stripped_petrified_sequoia_log", RotatedPillarBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).strength(2.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();

	// Plants
	public static final BlockEntry<WaterPlantBlock> ARCHAEFRUTUS = REGISTRATE.block("archaefrutus", properties -> new WaterPlantBlock(Effects.DAMAGE_BOOST, 3, properties)).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).item(WaterPlantItem::new).build().addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<FlowerPotBlock> POTTED_ARCHAEFRUTUS = REGISTRATE.block("potted_archaefrutus", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> ARCHAEFRUTUS.get(), properties)).properties(properties -> properties.of(Material.DECORATION).instabreak().noOcclusion()).addLayer(() -> RenderType::translucent).color(() -> LostWorldsBlocks::getWaterColour).register();
	public static final BlockEntry<ModDoublePlantBlock> ALETHOPTERIS = REGISTRATE.block("alethopteris", ModDoublePlantBlock::new).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<BrazileaBlock> BRAZILEA = REGISTRATE.block("brazilea", BrazileaBlock::new).properties(properties -> properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).noOcclusion().noCollission()).item(BrazileaItem::new).build().addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<CalamtiesSuckowiiSaplingBlock> CALAMITES_SUCKOWII_SAPLING = REGISTRATE.block("calamites_suckowii_sapling", CalamtiesSuckowiiSaplingBlock::new).properties(properties -> properties.of(Material.BAMBOO_SAPLING).randomTicks().instabreak().noCollission().strength(1.0F).sound(SoundType.BAMBOO_SAPLING)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<CalamtiesSuckowiiBlock> CALAMITES_SUCKOWII = REGISTRATE.blockAndItem("calamites_suckowii", CalamtiesSuckowiiBlock::new).properties(properties -> properties.of(Material.BAMBOO, MaterialColor.PLANT).randomTicks().instabreak().strength(1.0F).sound(SoundType.BAMBOO).noOcclusion()).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<ModDoublePlantBlock> CEPHALOTAXUS = REGISTRATE.block("cephalotaxus", ModDoublePlantBlock::new).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<CycadBlock> CYCAD = REGISTRATE.block("cycad", CycadBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(1.0F, 1.5F).randomTicks().noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<CycadBlock> DICKSONIA = REGISTRATE.block("dicksonia", CycadBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).harvestTool(ToolType.AXE).strength(1.0F, 1.5F).randomTicks().noOcclusion().sound(SoundType.WOOD)).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<FlowerBlock> DILLHOFFIA = REGISTRATE.blockAndItem("dillhoffia", properties -> new FlowerBlock(Effects.BLINDNESS, 7, properties)).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<FlowerPotBlock> POTTED_DILLHOFFIA = REGISTRATE.block("potted_dillhoffia", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> DILLHOFFIA.get(), properties)).properties(properties -> properties.of(Material.DECORATION).instabreak().noOcclusion()).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<ModDoublePlantBlock> DUISBERGIA = REGISTRATE.blockAndItem("duisbergia", ModDoublePlantBlock::new).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<FlowerBlock> EUDICOTS = REGISTRATE.blockAndItem("block", properties -> new FlowerBlock(Effects.HUNGER, 10, properties)).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<FlowerPotBlock> POTTED_EUDICOTS = REGISTRATE.block("potted_eudicots", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> LostWorldsBlocks.EUDICOTS.get(), properties)).properties(properties -> properties.of(Material.DECORATION).instabreak().noOcclusion()).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<GroundFernsBlock> GROUND_FERNS = REGISTRATE.blockAndItem("ground_ferns", GroundFernsBlock::new).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<FlowerBlock> MAGNOLIA = REGISTRATE.blockAndItem("magnolia", properties -> new FlowerBlock(Effects.MOVEMENT_SLOWDOWN, 8, properties)).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<FlowerPotBlock> POTTED_MAGNOLIA = REGISTRATE.block("potted_magnolia", properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> LostWorldsBlocks.MAGNOLIA.get(), properties)).properties(properties -> properties.of(Material.DECORATION).instabreak().noOcclusion()).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<ModBushBlock> OSMUNDA = REGISTRATE.blockAndItem("osmunda", ModBushBlock::new).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).register();
	public static final BlockEntry<ModBushBlock> PERMIAN_DESERT_FERNS = REGISTRATE.blockAndItem("permian_desert_ferns", ModBushBlock::new).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<ModBushBlock> PERMIAN_DESERT_SHRUB = REGISTRATE.blockAndItem("permian_desert_shrub", ModBushBlock::new).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_BROWN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
//	public static final Block TEMPSKYA = REGISTRATE.block("tempskya", new QUINTUPLEPLANTBLOCK(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).register()
	public static final BlockEntry<ModDoublePlantBlock> WILLIAMSONIA = REGISTRATE.blockAndItem("williamsonia", ModDoublePlantBlock::new).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<ModBushBlock> ZAMITES = REGISTRATE.blockAndItem("zamites", ModBushBlock::new).properties(properties -> AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_GREEN).noCollission().noOcclusion().instabreak().sound(SoundType.GRASS)).addLayer(() -> RenderType::cutout).color(() -> LostWorldsBlocks::getGrassyColour).color(() -> LostWorldsBlocks::getGrassyColour).register();

	// Natural Blocks
	public static final BlockEntry<DiictodonBurrowBlock> DIICTODON_BURROW = REGISTRATE.blockAndItem("diictodon_burrow", DiictodonBurrowBlock::new).properties(properties -> properties.of(Material.SAND, MaterialColor.SAND).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND).randomTicks()).register();
	public static final BlockEntry<Block> TUNNELED_SOIL = REGISTRATE.blockAndItem("tunneled_soil", Block::new).properties(properties -> properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL)).register();

	public static final BlockEntry<NautilusShellBlock> NAUTILUS_SHELL = REGISTRATE.blockAndItem("nautilus_shell", NautilusShellBlock::new).properties(properties -> properties.of(Material.SHULKER_SHELL).instabreak().noOcclusion().sound(SoundType.ANVIL)).register();

	public static final BlockEntry<GeyserBlock> GEYSER_BLOCK = REGISTRATE.blockAndItem("geyser_block", GeyserBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.75F).noOcclusion()).register();

	public static final BlockEntry<Block> NESTING_BLOCK = REGISTRATE.blockAndItem("nesting_block", Block::new).properties(properties -> properties.of(Material.PLANT, MaterialColor.COLOR_BROWN).harvestTool(ToolType.SHOVEL).strength(0.3F).noOcclusion().sound(SoundType.GRASS)).register();

	public static final BlockEntry<DeadSpongeColonyBlock> DEAD_SPONGE_COLONY = REGISTRATE.blockAndItem("dead_sponge_colony", DeadSpongeColonyBlock::new).properties(properties -> properties.of(Material.REPLACEABLE_PLANT, MaterialColor.STONE).noOcclusion().strength(1.5F, 6.0F).sound(SoundType.GRASS)).register();
	public static final BlockEntry<SpongeColonyBlock> SPONGE_COLONY = REGISTRATE.blockAndItem("sponge_colony", properties -> new SpongeColonyBlock(() -> LostWorldsBlocks.DEAD_SPONGE_COLONY.get(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().noOcclusion().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK)).register();

	// Jurassic Park Building Blocks
	public static final BlockEntry<Block> LIGHT_CONCRETE = REGISTRATE.blockAndItem("light_concrete", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<StairsBlock> LIGHT_CONCRETE_STAIRS = REGISTRATE.stairBlock("light_concrete_stairs", "light_concrete", properties -> new StairsBlock(() -> LostWorldsBlocks.LIGHT_CONCRETE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<SlabBlock> LIGHT_CONCRETE_SLAB = REGISTRATE.slabBlock("light_concrete_slab", "light_concrete", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<WallBlock> LIGHT_CONCRETE_WALL = REGISTRATE.wallBlock("light_concrete_wall", "light_concrete", WallBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)).register();
	public static final BlockEntry<PressurePlateBlock> LIGHT_CONCRETE_PRESSURE_PLATE = REGISTRATE.pressurePlateBlock("light_concrete_pressure_plate", "light_concrete", properties -> new PressurePlateBlock(Sensitivity.MOBS, properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)).register();
	public static final BlockEntry<StoneButtonBlock> LIGHT_CONCRETE_BUTTON = REGISTRATE.buttonBlock("light_concrete_button", "light_concrete", StoneButtonBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.0F, 8.0F).noCollission().sound(SoundType.STONE)).register();

	public static final BlockEntry<ConnectedTexturesBlock> POLISHED_LIGHT_CONCRETE = REGISTRATE.blockAndItem("polished_light_concrete", properties -> new ConnectedTexturesBlock("polished_light_concrete", true, properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE)).register();

	public static final BlockEntry<Block> ACCENT_LIGHT_CONCRETE = REGISTRATE.blockAndItem("accent_light_concrete", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE)).register();
	public static final BlockEntry<StairsBlock> ACCENT_LIGHT_CONCRETE_STAIRS = REGISTRATE.stairBlock("accent_light_concrete_stairs", "accent_light_concrete", properties -> new StairsBlock(() -> LostWorldsBlocks.LIGHT_CONCRETE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE)).register();
	public static final BlockEntry<SlabBlock> ACCENT_LIGHT_CONCRETE_SLAB = REGISTRATE.slabBlock("accent_light_concrete_slab", "accent_light_concrete", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE)).register();

	public static final BlockEntry<Block> WOODEN_PLANKS = REGISTRATE.blockAndItem("wooden_planks", Block::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<StairsBlock> WOODEN_PLANKS_STAIRS = REGISTRATE.stairBlock("wooden_planks_stairs", "wooden_planks", properties -> new StairsBlock(() -> LostWorldsBlocks.WOODEN_PLANKS.getDefaultState(), properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<SlabBlock> WOODEN_PLANKS_SLAB = REGISTRATE.slabBlock("wooden_planks_slab", "wooden_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceBlock> WOODEN_PLANKS_FENCE = REGISTRATE.fenceBlock("wooden_planks_fence", "wooden_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceGateBlock> WOODEN_PLANKS_FENCE_GATE = REGISTRATE.fenceGateBlock("wooden_planks_fence_gate", "wooden_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<PressurePlateBlock> WOODEN_PLANKS_PRESSURE_PLATE = REGISTRATE.pressurePlateBlock("wooden_planks_pressure_plate", "wooden_planks", properties -> new PressurePlateBlock(Sensitivity.EVERYTHING, properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WoodButtonBlock> WOODEN_PLANKS_BUTTON = REGISTRATE.buttonBlock("wooden_planks_button", "wooden_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(1.2F, 0.5F).noCollission().sound(SoundType.WOOD)).register();

	public static final BlockEntry<FenceBlock> METAL_FENCE = REGISTRATE.fenceBlock("metal_fence", FenceBlock::new).properties(properties -> properties.of(Material.METAL, MaterialColor.COLOR_RED).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(4.5F, 6.0F).sound(SoundType.METAL)).register();

	public static final BlockEntry<HayBlock> THATCH_BUNDLE = REGISTRATE.blockAndItem("thatch_bundle", HayBlock::new).properties(properties -> properties.copy(Blocks.HAY_BLOCK)).register();

	public static final BlockEntry<DoorBlock> OUTDOOR_TOILET_DOOR = REGISTRATE.blockAndItem("outdoor_toilet_door", DoorBlock::new).properties(properties -> properties.copy(Blocks.OAK_DOOR)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DoorBlock> VISITOR_CENTER_DOOR = REGISTRATE.blockAndItem("visitor_center_door", DoorBlock::new).properties(properties -> properties.copy(Blocks.OAK_DOOR)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DoorBlock> SECURITY_DOOR = REGISTRATE.blockAndItem("security_door", DoorBlock::new).properties(properties -> properties.copy(Blocks.IRON_DOOR)).addLayer(() -> RenderType::cutout).register();

	// Jurassic World Building Blocks
	public static final BlockEntry<Block> DARK_CONCRETE = REGISTRATE.blockAndItem("dark_concrete", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<StairsBlock> DARK_CONCRETE_STAIRS = REGISTRATE.stairBlock("dark_concrete_stairs", "dark_concrete", properties -> new StairsBlock(() -> LostWorldsBlocks.DARK_CONCRETE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<SlabBlock> DARK_CONCRETE_SLAB = REGISTRATE.slabBlock("dark_concrete_slab", "dark_concrete", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<WallBlock> DARK_CONCRETE_WALL = REGISTRATE.wallBlock("dark_concrete_wall", "dark_concrete", WallBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<PressurePlateBlock> DARK_CONCRETE_PRESSURE_PLATE = REGISTRATE.pressurePlateBlock("dark_concrete_pressure_plate", "dark_concrete", properties -> new PressurePlateBlock(Sensitivity.MOBS, properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).noCollission().sound(SoundType.STONE)).register();
	public static final BlockEntry<StoneButtonBlock> DARK_CONCRETE_BUTTON = REGISTRATE.buttonBlock("dark_concrete_button", "dark_concrete", StoneButtonBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.0F, 10.0F).noCollission().sound(SoundType.STONE)).register();

	public static final BlockEntry<ConnectedTexturesBlock> POLISHED_DARK_CONCRETE = REGISTRATE.blockAndItem("polished_dark_concrete", properties -> new ConnectedTexturesBlock("polished_dark_concrete", true, properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(6.5F, 8.5F).sound(SoundType.STONE)).register();

	public static final BlockEntry<Block> ACCENT_DARK_CONCRETE = REGISTRATE.blockAndItem("accent_dark_concrete", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.5F, 10.5F).sound(SoundType.STONE)).register();
	public static final BlockEntry<StairsBlock> ACCENT_DARK_CONCRETE_STAIRS = REGISTRATE.stairBlock("accent_dark_concrete_stairs", "accent_dark_concrete", properties -> new StairsBlock(() -> LostWorldsBlocks.DARK_CONCRETE.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.5F, 10.5F).sound(SoundType.STONE)).register();
	public static final BlockEntry<SlabBlock> ACCENT_DARK_CONCRETE_SLAB = REGISTRATE.slabBlock("accent_dark_concrete_slab", "accent_dark_concrete", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops().strength(8.5F, 10.5F).sound(SoundType.STONE)).register();

	public static final BlockEntry<Block> REFINED_WOODEN_PLANKS = REGISTRATE.blockAndItem("refined_wooden_planks", Block::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<StairsBlock> REFINED_WOODEN_PLANKS_STAIRS = REGISTRATE.stairBlock("refined_wooden_planks_stairs", "refined_wooden_planks", properties -> new StairsBlock(() -> LostWorldsBlocks.REFINED_WOODEN_PLANKS.getDefaultState(), properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<SlabBlock> REFINED_WOODEN_PLANKS_SLAB = REGISTRATE.slabBlock("refined_wooden_planks_slab", "refined_wooden_planks", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceBlock> REFINED_WOODEN_PLANKS_FENCE = REGISTRATE.fenceBlock("refined_wooden_planks_fence", "refined_wooden_planks", FenceBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<FenceGateBlock> REFINED_WOODEN_PLANKS_FENCE_GATE = REGISTRATE.fenceGateBlock("refined_wooden_planks_fence_gate", "refined_wooden_planks", FenceGateBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<PressurePlateBlock> REFINED_WOODEN_PLANKS_PRESSURE_PLATE = REGISTRATE.pressurePlateBlock("refined_wooden_planks_pressure_plate", "refined_wooden_planks", properties -> new PressurePlateBlock(Sensitivity.EVERYTHING, properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).noCollission().sound(SoundType.WOOD)).register();
	public static final BlockEntry<WoodButtonBlock> REFINED_WOODEN_PLANKS_BUTTON = REGISTRATE.buttonBlock("refined_wooden_planks_button", "refined_wooden_planks", WoodButtonBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).noCollission().sound(SoundType.WOOD)).register();

	public static final BlockEntry<Block> TILE = REGISTRATE.blockAndItem("tile", Block::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<StairsBlock> TILE_STAIRS = REGISTRATE.stairBlock("tile_stairs", "tile", properties -> new StairsBlock(() -> LostWorldsBlocks.TILE.getDefaultState(), properties)).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)).register();
	public static final BlockEntry<SlabBlock> TILE_SLAB = REGISTRATE.slabBlock("tile_slab", "tile", SlabBlock::new).properties(properties -> properties.of(Material.WOOD, MaterialColor.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(2.7F, 1.9F).sound(SoundType.WOOD)).register();

	public static final BlockEntry<StandingSignBlock> GLASS_SIGN = REGISTRATE.signBlock("glass_sign", "glass_sign_particle", properties -> new StandingSignBlock(properties, ModWoodType.GLASS)).properties(properties -> properties.of(Material.GLASS, MaterialColor.COLOR_BLACK).instabreak().noOcclusion().noCollission().sound(SoundType.GLASS)).register();
	public static final BlockEntry<WallSignBlock> GLASS_WALL_SIGN = REGISTRATE.signBlock("glass_wall_sign", "glass_sign_particle", properties -> new WallSignBlock(properties, ModWoodType.GLASS)).properties(properties -> properties.of(Material.GLASS, MaterialColor.COLOR_BLACK).instabreak().noOcclusion().noCollission().sound(SoundType.GLASS)).register();
	public static final ItemEntry<SignItem> GLASS_SIGN_ITEM = REGISTRATE.item("glass_sign", properties -> new SignItem(properties, GLASS_SIGN.get(), GLASS_WALL_SIGN.get())).properties(properties -> properties.stacksTo(16)).register();

	public static final BlockEntry<DoorBlock> GLASS_SHOP_DOOR = REGISTRATE.block("glass_shop_door", DoorBlock::new).properties(properties -> properties.copy(Blocks.OAK_DOOR)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DoorBlock> INNOVATION_CENTER_DOOR = REGISTRATE.block("innovation_center_door", DoorBlock::new).properties(properties -> properties.copy(Blocks.OAK_DOOR)).addLayer(() -> RenderType::cutout).register();
	public static final BlockEntry<DoorBlock> BACK_DOOR = REGISTRATE.block("back_door", DoorBlock::new).properties(properties -> properties.copy(Blocks.IRON_DOOR)).addLayer(() -> RenderType::cutout).register();

	public static final BlockEntry<GlassBlock> CLEAR_GLASS = REGISTRATE.blockAndItem("clear_glass", GlassBlock::new).properties(properties -> properties.of(Material.GLASS).strength(0.3F).noOcclusion().sound(SoundType.GLASS)).addLayer(() -> RenderType::translucent).register();
	public static final BlockEntry<GlassBlock> TINTED_GLASS = REGISTRATE.blockAndItem("tinted_glass", GlassBlock::new).properties(properties -> properties.of(Material.GLASS).strength(0.3F).noOcclusion().sound(SoundType.GLASS)).addLayer(() -> RenderType::translucent).register();
	public static final BlockEntry<GlassBlock> SHADED_GLASS = REGISTRATE.blockAndItem("shaded_glass", GlassBlock::new).properties(properties -> properties.of(Material.GLASS).strength(0.3F).noOcclusion().sound(SoundType.GLASS)).addLayer(() -> RenderType::translucent).register();
	public static final DyedBlockList<GlassBlock> COLOURED_GLASS = new DyedBlockList<>((colour) -> {
		String colourName = colour.getSerializedName();
		return REGISTRATE.blockAndItem(colourName + "_glass", GlassBlock::new).properties(properties -> properties.of(Material.GLASS).strength(0.3F).noOcclusion().sound(SoundType.GLASS)).addLayer(() -> RenderType::translucent).register();
	});
	public static final BlockEntry<PaneBlock> CLEAR_GLASS_PANE = REGISTRATE.blockAndItem("clear_glass_pane", PaneBlock::new).properties(properties -> properties.of(Material.GLASS).strength(0.3F).noOcclusion().sound(SoundType.GLASS)).addLayer(() -> RenderType::translucent).blockstate((block, provider) -> provider.paneBlock(block.get(), provider.modLoc("clear_glass"), provider.modLoc("clear_glass_pane_top"))).addLayer(() -> RenderType::translucent).register();
	public static final BlockEntry<PaneBlock> TINTED_GLASS_PANE = REGISTRATE.blockAndItem("tinted_glass_pane", PaneBlock::new).properties(properties -> properties.of(Material.GLASS).strength(0.3F).noOcclusion().sound(SoundType.GLASS)).addLayer(() -> RenderType::translucent).blockstate((block, provider) -> provider.paneBlock(block.get(), provider.modLoc("tinted_glass"), provider.modLoc("clear_glass_pane_top"))).addLayer(() -> RenderType::translucent).register();
	public static final BlockEntry<PaneBlock> SHADED_GLASS_PANE = REGISTRATE.blockAndItem("shaded_glass_pane", PaneBlock::new).properties(properties -> properties.of(Material.GLASS).strength(0.3F).noOcclusion().sound(SoundType.GLASS)).addLayer(() -> RenderType::translucent).blockstate((block, provider) -> provider.paneBlock(block.get(), provider.modLoc("shaded_glass"), provider.modLoc("clear_glass_pane_top"))).addLayer(() -> RenderType::translucent).register();
	public static final DyedBlockList<PaneBlock> COLOURED_GLASS_PANE = new DyedBlockList<>((colour) -> {
		String colourName = colour.getSerializedName();
		return REGISTRATE.blockAndItem(colourName + "_glass_pane", PaneBlock::new).properties(properties -> properties.of(Material.GLASS).strength(0.3F).noOcclusion().sound(SoundType.GLASS)).addLayer(() -> RenderType::translucent).blockstate((block, provider) -> provider.paneBlock(block.get(), provider.modLoc(colourName + "_glass"), provider.modLoc("clear_glass_pane_top"))).addLayer(() -> RenderType::translucent).register();
	});
	public static final DyedBlockList<Block> COLOURED_DECORATION_BLOCK = new DyedBlockList<>((colour) -> {
		String colourName = colour.getSerializedName();
		return REGISTRATE.blockAndItem(colourName + "_decoration_block", Block::new).properties(properties -> properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)).register();
	});

	// Mixed Park
	public static final BlockEntry<Block> PAVEMENT = REGISTRATE.blockAndItem("pavement", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<StairsBlock> PAVEMENT_SLOPE = REGISTRATE.stairBlock("pavement_slope", "pavement", properties -> new StairsBlock(() -> LostWorldsBlocks.PAVEMENT.getDefaultState(), properties)).properties(properties -> properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.0F).sound(SoundType.STONE)).register();
	public static final BlockEntry<SlabBlock> RAISED_PAVEMENT = REGISTRATE.slabBlock("raised_pavement", "pavement", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)).register();

	public static final BlockEntry<Block> GRAVEL_ROAD = REGISTRATE.blockAndItem("gravel_road", Block::new).properties(properties -> properties.of(Material.SAND, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND)).register();
	public static final BlockEntry<SlabBlock> RAISED_GRAVEL_ROAD = REGISTRATE.slabBlock("raised_gravel_road", "gravel_road", SlabBlock::new).properties(properties -> properties.of(Material.SAND, MaterialColor.TERRACOTTA_LIGHT_GRAY).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.SAND)).register();

	public static final BlockEntry<Block> DIRT_ROAD = REGISTRATE.blockAndItem("dirt_road", Block::new).properties(properties -> properties.of(Material.DIRT, MaterialColor.DIRT).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.GRASS)).register();
	public static final BlockEntry<SlabBlock> RAISED_DIRT_ROAD = REGISTRATE.slabBlock("raised_dirt_road", "dirt_road", SlabBlock::new).properties(properties -> properties.of(Material.DIRT, MaterialColor.DIRT).harvestTool(ToolType.SHOVEL).strength(1.5F).sound(SoundType.GRASS)).register();

	public static final BlockEntry<Block> PAVED_ROAD = REGISTRATE.blockAndItem("paved_road", Block::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.STONE)).register();
	public static final BlockEntry<SlabBlock> RAISED_PAVED_ROAD = REGISTRATE.slabBlock("raised_paved_road", "paved_road", SlabBlock::new).properties(properties -> properties.of(Material.STONE, MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.STONE)).register();

	private static Boolean ocelotOrParrot(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}

	private static boolean never(BlockState state, IBlockReader reader, BlockPos pos) {
		return false;
	}

	private static LeavesBlock leaves(AbstractBlock.Properties properties) {
		return new LeavesBlock(properties.of(Material.LEAVES).strength(0.2F).harvestTool(ToolType.HOE).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(LostWorldsBlocks::ocelotOrParrot).isSuffocating(LostWorldsBlocks::never).isViewBlocking(LostWorldsBlocks::never));
	}

	public static IBlockColor getGrassyColour() {
		return (state, world, pos, layer) -> pos != null && world != null ? BiomeColors.getAverageGrassColor(world, pos) : FoliageColors.get(0.5D, 1.0D);
	}

	public static IBlockColor getWaterColour() {
		return (state, world, pos, layer) -> world != null && pos != null ? BiomeColors.getAverageWaterColor(world, pos) : -1;
	}

	public static IBlockColor getEggBlock(DinoTypes types) {
		return LostWorldsConfig.CLIENT_CONFIG.eggsSetColour.get() ? (state, world, pos, layer) -> pos != null && world != null ? BiomeColors.getAverageGrassColor(world, pos) : FoliageColors.get(0.5D, 1.0D) : new IBlockColor() {
			@Override
			public int getColor(BlockState state, IBlockDisplayReader reader, BlockPos pos, int colour) {
				return types.getSetEggColour();
			}
		};
	}

	public static Item register(String id, Item item) {
		item.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.ITEMS.register(item);
		return item;
	}

	public static void registerSignBlock(Supplier<? extends Block> sign) {
		synchronized (SIGN_BLOCKS) {
			SIGN_BLOCKS.add(sign);
		}
	}

	public static void forEachSignBlock(Consumer<? super Block> consumer) {
		SIGN_BLOCKS.forEach(block -> consumer.accept(block.get()));
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Blocks");

		registerSignBlock(() -> LostWorldsBlocks.ARAUCARIA_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.ARAUCARIA_WALL_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.CALAMITES_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.CALAMITES_WALL_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.CONIFER_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.CONIFER_WALL_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.CYPRESS_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.CYPRESS_WALL_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.GINKGO_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.GINKGO_WALL_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.GLASS_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.GLASS_WALL_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.SCORCHED_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.SCORCHED_WALL_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.SEQUOIA_SIGN.get());
		registerSignBlock(() -> LostWorldsBlocks.SEQUOIA_WALL_SIGN.get());

		for (DinoTypes types : DinoTypes.eggLaying()) {
			BlockEntry<Block> egg = REGISTRATE.block(types.getId() + "_egg", properties -> types.getEgg(types.getEntityType())).color(() -> () -> LostWorldsBlocks.getEggBlock(types)).register();
			types.setEgg(() -> egg.get());
		}

		for (DinoTypes types : DinoTypes.values()) {
			if (types == DinoTypes.NAUTILUS) {
				BlockEntry<NautilusShellBlock> shell = REGISTRATE.blockAndItem("fossilized_nautilus_shell", NautilusShellBlock::new).properties(properties -> properties.of(Material.SHULKER_SHELL).instabreak().noOcclusion().sound(SoundType.ANVIL)).register();
				types.setExtraBlock(() -> shell.get());
			}
		}
	}
}
