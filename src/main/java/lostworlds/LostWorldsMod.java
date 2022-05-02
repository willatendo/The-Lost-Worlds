package lostworlds;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.tterrag.registrate.util.NonNullLazyValue;

import lostworlds.client.LostWorldsConfig;
import lostworlds.client.books.TyrannibookHelper;
import lostworlds.client.books.lostworlds.LostWorldsBooks;
import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.BiomeKeys;
import lostworlds.server.biome.DisksFeatures;
import lostworlds.server.biome.LostWorldsBiomes;
import lostworlds.server.biome.LostWorldsBlockstateProviders;
import lostworlds.server.biome.ModConfiguredFeatures;
import lostworlds.server.biome.ModConfiguredStructures;
import lostworlds.server.biome.OreFeatures;
import lostworlds.server.biome.TreeFeatures;
import lostworlds.server.biome.surfacebuilders.LostWorldsSurfaceBuilders;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.entity.LostWorldsBlockEntities;
import lostworlds.server.container.LostWorldsContainers;
import lostworlds.server.container.recipes.LostWorldsRecipes;
import lostworlds.server.dimension.LostWorldsDimensions;
import lostworlds.server.dimension.carver.LostWorldsConfiguredCarvers;
import lostworlds.server.dimension.carver.LostWorldsWorldCarvers;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.LostWorldsPOIs;
import lostworlds.server.entity.LostWorldsVillagerProfessions;
import lostworlds.server.feature.FeatureAdder;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.item.LostWorldsBanners;
import lostworlds.server.item.LostWorldsEnchantments;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.item.LostWorldsPotions;
import lostworlds.server.placement.LostWorldsPlacements;
import lostworlds.server.structure.LostWorldsStructurePecies;
import lostworlds.server.structure.LostWorldsStructures;
import lostworlds.server.util.LostWorldsRegistrate;
import lostworlds.server.util.Version;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.ItemHandlerHelper;
import software.bernie.geckolib3.GeckoLib;

@Mod(LostWorldsMod.ID)
public class LostWorldsMod {
	public static final String ID = "lostworlds";
	private static final NonNullLazyValue<LostWorldsRegistrate> REGISTRATE = LostWorldsRegistrate.lazy(ID);

	public LostWorldsMod() {
		this.makeMod();
	}

	public void makeMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forge = MinecraftForge.EVENT_BUS;

		GeckoLib.initialize();

		LostWorldsBlocks.init();
		LostWorldsItems.init();
		LostWorldsTags.init();
		LostWorldsPotions.init(bus);
		LostWorldsSounds.init(bus);
		LostWorldsEnchantments.init();
		LostWorldsEntities.init();
		LostWorldsBanners.init();
		LostWorldsBlockEntities.init();
		LostWorldsContainers.init();
		LostWorldsRecipes.init();
		LostWorldsVillagerProfessions.init(bus);
		LostWorldsPOIs.init(bus);
		LostWorldsBlockstateProviders.BLOCK_STATE_PROVIDER.register(bus);
		ModConfiguredFeatures.init();
		LostWorldsWorldCarvers.init();
		LostWorldsConfiguredCarvers.init();
		LostWorldsSurfaceBuilders.init();
		LostWorldsFeatures.init(bus);
		LostWorldsStructures.init();
		LostWorldsStructurePecies.init();
		LostWorldsPlacements.init();
		LostWorldsBiomes.init();

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);

		forge.addListener(this::biomeModification);
		forge.addListener(this::onPlayerLoggedIn);
		forge.addListener(this::onLivingTick);
		forge.addListener(this::onPlayerHarvest);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> this.clientForgeBus(forge));

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LostWorldsConfig.commonSpec);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LostWorldsConfig.clientSpec);
	}

	@OnlyIn(Dist.CLIENT)
	private void clientForgeBus(IEventBus bus) {
		bus.addListener(this::onClientPlayerLoggedInEvent);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.POISON)), Ingredient.of(Items.SUGAR), LostWorldsItems.CONTRACEPTIVES.get().getDefaultInstance());

		LostWorldsUtils.ITEMS.setIcon(() -> LostWorldsItems.LOST_WORLDS_LEXICON.asStack());
		LostWorldsUtils.BLOCKS.setIcon(() -> LostWorldsBlocks.PLASTERED_FOSSILIZED_TRACK.asStack());

		TyrannibookHelper.commonSetup(event);

		event.enqueueWork(() -> {
			LostWorldsStructurePecies.registerBiomeGeneration();
			ModConfiguredStructures.init();

			LostWorldsDimensions.initBiomeSourcesAndChunkGenerator();

			ImmutableSet.Builder<Block> builder = ImmutableSet.builder();
			builder.addAll(TileEntityType.SIGN.validBlocks);
			LostWorldsBlocks.forEachSignBlock(builder::add);
			TileEntityType.SIGN.validBlocks = builder.build();
		});

		LostWorldsUtils.translateToWaves(LostWorldsEntities.FOSSIL_POACHER.get(), Arrays.asList(1, 0, 0, 0, 1, 2, 2, 3));
	}

	private void biomeModification(BiomeLoadingEvent event) {
		// Biomes
		if (LostWorldsUtils.SERVER_CONFIG.araucariaForestShouldSpawn.get()) {
			if (event.getName().equals(LostWorldsBiomes.ARAUCARIA_FOREST.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.ARAUCARIA_FOREST, LostWorldsUtils.SERVER_CONFIG.araucariaForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.ARAUCARIA_FOREST, Type.FOREST, Type.CONIFEROUS);
			}

			if (event.getName().equals(LostWorldsBiomes.ARAUCARIA_FOREST_HILLS.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.ARAUCARIA_FOREST_HILLS, LostWorldsUtils.SERVER_CONFIG.araucariaForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.ARAUCARIA_FOREST_HILLS, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.coniferForestShouldSpawn.get()) {
			if (event.getName().equals(LostWorldsBiomes.CONIFER_FOREST.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.CONIFER_FOREST, LostWorldsUtils.SERVER_CONFIG.coniferForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.CONIFER_FOREST, Type.FOREST, Type.CONIFEROUS);
			}

			if (event.getName().equals(LostWorldsBiomes.CONIFER_FOREST_HILLS.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.CONIFER_FOREST_HILLS, LostWorldsUtils.SERVER_CONFIG.coniferForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.CONIFER_FOREST_HILLS, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.ginkgoForestShouldSpawn.get()) {
			if (event.getName().equals(LostWorldsBiomes.GINKGO_FOREST.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BiomeKeys.GINKGO_FOREST, LostWorldsUtils.SERVER_CONFIG.ginkgoForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.GINKGO_FOREST, Type.FOREST, Type.DENSE);
			}

			if (event.getName().equals(LostWorldsBiomes.GINKGO_FOREST_HILLS.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BiomeKeys.GINKGO_FOREST_HILLS, LostWorldsUtils.SERVER_CONFIG.ginkgoForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.GINKGO_FOREST_HILLS, Type.FOREST, Type.DENSE, Type.HILLS);
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.sequoiaForestShouldSpawn.get()) {
			if (event.getName().equals(LostWorldsBiomes.REDWOODS_FOREST.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.REDWOODS_FOREST, LostWorldsUtils.SERVER_CONFIG.sequoiaForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.REDWOODS_FOREST, Type.FOREST, Type.COLD, Type.CONIFEROUS);
			}

			if (event.getName().equals(LostWorldsBiomes.REDWOODS_FOREST_HILLS.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.REDWOODS_FOREST_HILLS, LostWorldsUtils.SERVER_CONFIG.sequoiaForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.REDWOODS_FOREST_HILLS, Type.FOREST, Type.COLD, Type.CONIFEROUS);
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.volcanoShouldSpawn.get()) {
			if (event.getName().equals(LostWorldsBiomes.VOLCANO.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.VOLCANO, LostWorldsUtils.SERVER_CONFIG.volcanoWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.VOLCANO, Type.MOUNTAIN, Type.HOT);
			}
		}

		// Spawns
		if (LostWorldsUtils.SERVER_CONFIG.livingFossils.get()) {
			List<? extends String> nautilusBiomes = Lists.newArrayList("minecraft:warm_ocean");
			if (nautilusBiomes.contains(event.getName().toString())) {
				FeatureAdder.addSpawn(event, EntityClassification.WATER_CREATURE, LostWorldsEntities.NAUTILUS.get(), LostWorldsUtils.SERVER_CONFIG.nautilusSpawnWeight.get(), LostWorldsUtils.SERVER_CONFIG.nautilusSpawnGroupMinimum.get(), LostWorldsUtils.SERVER_CONFIG.nautilusSpawnGroupMaximum.get());
			}
		}

		// Features
		if (LostWorldsUtils.SERVER_CONFIG.amberOre.get()) {
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_AMBER_ORE.get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.plantFossilsInOverworld.get()) {
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_PLANT_FOSSIL_ALETHOPTERIS.get());
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_PLANT_FOSSIL_BRAZILEA.get());
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_PLANT_FOSSIL_CALAMITES_SUCKOWII.get());
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_PLANT_FOSSIL_CEPHALOTAXUS.get());
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_PLANT_FOSSIL_DILLHOFFIA.get());
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_PLANT_FOSSIL_DUISBERGIA.get());
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_PLANT_FOSSIL_OSMUNDA.get());
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_PLANT_FOSSIL_WILLIAMSONIA.get());
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_PLANT_FOSSIL_ZAMITES.get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.siltPatchGeneration.get()) {
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.SILT_PATCH.get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.mudDisksInSwamps.get()) {
			List<? extends String> biomes = Lists.newArrayList("minecraft:swamp", "minecraft:swamp_hills");

			if (biomes.contains(event.getName().toString())) {
				FeatureAdder.addFeature(event, GenerationStage.Decoration.TOP_LAYER_MODIFICATION, DisksFeatures.MUD_DISK.get());
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.cypressTreesInSwamps.get()) {
			List<? extends String> biomes = Lists.newArrayList("minecraft:swamp", "minecraft:swamp_hills");

			if (biomes.contains(event.getName().toString())) {
				FeatureAdder.addFeature(event, GenerationStage.Decoration.TOP_LAYER_MODIFICATION, TreeFeatures.SCANT_CYPRESS_TREES.get());
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedAraucariaTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.PETRIFIED_ARAUCARIA.get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedCalamitesTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.PETRIFIED_CALAMITES.get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedConiferTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.PETRIFIED_CONIFER.get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedCypressTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.PETRIFIED_CYPRESS.get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedGinkgoTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.PETRIFIED_GINKGO.get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.petrifiedSequoiaTreeShouldSpawn.get()) {
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.PETRIFIED_SEQUOIA.get());
		}

		if (LostWorldsUtils.SERVER_CONFIG.fossilsInOverworld.get()) {
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_TINY_NEST.get());
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_SMALL_NEST.get());
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_MEDIUM_NEST.get());
			FeatureAdder.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, OreFeatures.OVERWORLD_LARGE_NEST.get());
		}

		// Structures
		if (LostWorldsUtils.SERVER_CONFIG.blackMarketShouldSpawn.get()) {
			if (LostWorldsUtils.SIMPLE_SPAWNABLE_BIOME_CATEGORIES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_BLACK_MARKET);
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.meteoriteShouldSpawn.get()) {
			if (LostWorldsUtils.SIMPLE_SPAWNABLE_BIOME_CATEGORIES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_METEORITE);
			}
		}

		if (LostWorldsUtils.SERVER_CONFIG.fossilsInOverworld.get()) {
			if (LostWorldsUtils.FOSSIL_BIOMES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_SURFACE_FOSSIL);
			}
			if (LostWorldsUtils.FOSSIL_BIOMES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_SUBTERRANEAN_FOSSIL);
			}
			if (LostWorldsUtils.FOSSIL_BIOMES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_TRACE_FOSSIL);
			}
		}
	}

	private void clientSetup(FMLClientSetupEvent event) {
		TyrannibookHelper.listenersSetup(event);

		LostWorldsBooks.initBooks();

		LostWorldsDimensions.initClient();
	}

	@OnlyIn(Dist.CLIENT)
	private void onClientPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		player.sendMessage(Version.getMessage(LostWorldsUtils.VERSION_PARSER, Version.toStringVersion(LostWorldsUtils.VERSION)), player.getUUID());
	}

	private void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		CompoundNBT playerData = event.getPlayer().getPersistentData();
		CompoundNBT data = playerData.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
		if (data != null && !data.getBoolean("has_lexicon")) {
			ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), LostWorldsItems.LOST_WORLDS_LEXICON.asStack());
			data.putBoolean("has_lexicon", true);
			playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
		}
	}

	public void onLivingTick(LivingEvent.LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();

		if (entity != null) {
			World world = entity.level;
			BlockPos pos = entity.blockPosition();
			if (world.getBlockState(pos).is(LostWorldsBlocks.VOLCANIC_ASH_LAYER.get())) {
				if (!isWearingMask(entity, EquipmentSlotType.HEAD)) {
					entity.addEffect(new EffectInstance(LostWorldsPotions.ASHY_LUNG_EFFECT.get(), 200));
				}
			}
		}
	}

	public void onPlayerHarvest(PlayerEvent.BreakSpeed event) {
		PlayerEntity entity = event.getPlayer();

		if (entity != null) {
			if (event.getState().is(LostWorldsBlocks.VOLCANIC_ASH_LAYER.get())) {
				if (!isWearingMask(entity, EquipmentSlotType.HEAD)) {
					entity.addEffect(new EffectInstance(LostWorldsPotions.ASHY_LUNG_EFFECT.get(), 200));
				}
			}
		}
	}

	public static boolean isWearingMask(LivingEntity living, EquipmentSlotType pieceValue) {
		List<Item> mask = ImmutableList.of(LostWorldsItems.CLOTH_MASK.get());
		return mask.contains(living.getItemBySlot(pieceValue).getItem());
	}

	public static LostWorldsRegistrate getRegistrate() {
		return REGISTRATE.get();
	}
}
