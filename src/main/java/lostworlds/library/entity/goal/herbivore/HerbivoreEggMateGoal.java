package lostworlds.library.entity.goal.herbivore;

import java.util.Random;

import lostworlds.library.entity.terrestrial.HerbivoreEggLayingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.GameRules;

public class HerbivoreEggMateGoal extends SleepyBreedGoal 
{
	private final HerbivoreEggLayingEntity entity;

	public HerbivoreEggMateGoal(HerbivoreEggLayingEntity entity, double speedModifier) 
	{
		super(entity, speedModifier);
		this.entity = entity;
	}

	@Override
	public boolean canUse() 
	{
		return super.canUse() && !this.entity.hasEgg();
	}

	@Override
	protected void breed() 
	{
		ServerPlayerEntity serverplayerentity = this.entity.getLoveCause();
		if(serverplayerentity == null && this.partner.getLoveCause() != null)
		{
			serverplayerentity = this.partner.getLoveCause();
		}

		if(serverplayerentity != null) 
		{
			serverplayerentity.awardStat(Stats.ANIMALS_BRED);
		}

		this.entity.setHasEgg(true);
		this.entity.resetLove();
		this.partner.resetLove();
		Random random = this.entity.getRandom();
		if(this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) 
		{
			this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.entity.getX(), this.entity.getY(), this.entity.getZ(), random.nextInt(7) + 1));
		}
	}
}
