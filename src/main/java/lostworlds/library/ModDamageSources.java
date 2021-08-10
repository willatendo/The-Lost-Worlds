package lostworlds.library;

import net.minecraft.util.DamageSource;

public class ModDamageSources 
{
	//Player Effect Deaths
	public static final DamageSource ASHY_LUNG = (new DamageSource("ashyLung")).bypassArmor().bypassInvul().bypassMagic();
	public static final DamageSource SUFFICATION = (new DamageSource("suffication")).bypassArmor().bypassInvul().bypassMagic();
}
