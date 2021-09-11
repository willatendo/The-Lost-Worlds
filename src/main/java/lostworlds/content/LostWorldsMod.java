package lostworlds.content;

import java.util.Arrays;
import java.util.List;

import lostworlds.addon.LostWorldsAddon;
import lostworlds.content.client.dimension.PermianDimensionRenderInfo;
import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.DimensionInit;
import lostworlds.content.server.init.EntityInit;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.PotionInit;
import lostworlds.content.server.init.StructurePieceInit;
import lostworlds.library.biome.BiomeGeneration;
import lostworlds.library.biome.ModConfiguredStructures;
import lostworlds.library.biome.OreGeneration;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.world.raid.Raid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(ModUtils.ID)
public class LostWorldsMod 
{			
	public LostWorldsMod() 
	{		
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;
				
		ModRegistry.register(bus);
		LostWorldsAddon.getAddons();
		
		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
				
		forgeBus.addListener(this::biomeModification);
		forgeBus.addListener(EventPriority.HIGH, OreGeneration::addOresToOverworld);
		forgeBus.addListener(EventPriority.HIGH, BiomeGeneration::addBiomesToOverworld);
		forgeBus.addListener(EventPriority.HIGH, BiomeGeneration::addFeaturesToOverworld);
		
		ModLoadingContext.get().registerConfig(Type.COMMON, LostWorldsConfig.commonSpec);
		
		GeckoLib.initialize();
	}
	
	private void commonSetup(FMLCommonSetupEvent event)
	{		
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)), Ingredient.of(BlockInit.VOLCANIC_ASH.asItem()), PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.ASHY_LUNG_POTION));
				
		ModItemGroup.ITEMS.setIcon(ItemInit.WET_PAPER.getDefaultInstance());
		ModItemGroup.BLOCKS.setIcon(BlockInit.ACCENT_LIGHT_CONCRETE.asItem().getDefaultInstance());
		
		event.enqueueWork(() -> 
		{
			StructurePieceInit.registerBiomeGeneration();
			ModConfiguredStructures.init();
			
			DimensionInit.initBiomeSourcesAndChunkGenerator();
		});	
		
		translateToWaves(EntityInit.FOSSIL_POACHER, Arrays.asList(1, 0, 0, 0, 1, 2, 2, 3));
	}
	
	private void clientSetup(FMLClientSetupEvent event) 
	{		
		DimensionRenderInfo baseRenderer = new PermianDimensionRenderInfo();
		
		DimensionRenderInfo.EFFECTS.put(ModUtils.rL("permian_render"), baseRenderer);
	}
	
	private void biomeModification(final BiomeLoadingEvent event) 
	{
		if(ModUtils.SIMPLE_SPAWNABLE_BIOME_CATEGORIES.contains(event.getCategory()))
		{
			event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_BLACK_MARKET);
		}
    }
	
	private void translateToWaves(EntityType<? extends AbstractRaiderEntity> type, List<? extends Integer> list) 
	{
		Raid.WaveMember.create(type.getRegistryName().toString(), type, new int[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7)});
	}
}
