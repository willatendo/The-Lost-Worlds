package lostworlds.server.entity;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsPOIs {
	public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, LostWorldsUtils.ID);

	public static final RegistryObject<PointOfInterestType> ARCHAEOLOGY_TABLE = POI_TYPES.register("archaeology_table", () -> new PointOfInterestType("archaeology_table", PointOfInterestType.getBlockStates(LostWorldsBlocks.ARCHAEOLOGY_TABLE.get()), 1, 1));
	public static final RegistryObject<PointOfInterestType> PALEONTOLOGY_TABLE = POI_TYPES.register("paleontology_table", () -> new PointOfInterestType("paleontology_table", PointOfInterestType.getBlockStates(LostWorldsBlocks.PALEONTOLOGY_TABLE.get()), 1, 1));
	public static final RegistryObject<PointOfInterestType> PALEOBOTANY_TABLE = POI_TYPES.register("paleobotany_table", () -> new PointOfInterestType("paleobotany_table", PointOfInterestType.getBlockStates(LostWorldsBlocks.PALEOBOTANY_TABLE.get()), 1, 1));

	// Registry
	public static void init(IEventBus bus) {
		POI_TYPES.register(bus);
		LostWorldsUtils.LOGGER.debug("Registering Mod Points of Interest");
	}
}
