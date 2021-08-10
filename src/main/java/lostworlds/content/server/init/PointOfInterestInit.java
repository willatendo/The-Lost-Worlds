package lostworlds.content.server.init;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.village.PointOfInterestType;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class PointOfInterestInit 
{
	public static Set<BlockState> getBlockStates(Block block) 
	{
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}
	
	public static final PointOfInterestType ARCHAEOLOGY_TABLE_POI = ModRegistry.register("archaeology_table_poi", new PointOfInterestType("archaeology_table_poi", getBlockStates(BlockInit.ARCHAEOLOGY_TABLE), 1, 1));
	
	//Registry
	public static void init() { ModUtil.LOGGER.debug("Registering Mod Points of Interest"); }
}
