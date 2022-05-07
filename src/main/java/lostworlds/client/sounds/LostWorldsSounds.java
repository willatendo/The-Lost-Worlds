package lostworlds.client.sounds;

import static lostworlds.LostWorldsMod.getRegistrate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsSounds {
	private static final LostWorldsRegistrate REGISTRATE = getRegistrate();
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LostWorldsUtils.ID);
	public static final ArrayList<Pair<SoundEvent, String>> SOUNDS = Lists.newArrayList();

	public static final RegistryObject<SoundEvent> MACHINE_WHIRLING = register("machine_whirling", "block");
	public static final RegistryObject<SoundEvent> POT_SMASH = register("pot_smash", "block");

	public static final RegistryObject<SoundEvent> BIG_WALK = register("big_walk", "entity");
	public static final RegistryObject<SoundEvent> MEDIUM_WALK = register("medium_walk", "entity");
	public static final RegistryObject<SoundEvent> SMALL_WALK = register("small_walk", "entity");

	public static RegistryObject<SoundEvent> register(String id, String type) {
		SoundEvent event = new SoundEvent(LostWorldsUtils.rL(type + ".sound." + id));
		SOUNDS.add(Pair.of(event, "subtitle." + type + "." + id));
		REGISTRATE.subtitle(type + "." + id, Arrays.stream(id.toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")));
		return SOUND_EVENTS.register(id, () -> event);
	}

	public static void deferred(IEventBus bus) {
		SOUND_EVENTS.register(bus);
	}
}
