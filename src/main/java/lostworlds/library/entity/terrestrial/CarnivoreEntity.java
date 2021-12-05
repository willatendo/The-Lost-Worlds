package lostworlds.library.entity.terrestrial;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public abstract class CarnivoreEntity extends EggLayingEntity
{
	public CarnivoreEntity(EntityType<? extends EggLayingEntity> entity, World world)
	{
		super(entity, world);
	}
	
	@Override
	public void killed(ServerWorld world, LivingEntity entity) 
	{
		this.increaseHunger(9000);
	}
}
