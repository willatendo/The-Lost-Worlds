package lostworlds.client.books;

import lostworlds.client.books.tyrannibook.client.TyrannobookLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class TyrannibookHelper {
	public static void listenersSetup(FMLClientSetupEvent event) {
		IResourceManager manager = Minecraft.getInstance().getResourceManager();
		if (manager instanceof IReloadableResourceManager) {
			((IReloadableResourceManager) manager).registerReloadListener(new TyrannobookLoader());
		}
	}
}
