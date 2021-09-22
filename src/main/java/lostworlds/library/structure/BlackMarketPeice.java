package lostworlds.library.structure;

import java.util.List;
import java.util.Random;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.EntityInit;
import lostworlds.content.server.init.StructurePieceInit;
import lostworlds.library.entity.illager.FossilPoacherEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.BarrelTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
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

public class BlackMarketPeice 
{
	public static final ResourceLocation BLACK_MARKET_LOCATION = ModUtils.rL("black_market");
	
	public static void addStructure(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> piece, Random rand, Biome biome) 
	{
		piece.add(new BlackMarketPeice.Piece(manager, BLACK_MARKET_LOCATION, pos, rotation));
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
		protected void handleDataMarker(String data, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox box) 
		{
			if("fossil_poacher_spawn".equals(data))
			{
				world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				FossilPoacherEntity entity = EntityInit.FOSSIL_POACHER.create((World)world.getLevel());
				entity.setPersistenceRequired();
				entity.setPos(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
				entity.finalizeSpawn(world, world.getCurrentDifficultyAt(entity.blockPosition()), SpawnReason.STRUCTURE, (ILivingEntityData)null, (CompoundNBT)null);
				world.addFreshEntity(entity);
			}
			
			if("black_market_loot".equals(data)) 
			{
				world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				TileEntity tileentity = world.getBlockEntity(pos.below());
				if(tileentity instanceof BarrelTileEntity) 
				{
					((BarrelTileEntity)tileentity).setLootTable(ModUtils.rL("chests/black_market_loot"), rand.nextLong());
				}
			}
		}

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
