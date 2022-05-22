package lostworlds.server.structure;

import java.util.List;
import java.util.Random;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.illager.FossilPoacherEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class BlackMarketPeice {
	public static final ResourceLocation BLACK_MARKET_LOCATION = LostWorldsUtils.rL("black_market");

	public static void addStructure(StructureManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> piece, Random rand, Biome biome) {
		piece.add(new BlackMarketPeice.Piece(manager, BLACK_MARKET_LOCATION, pos, rotation));
	}

	public static class Piece extends TemplateStructurePiece {
		private final ResourceLocation templateLocation;
		private final Rotation rotation;

		public Piece(StructureManager manager, ResourceLocation location, BlockPos pos, Rotation rotation) {
			super(LostWorldsStructurePecies.BLACK_MARKET_PIECE, 0);
			this.templateLocation = location;
			this.templatePosition = pos;
			this.rotation = rotation;
			this.loadTemplate(manager);
		}

		public Piece(StructureManager manager, CompoundTag nbt) {
			super(LostWorldsStructurePecies.BLACK_MARKET_PIECE, nbt);
			this.templateLocation = new ResourceLocation(nbt.getString("Template"));
			this.rotation = Rotation.valueOf(nbt.getString("Rot"));
			this.loadTemplate(manager);
		}

		private void loadTemplate(StructureManager manager) {
			StructureTemplate template = manager.getOrCreate(this.templateLocation);
			StructurePlaceSettings placementsettings = (new StructurePlaceSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
			this.setup(template, this.templatePosition, placementsettings);
		}

		@Override
		protected void addAdditionalSaveData(CompoundTag nbt) {
			super.addAdditionalSaveData(nbt);
			nbt.putString("Template", this.templateLocation.toString());
			nbt.putString("Rot", this.rotation.name());
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
		public boolean postProcess(WorldGenLevel reader, StructureFeatureManager manager, ChunkGenerator chunkGenerator, Random rand, BoundingBox box, ChunkPos chunkPos, BlockPos pos) {
			BlockPos blockpos1 = this.templatePosition;
			int i = reader.getHeight(Heightmap.Types.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
			BlockPos blockpos2 = this.templatePosition;
			this.templatePosition = this.templatePosition.offset(0, i - 90 - 2, 0);
			boolean flag = super.postProcess(reader, manager, chunkGenerator, rand, box, chunkPos, pos);
			this.templatePosition = blockpos2;
			return flag;
		}
	}
}
