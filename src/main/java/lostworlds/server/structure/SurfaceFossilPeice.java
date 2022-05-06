package lostworlds.server.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.Damage;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.PotentialPart;
import lostworlds.server.block.SoftStoneBlock;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.entity.utils.enums.TimeEras;
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
		locations.add(LostWorldsUtils.rL("fossil/fossil_1"));
		locations.add(LostWorldsUtils.rL("fossil/fossil_2"));
		locations.add(LostWorldsUtils.rL("fossil/fossil_3"));
		locations.add(LostWorldsUtils.rL("fossil/fossil_4"));
		locations.add(LostWorldsUtils.rL("fossil/fossil_5"));
		locations.add(LostWorldsUtils.rL("fossil/fossil_6"));

		int fossil = rand.nextInt(locations.size());
		int type = rand.nextInt(DinoTypes.hasNoSpecialFossil().size());
		piece.add(new SurfaceFossilPeice.Piece(manager, locations.get(fossil), DinoTypes.values()[type], pos, rotation));
	}

	public static class Piece extends TemplateStructurePiece {
		private final ResourceLocation templateLocation;
		private final Rotation rotation;
		private final DinoTypes type;

		public Piece(TemplateManager manager, ResourceLocation location, DinoTypes type, BlockPos pos, Rotation rotation) {
			super(LostWorldsStructurePecies.SURFACE_FOSSIL_PIECE, 0);
			this.templateLocation = location;
			this.templatePosition = pos;
			this.rotation = rotation;
			this.type = type;
			this.loadTemplate(manager);
		}

		public Piece(TemplateManager manager, CompoundNBT nbt) {
			super(LostWorldsStructurePecies.SURFACE_FOSSIL_PIECE, nbt);
			this.templateLocation = new ResourceLocation(nbt.getString("Template"));
			this.rotation = Rotation.valueOf(nbt.getString("Rot"));
			this.type = DinoTypes.byName(nbt.getString("Type"));
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
			nbt.putString("Type", this.type.getId());
		}

		@Override
		protected void handleDataMarker(String data, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox box) {
			if ("skull".equals(data)) {
				world.setBlock(pos, LostWorldsBlocks.SOFT_STONE.getDefaultState().setValue(SoftStoneBlock.DAMAGE, Damage.NONE).setValue(SoftStoneBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(SoftStoneBlock.POTENTIAL_CREATURE, this.type).setValue(SoftStoneBlock.POTENTIAL_PART, PotentialPart.SKULL), 3);
			}

			if ("arm".equals(data)) {
				world.setBlock(pos, LostWorldsBlocks.SOFT_STONE.getDefaultState().setValue(SoftStoneBlock.DAMAGE, Damage.NONE).setValue(SoftStoneBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(SoftStoneBlock.POTENTIAL_CREATURE, this.type).setValue(SoftStoneBlock.POTENTIAL_PART, PotentialPart.ARM), 3);
			}

			if ("rib_cage".equals(data)) {
				world.setBlock(pos, LostWorldsBlocks.SOFT_STONE.getDefaultState().setValue(SoftStoneBlock.DAMAGE, Damage.NONE).setValue(SoftStoneBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(SoftStoneBlock.POTENTIAL_CREATURE, this.type).setValue(SoftStoneBlock.POTENTIAL_PART, PotentialPart.RIB_CAGE), 3);
			}

			if ("leg".equals(data)) {
				world.setBlock(pos, LostWorldsBlocks.SOFT_STONE.getDefaultState().setValue(SoftStoneBlock.DAMAGE, Damage.NONE).setValue(SoftStoneBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(SoftStoneBlock.POTENTIAL_CREATURE, this.type).setValue(SoftStoneBlock.POTENTIAL_PART, PotentialPart.LEG), 3);
			}

			if ("tail".equals(data)) {
				world.setBlock(pos, LostWorldsBlocks.SOFT_STONE.getDefaultState().setValue(SoftStoneBlock.DAMAGE, Damage.NONE).setValue(SoftStoneBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(SoftStoneBlock.POTENTIAL_CREATURE, this.type).setValue(SoftStoneBlock.POTENTIAL_PART, PotentialPart.TAIL), 3);
			}

			if ("stone".equals(data)) {
				world.setBlock(pos, LostWorldsBlocks.SOFT_STONE.getDefaultState().setValue(SoftStoneBlock.DAMAGE, Damage.NONE).setValue(SoftStoneBlock.ERA, TimeEras.MODERN_MINECRAFT).setValue(SoftStoneBlock.POTENTIAL_CREATURE, this.type).setValue(SoftStoneBlock.POTENTIAL_PART, PotentialPart.NONE), 3);
			}
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
