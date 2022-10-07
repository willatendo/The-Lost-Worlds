package lostworlds.data;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LostWorldsTranslationLanguageProvider extends LanguageProvider {
	private final AddTranslations translations;

	public LostWorldsTranslationLanguageProvider(DataGenerator dataGenerator, String local, AddTranslations addTranslations) {
		super(dataGenerator, LostWorldsUtils.ID, local);
		this.translations = addTranslations;
	}

	@Override
	protected void addTranslations() {
		this.translations.run(this);
	}

	public static interface AddTranslations {
		void run(LanguageProvider languageProvider);
	}
}
