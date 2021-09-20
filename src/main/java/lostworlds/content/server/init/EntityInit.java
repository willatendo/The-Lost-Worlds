package lostworlds.content.server.init;

import lostworlds.library.entity.ModBoatEntity;
import lostworlds.library.entity.illager.FossilPoacherEntity;
import lostworlds.library.entity.terrestrial.PrehistoricEntity;
import lostworlds.library.entity.terrestrial.jurassic.ChilesaurusEntity;
import lostworlds.library.item.builder.SpawnEggItemBuilder;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
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
	
	public static final EntityType<ChilesaurusEntity> CHILESAURUS = ModRegistry.register("chilesaurs", ChilesaurusEntity::new, EntityClassification.CREATURE, 1.0F, 1.0F);
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) 
	{
		GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) EntityInit.FOSSIL_POACHER, FossilPoacherEntity.createAttributes());
		GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) EntityInit.CHILESAURUS, ChilesaurusEntity.createAttributes());

		event.getRegistry().register(FOSSIL_POACHER.setRegistryName(ModUtils.rL("fossil_poacher")));
		event.getRegistry().register(MOD_BOAT.setRegistryName(ModUtils.rL("mod_boat")));
		
		SpawnEggItemBuilder.initSpawnEggs();
	}
	
	static
	{
		EntitySpawnPlacementRegistry.register(EntityInit.CHILESAURUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrehistoricEntity::canPrehistoricSpawn);
	}
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Enitites"); }
}
