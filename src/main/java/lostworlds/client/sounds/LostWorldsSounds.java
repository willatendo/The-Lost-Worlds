package lostworlds.client.sounds;

import static lostworlds.LostWorldsMod.getRegistrate;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsSounds {
	private static final LostWorldsRegistrate REGISTRATE = getRegistrate();
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LostWorldsUtils.ID);

	public static final RegistryObject<SoundEvent> MACHINE_WHIRLING = register("machine_whirling", "block");
	public static final RegistryObject<SoundEvent> POT_SMASH = register("pot_smash", "block");

	public static final RegistryObject<SoundEvent> BIG_WALK = register("big_walk", "entity");
	public static final RegistryObject<SoundEvent> MEDIUM_WALK = register("medium_walk", "entity");
	public static final RegistryObject<SoundEvent> SMALL_WALK = register("small_walk", "entity");

	public static final RegistryObject<SoundEvent> FOSSIL_POACHER_CELEBRATE = register("fossil_poacher.celebrate", "entity", "Fossil Poacher Celebrates");
	public static final RegistryObject<SoundEvent> FOSSIL_POACHER_DEATH = register("fossil_poacher.death", "entity", "Fossil Poacher Dies");
	public static final RegistryObject<SoundEvent> FOSSIL_POACHER_GRUNT = register("fossil_poacher.grunt", "entity", "Fossil Poacher Grunts");
	public static final RegistryObject<SoundEvent> FOSSIL_POACHER_HURT = register("fossil_poacher.hurt", "entity", "Fossil Poacher Hurts");

	public static RegistryObject<SoundEvent> register(String id, String type) {
		return register(id, type, Arrays.stream(id.toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")));
	}

	public static RegistryObject<SoundEvent> register(String id, String type, String subtile) {
		SoundEvent event = new SoundEvent(LostWorldsUtils.rL(type + ".sound." + id));
		REGISTRATE.subtitle(type + "." + id, subtile);
		return SOUND_EVENTS.register(id, () -> event);
	}

	public static void deferred(IEventBus bus) {
		SOUND_EVENTS.register(bus);
	}
}
