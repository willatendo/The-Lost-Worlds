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
		this.tag(LostWorldsTags.ModSpeciesTypeTags.ABSALOMICHTHYS).add(LostWorldsSpeciesTypes.ABSALOMICHTHYS_VELIFER.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.ALLOSAURUS).add(LostWorldsSpeciesTypes.ALLOSAURUS_FRAGILIS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.ANOMALOCARIS).add(LostWorldsSpeciesTypes.ANOMALOCARIS_CANADENSIS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.ANUROGNATHUS).add(LostWorldsSpeciesTypes.ANUROGNATHUS_AMMONI.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.CARNOTAURUS).add(LostWorldsSpeciesTypes.CARNOTAURUS_SASTREI.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.CHILESAURUS).add(LostWorldsSpeciesTypes.CHILESAURUS_DIEGOSUAREZI.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.CRYOLOPHOSAURUS).add(LostWorldsSpeciesTypes.CRYOLOPHOSAURUS_ELLIOTI_A.get(), LostWorldsSpeciesTypes.CRYOLOPHOSAURUS_ELLIOTI_B.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.CRYPTOCLIDUS).add(LostWorldsSpeciesTypes.CRYPTOCLIDUS_EURYMERUS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.DIICTODON).add(LostWorldsSpeciesTypes.DIICTODON_FELICEPS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.DILOPHOSAURUS).add(LostWorldsSpeciesTypes.DILOPHOSAURUS_WETHERILLI.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.DIMETRODON).add(LostWorldsSpeciesTypes.DIMETRODON_LIMBATUS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.EDAPHOSAURUS).add(LostWorldsSpeciesTypes.EDAPHOSAURUS_POGONIAS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.EORAPTOR).add(LostWorldsSpeciesTypes.EORAPTOR_LUNENSIS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.EUSTREPTOSPONDYLUS).add(LostWorldsSpeciesTypes.EUSTREPTOSPONDYLUS_OXONIENSIS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.FUKUIVENATOR).add(LostWorldsSpeciesTypes.FUKUIVENATOR_PARADOXUS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.GIGANOTOSAURUS).add(LostWorldsSpeciesTypes.GIGANOTOSAURUS_CAROLINII.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.GORGONOPS).add(LostWorldsSpeciesTypes.GORGONOPS_TORVUS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.GREAT_AUK).add(LostWorldsSpeciesTypes.PINGUINUS_IMPENNIS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.KENTROSAURUS).add(LostWorldsSpeciesTypes.KENTROSAURUS_AETHIOPICUS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.LIAONINGOSAURUS).add(LostWorldsSpeciesTypes.LIAONINGOSAURUS_PARADOXUS_A.get(), LostWorldsSpeciesTypes.LIAONINGOSAURUS_PARADOXUS_B.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.MESOSAURUS).add(LostWorldsSpeciesTypes.MESOSAURUS_TENUIDENS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.NAUTILUS).add(LostWorldsSpeciesTypes.NAUTILUS_POMPILIUS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.OPHTHALMOSAURUS).add(LostWorldsSpeciesTypes.OPHTHALMOSAURUS_ICENICUS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.OSTROMIA).add(LostWorldsSpeciesTypes.OSTROMIA_CRASSIPES.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.OURANOSAURUS).add(LostWorldsSpeciesTypes.OURANOSAURUS_NIGERIENSIS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.PARKSOSAURUS).add(LostWorldsSpeciesTypes.PARKSOSAURUS_WARRENI.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.PALAEONISCUM).add(LostWorldsSpeciesTypes.PALAEONISCUM_FREIESLEBENI.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.PROCOMPSOGNATHUS).add(LostWorldsSpeciesTypes.PROCOMPSOGNATHUS_TRIASSICUS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.PROTOSUCHUS).add(LostWorldsSpeciesTypes.PROTOSUCHUS_RICHARDSONI.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.PSITTACOSAURUS).add(LostWorldsSpeciesTypes.PSITTACOSAURUS_MONGOLIENSIS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.RHINESUCHUS).add(LostWorldsSpeciesTypes.RHINESUCHUS_WHAITSI.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.SUCHOMIMUS).add(LostWorldsSpeciesTypes.SUCHOMIMUS_TENERENSIS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.UTAHRAPTOR).add(LostWorldsSpeciesTypes.UTAHRAPTOR_OSTROMMAYSI.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.THANOS).add(LostWorldsSpeciesTypes.THANOS_SIMONATTOI_A.get(), LostWorldsSpeciesTypes.THANOS_SIMONATTOI_B.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.TETRACERATOPS).add(LostWorldsSpeciesTypes.TETRACERATOPS_INSIGNIS.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.TOYOTAMAPHIMEIA).add(LostWorldsSpeciesTypes.TOYOTAMAPHIMEIA_MACHIKANENSIS_A.get(), LostWorldsSpeciesTypes.TOYOTAMAPHIMEIA_MACHIKANENSIS_B.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.TYRANNOSAURUS).add(LostWorldsSpeciesTypes.TYRANNOSAURUS_REX.get());
		this.tag(LostWorldsTags.ModSpeciesTypeTags.ZEPHYROSAURUS).add(LostWorldsSpeciesTypes.ZEPHYROSAURUS_SCHAFFI_A.get(), LostWorldsSpeciesTypes.ZEPHYROSAURUS_SCHAFFI_B.get());
	}
}
