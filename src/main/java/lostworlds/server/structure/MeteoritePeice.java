package lostworlds.server.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.ServerLevelAccessor;
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

public class MeteoritePeice {
	public static final ArrayList<ResourceLocation> locations = new ArrayList<>();
	public static final ResourceLocation METEORITE_LOCATION_ONE = LostWorldsUtils.rL("meteorite_1"), METEORITE_LOCATION_TWO = LostWorldsUtils.rL("meteorite_2"), METEORITE_LOCATION_THREE = LostWorldsUtils.rL("meteorite_3"), METEORITE_LOCATION_FOUR = LostWorldsUtils.rL("meteorite_4"), METEORITE_LOCATION_FIVE = LostWorldsUtils.rL("meteorite_5"), METEORITE_LOCATION_SIX = LostWorldsUtils.rL("meteorite_6");

	public static void addStructure(StructureManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> piece, Random rand, Biome biome) {
		locations.add(METEORITE_LOCATION_ONE);
		locations.add(METEORITE_LOCATION_TWO);
		locations.add(METEORITE_LOCATION_THREE);
		locations.add(METEORITE_LOCATION_FOUR);
		locations.add(METEORITE_LOCATION_FIVE);

		int meteor = rand.nextInt(locations.size());
		piece.add(new MeteoritePeice.Piece(manager, locations.get(meteor), pos, rotation));
	}

	public static class Piece extends TemplateStructurePiece {
		private final ResourceLocation templateLocation;
		private final Rotation rotation;

		public Piece(StructureManager manager, ResourceLocation location, BlockPos pos, Rotation rotation) {
			super(LostWorldsStructurePecies.METEORITE_PIECE, 0);
			this.templateLocation = location;
			this.templatePosition = pos;
			this.rotation = rotation;
			this.loadTemplate(manager);
		}

		public Piece(StructureManager manager, CompoundTag nbt) {
			super(LostWorldsStructurePecies.METEORITE_PIECE, nbt);
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
