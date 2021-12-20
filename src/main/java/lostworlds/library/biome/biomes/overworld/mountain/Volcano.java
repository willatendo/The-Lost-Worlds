package lostworlds.library.biome.biomes.overworld.mountain;

import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModSurfaceBuilders;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import tyrannotitanlib.library.base.biome.BaseBiomeInfo;
import tyrannotitanlib.library.base.biome.TyrannoBiome;

public class Volcano extends TyrannoBiome
{
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeAmbience.Builder AMBIENCE = new BiomeAmbience.Builder().waterColor(BaseBiomeInfo.BASE_WATER_COLOUR).waterFogColor(BaseBiomeInfo.BASE_WATER_FOG_COLOUR).fogColor(BaseBiomeInfo.BASE_FOG_COLOUR).grassColorOverride(0x686868).foliageColorOverride(0x686868).skyColor(0x3a3a3a);
	
	static
	{
		GENERATION.surfaceBuilder(ModSurfaceBuilders.PERMIAN_ASHY_MEDOWS_BUILDER);
		
		ModBiomeFeatures.volcano(GENERATION);
	}
	
	public Volcano() 
	{
		super(BaseBiomeInfo.biome(RainType.NONE, Category.PLAINS, 1.0F, 0.5F, 0.0F, 2.5F, AMBIENCE.build(), GENERATION.build(), MOB_SPAWNS.build()));
	}
}
