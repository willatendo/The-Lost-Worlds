package lostworlds.server.species;

import lostworlds.server.LostWorldsRegistries;
import lostworlds.server.LostWorldsUtils;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsSpeciesTypes {
	public static final DeferredRegister<SpeciesType> SPECIES_TYPES = DeferredRegister.create(LostWorldsRegistries.SPECIES_TYPES, LostWorldsUtils.ID);

	public static final RegistryObject<SpeciesType> ALLOSAURUS_FRAGILIS = SPECIES_TYPES.register("allosaurus_fragilis", () -> new SpeciesType(LostWorldsUtils.rL("geo/allosaurus.geo.json"), LostWorldsUtils.rL("textures/model/entity/allosaurus/texture.png")));
}
