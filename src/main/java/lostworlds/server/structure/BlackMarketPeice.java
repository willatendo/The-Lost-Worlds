package lostworlds.server.structure;

import java.util.Random;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.illager.FossilPoacherEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

public class BlackMarketPeice {
	public static final ResourceLocation BLACK_MARKET_LOCATION = LostWorldsUtils.rL("black_market");

	public static void addStructure(StructureManager manager, BlockPos pos, Rotation rotation, StructurePieceAccessor accessor, Random rand) {
		accessor.addPiece(new BlackMarketPeice.Piece(manager, BLACK_MARKET_LOCATION, pos, rotation));
	}

	public static class Piece extends TemplateStructurePiece {
		public Piece(StructureManager manager, ResourceLocation templateLocation, BlockPos pos, Rotation rotation) {
			super(LostWorldsStructurePecies.BLACK_MARKET_PIECE, 0, manager, templateLocation, templateLocation.toString(), makeSettings(rotation), pos);
		}

		public Piece(StructureManager manager, CompoundTag tag) {
			super(LostWorldsStructurePecies.BLACK_MARKET_PIECE, tag, manager, (template) -> {
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
			if ("fossil_poacher_spawn".equals(data)) {
				world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				FossilPoacherEntity entity = LostWorldsEntities.FOSSIL_POACHER.create((Level) world.getLevel());
				entity.setPersistenceRequired();
				entity.setPos(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
				entity.finalizeSpawn(world, world.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.STRUCTURE, (SpawnGroupData) null, (CompoundTag) null);
				world.addFreshEntity(entity);
			}

			if ("black_market_loot".equals(data)) {
				world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				BlockEntity tileentity = world.getBlockEntity(pos.below());
				if (tileentity instanceof BarrelBlockEntity) {
					((BarrelBlockEntity) tileentity).setLootTable(LostWorldsUtils.rL("chests/black_market_loot"), rand.nextLong());
				}
			}
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
