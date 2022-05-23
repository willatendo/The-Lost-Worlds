package lostworlds.server.entity.item;

import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CrystalScarabGemItemEntity extends ModItemEntity {
	public CrystalScarabGemItemEntity(EntityType<? extends CrystalScarabGemItemEntity> entityType, Level world) {
		super(entityType, world);
	}

	public CrystalScarabGemItemEntity(Level w, double x, double y, double z, ItemStack stack) {
		super(LostWorldsEntities.CRYSTAL_SCARAB_GEM_ITEM.get(), w, x, y, z, stack);
		this.setExtendedLifetime();
	}

	@Override
	public void thunderHit(ServerLevel world, LightningBolt lightning) {
		ItemEntity entity = EntityType.ITEM.create(world);
		entity.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
		entity.setItem(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get().getDefaultInstance());
		world.addFreshEntity(entity);
		this.kill();
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source == DamageSource.LIGHTNING_BOLT ? true : super.isInvulnerableTo(source);
	}
}
