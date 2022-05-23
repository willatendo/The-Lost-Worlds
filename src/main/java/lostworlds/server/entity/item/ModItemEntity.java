package lostworlds.server.entity.item;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class ModItemEntity extends ItemEntity {
	public ModItemEntity(EntityType<? extends ModItemEntity> entityType, Level world) {
		super(entityType, world);
	}

	public ModItemEntity(EntityType<? extends ModItemEntity> entityType, Level world, double x, double y, double z, ItemStack stack) {
		this(entityType, world);
		this.setPos(x, y, z);
		this.yRot = this.random.nextFloat() * 360.0F;
		this.setDeltaMovement(this.random.nextDouble() * 0.2D - 0.1D, 0.2D, this.random.nextDouble() * 0.2D - 0.1D);
		this.setItem(stack);
		this.lifespan = stack.getEntityLifespan(world);
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
