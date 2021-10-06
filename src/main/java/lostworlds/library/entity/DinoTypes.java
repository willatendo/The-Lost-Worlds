package lostworlds.library.entity;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.mojang.serialization.Codec;

import lostworlds.content.client.entity.render.bone.SkeletonRenderer;
import lostworlds.content.server.init.EntityInit;
import lostworlds.library.entity.fossil.FossilEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IExtensibleEnum;

public enum DinoTypes implements IStringSerializable, IExtensibleEnum
{
	CHILESAURUS("chilesaurus", EntityInit.CHILESAURUS, Blocks.TURTLE_EGG, 0xb08533, 0x283c3f, 1, 3, 0.25F, 0.56F),
	KENTROSAURUS("kentrosaurus", EntityInit.KENTROSAURUS, Blocks.TURTLE_EGG, 0xd99760, 0x612c00, 3, 6, 0.4F, 0.66F),
	DILOPHOSAURUS("dilophosaurus", EntityInit.CHILESAURUS, Blocks.TUBE_CORAL, 0xb37a29, 0x191918, 3, 6, 0.4F, 0.66F)
	;
	
	public static final Codec<DinoTypes> CODEC = IStringSerializable.fromEnum(DinoTypes::values, DinoTypes::byName);
	private static final Map<String, DinoTypes> BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(DinoTypes::getId, (types) -> 
	{
		return types;
	}));
	
	private final String id;
	private EntityType<FossilEntity> entitytype;
	private EntityType<FossilEntity> dirtySkull;
	private EntityType<FossilEntity> dirtyArmBones;
	private EntityType<FossilEntity> dirtyLegBones;
	private EntityType<FossilEntity> dirtyRibCage;
	private EntityType<FossilEntity> dirtyTail;
	private EntityType<FossilEntity> skull;
	private EntityType<FossilEntity> armBones;
	private EntityType<FossilEntity> legBones;
	private EntityType<FossilEntity> ribCage;
	private EntityType<FossilEntity> tail;
	private EntityType<FossilEntity> skeleton;
	private Item skeletonPick;
	private Item dna;
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
	
	public String getId()
	{
		return this.id;
	}
	
	public EntityType<FossilEntity> setDirtySkull(EntityType<FossilEntity> entity)
	{
		return this.dirtySkull = entity;
	}

	public EntityType<FossilEntity> setDirtyArmBones(EntityType<FossilEntity> entity)
	{
		return this.dirtyArmBones = entity;
	}
	
	public EntityType<FossilEntity> setDirtyLegBones(EntityType<FossilEntity> entity)
	{
		return this.dirtyLegBones = entity;
	}
	
	public EntityType<FossilEntity> setDirtyRibCage(EntityType<FossilEntity> entity)
	{
		return this.dirtyRibCage = entity;
	}
	
	public EntityType<FossilEntity> setDirtyTail(EntityType<FossilEntity> entity)
	{
		return this.dirtyTail = entity;
	}
	
	public EntityType<FossilEntity> setSkull(EntityType<FossilEntity> entity)
	{
		return this.skull = entity;
	}

	public EntityType<FossilEntity> setArmBones(EntityType<FossilEntity> entity)
	{
		return this.armBones = entity;
	}
	
	public EntityType<FossilEntity> setLegBones(EntityType<FossilEntity> entity)
	{
		return this.legBones = entity;
	}
	
	public EntityType<FossilEntity> setRibCage(EntityType<FossilEntity> entity)
	{
		return this.ribCage = entity;
	}
	
	public EntityType<FossilEntity> setTail(EntityType<FossilEntity> entity)
	{
		return this.tail = entity;
	}
	
	public EntityType<FossilEntity> setSkeleton(EntityType<FossilEntity> entity)
	{
		return this.skeleton = entity;
	}
	
	public Item setSkeletonPick(Item item)
	{
		return this.skeletonPick = item;
	}
	
	public EntityType<FossilEntity> getEntityType()
	{
		return this.entitytype;
	}
	
	public EntityType<FossilEntity> getDirtySkull()
	{
		return this.dirtySkull;
	}

	public EntityType<FossilEntity> getDirtyArmBones()
	{
		return this.dirtyArmBones;
	}
	
	public EntityType<FossilEntity> getDirtyLegBones()
	{
		return this.dirtyLegBones;
	}
	
	public EntityType<FossilEntity> getDirtyRibCage()
	{
		return this.dirtyRibCage;
	}
	
	public EntityType<FossilEntity> getDirtyTail()
	{
		return this.dirtyTail;
	}
	
	public EntityType<FossilEntity> getSkull()
	{
		return this.skull;
	}

	public EntityType<FossilEntity> getArmBones()
	{
		return this.armBones;
	}
	
	public EntityType<FossilEntity> getLegBones()
	{
		return this.legBones;
	}
	
	public EntityType<FossilEntity> getRibCage()
	{
		return this.ribCage;
	}
	
	public EntityType<FossilEntity> getTail()
	{
		return this.tail;
	}
	
	public EntityType<FossilEntity> getSkeleton()
	{
		return this.skeleton;
	}
	
	public Item getSkeletonPick()
	{
		return this.skeletonPick;
	}
	
	public Item getDNA()
	{
		return this.dna;
	}
	
	public Item setDNA(Item item)
	{
		return this.dna = item;
	}
	
	public Callable<ItemStackTileEntityRenderer> getISTER()
	{
		return new SkeletonRenderer(this.id);
	}
	
	public Callable<ItemStackTileEntityRenderer> getISTER(String part)
	{
		return new SkeletonRenderer(this.id + "_" + part, this.id);
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
	
	@Nullable
	public static DinoTypes byName(String id) 
	{
		return BY_NAME.get(id);
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
