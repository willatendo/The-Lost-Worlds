package lostworlds.library.biome.biomes.permian.plains;

import lostworlds.library.biome.ModBiomeMaker;
import lostworlds.content.server.init.SurfaceBuilderInit;
import lostworlds.library.biome.ModBiomeFeatures;
import lostworlds.library.biome.ModSurfaceBuilders;
import lostworlds.library.biome.biomes.SimpleBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class PermianDriedPlains extends SimpleBiome 
{
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeGenerationSettings.Builder GENERATION = genSettings(SurfaceBuilderInit.NAKED_PERMIAN_DRIED_PLAINS, ModSurfaceBuilders.DRIED_SOIL_MUD_CONFIG);
	
	static 
	{
		addSpawns();
		addGeneration();
	}
	
	static void addSpawns() { }
	
	static void addGeneration()
	{		
		ModBiomeFeatures.permianDriedPlains(GENERATION);
	}
	
	public static Biome create()
	{		
		return ModBiomeMaker.create(RainType.NONE, Category.PLAINS, 0.125F, 0.05F, 2.0F, 0.0F, 0xb78f59, 0x99774a, 0xbc745e, 0xdd5f39, 0xb78f59, 0xb78f59, MOB_SPAWNS.build(), GENERATION.build());
	}
	
	public static Biome create(float depth, float scale)
	{		
		return ModBiomeMaker.create(RainType.NONE, Category.PLAINS, depth, scale, 2.0F, 0.0F, 0xb78f59, 0x99774a, 0xbc745e, 0xdd5f39, 0xb78f59, 0xb78f59, MOB_SPAWNS.build(), GENERATION.build());
	}
}
