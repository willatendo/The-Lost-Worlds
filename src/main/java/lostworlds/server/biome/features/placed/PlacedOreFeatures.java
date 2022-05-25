package lostworlds.server.biome.features.placed;

import static lostworlds.server.biome.features.placed.LostWorldsPlacedFeatures.register;

import java.util.List;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.biome.features.configured.OreFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class PlacedOreFeatures {
	// Overworld
	public static final Holder<PlacedFeature> SILT_PATCH = register("silt_patch", OreFeatures.SILT_PATCH, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.siltCountPerChunk.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(LostWorldsConfig.COMMON_CONFIG.siltRange.get()))));

	public static final Holder<PlacedFeature> PETRIFIED_ARAUCARIA = register("petrified_araucaria", OreFeatures.PETRIFIED_ARAUCARIA, rareOrePlacement(LostWorldsConfig.COMMON_CONFIG.petrifiedAraucariaChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> PETRIFIED_CALAMITES = register("petrified_calamties", OreFeatures.PETRIFIED_CALAMITES, rareOrePlacement(LostWorldsConfig.COMMON_CONFIG.petrifiedAraucariaChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> PETRIFIED_CONIFER = register("petrified_conifer", OreFeatures.PETRIFIED_CONIFER, rareOrePlacement(LostWorldsConfig.COMMON_CONFIG.petrifiedAraucariaChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> PETRIFIED_CYPRESS = register("petrified_cypress", OreFeatures.PETRIFIED_CYPRESS, rareOrePlacement(LostWorldsConfig.COMMON_CONFIG.petrifiedAraucariaChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> PETRIFIED_GINKGO = register("petrified_ginkgo", OreFeatures.PETRIFIED_GINKGO, rareOrePlacement(LostWorldsConfig.COMMON_CONFIG.petrifiedAraucariaChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> PETRIFIED_SEQUOIA = register("petrified_sequoia", OreFeatures.PETRIFIED_SEQUOIA, rareOrePlacement(LostWorldsConfig.COMMON_CONFIG.petrifiedAraucariaChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));

	public static final Holder<PlacedFeature> OVERWORLD_AMBER_ORE = register("overworld_amber_ore", OreFeatures.OVERWORLD_AMBER_ORE, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.amberVeinSize.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(LostWorldsConfig.COMMON_CONFIG.amberRange.get()))));

	public static final Holder<PlacedFeature> OVERWORLD_PLANT_FOSSIL_ALETHOPTERIS = register("overworld_plant_fossil_alethopteris", OreFeatures.OVERWORLD_PLANT_FOSSIL_ALETHOPTERIS, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> OVERWORLD_PLANT_FOSSIL_BRAZILEA = register("overworld_plant_fossil_brazilea", OreFeatures.OVERWORLD_PLANT_FOSSIL_BRAZILEA, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> OVERWORLD_PLANT_FOSSIL_CALAMITES_SUCKOWII = register("overworld_plant_fossil_calamites_suckowii", OreFeatures.OVERWORLD_PLANT_FOSSIL_CALAMITES_SUCKOWII, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> OVERWORLD_PLANT_FOSSIL_CEPHALOTAXUS = register("overworld_plant_fossil_cephalotaxus", OreFeatures.OVERWORLD_PLANT_FOSSIL_CEPHALOTAXUS, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> OVERWORLD_PLANT_FOSSIL_DILLHOFFIA = register("overworld_plant_fossil_dillhoffia", OreFeatures.OVERWORLD_PLANT_FOSSIL_DILLHOFFIA, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> OVERWORLD_PLANT_FOSSIL_DUISBERGIA = register("overworld_plant_fossil_duisbergia", OreFeatures.OVERWORLD_PLANT_FOSSIL_DUISBERGIA, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> OVERWORLD_PLANT_FOSSIL_OSMUNDA = register("overworld_plant_fossil_osmunda", OreFeatures.OVERWORLD_PLANT_FOSSIL_OSMUNDA, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> OVERWORLD_PLANT_FOSSIL_WILLIAMSONIA = register("overworld_plant_fossil_williamsonia", OreFeatures.OVERWORLD_PLANT_FOSSIL_WILLIAMSONIA, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> OVERWORLD_PLANT_FOSSIL_ZAMITES = register("overworld_plant_fossil_zamites", OreFeatures.OVERWORLD_PLANT_FOSSIL_ZAMITES, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));

	public static final Holder<PlacedFeature> OVERWORLD_TINY_NEST = register("overworld_tiny_nest", OreFeatures.OVERWORLD_TINY_NEST, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> OVERWORLD_SMALL_NEST = register("overworld_small_nest", OreFeatures.OVERWORLD_SMALL_NEST, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> OVERWORLD_MEDIUM_NEST = register("overworld_medium_nest", OreFeatures.OVERWORLD_MEDIUM_NEST, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));
	public static final Holder<PlacedFeature> OVERWORLD_LARGE_NEST = register("overworld_large_nest", OreFeatures.OVERWORLD_LARGE_NEST, commonOrePlacement(LostWorldsConfig.COMMON_CONFIG.plantFossilChance.get(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60))));

	// Permian
	public static final Holder<PlacedFeature> PERMIAN_MAGMA_ORE = register("permian_magma_ore", OreFeatures.PERMIAN_MAGMA_ORE, commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(27), VerticalAnchor.absolute(36))));

	public static final Holder<PlacedFeature> PERMIAN_COAL_ORE = register("permian_coal_ore", OreFeatures.PERMIAN_COAL_ORE, commonOrePlacement(20, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128))));
	public static final Holder<PlacedFeature> PERMIAN_IRON_ORE = register("permian_iron_ore", OreFeatures.PERMIAN_IRON_ORE, commonOrePlacement(20, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64))));
	public static final Holder<PlacedFeature> PERMIAN_GOLD_ORE = register("permian_gold_ore", OreFeatures.PERMIAN_GOLD_ORE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32))));
	public static final Holder<PlacedFeature> PERMIAN_REDSTONE_ORE = register("permian_redstone_ore", OreFeatures.PERMIAN_REDSTONE_ORE, commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(16))));
	public static final Holder<PlacedFeature> PERMIAN_DIAMOND_ORE = register("permian_diamond_ore", OreFeatures.PERMIAN_DIAMOND_ORE, commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(16))));
	public static final Holder<PlacedFeature> PERMIAN_LAPIS_ORE = register("permian_lapis_ore", OreFeatures.PERMIAN_LAPIS_ORE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(16))));
	public static final Holder<PlacedFeature> PERMIAN_EMERALD_ORE = register("permian_emerald_ore", OreFeatures.PERMIAN_EMERALD_ORE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128))));
	public static final Holder<PlacedFeature> PERMIAN_COPPER_ORE = register("permian_copper_ore", OreFeatures.PERMIAN_COPPER_ORE, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(50))));

	public static final Holder<PlacedFeature> PERMIAN_DIRT_ORE = register("permian_dirt_ore", OreFeatures.PERMIAN_DIRT_ORE, commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> PERMIAN_GRAVEL_ORE = register("permian_gravel_ore", OreFeatures.PERMIAN_GRAVEL_ORE, commonOrePlacement(14, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> PERMIAN_LATERLITE_ORE = register("permian_laterlite_ore", OreFeatures.PERMIAN_LATERLITE_ORE, commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> PERMIAN_RAW_MARBLE_ORE = register("permian_raw_marble_ore", OreFeatures.PERMIAN_RAW_MARBLE_ORE, commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> PERMIAN_LIMESTONE_ORE = register("permian_limestone_ore", OreFeatures.PERMIAN_LIMESTONE_ORE, commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));

	// Jurassic
	public static final Holder<PlacedFeature> JURASSIC_MAGMA_ORE = register("jurassic_magma_ore", OreFeatures.JURASSIC_MAGMA_ORE, commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(27), VerticalAnchor.absolute(36))));

	public static final Holder<PlacedFeature> JURASSIC_COAL_ORE = register("jurassic_coal_ore", OreFeatures.JURASSIC_COAL_ORE, commonOrePlacement(20, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128))));
	public static final Holder<PlacedFeature> JURASSIC_IRON_ORE = register("jurassic_iron_ore", OreFeatures.JURASSIC_IRON_ORE, commonOrePlacement(20, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64))));
	public static final Holder<PlacedFeature> JURASSIC_GOLD_ORE = register("jurassic_gold_ore", OreFeatures.JURASSIC_GOLD_ORE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32))));
	public static final Holder<PlacedFeature> JURASSIC_REDSTONE_ORE = register("jurassic_redstone_ore", OreFeatures.JURASSIC_REDSTONE_ORE, commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(16))));
	public static final Holder<PlacedFeature> JURASSIC_DIAMOND_ORE = register("jurassic_diamond_ore", OreFeatures.JURASSIC_DIAMOND_ORE, commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(16))));
	public static final Holder<PlacedFeature> JURASSIC_LAPIS_ORE = register("jurassic_lapis_ore", OreFeatures.JURASSIC_LAPIS_ORE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(16))));
	public static final Holder<PlacedFeature> JURASSIC_EMERALD_ORE = register("jurassic_emerald_ore", OreFeatures.JURASSIC_EMERALD_ORE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128))));
	public static final Holder<PlacedFeature> JURASSIC_COPPER_ORE = register("jurassic_copper_ore", OreFeatures.JURASSIC_COPPER_ORE, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(50))));

	public static final Holder<PlacedFeature> JURASSIC_DIRT_ORE = register("jurassic_dirt_ore", OreFeatures.JURASSIC_DIRT_ORE, commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> JURASSIC_GRAVEL_ORE = register("jurassic_gravel_ore", OreFeatures.JURASSIC_GRAVEL_ORE, commonOrePlacement(14, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> JURASSIC_LATERLITE_ORE = register("jurassic_laterlite_ore", OreFeatures.JURASSIC_LATERLITE_ORE, commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> JURASSIC_RAW_MARBLE_ORE = register("jurassic_raw_marble_ore", OreFeatures.JURASSIC_RAW_MARBLE_ORE, commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> JURASSIC_LIMESTONE_ORE = register("jurassic_limestone_ore", OreFeatures.JURASSIC_LIMESTONE_ORE, commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));

	// Creataceous
	public static final Holder<PlacedFeature> CRETACEOUS_MAGMA_ORE = register("cretaceous_magma_ore", OreFeatures.CRETACEOUS_MAGMA_ORE, commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(27), VerticalAnchor.absolute(36))));

	public static final Holder<PlacedFeature> CRETACEOUS_COAL_ORE = register("cretaceous_coal_ore", OreFeatures.CRETACEOUS_COAL_ORE, commonOrePlacement(20, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128))));
	public static final Holder<PlacedFeature> CRETACEOUS_IRON_ORE = register("cretaceous_iron_ore", OreFeatures.CRETACEOUS_IRON_ORE, commonOrePlacement(20, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64))));
	public static final Holder<PlacedFeature> CRETACEOUS_GOLD_ORE = register("cretaceous_gold_ore", OreFeatures.CRETACEOUS_GOLD_ORE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32))));
	public static final Holder<PlacedFeature> CRETACEOUS_REDSTONE_ORE = register("cretaceous_redstone_ore", OreFeatures.CRETACEOUS_REDSTONE_ORE, commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(16))));
	public static final Holder<PlacedFeature> CRETACEOUS_DIAMOND_ORE = register("cretaceous_diamond_ore", OreFeatures.CRETACEOUS_DIAMOND_ORE, commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(16))));
	public static final Holder<PlacedFeature> CRETACEOUS_LAPIS_ORE = register("cretaceous_lapis_ore", OreFeatures.CRETACEOUS_LAPIS_ORE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(16))));
	public static final Holder<PlacedFeature> CRETACEOUS_EMERALD_ORE = register("cretaceous_emerald_ore", OreFeatures.CRETACEOUS_EMERALD_ORE, commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128))));
	public static final Holder<PlacedFeature> CRETACEOUS_COPPER_ORE = register("cretaceous_copper_ore", OreFeatures.CRETACEOUS_COPPER_ORE, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(50))));

	public static final Holder<PlacedFeature> CRETACEOUS_DIRT_ORE = register("cretaceous_dirt_ore", OreFeatures.CRETACEOUS_DIRT_ORE, commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> CRETACEOUS_GRAVEL_ORE = register("cretaceous_gravel_ore", OreFeatures.CRETACEOUS_GRAVEL_ORE, commonOrePlacement(14, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> CRETACEOUS_LATERLITE_ORE = register("cretaceous_laterlite_ore", OreFeatures.CRETACEOUS_LATERLITE_ORE, commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> CRETACEOUS_RAW_MARBLE_ORE = register("cretaceous_raw_marble_ore", OreFeatures.CRETACEOUS_RAW_MARBLE_ORE, commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
	public static final Holder<PlacedFeature> CRETACEOUS_LIMESTONE_ORE = register("cretaceous_limestone_ore", OreFeatures.CRETACEOUS_LIMESTONE_ORE, commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));

	// Mixed Era
	public static final Holder<PlacedFeature> BASALT_DIAMOND_ORE = register("basalt_diamond_ore", OreFeatures.BASALT_DIAMOND_ORE, commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));

	private static List<PlacementModifier> orePlacement(PlacementModifier modifier1, PlacementModifier modifier2) {
		return List.of(modifier1, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
	}

	private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
		return orePlacement(CountPlacement.of(count), modifier);
	}

	private static List<PlacementModifier> rareOrePlacement(int count, PlacementModifier modifier) {
		return orePlacement(RarityFilter.onAverageOnceEvery(count), modifier);
	}
}
