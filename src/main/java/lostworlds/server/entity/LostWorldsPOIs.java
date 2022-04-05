package lostworlds.server.entity;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsPOIs {
	private static Set<BlockState> getBlockStates(Block block) {
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}

	public static final PointOfInterestType ARCHAEOLOGY_TABLE = register("archaeology_table", new PointOfInterestType("archaeology_table", getBlockStates(LostWorldsBlocks.ARCHAEOLOGY_TABLE), 1, 1));
	public static final PointOfInterestType PALEONTOLOGY_TABLE = register("paleontology_table", new PointOfInterestType("paleontology_table", getBlockStates(LostWorldsBlocks.PALEONTOLOGY_TABLE), 1, 1));
	public static final PointOfInterestType PALEOBOTANY_TABLE = register("paleobotany_table", new PointOfInterestType("paleobotany_table", getBlockStates(LostWorldsBlocks.PALEOBOTANY_TABLE), 1, 1));

	public static PointOfInterestType register(String id, PointOfInterestType poi) {
		poi.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.POI_TYPES.register(poi);
		return poi;
	}

	// Registry
	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Points of Interest");
	}
}
