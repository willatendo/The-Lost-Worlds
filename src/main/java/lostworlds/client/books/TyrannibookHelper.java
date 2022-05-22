package lostworlds.client.books;

import lostworlds.client.books.tyrannibook.client.TyrannobookLoader;
import lostworlds.client.books.tyranninetwork.Tyranninetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class TyrannibookHelper {
	public static void commonSetup(FMLCommonSetupEvent event) {
		Tyranninetwork.registerPackets();
	}

	public static void listenersSetup(FMLClientSetupEvent event) {
		ResourceManager manager = Minecraft.getInstance().getResourceManager();
		if (manager instanceof ReloadableResourceManager) {
			((ReloadableResourceManager) manager).registerReloadListener(new TyrannobookLoader());
		}
	}
}
