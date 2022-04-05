package lostworlds.server.item;

import java.util.Locale;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.tileentity.BannerPattern;

public class LostWorldsBanners {
	public static final BannerPattern SCARAB = register("scarab");

	public static BannerPattern register(String id) {
		return BannerPattern.create(id.toUpperCase(Locale.ROOT), id, id, false);
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Banner Patterns");
	}
}
