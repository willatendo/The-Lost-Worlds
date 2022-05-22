package lostworlds.server.effect;

import lostworlds.server.entity.utils.ModDamageSources;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SufficationEffect extends MobEffect {
	public SufficationEffect() {
		super(MobEffectCategory.HARMFUL, 0xf7f5f4);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int tick) {
		if (entity instanceof Player && !((Player) entity).isCreative() && !((Player) entity).isSpectator()) {
			entity.hurt(ModDamageSources.SUFFICATION, 2);
		}
	}

	@Override
	public boolean isDurationEffectTick(int i1, int i2) {
		int i = 40 >> i2;
		if (i > 0) {
			return i1 % i == 0;
		} else {
			return true;
		}
	}

	public static boolean canKill(Player player) {
		if (player.isCreative() || player.isSpectator()) {
			return false;
		} else {
			return true;
		}
	}
}
