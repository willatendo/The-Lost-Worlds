package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.entity.DinoTypes;
import lostworlds.library.entity.ModBoatEntity;
import lostworlds.library.entity.fossil.FossilEntity;
import lostworlds.library.entity.illager.FossilPoacherEntity;
import lostworlds.library.entity.item.ChargedCrystalScarabGemItemEntity;
import lostworlds.library.entity.terrestrial.PrehistoricEntity;
import lostworlds.library.entity.terrestrial.jurassic.ChilesaurusEntity;
import lostworlds.library.entity.terrestrial.jurassic.KentrosaurusEntity;
import lostworlds.library.item.ModSpawnEggItem;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD)
public class EntityInit 
{	
	public static final EntityType<FossilPoacherEntity> FOSSIL_POACHER = EntityType.Builder.of(FossilPoacherEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).build("fossil_poacher");
	
	public static final EntityType<ModBoatEntity> MOD_BOAT = EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, EntityClassification.MISC).sized(1.375F, 0.5625F).build("mod_boat");
	public static final EntityType<ChargedCrystalScarabGemItemEntity> CHARGED_CRYSTAL_SCARAB_GEM_ITEM = ModRegistry.register("charged_crystal_scarab_gem_item", ChargedCrystalScarabGemItemEntity::new, EntityClassification.MISC, 0.5F, 0.5F);
	
	public static final EntityType<ChilesaurusEntity> CHILESAURUS = ModRegistry.register("chilesaurus", ChilesaurusEntity::new, EntityClassification.CREATURE, 1.0F, 1.0F);
	public static final EntityType<KentrosaurusEntity> KENTROSAURUS = ModRegistry.register("kentrosaurus", KentrosaurusEntity::new, EntityClassification.CREATURE, 1.0F, 0.75F);
	public static final EntityType<FossilEntity> KENTROSAURUS_SKELETON = ModRegistry.register("kentrosaurus_skeleton", FossilEntity::new, EntityClassification.MISC, 1.0F, 0.75F);
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) 
	{
		GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) EntityInit.FOSSIL_POACHER, FossilPoacherEntity.createAttributes());
		GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) EntityInit.CHILESAURUS, ChilesaurusEntity.createAttributes());
		GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) EntityInit.KENTROSAURUS, KentrosaurusEntity.createAttributes());
		GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) EntityInit.KENTROSAURUS_SKELETON, FossilEntity.createAttributes());
		
		for(DinoTypes dinos : DinoTypes.values())
		{
			GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) dinos.getArmBones(), FossilEntity.createAttributes());
			GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) dinos.getLegBones(), FossilEntity.createAttributes());
			GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) dinos.getRibCage(), FossilEntity.createAttributes());
			GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) dinos.getSkull(),FossilEntity.createAttributes());
			GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) dinos.getTail(), FossilEntity.createAttributes());
		}
		
		event.getRegistry().register(FOSSIL_POACHER.setRegistryName(ModUtils.rL("fossil_poacher")));
		event.getRegistry().register(MOD_BOAT.setRegistryName(ModUtils.rL("mod_boat")));
		
		ModSpawnEggItem.initSpawnEggs();
	}
	
	static
	{
		EntitySpawnPlacementRegistry.register(EntityInit.CHILESAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
		EntitySpawnPlacementRegistry.register(EntityInit.KENTROSAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
	}
	
	public static void init() 
	{
		ModUtils.LOGGER.debug("Registering Mod Enitites"); 
		
		for(DinoTypes dinoType : DinoTypes.values())
		{
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
			else
			{
				dinoType.setSkeleton(KENTROSAURUS_SKELETON);
			}
		}
	}
}
