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

		//Biome
		public final BooleanValue coniferForestShouldSpawn;
		public final BooleanValue ginkgoForestShouldSpawn;
		
		public final IntValue coniferForestWeight;
		public final IntValue ginkgoForestWeight;
		
		public Common(ForgeConfigSpec.Builder builder)
		{
			//Ids
			this.blackMarketGenerationId = builder.comment("Sets the Black Market's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "blackMarketGenerationId").defineInRange("blackMarketGenerationId", 930134351, 111111111, 999999999);
			
			//Biome
			this.coniferForestShouldSpawn = builder.comment("Sets if the Conifer Forest should spawn in the overworld. To change the weight, go to coniferForestWeight").translation(TRANSLATION_TEXT + "coniferForestShouldSpawn").define("coniferForestShouldSpawn", true);
			this.ginkgoForestShouldSpawn = builder.comment("Sets if the Ginkgo Forest should spawn in the overworld. To change the weight, go to ginkgoForestWeight").translation(TRANSLATION_TEXT + "ginkgoForestShouldSpawn").define("ginkgoForestShouldSpawn", true);
			
			this.coniferForestWeight = builder.comment("Sets the weight of the Conifer Forest spawning in the overworld. To disable this, got to coniferForestShouldSpawn").translation(TRANSLATION_TEXT + "coniferForestWeight").defineInRange("coniferForestWeight", 3, 1, 999);
			this.ginkgoForestWeight = builder.comment("Sets the weight of the Ginkgo Forest spawning in the overworld. To disable this, got to ginkgoForestWeight").translation(TRANSLATION_TEXT + "ginkgoForestWeight").defineInRange("ginkgoForestWeight", 3, 1, 999);
		}
	}
}
