package lostworlds.data.tag;

import org.jetbrains.annotations.Nullable;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.structure.LostWorldsConfiguredStructures;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LostWorldsConfiguredStructureTagProvider extends StructureTagsProvider {
	public LostWorldsConfiguredStructureTagProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGenerator, LostWorldsUtils.ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(LostWorldsTags.ModConfiguredStructureTags.FOSSIL_MAP_LOCATION).add(LostWorldsConfiguredStructures.SURFACE_FOSSIL_KEY).add(LostWorldsConfiguredStructures.SUBTERRANEAN_FOSSIL_KEY);
	}
}
