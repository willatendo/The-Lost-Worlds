package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.entity.DinoTypes;
import lostworlds.library.entity.ModBoatEntity;
import lostworlds.library.entity.aquatic.jurassic.OphthalmosaurusEntity;
import lostworlds.library.entity.aquatic.modern.NautilusEntity;
import lostworlds.library.entity.aquatic.permian.PalaeoniscumEntity;
import lostworlds.library.entity.fossil.DirtyFossilEntity;
import lostworlds.library.entity.fossil.FossilEntity;
import lostworlds.library.entity.illager.FossilPoacherEntity;
import lostworlds.library.entity.item.ChargedCrystalScarabGemItemEntity;
import lostworlds.library.entity.item.CrystalScarabGemItemEntity;
import lostworlds.library.entity.semiaquatic.modern.GreatAukEntity;
import lostworlds.library.entity.terrestrial.PrehistoricEntity;
import lostworlds.library.entity.terrestrial.cretaceous.PsittacosaurusEntity;
import lostworlds.library.entity.terrestrial.cretaceous.ZephyrosaurusEntity;
import lostworlds.library.entity.terrestrial.jurassic.ChilesaurusEntity;
import lostworlds.library.entity.terrestrial.jurassic.DilophosaurusEntity;
import lostworlds.library.entity.terrestrial.jurassic.KentrosaurusEntity;
import lostworlds.library.entity.terrestrial.jurassic.LiaoningosaurusEntity;
import lostworlds.library.entity.terrestrial.permian.DiictodonEntity;
import lostworlds.library.entity.terrestrial.permian.TetraceratopsEntity;
import lostworlds.library.item.ModSpawnEggItem;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD)
public class EntityInit 
{	
	public static final EntityClassification SEMI_AQUATIC_CREATURE = EntityClassification.create("semi_aquatic_creature", "semi_aquatic_creature", 10, true, true, 128);
	
	public static final EntityType<FossilPoacherEntity> FOSSIL_POACHER = ModRegistry.register("fossil_poacher", FossilPoacherEntity::new, EntityClassification.MONSTER, 0.6F, 1.95F);
	
	public static final EntityType<ModBoatEntity> MOD_BOAT = ModRegistry.register("mod_boat", ModBoatEntity::new, EntityClassification.MISC, 1.375F, 0.5625F);
	public static final EntityType<ChargedCrystalScarabGemItemEntity> CHARGED_CRYSTAL_SCARAB_GEM_ITEM = ModRegistry.register("charged_crystal_scarab_gem_item", ChargedCrystalScarabGemItemEntity::new, EntityClassification.MISC, 0.5F, 0.5F);
	public static final EntityType<CrystalScarabGemItemEntity> CRYSTAL_SCARAB_GEM_ITEM = ModRegistry.register("crystal_scarab_gem_item", CrystalScarabGemItemEntity::new, EntityClassification.MISC, 0.5F, 0.5F);
	
	public static final EntityType<ChilesaurusEntity> CHILESAURUS = ModRegistry.register("chilesaurus", ChilesaurusEntity::new, EntityClassification.CREATURE, 1.0F, 1.0F);
	public static final EntityType<FossilEntity> CHILESAURUS_SKELETON = ModRegistry.register("chilesaurus_skeleton", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
	public static final EntityType<DiictodonEntity> DIICTODON = ModRegistry.register("diictodon", DiictodonEntity::new, EntityClassification.CREATURE, 0.25F, 0.25F);
	public static final EntityType<DilophosaurusEntity> DILOPHOSAURUS = ModRegistry.register("dilophosaurus", DilophosaurusEntity::new, EntityClassification.CREATURE, 1.5F, 1.5F);
	public static final EntityType<GreatAukEntity> GREAT_AUK = ModRegistry.register("great_auk", GreatAukEntity::new, SEMI_AQUATIC_CREATURE, 0.75F, 1.5F);
	public static final EntityType<KentrosaurusEntity> KENTROSAURUS = ModRegistry.register("kentrosaurus", KentrosaurusEntity::new, EntityClassification.CREATURE, 1.0F, 0.75F);
	public static final EntityType<FossilEntity> KENTROSAURUS_SKELETON = ModRegistry.register("kentrosaurus_skeleton", FossilEntity::new, EntityClassification.MISC, 1.0F, 0.75F);
	public static final EntityType<LiaoningosaurusEntity> LIAONINGOSAURUS = ModRegistry.register("liaoningosaurus", LiaoningosaurusEntity::new, EntityClassification.CREATURE, 1.0F, 0.75F);
	public static final EntityType<NautilusEntity> NAUTILUS = ModRegistry.register("nautilus", NautilusEntity::new, EntityClassification.WATER_CREATURE, 0.5F, 0.5F);
	public static final EntityType<OphthalmosaurusEntity> OPHTHALMOSAURUS = ModRegistry.register("ophthalmosaurus", OphthalmosaurusEntity::new, EntityClassification.WATER_CREATURE, 2.0F, 2.0F);
	public static final EntityType<PalaeoniscumEntity> PALAEONISCUM = ModRegistry.register("palaeoniscum", PalaeoniscumEntity::new, EntityClassification.WATER_CREATURE, 0.5F, 0.5F);
	public static final EntityType<PsittacosaurusEntity> PSITTACOSAURUS = ModRegistry.register("psittacosaurus", PsittacosaurusEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
	public static final EntityType<TetraceratopsEntity> TETRACERATOPS = ModRegistry.register("tetraceratops", TetraceratopsEntity::new, EntityClassification.CREATURE, 0.75F, 0.5F);
	public static final EntityType<ZephyrosaurusEntity> ZEPHYROSAURUS = ModRegistry.register("zephyrosaurus", ZephyrosaurusEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) 
	{	
		ModSpawnEggItem.initSpawnEggs();
	}
	
	@SubscribeEvent
	public static void giveAttributes(EntityAttributeCreationEvent event)
	{
		for(DinoTypes dinos : DinoTypes.values())
		{
			event.put(dinos.getDirtyArmBones(), FossilEntity.createAttributes());
			event.put(dinos.getDirtyLegBones(), FossilEntity.createAttributes());
			event.put(dinos.getDirtyRibCage(), FossilEntity.createAttributes());
			event.put(dinos.getDirtySkull(),FossilEntity.createAttributes());
			event.put(dinos.getDirtyTail(), FossilEntity.createAttributes());
			event.put(dinos.getArmBones(), FossilEntity.createAttributes());
			event.put(dinos.getLegBones(), FossilEntity.createAttributes());
			event.put(dinos.getRibCage(), FossilEntity.createAttributes());
			event.put(dinos.getSkull(),FossilEntity.createAttributes());
			event.put(dinos.getTail(), FossilEntity.createAttributes());
		}
		
		
		event.put(FOSSIL_POACHER, FossilPoacherEntity.createAttributes());
		event.put(CHILESAURUS, ChilesaurusEntity.createAttributes());
		event.put(CHILESAURUS_SKELETON, FossilEntity.createAttributes());
		event.put(DIICTODON, DiictodonEntity.createAttributes());
		event.put(DILOPHOSAURUS, DilophosaurusEntity.createAttributes());
		event.put(GREAT_AUK, GreatAukEntity.createAttributes());
		event.put(KENTROSAURUS, KentrosaurusEntity.createAttributes());
		event.put(KENTROSAURUS_SKELETON, FossilEntity.createAttributes());
		event.put(LIAONINGOSAURUS, LiaoningosaurusEntity.createAttributes());
		event.put(NAUTILUS, NautilusEntity.createAttributes());
		event.put(OPHTHALMOSAURUS, OphthalmosaurusEntity.createAttributes());
		event.put(PALAEONISCUM, PalaeoniscumEntity.createBasicAttributes());
		event.put(PSITTACOSAURUS, PsittacosaurusEntity.createAttributes());
		event.put(TETRACERATOPS, TetraceratopsEntity.createAttributes());
		event.put(ZEPHYROSAURUS, ZephyrosaurusEntity.createAttributes());
	}
	
	static
	{
		EntitySpawnPlacementRegistry.register(EntityInit.CHILESAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(EntityInit.DIICTODON, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(EntityInit.DILOPHOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(EntityInit.GREAT_AUK, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GreatAukEntity::checkSpawnRules);
		EntitySpawnPlacementRegistry.register(EntityInit.KENTROSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(EntityInit.LIAONINGOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(EntityInit.NAUTILUS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, NautilusEntity::spawnRules);
		EntitySpawnPlacementRegistry.register(EntityInit.OPHTHALMOSAURUS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, OphthalmosaurusEntity::checkBasicSpawnRules);
		EntitySpawnPlacementRegistry.register(EntityInit.PALAEONISCUM, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, PalaeoniscumEntity::spawnRules);
		EntitySpawnPlacementRegistry.register(EntityInit.PSITTACOSAURUS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(EntityInit.TETRACERATOPS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(EntityInit.ZEPHYROSAURUS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, PrehistoricEntity::canPrehistoricSpawn);
	}
	
	public static void init() 
	{
		ModUtils.LOGGER.debug("Registering Mod Enitites"); 
		
		for(DinoTypes dinoType : DinoTypes.values())
		{
			EntityType<FossilEntity> dirtyskull = ModRegistry.register("dirty_" + dinoType.toString().toLowerCase() + "_skull", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setDirtySkull(dirtyskull);
			EntityType<FossilEntity> dirtyarmbones = ModRegistry.register("dirty_" + dinoType.toString().toLowerCase() + "_arm_bones", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setDirtyArmBones(dirtyarmbones);
			EntityType<FossilEntity> dirtylegbones = ModRegistry.register("dirty_" + dinoType.toString().toLowerCase() + "_leg_bones", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setDirtyLegBones(dirtylegbones);
			EntityType<FossilEntity> dirtyribcage = ModRegistry.register("dirty_" + dinoType.toString().toLowerCase() + "_rib_cage", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setDirtyRibCage(dirtyribcage);
			EntityType<FossilEntity> dirtytail = ModRegistry.register("dirty_" + dinoType.toString().toLowerCase() + "_tail", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setDirtyTail(dirtytail);
			EntityType<FossilEntity> skull = ModRegistry.register(dinoType.toString().toLowerCase() + "_skull", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setSkull(skull);
			EntityType<FossilEntity> arm_bones = ModRegistry.register(dinoType.toString().toLowerCase() + "_arm_bones", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setArmBones(arm_bones);
			EntityType<FossilEntity> leg_bones = ModRegistry.register(dinoType.toString().toLowerCase() + "_leg_bones", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setLegBones(leg_bones);
			EntityType<FossilEntity> rib_cage = ModRegistry.register(dinoType.toString().toLowerCase() + "_rib_cage", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setRibCage(rib_cage);
			EntityType<FossilEntity> tail = ModRegistry.register(dinoType.toString().toLowerCase() + "_tail", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setTail(tail);
			if(dinoType == DinoTypes.KENTROSAURUS)
			{
				dinoType.setSkeleton(KENTROSAURUS_SKELETON);
			}
			if(dinoType == DinoTypes.CHILESAURUS)
			{
				dinoType.setSkeleton(CHILESAURUS_SKELETON);
			}
			else
			{
				dinoType.setSkeleton(KENTROSAURUS_SKELETON);
			}
		}
	}
}
