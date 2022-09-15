package lostworlds.client;

import java.util.List;

import com.google.common.collect.ImmutableList;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.item.LostWorldsItems;
import lostworlds.server.item.LostWorldsEffects;
import lostworlds.server.util.Version;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.ItemHandlerHelper;

@EventBusSubscriber(bus = Bus.FORGE, modid = LostWorldsUtils.ID, value = Dist.CLIENT)
public class PlayerLoggedInEvent {
	@SubscribeEvent
	public static void onClientPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		player.sendMessage(Version.getMessage(LostWorldsUtils.VERSION_PARSER, Version.toStringVersion(LostWorldsUtils.VERSION)), player.getUUID());
		CompoundNBT playerData = event.getPlayer().getPersistentData();
		CompoundNBT data = playerData.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
		if (data != null && !data.getBoolean("has_lexicon")) {
			ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), LostWorldsItems.LOST_WORLDS_LEXICON.asStack());
			data.putBoolean("has_lexicon", true);
			playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
		}
	}

	@SubscribeEvent
	public static void onLivingTick(LivingEvent.LivingUpdateEvent event) {
		LivingEntity entity = event.getEntityLiving();

		if (entity != null) {
			World world = entity.level;
			BlockPos pos = entity.blockPosition();
			if (world.getBlockState(pos).is(LostWorldsBlocks.VOLCANIC_ASH_LAYER.get())) {
				if (!isWearingMask(entity, EquipmentSlotType.HEAD)) {
					entity.addEffect(new EffectInstance(LostWorldsEffects.ASHY_LUNG_EFFECT.get(), 200));
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerHarvest(PlayerEvent.BreakSpeed event) {
		PlayerEntity entity = event.getPlayer();
		if (entity != null) {
			if (event.getState().is(LostWorldsBlocks.VOLCANIC_ASH_LAYER.get())) {
				if (!isWearingMask(entity, EquipmentSlotType.HEAD)) {
					entity.addEffect(new EffectInstance(LostWorldsEffects.ASHY_LUNG_EFFECT.get(), 200));
				}
			}
		}
	}

	public static boolean isWearingMask(LivingEntity living, EquipmentSlotType pieceValue) {
		List<Item> mask = ImmutableList.of(LostWorldsItems.CLOTH_MASK.get());
		return mask.contains(living.getItemBySlot(pieceValue).getItem());
	}
}
