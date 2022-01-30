package lostworlds.content.server.init;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.village.PointOfInterestType;

public class PointOfInterestInit {
	private static Set<BlockState> getBlockStates(Block block) {
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}

	public static final PointOfInterestType ARCHAEOLOGY_TABLE = ModRegistry.register("archaeology_table", new PointOfInterestType("archaeology_table", getBlockStates(BlockInit.ARCHAEOLOGY_TABLE), 1, 1));
	public static final PointOfInterestType PALEONTOLOGY_TABLE = ModRegistry.register("paleontology_table", new PointOfInterestType("paleontology_table", getBlockStates(BlockInit.PALEONTOLOGY_TABLE), 1, 1));
	public static final PointOfInterestType PALEOBOTANY_TABLE = ModRegistry.register("paleobotany_table", new PointOfInterestType("paleobotany_table", getBlockStates(BlockInit.PALEOBOTANY_TABLE), 1, 1));

	// Registry
	public static void init() {
		ModUtils.LOGGER.debug("Registering Mod Points of Interest");
	}
}
