package lostworlds.server.entity.item;

import lostworlds.server.entity.LostWorldsEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;

public class ChargedCrystalScarabGemItemEntity extends ModItemEntity {
	public ChargedCrystalScarabGemItemEntity(EntityType<? extends ChargedCrystalScarabGemItemEntity> entityType, Level world) {
		super(entityType, world);
	}

	public ChargedCrystalScarabGemItemEntity(Level w, double x, double y, double z, ItemStack stack) {
		super(LostWorldsEntities.CHARGED_CRYSTAL_SCARAB_GEM_ITEM.get(), w, x, y, z, stack);
		this.setExtendedLifetime();
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source == DamageSource.LIGHTNING_BOLT ? true : super.isInvulnerableTo(source);
	}
}
