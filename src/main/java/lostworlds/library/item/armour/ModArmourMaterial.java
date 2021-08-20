package lostworlds.library.item.armour;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ModArmourMaterial implements IArmorMaterial
{
	CLOTH_MASK("cloth_mask", 0, new int[] { 0, 2, 3, 1 }, 0, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> 
	{
		return Ingredient.of(ItemTags.WOOL);
	}),
	OXYGEN_MASK("oxygen_mask", 0, new int[] { 2, 4, 7, 3 }, 0, SoundEvents.IRON_DOOR_OPEN, 0.0F, 0.0F, () -> 
	{
		return Ingredient.of(ItemTags.WOOL);
	});
	
	private static final int[] HEALTH_PER_SLOT = new int[] { 13, 15, 16, 11 };
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyValue<Ingredient> repairIngredient;

	private ModArmourMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) 
	{
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.slotProtections = slotProtections;
		this.enchantmentValue = enchantmentValue;
		this.sound = sound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = new LazyValue<>(repairIngredient);
	}

	public int getDurabilityForSlot(EquipmentSlotType slotType) 
	{
		return HEALTH_PER_SLOT[slotType.getIndex()] * this.durabilityMultiplier;
	}

	public int getDefenseForSlot(EquipmentSlotType slotType) 
	{
		return this.slotProtections[slotType.getIndex()];
	}

	public int getEnchantmentValue() 
	{
		return this.enchantmentValue;
	}

	public SoundEvent getEquipSound() 
	{
		return this.sound;
	}
	
	public Ingredient getRepairIngredient() 
	{
		return this.repairIngredient.get();
	}

	@OnlyIn(Dist.CLIENT)
	public String getName() 
	{
		return this.name;
	}

	public float getToughness() 
	{
		return this.toughness;
	}

	public float getKnockbackResistance() 
	{
		return this.knockbackResistance;
	}
}
