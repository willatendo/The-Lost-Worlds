package lostworlds.library.biome.biomes.jurassic.ocean;

import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import tyrannotitanlib.library.base.biome.BaseBiomes;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class JurassicOcean extends TyrannoBiome
{
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomes.OCEAN_WATER_COLOUR).waterFogColor(BaseBiomes.OCEAN_WATER_FOG_COLOUR).fogColor(BaseBiomes.BASE_FOG_COLOUR).skyColor(calculateSkyColor(0.5F));
	
	static
	{
		GENERATION.surfaceBuilder(ModSurfaceBuilders.JURASSIC_ROCKY_SOIL_BUILDER);
		
		ModBiomeFeatures.jurassicOcean(GENERATION);
	}
	
	public JurassicOcean(float depth, float scale) 
	{
		super(BaseBiomes.biome(RainType.RAIN, Category.OCEAN, depth, scale, 0.8F, 0.5F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
	
	public JurassicOcean() 
	{
		this(-1.0F, 0.1F);
	}
}
