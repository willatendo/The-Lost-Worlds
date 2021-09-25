package lostworlds.content.config;

import org.apache.commons.lang3.tuple.Pair;

import lostworlds.content.client.ClientConfig;
import lostworlds.content.server.ServerConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class LostWorldsConfig 
{
	public static final ForgeConfigSpec commonSpec;
	public static final ServerConfig COMMON_CONFIG;
	
	public static final ForgeConfigSpec clientSpec;
	public static final ClientConfig CLIENT_CONFIG;
	
	static 
	{
		final Pair<ServerConfig, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
		commonSpec = commonSpecPair.getRight();
		COMMON_CONFIG = commonSpecPair.getLeft();

		final Pair<ClientConfig, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
		clientSpec = clientSpecPair.getRight();
		CLIENT_CONFIG = clientSpecPair.getLeft();
	}
}
