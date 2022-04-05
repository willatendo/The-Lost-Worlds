package lostworlds.server.item;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.effect.AshyLungEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsPotions {
	public static final Effect ASHY_LUNG_EFFECT = register("ashy_lung_effect", new AshyLungEffect());
	public static final Potion ASHY_LUNG_POTION = register("ashy_lung_potion", new Potion(new EffectInstance(LostWorldsPotions.ASHY_LUNG_EFFECT, 3600)));

	public static Effect register(String id, Effect effect) {
		effect.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.POTIONS.register(effect);
		return effect;
	}

	public static Potion register(String id, Potion potion) {
		potion.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.POTION_TYPES.register(potion);
		return potion;
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Potions");
	}
}
