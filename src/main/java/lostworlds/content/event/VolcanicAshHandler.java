package lostworlds.content.event;

import java.util.List;

import com.google.common.collect.ImmutableList;

import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.PotionInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VolcanicAshHandler 
{
	private static final List<Item> MASK_GEAR = ImmutableList.of(ItemInit.CLOTH_MASK);

	@SubscribeEvent
	public void onLivingTick(LivingEvent.LivingUpdateEvent event) 
	{
		LivingEntity entity = event.getEntityLiving();

		if(entity != null) 
		{
			World world = entity.level;
			BlockPos pos = entity.blockPosition();
			if(world.getBlockState(pos).getBlock() == BlockInit.VOLCANIC_ASH_LAYER) 
			{
				if(!isWearingMask(entity, EquipmentSlotType.HEAD)) 
				{
					entity.addEffect(new EffectInstance(PotionInit.ASHY_LUNG_EFFECT, 200));
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerHarvest(PlayerEvent.BreakSpeed event) 
	{
		PlayerEntity entity = event.getPlayer();

		if(entity != null) 
		{
			if(event.getState() == BlockInit.VOLCANIC_ASH_LAYER.defaultBlockState()) 
			{
				if(!isWearingMask(entity, EquipmentSlotType.HEAD)) 
				{
					entity.addEffect(new EffectInstance(PotionInit.ASHY_LUNG_EFFECT, 200));
				}
			}
		}
	}

	public static boolean isWearingMask(LivingEntity living, EquipmentSlotType pieceValue) 
	{
		return MASK_GEAR.contains(living.getItemBySlot(pieceValue).getItem());
	}
}
