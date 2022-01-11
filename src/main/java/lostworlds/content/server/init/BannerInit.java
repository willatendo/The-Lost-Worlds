package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import net.minecraft.tileentity.BannerPattern;

public class BannerInit {
	public static final BannerPattern SCARAB = ModRegistry.createPattern("scarab");

	public static void init() {
		ModUtils.LOGGER.debug("Registering Mod Banner Patterns");
	}
}
