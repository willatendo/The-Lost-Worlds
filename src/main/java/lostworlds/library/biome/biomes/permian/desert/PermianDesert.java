package lostworlds.library.biome.biomes.permian.desert;

import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import tyrannotitanlib.library.base.biome.BaseBiomes;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class PermianDesert extends TyrannoBiome
{
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomes.BASE_WATER_COLOUR).waterFogColor(BaseBiomes.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomes.BASE_FOG_COLOUR).skyColor(calculateSkyColor(3.0F));
	
	static
	{
		GENERATION.surfaceBuilder(ModSurfaceBuilders.PERMIAN_DESERT_BUILDER);	
		
		ModBiomeFeatures.permianDesert(GENERATION);
	}
	
	public PermianDesert(float depth, float scale) 
	{
		super(BaseBiomes.biome(RainType.NONE, Category.DESERT, depth, scale, 0.0F, 3.0F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
	
	public PermianDesert() 
	{
		this(0.125F, 0.05F);
	}
}
