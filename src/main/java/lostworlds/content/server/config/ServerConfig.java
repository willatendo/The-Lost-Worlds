package lostworlds.content.server.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig 
{
	/*
	 * Creature Stats
	 * Sorted A to Z
	 */
	
	public final ForgeConfigSpec.DoubleValue allosaurusHeath;
	public final ForgeConfigSpec.DoubleValue allosaurusMovementSpeed;
	public final ForgeConfigSpec.DoubleValue allosaurusAttackDamage;
			
	/*
	 * World Settings
	 * 
	 * Sorted Boolean then Integer
	 */
	
	public final ForgeConfigSpec.BooleanValue ancientCreatureSpawnInOverworld;
	
	public final ForgeConfigSpec.BooleanValue shouldForestsSpawnInOverworld;
		
	public final ForgeConfigSpec.IntValue coniferForestWeight;
	public final ForgeConfigSpec.IntValue ginkgoForestWeight;
	public final ForgeConfigSpec.IntValue araucariaForestWeight;

	public final ForgeConfigSpec.BooleanValue shouldVolcanoSpawnInOverworld;

	public final ForgeConfigSpec.IntValue volcanoWeight;
			
	public ServerConfig(ForgeConfigSpec.Builder builder) 
	{
		/*
		 * Creature Stats
		 * Sorted A to Z
		 */
		
		this.allosaurusHeath = builder.comment("Sets the Allosaurus's heath.").translation("lostworlds.config.allosaurusHeath").defineInRange("allosaurusHeath", 55.0D, 0.0D, 1000.0D);
		this.allosaurusMovementSpeed = builder.comment("Sets the Allosaurus's movement speed.").translation("lostworlds.config.allosaurusMovementSpeed").defineInRange("allosaurusMovementSpeed", 0.2D, 0.0D, 1.0D);
		this.allosaurusAttackDamage = builder.comment("Sets the Allosaurus's attack damage.").translation("lostworlds.config.allosaurusAttackDamage").defineInRange("allosaurusAttackDamage", 8.0D, 0.0D, 30.0D);
		
		this.ancientCreatureSpawnInOverworld = builder.comment("If true, prehistoric creatures will spawn naturally in the overworld.").translation("lostworlds.config.ancientCreatureSpawnInOverworld").define("ancientCreatureSpawnInOverworld", false);
		
		this.shouldForestsSpawnInOverworld = builder.comment("If true, forests will spawn naturally in the overworld.").translation("lostworlds.config.shouldForestsSpawnInOverworld").define("shouldForestsSpawnInOverworld", true);
		
		this.coniferForestWeight = builder.comment("Sets the weight of Conifer Forests spawns in the Overworld.").translation("lostoworlds.config.coniferForestWeight").defineInRange("coniferForestWeight", 3, 1, 10);
		this.ginkgoForestWeight = builder.comment("Sets the weight of Gingko Forests spawns in the Overworld.").translation("lostoworlds.config.ginkgoForestWeight").defineInRange("ginkgoForestWeight", 3, 1, 10);
		this.araucariaForestWeight = builder.comment("Sets the weight of Araucaria Forests spawns in the Overworld.").translation("lostoworlds.config.araucariaForestWeight").defineInRange("araucariaForestWeight", 3, 1, 10);

		this.shouldVolcanoSpawnInOverworld = builder.comment("If true, volcanos will spawn naturally in the overworld.").translation("lostworlds.config.shouldVolcanoSpawnInOverworld").define("shouldVolcanoSpawnInOverworld", true);

		this.volcanoWeight = builder.comment("Sets the weight of Volcanos spawns in the Overworld.").translation("lostoworlds.config.volcanoWeight").defineInRange("volcanoWeight", 1, 1, 10);
	}
}
