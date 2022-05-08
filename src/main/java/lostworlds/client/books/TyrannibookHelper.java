package lostworlds.client.books;

import lostworlds.client.books.tyrannibook.client.TyrannobookLoader;
import lostworlds.client.books.tyranninetwork.Tyranninetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class TyrannibookHelper {
	public static void commonSetup(FMLCommonSetupEvent event) {
		Tyranninetwork.registerPackets();
	}

	public static void listenersSetup(FMLClientSetupEvent event) {
		IResourceManager manager = Minecraft.getInstance().getResourceManager();
		if (manager instanceof IReloadableResourceManager) {
			((IReloadableResourceManager) manager).registerReloadListener(new TyrannobookLoader());
		}
	}
}
