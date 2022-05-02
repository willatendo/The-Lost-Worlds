package lostworlds.client.sounds;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsSounds {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LostWorldsUtils.ID);

	public static final RegistryObject<SoundEvent> MACHINE_WHIRLING = register("machine_whirling", "block");
	public static final RegistryObject<SoundEvent> POT_SMASH = register("pot_smash", "block");

	public static final RegistryObject<SoundEvent> BIG_WALK = register("big_walk", "entity");
	public static final RegistryObject<SoundEvent> MEDIUM_WALK = register("medium_walk", "entity");
	public static final RegistryObject<SoundEvent> SMALL_WALK = register("small_walk", "entity");

	public static RegistryObject<SoundEvent> register(String id, String type) {
		return SOUND_EVENTS.register(id, () -> new SoundEvent(LostWorldsUtils.rL(type + ".sound." + id)));
	}

	public static void deferred(IEventBus bus) {
		SOUND_EVENTS.register(bus);
	}
}
