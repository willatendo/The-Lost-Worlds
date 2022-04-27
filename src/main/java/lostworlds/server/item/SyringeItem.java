package lostworlds.server.item;

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
			for (DinoTypes types : DinoTypes.values()) {
				if (entity.getClass() == types.getEntity()) {
					player.playSound(SoundEvents.PLAYER_HURT, 1.0F, 1.0F);
					entity.hurt(ModDamageSources.PRICK, 1);
					ItemStack blood = types.getBloodSyringe().get().getDefaultInstance();
					player.setItemInHand(hand, blood);
				}
			}
		}

		return ActionResultType.FAIL;
	}
}
