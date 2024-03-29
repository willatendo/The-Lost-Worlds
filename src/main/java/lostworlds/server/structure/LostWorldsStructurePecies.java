package lostworlds.server.structure;

import java.util.Locale;

import lostworlds.server.LostWorldsConfig;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public class LostWorldsStructurePecies {
	public static final IStructurePieceType BLACK_MARKET_PIECE = register("black_market_piece", BlackMarketPeice.Piece::new);
	public static final IStructurePieceType SURFACE_FOSSIL_PIECE = register("surface_fossil_piece", SurfaceFossilPeice.Piece::new);
	public static final IStructurePieceType SUBTERRANEAN_FOSSIL_PIECE = register("subterranean_fossil_piece", SubterraneanFossilPeice.Piece::new);
	public static final IStructurePieceType TRACE_FOSSIL_PIECE = register("trace_fossil_piece", TraceFossilPeice.Piece::new);
	public static final IStructurePieceType METEORITE_PIECE = register("meteorite_piece", MeteoritePeice.Piece::new);

	public static void registerBiomeGeneration() {
		WorldGenRegistries.NOISE_GENERATOR_SETTINGS.forEach(settings -> {
			settings.structureSettings().structureConfig().put(LostWorldsStructures.BLACK_MARKET.get(), new StructureSeparationSettings(128, 8, LostWorldsConfig.COMMON_CONFIG.blackMarketGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.SURFACE_FOSSIL.get(), new StructureSeparationSettings(32, 8, LostWorldsConfig.COMMON_CONFIG.surfaceFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.SUBTERRANEAN_FOSSIL.get(), new StructureSeparationSettings(32, 8, LostWorldsConfig.COMMON_CONFIG.subterraneanFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.TRACE_FOSSIL.get(), new StructureSeparationSettings(32, 8, LostWorldsConfig.COMMON_CONFIG.traceFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.METEORITE.get(), new StructureSeparationSettings(128, 8, LostWorldsConfig.COMMON_CONFIG.meteoriteGenerationId.get()));
		});
	}

	public static IStructurePieceType register(String id, IStructurePieceType type) {
		return Registry.register(Registry.STRUCTURE_PIECE, LostWorldsUtils.rL(id.toLowerCase(Locale.ROOT)), type);
	}

	public static void init() {
	}
}
