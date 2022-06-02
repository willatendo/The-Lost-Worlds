package lostworlds;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import lostworlds.client.LostWorldsBooks;
import lostworlds.client.LostWorldsConfig;
import lostworlds.client.book.BookLoader;
import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomes;
import lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures;
import lostworlds.server.biome.features.placed.LostWorldsPlacedFeatures;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.entity.LostWorldsBlockEntities;
import lostworlds.server.dimension.LostWorldsDimensionRenderInfo;
import lostworlds.server.dimension.LostWorldsNoise;
import lostworlds.server.dimension.LostWorldsNoiseGeneratorSettings;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.LostWorldsPOIs;
import lostworlds.server.entity.LostWorldsVillagerProfessions;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.impl.ImplInit;
import lostworlds.server.item.LostWorldsBanners;
import lostworlds.server.item.LostWorldsEnchantments;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.item.LostWorldsMobEffects;
import lostworlds.server.menu.LostWorldsMenus;
import lostworlds.server.menu.recipes.LostWorldsRecipeSerializers;
import lostworlds.server.menu.recipes.LostWorldsRecipeTypes;
import lostworlds.server.network.LostWorldsNetwork;
import lostworlds.server.structure.LostWorldsConfiguredStructures;
import lostworlds.server.structure.LostWorldsStructurePecies;
import lostworlds.server.structure.LostWorldsStructureSets;
import lostworlds.server.structure.LostWorldsStructures;
import lostworlds.server.util.Version;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import lostworlds.server.world.FeatureGen;
import lostworlds.server.world.terrablender.LostWorldsTerrablender;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent.Register;
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
import terrablender.core.TerraBlender;

@Mod(LostWorldsUtils.ID)
public class LostWorldsMod {
	private static final NonNullSupplier<LostWorldsRegistrate> REGISTRATE = LostWorldsRegistrate.lazy(LostWorldsUtils.ID);

	public LostWorldsMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forge = MinecraftForge.EVENT_BUS;

		GeckoLib.initialize();

		ImplInit.init();

		LostWorldsBlocks.registrate();
		LostWorldsItems.registrate();
		LostWorldsMobEffects.MOB_EFFECTS.register(bus);
		LostWorldsSounds.SOUND_EVENTS.register(bus);
		LostWorldsEnchantments.registrate();
		LostWorldsEntities.registrate();
		LostWorldsBanners.init();
		LostWorldsBlockEntities.registrate();
		LostWorldsMenus.registrate();
		LostWorldsRecipeSerializers.RECIPE_SERIALIZERS.register(bus);
		LostWorldsVillagerProfessions.VILLAGER_PROFESSIONS.register(bus);
		LostWorldsPOIs.POI_TYPES.register(bus);
		LostWorldsFeatures.FEATURES.register(bus);
		LostWorldsStructures.STRUCTURE_FEATURES.register(bus);
		LostWorldsBiomes.BIOMES.register(bus);

		LostWorldsTags.init();

		forge.register(this);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::listenersSetup);

		bus.addGenericListener(RecipeSerializer.class, LostWorldsMod::registerRecipeTypes);

		forge.addListener(this::biomeStuff);
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

		event.enqueueWork(() -> {
			LostWorldsNetwork.registerPackets();

			if (LostWorldsUtils.modLoaded(TerraBlender.MOD_ID)) {
				LostWorldsTerrablender.init();
			}

			LostWorldsConfiguredFeatures.init();
			LostWorldsPlacedFeatures.init();

			LostWorldsStructurePecies.init();
			LostWorldsStructureSets.init();
			LostWorldsConfiguredStructures.init();

			LostWorldsNoiseGeneratorSettings.init();
			LostWorldsNoise.init();

			ImmutableSet.Builder<Block> builder = ImmutableSet.builder();
			builder.addAll(BlockEntityType.SIGN.validBlocks);
			LostWorldsBlocks.forEachSignBlock(builder::add);
			BlockEntityType.SIGN.validBlocks = builder.build();
		});

		LostWorldsUtils.translateToWaves(LostWorldsEntities.FOSSIL_POACHER.get(), Arrays.asList(1, 0, 0, 0, 1, 2, 2, 3));
	}

	private static void registerRecipeTypes(Register<RecipeSerializer<?>> event) {
		LostWorldsRecipeTypes.init();
	}

	private void biomeStuff(BiomeLoadingEvent event) {
//		EntitySpawns.init(event);

		FeatureGen.init(event);
	}

	private void clientSetup(FMLClientSetupEvent event) {
		LostWorldsDimensionRenderInfo.initClient();
		LostWorldsBooks.initBooks();
	}

	@OnlyIn(Dist.CLIENT)
	private void onClientPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(Version.getMessage(LostWorldsUtils.VERSION_PARSER, Version.toStringVersion(LostWorldsUtils.VERSION)), player.getUUID());
	}

	private void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		CompoundTag playerData = event.getPlayer().getPersistentData();
		CompoundTag data = playerData.getCompound(Player.PERSISTED_NBT_TAG);
		if (data != null && !data.getBoolean("has_lexicon")) {
			ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), LostWorldsItems.LOST_WORLDS_LEXICON.asStack());
			data.putBoolean("has_lexicon", true);
			playerData.put(Player.PERSISTED_NBT_TAG, data);
		}
	}

	private void listenersSetup(RegisterClientReloadListenersEvent event) {
		event.registerReloadListener(new BookLoader());
	}

	public void onLivingTick(LivingEvent.LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();

		if (entity != null) {
			Level world = entity.level;
			BlockPos pos = entity.blockPosition();
			if (world.getBlockState(pos).is(LostWorldsBlocks.VOLCANIC_ASH_LAYER.get())) {
				if (!isWearingMask(entity, EquipmentSlot.HEAD)) {
					entity.addEffect(new MobEffectInstance(LostWorldsMobEffects.ASHY_LUNG_EFFECT.get(), 200));
				}
			}
		}
	}

	public void onPlayerHarvest(PlayerEvent.BreakSpeed event) {
		Player entity = event.getPlayer();

		if (entity != null) {
			if (event.getState().is(LostWorldsBlocks.VOLCANIC_ASH_LAYER.get())) {
				if (!isWearingMask(entity, EquipmentSlot.HEAD)) {
					entity.addEffect(new MobEffectInstance(LostWorldsMobEffects.ASHY_LUNG_EFFECT.get(), 200));
				}
			}
		}
	}

	public static boolean isWearingMask(LivingEntity entity, EquipmentSlot slot) {
		List<Item> mask = ImmutableList.of(LostWorldsItems.CLOTH_MASK.get());
		return mask.contains(entity.getItemBySlot(slot).getItem());
	}

	public static LostWorldsRegistrate getRegistrate() {
		return REGISTRATE.get();
	}
}
