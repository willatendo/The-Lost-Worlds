package lostworlds.server.entity.item;

import lostworlds.server.entity.LostWorldsEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ChargedCrystalScarabGemItemEntity extends ModItemEntity {
	public ChargedCrystalScarabGemItemEntity(EntityType<? extends ChargedCrystalScarabGemItemEntity> entityType, World world) {
		super(entityType, world);
	}

	public ChargedCrystalScarabGemItemEntity(World w, double x, double y, double z, ItemStack stack) {
		super(LostWorldsEntities.CHARGED_CRYSTAL_SCARAB_GEM_ITEM, w, x, y, z, stack);
		this.setExtendedLifetime();
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source == DamageSource.LIGHTNING_BOLT ? true : super.isInvulnerableTo(source);
	}
}
