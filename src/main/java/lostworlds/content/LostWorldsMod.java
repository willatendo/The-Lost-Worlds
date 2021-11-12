package lostworlds.content;

import java.util.Arrays;
import java.util.List;

import lostworlds.content.client.book.LostWorldsBooks;
import lostworlds.content.client.dimension.StandardDimensionRenderInfo;
import lostworlds.content.client.setup.ClientSetup;
import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.DimensionInit;
import lostworlds.content.server.init.EntityInit;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.KeyInit;
import lostworlds.content.server.init.PotionInit;
import lostworlds.content.server.init.StructurePieceInit;
import lostworlds.library.biome.BiomeGeneration;
import lostworlds.library.biome.DinosaurSpawn;
import lostworlds.library.biome.ModConfiguredStructures;
import lostworlds.library.biome.OreGeneration;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.world.raid.Raid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tyrannotitanlib.library.TyrannotitanMod;

@Mod(ModUtils.ID)
public class LostWorldsMod 
{
	public LostWorldsMod() 
	{		
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;
				
		TyrannotitanMod.init(ModUtils.ID);
		ModRegistry.register();
		
		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
				
		forgeBus.addListener(this::biomeModification);
		forgeBus.addListener(EventPriority.HIGH, OreGeneration::addOresToOverworld);
		forgeBus.addListener(EventPriority.HIGH, BiomeGeneration::addBiomesToOverworld);
		forgeBus.addListener(EventPriority.HIGH, BiomeGeneration::addFeaturesToOverworld);
		forgeBus.addListener(EventPriority.HIGH, DinosaurSpawn::addDinosaursToOverworld);
		
		ModLoadingContext.get().registerConfig(Type.CLIENT, LostWorldsConfig.clientSpec);
		ModLoadingContext.get().registerConfig(Type.COMMON, LostWorldsConfig.commonSpec);
	}
	
	private void commonSetup(FMLCommonSetupEvent event)
	{		
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)), Ingredient.of(BlockInit.VOLCANIC_ASH.asItem()), PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.ASHY_LUNG_POTION));
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.POISON)), Ingredient.of(Items.SUGAR), ItemInit.CONTRACEPTIVES.getDefaultInstance());
		
		ModUtils.ITEMS.setIcon(ItemInit.LOST_WORLDS_LEXICON.getDefaultInstance());
		ModUtils.BLOCKS.setIcon(BlockInit.PLASTERED_FOSSILIZED_TRACK.asItem().getDefaultInstance());
						
		event.enqueueWork(() -> 
		{
			StructurePieceInit.registerBiomeGeneration();
			ModConfiguredStructures.init();
			
			DimensionInit.initBiomeSourcesAndChunkGenerator();
		});	
		
		translateToWaves(EntityInit.FOSSIL_POACHER, Arrays.asList(1, 0, 0, 0, 1, 2, 2, 3));
	}
	
	@OnlyIn(Dist.CLIENT)
	private void clientSetup(FMLClientSetupEvent event) 
	{		
		DimensionRenderInfo baseRenderer = new StandardDimensionRenderInfo();
		
		DimensionRenderInfo.EFFECTS.put(ModUtils.rL("permian_render"), baseRenderer);
		DimensionRenderInfo.EFFECTS.put(ModUtils.rL("jurassic_render"), baseRenderer);
		DimensionRenderInfo.EFFECTS.put(ModUtils.rL("cretaceous_render"), baseRenderer);
		
		DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientSetup::setupOther);
		
		LostWorldsBooks.initBooks();
		
		if(LostWorldsConfig.COMMON_CONFIG.tameableDinos.get())
		{
			KeyInit.init();
		}
	}
	
	private void biomeModification(final BiomeLoadingEvent event) 
	{
		if(LostWorldsConfig.COMMON_CONFIG.blackMarketShouldSpawn.get())
		{
			if(ModUtils.SIMPLE_SPAWNABLE_BIOME_CATEGORIES.contains(event.getCategory()))
			{
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_BLACK_MARKET);
			}
		}
		
		if(LostWorldsConfig.COMMON_CONFIG.meteoriteShouldSpawn.get())
		{
			if(ModUtils.SIMPLE_SPAWNABLE_BIOME_CATEGORIES.contains(event.getCategory()))
			{
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_METEORITE);
			}
		}
	
		if(LostWorldsConfig.COMMON_CONFIG.fossilsInOverworld.get())
		{
			if(ModUtils.FOSSIL_BIOMES.contains(event.getCategory()))
			{
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_SURFACE_FOSSIL);
			}
			if(ModUtils.FOSSIL_BIOMES.contains(event.getCategory()))
			{
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_SUBTERRANEAN_FOSSIL);
			}
			if(ModUtils.FOSSIL_BIOMES.contains(event.getCategory()))
			{
				event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_TRACE_FOSSIL);
			}
		}
    }
	
	private void translateToWaves(EntityType<? extends AbstractRaiderEntity> type, List<? extends Integer> list) 
	{
		Raid.WaveMember.create(type.getRegistryName().toString(), type, new int[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7)});
	}
}
