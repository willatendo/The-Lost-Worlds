package lostworlds.server.structure;

import java.util.Locale;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

public class LostWorldsStructurePecies {
	public static StructurePieceType BLACK_MARKET_PIECE;
	public static StructurePieceType FOSSIL_PIECE;
	public static StructurePieceType TRACE_FOSSIL_PIECE;
	public static StructurePieceType METEORITE_PIECE;

	public static StructurePieceType register(String id, StructurePieceType.StructureTemplateType type) {
		return Registry.register(Registry.STRUCTURE_PIECE, LostWorldsUtils.rL(id.toLowerCase(Locale.ROOT)), type);
	}

	public static void init() {
		BLACK_MARKET_PIECE = register("black_market_piece", BlackMarketPeice.Piece::new);
		FOSSIL_PIECE = register("fossil_piece", FossilPeice.Piece::new);
		TRACE_FOSSIL_PIECE = register("trace_fossil_piece", TraceFossilPeice.Piece::new);
		METEORITE_PIECE = register("meteorite_piece", MeteoritePeice.Piece::new);
	}
}
