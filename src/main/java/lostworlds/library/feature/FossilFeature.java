package lostworlds.library.feature;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

import com.mojang.serialization.Codec;

import lostworlds.content.ModUtils;
import lostworlds.library.feature.config.FossilFeatureConfig;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.IntegrityProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class FossilFeature extends Feature<FossilFeatureConfig>
{		
	public FossilFeature(Codec<FossilFeatureConfig> codec) 
	{
		super(codec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator chunk, Random rand, BlockPos pos, FossilFeatureConfig config) 
	{		
		ArrayList<ResourceLocation> fossils = new ArrayList<>();
		
		//for(DinoTypes types : DinoTypes.values())
		//{
			ResourceLocation rL1 = ModUtils.rL("fossil/" + "chilesaurus_fossil_1");//types.getId() + "_fossil_1");
			ResourceLocation rL2 = ModUtils.rL("fossil/" + "chilesaurus_fossil_2"); //types.getId() + "_fossil_2");
			ResourceLocation rL3 = ModUtils.rL("fossil/" + "chilesaurus_fossil_3"); //types.getId() + "_fossil_3");
			ResourceLocation rL4 = ModUtils.rL("fossil/" + "chilesaurus_fossil_4"); //types.getId() + "_fossil_4");
			ResourceLocation rL5 = ModUtils.rL("fossil/" + "chilesaurus_fossil_5"); //types.getId() + "_fossil_5");
			ResourceLocation rL6 = ModUtils.rL("fossil/" + "chilesaurus_fossil_6"); //types.getId() + "_fossil_6");
			fossils.add(rL1);
			fossils.add(rL2);
			fossils.add(rL3);
			fossils.add(rL4);
			fossils.add(rL5);
			fossils.add(rL6);
		//}
		
		//Around Block
		float f = rand.nextFloat() * (float)Math.PI;
		float size = (float)config.size / 8.0F;
		int i = MathHelper.ceil(((float)config.size / 16.0F * 2.0F + 1.0F) / 2.0F);
		double px = (double)pos.getX() + Math.sin((double)f) * (double)size;
		double nx = (double)pos.getX() - Math.sin((double)f) * (double)size;
		double pz = (double)pos.getZ() + Math.cos((double)f) * (double)size;
		double nz = (double)pos.getZ() - Math.cos((double)f) * (double)size;
		double y1 = (double)(pos.getY() + rand.nextInt(3) - 2);
		double y2 = (double)(pos.getY() + rand.nextInt(3) - 2);
		int x = pos.getX() - MathHelper.ceil(size) - i;
		int y = pos.getY() - 2 - i;
		int z = pos.getZ() - MathHelper.ceil(size) - i;
		int j1 = 2 * (MathHelper.ceil(size) + i);
		int k1 = 2 * (2 + i);

		for(int l1 = x; l1 <= x + j1; ++l1) 
		{
			for(int i2 = z; i2 <= z + j1; ++i2) 
			{
				if(y <= reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, l1, i2)) 
				{
					return this.doPlace(reader, rand, config, px, nx, pz, nz, y1, y2, x, y, z, j1, k1);
				}
			}
		}
		
		//Fossil Placement
		Rotation rotation = Rotation.getRandom(rand);
		int iF = rand.nextInt(fossils.size());
		TemplateManager templatemanager = reader.getLevel().getServer().getStructureManager();
		Template template = templatemanager.getOrCreate(fossils.get(iF));
		ChunkPos chunkpos = new ChunkPos(pos);
		MutableBoundingBox mutableboundingbox = new MutableBoundingBox(chunkpos.getMinBlockX(), 0, chunkpos.getMinBlockZ(), chunkpos.getMaxBlockX(), 256, chunkpos.getMaxBlockZ());
		PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(mutableboundingbox).setRandom(rand).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_AND_AIR);
		BlockPos blockpos = template.getSize(rotation);
		int jf = rand.nextInt(16 - blockpos.getX());
		int kf = rand.nextInt(16 - blockpos.getZ());
		int lf = 256;
		
		for(int i1f = 0; i1f < blockpos.getX(); ++i1f) 
		{
			for(int j1f = 0; j1f < blockpos.getZ(); ++j1f) 
			{
				lf = Math.min(lf, reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX() + i1f + jf, pos.getZ() + j1 + kf));
			}
		}
		
		BlockPos blockpos1 = template.getZeroPositionWithTransform(pos.offset(jf, k1, kf), Mirror.NONE, rotation);
		IntegrityProcessor integrityprocessor = new IntegrityProcessor(0.9F);
		placementsettings.clearProcessors().addProcessor(integrityprocessor);
		template.placeInWorld(reader, blockpos1, blockpos1, placementsettings, rand, 4);
		placementsettings.popProcessor(integrityprocessor);
		IntegrityProcessor integrityprocessor1 = new IntegrityProcessor(0.1F);
		placementsettings.clearProcessors().addProcessor(integrityprocessor1);
		
		return true;
	}
	
	protected boolean doPlace(IWorld world, Random rand, FossilFeatureConfig config, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_) 
	{
		int i = 0;
		BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		int j = config.size;
		double[] adouble = new double[j * 4];

		for(int k = 0; k < j; ++k) 
		{
			float f = (float) k / (float) j;
			double d0 = MathHelper.lerp((double) f, p_207803_4_, p_207803_6_);
			double d2 = MathHelper.lerp((double) f, p_207803_12_, p_207803_14_);
			double d4 = MathHelper.lerp((double) f, p_207803_8_, p_207803_10_);
			double d6 = rand.nextDouble() * (double) j / 16.0D;
			double d7 = ((double) (MathHelper.sin((float) Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
			adouble[k * 4 + 0] = d0;
			adouble[k * 4 + 1] = d2;
			adouble[k * 4 + 2] = d4;
			adouble[k * 4 + 3] = d7;
		}

		for(int i3 = 0; i3 < j - 1; ++i3) 
		{
			if(!(adouble[i3 * 4 + 3] <= 0.0D)) 
			{
				for(int k3 = i3 + 1; k3 < j; ++k3) 
				{
					if(!(adouble[k3 * 4 + 3] <= 0.0D)) 
					{
						double d12 = adouble[i3 * 4 + 0] - adouble[k3 * 4 + 0];
						double d13 = adouble[i3 * 4 + 1] - adouble[k3 * 4 + 1];
						double d14 = adouble[i3 * 4 + 2] - adouble[k3 * 4 + 2];
						double d15 = adouble[i3 * 4 + 3] - adouble[k3 * 4 + 3];
						if(d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) 
						{
							if(d15 > 0.0D) 
							{
								adouble[k3 * 4 + 3] = -1.0D;
							} 
							else 
							{
								adouble[i3 * 4 + 3] = -1.0D;
							}
						}
					}
				}
			}
		}

		for(int j3 = 0; j3 < j; ++j3) 
		{
			double d11 = adouble[j3 * 4 + 3];
			if(!(d11 < 0.0D)) 
			{
				double d1 = adouble[j3 * 4 + 0];
				double d3 = adouble[j3 * 4 + 1];
				double d5 = adouble[j3 * 4 + 2];
				int l = Math.max(MathHelper.floor(d1 - d11), p_207803_16_);
				int l3 = Math.max(MathHelper.floor(d3 - d11), p_207803_17_);
				int i1 = Math.max(MathHelper.floor(d5 - d11), p_207803_18_);
				int j1 = Math.max(MathHelper.floor(d1 + d11), l);
				int k1 = Math.max(MathHelper.floor(d3 + d11), l3);
				int l1 = Math.max(MathHelper.floor(d5 + d11), i1);

				for(int i2 = l; i2 <= j1; ++i2) 
				{
					double d8 = ((double) i2 + 0.5D - d1) / d11;
					if(d8 * d8 < 1.0D) 
					{
						for(int j2 = l3; j2 <= k1; ++j2) 
						{
							double d9 = ((double) j2 + 0.5D - d3) / d11;
							if(d8 * d8 + d9 * d9 < 1.0D) 
							{
								for(int k2 = i1; k2 <= l1; ++k2) 
								{
									double d10 = ((double) k2 + 0.5D - d5) / d11;
									if(d8 * d8 + d9 * d9 + d10 * d10 < 1.0D) 
									{
										int l2 = i2 - p_207803_16_ + (j2 - p_207803_17_) * p_207803_19_ + (k2 - p_207803_18_) * p_207803_19_ * p_207803_20_;
										if(!bitset.get(l2)) 
										{
											bitset.set(l2);
											blockpos$mutable.set(i2, j2, k2);
											if(config.target.test(world.getBlockState(blockpos$mutable), rand)) 
											{
												world.setBlock(blockpos$mutable, config.state, 2);
												++i;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return i > 0;
	}
}
