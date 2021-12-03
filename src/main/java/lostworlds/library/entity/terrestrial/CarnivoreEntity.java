package lostworlds.library.entity.terrestrial;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class CarnivoreEntity extends EggLayingEntity
{
	public CarnivoreEntity(EntityType<? extends EggLayingEntity> entity, World world)
	{
		super(entity, world);
	}
}
