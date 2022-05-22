package lostworlds.server.item;

import java.util.ArrayList;
import java.util.function.Supplier;

import com.mojang.datafixers.util.Pair;

import lostworlds.server.entity.utils.ModDamageSources;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SyringeItem extends Item {
	public static final ArrayList<Pair<Supplier<? extends EntityType<? extends Entity>>, Supplier<Item>>> MAP = new ArrayList<>();

	public SyringeItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
		if (entity != null) {
			for (Pair<Supplier<? extends EntityType<? extends Entity>>, Supplier<Item>> map : MAP) {
				if (entity.getType() == map.getFirst().get()) {
					player.playSound(SoundEvents.PLAYER_HURT, 1.0F, 1.0F);
					entity.hurt(ModDamageSources.PRICK, 1);
					stack.shrink(1);
					player.setItemInHand(hand, map.getSecond().get().getDefaultInstance());
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.PASS;
	}
}
