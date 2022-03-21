package lostworlds.client.sounds;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.util.SoundEvent;

public class LostWorldsSounds {
	public static final SoundEvent ASCENTED = LostWorldsRegistry.register("ascented", new SoundEvent(LostWorldsUtils.rL("disc.sound.ascented")));

	public static final SoundEvent MACHINE_WHIRLING = LostWorldsRegistry.register("machine_whirling", new SoundEvent(LostWorldsUtils.rL("block.sound.machine_whirling")));
	public static final SoundEvent POT_SMASH = LostWorldsRegistry.register("pot_smash", new SoundEvent(LostWorldsUtils.rL("block.sound.pot_smash")));

	public static final SoundEvent BIG_WALK = LostWorldsRegistry.register("big_walk", new SoundEvent(LostWorldsUtils.rL("entity.sound.big_walk")));
	public static final SoundEvent MEDIUM_WALK = LostWorldsRegistry.register("medium_walk", new SoundEvent(LostWorldsUtils.rL("entity.sound.medium_walk")));
	public static final SoundEvent SMALL_WALK = LostWorldsRegistry.register("small_walk", new SoundEvent(LostWorldsUtils.rL("entity.sound.small_walk")));

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Sounds");
	}
}
