package lostworlds.server.item;

import java.util.function.Supplier;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CustomEntityItem extends Item {
	private final Supplier<EntityType<? extends ItemEntity>> itemEntity;
	private final int lifespan;

	public CustomEntityItem(Supplier<EntityType<? extends ItemEntity>> itemEntity, int lifespan, Properties properties) {
		super(properties);
		this.itemEntity = itemEntity;
		this.lifespan = lifespan;
	}

	@Override
	public int getEntityLifespan(ItemStack stack, Level level) {
		return this.lifespan;
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}

	@Override
	public Entity createEntity(Level level, Entity entity, ItemStack stack) {
		var itemEntity = this.itemEntity.get().create(level);
		itemEntity.setPos(entity.getX(), entity.getY(), entity.getZ());
		itemEntity.setItem(stack);
		itemEntity.setDeltaMovement(entity.getDeltaMovement());
		itemEntity.setPickUpDelay(40);

		return itemEntity;
	}
}
