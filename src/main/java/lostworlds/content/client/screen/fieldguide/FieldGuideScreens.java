package lostworlds.content.client.screen.fieldguide;

import net.minecraft.client.Minecraft;

public class FieldGuideScreens 
{
	public static void allosaurusEntry()
	{
		Minecraft.getInstance().setScreen(new AllosaurusGUI());
	}
	
	static class AllosaurusGUI extends FieldGuideScreen
	{
		public AllosaurusGUI() 
		{
			super("allosaurus");
		}		
	}
}
