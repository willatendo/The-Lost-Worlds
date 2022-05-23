package lostworlds;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import lostworlds.client.LostWorldsConfig;
import lostworlds.client.books.TyrannibookHelper;
import lostworlds.client.books.lostworlds.LostWorldsBooks;
import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomes;
import lostworlds.server.biome.LostWorldsBlockstateProviders;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.entity.LostWorldsBlockEntities;
import lostworlds.server.dimension.LostWorldsDimensions;
import lostworlds.server.dimension.carver.LostWorldsConfiguredCarvers;
import lostworlds.server.dimension.carver.LostWorldsWorldCarvers;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.LostWorldsPOIs;
import lostworlds.server.entity.LostWorldsVillagerProfessions;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.impl.ImplInit;
import lostworlds.server.item.LostWorldsBanners;
import lostworlds.server.item.LostWorldsEnchantments;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.item.LostWorldsPotions;
import lostworlds.server.menu.LostWorldsMenus;
import lostworlds.server.menu.recipes.LostWorldsRecipes;
import lostworlds.server.structure.LostWorldsConfiguredStructures;
import lostworlds.server.structure.LostWorldsStructurePecies;
import lostworlds.server.structure.LostWorldsStructures;
import lostworlds.server.util.Version;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import lostworlds.server.world.EntitySpawns;
import lostworlds.server.world.FeatureGen;
import lostworlds.terrablender.TerrablenderLoader;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
	private static final NonNullSupplier<LostWorldsRegistrate> REGISTRATE = LostWorldsRegistrate.lazy(ID);

	public LostWorldsMod() {
		this.makeMod();
	}

	public void makeMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forge = MinecraftForge.EVENT_BUS;

		GeckoLib.initialize();

		ImplInit.init();

		LostWorldsBlocks.registrate();
		LostWorldsItems.registrate();
		LostWorldsPotions.deferred(bus);
		LostWorldsSounds.deferred(bus);
		LostWorldsEnchantments.registrate();
		LostWorldsEntities.registrate();
		LostWorldsBanners.init();
		LostWorldsBlockEntities.registrate();
		LostWorldsMenus.registrate();
		LostWorldsRecipes.deferred(bus);
		LostWorldsVillagerProfessions.deferred(bus);
		LostWorldsPOIs.deferred(bus);
		LostWorldsBlockstateProviders.deferred(bus);
		LostWorldsWorldCarvers.deferred(bus);
		LostWorldsConfiguredCarvers.init();
//		LostWorldsSurfaceBuilders.deferred(bus);
		LostWorldsFeatures.deferred(bus);
		LostWorldsStructures.deferred(bus);
		LostWorldsStructurePecies.init();
		LostWorldsBiomes.deferred(bus);

		LostWorldsTags.init();

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);

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

		LostWorldsUtils.ITEMS.setIcon(() -> LostWorldsItems.LOST_WORLDS_LEXICON.asStack());
		LostWorldsUtils.BLOCKS.setIcon(() -> LostWorldsBlocks.PLASTERED_FOSSILIZED_TRACK.asStack());

		TyrannibookHelper.commonSetup(event);

		event.enqueueWork(() -> {
			if (LostWorldsUtils.modLoaded("terrablender")) {
				TerrablenderLoader.init();
			}

			LostWorldsConfiguredStructures.init();

			LostWorldsDimensions.initBiomeSourcesAndChunkGenerator();

			ImmutableSet.Builder<Block> builder = ImmutableSet.builder();
			builder.addAll(BlockEntityType.SIGN.validBlocks);
			LostWorldsBlocks.forEachSignBlock(builder::add);
			BlockEntityType.SIGN.validBlocks = builder.build();
		});

		LostWorldsUtils.translateToWaves(LostWorldsEntities.FOSSIL_POACHER.get(), Arrays.asList(1, 0, 0, 0, 1, 2, 2, 3));
	}

	private void biomeStuff(BiomeLoadingEvent event) {
		// Spawns
		EntitySpawns.init(event);

		// Features
		FeatureGen.init(event);
	}

	private void clientSetup(FMLClientSetupEvent event) {
		TyrannibookHelper.listenersSetup(event);

		LostWorldsBooks.initBooks();

		LostWorldsDimensions.initClient();
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

	public void onLivingTick(LivingEvent.LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();

		if (entity != null) {
			Level world = entity.level;
			BlockPos pos = entity.blockPosition();
			if (world.getBlockState(pos).is(LostWorldsBlocks.VOLCANIC_ASH_LAYER.get())) {
				if (!isWearingMask(entity, EquipmentSlot.HEAD)) {
					entity.addEffect(new MobEffectInstance(LostWorldsPotions.ASHY_LUNG_EFFECT.get(), 200));
				}
			}
		}
	}

	public void onPlayerHarvest(PlayerEvent.BreakSpeed event) {
		Player entity = event.getPlayer();

		if (entity != null) {
			if (event.getState().is(LostWorldsBlocks.VOLCANIC_ASH_LAYER.get())) {
				if (!isWearingMask(entity, EquipmentSlot.HEAD)) {
					entity.addEffect(new MobEffectInstance(LostWorldsPotions.ASHY_LUNG_EFFECT.get(), 200));
				}
			}
		}
	}

	public static boolean isWearingMask(LivingEntity living, EquipmentSlot pieceValue) {
		List<Item> mask = ImmutableList.of(LostWorldsItems.CLOTH_MASK.get());
		return mask.contains(living.getItemBySlot(pieceValue).getItem());
	}

	public static LostWorldsRegistrate getRegistrate() {
		return REGISTRATE.get();
	}
}
