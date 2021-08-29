package lostworlds.library.biome.biomes;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public class SimpleBiome 
{	
	public static final MobSpawnInfo.Builder MOB_SPAWNS = new MobSpawnInfo.Builder();
	public static final BiomeGenerationSettings.Builder GENERATION = new BiomeGenerationSettings.Builder();
	
	public static int calculateSkyColor(float temperature) 
	{
		float colour = temperature / 3.0F;
		colour = MathHelper.clamp(colour, -1.0F, 1.0F);
		return MathHelper.hsvToRgb(0.62222224F - colour * 0.05F, 0.5F + colour * 0.1F, 1.0F);
	}
}
