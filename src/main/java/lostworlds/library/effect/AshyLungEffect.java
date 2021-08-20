package lostworlds.library.effect;

import lostworlds.library.entity.ModDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class AshyLungEffect extends Effect
{
	public AshyLungEffect() 
	{
		super(EffectType.HARMFUL, 0x5b5858);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int tick) 
	{	
		if(entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative() && !((PlayerEntity) entity).isSpectator())
		{
			entity.hurt(ModDamageSources.ASHY_LUNG, 2);
		}
	}
	
	@Override
	public boolean isDurationEffectTick(int i1, int i2) 
	{
		int i = 40 >> i2;
		if(i > 0) 
		{
			return i1 % i == 0;
		} 
		else 
		{
			return true;
		}
	}
	
	public static boolean canKill(PlayerEntity player)
	{
		if(player.isCreative() || player.isSpectator())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
