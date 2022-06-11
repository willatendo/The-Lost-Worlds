package lostworlds.server.events;

import java.util.List;

import com.google.common.collect.ImmutableList;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.item.LostWorldsMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.FORGE)
public class OnLivingTickEvents {
	@SubscribeEvent
	public static void onLivingTick(LivingEvent.LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();

		if (entity != null) {
			Level world = entity.level;
			BlockPos pos = entity.blockPosition();
			if (world.getBlockState(pos).is(LostWorldsBlocks.VOLCANIC_ASH_LAYER.get())) {
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
