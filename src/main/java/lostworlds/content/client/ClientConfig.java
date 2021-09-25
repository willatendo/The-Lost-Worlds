package lostworlds.content.client;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class ClientConfig 
{
	protected static final String TRANSLATION_TEXT = "lostworlds.config.";
	
	//Sounds
	public final BooleanValue machineSounds;
	
	public ClientConfig(ForgeConfigSpec.Builder builder) 
	{
		this.machineSounds = builder.comment("Sets weather or not machines will have the machine whirrling sound.").translation(TRANSLATION_TEXT + "machineSounds").define("machineSounds", true);
	}
}
