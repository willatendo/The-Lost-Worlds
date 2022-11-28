package lostworlds.server.events;

import java.util.List;

import com.google.common.collect.ImmutableList;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.item.LostWorldsMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.FORGE)
public class BreakSpeedEvents {
	@SubscribeEvent
	public static void breakSpeed(PlayerEvent.BreakSpeed event) {
		Player entity = event.getEntity();

		if (entity != null) {
			if (event.getState().is(LostWorldsBlocks.VOLCANIC_ASH_LAYER.get())) {
				if (!isWearingMask(entity, EquipmentSlot.HEAD)) {
					entity.addEffect(new MobEffectInstance(LostWorldsMobEffects.ASHY_LUNG_EFFECT.get(), 200));
				}
			}
		}
	}

	private static boolean isWearingMask(LivingEntity entity, EquipmentSlot slot) {
		List<Item> mask = ImmutableList.of(LostWorldsItems.CLOTH_MASK.get());
		return mask.contains(entity.getItemBySlot(slot).getItem());
	}
}
