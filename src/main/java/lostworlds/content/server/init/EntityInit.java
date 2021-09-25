package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.entity.DinoTypes;
import lostworlds.library.entity.ModBoatEntity;
import lostworlds.library.entity.fossil.FossilEntity;
import lostworlds.library.entity.illager.FossilPoacherEntity;
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
	
	public static final EntityType<ChilesaurusEntity> CHILESAURUS = ModRegistry.register("chilesaurus", ChilesaurusEntity::new, EntityClassification.CREATURE, 1.0F, 1.0F);
	public static final EntityType<KentrosaurusEntity> KENTROSAURUS = ModRegistry.register("kentrosaurus", KentrosaurusEntity::new, EntityClassification.CREATURE, 2.0F, 1.5F);
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) 
	{
		GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) EntityInit.FOSSIL_POACHER, FossilPoacherEntity.createAttributes());
		GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) EntityInit.CHILESAURUS, ChilesaurusEntity.createAttributes());
		GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) EntityInit.KENTROSAURUS, KentrosaurusEntity.createAttributes());

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
			EntityType<FossilEntity> left_arm = ModRegistry.register(dinoType.toString().toLowerCase() + "_left_arm", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setLeftArm(left_arm);
			EntityType<FossilEntity> right_arm = ModRegistry.register(dinoType.toString().toLowerCase() + "_right_arm", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setRightArm(right_arm);
			EntityType<FossilEntity> left_leg = ModRegistry.register(dinoType.toString().toLowerCase() + "_left_leg", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setLeftLeg(left_leg);
			EntityType<FossilEntity> right_leg = ModRegistry.register(dinoType.toString().toLowerCase() + "_right_leg", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setRightLeg(right_leg);
			EntityType<FossilEntity> rib_cage = ModRegistry.register(dinoType.toString().toLowerCase() + "_rib_cage", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setRibCage(rib_cage);
			EntityType<FossilEntity> tail = ModRegistry.register(dinoType.toString().toLowerCase() + "_tail", FossilEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
			dinoType.setTail(tail);
		}
	}
}
