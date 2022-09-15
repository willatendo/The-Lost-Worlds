package lostworlds;

import java.util.Arrays;

import com.tterrag.registrate.util.NonNullLazyValue;

import lostworlds.client.books.tyranninetwork.Tyranninetwork;
import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.LostWorldsConfig;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomes;
import lostworlds.server.biome.LostWorldsBlockstateProviders;
import lostworlds.server.biome.ModConfiguredStructures;
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
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.impl.ImplInit;
import lostworlds.server.item.LostWorldsBanners;
import lostworlds.server.item.LostWorldsEffects;
import lostworlds.server.item.LostWorldsEnchantments;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.loot.LostWorldsGlobalLootModifers;
import lostworlds.server.placement.LostWorldsPlacements;
import lostworlds.server.structure.LostWorldsStructurePecies;
import lostworlds.server.structure.LostWorldsStructures;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(LostWorldsUtils.ID)
public class LostWorldsMod {
	private static final NonNullLazyValue<LostWorldsRegistrate> REGISTRATE = LostWorldsRegistrate.lazy(LostWorldsUtils.ID);

	public LostWorldsMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		GeckoLib.initialize();

		ImplInit.init();

		LostWorldsBlocks.registrate();
		LostWorldsItems.registrate();
		LostWorldsEffects.deferred(bus);
		LostWorldsSounds.deferred(bus);
		LostWorldsEnchantments.registrate();
		LostWorldsEntities.registrate();
		LostWorldsBanners.init();
		LostWorldsBlockEntities.registrate();
		LostWorldsContainers.registrate();
		LostWorldsRecipes.deferred(bus);
		LostWorldsVillagerProfessions.deferred(bus);
		LostWorldsPOIs.deferred(bus);
		LostWorldsBlockstateProviders.deferred(bus);
		LostWorldsWorldCarvers.deferred(bus);
		LostWorldsConfiguredCarvers.init();
		LostWorldsSurfaceBuilders.deferred(bus);
		LostWorldsFeatures.deferred(bus);
		LostWorldsStructures.deferred(bus);
		LostWorldsStructurePecies.init();
		LostWorldsPlacements.deferred(bus);
		LostWorldsBiomes.deferred(bus);
		LostWorldsGlobalLootModifers.deferred(bus);

		LostWorldsTags.init();

		bus.addListener(this::commonSetup);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LostWorldsConfig.commonSpec);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LostWorldsConfig.clientSpec);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.POISON)), Ingredient.of(Items.SUGAR), LostWorldsItems.CONTRACEPTIVES.get().getDefaultInstance());

		Tyranninetwork.registerPackets();

		event.enqueueWork(() -> {
			LostWorldsStructurePecies.registerBiomeGeneration();
			ModConfiguredStructures.init();

			LostWorldsDimensions.initBiomeSourcesAndChunkGenerator();
		});

		LostWorldsUtils.translateToWaves(LostWorldsEntities.FOSSIL_POACHER.get(), Arrays.asList(1, 0, 0, 0, 1, 2, 2, 3));
	}

	public static LostWorldsRegistrate getRegistrate() {
		return REGISTRATE.get();
	}
}
