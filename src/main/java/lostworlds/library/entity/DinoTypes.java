package lostworlds.library.entity;

import lostworlds.content.server.init.EntityInit;
import lostworlds.library.entity.fossil.FossilEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IExtensibleEnum;

public enum DinoTypes implements IStringSerializable, IExtensibleEnum
{
	CHILESAURUS("chilesaurus", EntityInit.CHILESAURUS, Blocks.TURTLE_EGG, 0xb08533, 0x283c3f, 1, 3, 0.25F, 0.56F),
	KENTROSAURUS("kentrosaurus", EntityInit.KENTROSAURUS, Blocks.TURTLE_EGG, 0xd99760, 0x612c00, 3, 6, 0.4F, 0.66F),
	;
	
	private final String id;
	private EntityType<FossilEntity> entitytype;
	private EntityType<FossilEntity> skull;
	private EntityType<FossilEntity> leftArm;
	private EntityType<FossilEntity> rightArm;
	private EntityType<FossilEntity> leftLeg;
	private EntityType<FossilEntity> rightLeg;
	private EntityType<FossilEntity> ribCage;
	private EntityType<FossilEntity> tail;
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
	
	public EntityType<FossilEntity> setSkull(EntityType<FossilEntity> entity)
	{
		return this.skull = entity;
	}

	public EntityType<FossilEntity> setLeftArm(EntityType<FossilEntity> entity)
	{
		return this.leftArm = entity;
	}
	
	public EntityType<FossilEntity> setRightArm(EntityType<FossilEntity> entity)
	{
		return this.rightArm = entity;
	}
	
	public EntityType<FossilEntity> setLeftLeg(EntityType<FossilEntity> entity)
	{
		return this.leftLeg = entity;
	}
	
	public EntityType<FossilEntity> setRightLeg(EntityType<FossilEntity> entity)
	{
		return this.rightLeg = entity;
	}
	
	public EntityType<FossilEntity> setRibCage(EntityType<FossilEntity> entity)
	{
		return this.ribCage = entity;
	}
	
	public EntityType<FossilEntity> setTail(EntityType<FossilEntity> entity)
	{
		return this.tail = entity;
	}
	
	public EntityType<FossilEntity> getEntityType()
	{
		return this.entitytype;
	}
	
	public EntityType<FossilEntity> getSkull()
	{
		return this.skull;
	}

	public EntityType<FossilEntity> getLeftArm()
	{
		return this.leftArm;
	}
	
	public EntityType<FossilEntity> getRightArm()
	{
		return this.rightArm;
	}
	
	public EntityType<FossilEntity> getLeftLeg()
	{
		return this.leftLeg;
	}
	
	public EntityType<FossilEntity> getRightLeg()
	{
		return this.rightLeg;
	}
	
	public EntityType<FossilEntity> getRibCage()
	{
		return this.ribCage;
	}
	
	public EntityType<FossilEntity> getTail()
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
