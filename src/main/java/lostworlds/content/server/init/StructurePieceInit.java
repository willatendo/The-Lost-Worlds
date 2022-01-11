package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.content.config.LostWorldsConfig;
import lostworlds.library.structure.BlackMarketPeice;
import lostworlds.library.structure.SurfaceFossilPeice;
import lostworlds.library.structure.MeteoritePeice;
import lostworlds.library.structure.SubterraneanFossilPeice;
import lostworlds.library.structure.TraceFossilPeice;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public class StructurePieceInit {
	public static final IStructurePieceType BLACK_MARKET_PIECE = ModRegistry.register("black_market_piece", BlackMarketPeice.Piece::new);
	public static final IStructurePieceType SURFACE_FOSSIL_PIECE = ModRegistry.register("surface_fossil_piece", SurfaceFossilPeice.Piece::new);
	public static final IStructurePieceType SUBTERRANEAN_FOSSIL_PIECE = ModRegistry.register("subterranean_fossil_piece", SubterraneanFossilPeice.Piece::new);
	public static final IStructurePieceType TRACE_FOSSIL_PIECE = ModRegistry.register("trace_fossil_piece", TraceFossilPeice.Piece::new);
	public static final IStructurePieceType METEORITE_PIECE = ModRegistry.register("meteorite_piece", MeteoritePeice.Piece::new);

	public static void registerBiomeGeneration() {
		Structure.STRUCTURES_REGISTRY.put("black_market", StructureInit.BLACK_MARKET);
		Structure.STRUCTURES_REGISTRY.put("surface_fossil", StructureInit.SURFACE_FOSSIL);
		Structure.STRUCTURES_REGISTRY.put("subterranean_fossil", StructureInit.SUBTERRANEAN_FOSSIL);
		Structure.STRUCTURES_REGISTRY.put("trace_fossil_piece", StructureInit.TRACE_FOSSIL);
		Structure.STRUCTURES_REGISTRY.put("meteorite", StructureInit.METEORITE);
		WorldGenRegistries.NOISE_GENERATOR_SETTINGS.forEach(settings -> {
			settings.structureSettings().structureConfig().put(StructureInit.BLACK_MARKET, new StructureSeparationSettings(128, 8, LostWorldsConfig.SERVER_CONFIG.blackMarketGenerationId.get()));
			settings.structureSettings().structureConfig().put(StructureInit.SURFACE_FOSSIL, new StructureSeparationSettings(32, 8, LostWorldsConfig.SERVER_CONFIG.surfaceFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(StructureInit.SUBTERRANEAN_FOSSIL, new StructureSeparationSettings(32, 8, LostWorldsConfig.SERVER_CONFIG.subterraneanFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(StructureInit.TRACE_FOSSIL, new StructureSeparationSettings(32, 8, LostWorldsConfig.SERVER_CONFIG.traceFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(StructureInit.METEORITE, new StructureSeparationSettings(128, 8, LostWorldsConfig.SERVER_CONFIG.meteoriteGenerationId.get()));
		});
	}

	public static void init() {
		ModUtils.LOGGER.debug("Registering Mod Structure Pieces and Generation");
	}
}
