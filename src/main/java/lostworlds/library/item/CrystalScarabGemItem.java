package lostworlds.library.item;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.entity.item.ChargedCrystalScarabGemItemEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;

public class CrystalScarabGemItem extends Item
{
	public static Item charged_crystal_scarab_gem = new CEChargedCrystalScarabGemItem(new Properties().tab(ModUtils.ITEMS).fireResistant().rarity(Rarity.RARE), Variant.CHARGED);
	public static Item crystal_scarab_gem = new CrystalScarabGemItem(new Properties().tab(ModUtils.ITEMS).rarity(Rarity.RARE), Variant.UNCHARGED);
	
	public static Item crystal_scarab_abdomen = new CrystalScarabGemItem(new Properties().tab(ModUtils.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	public static Item crystal_scarab_bottom_left_leg = new CrystalScarabGemItem(new Properties().tab(ModUtils.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	public static Item crystal_scarab_bottom_right_leg = new CrystalScarabGemItem(new Properties().tab(ModUtils.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	public static Item crystal_scarab_thorax = new CrystalScarabGemItem(new Properties().tab(ModUtils.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	public static Item crystal_scarab_top_left_leg = new CrystalScarabGemItem(new Properties().tab(ModUtils.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	public static Item crystal_scarab_top_right_leg = new CrystalScarabGemItem(new Properties().tab(ModUtils.ITEMS).rarity(Rarity.RARE), Variant.BROKEN);
	
	private Variant variant;
	
	protected CrystalScarabGemItem(Properties properties, Variant variant)
	{
		super(properties);
		this.variant = variant;
	}
	
	@Override
	public boolean isFoil(ItemStack stack) 
	{
		if(this.variant != Variant.CHARGED)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static Item createAll()
	{
		ModRegistry.register("charged_crystal_scarab_gem", charged_crystal_scarab_gem);
		ModRegistry.register("crystal_scarab_gem", crystal_scarab_gem);

		ModRegistry.register("crystal_scarab_abdomen", crystal_scarab_abdomen);
		ModRegistry.register("crystal_scarab_bottom_left_leg", crystal_scarab_bottom_left_leg);
		ModRegistry.register("crystal_scarab_bottom_right_leg", crystal_scarab_bottom_right_leg);
		ModRegistry.register("crystal_scarab_thorax", crystal_scarab_thorax);
		ModRegistry.register("crystal_scarab_top_left_leg", crystal_scarab_top_left_leg);
		ModRegistry.register("crystal_scarab_top_right_leg", crystal_scarab_top_right_leg);

		return charged_crystal_scarab_gem;
	}

	public enum Variant
	{
		BROKEN,
		UNCHARGED,
		CHARGED;
	}
	
	static class CEChargedCrystalScarabGemItem extends CrystalScarabGemItem
	{
		protected CEChargedCrystalScarabGemItem(Properties properties, Variant variant) 
		{
			super(properties, variant);
		}
		
		@Override
		public int getEntityLifespan(ItemStack itemStack, World world) 
		{
			return Integer.MAX_VALUE;
		}
		
		@Override
		public boolean hasCustomEntity(ItemStack stack) 
		{
			return true;
		}
		
		@Override
		public Entity createEntity(World world, Entity entity, ItemStack stack) 
		{
			final ChargedCrystalScarabGemItemEntity customentity = new ChargedCrystalScarabGemItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), stack);
			customentity.setDeltaMovement(entity.getDeltaMovement());
			customentity.setPickUpDelay(40);
			
			return customentity;
		}
	}
}
