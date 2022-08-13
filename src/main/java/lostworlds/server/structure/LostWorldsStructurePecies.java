package lostworlds.server.structure;

import java.util.function.Supplier;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType.StructureTemplateType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsStructurePecies {
	public static final DeferredRegister<StructurePieceType> STRUCTURE_PECIES = DeferredRegister.create(Registry.STRUCTURE_PIECE_REGISTRY, LostWorldsUtils.ID);

	public static final RegistryObject<StructureTemplateType> BLACK_MARKET_PIECE = register("black_market_piece", () -> BlackMarketPeice.Piece::new);
	public static final RegistryObject<StructureTemplateType> FOSSIL_PIECE = register("fossil_piece", () -> FossilPeice.Piece::new);
	public static final RegistryObject<StructureTemplateType> TRACE_FOSSIL_PIECE = register("trace_fossil_piece", () -> TraceFossilPeice.Piece::new);
	public static final RegistryObject<StructureTemplateType> METEORITE_PIECE = register("meteorite_piece", () -> MeteoritePeice.Piece::new);

	public static RegistryObject<StructureTemplateType> register(String id, Supplier<StructurePieceType.StructureTemplateType> type) {
		return STRUCTURE_PECIES.register(id, type);
	}
}
