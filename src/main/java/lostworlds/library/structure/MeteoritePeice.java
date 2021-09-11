package lostworlds.library.structure;

import java.util.List;
import java.util.Random;

import lostworlds.content.server.init.StructurePieceInit;
import lostworlds.library.util.ModUtils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class MeteoritePeice 
{
	public static final ResourceLocation METEORITE_LOCATION_ONE = ModUtils.rL("meteorite_one"), METEORITE_LOCATION_TWO = ModUtils.rL("meteorite_two"), METEORITE_LOCATION_THREE = ModUtils.rL("meteorite_three"), METEORITE_LOCATION_FOUR = ModUtils.rL("meteorite_four"), METEORITE_LOCATION_FIVE = ModUtils.rL("meteorite_five"), METEORITE_LOCATION_SIX = ModUtils.rL("meteorite_six");
	
	public static void addPieces(TemplateManager manager, List<StructurePiece> piece, Random rand, BlockPos pos) 
	{
		Rotation rotation = Rotation.getRandom(rand);
		piece.add(new MeteoritePeice.Piece(manager, chooseRandom(), pos, rotation));
	}
	
	private static ResourceLocation chooseRandom()
	{
		Random rand = new Random();
		int location = rand.nextInt(6);
		return location == 1 ? METEORITE_LOCATION_ONE : location == 2 ? METEORITE_LOCATION_TWO : location == 3 ? METEORITE_LOCATION_THREE : location == 4 ? METEORITE_LOCATION_FOUR : location == 5 ? METEORITE_LOCATION_FIVE : METEORITE_LOCATION_SIX;
	}
	
	public static class Piece extends TemplateStructurePiece 
	{
		private final ResourceLocation templateLocation;
		private final Rotation rotation;

		public Piece(TemplateManager manager, ResourceLocation location, BlockPos pos, Rotation rotation) 
		{
			super(StructurePieceInit.BLACK_MARKET_PIECE, 0);
			this.templateLocation = location;
			this.templatePosition = pos;
			this.rotation = rotation;
			this.loadTemplate(manager);
		}

		public Piece(TemplateManager manager, CompoundNBT nbt) 
		{
			super(StructurePieceInit.BLACK_MARKET_PIECE, nbt);
			this.templateLocation = new ResourceLocation(nbt.getString("Template"));
			this.rotation = Rotation.valueOf(nbt.getString("Rot"));
			this.loadTemplate(manager);
		}

		private void loadTemplate(TemplateManager manager)
		{
			Template template = manager.getOrCreate(this.templateLocation);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
			this.setup(template, this.templatePosition, placementsettings);
		}

		@Override
		protected void addAdditionalSaveData(CompoundNBT nbt) 
		{
			super.addAdditionalSaveData(nbt);
			nbt.putString("Template", this.templateLocation.toString());
			nbt.putString("Rot", this.rotation.name());
		}

		@Override
		protected void handleDataMarker(String data, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox box) { }

		@Override
		public boolean postProcess(ISeedReader reader, StructureManager manager, ChunkGenerator chunkGenerator, Random rand, MutableBoundingBox box, ChunkPos chunkPos, BlockPos pos) 
		{
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
