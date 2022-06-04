package lostworlds.data.tag;

import org.jetbrains.annotations.Nullable;

import lostworlds.data.custom.SpeciesTypeTagProvider;
import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.species.LostWorldsSpeciesTypes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LostWorldsSpeciesTypeTagProvider extends SpeciesTypeTagProvider {
	public LostWorldsSpeciesTypeTagProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGenerator, LostWorldsUtils.ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(LostWorldsTags.ModSpeciesTypeTags.ALLOSAURUS).add(LostWorldsSpeciesTypes.ALLOSAURUS_FRAGILIS.get());
	}
}
