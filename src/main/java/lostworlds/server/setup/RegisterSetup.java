package lostworlds.server.setup;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.menu.recipes.LostWorldsRecipeTypes;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
public class RegisterSetup {
	@SubscribeEvent
	public static void registerRecipeTypes(Register<RecipeSerializer<?>> event) {
		LostWorldsRecipeTypes.init();
	}
}
