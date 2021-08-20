package lostworlds.library.item.armour;

import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;

import lostworlds.content.server.init.ItemInit;
import lostworlds.library.tab.ModItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class OxygenTankItem extends ArmorItem
{
	private static final List<Item> MASK_GEAR = ImmutableList.of(ItemInit.OXYGEN_MASK);

	public OxygenTankItem() 
	{
		super(ModArmourMaterial.OXYGEN_MASK, EquipmentSlotType.CHEST, new Properties().stacksTo(1).tab(ModItemGroup.ITEMS));
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int tick, boolean b) 
	{
		Random rand = new Random();
		LivingEntity livingEntity = (LivingEntity)entity;
		if(isWearingMask(livingEntity, EquipmentSlotType.HEAD) && livingEntity.getItemBySlot(EquipmentSlotType.CHEST).getItem() == this)
		{
			if(stack.getMaxDamage() - stack.getDamageValue() <= 1)
			{
				stack.shrink(1);
			}
			else
			{
				ServerPlayerEntity player = (ServerPlayerEntity)livingEntity;
				stack.hurt(tick, rand, player);
			}
		}
	}
	
	public static boolean isWearingMask(LivingEntity living, EquipmentSlotType pieceValue)
	{
		return MASK_GEAR.contains(living.getItemBySlot(pieceValue).getItem());
	}
}
