package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import net.minecraft.util.SoundEvent;

public class SoundInit 
{
	public static final SoundEvent MACHINE_WHIRLING = ModRegistry.register("machine_whirling", new SoundEvent(ModUtils.rL("block.sound.machine_whirling")));
	public static final SoundEvent POT_SMASH = ModRegistry.register("pot_smash", new SoundEvent(ModUtils.rL("block.sound.pot_smash")));
	
	public static final SoundEvent BIG_WALK = ModRegistry.register("big_walk", new SoundEvent(ModUtils.rL("entity.sound.big_walk")));
	public static final SoundEvent MEDIUM_WALK = ModRegistry.register("medium_walk", new SoundEvent(ModUtils.rL("entity.sound.medium_walk")));
	public static final SoundEvent SMALL_WALK = ModRegistry.register("small_walk", new SoundEvent(ModUtils.rL("entity.sound.small_walk")));
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Sounds"); }
}
