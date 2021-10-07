package lostworlds.content.server;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ServerConfig 
{
	protected static final String TRANSLATION_TEXT = "lostworlds.config.";

	//Structures
	public final IntValue blackMarketGenerationId;
	public final BooleanValue blackMarketShouldSpawn;

	public final IntValue meteoriteGenerationId;
	public final BooleanValue meteoriteShouldSpawn;

	//Features
	public final BooleanValue mudDisksInSwamps;

	public final BooleanValue copperOreGeneration;
	public final IntValue copperVeinSize;
	public final IntValue copperRange;
	public final IntValue copperCountPerChunk;

	public final BooleanValue siltPatchGeneration;
	public final IntValue siltVeinSize;
	public final IntValue siltRange;
	public final IntValue siltCountPerChunk;

	public final BooleanValue plantFossilsInOverworld;
	public final BooleanValue fossilsInOverworld;

	public final BooleanValue petrifiedAraucariaTreeShouldSpawn;
	public final BooleanValue petrifiedCalamitesTreeShouldSpawn;
	public final BooleanValue petrifiedConiferTreeShouldSpawn;
	public final BooleanValue petrifiedGinkgoTreeShouldSpawn;

	//Biomes
	public final BooleanValue coniferForestShouldSpawn;
	public final BooleanValue ginkgoForestShouldSpawn;

	public final IntValue coniferForestWeight;
	public final IntValue ginkgoForestWeight;

	//Dinosaurs
	public final BooleanValue dinosaursSpawnInOverworld;

	public final BooleanValue tameableDinos;
	public final IntValue maxDinoGroup;
	public final DoubleValue maxSearchRange;

	public final DoubleValue chilesaurusHeath;
	public final IntValue chilesaurusSpawnWeight;
	public final IntValue chilesaurusSpawnGroupMinimum;
	public final IntValue chilesaurusSpawnGroupMaximum;

	public final DoubleValue kentrosaurusHeath;
	public final IntValue kentrosaurusSpawnWeight;
	public final IntValue kentrosaurusSpawnGroupMinimum;
	public final IntValue kentrosaurusSpawnGroupMaximum;

	public ServerConfig(ForgeConfigSpec.Builder builder) 
	{
		//Ids
		this.blackMarketGenerationId = builder.comment("Sets the Black Market's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "blackMarketGenerationId").defineInRange("blackMarketGenerationId", 930134351, 111111111, 999999999);
		this.blackMarketShouldSpawn = builder.comment("Sets if the Black Market should spawn in the overworld.").translation(TRANSLATION_TEXT + "blackMarketShouldSpawn").define("blackMarketShouldSpawn", true);

		this.meteoriteGenerationId = builder.comment("Sets the Meteorite's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "meteoriteGenerationId").defineInRange("meteoriteGenerationId", 930134352, 111111111, 999999999);
		this.meteoriteShouldSpawn = builder.comment("Sets if meteorite structures should spawn in the overworld.").translation(TRANSLATION_TEXT + "meteoritesShouldSpawn").define("meteoritesShouldSpawn", true);

		//Features
		this.mudDisksInSwamps = builder.comment("Sets if mud disks should spawn in swamp biomes.").translation(TRANSLATION_TEXT + "mudDisksInSwamps").define("mudDisksInSwamps", true);

		this.copperOreGeneration = builder.comment("Sets if copper ore should spawn.").translation(TRANSLATION_TEXT + "copperOreGeneration").define("copperOreGeneration", true);
		this.copperVeinSize = builder.comment("Sets the size a copper vein can spawn.").translation(TRANSLATION_TEXT + "copperVeinSize").defineInRange("copperVeinSize", 9, 1, 100);
		this.copperRange = builder.comment("Sets the range copper can spawn in.").translation(TRANSLATION_TEXT + "copperRange").defineInRange("copperRange", 64, 1, 256);
		this.copperCountPerChunk = builder.comment("Sets the ammount of copper veins per chunk.").translation(TRANSLATION_TEXT + "copperCountPerChunk").defineInRange("copperCountPerChunk", 20, 1, 100);

		this.siltPatchGeneration = builder.comment("Sets if silt patches should spawn.").translation(TRANSLATION_TEXT + "siltPatchGeneration").define("siltPatchGeneration", true);
		this.siltVeinSize = builder.comment("Sets the size a silt patches can spawn.").translation(TRANSLATION_TEXT + "siltVeinSize").defineInRange("siltVeinSize", 29, 1, 100);
		this.siltRange = builder.comment("Sets the range silt patches can spawn in.").translation(TRANSLATION_TEXT + "siltRange").defineInRange("siltRange", 64, 1, 256);
		this.siltCountPerChunk = builder.comment("Sets the ammount of silt patches veins per chunk.").translation(TRANSLATION_TEXT + "siltCountPerChunk").defineInRange("siltCountPerChunk", 3, 1, 100);

		this.plantFossilsInOverworld = builder.comment("Sets if plant fossils should spawn in the overworld.").translation(TRANSLATION_TEXT + "plantFossilsInOverworld").define("plantFossilsInOverworld", true);
		this.fossilsInOverworld = builder.comment("Sets if fossils should spawn in the overworld.").translation(TRANSLATION_TEXT + "fossilsInOverworld").define("fossilsInOverworld", true);
		
		this.petrifiedAraucariaTreeShouldSpawn = builder.comment("Sets if petrified araucaria trees should spawn in the overworld.").translation(TRANSLATION_TEXT + "petrifiedAraucariaTreeShouldSpawn").define("petrifiedAraucariaTreeShouldSpawn", true);
		this.petrifiedCalamitesTreeShouldSpawn = builder.comment("Sets if petrified calamites trees should spawn in the overworld.").translation(TRANSLATION_TEXT + "petrifiedCalamitesTreeShouldSpawn").define("petrifiedCalamitesTreeShouldSpawn", true);
		this.petrifiedConiferTreeShouldSpawn = builder.comment("Sets if petrified conifer trees should spawn in the overworld.").translation(TRANSLATION_TEXT + "petrifiedConiferTreeShouldSpawn").define("petrifiedConiferTreeShouldSpawn", true);
		this.petrifiedGinkgoTreeShouldSpawn = builder.comment("Sets if petrified ginkgo trees should spawn in the overworld.").translation(TRANSLATION_TEXT + "petrifiedGinkgoTreeShouldSpawn").define("petrifiedGinkgoTreeShouldSpawn", true);

		//Biome
		this.coniferForestShouldSpawn = builder.comment("Sets if the Conifer Forest should spawn in the overworld. To change the weight, go to coniferForestWeight").translation(TRANSLATION_TEXT + "coniferForestShouldSpawn").define("coniferForestShouldSpawn", true);
		this.ginkgoForestShouldSpawn = builder.comment("Sets if the Ginkgo Forest should spawn in the overworld. To change the weight, go to ginkgoForestWeight").translation(TRANSLATION_TEXT + "ginkgoForestShouldSpawn").define("ginkgoForestShouldSpawn", true);

		this.coniferForestWeight = builder.comment("Sets the weight of the Conifer Forest spawning in the overworld. To disable this, got to coniferForestShouldSpawn").translation(TRANSLATION_TEXT + "coniferForestWeight").defineInRange("coniferForestWeight", 3, 1, 999);
		this.ginkgoForestWeight = builder.comment("Sets the weight of the Ginkgo Forest spawning in the overworld. To disable this, got to ginkgoForestWeight").translation(TRANSLATION_TEXT + "ginkgoForestWeight").defineInRange("ginkgoForestWeight", 3, 1, 999);

		//Dinosaurs
		this.dinosaursSpawnInOverworld = builder.comment("If true, dinosaurs will spawn naturally in the overworld. They will spawn on any block listed in the dino_spawnables tag.").translation(TRANSLATION_TEXT + "dinosaursSpawnInOverworld").define("dinosaursSpawnInOverworld", false);

		this.tameableDinos = builder.comment("If true, dinos will be able to be tamed.").translation(TRANSLATION_TEXT + "tameableDinos").define("tameableDinos", false);
		this.maxDinoGroup = builder.comment("Sets the ammount of dinosaurs that prevent breeding.").translation(TRANSLATION_TEXT + "maxDinoGroup").defineInRange("maxDinoGroup", 10, 1, 30);
		this.maxSearchRange = builder.comment("Sets the search rang of the group to prevent breeding. POTENTIAL PREFORMACE HIT.").translation(TRANSLATION_TEXT + "maxSearchRange").defineInRange("maxSearchRange", 400.0D, 100.0D, 9000.0D);

		this.chilesaurusHeath = builder.comment("Sets the heath of the Chilesaurs").translation(TRANSLATION_TEXT + "chilesaurusHeath").defineInRange("chilesaurusHeath", 10.0D, 1.0D, 999.0D);
		this.chilesaurusSpawnWeight = builder.comment("Sets the weight of Chilesaurus in spawning").translation(TRANSLATION_TEXT + "chilesaurusSpawnWeight").defineInRange("chilesaurusSpawnWeight", 4, 1, 100);
		this.chilesaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Chilesaurus in a spawn group").translation(TRANSLATION_TEXT + "chilesaurusSpawnGroupMinimum").defineInRange("chilesaurusSpawnGroupMinimum", 3, 1, 100);
		this.chilesaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Chilesaurus in a spawn group").translation(TRANSLATION_TEXT + "chilesaurusSpawnGroupMaximum").defineInRange("chilesaurusSpawnGroupMaximum", 10, 1, 100);

		this.kentrosaurusHeath = builder.comment("Sets the heath of the Kentrosaurus").translation(TRANSLATION_TEXT + "kentrosaurusHeath").defineInRange("kentrosaurusHeath", 30.0D, 1.0D, 999.0D);
		this.kentrosaurusSpawnWeight = builder.comment("Sets the weight of Kentrosaurus in spawning").translation(TRANSLATION_TEXT + "kentrosaurusSpawnWeight").defineInRange("kentrosaurusSpawnWeight", 3, 1, 100);
		this.kentrosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Kentrosaurus in a spawn group").translation(TRANSLATION_TEXT + "kentrosaurusSpawnGroupMinimum").defineInRange("kentrosaurusSpawnGroupMinimum", 2, 1, 100);
		this.kentrosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Kentrosaurus in a spawn group").translation(TRANSLATION_TEXT + "kentrosaurusSpawnGroupMaximum").defineInRange("kentrosaurusSpawnGroupMaximum", 8, 1, 100);
	}
}
