package lostworlds.server;

import org.apache.commons.lang3.tuple.Pair;

import lostworlds.client.LostWorldsClientConfigs;
import net.minecraftforge.common.ForgeConfigSpec;

public class LostWorldsConfig {
	public static final ForgeConfigSpec commonSpec;
	public static final LostWorldsCommonConfig COMMON_CONFIG;

	public static final ForgeConfigSpec clientSpec;
	public static final LostWorldsClientConfigs CLIENT_CONFIG;

	static {
		final Pair<LostWorldsCommonConfig, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(LostWorldsCommonConfig::new);
		commonSpec = serverSpecPair.getRight();
		COMMON_CONFIG = serverSpecPair.getLeft();

		final Pair<LostWorldsClientConfigs, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(LostWorldsClientConfigs::new);
		clientSpec = clientSpecPair.getRight();
		CLIENT_CONFIG = clientSpecPair.getLeft();
	}
}
