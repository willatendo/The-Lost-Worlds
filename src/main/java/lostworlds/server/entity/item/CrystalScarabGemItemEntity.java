package lostworlds.server.entity.item;

import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class CrystalScarabGemItemEntity extends ModItemEntity {
	public CrystalScarabGemItemEntity(EntityType<? extends CrystalScarabGemItemEntity> entityType, World world) {
		super(entityType, world);
	}

	public CrystalScarabGemItemEntity(World w, double x, double y, double z, ItemStack stack) {
		super(LostWorldsEntities.CRYSTAL_SCARAB_GEM_ITEM, w, x, y, z, stack);
		this.setExtendedLifetime();
	}

	@Override
	public void thunderHit(ServerWorld world, LightningBoltEntity lightning) {
		ItemEntity entity = EntityType.ITEM.create(world);
		entity.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
		entity.setItem(LostWorldsItems.CHARGED_CRYSTAL_SCARAB_GEM.get().getDefaultInstance());
		world.addFreshEntity(entity);
		this.remove();
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source == DamageSource.LIGHTNING_BOLT ? true : super.isInvulnerableTo(source);
	}
}
