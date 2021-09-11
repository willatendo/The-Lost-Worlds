package lostworlds.library.biome.biomes.overworld.forest;

import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModBiomeMaker;
import lostworlds.library.biome.biomes.SimpleBiome;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class ConiferForest extends SimpleBiome
{
	public static final MobSpawnInfo.Builder MOB_SPAWNS = defaultOverworldSpawns();
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	
	static
	{
		addSpawns();
		addGeneration();
	}
	
	static void addSpawns() 
	{
		MOB_SPAWNS.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.WOLF, 5, 4, 4)).setPlayerCanSpawn();
	}
	
	static void addGeneration() 
	{	
		GENERATION.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
		
		DefaultBiomeFeatures.addDefaultOverworldLandStructures(GENERATION);
		GENERATION.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
		DefaultBiomeFeatures.addDefaultCarvers(GENERATION);
		DefaultBiomeFeatures.addDefaultLakes(GENERATION);
		DefaultBiomeFeatures.addDefaultMonsterRoom(GENERATION);
		DefaultBiomeFeatures.addForestFlowers(GENERATION);
		DefaultBiomeFeatures.addDefaultUndergroundVariety(GENERATION);
		DefaultBiomeFeatures.addDefaultOres(GENERATION);
		DefaultBiomeFeatures.addDefaultSoftDisks(GENERATION);
		DefaultBiomeFeatures.addDefaultFlowers(GENERATION);
		DefaultBiomeFeatures.addForestGrass(GENERATION);
		DefaultBiomeFeatures.addDefaultMushrooms(GENERATION);
		DefaultBiomeFeatures.addDefaultExtraVegetation(GENERATION);
		DefaultBiomeFeatures.addDefaultSprings(GENERATION);
		DefaultBiomeFeatures.addSurfaceFreezing(GENERATION);
		
		ModBiomeFeatures.addConiferTrees(GENERATION);
	}
	
	public static Biome create()
	{
		return ModBiomeMaker.create(RainType.RAIN, Category.FOREST, 0.1F, 0.2F, 0.7F, 0.8F, 4159204, 329011, 12638463, calculateSkyColor(0.7F), MOB_SPAWNS.build(), GENERATION.build());
	}
	
	public static Biome create(float depth, float scale)
	{
		return ModBiomeMaker.create(RainType.RAIN, Category.FOREST, depth, scale, 0.7F, 0.8F, 4159204, 329011, 12638463, calculateSkyColor(0.7F), MOB_SPAWNS.build(), GENERATION.build());
	}
}