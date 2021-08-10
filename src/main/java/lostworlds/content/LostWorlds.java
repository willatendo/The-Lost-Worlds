package lostworlds.content;

import lostworlds.addon.LostWorldsAddon;
import lostworlds.content.server.config.LostWorldsConfig;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.DimensionInit;
import lostworlds.content.server.init.PotionInit;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtil;
import net.minecraft.client.world.DimensionRenderInfo;
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
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(ModUtil.ID)
public class LostWorlds 
{
	public LostWorlds() 
	{
		ModUtil.LOGGER.debug("Starting: Lost Worlds Registration");
		
		//Objects
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModRegistry.register(bus);
		LostWorldsAddon.getModPlugins();
		
		//Config
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LostWorldsConfig.serverSpec);
		
		//Setup
		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		
		//v3.0.30	
		GeckoLib.initialize();
		
		ModUtil.LOGGER.debug("Finished: Lost Worlds Registration");
	}
	
	private void commonSetup(final FMLCommonSetupEvent event)
	{
		ModUtil.LOGGER.debug("Loading: Mod Potion Recipes");
		
		//Recipes
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)), Ingredient.of(BlockInit.VOLCANIC_ASH.asItem()), PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.ASHY_LUNG_POTION));

		ModUtil.LOGGER.debug("Finished: Mod Potion Recipes");
		
		event.enqueueWork(() -> 
		{
			ModUtil.LOGGER.debug("Loading: Making Dimension Pieces");

			DimensionInit.init();

			ModUtil.LOGGER.debug("Finished: Making Dimension Pieces");
		});	
	}
	
	private void clientSetup(FMLClientSetupEvent event) 
	{
		ModUtil.LOGGER.debug("Loading: Dimension Renders");
		
		//Sky Render
		DimensionRenderInfo permian = new DimensionRenderInfo.Overworld();
		DimensionRenderInfo.EFFECTS.put(ModUtil.rL("permian_render"), permian);
		
		ModUtil.LOGGER.debug("Finished: Dimension Renders");
	}
}
