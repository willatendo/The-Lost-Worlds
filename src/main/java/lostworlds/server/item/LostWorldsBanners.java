package lostworlds.server.item;

import java.util.ArrayList;
import java.util.Locale;

import org.apache.commons.compress.utils.Lists;

import net.minecraft.world.level.block.entity.BannerPattern;

public class LostWorldsBanners {
	public static final ArrayList<BannerPattern> BANNER_PATTERNS = Lists.newArrayList();
	public static final BannerPattern SCARAB = register("scarab");

	public static BannerPattern register(String id) {
		BannerPattern pattern = BannerPattern.create(id.toUpperCase(Locale.ROOT), id, id, false);
		BANNER_PATTERNS.add(pattern);
		return pattern;
	}

	public static void init() {
	}
}
