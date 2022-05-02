package lostworlds.server.item;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.effect.AshyLungEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsPotions {
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, LostWorldsUtils.ID);

	public static final RegistryObject<AshyLungEffect> ASHY_LUNG_EFFECT = EFFECTS.register("ashy_lung_effect", () -> new AshyLungEffect());

	public static void deferred(IEventBus bus) {
		EFFECTS.register(bus);
	}
}
