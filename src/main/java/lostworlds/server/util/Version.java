package lostworlds.server.util;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public enum Version {
	DEV,
	SNAPSHOT,
	YOUTUBE,
	PUBLIC;

	public static ITextComponent getMessage(Version version, String versionString) {
		if (version == DEV) {
			return LostWorldsUtils.cTCA("event", "load_dev_build", versionString, TextFormatting.DARK_RED, TextFormatting.BOLD);
		} else if (version == SNAPSHOT) {
			return LostWorldsUtils.cTCA("event", "load_snapshot_build", versionString, TextFormatting.AQUA);
		} else if (version == YOUTUBE) {
			return LostWorldsUtils.cTCA("event", "load_youtube_build", versionString, TextFormatting.RED);
		} else {
			return LostWorldsUtils.cTCA("event", "load", versionString, TextFormatting.GOLD, TextFormatting.ITALIC);
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

	public static String toStringVersion(String version) {
		return version.replace("_", "").replace("dev", "").replace("a", "Alpha ").replace("b", "Beta ");
	}
}
