package lostworlds.content.server.init;

import lostworlds.library.structure.BlackMarketPeice;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public class StructurePieceInit 
{
	public static final IStructurePieceType BLACK_MARKET_PIECE = ModRegistry.register("black_market_piece", BlackMarketPeice.Piece::new);

	public static void registerBiomeGeneration()
	{
		Structure.STRUCTURES_REGISTRY.put("black_market", StructureInit.BLACK_MARKET);
		WorldGenRegistries.NOISE_GENERATOR_SETTINGS.forEach(settings -> 
		{
			settings.structureSettings().structureConfig().put(StructureInit.BLACK_MARKET, new StructureSeparationSettings(128, 8, 930134351));
		});
	}
	
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Structure Pieces"); }
}
