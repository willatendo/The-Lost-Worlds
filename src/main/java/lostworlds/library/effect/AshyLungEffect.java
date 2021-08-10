package lostworlds.library.effect;

import lostworlds.library.ModDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class AshyLungEffect extends Effect
{
	public AshyLungEffect(EffectType effectType, int colour) 
	{
		super(effectType, colour);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int i) 
	{	
		if(entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative())
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
		if(player.isCreative())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
