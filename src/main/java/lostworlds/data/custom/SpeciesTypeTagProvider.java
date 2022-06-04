package lostworlds.data.custom;

import com.mojang.serialization.Lifecycle;

import lostworlds.server.LostWorldsRegistries;
import lostworlds.server.species.SpeciesType;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class SpeciesTypeTagProvider extends TagsProvider<SpeciesType> {
	private static <T extends IForgeRegistryEntry<T>> Registry<T> wrapRegistry(IForgeRegistry<T> forgeRegistry) {
		if (forgeRegistry.tags() == null)
			throw new IllegalArgumentException("Forge registry " + forgeRegistry.getRegistryName() + " does not have support for tags");
		if (forgeRegistry.getDefaultKey() == null)
			return GameData.getWrapper(forgeRegistry.getRegistryKey(), Lifecycle.experimental());
		return GameData.getWrapper(forgeRegistry.getRegistryKey(), Lifecycle.experimental(), "default");
	}

	public SpeciesTypeTagProvider(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
		super(generator, wrapRegistry(LostWorldsRegistries.SPECIES_TYPES_REGISTRY.get()), modId, existingFileHelper);
	}

	@Override
	public String getName() {
		return "Species Type Tags";
	}
}
