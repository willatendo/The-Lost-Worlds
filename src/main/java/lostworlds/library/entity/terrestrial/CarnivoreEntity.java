package lostworlds.library.entity.terrestrial;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.utils.enums.CreatureDiet;
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
	public CreatureDiet diet() 
	{
		return CreatureDiet.CARNIVORE;
	}
	
	@Override
	public void killed(ServerWorld world, LivingEntity entity) 
	{
		super.killed(world, entity);
		ModUtils.LOGGER.debug("HAY!! This is called!");
		this.increaseHunger(this.maxHunger());
		this.heal(3.0F);
	}
}
