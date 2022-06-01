package lostworlds.server.biome.features.configured;

import static lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures.register;

import java.util.List;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.block.Damage;
import lostworlds.server.block.Egg;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.PlantFossilBlock;
import lostworlds.server.block.Plants;
import lostworlds.server.block.SoftDirtBlock;
import lostworlds.server.entity.utils.enums.TimeEras;
import lostworlds.server.util.registrate.WoodTypes;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class OreFeatures {
	// Rules
	public static final RuleTest DIRT = new BlockMatchTest(Blocks.DIRT);
	public static final RuleTest BASALT = new BlockMatchTest(Blocks.BASALT);
	public static final RuleTest STONE = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
	public static final RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
	public static final RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
	public static final RuleTest PERMIAN_STONE = new TagMatchTest(LostWorldsTags.ModBlockTags.BASE_STONE_PERMIAN.tag);
	public static final RuleTest PERMIAN_STONE_ORE_REPLACEABLES = new TagMatchTest(LostWorldsTags.ModBlockTags.PERMIAN_STONE_ORE_REPLACEABLES.tag);
	public static final RuleTest PERMIAN_DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(LostWorldsTags.ModBlockTags.PERMIAN_DEEPSLATE_ORE_REPLACEABLES.tag);
	public static final RuleTest JURASSIC_STONE = new TagMatchTest(LostWorldsTags.ModBlockTags.BASE_STONE_JURASSIC.tag);

	// Overworld
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> SILT_PATCH = register("silt_patch", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.SILT.getDefaultState(), LostWorldsConfig.COMMON_CONFIG.siltVeinSize.get()));

	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PETRIFIED_ARAUCARIA = register("petrified_araucaria", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.PETRIFIED_LOG).get().getDefaultState(), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PETRIFIED_CALAMITES = register("petrified_calamties", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.PETRIFIED_LOG).get().getDefaultState(), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PETRIFIED_CONIFER = register("petrified_conifer", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.CONIFER.getBlock(WoodTypes.PETRIFIED_LOG).get().getDefaultState(), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PETRIFIED_CYPRESS = register("petrified_cypress", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.PETRIFIED_LOG).get().getDefaultState(), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PETRIFIED_GINKGO = register("petrified_ginkgo", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.GINKGO.getBlock(WoodTypes.PETRIFIED_LOG).get().getDefaultState(), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PETRIFIED_SEQUOIA = register("petrified_sequoia", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.PETRIFIED_LOG).get().getDefaultState(), 1));

	public static final List<OreConfiguration.TargetBlockState> AMBER_ORE_TARGET_LIST = List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, LostWorldsBlocks.AMBER_ORE.getDefaultState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, LostWorldsBlocks.DEEPSLATE_AMBER_ORE.getDefaultState()));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_AMBER_ORE = register("overworld_amber_ore", Feature.ORE, new OreConfiguration(AMBER_ORE_TARGET_LIST, LostWorldsConfig.COMMON_CONFIG.amberVeinSize.get(), 0.3F));

	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_PLANT_FOSSIL_ALETHOPTERIS = register("overworld_plant_fossil_alethopteris", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.PLANT_FOSSIL.getDefaultState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.ALETHOPTERIS).setValue(PlantFossilBlock.DAMAGE, Damage.NONE), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_PLANT_FOSSIL_BRAZILEA = register("overworld_plant_fossil_brazilea", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.PLANT_FOSSIL.getDefaultState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.BRAZILEA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_PLANT_FOSSIL_CALAMITES_SUCKOWII = register("overworld_plant_fossil_calamites_suckowii", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.PLANT_FOSSIL.getDefaultState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.CALAMITES_SUCKOWII).setValue(PlantFossilBlock.DAMAGE, Damage.NONE), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_PLANT_FOSSIL_CEPHALOTAXUS = register("overworld_plant_fossil_cephalotaxus", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.PLANT_FOSSIL.getDefaultState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.CEPHALOTAXUS).setValue(PlantFossilBlock.DAMAGE, Damage.NONE), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_PLANT_FOSSIL_DILLHOFFIA = register("overworld_plant_fossil_dillhoffia", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.PLANT_FOSSIL.getDefaultState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.DILLHOFFIA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_PLANT_FOSSIL_DUISBERGIA = register("overworld_plant_fossil_duisbergia", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.PLANT_FOSSIL.getDefaultState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.DUISBERGIA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_PLANT_FOSSIL_OSMUNDA = register("overworld_plant_fossil_osmunda", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.PLANT_FOSSIL.getDefaultState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.OSMUNDA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_PLANT_FOSSIL_WILLIAMSONIA = register("overworld_plant_fossil_williamsonia", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.PLANT_FOSSIL.getDefaultState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.WILLIAMSONIA).setValue(PlantFossilBlock.DAMAGE, Damage.NONE), 1));;
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_PLANT_FOSSIL_ZAMITES = register("overworld_plant_fossil_zamites", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.PLANT_FOSSIL.getDefaultState().setValue(PlantFossilBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(PlantFossilBlock.POTENTIAL_PLANT, Plants.ZAMITES).setValue(PlantFossilBlock.DAMAGE, Damage.NONE), 1));

	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_TINY_NEST = register("overworld_tiny_nest", Feature.ORE, new OreConfiguration(DIRT, LostWorldsBlocks.SOFT_DIRT.getDefaultState().setValue(SoftDirtBlock.EGG, Egg.TINY), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_SMALL_NEST = register("overworld_small_nest", Feature.ORE, new OreConfiguration(DIRT, LostWorldsBlocks.SOFT_DIRT.getDefaultState().setValue(SoftDirtBlock.EGG, Egg.SMALL), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_MEDIUM_NEST = register("overworld_medium_nest", Feature.ORE, new OreConfiguration(DIRT, LostWorldsBlocks.SOFT_DIRT.getDefaultState().setValue(SoftDirtBlock.EGG, Egg.MEDIUM), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_LARGE_NEST = register("overworld_large_nest", Feature.ORE, new OreConfiguration(DIRT, LostWorldsBlocks.SOFT_DIRT.getDefaultState().setValue(SoftDirtBlock.EGG, Egg.LARGE), 1));

	// Permian
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_MAGMA_ORE = register("permian_magma_ore", Feature.ORE, new OreConfiguration(PERMIAN_STONE, Blocks.MAGMA_BLOCK.defaultBlockState(), 33));

	public static final List<OreConfiguration.TargetBlockState> PERMIAN_COAL_ORE_TARGET_LIST = List.of(OreConfiguration.target(PERMIAN_STONE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_COAL_ORE.getDefaultState()), OreConfiguration.target(PERMIAN_DEEPSLATE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_DEEPSLATE_COAL_ORE.getDefaultState()));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_COAL_ORE = register("permian_coal_ore", Feature.ORE, new OreConfiguration(PERMIAN_COAL_ORE_TARGET_LIST, 17));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_COAL_ORE_BURIED = register("permian_coal_ore_buried", Feature.ORE, new OreConfiguration(PERMIAN_COAL_ORE_TARGET_LIST, 17, 0.5F));
	public static final List<OreConfiguration.TargetBlockState> PERMIAN_IRON_ORE_TARGET_LIST = List.of(OreConfiguration.target(PERMIAN_STONE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_IRON_ORE.getDefaultState()), OreConfiguration.target(PERMIAN_DEEPSLATE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_DEEPSLATE_IRON_ORE.getDefaultState()));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_IRON_ORE = register("permian_iron_ore", Feature.ORE, new OreConfiguration(PERMIAN_IRON_ORE_TARGET_LIST, 9));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_IRON_ORE_SMALL = register("permian_iron_ore_small", Feature.ORE, new OreConfiguration(PERMIAN_IRON_ORE_TARGET_LIST, 4));
	public static final List<OreConfiguration.TargetBlockState> PERMIAN_GOLD_ORE_TARGET_LIST = List.of(OreConfiguration.target(PERMIAN_STONE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_GOLD_ORE.getDefaultState()), OreConfiguration.target(PERMIAN_DEEPSLATE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_DEEPSLATE_GOLD_ORE.getDefaultState()));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_GOLD_ORE = register("permian_gold_ore", Feature.ORE, new OreConfiguration(PERMIAN_GOLD_ORE_TARGET_LIST, 9));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_GOLD_ORE_BURIED = register("permian_gold_ore_buried", Feature.ORE, new OreConfiguration(PERMIAN_GOLD_ORE_TARGET_LIST, 9, 0.5F));
	public static final List<OreConfiguration.TargetBlockState> PERMIAN_REDSTONE_ORE_TARGET_LIST = List.of(OreConfiguration.target(PERMIAN_STONE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_REDSTONE_ORE.getDefaultState()), OreConfiguration.target(PERMIAN_DEEPSLATE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_DEEPSLATE_REDSTONE_ORE.getDefaultState()));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_REDSTONE_ORE = register("permian_redstone_ore", Feature.ORE, new OreConfiguration(PERMIAN_REDSTONE_ORE_TARGET_LIST, 8));
	public static final List<OreConfiguration.TargetBlockState> PERMIAN_DIAMOND_ORE_TARGET_LIST = List.of(OreConfiguration.target(PERMIAN_STONE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_DIAMOND_ORE.getDefaultState()), OreConfiguration.target(PERMIAN_DEEPSLATE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_DEEPSLATE_DIAMOND_ORE.getDefaultState()));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_DIAMOND_ORE_SMALL = register("permian_diamond_ore_small", Feature.ORE, new OreConfiguration(PERMIAN_DIAMOND_ORE_TARGET_LIST, 4, 0.5F));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_DIAMOND_ORE_LARGE = register("permian_diamond_ore_large", Feature.ORE, new OreConfiguration(PERMIAN_DIAMOND_ORE_TARGET_LIST, 12, 0.7F));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_DIAMOND_ORE_BURIED = register("permian_diamond_ore_buried", Feature.ORE, new OreConfiguration(PERMIAN_DIAMOND_ORE_TARGET_LIST, 8, 1.0F));
	public static final List<OreConfiguration.TargetBlockState> PERMIAN_LAPIS_ORE_TARGET_LIST = List.of(OreConfiguration.target(PERMIAN_STONE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_LAPIS_ORE.getDefaultState()), OreConfiguration.target(PERMIAN_DEEPSLATE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_DEEPSLATE_LAPIS_ORE.getDefaultState()));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_LAPIS_ORE = register("permian_lapis_ore", Feature.ORE, new OreConfiguration(PERMIAN_LAPIS_ORE_TARGET_LIST, 7));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_LAPIS_ORE_BURIED = register("permian_lapis_ore_buried", Feature.ORE, new OreConfiguration(PERMIAN_LAPIS_ORE_TARGET_LIST, 7, 1.0F));
	public static final List<OreConfiguration.TargetBlockState> PERMIAN_EMERALD_ORE_TARGET_LIST = List.of(OreConfiguration.target(PERMIAN_STONE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_EMERALD_ORE.getDefaultState()), OreConfiguration.target(PERMIAN_DEEPSLATE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_DEEPSLATE_EMERALD_ORE.getDefaultState()));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_EMERALD_ORE = register("permian_emerald_ore", Feature.ORE, new OreConfiguration(PERMIAN_EMERALD_ORE_TARGET_LIST, 3));
	public static final List<OreConfiguration.TargetBlockState> PERMIAN_COPPER_ORE_TARGET_LIST = List.of(OreConfiguration.target(PERMIAN_STONE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_COPPER_ORE.getDefaultState()), OreConfiguration.target(PERMIAN_DEEPSLATE_ORE_REPLACEABLES, LostWorldsBlocks.PERMIAN_DEEPSLATE_COPPER_ORE.getDefaultState()));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_COPPER_ORE_SMALL = register("permian_copper_ore_small", Feature.ORE, new OreConfiguration(PERMIAN_COPPER_ORE_TARGET_LIST, 10));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_COPPER_ORE_LARGE = register("permian_copper_ore_large", Feature.ORE, new OreConfiguration(PERMIAN_COPPER_ORE_TARGET_LIST, 20));

	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_DIRT_ORE = register("permian_dirt_ore", Feature.ORE, new OreConfiguration(PERMIAN_STONE, Blocks.DIRT.defaultBlockState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_GRAVEL_ORE = register("permian_gravel_ore", Feature.ORE, new OreConfiguration(PERMIAN_STONE, Blocks.GRAVEL.defaultBlockState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_LATERLITE_ORE = register("permian_laterlite_ore", Feature.ORE, new OreConfiguration(PERMIAN_STONE, LostWorldsBlocks.LATERLITE.getDefaultState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_RAW_MARBLE_ORE = register("permian_raw_marble_ore", Feature.ORE, new OreConfiguration(PERMIAN_STONE, LostWorldsBlocks.RAW_MARBLE.getDefaultState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PERMIAN_LIMESTONE_ORE = register("permian_limestone_ore", Feature.ORE, new OreConfiguration(PERMIAN_STONE, LostWorldsBlocks.LIMESTONE.getDefaultState(), 33));

	// Jurassic
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_MAGMA_ORE = register("jurassic_magma_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, Blocks.MAGMA_BLOCK.defaultBlockState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_COAL_ORE = register("jurassic_coal_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, LostWorldsBlocks.JURASSIC_COAL_ORE.getDefaultState(), 17));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_IRON_ORE = register("jurassic_iron_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, LostWorldsBlocks.JURASSIC_IRON_ORE.getDefaultState(), 9));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_GOLD_ORE = register("jurassic_gold_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, LostWorldsBlocks.JURASSIC_GOLD_ORE.getDefaultState(), 9));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_REDSTONE_ORE = register("jurassic_redstone_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, LostWorldsBlocks.JURASSIC_REDSTONE_ORE.getDefaultState(), 8));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_DIAMOND_ORE = register("jurassic_diamond_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, LostWorldsBlocks.JURASSIC_DIAMOND_ORE.getDefaultState(), 8));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_LAPIS_ORE = register("jurassic_lapis_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, LostWorldsBlocks.JURASSIC_LAPIS_ORE.getDefaultState(), 7));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_EMERALD_ORE = register("jurassic_emerald_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, LostWorldsBlocks.JURASSIC_EMERALD_ORE.getDefaultState(), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_COPPER_ORE = register("jurassic_copper_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, LostWorldsBlocks.JURASSIC_COPPER_ORE.getDefaultState(), 10));

	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_DIRT_ORE = register("jurassic_dirt_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, Blocks.DIRT.defaultBlockState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_GRAVEL_ORE = register("jurassic_gravel_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, Blocks.GRAVEL.defaultBlockState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_LATERLITE_ORE = register("jurassic_laterlite_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, LostWorldsBlocks.LATERLITE.getDefaultState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_RAW_MARBLE_ORE = register("jurassic_raw_marble_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, LostWorldsBlocks.RAW_MARBLE.getDefaultState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> JURASSIC_LIMESTONE_ORE = register("jurassic_limestone_ore", Feature.ORE, new OreConfiguration(JURASSIC_STONE, LostWorldsBlocks.LIMESTONE.getDefaultState(), 33));

	// Creataceous
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_MAGMA_ORE = register("cretaceous_magma_ore", Feature.ORE, new OreConfiguration(STONE, Blocks.MAGMA_BLOCK.defaultBlockState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_COAL_ORE = register("cretaceous_coal_ore", Feature.ORE, new OreConfiguration(STONE, Blocks.COAL_ORE.defaultBlockState(), 17));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_IRON_ORE = register("cretaceous_iron_ore", Feature.ORE, new OreConfiguration(STONE, Blocks.IRON_ORE.defaultBlockState(), 9));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_GOLD_ORE = register("cretaceous_gold_ore", Feature.ORE, new OreConfiguration(STONE, Blocks.GOLD_ORE.defaultBlockState(), 9));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_REDSTONE_ORE = register("cretaceous_redstone_ore", Feature.ORE, new OreConfiguration(STONE, Blocks.REDSTONE_ORE.defaultBlockState(), 8));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_DIAMOND_ORE = register("cretaceous_diamond_ore", Feature.ORE, new OreConfiguration(STONE, Blocks.DIAMOND_ORE.defaultBlockState(), 8));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_LAPIS_ORE = register("cretaceous_lapis_ore", Feature.ORE, new OreConfiguration(STONE, Blocks.LAPIS_ORE.defaultBlockState(), 7));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_EMERALD_ORE = register("cretaceous_emerald_ore", Feature.ORE, new OreConfiguration(STONE, Blocks.EMERALD_ORE.defaultBlockState(), 1));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_COPPER_ORE = register("cretaceous_copper_ore", Feature.ORE, new OreConfiguration(STONE, Blocks.COPPER_ORE.defaultBlockState(), 10));

	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_DIRT_ORE = register("cretaceous_dirt_ore", Feature.ORE, new OreConfiguration(STONE, Blocks.DIRT.defaultBlockState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_GRAVEL_ORE = register("cretaceous_gravel_ore", Feature.ORE, new OreConfiguration(STONE, Blocks.GRAVEL.defaultBlockState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_LATERLITE_ORE = register("cretaceous_laterlite_ore", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.LATERLITE.getDefaultState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_RAW_MARBLE_ORE = register("cretaceous_raw_marble_ore", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.RAW_MARBLE.getDefaultState(), 33));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CRETACEOUS_LIMESTONE_ORE = register("cretaceous_limestone_ore", Feature.ORE, new OreConfiguration(STONE, LostWorldsBlocks.LIMESTONE.getDefaultState(), 33));

	// Mixed Era
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> BASALT_DIAMOND_ORE = register("basalt_diamond_ore", Feature.ORE, new OreConfiguration(BASALT, LostWorldsBlocks.BASALT_DIAMOND_ORE.getDefaultState(), 10));

	public static void init() {
	}
}
