package lostworlds.content;

import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.DimensionInit;
import lostworlds.content.server.init.PotionInit;
import lostworlds.integration.quark.util.QuarkRegistry;
import lostworlds.integration.quark.util.QuarkUtils;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.geckolib3.GeckoLib;

@Mod(ModUtils.ID)
public class LostWorlds 
{
	public LostWorlds() 
	{
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		ModRegistry.register(bus);
		
		if(ModList.get().isLoaded(QuarkUtils.QUARK_ID) || !FMLEnvironment.production)
		{
			QuarkRegistry.register();
		}
		
		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
				
		GeckoLib.initialize();
	}
	
	private void commonSetup(final FMLCommonSetupEvent event)
	{		
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)), Ingredient.of(BlockInit.VOLCANIC_ASH.asItem()), PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.ASHY_LUNG_POTION));
		
		event.enqueueWork(() -> 
		{
			DimensionInit.initBiomeSourcesAndChunkGenerator();
		});	
	}
	
	private void clientSetup(FMLClientSetupEvent event) 
	{		
		DimensionRenderInfo baseRenderer = new DimensionRenderInfo.Overworld();
		
		DimensionRenderInfo.EFFECTS.put(ModUtils.rL("permian_render"), baseRenderer);
	}
}
