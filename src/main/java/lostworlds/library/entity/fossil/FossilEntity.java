package lostworlds.library.entity.fossil;

import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class FossilEntity extends AnimalEntity
{
	public FossilEntity(EntityType<? extends FossilEntity> entity, World world) 
	{
		super(entity, world);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() 
	{
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 100.0D);
	}

	@Override
	protected int getExperienceReward(PlayerEntity player) 
	{
		return 0;
	}

	@Override
	public boolean canBreatheUnderwater() 
	{
		return true;
	}
	
	private void playBrokenSound() 
	{
		this.level.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.SKELETON_DEATH, SoundCategory.BLOCKS, 1.0F, 1.0F);

	}

	private void playParticles() 
	{
		if(this.level instanceof ServerWorld) 
		{
			((ServerWorld)this.level).addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.BONE_BLOCK.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double)(this.getBbWidth() / 4.0F), (double)(this.getBbHeight() / 4.0F));
		}
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) 
	{
		if(source.getDirectEntity() instanceof PlayerEntity) 
		{
			this.remove();
			this.playBrokenSound();
			this.playParticles();
		}
		return false;
	}
	
	@Override
	public boolean isAffectedByPotions() 
	{
		return false;
	}

	public void onKillCommand() 
	{
		this.remove();
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) 
	{
		return null;
	}
}
