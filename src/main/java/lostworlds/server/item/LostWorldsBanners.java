package lostworlds.server.item;

import java.util.Locale;

import net.minecraft.tileentity.BannerPattern;

public class LostWorldsBanners {
	public static final BannerPattern SCARAB = register("scarab");

	public static BannerPattern register(String id) {
		return BannerPattern.create(id.toUpperCase(Locale.ROOT), id, id, false);
	}

	public static void init() {
	}
}
