package lostworlds.content.server.init;

import lostworlds.library.effect.AshyLungEffect;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtil;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;

public class PotionInit 
{
	public static final Effect ASHY_LUNG_EFFECT = ModRegistry.register("ashy_lung_effect", new AshyLungEffect(EffectType.HARMFUL, 0x5b5858));
	public static final Potion ASHY_LUNG_POTION = ModRegistry.register("ashy_lung_potion", new Potion(new EffectInstance(PotionInit.ASHY_LUNG_EFFECT, 3600)));
	
	public static void init() { ModUtil.LOGGER.debug("Registering Mod Potions"); }
}
