package lostworlds;

import com.tterrag.registrate.util.nullness.NonNullSupplier;

import lostworlds.client.LostWorldsConfig;
import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.LostWorldsRegistries;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomes;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.entity.LostWorldsBlockEntities;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.LostWorldsPOIs;
import lostworlds.server.entity.LostWorldsVillagerProfessions;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.impl.ImplInit;
import lostworlds.server.item.LostWorldsBanners;
import lostworlds.server.item.LostWorldsEnchantments;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.item.LostWorldsMobEffects;
import lostworlds.server.loot.LostWorldsGlobalLootModifers;
import lostworlds.server.menu.LostWorldsMenus;
import lostworlds.server.menu.recipes.LostWorldsRecipeSerializers;
import lostworlds.server.species.LostWorldsSpeciesTypes;
import lostworlds.server.structure.LostWorldsStructures;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(LostWorldsUtils.ID)
public class LostWorldsMod {
	private static final NonNullSupplier<LostWorldsRegistrate> REGISTRATE = LostWorldsRegistrate.lazy(LostWorldsUtils.ID);

	public LostWorldsMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		LostWorldsRegistries.DEFERRED_SPECIES_TYPES.register(bus);
		LostWorldsRegistries.init();

		GeckoLib.initialize();
		ImplInit.init();
		LostWorldsBlocks.registrate();
		LostWorldsItems.registrate();
		LostWorldsGlobalLootModifers.LOOT_MODIFIER_SERIALIZERS.register(bus);
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
		LostWorldsSpeciesTypes.SPECIES_TYPES.register(bus);
		LostWorldsTags.init();

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LostWorldsConfig.commonSpec);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LostWorldsConfig.clientSpec);
	}

	public static LostWorldsRegistrate getRegistrate() {
		return REGISTRATE.get();
	}
}
