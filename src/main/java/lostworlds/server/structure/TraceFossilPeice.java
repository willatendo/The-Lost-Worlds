package lostworlds.server.structure;

import java.util.ArrayList;
import java.util.Random;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

public class TraceFossilPeice {
	public static final ArrayList<ResourceLocation> locations = new ArrayList<>();

	public static void addStructure(StructureManager manager, BlockPos pos, Rotation rotation, StructurePieceAccessor accessor, Random rand) {
		locations.add(LostWorldsUtils.rL("fossil/trace_fossil_1"));
		locations.add(LostWorldsUtils.rL("fossil/trace_fossil_2"));
		locations.add(LostWorldsUtils.rL("fossil/trace_fossil_3"));
		locations.add(LostWorldsUtils.rL("fossil/trace_fossil_4"));

		int fossil = rand.nextInt(locations.size());
		accessor.addPiece(new TraceFossilPeice.Piece(manager, locations.get(fossil), pos, rotation));
	}

	public static class Piece extends TemplateStructurePiece {
		public Piece(StructureManager manager, ResourceLocation templateLocation, BlockPos pos, Rotation rotation) {
			super(LostWorldsStructurePecies.TRACE_FOSSIL_PIECE, 0, manager, templateLocation, templateLocation.toString(), makeSettings(rotation), pos);
		}

		public Piece(StructureManager manager, CompoundTag tag) {
			super(LostWorldsStructurePecies.TRACE_FOSSIL_PIECE, tag, manager, (template) -> {
				return makeSettings(Rotation.valueOf(tag.getString("Rot")));
			});
		}

		private static StructurePlaceSettings makeSettings(Rotation rotation) {
			return (new StructurePlaceSettings()).setRotation(rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
		}

		@Override
		protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
			super.addAdditionalSaveData(context, tag);
			tag.putString("Rot", this.placeSettings.getRotation().name());
		}

		@Override
		protected void handleDataMarker(String data, BlockPos pos, ServerLevelAccessor world, Random rand, BoundingBox box) {
		}

		@Override
		public void postProcess(WorldGenLevel reader, StructureFeatureManager manager, ChunkGenerator chunkGenerator, Random rand, BoundingBox box, ChunkPos chunkPos, BlockPos pos) {
			BlockPos blockpos1 = this.templatePosition;
			int i = reader.getHeight(Heightmap.Types.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
			BlockPos blockpos2 = this.templatePosition;
			this.templatePosition = this.templatePosition.offset(0, i - 90 - 2, 0);
			this.templatePosition = blockpos2;
			super.postProcess(reader, manager, chunkGenerator, rand, box, chunkPos, pos);
		}
	}
}
