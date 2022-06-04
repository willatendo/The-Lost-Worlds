package lostworlds.server;

import java.util.function.Supplier;

import lostworlds.server.species.SpeciesType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

public class LostWorldsRegistries {
	public static final ResourceKey<Registry<SpeciesType>> SPECIES_TYPES = key("species_types");
	public static final DeferredRegister<SpeciesType> DEFERRED_SPECIES_TYPES = DeferredRegister.create(LostWorldsRegistries.SPECIES_TYPES, LostWorldsRegistries.SPECIES_TYPES.location().getNamespace());
	public static final Supplier<IForgeRegistry<SpeciesType>> SPECIES_TYPES_REGISTRY = DEFERRED_SPECIES_TYPES.makeRegistry(SpeciesType.class, () -> new RegistryBuilder().setName(LostWorldsRegistries.SPECIES_TYPES.location()).setType(SpeciesType.class).setMaxID(Integer.MAX_VALUE - 1).setDefaultKey(LostWorldsUtils.rL("default")).hasTags());

	private static <T> ResourceKey<Registry<T>> key(String name) {
		return ResourceKey.createRegistryKey(LostWorldsUtils.rL(name));
	}

	public static void init() {
	}
}
