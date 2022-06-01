package lostworlds.server.item.armour;

import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public record ArmourTier(ResourceLocation name, int maxDamage, int[] damageReductionArray, int enchantability, Supplier<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairItem) implements ArmorMaterial {

	private static final int[] MAX_SLOT_DAMAGE = new int[] { 13, 15, 16, 11 };

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
