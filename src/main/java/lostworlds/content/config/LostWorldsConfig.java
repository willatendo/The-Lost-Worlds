package lostworlds.content.config;


import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
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
	
	static class Common 
	{
		public final IntValue blackMarketGenerationId;

		public Common(ForgeConfigSpec.Builder builder)
		{
			this.blackMarketGenerationId = builder.comment("Sets the Black Market's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "blackMarketGenerationId").defineInRange("blackMarketGenerationId", 930134351, 111111111, 999999999);
		}
	}
}
