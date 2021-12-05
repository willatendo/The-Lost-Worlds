package lostworlds.library.entity.utils.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.mojang.serialization.Codec;

import lostworlds.content.client.entity.render.bone.SkeletonRenderer;
import lostworlds.content.server.init.EntityInit;
import lostworlds.library.block.LargeEggBlock;
import lostworlds.library.block.MediumEggBlock;
import lostworlds.library.block.SmallEggBlock;
import lostworlds.library.block.TinyEggBlock;
import lostworlds.library.entity.fossil.FossilEntity;
import lostworlds.library.entity.terrestrial.PrehistoricEntity;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;

public enum DinoTypes implements IStringSerializable
{
	CHILESAURUS("chilesaurus", EntityInit.CHILESAURUS, true, Size.SMALL, CreatureDiet.HERBIVORE, 0xb08533, 0xb08533, 0x283c3f, 1, 3, 0.25F, 0.56F),
	DIICTODON("diictodon", EntityInit.DIICTODON, false, Size.TINY, CreatureDiet.HERBIVORE, 0xdc8a54, 0xdc8a54, 0x8b462e, 1, 2, 0.1F, 0.2F),
	DILOPHOSAURUS("dilophosaurus", EntityInit.DILOPHOSAURUS, true, Size.LARGE, CreatureDiet.CARNIVORE, 0xc49838, 0xc49838, 0xc75539, 5, 7, 0.5F, 0.76F),
	KENTROSAURUS("kentrosaurus", EntityInit.KENTROSAURUS, true, Size.MEDIUM, CreatureDiet.HERBIVORE, 0xd99760, 0xd99760, 0x612c00, 3, 6, 0.4F, 0.66F),
	LIAONINGOSAURUS("liaoningosaurus", EntityInit.LIAONINGOSAURUS, true, Size.MEDIUM, CreatureDiet.HERBIVORE, 0x712d0d, 0x712d0d, 0x7c8237, 4, 7, 0.45F, 0.68F),
	NAUTILUS("nautilus", EntityInit.NAUTILUS, false, Size.SMALL, CreatureDiet.NONE, 0xd4ccc3, 0xd4ccc3, 0xca7548, 2, 5, 0.3F, 0.5F),
	OPHTHALMOSAURUS("ophthalmosaurus", EntityInit.OPHTHALMOSAURUS, false, Size.MEDIUM, CreatureDiet.PISCIVORE, 0x858794, 0x858794, 0x0e131b, 4, 7, 0.4F, 0.6F),
	PALAEONISCUM("palaeoniscum", EntityInit.PALAEONISCUM, false, Size.TINY, CreatureDiet.NONE, 0x72797a, 0x72797a, 0x2f3a3d, 2, 5, 0.3F, 0.5F),
	PSITTACOSAURUS("psittacosaurus", EntityInit.PSITTACOSAURUS, true, Size.SMALL, CreatureDiet.HERBIVORE, 0x4c2c21, 0x4c2c21, 0x938639, 3, 5, 0.3F, 0.54F),
	TETRACERATOPS("tetraceratops", EntityInit.TETRACERATOPS, true, Size.SMALL, CreatureDiet.HERBIVORE, 0x623015, 0x623015, 0x21369b, 3, 5, 0.3F, 0.54F),
	ZEPHYROSAURUS("zephyrosaurus", EntityInit.ZEPHYROSAURUS, true, Size.SMALL, CreatureDiet.HERBIVORE, 0x577476, 0x577476, 0x9ba3a3, 3, 5, 0.3F, 0.54F),
	;
	
	public static final Codec<DinoTypes> CODEC = IStringSerializable.fromEnum(DinoTypes::values, DinoTypes::byName);
	private static final Map<String, DinoTypes> BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(DinoTypes::getId, (types) -> 
	{
		return types;
	}));
	
	private final String id;
	private EntityType<? extends PrehistoricEntity> entitytype;
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
	private Block egg;
	private Item skeletonPick;
	private Item dna;
	private Item bloodSample;
	private Item meat;
	private Item fishBucket;
	private final boolean eggLaying;
	private final Size eggSize;
	private final CreatureDiet diet;
	private final int primaryColour;
	private final int eggSetColour;
	private final int secondaryColour;
	private final int rawNutrition;
	private final int cookedNutrition;
	private final float rawSaturation;
	private final float cookedSaturation;
	
	private DinoTypes(String id, EntityType entity, boolean eggLaying, Size eggSize, CreatureDiet diet, int eggSetColour, int primaryColour, int secondaryColour, int rawNutrition, int cookedNutrition, float rawSaturation, float cookedSaturation)
	{		
		this.id = id;
		this.entitytype = entity;
		this.eggLaying = eggLaying;
		this.eggSize = eggSize;
		this.diet = diet;
		this.eggSetColour = eggSetColour;
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
	
	public EntityType<? extends PrehistoricEntity> getEntityType()
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
	
	public Item setMeat(Item meat)
	{
		return this.meat = meat;
	}
	
	public Item getMeat()
	{
		return this.meat;
	}
	
	public Item setFishBucket(Item fishBucket)
	{
		return this.fishBucket = fishBucket;
	}
	
	public Item getFishBucket()
	{
		return this.fishBucket;
	}
	
	public Block setEgg(Block egg)
	{
		return this.egg = egg;
	}
	
	public Block getEgg()
	{
		return this.egg;
	}
	
	public Block getEgg(EntityType<? extends PrehistoricEntity> entity)
	{
		Properties abstractProperties = Properties.of(Material.EGG).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion();
		
		switch(this.eggSize)
		{
			case TINY:
				Block tinyegg = new TinyEggBlock(abstractProperties, () -> entity);
				this.egg = tinyegg;
				return tinyegg;
			case SMALL:
				Block smallegg = new SmallEggBlock(abstractProperties, () -> entity);
				this.egg = smallegg;
				return smallegg;
			case MEDIUM:
				Block mediumegg = new MediumEggBlock(abstractProperties, () -> entity);
				this.egg = mediumegg;
				return mediumegg;
			case LARGE:
				Block largeegg = new LargeEggBlock(abstractProperties, () -> entity);
				this.egg = largeegg;
				return largeegg;
			case NONE:
			default:
				return null;
		}
	}
	
	public CreatureDiet getDiet()
	{
		return this.diet;
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
	
	public Item getBloodSample()
	{
		return this.bloodSample;
	}
	
	public Item setBloodSample(Item item)
	{
		return this.bloodSample = item;
	}
	
	public Callable<ItemStackTileEntityRenderer> getISTER()
	{
		return new SkeletonRenderer(this.id);
	}
	
	public Callable<ItemStackTileEntityRenderer> getISTER(String part)
	{
		return new SkeletonRenderer(this.id + "_" + part, this.id);
	}
	
	public int getSetEggColour()
	{
		return this.eggSetColour;
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
	
	public static ArrayList<DinoTypes> liveBirth()
	{
		ArrayList<DinoTypes> list = new ArrayList<>();
		for(DinoTypes type : DinoTypes.values())
		{
			if(!type.eggLaying)
			{
				list.add(type);
			}
		}
		return list;
	}
	
	public static ArrayList<DinoTypes> eggLaying()
	{
		ArrayList<DinoTypes> list = new ArrayList<>();
		for(DinoTypes type : DinoTypes.values())
		{
			if(type.eggLaying)
			{
				list.add(type);
			}
		}
		return list;
	}
	
	public static ArrayList<DinoTypes> fish()
	{
		ArrayList<DinoTypes> list = new ArrayList<>();
		list.add(PALAEONISCUM);
		return list;
	}
	
	@Nullable
	public static DinoTypes byName(String id) 
	{
		return BY_NAME.get(id);
	}

	@Override
	public String getSerializedName() 
	{
		return this.id;
	}
}
