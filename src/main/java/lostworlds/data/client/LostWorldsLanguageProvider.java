package lostworlds.data.client;

import lostworlds.LostWorldsMod;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

public class LostWorldsLanguageProvider extends LanguageProvider {
	public LostWorldsLanguageProvider(DataGenerator gen) {
		super(gen, LostWorldsMod.ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.addToolTip(LostWorldsItems.LOST_WORLDS_LEXICON.get(), "An Everything-You-Need-to-Know Book!");
	}
	
	public void addToolTip(Item key, String name) {
		this.add("item.lostworlds." + key.getRegistryName().getPath() + ".desc", name);
	}
}
