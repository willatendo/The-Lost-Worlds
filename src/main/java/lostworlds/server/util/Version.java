package lostworlds.server.util;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

public enum Version {
	DEV,
	SNAPSHOT,
	YOUTUBE,
	PUBLIC;

	public static Component getMessage(Version version, String versionString) {
		if (version == DEV) {
			return LostWorldsUtils.cTCA("event", "load_dev_build", versionString, ChatFormatting.DARK_RED, ChatFormatting.BOLD);
		} else if (version == SNAPSHOT) {
			return LostWorldsUtils.cTCA("event", "load_snapshot_build", versionString, ChatFormatting.AQUA);
		} else if (version == YOUTUBE) {
			return LostWorldsUtils.cTCA("event", "load_youtube_build", versionString, ChatFormatting.RED);
		} else {
			return LostWorldsUtils.cTCA("event", "load", versionString, ChatFormatting.GOLD, ChatFormatting.ITALIC);
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
