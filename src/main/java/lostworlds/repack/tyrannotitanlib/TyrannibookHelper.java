package lostworlds.repack.tyrannotitanlib;

import lostworlds.repack.tyrannotitanlib.tyrannibook.client.TyrannobookLoader;
import lostworlds.repack.tyrannotitanlib.tyranninetwork.Tyranninetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class TyrannibookHelper {
	// Use This
	public void commonSetup(FMLCommonSetupEvent event) {
		Tyranninetwork.registerPackets();
	}

	// Use This
	public static void listenersSetup(FMLClientSetupEvent event) {
		IResourceManager manager = Minecraft.getInstance().getResourceManager();
		if (manager instanceof IReloadableResourceManager) {
			((IReloadableResourceManager) manager).registerReloadListener(new TyrannobookLoader());
		}
	}
}
