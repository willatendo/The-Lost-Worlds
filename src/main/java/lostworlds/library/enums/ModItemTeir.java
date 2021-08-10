package lostworlds.library.enums;

import java.util.function.Supplier;

import lostworlds.content.server.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public enum ModItemTeir implements IItemTier
{
	CRYSTAL_SCARAB(4, 13616, 30.0F, 80.0F, 40, () -> 
	{
		return Ingredient.of(ItemInit.CHARGED_CRYSTAL_SCARAB_GEM); 
	}); 
	
	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final LazyValue<Ingredient> repairIngredient;
	
	ModItemTeir(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) 
	{
		this.level = level;
		this.uses = uses;
		this.speed = speed;
		this.damage = damage;
		this.enchantmentValue = enchantmentValue;
		this.repairIngredient = new LazyValue<>(repairIngredient);
	}
	
	@Override
	public int getUses() 
	{
		return this.uses;
	}
	
	@Override
	public float getSpeed() 
	{
		return this.speed;
	}
	
	@Override
	public float getAttackDamageBonus() 
	{
		return this.damage;
	}
	
	@Override
	public int getLevel() 
	{
		return this.level;
	}
	
	@Override
	public int getEnchantmentValue() 
	{
		return this.enchantmentValue;
	}
	
	@Override
	public Ingredient getRepairIngredient() 
	{
		return this.repairIngredient.get();
	}
}