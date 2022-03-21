package lostworlds.server.item;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.tileentity.BannerPattern;

public class LostWorldsBanners {
	public static final BannerPattern SCARAB = LostWorldsRegistry.createPattern("scarab");

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Banner Patterns");
	}
}
