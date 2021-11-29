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

	public final IntValue surfaceFossilGenerationId;
	public final IntValue subterraneanFossilGenerationId;

	public final IntValue traceFossilGenerationId;

	public final IntValue meteoriteGenerationId;
	public final BooleanValue meteoriteShouldSpawn;

	//Features
	public final BooleanValue mudDisksInSwamps;

	public final BooleanValue cypressTreesInSwamps;

	public final BooleanValue siltPatchGeneration;
	public final IntValue siltVeinSize;
	public final IntValue siltRange;
	public final IntValue siltCountPerChunk;

	public final BooleanValue amberOre;
	public final IntValue amberVeinSize;
	public final IntValue amberRange;
	public final IntValue amberCountPerChunk;
	
	public final BooleanValue plantFossilsInOverworld;
	public final IntValue plantFossilChance;
	public final BooleanValue fossilsInOverworld;

	public final IntValue fossilSize;
	public final IntValue fossilRange;
	public final IntValue fossilCountPerChunk;
	public final IntValue fossilChance;
	
	public final BooleanValue petrifiedAraucariaTreeShouldSpawn;
	public final BooleanValue petrifiedCalamitesTreeShouldSpawn;
	public final BooleanValue petrifiedConiferTreeShouldSpawn;
	public final BooleanValue petrifiedGinkgoTreeShouldSpawn;
	
	public final IntValue petrifiedAraucariaChance;
	public final IntValue petrifiedCalamitesChance;
	public final IntValue petrifiedConiferChance;
	public final IntValue petrifiedGinkgoChance;

	//Biomes
	public final BooleanValue araucariaForestShouldSpawn;
	public final BooleanValue coniferForestShouldSpawn;
	public final BooleanValue ginkgoForestShouldSpawn;
	public final BooleanValue sequoiaForestShouldSpawn;
	public final BooleanValue volcanoShouldSpawn;

	public final IntValue araucariaForestWeight;
	public final IntValue coniferForestWeight;
	public final IntValue ginkgoForestWeight;
	public final IntValue sequoiaForestWeight;
	public final IntValue volcanoWeight;

	//Dinosaurs	
	public final BooleanValue livingFossils;
	public final BooleanValue dinosaursSpawnInOverworld;

	public final BooleanValue tameableDinos;
	public final IntValue maxDinoGroup;
	public final DoubleValue maxSearchRange;

	public final DoubleValue chilesaurusHeath;
	public final IntValue chilesaurusSpawnWeight;
	public final IntValue chilesaurusSpawnGroupMinimum;
	public final IntValue chilesaurusSpawnGroupMaximum;

	public final DoubleValue dilophosaurusHeath;
	public final DoubleValue dilophosaurusAttackDamage;
	public final IntValue dilophosaurusSpawnWeight;
	public final IntValue dilophosaurusSpawnGroupMinimum;
	public final IntValue dilophosaurusSpawnGroupMaximum;

	public final DoubleValue greatAukHeath;
	public final DoubleValue greatAukAttackDamage;
	public final IntValue greatAukSpawnWeight;
	public final IntValue greatAukSpawnGroupMinimum;
	public final IntValue greatAukSpawnGroupMaximum;

	public final DoubleValue kentrosaurusHeath;
	public final IntValue kentrosaurusSpawnWeight;
	public final IntValue kentrosaurusSpawnGroupMinimum;
	public final IntValue kentrosaurusSpawnGroupMaximum;
	
	public final DoubleValue nautilusHeath;
	public final DoubleValue nautilusArmour;
	public final IntValue nautilusSpawnWeight;
	public final IntValue nautilusSpawnGroupMinimum;
	public final IntValue nautilusSpawnGroupMaximum;
	
	public final DoubleValue ophthalmosaurusHeath;
	public final DoubleValue ophthalmosaurusSpeed;
	public final DoubleValue ophthalmosaurusAttackDamage;
	public final IntValue ophthalmosaurusSpawnWeight;
	public final IntValue ophthalmosaurusSpawnGroupMinimum;
	public final IntValue ophthalmosaurusSpawnGroupMaximum;

	public ServerConfig(ForgeConfigSpec.Builder builder) 
	{
		//Ids
		this.blackMarketGenerationId = builder.comment("Sets the Black Market's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "blackMarketGenerationId").defineInRange("blackMarketGenerationId", 930134351, 111111111, 999999999);
		this.blackMarketShouldSpawn = builder.comment("Sets if the Black Market should spawn in the overworld.").translation(TRANSLATION_TEXT + "blackMarketShouldSpawn").define("blackMarketShouldSpawn", true);

		this.surfaceFossilGenerationId = builder.comment("Sets the Surface Fossil's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "fossilGenerationId").defineInRange("fossilGenerationId", 930134352, 111111111, 999999999);
		this.subterraneanFossilGenerationId = builder.comment("Sets the Subterranean Fossil's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "subterraneanFossilGenerationId").defineInRange("subterraneanFossilGenerationId", 930134357, 111111111, 999999999);
		this.traceFossilGenerationId = builder.comment("Sets the Trace Fossil's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "traceFossilGenerationId").defineInRange("traceFossilGenerationId", 930134353, 111111111, 999999999);
		
		this.meteoriteGenerationId = builder.comment("Sets the Meteorite's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "meteoriteGenerationId").defineInRange("meteoriteGenerationId", 930134354, 111111111, 999999999);
		this.meteoriteShouldSpawn = builder.comment("Sets if meteorite structures should spawn in the overworld.").translation(TRANSLATION_TEXT + "meteoritesShouldSpawn").define("meteoritesShouldSpawn", true);

		//Features
		this.mudDisksInSwamps = builder.comment("Sets if mud disks should spawn in swamp biomes.").translation(TRANSLATION_TEXT + "mudDisksInSwamps").define("mudDisksInSwamps", true);

		this.cypressTreesInSwamps = builder.comment("Sets if cypress trees should spawn in swamp biomes.").translation(TRANSLATION_TEXT + "cypressTreesInSwamps").define("cypressTreesInSwamps", true);

		this.siltPatchGeneration = builder.comment("Sets if silt patches should spawn.").translation(TRANSLATION_TEXT + "siltPatchGeneration").define("siltPatchGeneration", true);
		this.siltVeinSize = builder.comment("Sets the size a silt patch can spawn.").translation(TRANSLATION_TEXT + "siltVeinSize").defineInRange("siltVeinSize", 29, 1, 100);
		this.siltRange = builder.comment("Sets the range silt patches can spawn in.").translation(TRANSLATION_TEXT + "siltRange").defineInRange("siltRange", 64, 1, 256);
		this.siltCountPerChunk = builder.comment("Sets the amount of silt patches per chunk.").translation(TRANSLATION_TEXT + "siltCountPerChunk").defineInRange("siltCountPerChunk", 3, 1, 100);

		this.amberOre = builder.comment("Sets if amber ore should spawn in the overworld.").translation(TRANSLATION_TEXT + "amberOre").define("amberOre", true);
		this.amberVeinSize = builder.comment("Sets the size of an amber ore vein can spawn.").translation(TRANSLATION_TEXT + "amberVeinSize").defineInRange("amberVeinSize", 1, 1, 100);
		this.amberRange = builder.comment("Sets the range amber ore can spawn in.").translation(TRANSLATION_TEXT + "amberRange").defineInRange("amberRange", 16, 1, 256);
		this.amberCountPerChunk = builder.comment("Sets the amount of amber ore patches per chunk.").translation(TRANSLATION_TEXT + "amberCountPerChunk").defineInRange("amberCountPerChunk", 4, 1, 100);
		
		this.plantFossilsInOverworld = builder.comment("Sets if plant fossils should spawn in the overworld.").translation(TRANSLATION_TEXT + "plantFossilsInOverworld").define("plantFossilsInOverworld", true);
		this.plantFossilChance = builder.comment("Sets the chance of plant fossils spawning.").translation(TRANSLATION_TEXT + "plantFossilChance").defineInRange("plantFossilChance", 128, 0, 999);
		
		this.fossilsInOverworld = builder.comment("Sets if fossils should spawn in the overworld.").translation(TRANSLATION_TEXT + "fossilsInOverworld").define("fossilsInOverworld", true);
		
		this.fossilSize = builder.comment("Sets the size of a fossil vein.").translation(TRANSLATION_TEXT + "fossilCountPerChunk").defineInRange("fossilCountPerChunk", 20, 1, 999);
		this.fossilRange = builder.comment("Sets the rand fossils can spawn in.").translation(TRANSLATION_TEXT + "fossilCountPerChunk").defineInRange("fossilCountPerChunk", 128, 1, 999);
		this.fossilCountPerChunk = builder.comment("Sets the amount of fossils generating per chunk.").translation(TRANSLATION_TEXT + "fossilCountPerChunk").defineInRange("fossilCountPerChunk", 1, 1, 999);
		this.fossilChance = builder.comment("Sets the chance of a fossil generating.").translation(TRANSLATION_TEXT + "fossilChance").defineInRange("fossilChance", 1, 1, 999);
		
		this.petrifiedAraucariaTreeShouldSpawn = builder.comment("Sets if petrified araucaria trees should spawn in the overworld.").translation(TRANSLATION_TEXT + "petrifiedAraucariaTreeShouldSpawn").define("petrifiedAraucariaTreeShouldSpawn", true);
		this.petrifiedCalamitesTreeShouldSpawn = builder.comment("Sets if petrified calamites trees should spawn in the overworld.").translation(TRANSLATION_TEXT + "petrifiedCalamitesTreeShouldSpawn").define("petrifiedCalamitesTreeShouldSpawn", true);
		this.petrifiedConiferTreeShouldSpawn = builder.comment("Sets if petrified conifer trees should spawn in the overworld.").translation(TRANSLATION_TEXT + "petrifiedConiferTreeShouldSpawn").define("petrifiedConiferTreeShouldSpawn", true);
		this.petrifiedGinkgoTreeShouldSpawn = builder.comment("Sets if petrified ginkgo trees should spawn in the overworld.").translation(TRANSLATION_TEXT + "petrifiedGinkgoTreeShouldSpawn").define("petrifiedGinkgoTreeShouldSpawn", true);
		
		this.petrifiedAraucariaChance = builder.comment("Sets the petrified araucaria chance of spawning in the overworld.").translation(TRANSLATION_TEXT + "petrifiedAraucariaChance").defineInRange("petrifiedAraucariaChance", 32, 0, 999);
		this.petrifiedCalamitesChance = builder.comment("Sets the petrified calamites chance of spawning in the overworld.").translation(TRANSLATION_TEXT + "petrifiedCalamitesChance").defineInRange("petrifiedCalamitesChance", 32, 0, 999);
		this.petrifiedConiferChance = builder.comment("Sets the petrified conifer chance of spawning in the overworld.").translation(TRANSLATION_TEXT + "petrifiedConiferChance").defineInRange("petrifiedConiferChance", 32, 0, 999);
		this.petrifiedGinkgoChance = builder.comment("Sets the petrified ginkgo chance of spawning in the overworld.").translation(TRANSLATION_TEXT + "petrifiedGinkgoChance").defineInRange("petrifiedGinkgoChance", 32, 0, 999);
		
		//Biome
		this.araucariaForestShouldSpawn = builder.comment("Sets if the Araucaria Forest should spawn in the overworld. To change the weight, go to araucariaForestWeight").translation(TRANSLATION_TEXT + "araucariaForestShouldSpawn").define("araucariaForestShouldSpawn", true);
		this.coniferForestShouldSpawn = builder.comment("Sets if the Conifer Forest should spawn in the overworld. To change the weight, go to coniferForestWeight").translation(TRANSLATION_TEXT + "coniferForestShouldSpawn").define("coniferForestShouldSpawn", true);
		this.ginkgoForestShouldSpawn = builder.comment("Sets if the Ginkgo Forest should spawn in the overworld. To change the weight, go to ginkgoForestWeight").translation(TRANSLATION_TEXT + "ginkgoForestShouldSpawn").define("ginkgoForestShouldSpawn", true);
		this.sequoiaForestShouldSpawn = builder.comment("Sets if the Sequoia Forest should spawn in the overworld. To change the weight, go to sequoiaForestWeight").translation(TRANSLATION_TEXT + "sequoiaForestShouldSpawn").define("sequoiaForestShouldSpawn", true);
		this.volcanoShouldSpawn = builder.comment("Sets if the Volcano should spawn in the overworld. To change the weight, go to volcanoWeight").translation(TRANSLATION_TEXT + "volcanoShouldSpawn").define("volcanoShouldSpawn", true);

		this.araucariaForestWeight = builder.comment("Sets the weight of the Araucaria Forest spawning in the overworld. To disable this, got to araucariaForestShouldSpawn").translation(TRANSLATION_TEXT + "araucariaForestWeight").defineInRange("araucariaForestWeight", 3, 1, 999);
		this.coniferForestWeight = builder.comment("Sets the weight of the Conifer Forest spawning in the overworld. To disable this, got to coniferForestShouldSpawn").translation(TRANSLATION_TEXT + "coniferForestWeight").defineInRange("coniferForestWeight", 3, 1, 999);
		this.ginkgoForestWeight = builder.comment("Sets the weight of the Ginkgo Forest spawning in the overworld. To disable this, got to ginkgoForestShouldSpawn").translation(TRANSLATION_TEXT + "ginkgoForestWeight").defineInRange("ginkgoForestWeight", 3, 1, 999);
		this.sequoiaForestWeight = builder.comment("Sets the weight of the Sequoia Forest spawning in the overworld. To disable this, got to sequoiaForestShouldSpawn").translation(TRANSLATION_TEXT + "sequoiaForestWeight").defineInRange("sequoiaForestWeight", 2, 1, 999);
		this.volcanoWeight = builder.comment("Sets the weight of the Volcano spawning in the overworld. To disable this, got to volcanoShouldSpawn").translation(TRANSLATION_TEXT + "volcanoWeight").defineInRange("volcanoWeight", 1, 1, 999);

		//Dinosaurs		
		this.livingFossils = builder.comment("If false, living fossils like the nautilus will not spawn in the overworld.").translation(TRANSLATION_TEXT + "livingFossils").define("livingFossils", true);
		this.dinosaursSpawnInOverworld = builder.comment("If true, dinosaurs will spawn naturally in the overworld. They will spawn on any block listed in the dino_spawnables tag.").translation(TRANSLATION_TEXT + "dinosaursSpawnInOverworld").define("dinosaursSpawnInOverworld", false);

		this.tameableDinos = builder.comment("If true, dinos will be able to be tamed.").translation(TRANSLATION_TEXT + "tameableDinos").define("tameableDinos", false);
		this.maxDinoGroup = builder.comment("Sets the ammount of dinosaurs that prevent breeding.").translation(TRANSLATION_TEXT + "maxDinoGroup").defineInRange("maxDinoGroup", 10, 1, 30);
		this.maxSearchRange = builder.comment("Sets the search rang of the group to prevent breeding. POTENTIAL PREFORMACE HIT.").translation(TRANSLATION_TEXT + "maxSearchRange").defineInRange("maxSearchRange", 400.0D, 100.0D, 9000.0D);

		this.chilesaurusHeath = builder.comment("Sets the heath of the Chilesaurs").translation(TRANSLATION_TEXT + "chilesaurusHeath").defineInRange("chilesaurusHeath", 10.0D, 1.0D, 999.0D);
		this.chilesaurusSpawnWeight = builder.comment("Sets the weight of Chilesaurus in spawning").translation(TRANSLATION_TEXT + "chilesaurusSpawnWeight").defineInRange("chilesaurusSpawnWeight", 4, 1, 100);
		this.chilesaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Chilesaurus in a spawn group").translation(TRANSLATION_TEXT + "chilesaurusSpawnGroupMinimum").defineInRange("chilesaurusSpawnGroupMinimum", 3, 1, 100);
		this.chilesaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Chilesaurus in a spawn group").translation(TRANSLATION_TEXT + "chilesaurusSpawnGroupMaximum").defineInRange("chilesaurusSpawnGroupMaximum", 10, 1, 100);

		this.dilophosaurusHeath = builder.comment("Sets the heath of the Dilophosaurus").translation(TRANSLATION_TEXT + "dilophosaurusHeath").defineInRange("dilophosaurusHeath", 35.0D, 1.0D, 999.0D);
		this.dilophosaurusAttackDamage = builder.comment("Sets the attack damage of the Dilophosaurus").translation(TRANSLATION_TEXT + "dilophosaurusAttackDamage").defineInRange("dilophosaurusAttackDamage", 10.0D, 1.0D, 999.0D);
		this.dilophosaurusSpawnWeight = builder.comment("Sets the weight of Dilophosaurus in spawning").translation(TRANSLATION_TEXT + "dilophosaurusSpawnWeight").defineInRange("dilophosaurusSpawnWeight", 4, 1, 100);
		this.dilophosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Dilophosaurus in a spawn group").translation(TRANSLATION_TEXT + "dilophosaurusSpawnGroupMinimum").defineInRange("dilophosaurusSpawnGroupMinimum", 3, 1, 100);
		this.dilophosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Dilophosaurus in a spawn group").translation(TRANSLATION_TEXT + "dilophosaurusSpawnGroupMaximum").defineInRange("dilophosaurusSpawnGroupMaximum", 10, 1, 100);

		this.greatAukHeath = builder.comment("Sets the heath of the Great Auk").translation(TRANSLATION_TEXT + "greatAukHeath").defineInRange("greatAukHeath", 15.0D, 1.0D, 999.0D);
		this.greatAukAttackDamage = builder.comment("Sets the attack damage of the Great Auk").translation(TRANSLATION_TEXT + "greatAukAttackDamage").defineInRange("greatAukAttackDamage", 2.0D, 1.0D, 999.0D);
		this.greatAukSpawnWeight = builder.comment("Sets the weight of Great Auk in spawning").translation(TRANSLATION_TEXT + "greatAukSpawnWeight").defineInRange("greatAukSpawnWeight", 4, 1, 100);
		this.greatAukSpawnGroupMinimum = builder.comment("Sets the minimum amount of Great Auk in a spawn group").translation(TRANSLATION_TEXT + "greatAukSpawnGroupMinimum").defineInRange("greatAukSpawnGroupMinimum", 7, 1, 100);
		this.greatAukSpawnGroupMaximum = builder.comment("Sets the maximum amount of Great Auk in a spawn group").translation(TRANSLATION_TEXT + "greatAukSpawnGroupMaximum").defineInRange("greatAukSpawnGroupMaximum", 20, 1, 100);

		this.kentrosaurusHeath = builder.comment("Sets the heath of the Kentrosaurus").translation(TRANSLATION_TEXT + "kentrosaurusHeath").defineInRange("kentrosaurusHeath", 30.0D, 1.0D, 999.0D);
		this.kentrosaurusSpawnWeight = builder.comment("Sets the weight of Kentrosaurus in spawning").translation(TRANSLATION_TEXT + "kentrosaurusSpawnWeight").defineInRange("kentrosaurusSpawnWeight", 3, 1, 100);
		this.kentrosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Kentrosaurus in a spawn group").translation(TRANSLATION_TEXT + "kentrosaurusSpawnGroupMinimum").defineInRange("kentrosaurusSpawnGroupMinimum", 2, 1, 100);
		this.kentrosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Kentrosaurus in a spawn group").translation(TRANSLATION_TEXT + "kentrosaurusSpawnGroupMaximum").defineInRange("kentrosaurusSpawnGroupMaximum", 8, 1, 100);

		this.nautilusHeath = builder.comment("Sets the heath of the Nautilus").translation(TRANSLATION_TEXT + "nautilusHeath").defineInRange("nautilusHeath", 20.0D, 1.0D, 999.0D);
		this.nautilusArmour = builder.comment("Sets the armour of the Nautilus").translation(TRANSLATION_TEXT + "nautilusArmour").defineInRange("nautilusArmour", 30.0D, 1.0D, 999.0D);
		this.nautilusSpawnWeight = builder.comment("Sets the weight of Nautilus in spawning").translation(TRANSLATION_TEXT + "kentrosaurusSpawnWeight").defineInRange("kentrosaurusSpawnWeight", 1, 1, 100);
		this.nautilusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Nautilus in a spawn group").translation(TRANSLATION_TEXT + "kentrosaurusSpawnGroupMinimum").defineInRange("kentrosaurusSpawnGroupMinimum", 1, 1, 100);
		this.nautilusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Nautilus in a spawn group").translation(TRANSLATION_TEXT + "kentrosaurusSpawnGroupMaximum").defineInRange("kentrosaurusSpawnGroupMaximum", 1, 1, 100);

		this.ophthalmosaurusHeath = builder.comment("Sets the heath of the Ophthalmosaurus").translation(TRANSLATION_TEXT + "ophthalmosaurusHeath").defineInRange("ophthalmosaurusHeath", 25.0D, 1.0D, 999.0D);
		this.ophthalmosaurusSpeed = builder.comment("Sets the speed of the Ophthalmosaurus").translation(TRANSLATION_TEXT + "ophthalmosaurusSpeed").defineInRange("ophthalmosaurusSpeed", 1.6D, 1.0D, 999.0D);
		this.ophthalmosaurusAttackDamage = builder.comment("Sets the attack damage of the Ophthalmosaurus").translation(TRANSLATION_TEXT + "ophthalmosaurusAttackDamage").defineInRange("ophthalmosaurusAttackDamage", 5.0D, 1.0D, 999.0D);
		this.ophthalmosaurusSpawnWeight = builder.comment("Sets the weight of Ophthalmosaurus in spawning").translation(TRANSLATION_TEXT + "ophthalmosaurusSpawnWeight").defineInRange("ophthalmosaurusSpawnWeight", 1, 1, 100);
		this.ophthalmosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Ophthalmosaurus in a spawn group").translation(TRANSLATION_TEXT + "ophthalmosaurusSpawnGroupMinimum").defineInRange("ophthalmosaurusSpawnGroupMinimum", 2, 1, 100);
		this.ophthalmosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Ophthalmosaurus in a spawn group").translation(TRANSLATION_TEXT + "ophthalmosaurusSpawnGroupMaximum").defineInRange("ophthalmosaurusSpawnGroupMaximum", 8, 1, 100);
	}
}
