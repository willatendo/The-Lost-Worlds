package lostworlds.server.entity;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.village.PointOfInterestType;

public class LostWorldsPOIs {
	private static Set<BlockState> getBlockStates(Block block) {
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}

	public static final PointOfInterestType ARCHAEOLOGY_TABLE = LostWorldsRegistry.register("archaeology_table", new PointOfInterestType("archaeology_table", getBlockStates(LostWorldsBlocks.ARCHAEOLOGY_TABLE), 1, 1));
	public static final PointOfInterestType PALEONTOLOGY_TABLE = LostWorldsRegistry.register("paleontology_table", new PointOfInterestType("paleontology_table", getBlockStates(LostWorldsBlocks.PALEONTOLOGY_TABLE), 1, 1));
	public static final PointOfInterestType PALEOBOTANY_TABLE = LostWorldsRegistry.register("paleobotany_table", new PointOfInterestType("paleobotany_table", getBlockStates(LostWorldsBlocks.PALEOBOTANY_TABLE), 1, 1));

	// Registry
	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Points of Interest");
	}
}
