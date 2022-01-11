package lostworlds.content.config;

import org.apache.commons.lang3.tuple.Pair;

import lostworlds.content.client.ClientConfigs;
import lostworlds.content.server.ServerConfigs;
import net.minecraftforge.common.ForgeConfigSpec;

public class LostWorldsConfig 
{
	public static final ForgeConfigSpec serverSpec;
	public static final ServerConfigs SERVER_CONFIG;
	
	public static final ForgeConfigSpec clientSpec;
	public static final ClientConfigs CLIENT_CONFIG;
	
	static 
	{
		final Pair<ServerConfigs, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(ServerConfigs::new);
		serverSpec = serverSpecPair.getRight();
		SERVER_CONFIG = serverSpecPair.getLeft();

		final Pair<ClientConfigs, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(ClientConfigs::new);
		clientSpec = clientSpecPair.getRight();
		CLIENT_CONFIG = clientSpecPair.getLeft();
	}
}
