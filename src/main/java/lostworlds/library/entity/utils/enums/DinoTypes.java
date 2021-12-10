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
	ALLOSAURUS("allosaurus", EntityInit.ALLOSAURUS, true, false, true, Size.LARGE, CreatureDiet.CARNIVORE, 0x9f9f5a, 0x9f9f5a, 0xd68812, 5, 8, 0.4F, 0.8F),
	ANOMALOCARIS("anomalocaris", EntityInit.ANOMALOCARIS, false, false, false, Size.SMALL, CreatureDiet.CARNIVORE, 0xb94f33, 0xb94f33, 0x631312, 2, 4, 0.2F, 0.4F),
	CARNOTAURUS("carnotaurus", EntityInit.CARNOTAURUS, true, false, true, Size.LARGE, CreatureDiet.CARNIVORE, 0xbd7868, 0xbd7868, 0xe6d3bc, 6, 9, 0.4F, 0.8F),
	CHILESAURUS("chilesaurus", EntityInit.CHILESAURUS, true, false, true, Size.SMALL, CreatureDiet.HERBIVORE, 0xb08533, 0xb08533, 0x283c3f, 1, 3, 0.25F, 0.56F),
	CRYOLOPHOSAURUS("cryolophosaurus", EntityInit.CRYOLOPHOSAURUS, true, false, true, Size.MEDIUM, CreatureDiet.CARNIVORE, 0xab5a14, 0xab5a14, 0x1a2c5f, 5, 7, 0.5F, 0.6F),
	DIICTODON("diictodon", EntityInit.DIICTODON, false, false, false, Size.TINY, CreatureDiet.HERBIVORE, 0xdc8a54, 0xdc8a54, 0x8b462e, 1, 2, 0.1F, 0.2F),
	DILOPHOSAURUS("dilophosaurus", EntityInit.DILOPHOSAURUS, true, false, true, Size.LARGE, CreatureDiet.CARNIVORE, 0xc49838, 0xc49838, 0xc75539, 5, 7, 0.5F, 0.76F),
	DIMETRODON("dimetrodon", EntityInit.DIMETRODON, true, false, true, Size.MEDIUM, CreatureDiet.CARNIVORE, 0x81644c, 0x81644c, 0xcba755, 5, 7, 0.5F, 0.76F),
	EORAPTOR("eoraptor", EntityInit.EORAPTOR, true, true, true, Size.TINY, CreatureDiet.CARNIVORE, 0x523c3e, 0x523c3e, 0x824b78, 1, 3, 0.1F, 0.35F),
	EDAPHOSAURUS("edaphosaurus", EntityInit.EDAPHOSAURUS, true, false, true, Size.MEDIUM, CreatureDiet.HERBIVORE, 0x57614e, 0x57614e, 0xdf9046, 5, 7, 0.5F, 0.76F),
	FUKUIVENATOR("fukuivenator", EntityInit.FUKUIVENATOR, true, true, true, Size.SMALL, CreatureDiet.CARNIVORE, 0x52526f, 0x52526f, 0x5757959, 4, 5, 0.3F, 0.35F),
	GIGANOTOSAURUS("giganotosaurus", EntityInit.GIGANOTOSAURUS, true, false, true, Size.LARGE, CreatureDiet.CARNIVORE, 0x9f6b41, 0x9f6b41, 0x943c24, 9, 12, 0.5F, 0.8F),
	GORGONOPS("gorgonops", EntityInit.GORGONOPS, true, false, false, Size.SMALL, CreatureDiet.CARNIVORE, 0x443619, 0x443619, 0x3b3e2d, 3, 5, 0.3F, 0.46F),
	GREAT_AUK("great_auk", EntityInit.GREAT_AUK, true, true, false, Size.SMALL, CreatureDiet.PISCIVORE, 0x000000, 0x000000, 0xFFFFFF, 3, 5, 0.3F, 0.46F),
	KENTROSAURUS("kentrosaurus", EntityInit.KENTROSAURUS, true, false, true, Size.MEDIUM, CreatureDiet.HERBIVORE, 0xd99760, 0xd99760, 0x612c00, 3, 6, 0.4F, 0.66F),
	LIAONINGOSAURUS("liaoningosaurus", EntityInit.LIAONINGOSAURUS, true, false, true, Size.MEDIUM, CreatureDiet.HERBIVORE, 0x712d0d, 0x712d0d, 0x7c8237, 4, 7, 0.45F, 0.68F),
	NAUTILUS("nautilus", EntityInit.NAUTILUS, false, false, false, Size.SMALL, CreatureDiet.NONE, 0xd4ccc3, 0xd4ccc3, 0xca7548, 2, 5, 0.3F, 0.5F),
	OPHTHALMOSAURUS("ophthalmosaurus", EntityInit.OPHTHALMOSAURUS, false, false, false, Size.MEDIUM, CreatureDiet.PISCIVORE, 0x858794, 0x858794, 0x0e131b, 4, 7, 0.4F, 0.6F),
	OSTROMIA("ostromia", EntityInit.OSTROMIA, false, true, true, Size.TINY, CreatureDiet.CARNIVORE, 0x47a373, 0x47a373, 0x2c4d86, 2, 4, 0.2F, 0.3F),
	OURANOSAURUS("ouranosaurus", EntityInit.OURANOSAURUS, true, false, true, Size.MEDIUM, CreatureDiet.HERBIVORE, 0x999554, 0x999554, 0x90bdb4, 7, 10, 0.5F, 0.76F),
	PALAEONISCUM("palaeoniscum", EntityInit.PALAEONISCUM, false, false, false, Size.TINY, CreatureDiet.NONE, 0x72797a, 0x72797a, 0x2f3a3d, 2, 5, 0.3F, 0.5F),
	PROCOMPSOGNATHUS("procompsognathus", EntityInit.PROCOMPSOGNATHUS, false, true, true, Size.TINY, CreatureDiet.CARNIVORE, 0x445a2f, 0x445a2f, 0x404727, 1, 2, 0.1F, 0.2F),
	PROTOSUCHUS("protosuchus", EntityInit.PROTOSUCHUS, true, false, true, Size.SMALL, CreatureDiet.CARNIVORE, 0x8e2317, 0x8e2317, 0xb0492e, 3, 5, 0.3F, 0.54F),
	PSITTACOSAURUS("psittacosaurus", EntityInit.PSITTACOSAURUS, true, false, true, Size.SMALL, CreatureDiet.HERBIVORE, 0x4c2c21, 0x4c2c21, 0x938639, 3, 5, 0.3F, 0.54F),
	RHINESUCHUS("rhinesuchus", EntityInit.RHINESUCHUS, false, false, false, Size.SMALL, CreatureDiet.CARNIVORE, 0x576b54, 0x576b54, 0xaf944a, 3, 5, 0.3F, 0.54F),
	SUCHOMIMUS("suchomimus", EntityInit.SUCHOMIMUS, true, false, true, Size.MEDIUM, CreatureDiet.CARNIVORE, 0x57737b, 0x57737b, 0xcd9528, 5, 7, 0.5F, 0.64F),
	UTAHRAPTOR("utahraptor", EntityInit.UTAHRAPTOR, true, true, true, Size.MEDIUM, CreatureDiet.CARNIVORE, 0x503524, 0x503524, 0x635f5e, 5, 7, 0.5F, 0.64F),
	TETRACERATOPS("tetraceratops", EntityInit.TETRACERATOPS, true, false, true, Size.SMALL, CreatureDiet.HERBIVORE, 0x623015, 0x623015, 0x21369b, 3, 5, 0.3F, 0.54F),
	TYRANNOSAURUS("tyrannosaurus", EntityInit.TYRANNOSAURUS, true, false, true, Size.LARGE, CreatureDiet.CARNIVORE, 0x889a55, 0x889a55, 0x973229, 8, 11, 0.5F, 0.8F),
	ZEPHYROSAURUS("zephyrosaurus", EntityInit.ZEPHYROSAURUS, true, true, true, Size.SMALL, CreatureDiet.HERBIVORE, 0x577476, 0x577476, 0x9ba3a3, 3, 5, 0.3F, 0.54F),
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
	private Item feather;
	private Item spawn;
	private final boolean eggLaying;
	private final boolean feathered;
	private final boolean createHide;
	private final Size eggSize;
	private final CreatureDiet diet;
	private final int primaryColour;
	private final int eggSetColour;
	private final int secondaryColour;
	private final int rawNutrition;
	private final int cookedNutrition;
	private final float rawSaturation;
	private final float cookedSaturation;
	
	private DinoTypes(String id, EntityType entity, boolean eggLaying, boolean feathered, boolean createHide, Size eggSize, CreatureDiet diet, int eggSetColour, int primaryColour, int secondaryColour, int rawNutrition, int cookedNutrition, float rawSaturation, float cookedSaturation)
	{		
		this.id = id;
		this.entitytype = entity;
		this.eggLaying = eggLaying;
		this.feathered = feathered;
		this.createHide = createHide;
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
	
	public int getColour(int colour, int colour1, int colour2) 
	{
		return colour == 0 ? colour1 : colour2;
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
	
	public Item setFeather(Item feather)
	{
		return this.feather = feather;
	}
	
	public Item getFeather()
	{
		return this.feather;
	}
	
	public Item setSpawn(Item spawn)
	{
		return this.spawn = spawn;
	}
	
	public Item getSpawn()
	{
		return this.spawn;
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
	
	public static ArrayList<DinoTypes> hasSpawn()
	{
		ArrayList<DinoTypes> list = new ArrayList<>();
		list.add(ANOMALOCARIS);
		list.add(NAUTILUS);
		list.add(PALAEONISCUM);
		list.add(RHINESUCHUS);
		return list;
	}
	
	public static ArrayList<DinoTypes> liveBirth()
	{
		ArrayList<DinoTypes> list = new ArrayList<>();
		for(DinoTypes type : DinoTypes.values())
		{
			if(!type.eggLaying && !(type.hasSpawn().contains(type)))
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
	
	public static ArrayList<DinoTypes> feathered()
	{
		ArrayList<DinoTypes> list = new ArrayList<>();
		for(DinoTypes type : DinoTypes.values())
		{
			if(type.feathered)
			{
				list.add(type);
			}
		}
		return list;
	}
	
	public static ArrayList<DinoTypes> createHide()
	{
		ArrayList<DinoTypes> list = new ArrayList<>();
		for(DinoTypes type : DinoTypes.values())
		{
			if(type.createHide)
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
		list.add(ANOMALOCARIS);
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
