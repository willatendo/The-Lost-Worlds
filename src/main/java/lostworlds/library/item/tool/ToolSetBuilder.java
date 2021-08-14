package lostworlds.library.item.tool;

import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;

public class ToolSetBuilder 
{
	public static Item create(IItemTier tier)
	{
		Item toolSword = new SwordItem(tier, 3, -2.4F, new Properties().tab(ModItemGroup.ITEMS).setNoRepair())
		{
			@Override
			public boolean isEnchantable(ItemStack stack) 
			{
				return true;
			};
			
			@Override
			public boolean isBookEnchantable(ItemStack stack, ItemStack book) 
			{
				return false;
			};
			
			@Override
			public boolean isFoil(ItemStack stack) 
			{
				return false;
			};
		};
		Item toolShovel = new ShovelItem(tier, 1.5F, -3.0F, new Properties().tab(ModItemGroup.ITEMS).setNoRepair())
		{
			@Override
			public boolean isEnchantable(ItemStack stack) 
			{
				return false;
			};
			
			@Override
			public boolean isBookEnchantable(ItemStack stack, ItemStack book) 
			{
				return false;
			};
			
			@Override
			public boolean isFoil(ItemStack stack) 
			{
				return false;
			};
		};
		Item toolPickaxe = new PickaxeItem(tier, 1, -2.8F, new Properties().tab(ModItemGroup.ITEMS).setNoRepair())
		{
			@Override
			public boolean isEnchantable(ItemStack stack) 
			{
				return false;
			};
			
			@Override
			public boolean isBookEnchantable(ItemStack stack, ItemStack book) 
			{
				return false;
			};
			
			@Override
			public boolean isFoil(ItemStack stack) 
			{
				return false;
			};
		};
		Item toolAxe = new AxeItem(tier, 6.0F, -3.2F, new Properties().tab(ModItemGroup.ITEMS).setNoRepair())
		{
			@Override
			public boolean isEnchantable(ItemStack stack) 
			{
				return false;
			};
			
			@Override
			public boolean isBookEnchantable(ItemStack stack, ItemStack book) 
			{
				return false;
			};
			
			@Override
			public boolean isFoil(ItemStack stack) 
			{
				return false;
			};
		};
		Item toolHoe = new HoeItem(tier, 0, -3.0F, new Properties().tab(ModItemGroup.ITEMS).setNoRepair())
		{
			@Override
			public boolean isEnchantable(ItemStack stack) 
			{
				return false;
			};
			
			@Override
			public boolean isBookEnchantable(ItemStack stack, ItemStack book) 
			{
				return false;
			};
			
			@Override
			public boolean isFoil(ItemStack stack) 
			{
				return false;
			};
		};
		ModRegistry.register(tier.toString().toLowerCase() + "_sword", toolSword);
		ModRegistry.register(tier.toString().toLowerCase() + "_shovel", toolShovel);
		ModRegistry.register(tier.toString().toLowerCase() + "_pickaxe", toolPickaxe);
		ModRegistry.register(tier.toString().toLowerCase() + "_axe", toolAxe);
		ModRegistry.register(tier.toString().toLowerCase() + "_hoe", toolHoe);
		return toolSword;
	}
}
