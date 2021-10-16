package lostworlds.content.client;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class ClientConfig 
{
	protected static final String TRANSLATION_TEXT = "lostworlds.config.";
	
	//Sounds
	public final BooleanValue machineSounds;
	
	public final BooleanValue dinoStompingSounds;
	
	public final BooleanValue eggsSetColour;
	
	public ClientConfig(ForgeConfigSpec.Builder builder) 
	{
		//Sounds
		this.machineSounds = builder.comment("Sets weather or not machines will have the machine whirrling sound.").translation(TRANSLATION_TEXT + "machineSounds").define("machineSounds", true);
		
		this.dinoStompingSounds = builder.comment("Sets if creatures will make a walking sound.").translation(TRANSLATION_TEXT + "dinoStompingSounds").define("dinoStompingSounds", true);
		
		this.eggsSetColour = builder.comment("Sets if eggs will copy the foliage colour, or will be a set colour.").translation(TRANSLATION_TEXT + "eggsSetColour").define("eggsSetColour", false);
	}
}
