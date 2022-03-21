package lostworlds.server.entity;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.aquatic.cambrian.AnomalocarisEntity;
import lostworlds.server.entity.aquatic.jurassic.OphthalmosaurusEntity;
import lostworlds.server.entity.aquatic.modern.NautilusEntity;
import lostworlds.server.entity.aquatic.permian.PalaeoniscumEntity;
import lostworlds.server.entity.fossil.DirtyFossilEntity;
import lostworlds.server.entity.fossil.FossilEntity;
import lostworlds.server.entity.illager.FossilPoacherEntity;
import lostworlds.server.entity.item.ChargedCrystalScarabGemItemEntity;
import lostworlds.server.entity.item.CrystalScarabGemItemEntity;
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
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.item.ModSpawnEggItem;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
public class LostWorldsEntities {
	public static final EntityClassification SEMI_AQUATIC_CREATURE = EntityClassification.create("semi_aquatic_creature", "semi_aquatic_creature", 10, true, true, 128);

	public static final EntityType<FossilPoacherEntity> FOSSIL_POACHER = LostWorldsRegistry.register("fossil_poacher", FossilPoacherEntity::new, EntityClassification.MONSTER, 0.6F, 1.95F);

	public static final EntityType<ModBoatEntity> MOD_BOAT = LostWorldsRegistry.register("mod_boat", ModBoatEntity::new, EntityClassification.MISC, 1.375F, 0.5625F);
	public static final EntityType<ChargedCrystalScarabGemItemEntity> CHARGED_CRYSTAL_SCARAB_GEM_ITEM = LostWorldsRegistry.register("charged_crystal_scarab_gem_item", ChargedCrystalScarabGemItemEntity::new, EntityClassification.MISC, 0.5F, 0.5F);
	public static final EntityType<CrystalScarabGemItemEntity> CRYSTAL_SCARAB_GEM_ITEM = LostWorldsRegistry.register("crystal_scarab_gem_item", CrystalScarabGemItemEntity::new, EntityClassification.MISC, 0.5F, 0.5F);

	public static final EntityType<AllosaurusEntity> ALLOSAURUS = LostWorldsRegistry.register("allosaurus", AllosaurusEntity::new, EntityClassification.CREATURE, 2.0F, 1.5F);
	public static final EntityType<AnomalocarisEntity> ANOMALOCARIS = LostWorldsRegistry.register("anomalocaris", AnomalocarisEntity::new, EntityClassification.WATER_CREATURE, 1.0F, 1.0F);
	public static final EntityType<CarnotaurusEntity> CARNOTAURUS = LostWorldsRegistry.register("carnotaurus", CarnotaurusEntity::new, EntityClassification.CREATURE, 2.0F, 1.5F);
	public static final EntityType<ChilesaurusEntity> CHILESAURUS = LostWorldsRegistry.register("chilesaurus", ChilesaurusEntity::new, EntityClassification.CREATURE, 1.0F, 1.0F);
	public static final EntityType<FossilEntity> CHILESAURUS_SKELETON = LostWorldsRegistry.register("chilesaurus_skeleton", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
	public static final EntityType<CryolophosaurusEntity> CRYOLOPHOSAURUS = LostWorldsRegistry.register("cryolophosaurus", CryolophosaurusEntity::new, EntityClassification.CREATURE, 1.0F, 0.75F);
	public static final EntityType<DiictodonEntity> DIICTODON = LostWorldsRegistry.register("diictodon", DiictodonEntity::new, EntityClassification.CREATURE, 0.25F, 0.25F);
	public static final EntityType<DilophosaurusEntity> DILOPHOSAURUS = LostWorldsRegistry.register("dilophosaurus", DilophosaurusEntity::new, EntityClassification.CREATURE, 1.5F, 1.5F);
	public static final EntityType<DimetrodonEntity> DIMETRODON = LostWorldsRegistry.register("dimetrodon", DimetrodonEntity::new, EntityClassification.CREATURE, 1.5F, 1.5F);
	public static final EntityType<EdaphosaurusEntity> EDAPHOSAURUS = LostWorldsRegistry.register("edaphosaurus", EdaphosaurusEntity::new, EntityClassification.CREATURE, 1.0F, 1.0F);
	public static final EntityType<EoraptorEntity> EORAPTOR = LostWorldsRegistry.register("eoraptor", EoraptorEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
	public static final EntityType<FukuivenatorEntity> FUKUIVENATOR = LostWorldsRegistry.register("fukuivenator", FukuivenatorEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
	public static final EntityType<GiganotosaurusEntity> GIGANOTOSAURUS = LostWorldsRegistry.register("giganotosaurus", GiganotosaurusEntity::new, EntityClassification.CREATURE, 4.0F, 2.5F);
	public static final EntityType<GorgonopsEntity> GORGONOPS = LostWorldsRegistry.register("gorgonops", GorgonopsEntity::new, EntityClassification.CREATURE, 1.0F, 1.0F);
	public static final EntityType<GreatAukEntity> GREAT_AUK = LostWorldsRegistry.register("great_auk", GreatAukEntity::new, SEMI_AQUATIC_CREATURE, 0.75F, 1.5F);
	public static final EntityType<KentrosaurusEntity> KENTROSAURUS = LostWorldsRegistry.register("kentrosaurus", KentrosaurusEntity::new, EntityClassification.CREATURE, 1.0F, 0.75F);
	public static final EntityType<FossilEntity> KENTROSAURUS_SKELETON = LostWorldsRegistry.register("kentrosaurus_skeleton", FossilEntity::new, EntityClassification.MISC, 1.0F, 0.75F);
	public static final EntityType<LiaoningosaurusEntity> LIAONINGOSAURUS = LostWorldsRegistry.register("liaoningosaurus", LiaoningosaurusEntity::new, EntityClassification.CREATURE, 1.0F, 0.75F);
	public static final EntityType<NautilusEntity> NAUTILUS = LostWorldsRegistry.register("nautilus", NautilusEntity::new, EntityClassification.WATER_CREATURE, 0.5F, 0.5F);
	public static final EntityType<OphthalmosaurusEntity> OPHTHALMOSAURUS = LostWorldsRegistry.register("ophthalmosaurus", OphthalmosaurusEntity::new, EntityClassification.WATER_CREATURE, 2.0F, 2.0F);
	public static final EntityType<OstromiaEntity> OSTROMIA = LostWorldsRegistry.register("ostromia", OstromiaEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
	public static final EntityType<OuranosaurusEntity> OURANOSAURUS = LostWorldsRegistry.register("ouranosaurus", OuranosaurusEntity::new, EntityClassification.CREATURE, 2.5F, 2.5F);
	public static final EntityType<PalaeoniscumEntity> PALAEONISCUM = LostWorldsRegistry.register("palaeoniscum", PalaeoniscumEntity::new, EntityClassification.WATER_CREATURE, 0.5F, 0.5F);
	public static final EntityType<ProcompsognathusEntity> PROCOMPSOGNATHUS = LostWorldsRegistry.register("procompsognathus", ProcompsognathusEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
	public static final EntityType<ProtosuchusEntity> PROTOSUCHUS = LostWorldsRegistry.register("protosuchus", ProtosuchusEntity::new, EntityClassification.WATER_CREATURE, 0.5F, 0.5F);
	public static final EntityType<PsittacosaurusEntity> PSITTACOSAURUS = LostWorldsRegistry.register("psittacosaurus", PsittacosaurusEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
	public static final EntityType<RhinesuchusEntity> RHINESUCHUS = LostWorldsRegistry.register("rhinesuchus", RhinesuchusEntity::new, EntityClassification.WATER_CREATURE, 0.5F, 0.5F);
	public static final EntityType<SuchomimusEntity> SUCHOMIMUS = LostWorldsRegistry.register("suchomimus", SuchomimusEntity::new, EntityClassification.CREATURE, 1.5F, 1.5F);
	public static final EntityType<UtahraptorEntity> UTAHRAPTOR = LostWorldsRegistry.register("utahraptor", UtahraptorEntity::new, EntityClassification.CREATURE, 1.5F, 1.0F);
	public static final EntityType<TetraceratopsEntity> TETRACERATOPS = LostWorldsRegistry.register("tetraceratops", TetraceratopsEntity::new, EntityClassification.CREATURE, 0.75F, 0.5F);
	public static final EntityType<TyrannosaurusEntity> TYRANNOSAURUS = LostWorldsRegistry.register("tyrannosaurus", TyrannosaurusEntity::new, EntityClassification.CREATURE, 2.75F, 2.5F);
	public static final EntityType<ZephyrosaurusEntity> ZEPHYROSAURUS = LostWorldsRegistry.register("zephyrosaurus", ZephyrosaurusEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		ModSpawnEggItem.initSpawnEggs();
	}

	@SubscribeEvent
	public static void giveAttributes(EntityAttributeCreationEvent event) {
		for (DinoTypes dinos : DinoTypes.values()) {
			if (dinos != DinoTypes.NAUTILUS && dinos != DinoTypes.PALAEONISCUM && dinos != DinoTypes.ANOMALOCARIS) {
				event.put(dinos.getDirtyArmBones(), FossilEntity.createAttributes());
				event.put(dinos.getDirtyLegBones(), FossilEntity.createAttributes());
				event.put(dinos.getDirtyRibCage(), FossilEntity.createAttributes());
				event.put(dinos.getDirtySkull(), FossilEntity.createAttributes());
				event.put(dinos.getDirtyTail(), FossilEntity.createAttributes());
				event.put(dinos.getArmBones(), FossilEntity.createAttributes());
				event.put(dinos.getLegBones(), FossilEntity.createAttributes());
				event.put(dinos.getRibCage(), FossilEntity.createAttributes());
				event.put(dinos.getSkull(), FossilEntity.createAttributes());
				event.put(dinos.getTail(), FossilEntity.createAttributes());
			}

		}

		event.put(LostWorldsEntities.FOSSIL_POACHER, FossilPoacherEntity.createAttributes());

		event.put(LostWorldsEntities.ALLOSAURUS, AllosaurusEntity.createAttributes());
		event.put(LostWorldsEntities.ANOMALOCARIS, AnomalocarisEntity.createAttributes());
		event.put(LostWorldsEntities.CARNOTAURUS, CarnotaurusEntity.createAttributes());
		event.put(LostWorldsEntities.CHILESAURUS, ChilesaurusEntity.createAttributes());
		event.put(LostWorldsEntities.CHILESAURUS_SKELETON, FossilEntity.createAttributes());
		event.put(LostWorldsEntities.CRYOLOPHOSAURUS, CryolophosaurusEntity.createAttributes());
		event.put(LostWorldsEntities.DIICTODON, DiictodonEntity.createAttributes());
		event.put(LostWorldsEntities.DILOPHOSAURUS, DilophosaurusEntity.createAttributes());
		event.put(LostWorldsEntities.DIMETRODON, DimetrodonEntity.createAttributes());
		event.put(LostWorldsEntities.EORAPTOR, EoraptorEntity.createAttributes());
		event.put(LostWorldsEntities.EDAPHOSAURUS, EdaphosaurusEntity.createAttributes());
		event.put(LostWorldsEntities.FUKUIVENATOR, FukuivenatorEntity.createAttributes());
		event.put(LostWorldsEntities.GIGANOTOSAURUS, GiganotosaurusEntity.createAttributes());
		event.put(LostWorldsEntities.GORGONOPS, GorgonopsEntity.createAttributes());
		event.put(LostWorldsEntities.GREAT_AUK, GreatAukEntity.createAttributes());
		event.put(LostWorldsEntities.KENTROSAURUS, GorgonopsEntity.createAttributes());
		event.put(LostWorldsEntities.KENTROSAURUS_SKELETON, FossilEntity.createAttributes());
		event.put(LostWorldsEntities.LIAONINGOSAURUS, LiaoningosaurusEntity.createAttributes());
		event.put(LostWorldsEntities.NAUTILUS, NautilusEntity.createAttributes());
		event.put(LostWorldsEntities.OPHTHALMOSAURUS, OphthalmosaurusEntity.createAttributes());
		event.put(LostWorldsEntities.OSTROMIA, OstromiaEntity.createAttributes());
		event.put(LostWorldsEntities.OURANOSAURUS, OuranosaurusEntity.createAttributes());
		event.put(LostWorldsEntities.PALAEONISCUM, PalaeoniscumEntity.createBasicAttributes());
		event.put(LostWorldsEntities.PROCOMPSOGNATHUS, ProcompsognathusEntity.createAttributes());
		event.put(LostWorldsEntities.PROTOSUCHUS, ProtosuchusEntity.createAttributes());
		event.put(LostWorldsEntities.PSITTACOSAURUS, PsittacosaurusEntity.createAttributes());
		event.put(LostWorldsEntities.RHINESUCHUS, RhinesuchusEntity.createAttributes());
		event.put(LostWorldsEntities.SUCHOMIMUS, SuchomimusEntity.createAttributes());
		event.put(LostWorldsEntities.UTAHRAPTOR, UtahraptorEntity.createAttributes());
		event.put(LostWorldsEntities.TETRACERATOPS, TetraceratopsEntity.createAttributes());
		event.put(LostWorldsEntities.TYRANNOSAURUS, TyrannosaurusEntity.createAttributes());
		event.put(LostWorldsEntities.ZEPHYROSAURUS, ZephyrosaurusEntity.createAttributes());
	}

	static {
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.ALLOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.ANOMALOCARIS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, AnomalocarisEntity::spawnRules);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.CHILESAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.CRYOLOPHOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.DIICTODON, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.DILOPHOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.DIMETRODON, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.EORAPTOR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.EDAPHOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.FUKUIVENATOR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.GIGANOTOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.GORGONOPS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.GREAT_AUK, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GreatAukEntity::checkSpawnRules);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.KENTROSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.LIAONINGOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.NAUTILUS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, NautilusEntity::spawnRules);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.OPHTHALMOSAURUS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, OphthalmosaurusEntity::checkBasicSpawnRules);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.OSTROMIA, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.OURANOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.PALAEONISCUM, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, PalaeoniscumEntity::spawnRules);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.PROCOMPSOGNATHUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.PROTOSUCHUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.PSITTACOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.RHINESUCHUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RhinesuchusEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.SUCHOMIMUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SuchomimusEntity::checkSpawnRules);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.UTAHRAPTOR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.TETRACERATOPS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.TYRANNOSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(LostWorldsEntities.ZEPHYROSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Enitites");

		for (DinoTypes dinos : DinoTypes.values()) {
			if (dinos != DinoTypes.NAUTILUS && dinos != DinoTypes.PALAEONISCUM && dinos != DinoTypes.ANOMALOCARIS) {
				EntityType<FossilEntity> dirtyskull = LostWorldsRegistry.register("dirty_" + dinos.toString().toLowerCase() + "_skull", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtySkull(dirtyskull);
				EntityType<FossilEntity> dirtyarmbones = LostWorldsRegistry.register("dirty_" + dinos.toString().toLowerCase() + "_arm_bones", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyArmBones(dirtyarmbones);
				EntityType<FossilEntity> dirtylegbones = LostWorldsRegistry.register("dirty_" + dinos.toString().toLowerCase() + "_leg_bones", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyLegBones(dirtylegbones);
				EntityType<FossilEntity> dirtyribcage = LostWorldsRegistry.register("dirty_" + dinos.toString().toLowerCase() + "_rib_cage", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyRibCage(dirtyribcage);
				EntityType<FossilEntity> dirtytail = LostWorldsRegistry.register("dirty_" + dinos.toString().toLowerCase() + "_tail", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyTail(dirtytail);
				EntityType<FossilEntity> skull = LostWorldsRegistry.register(dinos.toString().toLowerCase() + "_skull", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setSkull(skull);
				EntityType<FossilEntity> arm_bones = LostWorldsRegistry.register(dinos.toString().toLowerCase() + "_arm_bones", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setArmBones(arm_bones);
				EntityType<FossilEntity> leg_bones = LostWorldsRegistry.register(dinos.toString().toLowerCase() + "_leg_bones", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setLegBones(leg_bones);
				EntityType<FossilEntity> rib_cage = LostWorldsRegistry.register(dinos.toString().toLowerCase() + "_rib_cage", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setRibCage(rib_cage);
				EntityType<FossilEntity> tail = LostWorldsRegistry.register(dinos.toString().toLowerCase() + "_tail", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setTail(tail);
				if (dinos == DinoTypes.KENTROSAURUS) {
					dinos.setSkeleton(KENTROSAURUS_SKELETON);
				}
				if (dinos == DinoTypes.CHILESAURUS) {
					dinos.setSkeleton(CHILESAURUS_SKELETON);
				} else {
					dinos.setSkeleton(KENTROSAURUS_SKELETON);
				}
			}

			if (dinos == DinoTypes.ANOMALOCARIS) {
				EntityType<FossilEntity> dirtyExoskeleton = LostWorldsRegistry.register("dirty_" + dinos.toString().toLowerCase() + "_exoskeleton", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyExoskeleton(dirtyExoskeleton);
				EntityType<FossilEntity> exoskeleton = LostWorldsRegistry.register(dinos.toString().toLowerCase() + "_exoskeleton", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setExoskeleton(exoskeleton);
			}

			if (dinos == DinoTypes.PALAEONISCUM) {
				EntityType<FossilEntity> dirtyBody = LostWorldsRegistry.register("dirty_" + dinos.toString().toLowerCase() + "_body", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyBody(dirtyBody);
				EntityType<FossilEntity> body = LostWorldsRegistry.register(dinos.toString().toLowerCase() + "_body", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setBody(body);
			}
		}
	}
}
