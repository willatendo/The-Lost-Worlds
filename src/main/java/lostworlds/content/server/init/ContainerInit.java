package lostworlds.content.server.init;

import java.lang.reflect.Field;

import lostworlds.library.container.AnalyserContainer;
import lostworlds.library.container.ArchaeologyTableContianer;
import lostworlds.library.container.DNAExtractorContainer;
import lostworlds.library.container.DNAInjectorContainer;
import lostworlds.library.container.FossilCleanerContainer;
import lostworlds.library.container.FossilGrinderContainer;
import lostworlds.library.container.TimeMachineContainer;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtil;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

@Mod.EventBusSubscriber(modid = ModUtil.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerInit 
{
	public static final RegistryObject<ContainerType<FossilCleanerContainer>> FOSSIL_CLEANER_CONTAINER = ModRegistry.CONTAINER_REGISTRY.register("fossil_cleaner_container", () -> IForgeContainerType.create(FossilCleanerContainer::new));
	public static final RegistryObject<ContainerType<FossilGrinderContainer>> FOSSIL_GRINDER_CONTAINER = ModRegistry.CONTAINER_REGISTRY.register("fossil_grinder_container", () -> IForgeContainerType.create(FossilGrinderContainer::new));
	public static final RegistryObject<ContainerType<DNAExtractorContainer>> DNA_EXTRACTOR_CONTAINER = ModRegistry.CONTAINER_REGISTRY.register("dna_extractor_container", () -> IForgeContainerType.create(DNAExtractorContainer::new));
	public static final RegistryObject<ContainerType<AnalyserContainer>> ANALYSER_CONTAINER = ModRegistry.CONTAINER_REGISTRY.register("analyser_container", () -> IForgeContainerType.create(AnalyserContainer::new));
	public static final RegistryObject<ContainerType<DNAInjectorContainer>> DNA_INJECTOR_CONTAINER = ModRegistry.CONTAINER_REGISTRY.register("dna_injector_container", () -> IForgeContainerType.create(DNAInjectorContainer::new));

	public static final RegistryObject<ContainerType<TimeMachineContainer>> TIME_MACHINE_CONTAINER = ModRegistry.CONTAINER_REGISTRY.register("time_machine_container", () -> IForgeContainerType.create(TimeMachineContainer::new));

	public static final RegistryObject<ContainerType<ArchaeologyTableContianer>> ARCHAEOLOGY_CONTAINER = ModRegistry.CONTAINER_REGISTRY.register("archaeology_table_container", () -> IForgeContainerType.create(ArchaeologyTableContianer::new));
	
	//Registry
	@SubscribeEvent
	public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) 
	{
		try 
		{
			for(Field f : ContainerInit.class.getDeclaredFields()) 
			{
				Object obj = f.get(null);
				if(obj instanceof ContainerType) 
				{
					event.getRegistry().register((ContainerType) obj);
				} 
				else if(obj instanceof ContainerType[]) 
				{
					for(ContainerType container : (ContainerType[]) obj) 
					{
						event.getRegistry().register(container);
					}
				}
			}
		} 
		catch(IllegalAccessException e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void init() { ModUtil.LOGGER.debug("Registering Mod Container"); }
}
