package lostworlds.server.structure;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType.StructureTemplateType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsStructurePecies {
	public static final DeferredRegister<StructurePieceType> STRUCTURE_PECIES = DeferredRegister.create(Registry.STRUCTURE_PIECE_REGISTRY, LostWorldsUtils.ID);

	public static final RegistryObject<StructureTemplateType> BLACK_MARKET_PIECE = STRUCTURE_PECIES.register("black_market_piece", () -> BlackMarketPeice.Piece::new);
	public static final RegistryObject<StructureTemplateType> FOSSIL_PIECE = STRUCTURE_PECIES.register("fossil_piece", () -> FossilPeice.Piece::new);
	public static final RegistryObject<StructureTemplateType> TRACE_FOSSIL_PIECE = STRUCTURE_PECIES.register("trace_fossil_piece", () -> TraceFossilPeice.Piece::new);
	public static final RegistryObject<StructureTemplateType> METEORITE_PIECE = STRUCTURE_PECIES.register("meteorite_piece", () -> MeteoritePeice.Piece::new);
}
