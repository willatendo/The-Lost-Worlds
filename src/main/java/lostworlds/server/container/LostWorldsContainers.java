package lostworlds.server.container;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;

//@Mod.EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LostWorldsContainers {
	public static final ContainerType<FossilCleanerContainer> FOSSIL_CLEANER_CONTAINER = LostWorldsRegistry.register("fossil_cleaner_container", IForgeContainerType.create(FossilCleanerContainer::new));
	public static final ContainerType<FossilGrinderContainer> FOSSIL_GRINDER_CONTAINER = LostWorldsRegistry.register("fossil_grinder_container", IForgeContainerType.create(FossilGrinderContainer::new));
	public static final ContainerType<DNAExtractorContainer> DNA_EXTRACTOR_CONTAINER = LostWorldsRegistry.register("dna_extractor_container", IForgeContainerType.create(DNAExtractorContainer::new));
	public static final ContainerType<AnalyzerContainer> ANALYZER_CONTAINER = LostWorldsRegistry.register("analyzer_container", IForgeContainerType.create(AnalyzerContainer::new));
	public static final ContainerType<DNAInjectorContainer> DNA_INJECTOR_CONTAINER = LostWorldsRegistry.register("dna_injector_container", IForgeContainerType.create(DNAInjectorContainer::new));
	public static final ContainerType<CultivatorContainer> CULTIVATOR_CONTAINER = LostWorldsRegistry.register("cultivator_container", IForgeContainerType.create(CultivatorContainer::new));

	public static final ContainerType<TimeMachineContainer> TIME_MACHINE_CONTAINER = LostWorldsRegistry.register("time_machine_container", IForgeContainerType.create(TimeMachineContainer::new));

	public static final ContainerType<FeedingTroughContainer> FEEDING_TROUGH_CONTAINER = LostWorldsRegistry.register("feeding_trough_container", IForgeContainerType.create(FeedingTroughContainer::create));

	public static final ContainerType<DisplayCaseContainer> DISPLAY_CASE_CONTAINER = LostWorldsRegistry.register("display_case_container", IForgeContainerType.create(DisplayCaseContainer::create));

	public static final ContainerType<ArchaeologyTableContainer> ARCHAEOLOGY_CONTAINER = LostWorldsRegistry.register("archaeology_table_container", IForgeContainerType.create(ArchaeologyTableContainer::new));
	public static final ContainerType<PaleontologyTableContainer> PALEONTOLOGY_CONTAINER = LostWorldsRegistry.register("paleontology_table_container", IForgeContainerType.create(PaleontologyTableContainer::new));

	// Registry
//	@SubscribeEvent
//	public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
//		try {
//			for (Field f : LostWorldsContainers.class.getDeclaredFields()) {
//				Object obj = f.get(null);
//				if (obj instanceof ContainerType) {
//					event.getRegistry().register((ContainerType) obj);
//				} else if (obj instanceof ContainerType[]) {
//					for (ContainerType container : (ContainerType[]) obj) {
//						event.getRegistry().register(container);
//					}
//				}
//			}
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		}
//	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Container");
	}
}
