package lostworlds.library.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.StructurePieceInit;
import lostworlds.library.entity.utils.enums.DinoTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class SurfaceFossilPeice {
	public static final ArrayList<ResourceLocation> locations = new ArrayList<>();

	public static void addStructure(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> piece, Random rand, Biome biome) {
		for (DinoTypes types : DinoTypes.values()) {
			locations.add(ModUtils.rL("fossil/" + types.toString().toLowerCase() + "_fossil_1"));
			locations.add(ModUtils.rL("fossil/" + types.toString().toLowerCase() + "_fossil_2"));
			locations.add(ModUtils.rL("fossil/" + types.toString().toLowerCase() + "_fossil_3"));
			locations.add(ModUtils.rL("fossil/" + types.toString().toLowerCase() + "_fossil_4"));
			locations.add(ModUtils.rL("fossil/" + types.toString().toLowerCase() + "_fossil_5"));
			locations.add(ModUtils.rL("fossil/" + types.toString().toLowerCase() + "_fossil_6"));
		}

		int fossil = rand.nextInt(locations.size());
		piece.add(new SurfaceFossilPeice.Piece(manager, locations.get(fossil), pos, rotation));
	}

	public static class Piece extends TemplateStructurePiece {
		private final ResourceLocation templateLocation;
		private final Rotation rotation;

		public Piece(TemplateManager manager, ResourceLocation location, BlockPos pos, Rotation rotation) {
			super(StructurePieceInit.SURFACE_FOSSIL_PIECE, 0);
			this.templateLocation = location;
			this.templatePosition = pos;
			this.rotation = rotation;
			this.loadTemplate(manager);
		}

		public Piece(TemplateManager manager, CompoundNBT nbt) {
			super(StructurePieceInit.SURFACE_FOSSIL_PIECE, nbt);
			this.templateLocation = new ResourceLocation(nbt.getString("Template"));
			this.rotation = Rotation.valueOf(nbt.getString("Rot"));
			this.loadTemplate(manager);
		}

		private void loadTemplate(TemplateManager manager) {
			Template template = manager.getOrCreate(this.templateLocation);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
			this.setup(template, this.templatePosition, placementsettings);
		}

		@Override
		protected void addAdditionalSaveData(CompoundNBT nbt) {
			super.addAdditionalSaveData(nbt);
			nbt.putString("Template", this.templateLocation.toString());
			nbt.putString("Rot", this.rotation.name());
		}

		@Override
		protected void handleDataMarker(String data, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox box) {
		}

		@Override
		public boolean postProcess(ISeedReader reader, StructureManager manager, ChunkGenerator chunkGenerator, Random rand, MutableBoundingBox box, ChunkPos chunkPos, BlockPos pos) {
			BlockPos blockpos1 = this.templatePosition;
			int i = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
			BlockPos blockpos2 = this.templatePosition;
			this.templatePosition = this.templatePosition.offset(0, i - 90 - 2, 0);
			boolean flag = super.postProcess(reader, manager, chunkGenerator, rand, box, chunkPos, pos);
			this.templatePosition = blockpos2;
			return flag;
		}
	}
}
