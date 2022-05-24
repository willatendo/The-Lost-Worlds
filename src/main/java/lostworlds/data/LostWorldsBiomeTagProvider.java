package lostworlds.data;

import org.jetbrains.annotations.Nullable;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.biome.LostWorldsBiomes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LostWorldsBiomeTagProvider extends BiomeTagsProvider {
	public LostWorldsBiomeTagProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGenerator, LostWorldsUtils.ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(LostWorldsTags.ModBiomeTags.HAS_BLACK_MARKET).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_JUNGLE).addTag(BiomeTags.HAS_VILLAGE_DESERT).addTag(BiomeTags.HAS_VILLAGE_SAVANNA);
		this.tag(LostWorldsTags.ModBiomeTags.HAS_METEORITE).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_JUNGLE).addTag(BiomeTags.HAS_VILLAGE_DESERT).addTag(BiomeTags.HAS_VILLAGE_SAVANNA);
		this.tag(LostWorldsTags.ModBiomeTags.HAS_TRACE_FOSSIL).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_JUNGLE).addTag(BiomeTags.HAS_VILLAGE_DESERT).addTag(BiomeTags.HAS_VILLAGE_SAVANNA);
		this.tag(LostWorldsTags.ModBiomeTags.HAS_SURFACE_FOSSIL).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_JUNGLE).addTag(BiomeTags.HAS_VILLAGE_DESERT).addTag(BiomeTags.HAS_VILLAGE_SAVANNA).addTag(BiomeTags.IS_BADLANDS).addTag(BiomeTags.IS_MOUNTAIN).addTag(BiomeTags.IS_HILL);
		this.tag(LostWorldsTags.ModBiomeTags.HAS_SUBTERRANEAN_FOSSIL).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_JUNGLE).addTag(BiomeTags.HAS_VILLAGE_DESERT).addTag(BiomeTags.HAS_VILLAGE_SAVANNA).addTag(BiomeTags.IS_BADLANDS).addTag(BiomeTags.IS_MOUNTAIN).addTag(BiomeTags.IS_HILL);

		this.tag(BiomeTags.HAS_RUINED_PORTAL_STANDARD).add(LostWorldsBiomes.ARAUCARIA_FOREST.getSecond()).add(LostWorldsBiomes.CONIFER_FOREST.getSecond()).add(LostWorldsBiomes.GINKGO_FOREST.getSecond()).add(LostWorldsBiomes.REDWOODS_FOREST.getSecond());
	}
}
