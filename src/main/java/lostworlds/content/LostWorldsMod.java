package lostworlds.content;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import lostworlds.content.client.book.LostWorldsBooks;
import lostworlds.content.client.setup.ClientSetup;
import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.BannerInit;
import lostworlds.content.server.init.BiomeInit;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ContainerInit;
import lostworlds.content.server.init.DimensionInit;
import lostworlds.content.server.init.EnchantmentInit;
import lostworlds.content.server.init.EntityInit;
import lostworlds.content.server.init.FeatureInit;
import lostworlds.content.server.init.FoliagePlacerInit;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.PlacementInit;
import lostworlds.content.server.init.PointOfInterestInit;
import lostworlds.content.server.init.PotionInit;
import lostworlds.content.server.init.RecipeInit;
import lostworlds.content.server.init.SoundInit;
import lostworlds.content.server.init.StructureInit;
import lostworlds.content.server.init.StructurePieceInit;
import lostworlds.content.server.init.SurfaceBuilderInit;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.content.server.init.VillagerProfessionInit;
import lostworlds.content.server.init.WorldCarverInit;
import lostworlds.content.server.init.WorldTypeInit;
import lostworlds.library.biome.BiomeKeys;
import lostworlds.library.biome.ModConfiguredCarvers;
import lostworlds.library.biome.ModConfiguredFeatures;
import lostworlds.library.biome.ModConfiguredStructures;
import lostworlds.library.util.Version;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.Category;
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
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.items.ItemHandlerHelper;
import tyrannotitanlib.library.base.biome.generation.TyrannoWorld;

//Main Class
@Mod(ModUtils.ID)
public class LostWorldsMod {
	public LostWorldsMod() {
		this.modBus();
		this.forgeBus();
		this.clientForgeBus();

		lostWorldRegistry();

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LostWorldsConfig.clientSpec);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, LostWorldsConfig.serverSpec);
	}

	private void modBus() {
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
	}

	private void forgeBus() {
		final IEventBus bus = MinecraftForge.EVENT_BUS;

		bus.addListener(this::biomeModification);
//		bus.addListener(EventPriority.HIGH, DinosaurSpawn::addDinosaursToOverworld);
		bus.addListener(this::onPlayerLoggedIn);
		bus.addListener(this::onLivingTick);
		bus.addListener(this::onPlayerHarvest);
	}

	@OnlyIn(Dist.CLIENT)
	private void clientForgeBus() {
		final IEventBus bus = MinecraftForge.EVENT_BUS;

		bus.addListener(this::onClientPlayerLoggedInEvent);
	}

	private static void lostWorldRegistry() {
		ModRegistry.register();

		// Game Objects
		BlockInit.init();
		ItemInit.init();
		PotionInit.init();
		SoundInit.init();
		EnchantmentInit.init();
		EntityInit.init();
		BannerInit.init();
		TileEntityInit.init();
		ContainerInit.init();
		RecipeInit.init();

		// Villagers
		VillagerProfessionInit.init();
		PointOfInterestInit.init();

		// World Generation
		WorldCarverInit.init();
		ModConfiguredCarvers.init();
		SurfaceBuilderInit.init();
		FeatureInit.init();
		PlacementInit.init();
		StructureInit.init();
		StructurePieceInit.init();
		FoliagePlacerInit.init();

		// Data Driven
		BiomeInit.init();

		// Forge
		if (!FMLEnvironment.production) {
			WorldTypeInit.init();
		}
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)), Ingredient.of(BlockInit.VOLCANIC_ASH.asItem()), PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.ASHY_LUNG_POTION));
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.POISON)), Ingredient.of(Items.SUGAR), ItemInit.CONTRACEPTIVES.getDefaultInstance());

		ModUtils.ITEMS.setIcon(ItemInit.LOST_WORLDS_LEXICON.getDefaultInstance());
		ModUtils.BLOCKS.setIcon(BlockInit.PLASTERED_FOSSILIZED_TRACK.asItem().getDefaultInstance());

		event.enqueueWork(() -> {
			StructurePieceInit.registerBiomeGeneration();
			ModConfiguredStructures.init();

			DimensionInit.initBiomeSourcesAndChunkGenerator();
		});

		ModUtils.translateToWaves(EntityInit.FOSSIL_POACHER, Arrays.asList(1, 0, 0, 0, 1, 2, 2, 3));
	}

	private void biomeModification(BiomeLoadingEvent event) {
		// Biomes
		if (ModUtils.SERVER_CONFIG.araucariaForestShouldSpawn.get()) {
			if (event.getName().equals(BiomeInit.ARAUCARIA_FOREST.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.ARAUCARIA_FOREST, ModUtils.SERVER_CONFIG.araucariaForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.ARAUCARIA_FOREST, Type.FOREST, Type.CONIFEROUS);
			}

			if (event.getName().equals(BiomeInit.ARAUCARIA_FOREST_HILLS.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.ARAUCARIA_FOREST_HILLS, ModUtils.SERVER_CONFIG.araucariaForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.ARAUCARIA_FOREST_HILLS, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
			}
		}

		if (ModUtils.SERVER_CONFIG.coniferForestShouldSpawn.get()) {
			if (event.getName().equals(BiomeInit.CONIFER_FOREST.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.CONIFER_FOREST, ModUtils.SERVER_CONFIG.coniferForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.CONIFER_FOREST, Type.FOREST, Type.CONIFEROUS);
			}

			if (event.getName().equals(BiomeInit.CONIFER_FOREST_HILLS.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.CONIFER_FOREST_HILLS, ModUtils.SERVER_CONFIG.coniferForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.CONIFER_FOREST_HILLS, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
			}
		}

		if (ModUtils.SERVER_CONFIG.ginkgoForestShouldSpawn.get()) {
			if (event.getName().equals(BiomeInit.GINKGO_FOREST.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BiomeKeys.GINKGO_FOREST, ModUtils.SERVER_CONFIG.ginkgoForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.GINKGO_FOREST, Type.FOREST, Type.DENSE);
			}

			if (event.getName().equals(BiomeInit.GINKGO_FOREST_HILLS.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BiomeKeys.GINKGO_FOREST_HILLS, ModUtils.SERVER_CONFIG.ginkgoForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.GINKGO_FOREST_HILLS, Type.FOREST, Type.DENSE, Type.HILLS);
			}
		}

		if (ModUtils.SERVER_CONFIG.sequoiaForestShouldSpawn.get()) {
			if (event.getName().equals(BiomeInit.REDWOODS_FOREST.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.REDWOODS_FOREST, ModUtils.SERVER_CONFIG.sequoiaForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.REDWOODS_FOREST, Type.FOREST, Type.COLD, Type.CONIFEROUS);
			}

			if (event.getName().equals(BiomeInit.REDWOODS_FOREST_HILLS.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.REDWOODS_FOREST_HILLS, ModUtils.SERVER_CONFIG.sequoiaForestWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.REDWOODS_FOREST_HILLS, Type.FOREST, Type.COLD, Type.CONIFEROUS);
			}
		}

		if (ModUtils.SERVER_CONFIG.volcanoShouldSpawn.get()) {
			if (event.getName().equals(BiomeInit.VOLCANO.getRegistryName())) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeKeys.VOLCANO, ModUtils.SERVER_CONFIG.volcanoWeight.get()));
				BiomeDictionary.addTypes(BiomeKeys.VOLCANO, Type.MOUNTAIN, Type.HOT);
			}
		}

		// Spawns
		if (ModUtils.SERVER_CONFIG.livingFossils.get()) {
			List<? extends String> nautilusBiomes = Lists.newArrayList("minecraft:warm_ocean");
			if (nautilusBiomes.contains(event.getName().toString())) {
				TyrannoWorld.addSpawn(event, EntityClassification.WATER_CREATURE, EntityInit.NAUTILUS, ModUtils.SERVER_CONFIG.nautilusSpawnWeight.get(), ModUtils.SERVER_CONFIG.nautilusSpawnGroupMinimum.get(), ModUtils.SERVER_CONFIG.nautilusSpawnGroupMaximum.get());
			}
		}

		// Features
		if (ModUtils.SERVER_CONFIG.amberOre.get()) {
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_AMBER_ORE);
		}

		if (ModUtils.SERVER_CONFIG.plantFossilsInOverworld.get()) {
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_ALETHOPTERIS);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_BRAZILEA);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_CALAMITES_SUCKOWII);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_CEPHALOTAXUS);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_DILLHOFFIA);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_DUISBERGIA);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_OSMUNDA);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_WILLIAMSONIA);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_PLANT_FOSSIL_ZAMITES);
		}

		if (ModUtils.SERVER_CONFIG.siltPatchGeneration.get()) {
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.SILT_PATCH);
		}

		if (ModUtils.SERVER_CONFIG.mudDisksInSwamps.get()) {
			if (event.getCategory() == Category.SWAMP) {
				TyrannoWorld.addFeature(event, GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.MUD_DISK);
			}
		}

		if (ModUtils.SERVER_CONFIG.cypressTreesInSwamps.get()) {
			List<? extends String> biomes = Lists.newArrayList("minecraft:swamp", "minecraft:swamp_hills");

			if (biomes.contains(event.getName().toString())) {
				TyrannoWorld.addFeature(event, GenerationStage.Decoration.TOP_LAYER_MODIFICATION, ModConfiguredFeatures.SCANT_CYPRESS_TREES);
			}
		}

		if (ModUtils.SERVER_CONFIG.petrifiedAraucariaTreeShouldSpawn.get()) {
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PETRIFIED_ARAUCARIA);
		}

		if (ModUtils.SERVER_CONFIG.petrifiedCalamitesTreeShouldSpawn.get()) {
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PETRIFIED_CALAMITES);
		}

		if (ModUtils.SERVER_CONFIG.petrifiedConiferTreeShouldSpawn.get()) {
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PETRIFIED_CONIFER);
		}

		if (ModUtils.SERVER_CONFIG.petrifiedGinkgoTreeShouldSpawn.get()) {
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.PETRIFIED_GINKGO);
		}

		if (ModUtils.SERVER_CONFIG.fossilsInOverworld.get()) {
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_TINY_NEST);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_SMALL_NEST);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_MEDIUM_NEST);
			TyrannoWorld.addFeature(event, GenerationStage.Decoration.UNDERGROUND_ORES, ModConfiguredFeatures.OVERWORLD_LARGE_NEST);
		}

		// Structures
		if (ModUtils.SERVER_CONFIG.blackMarketShouldSpawn.get()) {
			if (ModUtils.SIMPLE_SPAWNABLE_BIOME_CATEGORIES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_BLACK_MARKET);
			}
		}

		if (ModUtils.SERVER_CONFIG.meteoriteShouldSpawn.get()) {
			if (ModUtils.SIMPLE_SPAWNABLE_BIOME_CATEGORIES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_METEORITE);
			}
		}

		if (ModUtils.SERVER_CONFIG.fossilsInOverworld.get()) {
			if (ModUtils.FOSSIL_BIOMES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_SURFACE_FOSSIL);
			}
			if (ModUtils.FOSSIL_BIOMES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_SUBTERRANEAN_FOSSIL);
			}
			if (ModUtils.FOSSIL_BIOMES.contains(event.getCategory())) {
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_TRACE_FOSSIL);
			}
		}
	}

	private void clientSetup(FMLClientSetupEvent event) {
		LostWorldsBooks.initBooks();

		DimensionInit.initClient();

		ClientSetup.blockColourSetup();
		ClientSetup.renderSetup();
		ClientSetup.screenSetup();
		ClientSetup.entityRenderSetup();

		DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientSetup::setupOther);
	}

	@OnlyIn(Dist.CLIENT)
	private void onClientPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		player.sendMessage(Version.getMessage(ModUtils.VERSION_PARSER), player.getUUID());
	}

	private void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		CompoundNBT playerData = event.getPlayer().getPersistentData();
		CompoundNBT data = playerData.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
		if (data != null && !data.getBoolean("has_lexicon")) {
			ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), new ItemStack(ItemInit.LOST_WORLDS_LEXICON));
			data.putBoolean("has_lexicon", true);
			playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
		}
	}

	public void onLivingTick(LivingEvent.LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();

		if (entity != null) {
			World world = entity.level;
			BlockPos pos = entity.blockPosition();
			if (world.getBlockState(pos).getBlock() == BlockInit.VOLCANIC_ASH_LAYER) {
				if (!isWearingMask(entity, EquipmentSlotType.HEAD)) {
					entity.addEffect(new EffectInstance(PotionInit.ASHY_LUNG_EFFECT, 200));
				}
			}
		}
	}

	public void onPlayerHarvest(PlayerEvent.BreakSpeed event) {
		PlayerEntity entity = event.getPlayer();

		if (entity != null) {
			if (event.getState() == BlockInit.VOLCANIC_ASH_LAYER.defaultBlockState()) {
				if (!isWearingMask(entity, EquipmentSlotType.HEAD)) {
					entity.addEffect(new EffectInstance(PotionInit.ASHY_LUNG_EFFECT, 200));
				}
			}
		}
	}

	public static boolean isWearingMask(LivingEntity living, EquipmentSlotType pieceValue) {
		List<Item> mask = ImmutableList.of(ItemInit.CLOTH_MASK);
		return mask.contains(living.getItemBySlot(pieceValue).getItem());
	}
}
