package lostworlds.client;

import org.apache.commons.lang3.tuple.Pair;

import lostworlds.server.LostWorldsCommonConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class LostWorldsConfig 
{
	public static final ForgeConfigSpec serverSpec;
	public static final LostWorldsCommonConfig SERVER_CONFIG;
	
	public static final ForgeConfigSpec clientSpec;
	public static final ClientConfigs CLIENT_CONFIG;
	
	static 
	{
		final Pair<LostWorldsCommonConfig, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(LostWorldsCommonConfig::new);
		serverSpec = serverSpecPair.getRight();
		SERVER_CONFIG = serverSpecPair.getLeft();

		final Pair<ClientConfigs, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(ClientConfigs::new);
		clientSpec = clientSpecPair.getRight();
		CLIENT_CONFIG = clientSpecPair.getLeft();
	}
}
