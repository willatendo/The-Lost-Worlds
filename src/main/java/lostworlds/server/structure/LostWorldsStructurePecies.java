package lostworlds.server.structure;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public class LostWorldsStructurePecies {
	public static final IStructurePieceType BLACK_MARKET_PIECE = LostWorldsRegistry.register("black_market_piece", BlackMarketPeice.Piece::new);
	public static final IStructurePieceType SURFACE_FOSSIL_PIECE = LostWorldsRegistry.register("surface_fossil_piece", SurfaceFossilPeice.Piece::new);
	public static final IStructurePieceType SUBTERRANEAN_FOSSIL_PIECE = LostWorldsRegistry.register("subterranean_fossil_piece", SubterraneanFossilPeice.Piece::new);
	public static final IStructurePieceType TRACE_FOSSIL_PIECE = LostWorldsRegistry.register("trace_fossil_piece", TraceFossilPeice.Piece::new);
	public static final IStructurePieceType METEORITE_PIECE = LostWorldsRegistry.register("meteorite_piece", MeteoritePeice.Piece::new);

	public static void registerBiomeGeneration() {
		Structure.STRUCTURES_REGISTRY.put("black_market", LostWorldsStructures.BLACK_MARKET);
		Structure.STRUCTURES_REGISTRY.put("surface_fossil", LostWorldsStructures.SURFACE_FOSSIL);
		Structure.STRUCTURES_REGISTRY.put("subterranean_fossil", LostWorldsStructures.SUBTERRANEAN_FOSSIL);
		Structure.STRUCTURES_REGISTRY.put("trace_fossil_piece", LostWorldsStructures.TRACE_FOSSIL);
		Structure.STRUCTURES_REGISTRY.put("meteorite", LostWorldsStructures.METEORITE);
		WorldGenRegistries.NOISE_GENERATOR_SETTINGS.forEach(settings -> {
			settings.structureSettings().structureConfig().put(LostWorldsStructures.BLACK_MARKET, new StructureSeparationSettings(128, 8, LostWorldsConfig.SERVER_CONFIG.blackMarketGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.SURFACE_FOSSIL, new StructureSeparationSettings(32, 8, LostWorldsConfig.SERVER_CONFIG.surfaceFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.SUBTERRANEAN_FOSSIL, new StructureSeparationSettings(32, 8, LostWorldsConfig.SERVER_CONFIG.subterraneanFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.TRACE_FOSSIL, new StructureSeparationSettings(32, 8, LostWorldsConfig.SERVER_CONFIG.traceFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.METEORITE, new StructureSeparationSettings(128, 8, LostWorldsConfig.SERVER_CONFIG.meteoriteGenerationId.get()));
		});
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Structure Pieces and Generation");
	}
}