package lostworlds.content.server.event;

import java.util.List;

import com.google.common.collect.ImmutableList;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ItemInit;
import lostworlds.content.server.init.PotionInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.ItemHandlerHelper;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.FORGE)
public class ServerEvent 
{
	private static final List<Item> MASK_GEAR = ImmutableList.of(ItemInit.CLOTH_MASK);

	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) 
	{
		CompoundNBT playerData = event.getPlayer().getPersistentData();
		CompoundNBT data = playerData.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
		if(data != null && !data.getBoolean("has_lexicon")) 
		{
			ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), new ItemStack(ItemInit.LOST_WORLDS_LEXICON));
			data.putBoolean("has_lexicon", true);
			playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
		}
	}
	
	@SubscribeEvent
	public void onLivingTick(LivingEvent.LivingUpdateEvent event)
	{
		LivingEntity entity = event.getEntityLiving();
		World world = entity.level;
		BlockPos pos = entity.blockPosition();
		
		if(entity != null && entity instanceof LivingEntity)
		{
			if(world.getBlockState(pos) == BlockInit.VOLCANIC_ASH_LAYER.defaultBlockState())
			{
				if(!isWearingMask(entity, EquipmentSlotType.HEAD))
				{
					entity.addEffect(new EffectInstance(PotionInit.ASHY_LUNG_EFFECT, 200));
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingTick(PlayerEvent.HarvestCheck event)
	{
		LivingEntity entity = event.getEntityLiving();
		
		if(entity != null && entity instanceof LivingEntity)
		{
			if(event.getTargetBlock() == BlockInit.VOLCANIC_ASH_LAYER.defaultBlockState())
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
