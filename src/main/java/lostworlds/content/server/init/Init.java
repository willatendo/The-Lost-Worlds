package lostworlds.content.server.init;

import lostworlds.library.util.ModUtil;
import lostworlds.library.worldtype.RiftWorldType;
import net.minecraftforge.registries.ForgeRegistries;

public class Init 
{
	public static RiftWorldType rift = new RiftWorldType();
	
	public static void setup()
	{
		rift.setRegistryName(ModUtil.rL("rift_world_type"));
		ForgeRegistries.WORLD_TYPES.register(rift);
	}
}
