package lostworlds.addon;

import lostworlds.content.client.entity.model.ChilesaurusModel;
import net.minecraft.util.ResourceLocation;

public class ExampleAddon extends LostWorldsAddon
{
	public static final String ID = "example";
	
	private static final ResourceLocation MALE_STRIPPED = new ResourceLocation(ID, "male_stripped");
	private static final ResourceLocation FEMALE_STRIPPED = new ResourceLocation(ID, "male_stripped");
	
	static
	{
		ChilesaurusModel.addSkins(MALE_STRIPPED, FEMALE_STRIPPED);
	}
	
	@Override
	public String addonId() 
	{
		return ID;
	}

	@Override
	public String addonLoadMessage() 
	{
		return "LoadedAddon";
	}
}
