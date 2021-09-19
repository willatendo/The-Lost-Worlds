package lostworlds.library.entity;

import net.minecraft.util.DamageSource;

public class ModDamageSources 
{
	//Human
	public static final DamageSource ASHY_LUNG = (new DamageSource("ashyLung")).bypassArmor().bypassInvul().bypassMagic();
	public static final DamageSource SUFFICATION = (new DamageSource("suffication")).bypassArmor().bypassInvul().bypassMagic();
	
	//Dinos
	public static final DamageSource HUNGER = (new DamageSource("hunger")).bypassArmor().bypassInvul().bypassMagic();
}
