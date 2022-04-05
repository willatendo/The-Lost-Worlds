package lostworlds.server.container;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsContainers {
	public static final ContainerType<FossilCleanerContainer> FOSSIL_CLEANER_CONTAINER = register("fossil_cleaner_container", IForgeContainerType.create(FossilCleanerContainer::new));
	public static final ContainerType<FossilGrinderContainer> FOSSIL_GRINDER_CONTAINER = register("fossil_grinder_container", IForgeContainerType.create(FossilGrinderContainer::new));
	public static final ContainerType<DNAExtractorContainer> DNA_EXTRACTOR_CONTAINER = register("dna_extractor_container", IForgeContainerType.create(DNAExtractorContainer::new));
	public static final ContainerType<AnalyzerContainer> ANALYZER_CONTAINER = register("analyzer_container", IForgeContainerType.create(AnalyzerContainer::new));
	public static final ContainerType<DNAInjectorContainer> DNA_INJECTOR_CONTAINER = register("dna_injector_container", IForgeContainerType.create(DNAInjectorContainer::new));
	public static final ContainerType<CultivatorContainer> CULTIVATOR_CONTAINER = register("cultivator_container", IForgeContainerType.create(CultivatorContainer::new));

	public static final ContainerType<TimeMachineContainer> TIME_MACHINE_CONTAINER = register("time_machine_container", IForgeContainerType.create(TimeMachineContainer::new));

	public static final ContainerType<FeedingTroughContainer> FEEDING_TROUGH_CONTAINER = register("feeding_trough_container", IForgeContainerType.create(FeedingTroughContainer::create));

	public static final ContainerType<DisplayCaseContainer> DISPLAY_CASE_CONTAINER = register("display_case_container", IForgeContainerType.create(DisplayCaseContainer::create));

	public static final ContainerType<ArchaeologyTableContainer> ARCHAEOLOGY_CONTAINER = register("archaeology_table_container", IForgeContainerType.create(ArchaeologyTableContainer::new));
	public static final ContainerType<PaleontologyTableContainer> PALEONTOLOGY_CONTAINER = register("paleontology_table_container", IForgeContainerType.create(PaleontologyTableContainer::new));

	public static <T extends Container> ContainerType<T> register(String id, ContainerType<T> container) {
		container.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.CONTAINERS.register(container);
		return container;
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Container");
	}
}
