package lostworlds.library.biome.biomes.permian.ocean;

import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import tyrannotitanlib.library.base.biome.BaseBiomes;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class WarmPermianOcean extends TyrannoBiome
{
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomes.WARM_OCEAN_WATER_COLOUR).waterFogColor(BaseBiomes.LUKE_WARM_OCEAN_WATER_FOG_COLOUR).fogColor(BaseBiomes.BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.4F));
	
	static
	{
		GENERATION.surfaceBuilder(ModSurfaceBuilders.PERMIAN_WARM_OCEAN_BUILDER);
		
		ModBiomeFeatures.permianCoralReef(GENERATION);
	}
	
	public WarmPermianOcean(float depth, float scale) 
	{
		super(BaseBiomes.biome(RainType.RAIN, Category.FOREST, depth, scale, 0.0F, 0.4F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
	
	public WarmPermianOcean() 
	{
		this(-1.0F, 0.1F);
	}
}
