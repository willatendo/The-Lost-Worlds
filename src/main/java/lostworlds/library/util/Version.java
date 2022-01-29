package lostworlds.library.util;

import lostworlds.content.ModUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public enum Version {
	DEV,
	SNAPSHOT,
	PUBLIC;

	public static ITextComponent getMessage(Version version) {
		if (version == DEV) {
			return ModUtils.cTC("event", "load_dev_build", TextFormatting.RED, TextFormatting.BOLD);
		} else if (version == SNAPSHOT) {
			return ModUtils.cTC("event", "load_snapshot_build", TextFormatting.AQUA);
		} else {
			return ModUtils.cTC("event", "load", TextFormatting.GOLD, TextFormatting.ITALIC);
		}
	}

	public static Version getVersion(String version) {
		if (version.contains("dev")) {
			return DEV;
		} else if (version.contains("snapshot")) {
			return SNAPSHOT;
		} else {
			return PUBLIC;
		}
	}
}
