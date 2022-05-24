package lostworlds.server.entity;

import static lostworlds.LostWorldsMod.getRegistrate;

import com.tterrag.registrate.providers.loot.RegistrateEntityLootTables;
import com.tterrag.registrate.util.entry.EntityEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;

import lostworlds.client.entity.render.AllosaurusRenderer;
import lostworlds.client.entity.render.AnomalocarisRenderer;
import lostworlds.client.entity.render.CarnotaurusRenderer;
import lostworlds.client.entity.render.ChilesaurusRenderer;
import lostworlds.client.entity.render.CryolophosaurusRenderer;
import lostworlds.client.entity.render.CustomisableRenderer;
import lostworlds.client.entity.render.DiictodonRenderer;
import lostworlds.client.entity.render.DilophosaurusRenderer;
import lostworlds.client.entity.render.DimetrodonRenderer;
import lostworlds.client.entity.render.EdaphosaurusRenderer;
import lostworlds.client.entity.render.EoraptorRenderer;
import lostworlds.client.entity.render.EustreptospondylusRenderer;
import lostworlds.client.entity.render.FossilPoacherRenderer;
import lostworlds.client.entity.render.FukuivenatorRenderer;
import lostworlds.client.entity.render.GiganotosaurusRenderer;
import lostworlds.client.entity.render.GorgonopsRenderer;
import lostworlds.client.entity.render.GreatAukRenderer;
import lostworlds.client.entity.render.KentrosaurusRenderer;
import lostworlds.client.entity.render.LiaoningosaurusRenderer;
import lostworlds.client.entity.render.ModBoatRenderer;
import lostworlds.client.entity.render.NautilusRenderer;
import lostworlds.client.entity.render.OphthalmosaurusRenderer;
import lostworlds.client.entity.render.OstromiaRenderer;
import lostworlds.client.entity.render.OuranosaurusRenderer;
import lostworlds.client.entity.render.PalaeoniscumRenderer;
import lostworlds.client.entity.render.ParksosaurusRenderer;
import lostworlds.client.entity.render.ProcompsognathusRenderer;
import lostworlds.client.entity.render.ProtosuchusRenderer;
import lostworlds.client.entity.render.PsittacosaurusRenderer;
import lostworlds.client.entity.render.RhinesuchusRenderer;
import lostworlds.client.entity.render.SuchomimusRenderer;
import lostworlds.client.entity.render.TetraceratopsRenderer;
import lostworlds.client.entity.render.ThanosRenderer;
import lostworlds.client.entity.render.TyrannosaurusRenderer;
import lostworlds.client.entity.render.UtahraptorRenderer;
import lostworlds.client.entity.render.ZephyrosaurusRenderer;
import lostworlds.server.entity.aquatic.cambrian.Anomalocaris;
import lostworlds.server.entity.aquatic.jurassic.Ophthalmosaurus;
import lostworlds.server.entity.aquatic.modern.Nautilus;
import lostworlds.server.entity.aquatic.permian.Palaeoniscum;
import lostworlds.server.entity.fossil.DirtyFossil;
import lostworlds.server.entity.fossil.Fossil;
import lostworlds.server.entity.illager.FossilPoacherEntity;
import lostworlds.server.entity.item.ChargedCrystalScarabGemItemEntity;
import lostworlds.server.entity.item.CrystalScarabGemItemEntity;
import lostworlds.server.entity.semiaquatic.jurassic.Protosuchus;
import lostworlds.server.entity.semiaquatic.modern.GreatAuk;
import lostworlds.server.entity.semiaquatic.permian.Rhinesuchus;
import lostworlds.server.entity.terrestrial.PrehistoricMob;
import lostworlds.server.entity.terrestrial.cretaceous.Carnotaurus;
import lostworlds.server.entity.terrestrial.cretaceous.Fukuivenator;
import lostworlds.server.entity.terrestrial.cretaceous.Giganotosaurus;
import lostworlds.server.entity.terrestrial.cretaceous.Ouranosaurus;
import lostworlds.server.entity.terrestrial.cretaceous.Parksosaurus;
import lostworlds.server.entity.terrestrial.cretaceous.Psittacosaurus;
import lostworlds.server.entity.terrestrial.cretaceous.Suchomimus;
import lostworlds.server.entity.terrestrial.cretaceous.Thanos;
import lostworlds.server.entity.terrestrial.cretaceous.Tyrannosaurus;
import lostworlds.server.entity.terrestrial.cretaceous.Utahraptor;
import lostworlds.server.entity.terrestrial.cretaceous.Zephyrosaurus;
import lostworlds.server.entity.terrestrial.jurassic.Allosaurus;
import lostworlds.server.entity.terrestrial.jurassic.Chilesaurus;
import lostworlds.server.entity.terrestrial.jurassic.Cryolophosaurus;
import lostworlds.server.entity.terrestrial.jurassic.Dilophosaurus;
import lostworlds.server.entity.terrestrial.jurassic.Eustreptospondylus;
import lostworlds.server.entity.terrestrial.jurassic.Kentrosaurus;
import lostworlds.server.entity.terrestrial.jurassic.Liaoningosaurus;
import lostworlds.server.entity.terrestrial.jurassic.Ostromia;
import lostworlds.server.entity.terrestrial.permian.DiictodonEntity;
import lostworlds.server.entity.terrestrial.permian.Dimetrodon;
import lostworlds.server.entity.terrestrial.permian.Edaphosaurus;
import lostworlds.server.entity.terrestrial.permian.Gorgonops;
import lostworlds.server.entity.terrestrial.permian.Tetraceratops;
import lostworlds.server.entity.terrestrial.triassic.Eoraptor;
import lostworlds.server.entity.terrestrial.triassic.Procompsognathus;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.util.registrate.LostWorldsRegistrate;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class LostWorldsEntities {
	public static final LostWorldsRegistrate REGISTRATE = getRegistrate();

	public static final MobCategory SEMI_AQUATIC_CREATURE = MobCategory.create("semi_aquatic_creature", "semi_aquatic_creature", 10, true, true, 128);

	public static final EntityEntry<FossilPoacherEntity> FOSSIL_POACHER = REGISTRATE.entity("fossil_poacher", FossilPoacherEntity::new, MobCategory.MONSTER).properties(properties -> properties.sized(0.6F, 1.95F)).attributes(() -> FossilPoacherEntity.createAttributes()).defaultSpawnEgg(0x959b9b, 0x363031).renderer(() -> FossilPoacherRenderer::new).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();

	public static final EntityEntry<ModBoat> MOD_BOAT = REGISTRATE.<ModBoat>entity("mod_boat", ModBoat::new, MobCategory.MISC).properties(properties -> properties.sized(1.375F, 0.5625F)).renderer(() -> ModBoatRenderer::new).register();
	public static final EntityEntry<ChargedCrystalScarabGemItemEntity> CHARGED_CRYSTAL_SCARAB_GEM_ITEM = REGISTRATE.<ChargedCrystalScarabGemItemEntity>entity("charged_crystal_scarab_gem_item", ChargedCrystalScarabGemItemEntity::new, MobCategory.MISC).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> context -> new ItemEntityRenderer(context)).register();
	public static final EntityEntry<CrystalScarabGemItemEntity> CRYSTAL_SCARAB_GEM_ITEM = REGISTRATE.<CrystalScarabGemItemEntity>entity("crystal_scarab_gem_item", CrystalScarabGemItemEntity::new, MobCategory.MISC).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> context -> new ItemEntityRenderer(context)).register();

	public static final EntityEntry<Allosaurus> ALLOSAURUS = REGISTRATE.entity("allosaurus", Allosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(2.0F, 1.5F)).renderer(() -> AllosaurusRenderer::new).defaultSpawnEgg(0x9f9f5a, 0xd68812).attributes(() -> Allosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.ALLOSAURUS, 2, 7)).register();
	public static final EntityEntry<Anomalocaris> ANOMALOCARIS = REGISTRATE.entity("anomalocaris", Anomalocaris::new, MobCategory.WATER_CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> AnomalocarisRenderer::new).defaultSpawnEgg(0xb94f33, 0x631312).attributes(() -> Anomalocaris.createAttributes()).spawnPlacement(Type.IN_WATER, Types.OCEAN_FLOOR, Anomalocaris::canFishLikeSpawn).loot(dinosaurDrop(DinoTypes.ANOMALOCARIS, 0, 2)).register();
	public static final EntityEntry<Carnotaurus> CARNOTAURUS = REGISTRATE.entity("carnotaurus", Carnotaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(2.0F, 1.5F)).renderer(() -> CarnotaurusRenderer::new).defaultSpawnEgg(0x605645, 0xb5a187).attributes(() -> Carnotaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.CARNOTAURUS, 1, 5)).register();
	public static final EntityEntry<Chilesaurus> CHILESAURUS = REGISTRATE.entity("chilesaurus", Chilesaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> ChilesaurusRenderer::new).defaultSpawnEgg(0xb08533, 0x283c3f).attributes(() -> Chilesaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.CHILESAURUS, 1, 3)).register();
	public static final EntityEntry<Cryolophosaurus> CRYOLOPHOSAURUS = REGISTRATE.entity("cryolophosaurus", Cryolophosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 0.75F)).renderer(() -> CryolophosaurusRenderer::new).defaultSpawnEgg(0xab5a14, 0x1a2c5f).attributes(() -> Cryolophosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.CRYOLOPHOSAURUS, 1, 4)).register();
	public static final EntityEntry<DiictodonEntity> DIICTODON = REGISTRATE.entity("diictodon", DiictodonEntity::new, MobCategory.CREATURE).properties(properties -> properties.sized(0.25F, 0.25F)).renderer(() -> DiictodonRenderer::new).defaultSpawnEgg(0xdc8a54, 0x8b462e).attributes(() -> DiictodonEntity.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.DIICTODON, 0, 1)).register();
	public static final EntityEntry<Dilophosaurus> DILOPHOSAURUS = REGISTRATE.entity("dilophosaurus", Dilophosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.5F, 1.5F)).renderer(() -> DilophosaurusRenderer::new).defaultSpawnEgg(0xc49838, 0xc75539).attributes(() -> Dilophosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.DILOPHOSAURUS, 1, 5)).register();
	public static final EntityEntry<Dimetrodon> DIMETRODON = REGISTRATE.entity("dimetrodon", Dimetrodon::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.5F, 1.5F)).renderer(() -> DimetrodonRenderer::new).defaultSpawnEgg(0x81644c, 0xcba755).attributes(() -> Dimetrodon.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.DIMETRODON, 1, 4)).register();
	public static final EntityEntry<Edaphosaurus> EDAPHOSAURUS = REGISTRATE.entity("edaphosaurus", Edaphosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> EdaphosaurusRenderer::new).defaultSpawnEgg(0x57614e, 0xdf9046).attributes(() -> Edaphosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.EDAPHOSAURUS, 1, 4)).register();
	public static final EntityEntry<Eoraptor> EORAPTOR = REGISTRATE.entity("eoraptor", Eoraptor::new, MobCategory.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> EoraptorRenderer::new).defaultSpawnEgg(0x523c3e, 0x824b78).attributes(() -> Eoraptor.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.EORAPTOR, 1, 2)).register();
	public static final EntityEntry<Eustreptospondylus> EUSTREPTOSPONDYLUS = REGISTRATE.entity("eustreptospondylus", Eustreptospondylus::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.5F, 1.0F)).renderer(() -> EustreptospondylusRenderer::new).defaultSpawnEgg(0x241d1a, 0xab5631).attributes(() -> Eustreptospondylus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.EUSTREPTOSPONDYLUS, 1, 4)).register();
	public static final EntityEntry<Fukuivenator> FUKUIVENATOR = REGISTRATE.entity("fukuivenator", Fukuivenator::new, MobCategory.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> FukuivenatorRenderer::new).defaultSpawnEgg(0x52526f, 0x5757959).attributes(() -> Fukuivenator.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.FUKUIVENATOR, 2, 4)).register();
	public static final EntityEntry<Giganotosaurus> GIGANOTOSAURUS = REGISTRATE.entity("giganotosaurus", Giganotosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(4.0F, 2.5F)).renderer(() -> GiganotosaurusRenderer::new).defaultSpawnEgg(0x9f6b41, 0x943c24).attributes(() -> Giganotosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.GIGANOTOSAURUS, 3, 8)).register();
	public static final EntityEntry<Gorgonops> GORGONOPS = REGISTRATE.entity("gorgonops", Gorgonops::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> GorgonopsRenderer::new).defaultSpawnEgg(0x443619, 0x3b3e2d).attributes(() -> Gorgonops.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.GORGONOPS, 1, 3)).register();
	public static final EntityEntry<GreatAuk> GREAT_AUK = REGISTRATE.entity("great_auk", GreatAuk::new, SEMI_AQUATIC_CREATURE).properties(properties -> properties.sized(0.75F, 1.5F)).renderer(() -> GreatAukRenderer::new).defaultSpawnEgg(0x000000, 0xFFFFFF).attributes(() -> GreatAuk.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, GreatAuk::canGreatAukSpawn).loot(dinosaurDrop(DinoTypes.GREAT_AUK, 1, 3)).register();
	public static final EntityEntry<Kentrosaurus> KENTROSAURUS = REGISTRATE.entity("kentrosaurus", Kentrosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 0.75F)).renderer(() -> KentrosaurusRenderer::new).defaultSpawnEgg(0xd99760, 0x612c00).attributes(() -> Kentrosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.KENTROSAURUS, 1, 4)).register();
	public static final EntityEntry<Liaoningosaurus> LIAONINGOSAURUS = REGISTRATE.entity("liaoningosaurus", Liaoningosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 0.75F)).renderer(() -> LiaoningosaurusRenderer::new).defaultSpawnEgg(0x712d0d, 0x7c8237).attributes(() -> Liaoningosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.LIAONINGOSAURUS, 2, 4)).register();
	public static final EntityEntry<Nautilus> NAUTILUS = REGISTRATE.entity("nautilus", Nautilus::new, MobCategory.WATER_CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> NautilusRenderer::new).defaultSpawnEgg(0xd4ccc3, 0xca7548).attributes(() -> Nautilus.createAttributes()).spawnPlacement(Type.IN_WATER, Types.OCEAN_FLOOR, Nautilus::canFishLikeSpawn).loot(dinosaurDrop(DinoTypes.NAUTILUS, 2, 5)).register();
	public static final EntityEntry<Ophthalmosaurus> OPHTHALMOSAURUS = REGISTRATE.entity("ophthalmosaurus", Ophthalmosaurus::new, MobCategory.WATER_CREATURE).properties(properties -> properties.sized(2.0F, 2.0F)).renderer(() -> OphthalmosaurusRenderer::new).defaultSpawnEgg(0x858794, 0x0e131b).attributes(() -> Ophthalmosaurus.createAttributes()).spawnPlacement(Type.IN_WATER, Types.OCEAN_FLOOR, Ophthalmosaurus::canDolphinLikeSpawn).loot(dinosaurDrop(DinoTypes.OPHTHALMOSAURUS, 1, 3)).register();
	public static final EntityEntry<Ostromia> OSTROMIA = REGISTRATE.entity("ostromia", Ostromia::new, MobCategory.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> OstromiaRenderer::new).defaultSpawnEgg(0x47a373, 0x2c4d86).attributes(() -> Ostromia.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.OSTROMIA, 1, 2)).register();
	public static final EntityEntry<Ouranosaurus> OURANOSAURUS = REGISTRATE.entity("ouranosaurus", Ouranosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(2.5F, 2.5F)).renderer(() -> OuranosaurusRenderer::new).defaultSpawnEgg(0x999554, 0x90bdb4).attributes(() -> Ouranosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.OURANOSAURUS, 2, 4)).register();
	public static final EntityEntry<Parksosaurus> PARKSOSAURUS = REGISTRATE.entity("parksosaurus", Parksosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> ParksosaurusRenderer::new).defaultSpawnEgg(0xa98460, 0xe59a3c).attributes(() -> Parksosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.PARKSOSAURUS, 1, 2)).register();
	public static final EntityEntry<Palaeoniscum> PALAEONISCUM = REGISTRATE.entity("palaeoniscum", Palaeoniscum::new, MobCategory.WATER_CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> PalaeoniscumRenderer::new).defaultSpawnEgg(0x72797a, 0x2f3a3d).attributes(() -> Palaeoniscum.createAttributes()).spawnPlacement(Type.IN_WATER, Types.OCEAN_FLOOR, Palaeoniscum::canFishLikeSpawn).loot(dinosaurDrop(DinoTypes.PALAEONISCUM, 1, 1)).register();
	public static final EntityEntry<Procompsognathus> PROCOMPSOGNATHUS = REGISTRATE.entity("procompsognathus", Procompsognathus::new, MobCategory.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> ProcompsognathusRenderer::new).defaultSpawnEgg(0x445a2f, 0x404727).attributes(() -> Procompsognathus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.PROCOMPSOGNATHUS, 0, 2)).register();
	public static final EntityEntry<Protosuchus> PROTOSUCHUS = REGISTRATE.entity("protosuchus", Protosuchus::new, MobCategory.WATER_CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> ProtosuchusRenderer::new).defaultSpawnEgg(0x8e2317, 0xb0492e).attributes(() -> Protosuchus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.PROTOSUCHUS, 1, 3)).register();
	public static final EntityEntry<Psittacosaurus> PSITTACOSAURUS = REGISTRATE.entity("psittacosaurus", Psittacosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> PsittacosaurusRenderer::new).defaultSpawnEgg(0x4c2c21, 0x938639).attributes(() -> Psittacosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.PSITTACOSAURUS, 0, 2)).register();
	public static final EntityEntry<Rhinesuchus> RHINESUCHUS = REGISTRATE.entity("rhinesuchus", Rhinesuchus::new, MobCategory.WATER_CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> RhinesuchusRenderer::new).defaultSpawnEgg(0x576b54, 0xaf944a).attributes(() -> Rhinesuchus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.RHINESUCHUS, 2, 4)).register();
	public static final EntityEntry<Suchomimus> SUCHOMIMUS = REGISTRATE.entity("suchomimus", Suchomimus::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.5F, 1.5F)).renderer(() -> SuchomimusRenderer::new).defaultSpawnEgg(0x57737b, 0xcd9528).attributes(() -> Suchomimus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.SUCHOMIMUS, 2, 5)).register();
	public static final EntityEntry<Utahraptor> UTAHRAPTOR = REGISTRATE.entity("utahraptor", Utahraptor::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.5F, 1.0F)).renderer(() -> UtahraptorRenderer::new).defaultSpawnEgg(0x503524, 0x635f5e).attributes(() -> Utahraptor.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.UTAHRAPTOR, 1, 4)).register();
	public static final EntityEntry<Thanos> THANOS = REGISTRATE.entity("thanos", Thanos::new, MobCategory.CREATURE).properties(properties -> properties.sized(1.5F, 1.5F)).renderer(() -> ThanosRenderer::new).defaultSpawnEgg(0xca2018, 0xa5800b).attributes(() -> Thanos.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.THANOS, 1, 5)).register();
	public static final EntityEntry<Tetraceratops> TETRACERATOPS = REGISTRATE.entity("tetraceratops", Tetraceratops::new, MobCategory.CREATURE).properties(properties -> properties.sized(0.75F, 0.5F)).renderer(() -> TetraceratopsRenderer::new).defaultSpawnEgg(0x623015, 0x21369b).attributes(() -> Tetraceratops.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.TETRACERATOPS, 1, 3)).register();
	public static final EntityEntry<Tyrannosaurus> TYRANNOSAURUS = REGISTRATE.entity("tyrannosaurus", Tyrannosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(2.75F, 2.5F)).renderer(() -> TyrannosaurusRenderer::new).defaultSpawnEgg(0x503524, 0x635f5e).attributes(() -> Tyrannosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.TYRANNOSAURUS, 3, 8)).register();
	public static final EntityEntry<Zephyrosaurus> ZEPHYROSAURUS = REGISTRATE.entity("zephyrosaurus", Zephyrosaurus::new, MobCategory.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> ZephyrosaurusRenderer::new).defaultSpawnEgg(0x577476, 0x9ba3a3).attributes(() -> Zephyrosaurus.createAttributes()).spawnPlacement(Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, PrehistoricMob::canPrehistoricSpawn).loot(dinosaurDrop(DinoTypes.ZEPHYROSAURUS, 1, 3)).register();

	static {
		for (DinoTypes dinos : DinoTypes.values()) {
			if (dinos != DinoTypes.NAUTILUS && dinos != DinoTypes.PALAEONISCUM && dinos != DinoTypes.ANOMALOCARIS) {
				EntityEntry<DirtyFossil> dirtyskull = REGISTRATE.<DirtyFossil>entity("dirty_" + dinos.toString().toLowerCase() + "_skull", (entity, level) -> new DirtyFossil(entity, level).setDirtyPick(dinos.getSkullItem().get().getDefaultInstance()), MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> context -> new CustomisableRenderer(context, dinos.getId() + "_skull", dinos.getId(), 0.5F)).attributes(() -> Fossil.createAttributes()).loot((provider, entity) -> provider.add(entity, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(dinos.getPlasteredSkullItem().get()))))).register();
				dinos.setDirtySkull(() -> dirtyskull.get());
				EntityEntry<DirtyFossil> dirtyarmbones = REGISTRATE.<DirtyFossil>entity("dirty_" + dinos.toString().toLowerCase() + "_arm_bones", (entity, level) -> new DirtyFossil(entity, level).setDirtyPick(dinos.getArmBonesItem().get().getDefaultInstance()), MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> context -> new CustomisableRenderer(context, dinos.getId() + "_arm_bones", dinos.getId(), 0.5F)).attributes(() -> Fossil.createAttributes()).loot((provider, entity) -> provider.add(entity, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(dinos.getPlasteredArmBonesItem().get()))))).register();
				dinos.setDirtyArmBones(() -> dirtyarmbones.get());
				EntityEntry<DirtyFossil> dirtylegbones = REGISTRATE.<DirtyFossil>entity("dirty_" + dinos.toString().toLowerCase() + "_leg_bones", (entity, level) -> new DirtyFossil(entity, level).setDirtyPick(dinos.getLegBonesItem().get().getDefaultInstance()), MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> context -> new CustomisableRenderer(context, dinos.getId() + "_leg_bones", dinos.getId(), 0.5F)).attributes(() -> Fossil.createAttributes()).loot((provider, entity) -> provider.add(entity, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(dinos.getPlasteredLegBonesItem().get()))))).register();
				dinos.setDirtyLegBones(() -> dirtylegbones.get());
				EntityEntry<DirtyFossil> dirtyribcage = REGISTRATE.<DirtyFossil>entity("dirty_" + dinos.toString().toLowerCase() + "_rib_cage", (entity, level) -> new DirtyFossil(entity, level).setDirtyPick(dinos.getRibCageItem().get().getDefaultInstance()), MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> context -> new CustomisableRenderer(context, dinos.getId() + "_rib_cage", dinos.getId(), 0.5F)).attributes(() -> Fossil.createAttributes()).loot((provider, entity) -> provider.add(entity, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(dinos.getPlasteredRibCageBonesItem().get()))))).register();
				dinos.setDirtyRibCage(() -> dirtyribcage.get());
				EntityEntry<DirtyFossil> dirtytail = REGISTRATE.<DirtyFossil>entity("dirty_" + dinos.toString().toLowerCase() + "_tail", (entity, level) -> new DirtyFossil(entity, level).setDirtyPick(dinos.getTailItem().get().getDefaultInstance()), MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> context -> new CustomisableRenderer(context, dinos.getId() + "_tail", dinos.getId(), 0.5F)).attributes(() -> Fossil.createAttributes()).loot((provider, entity) -> provider.add(entity, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(dinos.getPlasteredTailBonesItem().get()))))).register();
				dinos.setDirtyTail(() -> dirtytail.get());
				EntityEntry<Fossil> skull = REGISTRATE.<Fossil>entity(dinos.toString().toLowerCase() + "_skull", (entity, level) -> new Fossil(entity, level).setPick(dinos.getSkullItem().get().getDefaultInstance()), MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> context -> new CustomisableRenderer(context, dinos.getId() + "_skull", dinos.getId(), 0.5F)).attributes(() -> Fossil.createAttributes()).loot((provider, entity) -> provider.add(entity, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(dinos.getSkullItem().get()))))).register();
				dinos.setSkull(() -> skull.get());
				EntityEntry<Fossil> arm_bones = REGISTRATE.<Fossil>entity(dinos.toString().toLowerCase() + "_arm_bones", (entity, level) -> new Fossil(entity, level).setPick(dinos.getArmBonesItem().get().getDefaultInstance()), MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> context -> new CustomisableRenderer(context, dinos.getId() + "_arm_bones", dinos.getId(), 0.5F)).attributes(() -> Fossil.createAttributes()).loot((provider, entity) -> provider.add(entity, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(dinos.getArmBonesItem().get()))))).register();
				dinos.setArmBones(() -> arm_bones.get());
				EntityEntry<Fossil> leg_bones = REGISTRATE.<Fossil>entity(dinos.toString().toLowerCase() + "_leg_bones", (entity, level) -> new Fossil(entity, level).setPick(dinos.getLegBonesItem().get().getDefaultInstance()), MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> context -> new CustomisableRenderer(context, dinos.getId() + "_leg_bones", dinos.getId(), 0.5F)).attributes(() -> Fossil.createAttributes()).loot((provider, entity) -> provider.add(entity, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(dinos.getLegBonesItem().get()))))).register();
				dinos.setLegBones(() -> leg_bones.get());
				EntityEntry<Fossil> rib_cage = REGISTRATE.<Fossil>entity(dinos.toString().toLowerCase() + "_rib_cage", (entity, level) -> new Fossil(entity, level).setPick(dinos.getRibCageItem().get().getDefaultInstance()), MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> context -> new CustomisableRenderer(context, dinos.getId() + "_rib_cage", dinos.getId(), 0.5F)).attributes(() -> Fossil.createAttributes()).loot((provider, entity) -> provider.add(entity, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(dinos.getRibCageItem().get()))))).register();
				dinos.setRibCage(() -> rib_cage.get());
				EntityEntry<Fossil> tail = REGISTRATE.<Fossil>entity(dinos.toString().toLowerCase() + "_tail", (entity, level) -> new Fossil(entity, level).setPick(dinos.getTailItem().get().getDefaultInstance()), MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> context -> new CustomisableRenderer(context, dinos.getId() + "_tail", dinos.getId(), 0.5F)).attributes(() -> Fossil.createAttributes()).loot((provider, entity) -> provider.add(entity, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(dinos.getTailItem().get()))))).register();
				dinos.setTail(() -> tail.get());
				EntityEntry<Fossil> skeleton = REGISTRATE.<Fossil>entity(dinos.toString().toLowerCase() + "_skeleton", (entity, level) -> new Fossil(entity, level).setPick(dinos.getSkeletonPick().get().getDefaultInstance()), MobCategory.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> context -> new CustomisableRenderer(context, dinos.getId(), dinos.getId(), 0.5F)).attributes(() -> Fossil.createAttributes()).loot((provider, entity) -> provider.add(entity, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(dinos.getSkeletonPick().get()))))).register();
				dinos.setSkeleton(() -> skeleton.get());
			}
		}
	}

	public static <T extends LivingEntity> NonNullBiConsumer<RegistrateEntityLootTables, EntityType<T>> dinosaurDrop(DinoTypes type, int min, int max) {
		return (provider, entity) -> {
			Builder lootTable = LootTable.lootTable();
			lootTable.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(type.getMeat().get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build())))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));
			if (DinoTypes.feathered().contains(type)) {
				lootTable.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(type.getFeather().get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));
			}
			if (DinoTypes.createHide().contains(type)) {
				lootTable.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(type.getHide().get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));
			}
			if (type == DinoTypes.PALAEONISCUM) {
				lootTable.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.BONE_MEAL)).when(LootItemRandomChanceCondition.randomChance(0.05F)));
			}
			provider.add(entity, lootTable);
		};
	}

	public static void registrate() {
	}
}
