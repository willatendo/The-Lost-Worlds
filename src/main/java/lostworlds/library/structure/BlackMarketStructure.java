package lostworlds.library.structure;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class BlackMarketStructure extends Structure<NoFeatureConfig>
{	
	public BlackMarketStructure(Codec<NoFeatureConfig> codec) 
	{
		super(codec);
	}
	
	@Override
	public IStartFactory<NoFeatureConfig> getStartFactory() 
	{
		return BlackMarketStructure.Start::new;
	}
	
	@Override
	public Decoration step() 
	{
		return Decoration.SURFACE_STRUCTURES;
	}
	
	public static class Start extends StructureStart<NoFeatureConfig>
	{
		public Start(Structure<NoFeatureConfig> structure, int x, int z, MutableBoundingBox box, int i3, long seed) 
		{
			super(structure, x, z, box, i3, seed);
		}
		
		@Override
		public void generatePieces(DynamicRegistries registry, ChunkGenerator chunkGenerator, TemplateManager manager, int x, int z, Biome biome, NoFeatureConfig config) 
		{
			ChunkPos chunkpos = new ChunkPos(x, z);
			int i = chunkpos.getMinBlockX() + this.random.nextInt(16);
			int j = chunkpos.getMinBlockZ() + this.random.nextInt(16);
			int k = chunkGenerator.getSeaLevel();
			int l = k + this.random.nextInt(chunkGenerator.getGenDepth() - 2 - k);
			IBlockReader iblockreader = chunkGenerator.getBaseColumn(i, j);
			
			for(BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(i, l, j); l > k; --l) 
			{
				blockpos$mutable.move(Direction.DOWN);
				BlockState blockstate1 = iblockreader.getBlockState(blockpos$mutable);
				if(blockstate1.isFaceSturdy(iblockreader, blockpos$mutable, Direction.UP))
				{
					break;
				}
			}
			
			if(l > k) 
			{
				BlackMarketPeice.addPieces(manager, this.pieces, this.random, new BlockPos(i, l, j));
				this.calculateBoundingBox();
			}
		}
	}
}
