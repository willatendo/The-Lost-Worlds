package lostworlds.content.server.init;

import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.village.PointOfInterestType;

public class PointOfInterestInit 
{
	private static Set<BlockState> getBlockStates(Block block) 
	{
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}
	private static final Set<BlockState> TABLES = ImmutableList.of(BlockInit.ACACIA_PALEONTOLOGY_TABLE, BlockInit.ARAUCARIA_PALEONTOLOGY_TABLE, BlockInit.BIRCH_PALEONTOLOGY_TABLE, BlockInit.CALAMITES_PALEONTOLOGY_TABLE, BlockInit.CONIFER_PALEONTOLOGY_TABLE, BlockInit.CRIMSON_PALEONTOLOGY_TABLE, BlockInit.DARK_OAK_PALEONTOLOGY_TABLE, BlockInit.GINKGO_PALEONTOLOGY_TABLE, BlockInit.JUNGLE_PALEONTOLOGY_TABLE, BlockInit.OAK_PALEONTOLOGY_TABLE, BlockInit.SCORCHED_PALEONTOLOGY_TABLE, BlockInit.SPRUCE_PALEONTOLOGY_TABLE, BlockInit.WARPED_PALEONTOLOGY_TABLE).stream().flatMap((block) -> 
	{
		return block.getStateDefinition().getPossibleStates().stream();
	}).collect(ImmutableSet.toImmutableSet());
	
	public static final PointOfInterestType ARCHAEOLOGY_TABLE = ModRegistry.register("archaeology_table", new PointOfInterestType("archaeology_table", getBlockStates(BlockInit.ARCHAEOLOGY_TABLE), 1, 1));
	public static final PointOfInterestType PALEONTOLOGY_TABLE = ModRegistry.register("paleontology_table", new PointOfInterestType("paleontology_table", TABLES, 1, 1));
	public static final PointOfInterestType PALEOBOTANY_TABLE = ModRegistry.register("paleobotany_table", new PointOfInterestType("paleobotany_table", getBlockStates(BlockInit.PALEOBOTANY_TABLE), 1, 1));
	
	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Points of Interest"); }
}
