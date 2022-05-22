package lostworlds.server.item.armour;

import java.util.function.Supplier;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ArmourTier implements ArmorMaterial {
	private static final int[] MAX_SLOT_DAMAGE = new int[] { 13, 15, 16, 11 };
	private final ResourceLocation name;
	private final int maxDamage;
	private final int[] damageReductionArray;
	private final int enchantability;
	private final Supplier<SoundEvent> equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyLoadedValue<Ingredient> repairItem;

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
		this.repairItem = new LazyLoadedValue<>(repairItem);
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlot type) {
		return MAX_SLOT_DAMAGE[type.getIndex()] * this.maxDamage;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlot type) {
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
