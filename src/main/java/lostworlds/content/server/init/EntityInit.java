package lostworlds.content.server.init;

import lostworlds.library.entity.illager.FossilPoacherEntity;
import lostworlds.library.item.builder.SpawnEggItemBuilder;
import lostworlds.library.util.ModUtils;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD)
public class EntityInit 
{	
	public static final EntityType<FossilPoacherEntity> FOSSIL_POACHER = EntityType.Builder.of(FossilPoacherEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).build("fossil_poacher");
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) 
	{
		GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) EntityInit.FOSSIL_POACHER, FossilPoacherEntity.createAttributes());

		event.getRegistry().register(FOSSIL_POACHER.setRegistryName(ModUtils.rL("fossil_poacher")));
		
		SpawnEggItemBuilder.initSpawnEggs();
	}
	
	public static void init() { }
}
