package lostworlds.server.item;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.effect.AshyLungEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;

public class LostWorldsPotions {
	public static final Effect ASHY_LUNG_EFFECT = LostWorldsRegistry.register("ashy_lung_effect", new AshyLungEffect());
	public static final Potion ASHY_LUNG_POTION = LostWorldsRegistry.register("ashy_lung_potion", new Potion(new EffectInstance(LostWorldsPotions.ASHY_LUNG_EFFECT, 3600)));

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Potions");
	}
}
