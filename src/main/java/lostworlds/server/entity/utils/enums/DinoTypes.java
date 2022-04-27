package lostworlds.server.entity.utils.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.mojang.serialization.Codec;

import lostworlds.client.entity.render.bone.SkeletonRenderer;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.block.LargeEggBlock;
import lostworlds.server.block.MediumEggBlock;
import lostworlds.server.block.SmallEggBlock;
import lostworlds.server.block.TinyEggBlock;
import lostworlds.server.entity.aquatic.cambrian.AnomalocarisEntity;
import lostworlds.server.entity.aquatic.jurassic.OphthalmosaurusEntity;
import lostworlds.server.entity.aquatic.modern.NautilusEntity;
import lostworlds.server.entity.aquatic.permian.PalaeoniscumEntity;
import lostworlds.server.entity.fossil.FossilEntity;
import lostworlds.server.entity.semiaquatic.cretaceous.SuchomimusEntity;
import lostworlds.server.entity.semiaquatic.modern.GreatAukEntity;
import lostworlds.server.entity.semiaquatic.permian.ProtosuchusEntity;
import lostworlds.server.entity.semiaquatic.permian.RhinesuchusEntity;
import lostworlds.server.entity.terrestrial.PrehistoricEntity;
import lostworlds.server.entity.terrestrial.cretaceous.CarnotaurusEntity;
import lostworlds.server.entity.terrestrial.cretaceous.FukuivenatorEntity;
import lostworlds.server.entity.terrestrial.cretaceous.GiganotosaurusEntity;
import lostworlds.server.entity.terrestrial.cretaceous.OuranosaurusEntity;
import lostworlds.server.entity.terrestrial.cretaceous.PsittacosaurusEntity;
import lostworlds.server.entity.terrestrial.cretaceous.TyrannosaurusEntity;
import lostworlds.server.entity.terrestrial.cretaceous.UtahraptorEntity;
import lostworlds.server.entity.terrestrial.cretaceous.ZephyrosaurusEntity;
import lostworlds.server.entity.terrestrial.jurassic.AllosaurusEntity;
import lostworlds.server.entity.terrestrial.jurassic.ChilesaurusEntity;
import lostworlds.server.entity.terrestrial.jurassic.CryolophosaurusEntity;
import lostworlds.server.entity.terrestrial.jurassic.DilophosaurusEntity;
import lostworlds.server.entity.terrestrial.jurassic.KentrosaurusEntity;
import lostworlds.server.entity.terrestrial.jurassic.LiaoningosaurusEntity;
import lostworlds.server.entity.terrestrial.jurassic.OstromiaEntity;
import lostworlds.server.entity.terrestrial.permian.DiictodonEntity;
import lostworlds.server.entity.terrestrial.permian.DimetrodonEntity;
import lostworlds.server.entity.terrestrial.permian.EdaphosaurusEntity;
import lostworlds.server.entity.terrestrial.permian.GorgonopsEntity;
import lostworlds.server.entity.terrestrial.permian.TetraceratopsEntity;
import lostworlds.server.entity.terrestrial.triassic.EoraptorEntity;
import lostworlds.server.entity.terrestrial.triassic.ProcompsognathusEntity;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IStringSerializable;

public enum DinoTypes implements IStringSerializable {
	ALLOSAURUS(0, "allosaurus", AllosaurusEntity.class, LostWorldsTags.ModItemTags.ALLOSAURUS_FOSSILS, true, false, true, Size.LARGE, CreatureDiet.CARNIVORE, 0x9f9f5a, 0x9f9f5a, 0xd68812, 5, 8, 0.4F, 0.8F),
	ANOMALOCARIS(1, "anomalocaris", AnomalocarisEntity.class, LostWorldsTags.ModItemTags.ANOMALOCARIS_FOSSILS, false, false, false, Size.SMALL, CreatureDiet.CARNIVORE, 0xb94f33, 0xb94f33, 0x631312, 2, 4, 0.2F, 0.4F),
	CARNOTAURUS(2, "carnotaurus", CarnotaurusEntity.class, LostWorldsTags.ModItemTags.CARNOTAURUS_FOSSILS, true, false, true, Size.LARGE, CreatureDiet.CARNIVORE, 0xbd7868, 0xbd7868, 0xe6d3bc, 6, 9, 0.4F, 0.8F),
	CHILESAURUS(3, "chilesaurus", ChilesaurusEntity.class, LostWorldsTags.ModItemTags.CHILESAURUS_FOSSILS, true, false, true, Size.SMALL, CreatureDiet.HERBIVORE, 0xb08533, 0xb08533, 0x283c3f, 1, 3, 0.25F, 0.56F),
	CRYOLOPHOSAURUS(4, "cryolophosaurus", CryolophosaurusEntity.class, LostWorldsTags.ModItemTags.CRYOLOPHOSAURUS_FOSSILS, true, false, true, Size.MEDIUM, CreatureDiet.CARNIVORE, 0xab5a14, 0xab5a14, 0x1a2c5f, 5, 7, 0.5F, 0.6F),
	DIICTODON(5, "diictodon", DiictodonEntity.class, LostWorldsTags.ModItemTags.DIICTODON_FOSSILS, false, false, false, Size.TINY, CreatureDiet.HERBIVORE, 0xdc8a54, 0xdc8a54, 0x8b462e, 1, 2, 0.1F, 0.2F),
	DILOPHOSAURUS(6, "dilophosaurus", DilophosaurusEntity.class, LostWorldsTags.ModItemTags.DILOPHOSAURUS_FOSSILS, true, false, true, Size.LARGE, CreatureDiet.CARNIVORE, 0xc49838, 0xc49838, 0xc75539, 5, 7, 0.5F, 0.76F),
	DIMETRODON(7, "dimetrodon", DimetrodonEntity.class, LostWorldsTags.ModItemTags.DIMETRODON_FOSSILS, true, false, true, Size.MEDIUM, CreatureDiet.CARNIVORE, 0x81644c, 0x81644c, 0xcba755, 5, 7, 0.5F, 0.76F),
	EDAPHOSAURUS(8, "edaphosaurus", EdaphosaurusEntity.class, LostWorldsTags.ModItemTags.EDAPHOSAURUS_FOSSILS, true, false, true, Size.MEDIUM, CreatureDiet.HERBIVORE, 0x57614e, 0x57614e, 0xdf9046, 5, 7, 0.5F, 0.76F),
	EORAPTOR(9, "eoraptor", EoraptorEntity.class, LostWorldsTags.ModItemTags.EORAPTOR_FOSSILS, true, true, true, Size.TINY, CreatureDiet.CARNIVORE, 0x523c3e, 0x523c3e, 0x824b78, 1, 3, 0.1F, 0.35F),
	FUKUIVENATOR(10, "fukuivenator", FukuivenatorEntity.class, LostWorldsTags.ModItemTags.FUKUIVENATOR_FOSSILS, true, true, true, Size.SMALL, CreatureDiet.CARNIVORE, 0x52526f, 0x52526f, 0x5757959, 4, 5, 0.3F, 0.35F),
	GIGANOTOSAURUS(11, "giganotosaurus", GiganotosaurusEntity.class, LostWorldsTags.ModItemTags.GIGANOTOSAURUS_FOSSILS, true, false, true, Size.LARGE, CreatureDiet.CARNIVORE, 0x9f6b41, 0x9f6b41, 0x943c24, 9, 12, 0.5F, 0.8F),
	GORGONOPS(12, "gorgonops", GorgonopsEntity.class, LostWorldsTags.ModItemTags.GORGONOPS_FOSSILS, true, false, false, Size.SMALL, CreatureDiet.CARNIVORE, 0x443619, 0x443619, 0x3b3e2d, 3, 5, 0.3F, 0.46F),
	GREAT_AUK(13, "great_auk", GreatAukEntity.class, LostWorldsTags.ModItemTags.GREAT_AUK_FOSSILS, true, true, false, Size.SMALL, CreatureDiet.PISCIVORE, 0x000000, 0x000000, 0xFFFFFF, 3, 5, 0.3F, 0.46F),
	KENTROSAURUS(14, "kentrosaurus", KentrosaurusEntity.class, LostWorldsTags.ModItemTags.KENTROSAURUS_FOSSILS, true, false, true, Size.MEDIUM, CreatureDiet.HERBIVORE, 0xd99760, 0xd99760, 0x612c00, 3, 6, 0.4F, 0.66F),
	LIAONINGOSAURUS(15, "liaoningosaurus", LiaoningosaurusEntity.class, LostWorldsTags.ModItemTags.LIAONINGOSAURUS_FOSSILS, true, false, true, Size.MEDIUM, CreatureDiet.HERBIVORE, 0x712d0d, 0x712d0d, 0x7c8237, 4, 7, 0.45F, 0.68F),
	NAUTILUS(16, "nautilus", NautilusEntity.class, LostWorldsTags.ModItemTags.NAUTILUS_FOSSILS, false, false, false, Size.SMALL, CreatureDiet.NONE, 0xd4ccc3, 0xd4ccc3, 0xca7548, 2, 5, 0.3F, 0.5F),
	OPHTHALMOSAURUS(17, "ophthalmosaurus", OphthalmosaurusEntity.class, LostWorldsTags.ModItemTags.OPHTHALMOSAURUS_FOSSILS, false, false, false, Size.MEDIUM, CreatureDiet.PISCIVORE, 0x858794, 0x858794, 0x0e131b, 4, 7, 0.4F, 0.6F),
	OSTROMIA(18, "ostromia", OstromiaEntity.class, LostWorldsTags.ModItemTags.OSTROMIA_FOSSILS, true, true, true, Size.SMALL, CreatureDiet.CARNIVORE, 0x47a373, 0x47a373, 0x2c4d86, 2, 4, 0.2F, 0.3F),
	OURANOSAURUS(19, "ouranosaurus", OuranosaurusEntity.class, LostWorldsTags.ModItemTags.OURANOSAURUS_FOSSILS, true, false, true, Size.MEDIUM, CreatureDiet.HERBIVORE, 0x999554, 0x999554, 0x90bdb4, 7, 10, 0.5F, 0.76F),
	PALAEONISCUM(20, "palaeoniscum", PalaeoniscumEntity.class, LostWorldsTags.ModItemTags.PALAEONISCUM_FOSSILS, false, false, false, Size.TINY, CreatureDiet.NONE, 0x72797a, 0x72797a, 0x2f3a3d, 2, 5, 0.3F, 0.5F),
	PROCOMPSOGNATHUS(21, "procompsognathus", ProcompsognathusEntity.class, LostWorldsTags.ModItemTags.PROCOMPSOGNATHUS_FOSSILS, true, true, true, Size.TINY, CreatureDiet.CARNIVORE, 0x445a2f, 0x445a2f, 0x404727, 1, 2, 0.1F, 0.2F),
	PROTOSUCHUS(22, "protosuchus", ProtosuchusEntity.class, LostWorldsTags.ModItemTags.PROTOSUCHUS_FOSSILS, true, false, true, Size.SMALL, CreatureDiet.CARNIVORE, 0x8e2317, 0x8e2317, 0xb0492e, 3, 5, 0.3F, 0.54F),
	PSITTACOSAURUS(23, "psittacosaurus", PsittacosaurusEntity.class, LostWorldsTags.ModItemTags.PSITTACOSAURUS_FOSSILS, true, false, true, Size.SMALL, CreatureDiet.HERBIVORE, 0x4c2c21, 0x4c2c21, 0x938639, 3, 5, 0.3F, 0.54F),
	RHINESUCHUS(24, "rhinesuchus", RhinesuchusEntity.class, LostWorldsTags.ModItemTags.RHINESUCHUS_FOSSILS, false, false, false, Size.SMALL, CreatureDiet.CARNIVORE, 0x576b54, 0x576b54, 0xaf944a, 3, 5, 0.3F, 0.54F),
	SUCHOMIMUS(25, "suchomimus", SuchomimusEntity.class, LostWorldsTags.ModItemTags.SUCHOMIMUS_FOSSILS, true, false, true, Size.MEDIUM, CreatureDiet.CARNIVORE, 0x57737b, 0x57737b, 0xcd9528, 5, 7, 0.5F, 0.64F),
	TETRACERATOPS(26, "tetraceratops", TetraceratopsEntity.class, LostWorldsTags.ModItemTags.TETRACERATOPS_FOSSILS, true, false, true, Size.SMALL, CreatureDiet.HERBIVORE, 0x623015, 0x623015, 0x21369b, 3, 5, 0.3F, 0.54F),
	TYRANNOSAURUS(27, "tyrannosaurus", TyrannosaurusEntity.class, LostWorldsTags.ModItemTags.TYRANNOSAURUS_FOSSILS, true, false, true, Size.LARGE, CreatureDiet.CARNIVORE, 0x889a55, 0x889a55, 0x973229, 8, 11, 0.5F, 0.8F),
	UTAHRAPTOR(28, "utahraptor", UtahraptorEntity.class, LostWorldsTags.ModItemTags.UTAHRAPTOR_FOSSILS, true, true, true, Size.MEDIUM, CreatureDiet.CARNIVORE, 0x503524, 0x503524, 0x635f5e, 5, 7, 0.5F, 0.64F),
	ZEPHYROSAURUS(29, "zephyrosaurus", ZephyrosaurusEntity.class, LostWorldsTags.ModItemTags.ZEPHYROSAURUS_FOSSILS, true, true, true, Size.SMALL, CreatureDiet.HERBIVORE, 0x577476, 0x577476, 0x9ba3a3, 3, 5, 0.3F, 0.54F),;

	public static final Codec<DinoTypes> CODEC = IStringSerializable.fromEnum(DinoTypes::values, DinoTypes::byName);
	private static final Map<String, DinoTypes> BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(DinoTypes::getId, (types) -> {
		return types;
	}));
	public final DinoTypes[] dinoTypes = new DinoTypes[256];

	private final String id;
	private Supplier<EntityType<? extends PrehistoricEntity>> entityType;
	private Class<? extends Entity> entity;
	private ITag.INamedTag<Item> fossilTag;
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
	private EntityType<FossilEntity> dirtyExoskeleton;
	private EntityType<FossilEntity> exoskeleton;
	private EntityType<FossilEntity> dirtyBody;
	private EntityType<FossilEntity> body;
	private Supplier<Block> egg;
	private Supplier<Block> extraBlock;
	private Supplier<Item> plasteredSkullItem;
	private Supplier<Item> plasteredArmBonesItem;
	private Supplier<Item> plasteredLegBonesItem;
	private Supplier<Item> plasteredRibCageItem;
	private Supplier<Item> plasteredTailItem;
	private Supplier<Item> skullItem;
	private Supplier<Item> armBonesItem;
	private Supplier<Item> legBonesItem;
	private Supplier<Item> ribCageItem;
	private Supplier<Item> tailItem;
	private Supplier<Item> skeletonPick;
	private Supplier<Item> softTissue;
	private Supplier<Item> dna;
	private Supplier<Item> dnaDisc;
	private Supplier<Item> bloodSyringe;
	private Supplier<Item> bloodVile;
	private Supplier<Item> meat;
	private Supplier<Item> fishBucket;
	private Supplier<Item> feather;
	private Supplier<Item> spawn;
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

	private DinoTypes(int internalId, String id, Class<? extends Entity> entity, ITag.INamedTag<Item> fossilTag, boolean eggLaying, boolean feathered, boolean createHide, Size eggSize, CreatureDiet diet, int eggSetColour, int primaryColour, int secondaryColour, int rawNutrition, int cookedNutrition, float rawSaturation, float cookedSaturation) {
		this.id = id;
		this.entity = entity;
		this.fossilTag = fossilTag;
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

		if (this.dinoTypes[internalId] != null) {
			throw new IllegalArgumentException("Slot " + internalId + " is already occupied by " + dinoTypes[internalId] + " when adding " + this);
		} else {
			this.dinoTypes[internalId] = this;
		}
	}

	public DinoTypes getViaNumberId(int id) {
		return this.dinoTypes[id];
	}

	public int getColour(int colour, int colour1, int colour2) {
		return colour == 0 ? colour1 : colour2;
	}

	public String getId() {
		return this.id;
	}

	public ITag.INamedTag<Item> getFossilTag() {
		return fossilTag;
	}

	public EntityType<FossilEntity> setDirtySkull(EntityType<FossilEntity> entity) {
		return this.dirtySkull = entity;
	}

	public EntityType<FossilEntity> setDirtyArmBones(EntityType<FossilEntity> entity) {
		return this.dirtyArmBones = entity;
	}

	public EntityType<FossilEntity> setDirtyLegBones(EntityType<FossilEntity> entity) {
		return this.dirtyLegBones = entity;
	}

	public EntityType<FossilEntity> setDirtyRibCage(EntityType<FossilEntity> entity) {
		return this.dirtyRibCage = entity;
	}

	public EntityType<FossilEntity> setDirtyTail(EntityType<FossilEntity> entity) {
		return this.dirtyTail = entity;
	}

	public EntityType<FossilEntity> setSkull(EntityType<FossilEntity> entity) {
		return this.skull = entity;
	}

	public EntityType<FossilEntity> setArmBones(EntityType<FossilEntity> entity) {
		return this.armBones = entity;
	}

	public EntityType<FossilEntity> setLegBones(EntityType<FossilEntity> entity) {
		return this.legBones = entity;
	}

	public EntityType<FossilEntity> setRibCage(EntityType<FossilEntity> entity) {
		return this.ribCage = entity;
	}

	public EntityType<FossilEntity> setTail(EntityType<FossilEntity> entity) {
		return this.tail = entity;
	}

	public EntityType<FossilEntity> setSkeleton(EntityType<FossilEntity> entity) {
		return this.skeleton = entity;
	}

	public EntityType<FossilEntity> setDirtyExoskeleton(EntityType<FossilEntity> entity) {
		return this.dirtyExoskeleton = entity;
	}

	public EntityType<FossilEntity> setExoskeleton(EntityType<FossilEntity> entity) {
		return this.exoskeleton = entity;
	}

	public EntityType<FossilEntity> setDirtyBody(EntityType<FossilEntity> entity) {
		return this.dirtyBody = entity;
	}

	public EntityType<FossilEntity> setBody(EntityType<FossilEntity> entity) {
		return this.body = entity;
	}

	public Supplier<Item> setSkeletonPick(Supplier<Item> item) {
		return this.skeletonPick = item;
	}

	public Supplier<EntityType<? extends PrehistoricEntity>> setEntityType(Supplier<EntityType<? extends PrehistoricEntity>> entityType) {
		return this.entityType = entityType;
	}

	public Class<? extends Entity> getEntity() {
		return entity;
	}

	public EntityType<FossilEntity> getDirtySkull() {
		return this.dirtySkull;
	}

	public EntityType<FossilEntity> getDirtyArmBones() {
		return this.dirtyArmBones;
	}

	public EntityType<FossilEntity> getDirtyLegBones() {
		return this.dirtyLegBones;
	}

	public EntityType<FossilEntity> getDirtyRibCage() {
		return this.dirtyRibCage;
	}

	public EntityType<FossilEntity> getDirtyTail() {
		return this.dirtyTail;
	}

	public EntityType<FossilEntity> getSkull() {
		return this.skull;
	}

	public EntityType<FossilEntity> getArmBones() {
		return this.armBones;
	}

	public EntityType<FossilEntity> getLegBones() {
		return this.legBones;
	}

	public EntityType<FossilEntity> getRibCage() {
		return this.ribCage;
	}

	public EntityType<FossilEntity> getTail() {
		return this.tail;
	}

	public EntityType<FossilEntity> getSkeleton() {
		return this.skeleton;
	}

	public EntityType<FossilEntity> getDirtyExoskeleton() {
		return this.dirtyExoskeleton;
	}

	public EntityType<FossilEntity> getExoskeleton() {
		return this.exoskeleton;
	}

	public EntityType<FossilEntity> getDirtyBody() {
		return this.dirtyBody;
	}

	public EntityType<FossilEntity> getBody() {
		return this.body;
	}

	public Supplier<Item> setMeat(Supplier<Item> meat) {
		return this.meat = meat;
	}

	public Supplier<Item> getMeat() {
		return this.meat;
	}

	public Supplier<Item> setFishBucket(Supplier<Item> fishBucket) {
		return this.fishBucket = fishBucket;
	}

	public Supplier<Item> getFishBucket() {
		return this.fishBucket;
	}

	public Supplier<Item> setFeather(Supplier<Item> feather) {
		return this.feather = feather;
	}

	public Supplier<Item> getFeather() {
		return this.feather;
	}

	public Supplier<Item> setSpawn(Supplier<Item> spawn) {
		return this.spawn = spawn;
	}

	public Supplier<Item> getSpawn() {
		return this.spawn;
	}

	public Supplier<Block> setExtraBlock(Supplier<Block> extraBlock) {
		return this.extraBlock = extraBlock;
	}

	public Supplier<Block> getExtraBlock() {
		return this.extraBlock;
	}

	public Supplier<Block> setEgg(Supplier<Block> egg) {
		return this.egg = egg;
	}

	public Supplier<Block> getEgg() {
		return this.egg;
	}

	public Supplier<EntityType<? extends PrehistoricEntity>> getEntityType() {
		return this.entityType;
	}

	public Block getEgg(EntityType<? extends PrehistoricEntity> entity) {
		Properties abstractProperties = Properties.of(Material.EGG).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion();

		switch (this.eggSize) {
		case TINY:
			Block tinyegg = new TinyEggBlock(abstractProperties, () -> entity);
			this.egg = () -> tinyegg;
			return tinyegg;
		case SMALL:
			Block smallegg = new SmallEggBlock(abstractProperties, () -> entity);
			this.egg = () -> smallegg;
			return smallegg;
		case MEDIUM:
			Block mediumegg = new MediumEggBlock(abstractProperties, () -> entity);
			this.egg = () -> mediumegg;
			return mediumegg;
		case LARGE:
			Block largeegg = new LargeEggBlock(abstractProperties, () -> entity);
			this.egg = () -> largeegg;
			return largeegg;
		case NONE:
		default:
			return null;
		}
	}

	public CreatureDiet getDiet() {
		return this.diet;
	}

	public Supplier<Item> getSkeletonPick() {
		return this.skeletonPick;
	}

	public Supplier<Item> getPlasteredSkullItem() {
		return this.plasteredSkullItem;
	}

	public Supplier<Item> setPlasteredSkullItem(Supplier<Item> item) {
		return this.plasteredSkullItem = item;
	}

	public Supplier<Item> getPlasteredArmBonesItem() {
		return this.plasteredArmBonesItem;
	}

	public Supplier<Item> setPlasteredArmBonesItem(Supplier<Item> item) {
		return this.plasteredArmBonesItem = item;
	}

	public Supplier<Item> getPlasteredLegBonesItem() {
		return this.plasteredLegBonesItem;
	}

	public Supplier<Item> setPlasteredLegBonesItem(Supplier<Item> item) {
		return this.plasteredLegBonesItem = item;
	}

	public Supplier<Item> getPlasteredRibCageBonesItem() {
		return this.plasteredRibCageItem;
	}

	public Supplier<Item> setPlasteredRibCageItem(Supplier<Item> item) {
		return this.plasteredRibCageItem = item;
	}

	public Supplier<Item> getPlasteredTailBonesItem() {
		return this.plasteredTailItem;
	}

	public Supplier<Item> setPlasteredTailItem(Supplier<Item> item) {
		return this.plasteredTailItem = item;
	}

	public Supplier<Item> getSkullItem() {
		return this.skullItem;
	}

	public Supplier<Item> setSkullItem(Supplier<Item> item) {
		return this.skullItem = item;
	}

	public Supplier<Item> getArmBonesItem() {
		return this.armBonesItem;
	}

	public Supplier<Item> setArmBonesItem(Supplier<Item> item) {
		return this.armBonesItem = item;
	}

	public Supplier<Item> getLegBonesItem() {
		return this.legBonesItem;
	}

	public Supplier<Item> setLegBonesItem(Supplier<Item> item) {
		return this.legBonesItem = item;
	}

	public Supplier<Item> getRibCageItem() {
		return this.ribCageItem;
	}

	public Supplier<Item> setRibCageItem(Supplier<Item> item) {
		return this.ribCageItem = item;
	}

	public Supplier<Item> getTailItem() {
		return this.tailItem;
	}

	public Supplier<Item> setTailItem(Supplier<Item> item) {
		return this.tailItem = item;
	}

	public Supplier<Item> getSoftTissue() {
		return this.softTissue;
	}

	public Supplier<Item> setSoftTissue(Supplier<Item> item) {
		return this.softTissue = item;
	}

	public Supplier<Item> getDNA() {
		return this.dna;
	}

	public Supplier<Item> setDNA(Supplier<Item> item) {
		return this.dna = item;
	}

	public Supplier<Item> getDNADisc() {
		return this.dnaDisc;
	}

	public Supplier<Item> setDNADisc(Supplier<Item> item) {
		return this.dnaDisc = item;
	}

	public Supplier<Item> getBloodSyringe() {
		return this.bloodSyringe;
	}

	public Supplier<Item> setBloodSyringe(Supplier<Item> item) {
		return this.bloodSyringe = item;
	}

	public Supplier<Item> getBloodVile() {
		return this.bloodVile;
	}

	public Supplier<Item> setBloodVile(Supplier<Item> item) {
		return this.bloodVile = item;
	}

	public Callable<ItemStackTileEntityRenderer> getISTER() {
		return new SkeletonRenderer(this.id);
	}

	public Callable<ItemStackTileEntityRenderer> getISTER(String part) {
		return new SkeletonRenderer(this.id + "_" + part, this.id);
	}

	public int getSetEggColour() {
		return this.eggSetColour;
	}

	public int getPrimaryColour() {
		return this.primaryColour;
	}

	public int getSecondaryColour() {
		return this.secondaryColour;
	}

	public int getRawNutrition() {
		return this.rawNutrition;
	}

	public int getCookedNutrition() {
		return this.cookedNutrition;
	}

	public float getRawSaturation() {
		return this.rawSaturation;
	}

	public float getCookedSaturation() {
		return this.cookedSaturation;
	}

	public static ArrayList<DinoTypes> hasSpawn() {
		ArrayList<DinoTypes> list = new ArrayList<>();
		list.add(ANOMALOCARIS);
		list.add(NAUTILUS);
		list.add(PALAEONISCUM);
		list.add(RHINESUCHUS);
		return list;
	}

	public static ArrayList<DinoTypes> liveBirth() {
		ArrayList<DinoTypes> list = new ArrayList<>();
		for (DinoTypes type : DinoTypes.values()) {
			if (!type.eggLaying && !(type.hasSpawn().contains(type))) {
				list.add(type);
			}
		}
		return list;
	}

	public static ArrayList<DinoTypes> eggLaying() {
		ArrayList<DinoTypes> list = new ArrayList<>();
		for (DinoTypes type : DinoTypes.values()) {
			if (type.eggLaying) {
				list.add(type);
			}
		}
		return list;
	}

	public static ArrayList<DinoTypes> feathered() {
		ArrayList<DinoTypes> list = new ArrayList<>();
		for (DinoTypes type : DinoTypes.values()) {
			if (type.feathered) {
				list.add(type);
			}
		}
		return list;
	}

	public static ArrayList<DinoTypes> createHide() {
		ArrayList<DinoTypes> list = new ArrayList<>();
		for (DinoTypes type : DinoTypes.values()) {
			if (type.createHide) {
				list.add(type);
			}
		}
		return list;
	}

	public static ArrayList<DinoTypes> fish() {
		ArrayList<DinoTypes> list = new ArrayList<>();
		list.add(PALAEONISCUM);
		list.add(ANOMALOCARIS);
		return list;
	}

	@Nullable
	public static DinoTypes byName(String id) {
		return BY_NAME.get(id);
	}

	@Override
	public String getSerializedName() {
		return this.id;
	}
}
