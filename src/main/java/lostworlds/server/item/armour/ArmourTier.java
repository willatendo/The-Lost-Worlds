package lostworlds.server.item.armour;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ArmourTier implements IArmorMaterial {
	private static final int[] MAX_SLOT_DAMAGE = new int[] { 13, 15, 16, 11 };
	private final ResourceLocation name;
	private final int maxDamage;
	private final int[] damageReductionArray;
	private final int enchantability;
	private final Supplier<SoundEvent> equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyValue<Ingredient> repairItem;

	public ArmourTier(ResourceLocation name, int maxDamage, int[] damageReductionArray, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairItem) {
		this(name, maxDamage, damageReductionArray, enchantability, () -> equipSound, toughness, knockbackResistance, repairItem);
	}

	public ArmourTier(ResourceLocation name, int maxDamage, int[] damageReductionArray, int enchantability, Supplier<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairItem) {
		this.name = name;
		this.maxDamage = maxDamage;
		this.damageReductionArray = damageReductionArray;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairItem = new LazyValue<>(repairItem);
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlotType type) {
		return MAX_SLOT_DAMAGE[type.getIndex()] * this.maxDamage;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlotType type) {
		return this.damageReductionArray[type.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.equipSound.get();
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairItem.get();
	}

	@Override
	public String getName() {
		return this.name.toString();
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}
}
