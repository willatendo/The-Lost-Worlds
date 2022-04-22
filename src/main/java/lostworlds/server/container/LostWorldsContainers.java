package lostworlds.server.container;

import static lostworlds.LostWorldsMod.getRegistrate;

import com.tterrag.registrate.util.entry.ContainerEntry;

import lostworlds.client.screen.AnalyzerScreen;
import lostworlds.client.screen.ArchaeologyTableScreen;
import lostworlds.client.screen.CultivatorScreen;
import lostworlds.client.screen.DNAExtractorScreen;
import lostworlds.client.screen.DNAInjectorScreen;
import lostworlds.client.screen.DisplayCaseScreen;
import lostworlds.client.screen.FeedingTroughScreen;
import lostworlds.client.screen.FossilCleanerScreen;
import lostworlds.client.screen.FossilGrinderScreen;
import lostworlds.client.screen.PaleontologyTableScreen;
import lostworlds.client.screen.TimeMachineScreen;
import lostworlds.server.util.LostWorldsRegistrate;

public class LostWorldsContainers {
	public static final LostWorldsRegistrate REGISTRATE = getRegistrate();

	public static final ContainerEntry<FossilCleanerContainer> FOSSIL_CLEANER_CONTAINER = REGISTRATE.container("fossil_cleaner_container", FossilCleanerContainer::new, () -> FossilCleanerScreen::new).register();
	public static final ContainerEntry<FossilGrinderContainer> FOSSIL_GRINDER_CONTAINER = REGISTRATE.container("fossil_grinder_container", FossilGrinderContainer::new, () -> FossilGrinderScreen::new).register();
	public static final ContainerEntry<DNAExtractorContainer> DNA_EXTRACTOR_CONTAINER = REGISTRATE.container("dna_extractor_container", DNAExtractorContainer::new, () -> DNAExtractorScreen::new).register();
	public static final ContainerEntry<AnalyzerContainer> ANALYZER_CONTAINER = REGISTRATE.container("analyzer_container", AnalyzerContainer::new, () -> AnalyzerScreen::new).register();
	public static final ContainerEntry<DNAInjectorContainer> DNA_INJECTOR_CONTAINER = REGISTRATE.container("dna_injector_container", DNAInjectorContainer::new, () -> DNAInjectorScreen::new).register();
	public static final ContainerEntry<CultivatorContainer> CULTIVATOR_CONTAINER = REGISTRATE.container("cultivator_container", CultivatorContainer::new, () -> CultivatorScreen::new).register();

	public static final ContainerEntry<TimeMachineContainer> TIME_MACHINE_CONTAINER = REGISTRATE.container("time_machine_container", TimeMachineContainer::new, () -> TimeMachineScreen::new).register();

	public static final ContainerEntry<FeedingTroughContainer> FEEDING_TROUGH_CONTAINER = REGISTRATE.container("feeding_trough_container", FeedingTroughContainer::new, () -> FeedingTroughScreen::new).register();

	public static final ContainerEntry<DisplayCaseContainer> DISPLAY_CASE_CONTAINER = REGISTRATE.container("display_case_container", DisplayCaseContainer::new, () -> DisplayCaseScreen::new).register();

	public static final ContainerEntry<ArchaeologyTableContainer> ARCHAEOLOGY_CONTAINER = REGISTRATE.container("archaeology_table_container", ArchaeologyTableContainer::new, () -> ArchaeologyTableScreen::new).register();
	public static final ContainerEntry<PaleontologyTableContainer> PALEONTOLOGY_CONTAINER = REGISTRATE.container("paleontology_table_container", PaleontologyTableContainer::new, () -> PaleontologyTableScreen::new).register();

	public static void init() {
	}
}
