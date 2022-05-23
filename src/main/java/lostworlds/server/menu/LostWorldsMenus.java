package lostworlds.server.menu;

import static lostworlds.LostWorldsMod.getRegistrate;

import com.tterrag.registrate.util.entry.MenuEntry;

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
import lostworlds.server.util.registrate.LostWorldsRegistrate;

public class LostWorldsMenus {
	public static final LostWorldsRegistrate REGISTRATE = getRegistrate();

	public static final MenuEntry<FossilCleanerMenu> FOSSIL_CLEANER_CONTAINER = REGISTRATE.menu("fossil_cleaner", FossilCleanerMenu::new, () -> FossilCleanerScreen::new).register();
	public static final MenuEntry<FossilGrinderMenu> FOSSIL_GRINDER_CONTAINER = REGISTRATE.menu("fossil_grinder", FossilGrinderMenu::new, () -> FossilGrinderScreen::new).register();
	public static final MenuEntry<DNAExtractorMenu> DNA_EXTRACTOR_CONTAINER = REGISTRATE.menu("dna_extractor", DNAExtractorMenu::new, () -> DNAExtractorScreen::new).register();
	public static final MenuEntry<AnalyzerMenu> ANALYZER_CONTAINER = REGISTRATE.menu("analyzer", AnalyzerMenu::new, () -> AnalyzerScreen::new).register();
	public static final MenuEntry<DNAInjectorMenu> DNA_INJECTOR_CONTAINER = REGISTRATE.menu("dna_injector", DNAInjectorMenu::new, () -> DNAInjectorScreen::new).register();
	public static final MenuEntry<CultivatorMenu> CULTIVATOR_CONTAINER = REGISTRATE.menu("cultivator", CultivatorMenu::new, () -> CultivatorScreen::new).register();

	public static final MenuEntry<TimeMachineMenu> TIME_MACHINE_CONTAINER = REGISTRATE.menu("time_machine", TimeMachineMenu::new, () -> TimeMachineScreen::new).register();

	public static final MenuEntry<FeedingTroughMenu> FEEDING_TROUGH_CONTAINER = REGISTRATE.menu("feeding_trough", FeedingTroughMenu::new, () -> FeedingTroughScreen::new).register();

	public static final MenuEntry<DisplayCaseMenu> DISPLAY_CASE_CONTAINER = REGISTRATE.menu("display_case", DisplayCaseMenu::new, () -> DisplayCaseScreen::new).register();

	public static final MenuEntry<ArchaeologyTableMenu> ARCHAEOLOGY_CONTAINER = REGISTRATE.menu("archaeology_table", ArchaeologyTableMenu::new, () -> ArchaeologyTableScreen::new).register();
	public static final MenuEntry<PaleontologyTableMenu> PALEONTOLOGY_CONTAINER = REGISTRATE.menu("paleontology_table", PaleontologyTableMenu::new, () -> PaleontologyTableScreen::new).register();

	public static void registrate() {
	}
}
