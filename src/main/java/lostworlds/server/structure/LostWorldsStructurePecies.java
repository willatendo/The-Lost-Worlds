package lostworlds.server.structure;

import java.util.Locale;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

public class LostWorldsStructurePecies {
	public static final StructurePieceType BLACK_MARKET_PIECE = register("black_market_piece", BlackMarketPeice.Piece::new);
	public static final StructurePieceType SURFACE_FOSSIL_PIECE = register("surface_fossil_piece", SurfaceFossilPeice.Piece::new);
	public static final StructurePieceType SUBTERRANEAN_FOSSIL_PIECE = register("subterranean_fossil_piece", SubterraneanFossilPeice.Piece::new);
	public static final StructurePieceType TRACE_FOSSIL_PIECE = register("trace_fossil_piece", TraceFossilPeice.Piece::new);
	public static final StructurePieceType METEORITE_PIECE = register("meteorite_piece", MeteoritePeice.Piece::new);

	public static void registerBiomeGeneration() {
		BuiltinRegistries.NOISE_GENERATOR_SETTINGS.forEach(settings -> {
			settings.structureSettings().structureConfig().put(LostWorldsStructures.BLACK_MARKET.get(), new StructureFeatureConfiguration(128, 8, LostWorldsConfig.COMMON_CONFIG.blackMarketGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.SURFACE_FOSSIL.get(), new StructureFeatureConfiguration(32, 8, LostWorldsConfig.COMMON_CONFIG.surfaceFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.SUBTERRANEAN_FOSSIL.get(), new StructureFeatureConfiguration(32, 8, LostWorldsConfig.COMMON_CONFIG.subterraneanFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.TRACE_FOSSIL.get(), new StructureFeatureConfiguration(32, 8, LostWorldsConfig.COMMON_CONFIG.traceFossilGenerationId.get()));
			settings.structureSettings().structureConfig().put(LostWorldsStructures.METEORITE.get(), new StructureFeatureConfiguration(128, 8, LostWorldsConfig.COMMON_CONFIG.meteoriteGenerationId.get()));
		});
	}

	public static StructurePieceType register(String id, StructurePieceType type) {
		return Registry.register(Registry.STRUCTURE_PIECE, LostWorldsUtils.rL(id.toLowerCase(Locale.ROOT)), type);
	}

	public static void init() {
	}
}
