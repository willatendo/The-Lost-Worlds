package lostworlds.client.sounds;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsSounds {
	public static final SoundEvent ASCENTED = register("ascented", new SoundEvent(LostWorldsUtils.rL("disc.sound.ascented")));

	public static final SoundEvent MACHINE_WHIRLING = register("machine_whirling", new SoundEvent(LostWorldsUtils.rL("block.sound.machine_whirling")));
	public static final SoundEvent POT_SMASH = register("pot_smash", new SoundEvent(LostWorldsUtils.rL("block.sound.pot_smash")));

	public static final SoundEvent BIG_WALK = register("big_walk", new SoundEvent(LostWorldsUtils.rL("entity.sound.big_walk")));
	public static final SoundEvent MEDIUM_WALK = register("medium_walk", new SoundEvent(LostWorldsUtils.rL("entity.sound.medium_walk")));
	public static final SoundEvent SMALL_WALK = register("small_walk", new SoundEvent(LostWorldsUtils.rL("entity.sound.small_walk")));

	public static SoundEvent register(String id, SoundEvent sound) {
		sound.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.SOUND_EVENTS.register(sound);
		return sound;
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Sounds");
	}
}
