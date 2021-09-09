package lostworlds.content.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class LostWorldsConfig 
{
	protected static final String TRANSLATION_TEXT = "lostworlds.config.";
	
	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON_CONFIG;
	
	static 
	{
		final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = commonSpecPair.getRight();
		COMMON_CONFIG = commonSpecPair.getLeft();
	}
	
	public static class Common 
	{
		//Ids
		public final IntValue blackMarketGenerationId;

		//Features		
		public final BooleanValue mudDisksInSwamps;
		
		public final BooleanValue copperOreGeneration;
		public final IntValue copperVeinSize;
		public final IntValue copperRange;
		public final IntValue copperCountPerChunk;
		
		//Biome
		public final BooleanValue coniferForestShouldSpawn;
		public final BooleanValue ginkgoForestShouldSpawn;
		
		public final IntValue coniferForestWeight;
		public final IntValue ginkgoForestWeight;
		
		public Common(ForgeConfigSpec.Builder builder)
		{
			//Ids
			this.blackMarketGenerationId = builder.comment("Sets the Black Market's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "blackMarketGenerationId").defineInRange("blackMarketGenerationId", 930134351, 111111111, 999999999);
			
			//Features
			//this.meteoritesShouldSpawn = builder.comment("Sets if meteorite structures should spawn in the overworld.").translation(TRANSLATION_TEXT + "meteoritesShouldSpawn").define("meteoritesShouldSpawn", true);
			
			this.mudDisksInSwamps = builder.comment("Sets if mud disks should spawn in swamp biomes.").translation(TRANSLATION_TEXT + "mudDisksInSwamps").define("mudDisksInSwamps", true);
			
			this.copperOreGeneration = builder.comment("Sets if copper ore should spawn.").translation(TRANSLATION_TEXT + "copperOreGeneration").define("copperOreGeneration", true);
			this.copperVeinSize = builder.comment("Sets the size a copper vein can spawn.").translation(TRANSLATION_TEXT + "copperVeinSize").defineInRange("copperVeinSize", 9, 1, 100);
			this.copperRange = builder.comment("Sets the rang copper can spawn in.").translation(TRANSLATION_TEXT + "copperRange").defineInRange("copperRange", 64, 1, 256);
			this.copperCountPerChunk = builder.comment("Sets the ammount of copper veins per chunk").translation(TRANSLATION_TEXT + "copperCountPerChunk").defineInRange("copperCountPerChunk", 20, 1, 100);
			
			//Biome
			this.coniferForestShouldSpawn = builder.comment("Sets if the Conifer Forest should spawn in the overworld. To change the weight, go to coniferForestWeight").translation(TRANSLATION_TEXT + "coniferForestShouldSpawn").define("coniferForestShouldSpawn", true);
			this.ginkgoForestShouldSpawn = builder.comment("Sets if the Ginkgo Forest should spawn in the overworld. To change the weight, go to ginkgoForestWeight").translation(TRANSLATION_TEXT + "ginkgoForestShouldSpawn").define("ginkgoForestShouldSpawn", true);
			
			this.coniferForestWeight = builder.comment("Sets the weight of the Conifer Forest spawning in the overworld. To disable this, got to coniferForestShouldSpawn").translation(TRANSLATION_TEXT + "coniferForestWeight").defineInRange("coniferForestWeight", 3, 1, 999);
			this.ginkgoForestWeight = builder.comment("Sets the weight of the Ginkgo Forest spawning in the overworld. To disable this, got to ginkgoForestWeight").translation(TRANSLATION_TEXT + "ginkgoForestWeight").defineInRange("ginkgoForestWeight", 3, 1, 999);
		}
	}
}
