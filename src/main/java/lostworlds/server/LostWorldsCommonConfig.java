package lostworlds.server;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class LostWorldsCommonConfig {
	protected static final String TRANSLATION_TEXT = "lostworlds.config.";

	// Structures
	public final IntValue blackMarketGenerationId;
	public final BooleanValue blackMarketShouldSpawn;

	public final IntValue surfaceFossilGenerationId;
	public final IntValue subterraneanFossilGenerationId;

	public final IntValue traceFossilGenerationId;

	public final IntValue meteoriteGenerationId;
	public final BooleanValue meteoriteShouldSpawn;

	public final BooleanValue villageStructures;
	public final IntValue villageStructureWeights;

	// Features
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
	public final BooleanValue petrifiedCypressTreeShouldSpawn;
	public final BooleanValue petrifiedGinkgoTreeShouldSpawn;
	public final BooleanValue petrifiedSequoiaTreeShouldSpawn;

	public final IntValue petrifiedAraucariaChance;
	public final IntValue petrifiedCalamitesChance;
	public final IntValue petrifiedConiferChance;
	public final IntValue petrifiedGinkgoChance;

	// Biomes
	public final BooleanValue spawnOverworldBiomes;

	public final IntValue overworldBiomeWeight;

	// Dinosaurs
	public final BooleanValue livingFossils;
	public final BooleanValue dinosaursSpawnInOverworld;

	// public final BooleanValue tameableDinos;
	public final IntValue maxDinoGroup;
	public final DoubleValue maxSearchRange;

	public final DoubleValue allosaurusHeath;
	public final DoubleValue allosaurusAttackDamage;
	public final IntValue allosaurusSpawnWeight;
	public final IntValue allosaurusSpawnGroupMinimum;
	public final IntValue allosaurusSpawnGroupMaximum;

	public final DoubleValue anomalocarisHeath;
	public final DoubleValue anomalocarisAttackDamage;
	public final IntValue anomalocarisSpawnWeight;
	public final IntValue anomalocarisSpawnGroupMinimum;
	public final IntValue anomalocarisSpawnGroupMaximum;

	public final DoubleValue carnotaurusHeath;
	public final DoubleValue carnotaurusAttackDamage;
	public final IntValue carnotaurusSpawnWeight;
	public final IntValue carnotaurusSpawnGroupMinimum;
	public final IntValue carnotaurusSpawnGroupMaximum;

	public final DoubleValue chilesaurusHeath;
	public final IntValue chilesaurusSpawnWeight;
	public final IntValue chilesaurusSpawnGroupMinimum;
	public final IntValue chilesaurusSpawnGroupMaximum;

	public final DoubleValue cryolophosaurusHeath;
	public final DoubleValue cryolophosaurusAttackDamage;
	public final IntValue cryolophosaurusSpawnWeight;
	public final IntValue cryolophosaurusSpawnGroupMinimum;
	public final IntValue cryolophosaurusSpawnGroupMaximum;

	public final DoubleValue cryptoclidusHeath;
	public final DoubleValue cryptoclidusAttackDamage;
	public final DoubleValue cryptoclidusSpeed;
	public final IntValue cryptoclidusSpawnWeight;
	public final IntValue cryptoclidusSpawnGroupMinimum;
	public final IntValue cryptoclidusSpawnGroupMaximum;

	public final DoubleValue diictodonHeath;
	public final IntValue diictodonSpawnWeight;
	public final IntValue diictodonSpawnGroupMinimum;
	public final IntValue diictodonSpawnGroupMaximum;

	public final DoubleValue dilophosaurusHeath;
	public final DoubleValue dilophosaurusAttackDamage;
	public final IntValue dilophosaurusSpawnWeight;
	public final IntValue dilophosaurusSpawnGroupMinimum;
	public final IntValue dilophosaurusSpawnGroupMaximum;

	public final DoubleValue dimetrodonHeath;
	public final DoubleValue dimetrodonAttackDamage;
	public final IntValue dimetrodonSpawnWeight;
	public final IntValue dimetrodonSpawnGroupMinimum;
	public final IntValue dimetrodonSpawnGroupMaximum;

	public final DoubleValue edaphosaurusHeath;
	public final IntValue edaphosaurusSpawnWeight;
	public final IntValue edaphosaurusSpawnGroupMinimum;
	public final IntValue edaphosaurusSpawnGroupMaximum;

	public final DoubleValue eoraptorHeath;
	public final DoubleValue eoraptorAttackDamage;
	public final IntValue eoraptorSpawnWeight;
	public final IntValue eoraptorSpawnGroupMinimum;
	public final IntValue eoraptorSpawnGroupMaximum;

	public final DoubleValue eustreptospondylusHeath;
	public final DoubleValue eustreptospondylusAttackDamage;
	public final IntValue eustreptospondylusSpawnWeight;
	public final IntValue eustreptospondylusSpawnGroupMinimum;
	public final IntValue eustreptospondylusSpawnGroupMaximum;

	public final DoubleValue fukuivenatorHeath;
	public final DoubleValue fukuivenatorAttackDamage;
	public final IntValue fukuivenatorSpawnWeight;
	public final IntValue fukuivenatorSpawnGroupMinimum;
	public final IntValue fukuivenatorSpawnGroupMaximum;

	public final DoubleValue giganotosaurusHeath;
	public final DoubleValue giganotosaurusAttackDamage;
	public final IntValue giganotosaurusSpawnWeight;
	public final IntValue giganotosaurusSpawnGroupMinimum;
	public final IntValue giganotosaurusSpawnGroupMaximum;

	public final DoubleValue gorgonopsHeath;
	public final DoubleValue gorgonopsAttackDamage;
	public final IntValue gorgonopsSpawnWeight;
	public final IntValue gorgonopsSpawnGroupMinimum;
	public final IntValue gorgonopsSpawnGroupMaximum;

	public final DoubleValue greatAukHeath;
	public final DoubleValue greatAukAttackDamage;
	public final IntValue greatAukSpawnWeight;
	public final IntValue greatAukSpawnGroupMinimum;
	public final IntValue greatAukSpawnGroupMaximum;

	public final DoubleValue kentrosaurusHeath;
	public final IntValue kentrosaurusSpawnWeight;
	public final IntValue kentrosaurusSpawnGroupMinimum;
	public final IntValue kentrosaurusSpawnGroupMaximum;

	public final DoubleValue liaoningosaurusHeath;
	public final IntValue liaoningosaurusSpawnWeight;
	public final IntValue liaoningosaurusSpawnGroupMinimum;
	public final IntValue liaoningosaurusSpawnGroupMaximum;

	public final DoubleValue mesosaurusHeath;
	public final DoubleValue mesosaurusAttackDamage;
	public final IntValue mesosaurusSpawnWeight;
	public final IntValue mesosaurusSpawnGroupMinimum;
	public final IntValue mesosaurusSpawnGroupMaximum;

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

	public final DoubleValue ostromiaHeath;
	public final DoubleValue ostromiaAttackDamage;
	public final IntValue ostromiaSpawnWeight;
	public final IntValue ostromiaSpawnGroupMinimum;
	public final IntValue ostromiaSpawnGroupMaximum;

	public final DoubleValue ouranosaurusHeath;
	public final IntValue ouranosaurusSpawnWeight;
	public final IntValue ouranosaurusSpawnGroupMinimum;
	public final IntValue ouranosaurusSpawnGroupMaximum;

	public final DoubleValue parksosaurusHeath;
	public final IntValue parksosaurusSpawnWeight;
	public final IntValue parksosaurusSpawnGroupMinimum;
	public final IntValue parksosaurusSpawnGroupMaximum;

	public final DoubleValue procompsognathusHeath;
	public final DoubleValue procompsognathusAttackDamage;
	public final IntValue procompsognathusSpawnWeight;
	public final IntValue procompsognathusSpawnGroupMinimum;
	public final IntValue procompsognathusSpawnGroupMaximum;

	public final DoubleValue protosuchusHeath;
	public final DoubleValue protosuchusAttackDamage;
	public final IntValue protosuchusSpawnWeight;
	public final IntValue protosuchusSpawnGroupMinimum;
	public final IntValue protosuchusSpawnGroupMaximum;

	public final DoubleValue psittacosaurusHeath;
	public final IntValue psittacosaurusSpawnWeight;
	public final IntValue psittacosaurusSpawnGroupMinimum;
	public final IntValue psittacosaurusSpawnGroupMaximum;

	public final IntValue palaeoniscumSpawnWeight;
	public final IntValue palaeoniscumSpawnGroupMinimum;
	public final IntValue palaeoniscumSpawnGroupMaximum;

	public final DoubleValue rhinesuchusHeath;
	public final DoubleValue rhinesuchusAttackDamage;
	public final IntValue rhinesuchusSpawnWeight;
	public final IntValue rhinesuchusSpawnGroupMinimum;
	public final IntValue rhinesuchusSpawnGroupMaximum;

	public final DoubleValue suchomimusHeath;
	public final DoubleValue suchomimusAttackDamage;
	public final IntValue suchomimusSpawnWeight;
	public final IntValue suchomimusSpawnGroupMinimum;
	public final IntValue suchomimusSpawnGroupMaximum;

	public final DoubleValue utahraptorHeath;
	public final DoubleValue utahraptorAttackDamage;
	public final IntValue utahraptorSpawnWeight;
	public final IntValue utahraptorSpawnGroupMinimum;
	public final IntValue utahraptorSpawnGroupMaximum;

	public final DoubleValue tetraceratopsHeath;
	public final IntValue tetraceratopsSpawnWeight;
	public final IntValue tetraceratopsSpawnGroupMinimum;
	public final IntValue tetraceratopsSpawnGroupMaximum;

	public final DoubleValue thanosHeath;
	public final DoubleValue thanosAttackDamage;
	public final IntValue thanosSpawnWeight;
	public final IntValue thanosSpawnGroupMinimum;
	public final IntValue thanosSpawnGroupMaximum;

	public final DoubleValue toyotamaphimeiaHeath;
	public final DoubleValue toyotamaphimeiaAttackDamage;
	public final IntValue toyotamaphimeiaSpawnWeight;
	public final IntValue toyotamaphimeiaSpawnGroupMinimum;
	public final IntValue toyotamaphimeiaSpawnGroupMaximum;

	public final DoubleValue tyrannosaurusHeath;
	public final DoubleValue tyrannosaurusAttackDamage;
	public final IntValue tyrannosaurusSpawnWeight;
	public final IntValue tyrannosaurusSpawnGroupMinimum;
	public final IntValue tyrannosaurusSpawnGroupMaximum;

	public final DoubleValue zephyrosaurusHeath;
	public final IntValue zephyrosaurusSpawnWeight;
	public final IntValue zephyrosaurusSpawnGroupMinimum;
	public final IntValue zephyrosaurusSpawnGroupMaximum;

	public LostWorldsCommonConfig(ForgeConfigSpec.Builder builder) {
		// Ids
		this.blackMarketGenerationId = builder.comment("Sets the Black Market's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "blackMarketGenerationId").defineInRange("blackMarketGenerationId", 930134351, 111111111, 999999999);
		this.blackMarketShouldSpawn = builder.comment("Sets if the Black Market should spawn in the overworld.").translation(TRANSLATION_TEXT + "blackMarketShouldSpawn").define("blackMarketShouldSpawn", true);

		this.surfaceFossilGenerationId = builder.comment("Sets the Surface Fossil's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "fossilGenerationId").defineInRange("fossilGenerationId", 930134352, 111111111, 999999999);
		this.subterraneanFossilGenerationId = builder.comment("Sets the Subterranean Fossil's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "subterraneanFossilGenerationId").defineInRange("subterraneanFossilGenerationId", 930134357, 111111111, 999999999);
		this.traceFossilGenerationId = builder.comment("Sets the Trace Fossil's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "traceFossilGenerationId").defineInRange("traceFossilGenerationId", 930134353, 111111111, 999999999);

		this.meteoriteGenerationId = builder.comment("Sets the Meteorite's structure Id. Minecraft requires a number id, so this may conflict with another mod. If so, change it.").translation(TRANSLATION_TEXT + "meteoriteGenerationId").defineInRange("meteoriteGenerationId", 930134354, 111111111, 999999999);
		this.meteoriteShouldSpawn = builder.comment("Sets if meteorite structures should spawn in the overworld.").translation(TRANSLATION_TEXT + "meteoritesShouldSpawn").define("meteoritesShouldSpawn", true);

		this.villageStructures = builder.comment("Sets if new village houses should spawn.").translation(TRANSLATION_TEXT + "villageStructures").define("villageStructures", true);
		this.villageStructureWeights = builder.comment("Sets if weight of new village houses spawning.").translation(TRANSLATION_TEXT + "villageStructureWeights").defineInRange("villageStructureWeights", 4, 1, 999);

		// Features
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
		this.petrifiedCypressTreeShouldSpawn = builder.comment("Sets if petrified cypress trees should spawn in the overworld.").translation(TRANSLATION_TEXT + "petrifiedCypressTreeShouldSpawn").define("petrifiedCypressTreeShouldSpawn", true);
		this.petrifiedGinkgoTreeShouldSpawn = builder.comment("Sets if petrified ginkgo trees should spawn in the overworld.").translation(TRANSLATION_TEXT + "petrifiedGinkgoTreeShouldSpawn").define("petrifiedGinkgoTreeShouldSpawn", true);
		this.petrifiedSequoiaTreeShouldSpawn = builder.comment("Sets if petrified sequoia trees should spawn in the overworld.").translation(TRANSLATION_TEXT + "petrifiedSequoiaTreeShouldSpawn").define("petrifiedSequoiaTreeShouldSpawn", true);

		this.petrifiedAraucariaChance = builder.comment("Sets the petrified araucaria chance of spawning in the overworld.").translation(TRANSLATION_TEXT + "petrifiedAraucariaChance").defineInRange("petrifiedAraucariaChance", 32, 0, 999);
		this.petrifiedCalamitesChance = builder.comment("Sets the petrified calamites chance of spawning in the overworld.").translation(TRANSLATION_TEXT + "petrifiedCalamitesChance").defineInRange("petrifiedCalamitesChance", 32, 0, 999);
		this.petrifiedConiferChance = builder.comment("Sets the petrified conifer chance of spawning in the overworld.").translation(TRANSLATION_TEXT + "petrifiedConiferChance").defineInRange("petrifiedConiferChance", 32, 0, 999);
		this.petrifiedGinkgoChance = builder.comment("Sets the petrified ginkgo chance of spawning in the overworld.").translation(TRANSLATION_TEXT + "petrifiedGinkgoChance").defineInRange("petrifiedGinkgoChance", 32, 0, 999);

		// Biome
		this.spawnOverworldBiomes = builder.comment("Sets if the mod's overworld biomes should spawn, note requires terrablender to work").translation(TRANSLATION_TEXT + "spawnOverworldBiomes").define("spawnOverworldBiomes", true);

		this.overworldBiomeWeight = builder.comment("Sets the weight the mod's overworld biomes, note requires terrablender to work, for infomation regarding weight, go here: https://github.com/Glitchfiend/TerraBlender/wiki/Getting-started#api-introduction").translation(TRANSLATION_TEXT + "overworldBiomeWeight").defineInRange("overworldBiomeWeight", 3, 1, 999);

		// Dinosaurs
		this.livingFossils = builder.comment("If false, living fossils like the nautilus will not spawn in the overworld.").translation(TRANSLATION_TEXT + "livingFossils").define("livingFossils", true);
		this.dinosaursSpawnInOverworld = builder.comment("If true, dinosaurs will spawn naturally in the overworld. They will spawn on any block listed in the dino_spawnables tag.").translation(TRANSLATION_TEXT + "dinosaursSpawnInOverworld").define("dinosaursSpawnInOverworld", false);

		// this.tameableDinos = builder.comment("If true, dinos will be able to be
		// tamed.").translation(TRANSLATION_TEXT +
		// "tameableDinos").define("tameableDinos", false);
		this.maxDinoGroup = builder.comment("Sets the ammount of dinosaurs that prevent breeding.").translation(TRANSLATION_TEXT + "maxDinoGroup").defineInRange("maxDinoGroup", 10, 1, 30);
		this.maxSearchRange = builder.comment("Sets the search rang of the group to prevent breeding. POTENTIAL PREFORMACE HIT.").translation(TRANSLATION_TEXT + "maxSearchRange").defineInRange("maxSearchRange", 400.0D, 100.0D, 9000.0D);

		this.allosaurusHeath = builder.comment("Sets the heath of the Allosaurus").translation(TRANSLATION_TEXT + "allosaurusHeath").defineInRange("allosaurusHeath", 40.0D, 1.0D, 999.0D);
		this.allosaurusAttackDamage = builder.comment("Sets the attack damage of the Allosaurus").translation(TRANSLATION_TEXT + "allosaurusAttackDamage").defineInRange("allosaurusAttackDamage", 12.0D, 1.0D, 999.0D);
		this.allosaurusSpawnWeight = builder.comment("Sets the weight of Allosaurus spawning").translation(TRANSLATION_TEXT + "allosaurusSpawnWeight").defineInRange("allosaurusSpawnWeight", 1, 1, 100);
		this.allosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Allosaurus in a spawn group").translation(TRANSLATION_TEXT + "allosaurusSpawnGroupMinimum").defineInRange("allosaurusSpawnGroupMinimum", 1, 1, 100);
		this.allosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Allosaurus in a spawn group").translation(TRANSLATION_TEXT + "allosaurusSpawnGroupMaximum").defineInRange("allosaurusSpawnGroupMaximum", 1, 1, 100);

		this.anomalocarisHeath = builder.comment("Sets the heath of the Allosaurus").translation(TRANSLATION_TEXT + "anomalocarisHeath").defineInRange("anomalocarisHeath", 8.0D, 1.0D, 999.0D);
		this.anomalocarisAttackDamage = builder.comment("Sets the attack damage of the Allosaurus").translation(TRANSLATION_TEXT + "anomalocarisAttackDamage").defineInRange("anomalocarisAttackDamage", 3.0D, 1.0D, 999.0D);
		this.anomalocarisSpawnWeight = builder.comment("Sets the weight of Allosaurus spawning").translation(TRANSLATION_TEXT + "anomalocarisSpawnWeight").defineInRange("anomalocarisSpawnWeight", 3, 1, 100);
		this.anomalocarisSpawnGroupMinimum = builder.comment("Sets the minimum amount of Allosaurus in a spawn group").translation(TRANSLATION_TEXT + "anomalocarisSpawnGroupMinimum").defineInRange("anomalocarisSpawnGroupMinimum", 1, 1, 100);
		this.anomalocarisSpawnGroupMaximum = builder.comment("Sets the maximum amount of Allosaurus in a spawn group").translation(TRANSLATION_TEXT + "anomalocarisSpawnGroupMaximum").defineInRange("anomalocarisSpawnGroupMaximum", 1, 1, 100);

		this.carnotaurusHeath = builder.comment("Sets the heath of the Carnotaurus").translation(TRANSLATION_TEXT + "carnotaurusHeath").defineInRange("carnotaurusHeath", 40.0D, 1.0D, 999.0D);
		this.carnotaurusAttackDamage = builder.comment("Sets the attack damage of the Carnotaurus").translation(TRANSLATION_TEXT + "carnotaurusAttackDamage").defineInRange("carnotaurusAttackDamage", 14.0D, 1.0D, 999.0D);
		this.carnotaurusSpawnWeight = builder.comment("Sets the weight of Carnotaurus spawning").translation(TRANSLATION_TEXT + "carnotaurusSpawnWeight").defineInRange("carnotaurusSpawnWeight", 3, 1, 100);
		this.carnotaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Carnotaurus in a spawn group").translation(TRANSLATION_TEXT + "carnotaurusSpawnGroupMinimum").defineInRange("carnotaurusSpawnGroupMinimum", 1, 1, 100);
		this.carnotaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Carnotaurus in a spawn group").translation(TRANSLATION_TEXT + "carnotaurusSpawnGroupMaximum").defineInRange("carnotaurusSpawnGroupMaximum", 2, 1, 100);

		this.chilesaurusHeath = builder.comment("Sets the heath of the Chilesaurs").translation(TRANSLATION_TEXT + "chilesaurusHeath").defineInRange("chilesaurusHeath", 10.0D, 1.0D, 999.0D);
		this.chilesaurusSpawnWeight = builder.comment("Sets the weight of Chilesaurus spawning").translation(TRANSLATION_TEXT + "chilesaurusSpawnWeight").defineInRange("chilesaurusSpawnWeight", 4, 1, 100);
		this.chilesaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Chilesaurus in a spawn group").translation(TRANSLATION_TEXT + "chilesaurusSpawnGroupMinimum").defineInRange("chilesaurusSpawnGroupMinimum", 3, 1, 100);
		this.chilesaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Chilesaurus in a spawn group").translation(TRANSLATION_TEXT + "chilesaurusSpawnGroupMaximum").defineInRange("chilesaurusSpawnGroupMaximum", 10, 1, 100);

		this.cryolophosaurusHeath = builder.comment("Sets the heath of the Cryolophosaurus").translation(TRANSLATION_TEXT + "cryolophosaurusHeath").defineInRange("cryolophosaurusHeath", 25.0D, 1.0D, 999.0D);
		this.cryolophosaurusAttackDamage = builder.comment("Sets the attack damage of the Cryolophosaurus").translation(TRANSLATION_TEXT + "cryolophosaurusAttackDamage").defineInRange("cryolophosaurusAttackDamage", 7.0D, 1.0D, 999.0D);
		this.cryolophosaurusSpawnWeight = builder.comment("Sets the weight of Cryolophosaurus spawning").translation(TRANSLATION_TEXT + "cryolophosaurusSpawnWeight").defineInRange("cryolophosaurusSpawnWeight", 3, 1, 100);
		this.cryolophosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Carnotaurus in a spawn group").translation(TRANSLATION_TEXT + "cryolophosaurusSpawnGroupMinimum").defineInRange("cryolophosaurusSpawnGroupMinimum", 2, 1, 100);
		this.cryolophosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Carnotaurus in a spawn group").translation(TRANSLATION_TEXT + "cryolophosaurusSpawnGroupMaximum").defineInRange("cryolophosaurusSpawnGroupMaximum", 3, 1, 100);

		this.cryptoclidusHeath = builder.comment("Sets the heath of the Cryptoclidus").translation(TRANSLATION_TEXT + "cryptoclidusHeath").defineInRange("cryptoclidusHeath", 17.0D, 1.0D, 999.0D);
		this.cryptoclidusSpeed = builder.comment("Sets the speedModifer of the Cryptoclidus").translation(TRANSLATION_TEXT + "cryptoclidusSpeed").defineInRange("cryptoclidusSpeed", 1.6D, 1.0D, 999.0D);
		this.cryptoclidusAttackDamage = builder.comment("Sets the attack damage of the Cryptoclidus").translation(TRANSLATION_TEXT + "cryptoclidusAttackDamage").defineInRange("cryptoclidusAttackDamage", 8.0D, 1.0D, 999.0D);
		this.cryptoclidusSpawnWeight = builder.comment("Sets the weight of Cryptoclidus spawning").translation(TRANSLATION_TEXT + "cryptoclidusSpawnWeight").defineInRange("cryptoclidusSpawnWeight", 1, 1, 100);
		this.cryptoclidusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Cryptoclidus in a spawn group").translation(TRANSLATION_TEXT + "cryptoclidusSpawnGroupMinimum").defineInRange("cryptoclidusSpawnGroupMinimum", 1, 1, 100);
		this.cryptoclidusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Cryptoclidus in a spawn group").translation(TRANSLATION_TEXT + "cryptoclidusSpawnGroupMaximum").defineInRange("cryptoclidusSpawnGroupMaximum", 1, 1, 100);

		this.diictodonHeath = builder.comment("Sets the heath of the Diictodon").translation(TRANSLATION_TEXT + "diictodonHeath").defineInRange("diictodonHeath", 4.0D, 1.0D, 999.0D);
		this.diictodonSpawnWeight = builder.comment("Sets the weight of Diictodon spawning").translation(TRANSLATION_TEXT + "diictodonSpawnWeight").defineInRange("diictodonSpawnWeight", 4, 1, 100);
		this.diictodonSpawnGroupMinimum = builder.comment("Sets the minimum amount of Diictodon in a spawn group").translation(TRANSLATION_TEXT + "diictodonSpawnGroupMinimum").defineInRange("diictodonSpawnGroupMinimum", 3, 1, 100);
		this.diictodonSpawnGroupMaximum = builder.comment("Sets the maximum amount of Diictodon in a spawn group").translation(TRANSLATION_TEXT + "diictodonSpawnGroupMaximum").defineInRange("diictodonSpawnGroupMaximum", 10, 1, 100);

		this.dilophosaurusHeath = builder.comment("Sets the heath of the Dilophosaurus").translation(TRANSLATION_TEXT + "dilophosaurusHeath").defineInRange("dilophosaurusHeath", 35.0D, 1.0D, 999.0D);
		this.dilophosaurusAttackDamage = builder.comment("Sets the attack damage of the Dilophosaurus").translation(TRANSLATION_TEXT + "dilophosaurusAttackDamage").defineInRange("dilophosaurusAttackDamage", 10.0D, 1.0D, 999.0D);
		this.dilophosaurusSpawnWeight = builder.comment("Sets the weight of Dilophosaurus spawning").translation(TRANSLATION_TEXT + "dilophosaurusSpawnWeight").defineInRange("dilophosaurusSpawnWeight", 4, 1, 100);
		this.dilophosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Dilophosaurus in a spawn group").translation(TRANSLATION_TEXT + "dilophosaurusSpawnGroupMinimum").defineInRange("dilophosaurusSpawnGroupMinimum", 1, 1, 100);
		this.dilophosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Dilophosaurus in a spawn group").translation(TRANSLATION_TEXT + "dilophosaurusSpawnGroupMaximum").defineInRange("dilophosaurusSpawnGroupMaximum", 3, 1, 100);

		this.dimetrodonHeath = builder.comment("Sets the heath of the Dimetrodon").translation(TRANSLATION_TEXT + "dimetrodonHeath").defineInRange("dimetrodonHeath", 35.0D, 1.0D, 999.0D);
		this.dimetrodonAttackDamage = builder.comment("Sets the attack damage of the Dimetrodon").translation(TRANSLATION_TEXT + "dimetrodonAttackDamage").defineInRange("dimetrodonAttackDamage", 10.0D, 1.0D, 999.0D);
		this.dimetrodonSpawnWeight = builder.comment("Sets the weight of Dimetrodon spawning").translation(TRANSLATION_TEXT + "dimetrodonSpawnWeight").defineInRange("dimetrodonSpawnWeight", 4, 1, 100);
		this.dimetrodonSpawnGroupMinimum = builder.comment("Sets the minimum amount of Dimetrodon in a spawn group").translation(TRANSLATION_TEXT + "dimetrodonSpawnGroupMinimum").defineInRange("dimetrodonSpawnGroupMinimum", 1, 1, 100);
		this.dimetrodonSpawnGroupMaximum = builder.comment("Sets the maximum amount of Dimetrodon in a spawn group").translation(TRANSLATION_TEXT + "dimetrodonSpawnGroupMaximum").defineInRange("dimetrodonSpawnGroupMaximum", 3, 1, 100);

		this.eoraptorHeath = builder.comment("Sets the heath of the Dilophosaurus").translation(TRANSLATION_TEXT + "eoraptorHeath").defineInRange("eoraptorHeath", 5.0D, 1.0D, 999.0D);
		this.eoraptorAttackDamage = builder.comment("Sets the attack damage of the Dilophosaurus").translation(TRANSLATION_TEXT + "eoraptorAttackDamage").defineInRange("eoraptorAttackDamage", 3.0D, 1.0D, 999.0D);
		this.eoraptorSpawnWeight = builder.comment("Sets the weight of Dilophosaurus spawning").translation(TRANSLATION_TEXT + "eoraptorSpawnWeight").defineInRange("eoraptorSpawnWeight", 4, 1, 100);
		this.eoraptorSpawnGroupMinimum = builder.comment("Sets the minimum amount of Dilophosaurus in a spawn group").translation(TRANSLATION_TEXT + "eoraptorSpawnGroupMinimum").defineInRange("eoraptorSpawnGroupMinimum", 5, 1, 100);
		this.eoraptorSpawnGroupMaximum = builder.comment("Sets the maximum amount of Dilophosaurus in a spawn group").translation(TRANSLATION_TEXT + "eoraptorSpawnGroupMaximum").defineInRange("eoraptorSpawnGroupMaximum", 7, 1, 100);

		this.edaphosaurusHeath = builder.comment("Sets the heath of the Edaphosaurus").translation(TRANSLATION_TEXT + "edaphosaurusHeath").defineInRange("edaphosaurusHeath", 15.0D, 1.0D, 999.0D);
		this.edaphosaurusSpawnWeight = builder.comment("Sets the weight of Edaphosaurus spawning").translation(TRANSLATION_TEXT + "edaphosaurusSpawnWeight").defineInRange("edaphosaurusSpawnWeight", 4, 1, 100);
		this.edaphosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Edaphosaurus in a spawn group").translation(TRANSLATION_TEXT + "edaphosaurusSpawnGroupMinimum").defineInRange("edaphosaurusSpawnGroupMinimum", 5, 1, 100);
		this.edaphosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Edaphosaurus in a spawn group").translation(TRANSLATION_TEXT + "edaphosaurusSpawnGroupMaximum").defineInRange("edaphosaurusSpawnGroupMaximum", 7, 1, 100);

		this.eustreptospondylusHeath = builder.comment("Sets the heath of the Eustreptospondylus").translation(TRANSLATION_TEXT + "eustreptospondylusHeath").defineInRange("eustreptospondylusHeath", 35.0D, 1.0D, 999.0D);
		this.eustreptospondylusAttackDamage = builder.comment("Sets the attack damage of the Eustreptospondylus").translation(TRANSLATION_TEXT + "eustreptospondylusAttackDamage").defineInRange("eustreptospondylusAttackDamage", 10.0D, 1.0D, 999.0D);
		this.eustreptospondylusSpawnWeight = builder.comment("Sets the weight of Eustreptospondylus spawning").translation(TRANSLATION_TEXT + "eustreptospondylusSpawnWeight").defineInRange("eustreptospondylusSpawnWeight", 3, 1, 100);
		this.eustreptospondylusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Eustreptospondylus in a spawn group").translation(TRANSLATION_TEXT + "eustreptospondylusSpawnGroupMinimum").defineInRange("eustreptospondylusSpawnGroupMinimum", 3, 1, 100);
		this.eustreptospondylusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Eustreptospondylus in a spawn group").translation(TRANSLATION_TEXT + "eustreptospondylusSpawnGroupMaximum").defineInRange("eustreptospondylusSpawnGroupMaximum", 6, 1, 100);

		this.fukuivenatorHeath = builder.comment("Sets the heath of the Fukuvenator").translation(TRANSLATION_TEXT + "fukuivenatorHeath").defineInRange("fukuivenatorHeath", 15.0D, 1.0D, 999.0D);
		this.fukuivenatorAttackDamage = builder.comment("Sets the attack damage of the Fukuvenator").translation(TRANSLATION_TEXT + "fukuivenatorAttackDamage").defineInRange("fukuivenatorAttackDamage", 4.0D, 1.0D, 999.0D);
		this.fukuivenatorSpawnWeight = builder.comment("Sets the weight of Fukuvenator spawning").translation(TRANSLATION_TEXT + "fukuivenatorSpawnWeight").defineInRange("fukuivenatorSpawnWeight", 4, 1, 100);
		this.fukuivenatorSpawnGroupMinimum = builder.comment("Sets the minimum amount of Fukuvenator in a spawn group").translation(TRANSLATION_TEXT + "fukuivenatorSpawnGroupMinimum").defineInRange("fukuivenatorSpawnGroupMinimum", 1, 1, 100);
		this.fukuivenatorSpawnGroupMaximum = builder.comment("Sets the maximum amount of Fukuvenator in a spawn group").translation(TRANSLATION_TEXT + "fukuivenatorSpawnGroupMaximum").defineInRange("fukuivenatorSpawnGroupMaximum", 3, 1, 100);

		this.giganotosaurusHeath = builder.comment("Sets the heath of the Gorgonops").translation(TRANSLATION_TEXT + "giganotosaurusHeath").defineInRange("giganotosaurusHeath", 95.0D, 1.0D, 999.0D);
		this.giganotosaurusAttackDamage = builder.comment("Sets the attack damage of the Gorgonops").translation(TRANSLATION_TEXT + "giganotosaurusAttackDamage").defineInRange("giganotosaurusAttackDamage", 30.0D, 1.0D, 999.0D);
		this.giganotosaurusSpawnWeight = builder.comment("Sets the weight of Gorgonops spawning").translation(TRANSLATION_TEXT + "giganotosaurusSpawnWeight").defineInRange("giganotosaurusSpawnWeight", 1, 1, 100);
		this.giganotosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Gorgonops in a spawn group").translation(TRANSLATION_TEXT + "giganotosaurusSpawnGroupMinimum").defineInRange("giganotosaurusSpawnGroupMinimum", 1, 1, 100);
		this.giganotosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Gorgonops in a spawn group").translation(TRANSLATION_TEXT + "giganotosaurusSpawnGroupMaximum").defineInRange("giganotosaurusSpawnGroupMaximum", 1, 1, 100);

		this.gorgonopsHeath = builder.comment("Sets the heath of the Gorgonops").translation(TRANSLATION_TEXT + "gorgonopsHeath").defineInRange("gorgonopsHeath", 15.0D, 1.0D, 999.0D);
		this.gorgonopsAttackDamage = builder.comment("Sets the attack damage of the Gorgonops").translation(TRANSLATION_TEXT + "gorgonopsAttackDamage").defineInRange("gorgonopsAttackDamage", 2.0D, 1.0D, 999.0D);
		this.gorgonopsSpawnWeight = builder.comment("Sets the weight of Gorgonops spawning").translation(TRANSLATION_TEXT + "gorgonopsSpawnWeight").defineInRange("gorgonopsSpawnWeight", 4, 1, 100);
		this.gorgonopsSpawnGroupMinimum = builder.comment("Sets the minimum amount of Gorgonops in a spawn group").translation(TRANSLATION_TEXT + "gorgonopsSpawnGroupMinimum").defineInRange("gorgonopsSpawnGroupMinimum", 1, 1, 100);
		this.gorgonopsSpawnGroupMaximum = builder.comment("Sets the maximum amount of Gorgonops in a spawn group").translation(TRANSLATION_TEXT + "gorgonopsSpawnGroupMaximum").defineInRange("gorgonopsSpawnGroupMaximum", 3, 1, 100);

		this.greatAukHeath = builder.comment("Sets the heath of the Great Auk").translation(TRANSLATION_TEXT + "greatAukHeath").defineInRange("greatAukHeath", 15.0D, 1.0D, 999.0D);
		this.greatAukAttackDamage = builder.comment("Sets the attack damage of the Great Auk").translation(TRANSLATION_TEXT + "greatAukAttackDamage").defineInRange("greatAukAttackDamage", 2.0D, 1.0D, 999.0D);
		this.greatAukSpawnWeight = builder.comment("Sets the weight of Great Auk spawning").translation(TRANSLATION_TEXT + "greatAukSpawnWeight").defineInRange("greatAukSpawnWeight", 4, 1, 100);
		this.greatAukSpawnGroupMinimum = builder.comment("Sets the minimum amount of Great Auk in a spawn group").translation(TRANSLATION_TEXT + "greatAukSpawnGroupMinimum").defineInRange("greatAukSpawnGroupMinimum", 7, 1, 100);
		this.greatAukSpawnGroupMaximum = builder.comment("Sets the maximum amount of Great Auk in a spawn group").translation(TRANSLATION_TEXT + "greatAukSpawnGroupMaximum").defineInRange("greatAukSpawnGroupMaximum", 20, 1, 100);

		this.kentrosaurusHeath = builder.comment("Sets the heath of the Kentrosaurus").translation(TRANSLATION_TEXT + "kentrosaurusHeath").defineInRange("kentrosaurusHeath", 30.0D, 1.0D, 999.0D);
		this.kentrosaurusSpawnWeight = builder.comment("Sets the weight of Kentrosaurus spawning").translation(TRANSLATION_TEXT + "kentrosaurusSpawnWeight").defineInRange("kentrosaurusSpawnWeight", 3, 1, 100);
		this.kentrosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Kentrosaurus in a spawn group").translation(TRANSLATION_TEXT + "kentrosaurusSpawnGroupMinimum").defineInRange("kentrosaurusSpawnGroupMinimum", 2, 1, 100);
		this.kentrosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Kentrosaurus in a spawn group").translation(TRANSLATION_TEXT + "kentrosaurusSpawnGroupMaximum").defineInRange("kentrosaurusSpawnGroupMaximum", 8, 1, 100);

		this.liaoningosaurusHeath = builder.comment("Sets the heath of the Liaoningosaurus").translation(TRANSLATION_TEXT + "liaoningosaurusHeath").defineInRange("liaoningosaurusHeath", 25.0D, 1.0D, 999.0D);
		this.liaoningosaurusSpawnWeight = builder.comment("Sets the weight of Liaoningosaurus spawning").translation(TRANSLATION_TEXT + "liaoningosaurusSpawnWeight").defineInRange("liaoningosaurusSpawnWeight", 3, 1, 100);
		this.liaoningosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Liaoningosaurus in a spawn group").translation(TRANSLATION_TEXT + "liaoningosaurusSpawnGroupMinimum").defineInRange("liaoningosaurusSpawnGroupMinimum", 1, 1, 100);
		this.liaoningosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Liaoningosaurus in a spawn group").translation(TRANSLATION_TEXT + "liaoningosaurusSpawnGroupMaximum").defineInRange("liaoningosaurusSpawnGroupMaximum", 3, 1, 100);

		this.mesosaurusHeath = builder.comment("Sets the heath of the Mesosaurus").translation(TRANSLATION_TEXT + "mesosaurusHeath").defineInRange("mesosaurusHeath", 6.0D, 1.0D, 999.0D);
		this.mesosaurusAttackDamage = builder.comment("Sets the attack damage of the Mesosaurus").translation(TRANSLATION_TEXT + "mesosaurusAttackDamage").defineInRange("mesosaurusAttackDamage", 2.0D, 1.0D, 999.0D);
		this.mesosaurusSpawnWeight = builder.comment("Sets the weight of Mesosaurus spawning").translation(TRANSLATION_TEXT + "liaoningosaurusSpawnWeight").defineInRange("liaoningosaurusSpawnWeight", 3, 1, 100);
		this.mesosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Mesosaurus in a spawn group").translation(TRANSLATION_TEXT + "liaoningosaurusSpawnGroupMinimum").defineInRange("liaoningosaurusSpawnGroupMinimum", 1, 1, 100);
		this.mesosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Mesosaurus in a spawn group").translation(TRANSLATION_TEXT + "liaoningosaurusSpawnGroupMaximum").defineInRange("liaoningosaurusSpawnGroupMaximum", 3, 1, 100);

		this.nautilusHeath = builder.comment("Sets the heath of the Nautilus").translation(TRANSLATION_TEXT + "nautilusHeath").defineInRange("nautilusHeath", 20.0D, 1.0D, 999.0D);
		this.nautilusArmour = builder.comment("Sets the armour of the Nautilus").translation(TRANSLATION_TEXT + "nautilusArmour").defineInRange("nautilusArmour", 30.0D, 1.0D, 999.0D);
		this.nautilusSpawnWeight = builder.comment("Sets the weight of Nautilus spawning").translation(TRANSLATION_TEXT + "nautilusSpawnWeight").defineInRange("nautilusSpawnWeight", 1, 1, 100);
		this.nautilusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Nautilus in a spawn group").translation(TRANSLATION_TEXT + "nautilusSpawnGroupMinimum").defineInRange("nautilusSpawnGroupMinimum", 1, 1, 100);
		this.nautilusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Nautilus in a spawn group").translation(TRANSLATION_TEXT + "nautilusSpawnGroupMaximum").defineInRange("nautilusSpawnGroupMaximum", 1, 1, 100);

		this.ophthalmosaurusHeath = builder.comment("Sets the heath of the Ophthalmosaurus").translation(TRANSLATION_TEXT + "ophthalmosaurusHeath").defineInRange("ophthalmosaurusHeath", 25.0D, 1.0D, 999.0D);
		this.ophthalmosaurusSpeed = builder.comment("Sets the speedModifer of the Ophthalmosaurus").translation(TRANSLATION_TEXT + "ophthalmosaurusSpeed").defineInRange("ophthalmosaurusSpeed", 1.6D, 1.0D, 999.0D);
		this.ophthalmosaurusAttackDamage = builder.comment("Sets the attack damage of the Ophthalmosaurus").translation(TRANSLATION_TEXT + "ophthalmosaurusAttackDamage").defineInRange("ophthalmosaurusAttackDamage", 5.0D, 1.0D, 999.0D);
		this.ophthalmosaurusSpawnWeight = builder.comment("Sets the weight of Ophthalmosaurus spawning").translation(TRANSLATION_TEXT + "ophthalmosaurusSpawnWeight").defineInRange("ophthalmosaurusSpawnWeight", 1, 1, 100);
		this.ophthalmosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Ophthalmosaurus in a spawn group").translation(TRANSLATION_TEXT + "ophthalmosaurusSpawnGroupMinimum").defineInRange("ophthalmosaurusSpawnGroupMinimum", 2, 1, 100);
		this.ophthalmosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Ophthalmosaurus in a spawn group").translation(TRANSLATION_TEXT + "ophthalmosaurusSpawnGroupMaximum").defineInRange("ophthalmosaurusSpawnGroupMaximum", 8, 1, 100);

		this.ostromiaHeath = builder.comment("Sets the heath of the Ostromia").translation(TRANSLATION_TEXT + "ostromiaHeath").defineInRange("ostromiaHeath", 8.0D, 1.0D, 999.0D);
		this.ostromiaAttackDamage = builder.comment("Sets the attack damage of the Ostromia").translation(TRANSLATION_TEXT + "ostromiaAttackDamage").defineInRange("ostromiaAttackDamage", 1.0D, 1.0D, 999.0D);
		this.ostromiaSpawnWeight = builder.comment("Sets the weight of Ostromia spawning").translation(TRANSLATION_TEXT + "ostromiaSpawnWeight").defineInRange("ostromiaSpawnWeight", 1, 1, 100);
		this.ostromiaSpawnGroupMinimum = builder.comment("Sets the minimum amount of Ostromia in a spawn group").translation(TRANSLATION_TEXT + "ostromiaSpawnGroupMinimum").defineInRange("ostromiaSpawnGroupMinimum", 2, 1, 100);
		this.ostromiaSpawnGroupMaximum = builder.comment("Sets the maximum amount of Ostromia in a spawn group").translation(TRANSLATION_TEXT + "ostromiaSpawnGroupMaximum").defineInRange("ostromiaSpawnGroupMaximum", 8, 1, 100);

		this.ouranosaurusHeath = builder.comment("Sets the heath of the Ouranosaurus").translation(TRANSLATION_TEXT + "ouranosaurusHeath").defineInRange("ouranosaurusHeath", 30.0D, 1.0D, 999.0D);
		this.ouranosaurusSpawnWeight = builder.comment("Sets the weight of Ouranosaurus spawning").translation(TRANSLATION_TEXT + "ouranosaurusSpawnWeight").defineInRange("ouranosaurusSpawnWeight", 4, 1, 100);
		this.ouranosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Ouranosaurus in a spawn group").translation(TRANSLATION_TEXT + "ouranosaurusSpawnGroupMinimum").defineInRange("ouranosaurusSpawnGroupMinimum", 2, 1, 100);
		this.ouranosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Ouranosaurus in a spawn group").translation(TRANSLATION_TEXT + "ouranosaurusSpawnGroupMaximum").defineInRange("ouranosaurusSpawnGroupMaximum", 8, 1, 100);

		this.parksosaurusHeath = builder.comment("Sets the heath of the Parksosaurus").translation(TRANSLATION_TEXT + "parksosaurusHeath").defineInRange("parksosaurusHeath", 10.0D, 1.0D, 999.0D);
		this.parksosaurusSpawnWeight = builder.comment("Sets the weight of Parksosaurus spawning").translation(TRANSLATION_TEXT + "parksosaurusSpawnWeight").defineInRange("parksosaurusSpawnWeight", 4, 1, 100);
		this.parksosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Parksosaurus in a spawn group").translation(TRANSLATION_TEXT + "parksosaurusSpawnGroupMinimum").defineInRange("parksosaurusSpawnGroupMinimum", 3, 1, 100);
		this.parksosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Parksosaurus in a spawn group").translation(TRANSLATION_TEXT + "parksosaurusSpawnGroupMaximum").defineInRange("parksosaurusSpawnGroupMaximum", 10, 1, 100);

		this.procompsognathusHeath = builder.comment("Sets the heath of the Procompsognathus").translation(TRANSLATION_TEXT + "procompsognathusHeath").defineInRange("procompsognathusHeath", 3.0D, 1.0D, 999.0D);
		this.procompsognathusAttackDamage = builder.comment("Sets the attack damage of the Procompsognathus").translation(TRANSLATION_TEXT + "procompsognathusAttackDamage").defineInRange("procompsognathusAttackDamage", 1.0D, 1.0D, 999.0D);
		this.procompsognathusSpawnWeight = builder.comment("Sets the weight of Procompsognathus spawning").translation(TRANSLATION_TEXT + "procompsognathusSpawnWeight").defineInRange("procompsognathusSpawnWeight", 3, 1, 100);
		this.procompsognathusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Procompsognathus in a spawn group").translation(TRANSLATION_TEXT + "procompsognathusSpawnGroupMinimum").defineInRange("procompsognathusSpawnGroupMinimum", 5, 1, 100);
		this.procompsognathusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Procompsognathus in a spawn group").translation(TRANSLATION_TEXT + "procompsognathusSpawnGroupMaximum").defineInRange("procompsognathusSpawnGroupMaximum", 11, 1, 100);

		this.protosuchusHeath = builder.comment("Sets the heath of the Protosuchus").translation(TRANSLATION_TEXT + "protosuchusHeath").defineInRange("protosuchusHeath", 10.0D, 1.0D, 999.0D);
		this.protosuchusAttackDamage = builder.comment("Sets the attack damage of the Protosuchus").translation(TRANSLATION_TEXT + "protosuchusAttackDamage").defineInRange("protosuchusAttackDamage", 4.0D, 1.0D, 999.0D);
		this.protosuchusSpawnWeight = builder.comment("Sets the weight of Protosuchus spawning").translation(TRANSLATION_TEXT + "protosuchusSpawnWeight").defineInRange("protosuchusSpawnWeight", 2, 1, 100);
		this.protosuchusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Protosuchus in a spawn group").translation(TRANSLATION_TEXT + "protosuchusSpawnGroupMinimum").defineInRange("protosuchusSpawnGroupMinimum", 1, 1, 100);
		this.protosuchusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Protosuchus in a spawn group").translation(TRANSLATION_TEXT + "protosuchusSpawnGroupMaximum").defineInRange("protosuchusSpawnGroupMaximum", 2, 1, 100);

		this.psittacosaurusHeath = builder.comment("Sets the heath of the Psittacosaurus").translation(TRANSLATION_TEXT + "psittacosaurusHeath").defineInRange("psittacosaurusHeath", 7.0D, 1.0D, 999.0D);
		this.psittacosaurusSpawnWeight = builder.comment("Sets the weight of Psittacosaurus spawning").translation(TRANSLATION_TEXT + "psittacosaurusSpawnWeight").defineInRange("psittacosaurusSpawnWeight", 3, 1, 100);
		this.psittacosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Psittacosaurus in a spawn group").translation(TRANSLATION_TEXT + "psittacosaurusSpawnGroupMinimum").defineInRange("psittacosaurusSpawnGroupMinimum", 5, 1, 100);
		this.psittacosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Psittacosaurus in a spawn group").translation(TRANSLATION_TEXT + "psittacosaurusSpawnGroupMaximum").defineInRange("psittacosaurusSpawnGroupMaximum", 11, 1, 100);

		this.palaeoniscumSpawnWeight = builder.comment("Sets the weight of Palaeoniscum spawning").translation(TRANSLATION_TEXT + "palaeoniscumSpawnWeight").defineInRange("palaeoniscumSpawnWeight", 3, 1, 100);
		this.palaeoniscumSpawnGroupMinimum = builder.comment("Sets the minimum amount of Palaeoniscum in a spawn group").translation(TRANSLATION_TEXT + "palaeoniscumSpawnGroupMinimum").defineInRange("palaeoniscumSpawnGroupMinimum", 5, 1, 100);
		this.palaeoniscumSpawnGroupMaximum = builder.comment("Sets the maximum amount of Palaeoniscum in a spawn group").translation(TRANSLATION_TEXT + "palaeoniscumSpawnGroupMaximum").defineInRange("palaeoniscumSpawnGroupMaximum", 11, 1, 100);

		this.rhinesuchusHeath = builder.comment("Sets the heath of the Rhinesuchus").translation(TRANSLATION_TEXT + "rhinesuchusHeath").defineInRange("rhinesuchusHeath", 13.0D, 1.0D, 999.0D);
		this.rhinesuchusAttackDamage = builder.comment("Sets the attack damage of the Rhinesuchus").translation(TRANSLATION_TEXT + "rhinesuchusAttackDamage").defineInRange("rhinesuchusAttackDamage", 6.0D, 1.0D, 999.0D);
		this.rhinesuchusSpawnWeight = builder.comment("Sets the weight of Rhinesuchus spawning").translation(TRANSLATION_TEXT + "rhinesuchusSpawnWeight").defineInRange("rhinesuchusSpawnWeight", 3, 1, 100);
		this.rhinesuchusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Rhinesuchus in a spawn group").translation(TRANSLATION_TEXT + "rhinesuchusSpawnGroupMinimum").defineInRange("rhinesuchusSpawnGroupMinimum", 5, 1, 100);
		this.rhinesuchusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Rhinesuchus in a spawn group").translation(TRANSLATION_TEXT + "rhinesuchusSpawnGroupMaximum").defineInRange("rhinesuchusSpawnGroupMaximum", 11, 1, 100);

		this.suchomimusHeath = builder.comment("Sets the heath of the Suchomimus").translation(TRANSLATION_TEXT + "suchomimusHeath").defineInRange("suchomimusHeath", 20.0D, 1.0D, 999.0D);
		this.suchomimusAttackDamage = builder.comment("Sets the attack damage of the Suchomimus").translation(TRANSLATION_TEXT + "suchomimusAttackDamage").defineInRange("suchomimusAttackDamage", 5.0D, 1.0D, 999.0D);
		this.suchomimusSpawnWeight = builder.comment("Sets the weight of Suchomimus spawning").translation(TRANSLATION_TEXT + "suchomimusSpawnWeight").defineInRange("suchomimusSpawnWeight", 3, 1, 100);
		this.suchomimusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Suchomimus in a spawn group").translation(TRANSLATION_TEXT + "suchomimusSpawnGroupMinimum").defineInRange("suchomimusSpawnGroupMinimum", 1, 1, 100);
		this.suchomimusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Suchomimus in a spawn group").translation(TRANSLATION_TEXT + "suchomimusSpawnGroupMaximum").defineInRange("suchomimusSpawnGroupMaximum", 4, 1, 100);

		this.tetraceratopsHeath = builder.comment("Sets the heath of the Tetraceratops").translation(TRANSLATION_TEXT + "psittacosaurusHeath").defineInRange("tetraceratopsHeath", 13.0D, 1.0D, 999.0D);
		this.tetraceratopsSpawnWeight = builder.comment("Sets the weight of Tetraceratops spawning").translation(TRANSLATION_TEXT + "tetraceratopsSpawnWeight").defineInRange("tetraceratopsSpawnWeight", 3, 1, 100);
		this.tetraceratopsSpawnGroupMinimum = builder.comment("Sets the minimum amount of Tetraceratops in a spawn group").translation(TRANSLATION_TEXT + "tetraceratopsSpawnGroupMinimum").defineInRange("tetraceratopsSpawnGroupMinimum", 1, 1, 100);
		this.tetraceratopsSpawnGroupMaximum = builder.comment("Sets the maximum amount of Tetraceratops in a spawn group").translation(TRANSLATION_TEXT + "tetraceratopsSpawnGroupMaximum").defineInRange("tetraceratopsSpawnGroupMaximum", 2, 1, 100);

		this.thanosHeath = builder.comment("Sets the heath of the Thanos").translation(TRANSLATION_TEXT + "thanosHeath").defineInRange("thanosHeath", 35.0D, 1.0D, 999.0D);
		this.thanosAttackDamage = builder.comment("Sets the attack damage of the Thanos").translation(TRANSLATION_TEXT + "thanosAttackDamage").defineInRange("thanosAttackDamage", 14.0D, 1.0D, 999.0D);
		this.thanosSpawnWeight = builder.comment("Sets the weight of Thanos spawning").translation(TRANSLATION_TEXT + "thanosSpawnWeight").defineInRange("thanosSpawnWeight", 3, 1, 100);
		this.thanosSpawnGroupMinimum = builder.comment("Sets the minimum amount of Thanos in a spawn group").translation(TRANSLATION_TEXT + "thanosSpawnGroupMinimum").defineInRange("thanosSpawnGroupMinimum", 1, 1, 100);
		this.thanosSpawnGroupMaximum = builder.comment("Sets the maximum amount of Thanos in a spawn group").translation(TRANSLATION_TEXT + "thanosSpawnGroupMaximum").defineInRange("thanosSpawnGroupMaximum", 2, 1, 100);

		this.toyotamaphimeiaHeath = builder.comment("Sets the heath of the Toyotamaphimeia").translation(TRANSLATION_TEXT + "toyotamaphimeiaHeath").defineInRange("toyotamaphimeiaHeath", 6.0D, 1.0D, 999.0D);
		this.toyotamaphimeiaAttackDamage = builder.comment("Sets the attack damage of the Toyotamaphimeia").translation(TRANSLATION_TEXT + "toyotamaphimeiaAttackDamage").defineInRange("toyotamaphimeiaAttackDamage", 20.0D, 1.0D, 999.0D);
		this.toyotamaphimeiaSpawnWeight = builder.comment("Sets the weight of Toyotamaphimeia spawning").translation(TRANSLATION_TEXT + "toyotamaphimeiaSpawnWeight").defineInRange("toyotamaphimeiaSpawnWeight", 3, 1, 100);
		this.toyotamaphimeiaSpawnGroupMinimum = builder.comment("Sets the minimum amount of Toyotamaphimeia in a spawn group").translation(TRANSLATION_TEXT + "toyotamaphimeiaSpawnGroupMinimum").defineInRange("toyotamaphimeiaSpawnGroupMinimum", 1, 1, 100);
		this.toyotamaphimeiaSpawnGroupMaximum = builder.comment("Sets the maximum amount of Toyotamaphimeia in a spawn group").translation(TRANSLATION_TEXT + "toyotamaphimeiaSpawnGroupMaximum").defineInRange("toyotamaphimeiaSpawnGroupMaximum", 3, 1, 100);

		this.tyrannosaurusHeath = builder.comment("Sets the heath of the Tyrannosaurus").translation(TRANSLATION_TEXT + "tyrannosaurusHeath").defineInRange("tyrannosaurusHeath", 80.0D, 1.0D, 999.0D);
		this.tyrannosaurusAttackDamage = builder.comment("Sets the attack damage of the Tyrannosaurus").translation(TRANSLATION_TEXT + "tyrannosaurusAttackDamage").defineInRange("tyrannosaurusAttackDamage", 20.0D, 1.0D, 999.0D);
		this.tyrannosaurusSpawnWeight = builder.comment("Sets the weight of Tyrannosaurus spawning").translation(TRANSLATION_TEXT + "tyrannosaurusSpawnWeight").defineInRange("tyrannosaurusSpawnWeight", 1, 1, 100);
		this.tyrannosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Tyrannosaurus in a spawn group").translation(TRANSLATION_TEXT + "tyrannosaurusSpawnGroupMinimum").defineInRange("tyrannosaurusSpawnGroupMinimum", 1, 1, 100);
		this.tyrannosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Tyrannosaurus in a spawn group").translation(TRANSLATION_TEXT + "tyrannosaurusSpawnGroupMaximum").defineInRange("tyrannosaurusSpawnGroupMaximum", 1, 1, 100);

		this.utahraptorHeath = builder.comment("Sets the heath of the Utahraptor").translation(TRANSLATION_TEXT + "utahraptorHeath").defineInRange("utahraptorHeath", 35.0D, 1.0D, 999.0D);
		this.utahraptorAttackDamage = builder.comment("Sets the attack damage of the Utahraptor").translation(TRANSLATION_TEXT + "utahraptorAttackDamage").defineInRange("utahraptorAttackDamage", 10.0D, 1.0D, 999.0D);
		this.utahraptorSpawnWeight = builder.comment("Sets the weight of Utahraptor spawning").translation(TRANSLATION_TEXT + "utahraptorSpawnWeight").defineInRange("utahraptorSpawnWeight", 3, 1, 100);
		this.utahraptorSpawnGroupMinimum = builder.comment("Sets the minimum amount of Utahraptor in a spawn group").translation(TRANSLATION_TEXT + "utahraptorSpawnGroupMinimum").defineInRange("utahraptorSpawnGroupMinimum", 3, 1, 100);
		this.utahraptorSpawnGroupMaximum = builder.comment("Sets the maximum amount of Utahraptor in a spawn group").translation(TRANSLATION_TEXT + "utahraptorSpawnGroupMaximum").defineInRange("utahraptorSpawnGroupMaximum", 6, 1, 100);

		this.zephyrosaurusHeath = builder.comment("Sets the heath of the Zephyrosaurus").translation(TRANSLATION_TEXT + "zephyrosaurusHeath").defineInRange("zephyrosaurusHeath", 6.0D, 1.0D, 999.0D);
		this.zephyrosaurusSpawnWeight = builder.comment("Sets the weight of Zephyrosaurus spawning").translation(TRANSLATION_TEXT + "zephyrosaurusSpawnWeight").defineInRange("zephyrosaurusSpawnWeight", 3, 1, 100);
		this.zephyrosaurusSpawnGroupMinimum = builder.comment("Sets the minimum amount of Zephyrosaurus in a spawn group").translation(TRANSLATION_TEXT + "zephyrosaurusSpawnGroupMinimum").defineInRange("zephyrosaurusSpawnGroupMinimum", 5, 1, 100);
		this.zephyrosaurusSpawnGroupMaximum = builder.comment("Sets the maximum amount of Zephyrosaurus in a spawn group").translation(TRANSLATION_TEXT + "zephyrosaurusSpawnGroupMaximum").defineInRange("zephyrosaurusSpawnGroupMaximum", 11, 1, 100);
	}
}
