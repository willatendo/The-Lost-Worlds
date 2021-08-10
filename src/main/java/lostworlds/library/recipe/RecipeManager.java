package lostworlds.library.recipe;

import java.util.ArrayList;
import java.util.List;

import lostworlds.library.util.ModUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/*
 * Author: Willatendo
 * Date: July 1, 2021
 */

@Mod.EventBusSubscriber(bus = Bus.MOD, modid = ModUtil.ID)
public class RecipeManager 
{
    public static List<FossilCleanerRecipe> cleanerRecipes = new ArrayList<>();

    public static void initAlternateRecipes()
	{
		FossilCleanerRecipe fossil = new FossilCleanerRecipe()
				.addOutput(() -> Items.SAND)
				.addOutput(() -> Items.GRAVEL)
				/*
				.addOutput(() -> ItemInit.ALLOSAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.ALLOSAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.ALLOSAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.ALLOSAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.ALLOSAURUS_SKULL.get())
				.addOutput(() -> ItemInit.CARNOTAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.CARNOTAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.CARNOTAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.CARNOTAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.CARNOTAURUS_SKULL.get())
				.addOutput(() -> ItemInit.CHILESAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.CHILESAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.CHILESAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.CHILESAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.CHILESAURUS_SKULL.get())
				.addOutput(() -> ItemInit.CRYOLOPHOSAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.CRYOLOPHOSAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.CRYOLOPHOSAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.CRYOLOPHOSAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.CRYOLOPHOSAURUS_SKULL.get())
				.addOutput(() -> ItemInit.DIICTODON_ARM_BONE.get())
				.addOutput(() -> ItemInit.DIICTODON_FOOT_BONE.get())
				.addOutput(() -> ItemInit.DIICTODON_LEG_BONE.get())
				.addOutput(() -> ItemInit.DIICTODON_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.DIICTODON_SKULL.get())
				.addOutput(() -> ItemInit.DIMETRODON_ARM_BONE.get())
				.addOutput(() -> ItemInit.DIMETRODON_FOOT_BONE.get())
				.addOutput(() -> ItemInit.DIMETRODON_LEG_BONE.get())
				.addOutput(() -> ItemInit.DIMETRODON_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.DIMETRODON_SKULL.get())
				.addOutput(() -> ItemInit.DIMETRODON_SAIL.get())
				.addOutput(() -> ItemInit.EDAPHOSAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.EDAPHOSAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.EDAPHOSAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.EDAPHOSAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.EDAPHOSAURUS_SKULL.get())
				.addOutput(() -> ItemInit.EDAPHOSAURUS_SAIL.get())
				.addOutput(() -> ItemInit.FUKUIVENATOR_ARM_BONE.get())
				.addOutput(() -> ItemInit.FUKUIVENATOR_FOOT_BONE.get())
				.addOutput(() -> ItemInit.FUKUIVENATOR_LEG_BONE.get())
				.addOutput(() -> ItemInit.FUKUIVENATOR_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.FUKUIVENATOR_SKULL.get())
				.addOutput(() -> ItemInit.FUKUIVENATOR_CLAW.get())
				.addOutput(() -> ItemInit.GIGANOTOSAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.GIGANOTOSAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.GIGANOTOSAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.GIGANOTOSAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.GIGANOTOSAURUS_SKULL.get())
				.addOutput(() -> ItemInit.GORGONOPS_ARM_BONE.get())
				.addOutput(() -> ItemInit.GORGONOPS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.GORGONOPS_LEG_BONE.get())
				.addOutput(() -> ItemInit.GORGONOPS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.GORGONOPS_SKULL.get())
				.addOutput(() -> ItemInit.GREAT_AUK_ARM_BONE.get())
				.addOutput(() -> ItemInit.GREAT_AUK_FOOT_BONE.get())
				.addOutput(() -> ItemInit.GREAT_AUK_LEG_BONE.get())
				.addOutput(() -> ItemInit.GREAT_AUK_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.GREAT_AUK_SKULL.get())
				.addOutput(() -> ItemInit.GREAT_AUK_FEATHER.get())
				.addOutput(() -> ItemInit.KENTROSAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.KENTROSAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.KENTROSAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.KENTROSAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.KENTROSAURUS_SKULL.get())
				.addOutput(() -> ItemInit.KENTROSAURUS_PLATE.get())
				.addOutput(() -> ItemInit.LIAONINGOSAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.LIAONINGOSAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.LIAONINGOSAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.LIAONINGOSAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.LIAONINGOSAURUS_SKULL.get())
				.addOutput(() -> ItemInit.LIAONINGOSAURUS_SCUTE.get())
				.addOutput(() -> Items.NAUTILUS_SHELL)
				.addOutput(() -> ItemInit.OSTROMIA_ARM_BONE.get())
				.addOutput(() -> ItemInit.OSTROMIA_FOOT_BONE.get())
				.addOutput(() -> ItemInit.OSTROMIA_LEG_BONE.get())
				.addOutput(() -> ItemInit.OSTROMIA_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.OSTROMIA_SKULL.get())
				.addOutput(() -> ItemInit.OSTROMIA_FEATHER.get())
				.addOutput(() -> ItemInit.OURANOSAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.OURANOSAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.OURANOSAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.OURANOSAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.OURANOSAURUS_SKULL.get())
				.addOutput(() -> ItemInit.OURANOSAURUS_SAIL.get())
				.addOutput(() -> ItemInit.PALAEONISCUM_FIN_BONE.get())
				.addOutput(() -> ItemInit.PALAEONISCUM_SKULL.get())
				.addOutput(() -> ItemInit.PALAEONISCUM_RIB_BONE.get())
				.addOutput(() -> ItemInit.PROCOMPSOGNATHUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.PROCOMPSOGNATHUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.PROCOMPSOGNATHUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.PROCOMPSOGNATHUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.PROCOMPSOGNATHUS_SKULL.get())
				.addOutput(() -> ItemInit.PROTOSUCHUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.PROTOSUCHUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.PROTOSUCHUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.PROTOSUCHUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.PROTOSUCHUS_SKULL.get())
				.addOutput(() -> ItemInit.PSITTACOSAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.PSITTACOSAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.PSITTACOSAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.PSITTACOSAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.PSITTACOSAURUS_SKULL.get())
				.addOutput(() -> ItemInit.RHINESUCHUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.RHINESUCHUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.RHINESUCHUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.RHINESUCHUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.RHINESUCHUS_SKULL.get())
				.addOutput(() -> ItemInit.SUCHOMIMUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.SUCHOMIMUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.SUCHOMIMUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.SUCHOMIMUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.SUCHOMIMUS_SKULL.get())
				.addOutput(() -> ItemInit.TETRACERATOPS_ARM_BONE.get())
				.addOutput(() -> ItemInit.TETRACERATOPS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.TETRACERATOPS_LEG_BONE.get())
				.addOutput(() -> ItemInit.TETRACERATOPS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.TETRACERATOPS_SKULL.get())
				.addOutput(() -> ItemInit.TYRANNOSAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.TYRANNOSAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.TYRANNOSAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.TYRANNOSAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.TYRANNOSAURUS_SKULL.get())
				.addOutput(() -> ItemInit.UTAHRAPTOR_ARM_BONE.get())
				.addOutput(() -> ItemInit.UTAHRAPTOR_FOOT_BONE.get())
				.addOutput(() -> ItemInit.UTAHRAPTOR_LEG_BONE.get())
				.addOutput(() -> ItemInit.UTAHRAPTOR_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.UTAHRAPTOR_SKULL.get())
				.addOutput(() -> ItemInit.UTAHRAPTOR_FEATHER.get())
				.addOutput(() -> ItemInit.UTAHRAPTOR_CLAW.get())
				.addOutput(() -> ItemInit.ZEPHYROSAURUS_ARM_BONE.get())
				.addOutput(() -> ItemInit.ZEPHYROSAURUS_FOOT_BONE.get())
				.addOutput(() -> ItemInit.ZEPHYROSAURUS_LEG_BONE.get())
				.addOutput(() -> ItemInit.ZEPHYROSAURUS_RIB_BONE.get()) 
				.addOutput(() -> ItemInit.ZEPHYROSAURUS_SKULL.get())
				.addOutput(() -> ItemInit.ZEPHYROSAURUS_FEATHER.get())
				*/;
		
		registerCleaner(fossil);
	}
	
	public static void registerCleaner(FossilCleanerRecipe recipe) 
	{
		cleanerRecipes.add(recipe);
	}
	
	public static FossilCleanerRecipe getAnalyzerRecipeForItem(ItemStack stack) 
	{
		for(FossilCleanerRecipe recipe : cleanerRecipes) 
		{
			if(ItemStack.matches(recipe.getInput(), stack))
			{
				return recipe;
			}
		}
		return null;
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) 
	{
		ModUtil.LOGGER.debug("Loading: Mod Recipes That Aren't Recipes");
		
		initAlternateRecipes();
		
		ModUtil.LOGGER.debug("Finished: Mod Recipes That Aren't Recipes");		
	}
}
