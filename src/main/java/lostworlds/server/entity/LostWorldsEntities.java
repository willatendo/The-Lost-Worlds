package lostworlds.server.entity;

import static lostworlds.LostWorldsMod.getRegistrate;

import com.tterrag.registrate.util.entry.EntityEntry;

import lostworlds.client.entity.render.AllosaurusRenderer;
import lostworlds.client.entity.render.AnomalocarisRenderer;
import lostworlds.client.entity.render.CarnotaurusRenderer;
import lostworlds.client.entity.render.ChilesaurusRenderer;
import lostworlds.client.entity.render.CryolophosaurusRenderer;
import lostworlds.client.entity.render.DiictodonRenderer;
import lostworlds.client.entity.render.DilophosaurusRenderer;
import lostworlds.client.entity.render.DimetrodonRenderer;
import lostworlds.client.entity.render.EdaphosaurusRenderer;
import lostworlds.client.entity.render.EoraptorRenderer;
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
import lostworlds.client.entity.render.ProcompsognathusRenderer;
import lostworlds.client.entity.render.ProtosuchusRenderer;
import lostworlds.client.entity.render.PsittacosaurusRenderer;
import lostworlds.client.entity.render.RhinesuchusRenderer;
import lostworlds.client.entity.render.SuchomimusRenderer;
import lostworlds.client.entity.render.TetraceratopsRenderer;
import lostworlds.client.entity.render.TyrannosaurusRenderer;
import lostworlds.client.entity.render.UtahraptorRenderer;
import lostworlds.client.entity.render.ZephyrosaurusRenderer;
import lostworlds.server.LostWorldsTags;
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
import lostworlds.server.util.LostWorldsRegistrate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.IFactory;
import net.minecraft.loot.LootTable;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

// Todo: Angry Entities
@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
public class LostWorldsEntities {
	public static final LostWorldsRegistrate REGISTRATE = getRegistrate();

	public static final EntityClassification SEMI_AQUATIC_CREATURE = EntityClassification.create("semi_aquatic_creature", "semi_aquatic_creature", 10, true, true, 128);

	public static final EntityEntry<FossilPoacherEntity> FOSSIL_POACHER = REGISTRATE.entity("fossil_poacher", FossilPoacherEntity::new, EntityClassification.MONSTER).properties(properties -> properties.sized(0.6F, 1.95F)).attributes(() -> FossilPoacherEntity.createAttributes()).defaultSpawnEgg(0x959b9b, 0x363031).renderer(() -> FossilPoacherRenderer::new).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();

	public static final EntityEntry<ModBoatEntity> MOD_BOAT = REGISTRATE.<ModBoatEntity>entity("mod_boat", ModBoatEntity::new, EntityClassification.MISC).properties(properties -> properties.sized(1.375F, 0.5625F)).renderer(() -> ModBoatRenderer::new).register();
	public static final EntityEntry<ChargedCrystalScarabGemItemEntity> CHARGED_CRYSTAL_SCARAB_GEM_ITEM = REGISTRATE.<ChargedCrystalScarabGemItemEntity>entity("charged_crystal_scarab_gem_item", ChargedCrystalScarabGemItemEntity::new, EntityClassification.MISC).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> manager -> new ItemRenderer(manager, Minecraft.getInstance().getItemRenderer())).register();
	public static final EntityEntry<CrystalScarabGemItemEntity> CRYSTAL_SCARAB_GEM_ITEM = REGISTRATE.<CrystalScarabGemItemEntity>entity("crystal_scarab_gem_item", CrystalScarabGemItemEntity::new, EntityClassification.MISC).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> manager -> new ItemRenderer(manager, Minecraft.getInstance().getItemRenderer())).register();

	public static final EntityEntry<AllosaurusEntity> ALLOSAURUS = REGISTRATE.entity("allosaurus", AllosaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(2.0F, 1.5F)).renderer(() -> AllosaurusRenderer::new).defaultSpawnEgg(0x9f9f5a, 0xd68812).attributes(() -> AllosaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<AnomalocarisEntity> ANOMALOCARIS = REGISTRATE.entity("anomalocaris", AnomalocarisEntity::new, EntityClassification.WATER_CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> AnomalocarisRenderer::new).defaultSpawnEgg(0xb94f33, 0x631312).attributes(() -> AnomalocarisEntity.createAttributes()).spawnPlacement(PlacementType.IN_WATER, Type.OCEAN_FLOOR, AnomalocarisEntity::canFishLikeSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<CarnotaurusEntity> CARNOTAURUS = REGISTRATE.entity("carnotaurus", CarnotaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(2.0F, 1.5F)).renderer(() -> CarnotaurusRenderer::new).defaultSpawnEgg(0xbd7868, 0xe6d3bc).attributes(() -> CarnotaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<ChilesaurusEntity> CHILESAURUS = REGISTRATE.entity("chilesaurus", ChilesaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> ChilesaurusRenderer::new).defaultSpawnEgg(0xb08533, 0x283c3f).attributes(() -> ChilesaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<CryolophosaurusEntity> CRYOLOPHOSAURUS = REGISTRATE.entity("cryolophosaurus", CryolophosaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(1.0F, 0.75F)).renderer(() -> CryolophosaurusRenderer::new).defaultSpawnEgg(0xab5a14, 0x1a2c5f).attributes(() -> CryolophosaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<DiictodonEntity> DIICTODON = REGISTRATE.entity("diictodon", DiictodonEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(0.25F, 0.25F)).renderer(() -> DiictodonRenderer::new).defaultSpawnEgg(0xdc8a54, 0x8b462e).attributes(() -> DiictodonEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<DilophosaurusEntity> DILOPHOSAURUS = REGISTRATE.entity("dilophosaurus", DilophosaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(1.5F, 1.5F)).renderer(() -> DilophosaurusRenderer::new).defaultSpawnEgg(0xc49838, 0xc75539).attributes(() -> DilophosaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<DimetrodonEntity> DIMETRODON = REGISTRATE.entity("dimetrodon", DimetrodonEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(1.5F, 1.5F)).renderer(() -> DimetrodonRenderer::new).defaultSpawnEgg(0x81644c, 0xcba755).attributes(() -> DimetrodonEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<EdaphosaurusEntity> EDAPHOSAURUS = REGISTRATE.entity("edaphosaurus", EdaphosaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> EdaphosaurusRenderer::new).defaultSpawnEgg(0x57614e, 0xdf9046).attributes(() -> EdaphosaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<EoraptorEntity> EORAPTOR = REGISTRATE.entity("eoraptor", EoraptorEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> EoraptorRenderer::new).defaultSpawnEgg(0x523c3e, 0x824b78).attributes(() -> EoraptorEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<FukuivenatorEntity> FUKUIVENATOR = REGISTRATE.entity("fukuivenator", FukuivenatorEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> FukuivenatorRenderer::new).defaultSpawnEgg(0x52526f, 0x5757959).attributes(() -> FukuivenatorEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<GiganotosaurusEntity> GIGANOTOSAURUS = REGISTRATE.entity("giganotosaurus", GiganotosaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(4.0F, 2.5F)).renderer(() -> GiganotosaurusRenderer::new).defaultSpawnEgg(0x9f6b41, 0x943c24).attributes(() -> GiganotosaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<GorgonopsEntity> GORGONOPS = REGISTRATE.entity("gorgonops", GorgonopsEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(1.0F, 1.0F)).renderer(() -> GorgonopsRenderer::new).defaultSpawnEgg(0x443619, 0x3b3e2d).attributes(() -> GorgonopsEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<GreatAukEntity> GREAT_AUK = REGISTRATE.entity("great_auk", GreatAukEntity::new, SEMI_AQUATIC_CREATURE).properties(properties -> properties.sized(0.75F, 1.5F)).renderer(() -> GreatAukRenderer::new).defaultSpawnEgg(0x000000, 0xFFFFFF).attributes(() -> GreatAukEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, GreatAukEntity::canGreatAukSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<KentrosaurusEntity> KENTROSAURUS = REGISTRATE.entity("kentrosaurus", KentrosaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(1.0F, 0.75F)).renderer(() -> KentrosaurusRenderer::new).defaultSpawnEgg(0xd99760, 0x612c00).attributes(() -> KentrosaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<LiaoningosaurusEntity> LIAONINGOSAURUS = REGISTRATE.entity("liaoningosaurus", LiaoningosaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(1.0F, 0.75F)).renderer(() -> LiaoningosaurusRenderer::new).defaultSpawnEgg(0x712d0d, 0x7c8237).attributes(() -> LiaoningosaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<NautilusEntity> NAUTILUS = REGISTRATE.entity("nautilus", NautilusEntity::new, EntityClassification.WATER_CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> NautilusRenderer::new).defaultSpawnEgg(0xd4ccc3, 0xca7548).attributes(() -> NautilusEntity.createAttributes()).spawnPlacement(PlacementType.IN_WATER, Type.OCEAN_FLOOR, NautilusEntity::canFishLikeSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<OphthalmosaurusEntity> OPHTHALMOSAURUS = REGISTRATE.entity("ophthalmosaurus", OphthalmosaurusEntity::new, EntityClassification.WATER_CREATURE).properties(properties -> properties.sized(2.0F, 2.0F)).renderer(() -> OphthalmosaurusRenderer::new).defaultSpawnEgg(0x858794, 0x0e131b).attributes(() -> OphthalmosaurusEntity.createAttributes()).spawnPlacement(PlacementType.IN_WATER, Type.OCEAN_FLOOR, OphthalmosaurusEntity::canDolphinLikeSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<OstromiaEntity> OSTROMIA = REGISTRATE.entity("ostromia", OstromiaEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> OstromiaRenderer::new).defaultSpawnEgg(0x47a373, 0x2c4d86).attributes(() -> OstromiaEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<OuranosaurusEntity> OURANOSAURUS = REGISTRATE.entity("ouranosaurus", OuranosaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(2.5F, 2.5F)).renderer(() -> OuranosaurusRenderer::new).defaultSpawnEgg(0x999554, 0x90bdb4).attributes(() -> OuranosaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<PalaeoniscumEntity> PALAEONISCUM = REGISTRATE.entity("palaeoniscum", PalaeoniscumEntity::new, EntityClassification.WATER_CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> PalaeoniscumRenderer::new).defaultSpawnEgg(0x72797a, 0x2f3a3d).attributes(() -> PalaeoniscumEntity.createAttributes()).spawnPlacement(PlacementType.IN_WATER, Type.OCEAN_FLOOR, PalaeoniscumEntity::canFishLikeSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<ProcompsognathusEntity> PROCOMPSOGNATHUS = REGISTRATE.entity("procompsognathus", ProcompsognathusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> ProcompsognathusRenderer::new).defaultSpawnEgg(0x445a2f, 0x404727).attributes(() -> ProcompsognathusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<ProtosuchusEntity> PROTOSUCHUS = REGISTRATE.entity("protosuchus", ProtosuchusEntity::new, EntityClassification.WATER_CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> ProtosuchusRenderer::new).defaultSpawnEgg(0x8e2317, 0xb0492e).attributes(() -> ProtosuchusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<PsittacosaurusEntity> PSITTACOSAURUS = REGISTRATE.entity("psittacosaurus", PsittacosaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> PsittacosaurusRenderer::new).defaultSpawnEgg(0x4c2c21, 0x938639).attributes(() -> PsittacosaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<RhinesuchusEntity> RHINESUCHUS = REGISTRATE.entity("rhinesuchus", RhinesuchusEntity::new, EntityClassification.WATER_CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> RhinesuchusRenderer::new).defaultSpawnEgg(0x576b54, 0xaf944a).attributes(() -> RhinesuchusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<SuchomimusEntity> SUCHOMIMUS = REGISTRATE.entity("suchomimus", SuchomimusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(1.5F, 1.5F)).renderer(() -> SuchomimusRenderer::new).defaultSpawnEgg(0x57737b, 0xcd9528).attributes(() -> SuchomimusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<UtahraptorEntity> UTAHRAPTOR = REGISTRATE.entity("utahraptor", UtahraptorEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(1.5F, 1.0F)).renderer(() -> UtahraptorRenderer::new).defaultSpawnEgg(0x503524, 0x635f5e).attributes(() -> UtahraptorEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<TetraceratopsEntity> TETRACERATOPS = REGISTRATE.entity("tetraceratops", TetraceratopsEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(0.75F, 0.5F)).renderer(() -> TetraceratopsRenderer::new).defaultSpawnEgg(0x623015, 0x21369b).attributes(() -> TetraceratopsEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<TyrannosaurusEntity> TYRANNOSAURUS = REGISTRATE.entity("tyrannosaurus", TyrannosaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(2.75F, 2.5F)).renderer(() -> TyrannosaurusRenderer::new).defaultSpawnEgg(0x503524, 0x635f5e).attributes(() -> TyrannosaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();
	public static final EntityEntry<ZephyrosaurusEntity> ZEPHYROSAURUS = REGISTRATE.entity("zephyrosaurus", ZephyrosaurusEntity::new, EntityClassification.CREATURE).properties(properties -> properties.sized(0.5F, 0.5F)).renderer(() -> ZephyrosaurusRenderer::new).defaultSpawnEgg(0x577476, 0x9ba3a3).attributes(() -> ZephyrosaurusEntity.createAttributes()).spawnPlacement(PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn).tag(LostWorldsTags.ModEntityTypeTags.ANCIENT_CREATURES).loot((provider, entity) -> provider.add(entity, LootTable.lootTable())).register();

	static {
		for (DinoTypes dinos : DinoTypes.values()) {
			if (dinos != DinoTypes.NAUTILUS && dinos != DinoTypes.PALAEONISCUM && dinos != DinoTypes.ANOMALOCARIS) {
				EntityType<FossilEntity> dirtyskull = register("dirty_" + dinos.toString().toLowerCase() + "_skull", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtySkull(dirtyskull);
				EntityType<FossilEntity> dirtyarmbones = register("dirty_" + dinos.toString().toLowerCase() + "_arm_bones", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyArmBones(dirtyarmbones);
				EntityType<FossilEntity> dirtylegbones = register("dirty_" + dinos.toString().toLowerCase() + "_leg_bones", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyLegBones(dirtylegbones);
				EntityType<FossilEntity> dirtyribcage = register("dirty_" + dinos.toString().toLowerCase() + "_rib_cage", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyRibCage(dirtyribcage);
				EntityType<FossilEntity> dirtytail = register("dirty_" + dinos.toString().toLowerCase() + "_tail", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyTail(dirtytail);
				EntityType<FossilEntity> skull = register(dinos.toString().toLowerCase() + "_skull", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setSkull(skull);
				EntityType<FossilEntity> arm_bones = register(dinos.toString().toLowerCase() + "_arm_bones", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setArmBones(arm_bones);
				EntityType<FossilEntity> leg_bones = register(dinos.toString().toLowerCase() + "_leg_bones", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setLegBones(leg_bones);
				EntityType<FossilEntity> rib_cage = register(dinos.toString().toLowerCase() + "_rib_cage", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setRibCage(rib_cage);
				EntityType<FossilEntity> tail = register(dinos.toString().toLowerCase() + "_tail", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setTail(tail);
			}

			if (dinos == DinoTypes.ANOMALOCARIS) {
				EntityType<FossilEntity> dirtyExoskeleton = register("dirty_" + dinos.toString().toLowerCase() + "_exoskeleton", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyExoskeleton(dirtyExoskeleton);
				EntityType<FossilEntity> exoskeleton = register(dinos.toString().toLowerCase() + "_exoskeleton", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setExoskeleton(exoskeleton);
			}

			if (dinos == DinoTypes.PALAEONISCUM) {
				EntityType<FossilEntity> dirtyBody = register("dirty_" + dinos.toString().toLowerCase() + "_body", DirtyFossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setDirtyBody(dirtyBody);
				EntityType<FossilEntity> body = register(dinos.toString().toLowerCase() + "_body", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
				dinos.setBody(body);
			}
		}
	}

	public static <T extends Entity> EntityType<T> register(String id, IFactory<T> factory, EntityClassification classification, float width, float height) {
		EntityType<T> entityType = EntityType.Builder.of(factory, classification).sized(width, height).build(id);
		entityType.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.ENTITIES.register(entityType);
		return entityType;
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
	}

	public static void init() {
	}
}
