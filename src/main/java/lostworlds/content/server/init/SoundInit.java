package lostworlds.content.server.init;

import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtil;
import net.minecraft.util.SoundEvent;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class SoundInit 
{
	public static final SoundEvent MACHINE_WHIRLING = ModRegistry.register("machine_whirling", new SoundEvent(ModUtil.rL("block.sound.machine_whirling")));
	
	public static void init() { ModUtil.LOGGER.debug("Registering Mod Sounds"); }
}
