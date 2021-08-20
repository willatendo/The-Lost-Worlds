package lostworlds.library.dimension;

import java.util.List;

import com.google.common.collect.ImmutableList;

import lostworlds.content.server.init.DimensionInit;
import lostworlds.content.server.init.ItemInit;
import lostworlds.library.util.ModUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModUtils.ID)
public class DimensionSetup 
{
	private static final List<Item> MASK_GEAR = ImmutableList.of(ItemInit.OXYGEN_MASK, ItemInit.OXYGEN_TANK);

	@SubscribeEvent
	public static void onSleepFinished(SleepFinishedTimeEvent event) 
	{
		IWorld world = event.getWorld();
		if(world instanceof ServerWorld) 
		{
			ServerWorld serverworld = (ServerWorld) world;
			if(serverworld.dimension() == DimensionInit.PERMIAN_WORLD) 
			{
				if(world.getLevelData() instanceof DerivedWorldInfo) 
				{
					((DerivedWorldInfo) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingTick(LivingEvent.LivingUpdateEvent event)
	{
		LivingEntity entity = event.getEntityLiving();
		if(entity instanceof PlayerEntity)
		{
			World world = event.getEntity().getCommandSenderWorld();
			ServerWorld serverworld = (ServerWorld)world;
			if(serverworld.dimension() == DimensionInit.PERMIAN_WORLD)
			{
				
			}
		}
	}
	
	public static boolean isWearingMaskAndEquipment(LivingEntity living, EquipmentSlotType pieceValue)
	{
		return MASK_GEAR.contains(living.getItemBySlot(pieceValue).getItem());
	}
}
