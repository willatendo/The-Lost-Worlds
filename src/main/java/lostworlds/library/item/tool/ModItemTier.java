package lostworlds.library.item.tool;

import java.util.function.Supplier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum ModItemTier implements IItemTier
{
	LEATHER(0, 59, 2.0F, 0.0F, 15, () -> 
	{
		return Ingredient.of(Items.STRING);
	}),
	GOLD(0, 32, 2.0F, 0.0F, 22, () -> 
	{
		return Ingredient.of(Items.STRING);
	}),
	IRON(0, 250, 2.0F, 2.0F, 14, () -> 
	{
		return Ingredient.of(Items.STRING);
	}),
	DIAMOND(0, 1561, 2.0F, 3.0F, 10, () -> 
	{
		return Ingredient.of(Items.STRING);
	}),
	NETHERITE(0, 2031, 2.0F, 4.0F, 15, () -> 
	{
		return Ingredient.of(Items.STRING);
	}),	
	CRYSTAL_SCARAB(0, 13616, 2.0F, 80.0F, 40, () -> 
	{
		return Ingredient.of(Items.STRING); 
	}); 
	
	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final LazyValue<Ingredient> repairIngredient;
	
	private ModItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) 
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
