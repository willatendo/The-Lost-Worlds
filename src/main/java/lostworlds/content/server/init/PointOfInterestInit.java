package lostworlds.content.server.init;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.village.PointOfInterestType;

public class PointOfInterestInit 
{
	public static Set<BlockState> getBlockStates(Block block) 
	{
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}
	
	public static final PointOfInterestType ARCHAEOLOGY_TABLE = ModRegistry.register("archaeology_table", new PointOfInterestType("archaeology_table", getBlockStates(BlockInit.ARCHAEOLOGY_TABLE), 1, 1));
	public static final PointOfInterestType FOSSIL_CRATE = ModRegistry.register("fossil_crate", new PointOfInterestType("fossil_crate", getBlockStates(Blocks.ALLIUM), 1, 1));
	public static final PointOfInterestType PALEONTOLOGY_TABLE = ModRegistry.register("paleontology_table", new PointOfInterestType("paleontology_table", getBlockStates(BlockInit.FOSSIL_CLEANER), 1, 1));
	
	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Points of Interest"); }
}
