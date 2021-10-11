package lostworlds.library.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class ModBlockBlobFeature extends Feature<BlockStateFeatureConfig> 
{
	public ModBlockBlobFeature(Codec<BlockStateFeatureConfig> codec) 
	{
		super(codec);
	}

	public boolean place(ISeedReader reader, ChunkGenerator chunckGenerator, Random rand, BlockPos pos, BlockStateFeatureConfig config) 
	{
		while(true) 
		{
			place: 
			{
				if(pos.getY() > 3) 
				{
					if(reader.isEmptyBlock(pos.below())) 
					{
						break place;
					}

					Material material = reader.getBlockState(pos.below()).getMaterial();
					if(!material.isSolid() || material == Material.BAMBOO || material == Material.BAMBOO_SAPLING || material == Material.BUBBLE_COLUMN || material == Material.BUILDABLE_GLASS || material == Material.CACTUS || material == Material.CAKE || material == Material.DECORATION || material == Material.EGG || material == Material.EXPLOSIVE || material == Material.FIRE || material == Material.GLASS || material == Material.HEAVY_METAL || material == Material.ICE || material == Material.ICE_SOLID || material == Material.LAVA || material == Material.LEAVES || material == Material.METAL || material == Material.NETHER_WOOD || material == Material.PISTON || material == Material.PORTAL || material == Material.REPLACEABLE_FIREPROOF_PLANT || material == Material.REPLACEABLE_PLANT || material == Material.REPLACEABLE_WATER_PLANT || material == Material.SHULKER_SHELL || material == Material.SPONGE || material == Material.STRUCTURAL_AIR || material == Material.WATER || material == Material.WATER_PLANT || material == Material.WEB || material == Material.WOOD) 
					{
						break place;
					}
				}

				if(pos.getY() <= 3) 
				{
					return false;
				}

				for(int l = 0; l < 3; ++l) 
				{
					int i = rand.nextInt(2);
					int j = rand.nextInt(2);
					int k = rand.nextInt(2);
					float f = (float) (i + j + k) * 0.333F + 0.5F;

					for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-i, -j, -k), pos.offset(i, j, k))) 
					{
						if(blockpos.distSqr(pos) <= (double) (f * f)) 
						{
							reader.setBlock(blockpos, config.state, 4);
						}
					}

					pos = pos.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
				}

				return true;
			}

			pos = pos.below();
		}
	}
}
