package lostworlds.content.server.init;

import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.util.SoundEvent;

public class SoundInit 
{
	public static final SoundEvent MACHINE_WHIRLING = ModRegistry.register("machine_whirling", new SoundEvent(ModUtils.rL("block.sound.machine_whirling")));
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Sounds"); }
}
