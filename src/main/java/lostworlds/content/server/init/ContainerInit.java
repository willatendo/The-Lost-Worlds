package lostworlds.content.server.init;

import java.lang.reflect.Field;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.container.AnalyzerContainer;
import lostworlds.library.container.ArchaeologyTableContianer;
import lostworlds.library.container.CultivatorContainer;
import lostworlds.library.container.DNAExtractorContainer;
import lostworlds.library.container.DNAInjectorContainer;
import lostworlds.library.container.FossilCleanerContainer;
import lostworlds.library.container.FossilGrinderContainer;
import lostworlds.library.container.TimeMachineContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModUtils.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerInit 
{
	public static final ContainerType<FossilCleanerContainer> FOSSIL_CLEANER_CONTAINER = ModRegistry.register("fossil_cleaner_container", IForgeContainerType.create(FossilCleanerContainer::new));
	public static final ContainerType<FossilGrinderContainer> FOSSIL_GRINDER_CONTAINER = ModRegistry.register("fossil_grinder_container", IForgeContainerType.create(FossilGrinderContainer::new));
	public static final ContainerType<DNAExtractorContainer> DNA_EXTRACTOR_CONTAINER = ModRegistry.register("dna_extractor_container", IForgeContainerType.create(DNAExtractorContainer::new));
	public static final ContainerType<AnalyzerContainer> ANALYZER_CONTAINER = ModRegistry.register("analyzer_container", IForgeContainerType.create(AnalyzerContainer::new));
	public static final ContainerType<DNAInjectorContainer> DNA_INJECTOR_CONTAINER = ModRegistry.register("dna_injector_container", IForgeContainerType.create(DNAInjectorContainer::new));
	public static final ContainerType<CultivatorContainer> CULTIVATOR_CONTAINER = ModRegistry.register("cultivator_container", IForgeContainerType.create(CultivatorContainer::new));

	public static final ContainerType<TimeMachineContainer> TIME_MACHINE_CONTAINER = ModRegistry.register("time_machine_container", IForgeContainerType.create(TimeMachineContainer::new));

	public static final ContainerType<ArchaeologyTableContianer> ARCHAEOLOGY_CONTAINER = ModRegistry.register("archaeology_table_container", IForgeContainerType.create(ArchaeologyTableContianer::new));
	
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
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Container"); }
}
