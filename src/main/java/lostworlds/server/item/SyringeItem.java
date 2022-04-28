package lostworlds.server.item;

import java.util.ArrayList;
import java.util.function.Supplier;

import com.mojang.datafixers.util.Pair;

import lostworlds.server.entity.utils.ModDamageSources;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;

public class SyringeItem extends ModItem {
	public static final ArrayList<Pair<Supplier<? extends EntityType<? extends Entity>>, Supplier<Item>>> MAP = new ArrayList<>();

	public SyringeItem(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
		if (entity != null) {
			for (Pair<Supplier<? extends EntityType<? extends Entity>>, Supplier<Item>> map : MAP) {
				if (entity.getType() == map.getFirst().get()) {
					player.playSound(SoundEvents.PLAYER_HURT, 1.0F, 1.0F);
					entity.hurt(ModDamageSources.PRICK, 1);
					stack.shrink(1);
					player.setItemInHand(hand, map.getSecond().get().getDefaultInstance());
					return ActionResultType.SUCCESS;
				}
			}
		}
		return ActionResultType.PASS;
	}
}
