package lostworlds.library.entity.terrestrial;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class MediumStepSoundHerbivoreEntity extends HerbivoreEntity
{
	public MediumStepSoundHerbivoreEntity(EntityType<? extends HerbivoreEntity> entity, World world) 
	{
		super(entity, world);
	}
	
	@Override
	protected void playStepSound(BlockPos pos, BlockState state)
	{
		if(LostWorldsConfig.CLIENT_CONFIG.dinoStompingSounds.get())
		{
			this.playSound(SoundInit.MEDIUM_WALK, 1.0F * 0.15F, 1.0F);
		}
	}
}
