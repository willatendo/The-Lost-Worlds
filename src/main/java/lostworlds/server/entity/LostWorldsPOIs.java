package lostworlds.server.entity;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsPOIs {
	public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, LostWorldsUtils.ID);

	public static final RegistryObject<PoiType> ARCHAEOLOGY_TABLE = POI_TYPES.register("archaeology_table", () -> new PoiType("archaeology_table", PoiType.getBlockStates(LostWorldsBlocks.ARCHAEOLOGY_TABLE.get()), 1, 1));
	public static final RegistryObject<PoiType> PALEONTOLOGY_TABLE = POI_TYPES.register("paleontology_table", () -> new PoiType("paleontology_table", PoiType.getBlockStates(LostWorldsBlocks.PALEONTOLOGY_TABLE.get()), 1, 1));

	// Registry
	public static void deferred(IEventBus bus) {
		POI_TYPES.register(bus);
	}
}
