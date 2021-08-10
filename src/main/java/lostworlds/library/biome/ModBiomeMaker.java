package lostworlds.library.biome;

import lostworlds.content.server.init.SurfaceBuilderInit;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

/*
 * Author: Willatendo
 * Date: July 10, 2021
 */

public class ModBiomeMaker 
{	
	private static <C extends ISurfaceBuilderConfig> BiomeGenerationSettings.Builder genSettings(SurfaceBuilder<C> surfaceBuilder, C config) 
	{
		return new BiomeGenerationSettings.Builder().surfaceBuilder(surfaceBuilder.configured(config));
	}
	
	//Permian (A - Z)
	public static Biome permianDesert()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ModSurfaceBuilders.PERMIAN_DESERT_SURFACE_BUILDER);
			
		ModBiomeFeatures.permianDesert(generationBuilder);
	
		return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.DESERT).depth(0.125F).scale(0.05F).temperature(3.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0xb78f59).waterFogColor(0x99774a).fogColor(0xbc745e).skyColor(0xdd5f39).grassColorOverride(0xb78f59).foliageColorOverride(0xb78f59).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();
	}
	
	public static Biome permianDesertHills()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ModSurfaceBuilders.PERMIAN_DESERT_SURFACE_BUILDER);
		
		ModBiomeFeatures.permianDesert(generationBuilder);
		
		return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.DESERT).depth(0.45F).scale(0.3F).temperature(3.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0xb78f59).waterFogColor(0x99774a).fogColor(0xbc745e).skyColor(0xdd5f39).grassColorOverride(0xb78f59).foliageColorOverride(0xb78f59).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();
	}
	
	public static Biome permianDesertLake()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ModSurfaceBuilders.PERMIAN_DESERT_SURFACE_BUILDER);
			
		ModBiomeFeatures.permianDesert(generationBuilder);
	
		return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.DESERT).depth(0.225F).scale(0.25F).temperature(3.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0xb78f59).waterFogColor(0x99774a).fogColor(0xbc745e).skyColor(0xdd5f39).grassColorOverride(0xb78f59).foliageColorOverride(0xb78f59).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();
	}
	
	public static Biome permianDriedPlains()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = genSettings(SurfaceBuilderInit.NAKED_PERMIAN_DRIED_PLAINS, ModSurfaceBuilders.DRIED_SOIL_MUD_CONFIG);
			
		ModBiomeFeatures.permianDriedPlains(generationBuilder);
		
		return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x94ccc3).waterFogColor(0x72aaa2).fogColor(0xbc745e).skyColor(0xdd5f39).grassColorOverride(0xb78f59).foliageColorOverride(0xb78f59).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();
	}
	
	public static Biome permianDriedPlainsHills()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = genSettings(SurfaceBuilderInit.NAKED_PERMIAN_DRIED_PLAINS, ModSurfaceBuilders.DRIED_SOIL_CONFIG);
			
		ModBiomeFeatures.permianDriedPlains(generationBuilder);
		
		return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.PLAINS).depth(0.45F).scale(0.3F).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x94ccc3).waterFogColor(0x72aaa2).fogColor(0xbc745e).skyColor(0xdd5f39).grassColorOverride(0xb78f59).foliageColorOverride(0xb78f59).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();
	}
	
	public static Biome permianAshyMedows()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = genSettings(SurfaceBuilderInit.NAKED_PERMIAN_ASHY_MEDOWS, ModSurfaceBuilders.VOLCANIC_ASH_CONFIG);
		
		ModBiomeFeatures.permianAshyMedows(generationBuilder);
		
		return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(2.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x999999).waterFogColor(0x757575).fogColor(0x494949).skyColor(0x3a3a3a).grassColorOverride(0x686868).foliageColorOverride(0x686868).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).ambientParticle(new ParticleEffectAmbience(ParticleTypes.WHITE_ASH, 0.00625F)).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();
	}
	
	public static Biome permianFloodBasalts()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.BASALT_DELTAS);
		
		ModBiomeFeatures.permianFloodBasalts(generationBuilder);
		
		return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(5.0F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x999999).waterFogColor(0x757575).fogColor(0x494949).skyColor(0x3a3a3a).grassColorOverride(0x686868).foliageColorOverride(0x686868).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).ambientParticle(new ParticleEffectAmbience(ParticleTypes.WHITE_ASH, 0.0625F)).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();
	}
	
	public static Biome permianMountains()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = genSettings(SurfaceBuilderInit.NAKED_PERMIAN_MOUNTAINS, ModSurfaceBuilders.PERMIAN_STONE_CONFIG);
		
		ModBiomeFeatures.permianMountains(generationBuilder);
				
		return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.PLAINS).depth(1.0F).scale(0.5F).temperature(0.2F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x3181c6).waterFogColor(0x1c65a5).fogColor(0x77d3ea).skyColor(0x39aac6).grassColorOverride(0x2b9b33).foliageColorOverride(0x2b9b33).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();		
	}
	
	public static Biome permianConiferForest()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ModSurfaceBuilders.FOREST_BUILDER);
		
		ModBiomeFeatures.permianConiferForest(generationBuilder);
				
		return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(0.125F).scale(0.05F).temperature(0.2F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x3181c6).waterFogColor(0x1c65a5).fogColor(0x77d3ea).skyColor(0x39aac6).grassColorOverride(0x2b9b33).foliageColorOverride(0x2b9b33).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();		
	}
	
	public static Biome permianConiferForestHills()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ModSurfaceBuilders.FOREST_BUILDER);
		
		ModBiomeFeatures.permianConiferForest(generationBuilder);
				
		return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(0.45F).scale(0.3F).temperature(0.2F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x3181c6).waterFogColor(0x1c65a5).fogColor(0x77d3ea).skyColor(0x39aac6).grassColorOverride(0x2b9b33).foliageColorOverride(0x2b9b33).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();		
	}
	
	public static Biome permianGinkgoForest()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ModSurfaceBuilders.FOREST_BUILDER);
		
		ModBiomeFeatures.permianGinkgoForest(generationBuilder);
				
		return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(0.125F).scale(0.05F).temperature(0.2F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x3181c6).waterFogColor(0x1c65a5).fogColor(0x77d3ea).skyColor(0x39aac6).grassColorOverride(0x2b9b33).foliageColorOverride(0x2b9b33).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();		
	}
	
	public static Biome permianGinkgoForestHills()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ModSurfaceBuilders.FOREST_BUILDER);
		
		ModBiomeFeatures.permianGinkgoForest(generationBuilder);
				
		return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(0.45F).scale(0.3F).temperature(0.2F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x3181c6).waterFogColor(0x1c65a5).fogColor(0x77d3ea).skyColor(0x39aac6).grassColorOverride(0x2b9b33).foliageColorOverride(0x2b9b33).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();		
	}
	
	public static Biome permianPlains()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = genSettings(SurfaceBuilderInit.NAKED_PERMIAN_PLAINS, ModSurfaceBuilders.MOSSY_DIRT_CONFIG);
		
		ModBiomeFeatures.permianPlains(generationBuilder);
				
		return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.PLAINS).depth(0.125F).scale(0.05F).temperature(0.2F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x3181c6).waterFogColor(0x1c65a5).fogColor(0x77d3ea).skyColor(0x39aac6).grassColorOverride(0x2b9b33).foliageColorOverride(0x2b9b33).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();		
	}
	
	public static Biome permianPlainsHills()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = genSettings(SurfaceBuilderInit.NAKED_PERMIAN_PLAINS, ModSurfaceBuilders.MOSSY_DIRT_CONFIG);
		
		ModBiomeFeatures.permianPlains(generationBuilder);
				
		return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.PLAINS).depth(0.45F).scale(0.3F).temperature(0.2F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x3181c6).waterFogColor(0x1c65a5).fogColor(0x77d3ea).skyColor(0x39aac6).grassColorOverride(0x2b9b33).foliageColorOverride(0x2b9b33).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();		
	}
	
	public static Biome permianOcean()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ModSurfaceBuilders.PERMIAN_OCEAN_BUILDER);
		
		ModBiomeFeatures.permianOcean(generationBuilder);
				
		return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.OCEAN).depth(-1.0F).scale(0.1F).temperature(0.2F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x3181c6).waterFogColor(0x1c65a5).fogColor(0x77d3ea).skyColor(0x39aac6).grassColorOverride(0x2b9b33).foliageColorOverride(0x2b9b33).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();		
	}
	
	public static Biome permianDeepOcean()
	{
		MobSpawnInfo.Builder spawnBuilder = new MobSpawnInfo.Builder();
		
		BiomeGenerationSettings.Builder generationBuilder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ModSurfaceBuilders.PERMIAN_OCEAN_BUILDER);
		
		ModBiomeFeatures.permianOcean(generationBuilder);
				
		return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.OCEAN).depth(-1.8F).scale(0.1F).temperature(0.2F).downfall(0.0F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x3181c6).waterFogColor(0x1c65a5).fogColor(0x77d3ea).skyColor(0x39aac6).grassColorOverride(0x2b9b33).foliageColorOverride(0x2b9b33).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(generationBuilder.build()).build();		
	}
	
	/*public static Biome permianShore()
	{
		
	}*/
}
