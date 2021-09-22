package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.structure.BlackMarketStructure;
import lostworlds.library.structure.MeteoriteStructure;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class StructureInit 
{
	public static final Structure<NoFeatureConfig> BLACK_MARKET = ModRegistry.register("black_market", new BlackMarketStructure(NoFeatureConfig.CODEC));
	public static final Structure<NoFeatureConfig> METEORITE = ModRegistry.register("meteorite", new MeteoriteStructure(NoFeatureConfig.CODEC));
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Structures"); }
}
