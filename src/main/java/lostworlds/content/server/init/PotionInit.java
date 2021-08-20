package lostworlds.content.server.init;

import lostworlds.library.effect.AshyLungEffect;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;

public class PotionInit 
{
	public static final Effect ASHY_LUNG_EFFECT = ModRegistry.register("ashy_lung_effect", new AshyLungEffect());
	public static final Potion ASHY_LUNG_POTION = ModRegistry.register("ashy_lung_potion", new Potion(new EffectInstance(PotionInit.ASHY_LUNG_EFFECT, 3600)));
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Potions"); }
}
