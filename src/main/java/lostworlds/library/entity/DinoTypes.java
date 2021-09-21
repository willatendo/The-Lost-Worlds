package lostworlds.library.entity;

import lostworlds.content.server.init.EntityInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IExtensibleEnum;

public enum DinoTypes implements IStringSerializable, IExtensibleEnum
{
	CHILESAURUS("chilesaurus", EntityInit.CHILESAURUS, Blocks.TURTLE_EGG, 0xb08533, 0x283c3f, 1, 3, 0.25F, 0.56F),
	KENTROSAURUS("kentrosaurus", EntityInit.KENTROSAURUS, Blocks.TURTLE_EGG, 0xd99760, 0x612c00, 3, 6, 0.4F, 0.66F),
	;
	
	private final String id;
	private EntityType<? extends Entity> entitytype;
	private EntityType<? extends Entity> skull;
	private EntityType<? extends Entity> leftArm;
	private EntityType<? extends Entity> rightArm;
	private EntityType<? extends Entity> leftLeg;
	private EntityType<? extends Entity> rightLeg;
	private EntityType<? extends Entity> ribCage;
	private EntityType<? extends Entity> tail;
	private final Block egg;
	private final int primaryColour;
	private final int secondaryColour;
	private final int rawNutrition;
	private final int cookedNutrition;
	private final float rawSaturation;
	private final float cookedSaturation;
	
	private DinoTypes(String id, EntityType entity, Block egg, int primaryColour, int secondaryColour, int rawNutrition, int cookedNutrition, float rawSaturation, float cookedSaturation)
	{
		this.id = id;
		this.entitytype = entity;
		this.egg = egg;
		this.primaryColour = primaryColour;
		this.secondaryColour = secondaryColour;
		this.rawNutrition = rawNutrition;
		this.cookedNutrition = cookedNutrition;
		this.rawSaturation = rawSaturation;
		this.cookedSaturation = cookedSaturation;
	}
	
	public EntityType<? extends Entity> setSkull(EntityType<? extends Entity> entity)
	{
		return this.skull = entity;
	}

	public EntityType<? extends Entity> setLeftArm(EntityType<? extends Entity> entity)
	{
		return this.leftArm = entity;
	}
	
	public EntityType<? extends Entity> setRightArm(EntityType<? extends Entity> entity)
	{
		return this.rightArm = entity;
	}
	
	public EntityType<? extends Entity> setLeftLeg(EntityType<? extends Entity> entity)
	{
		return this.leftLeg = entity;
	}
	
	public EntityType<? extends Entity> setRightLeg(EntityType<? extends Entity> entity)
	{
		return this.rightLeg = entity;
	}
	
	public EntityType<? extends Entity> setRibCage(EntityType<? extends Entity> entity)
	{
		return this.ribCage = entity;
	}
	
	public EntityType<? extends Entity> setTail(EntityType<? extends Entity> entity)
	{
		return this.tail = entity;
	}
	
	public EntityType<? extends Entity> getEntityType()
	{
		return this.entitytype;
	}
	
	public EntityType<? extends Entity> getSkull()
	{
		return this.skull;
	}

	public EntityType<? extends Entity> getLeftArm()
	{
		return this.leftArm;
	}
	
	public EntityType<? extends Entity> getRightArm()
	{
		return this.rightArm;
	}
	
	public EntityType<? extends Entity> getLeftLeg()
	{
		return this.leftLeg;
	}
	
	public EntityType<? extends Entity> getRightLeg()
	{
		return this.rightLeg;
	}
	
	public EntityType<? extends Entity> getRibCage()
	{
		return this.ribCage;
	}
	
	public EntityType<? extends Entity> getTail()
	{
		return this.tail;
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
	
	//Used for addon creation. Use second one, first one is just because IExtensibleEnum is dumb.
	public static DinoTypes create(String name, String id, EntityType entity, Block egg, int primaryColour, int secondaryColour, int rawNutrition, int cookedNutrition, float rawSaturation, float cookedSaturation)
	{
		throw new IllegalStateException("Enum not extended");
	}
	
	public static DinoTypes register(String id, EntityType entity, Block egg, int primaryColour, int secondaryColour, int rawNutrition, int cookedNutrition, float rawSaturation, float cookedSaturation)
	{
		return create(id, id, entity, egg, primaryColour, secondaryColour, rawNutrition, cookedNutrition, rawSaturation, cookedSaturation);
	}

	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
}
