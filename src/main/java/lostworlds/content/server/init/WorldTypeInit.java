package lostworlds.content.server.init;

import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import lostworlds.library.worldtype.RiftWorldType;
import net.minecraftforge.common.world.ForgeWorldType;

public class WorldTypeInit 
{
	public static final ForgeWorldType RIFT = ModRegistry.register("rift", new RiftWorldType());
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod World Types"); }
}
