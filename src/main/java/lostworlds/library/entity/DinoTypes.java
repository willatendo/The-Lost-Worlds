package lostworlds.library.entity;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public enum DinoTypes 
{
	TEST(EntityType.ARMOR_STAND, Blocks.TURTLE_EGG, 0x000000, 0xFFFFFF, 1, 2, 1.0F, 2.0F);
	
	private final EntityType<? extends Entity> entitytype;
	private final Block egg;
	private final int primaryColour;
	private final int secondaryColour;
	private final int rawNutrition;
	private final int cookedNutrition;
	private final float rawSaturation;
	private final float cookedSaturation;
	
	private DinoTypes(EntityType entity, Block egg, int primaryColour, int secondaryColour, int rawNutrition, int cookedNutrition, float rawSaturation, float cookedSaturation)
	{
		this.entitytype = entity;
		this.egg = egg;
		this.primaryColour = primaryColour;
		this.secondaryColour = secondaryColour;
		this.rawNutrition = rawNutrition;
		this.cookedNutrition = cookedNutrition;
		this.rawSaturation = rawSaturation;
		this.cookedSaturation = cookedSaturation;
	}
	
	public EntityType<? extends Entity> getEntityType()
	{
		return this.entitytype;
	}
	
	public Block getEgg()
	{
		return this.egg;
	}
	
	public int getPrimaryColour()
	{
		return this.primaryColour;
	}
	
	public int getSecondaryColour()
	{
		return this.secondaryColour;
	}
	
	public int getRawNutrition()
	{
		return this.rawNutrition;
	}
	
	public int getCookedNutrition()
	{
		return this.cookedNutrition;
	}
	
	public float getRawSaturation()
	{
		return this.rawSaturation;
	}
	
	public float getCookedSaturation()
	{
		return this.cookedSaturation;
	}
}
