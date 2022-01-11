package lostworlds.library.entity.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ModItemEntity extends ItemEntity {
	public ModItemEntity(EntityType<? extends ModItemEntity> entityType, World world) {
		super(entityType, world);
	}

	public ModItemEntity(EntityType<? extends ModItemEntity> entityType, World world, double x, double y, double z, ItemStack stack) {

		this(entityType, world);
		this.setPos(x, y, z);
		this.yRot = this.random.nextFloat() * 360.0F;
		this.setDeltaMovement(this.random.nextDouble() * 0.2D - 0.1D, 0.2D, this.random.nextDouble() * 0.2D - 0.1D);
		this.setItem(stack);
		this.lifespan = stack.getEntityLifespan(world);
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
