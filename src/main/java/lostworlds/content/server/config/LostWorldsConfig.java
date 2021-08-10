package lostworlds.content.server.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class LostWorldsConfig 
{
	public static final ForgeConfigSpec serverSpec;
	public static final ServerConfig SERVER_CONFIG;

	static 
	{
		final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
		serverSpec = specPair.getRight();
		SERVER_CONFIG = specPair.getLeft();
	}
}
