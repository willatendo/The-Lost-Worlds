package lostworlds.server.item;

import lostworlds.server.entity.terrestrial.jurassic.ChilesaurusEntity;
import lostworlds.server.entity.terrestrial.jurassic.KentrosaurusEntity;
import lostworlds.server.entity.utils.ModDamageSources;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;

public class SyringeItem extends ModItem {
	public SyringeItem(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
		if (entity != null) {
			// Modded
			if (entity instanceof ChilesaurusEntity) {
				player.playSound(SoundEvents.PLAYER_HURT, 1.0F, 1.0F);
				entity.hurt(ModDamageSources.PRICK, 1);
				ItemStack blood = DinoTypes.CHILESAURUS.getBloodSample().getDefaultInstance();
				player.setItemInHand(hand, blood);
				return ActionResultType.SUCCESS;
			} else if (entity instanceof KentrosaurusEntity) {
				player.playSound(SoundEvents.PLAYER_HURT, 1.0F, 1.0F);
				entity.hurt(ModDamageSources.PRICK, 1);
				ItemStack blood = DinoTypes.KENTROSAURUS.getBloodSample().getDefaultInstance();
				player.setItemInHand(hand, blood);
				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.FAIL;
	}
}