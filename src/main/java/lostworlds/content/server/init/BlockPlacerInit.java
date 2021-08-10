package lostworlds.content.server.init;

import lostworlds.library.blockplacer.RockOutcropPlacer;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtil;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class BlockPlacerInit 
{
	public static final BlockPlacerType<?> ROCK_OUTCROP_PLACER = ModRegistry.register("rock_outcrop_placer", new BlockPlacerType(RockOutcropPlacer.CODEC));
	
	//Registry
	public static void init() { ModUtil.LOGGER.debug("Registering Mod Block Placers"); }
}
