package lostworlds.library.foliageplacer;

import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import lostworlds.content.server.init.FoliagePlacerInit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

public class ConiferFoliagePlacer extends FoliagePlacer 
{
	public static final Codec<ConiferFoliagePlacer> CODEC = RecordCodecBuilder.create((foliagePlacer) -> 
	{
		return foliagePlacerParts(foliagePlacer).and(FeatureSpread.codec(0, 16, 8).fieldOf("trunk_height").forGetter((coniferFoliagePlacer) -> 
		{
			return coniferFoliagePlacer.trunkHeight;
		})).apply(foliagePlacer, ConiferFoliagePlacer::new);
	});
	private final FeatureSpread trunkHeight;

	public ConiferFoliagePlacer(FeatureSpread radius, FeatureSpread offset, FeatureSpread trunkHeight) 
	{
		super(radius, offset);
		this.trunkHeight = trunkHeight;
	}

	@Override
	protected FoliagePlacerType<?> type() 
	{
		return FoliagePlacerInit.CONIFER_FOLIAGE_PLACER;
	}

	@Override
	protected void createFoliage(IWorldGenerationReader reader, Random rand, BaseTreeFeatureConfig config, int i1, FoliagePlacer.Foliage foliage, int i2, int i3, Set<BlockPos> setPos, int i4, MutableBoundingBox box) 
	{
		BlockPos blockpos = foliage.foliagePos();
		int i = rand.nextInt(2);
		int j = 1;
		int k = 0;

		for(int l = i4; l >= -i2; --l) 
		{
			this.placeLeavesRow(reader, rand, config, blockpos, i, setPos, l, foliage.doubleTrunk(), box);
			if(i >= j) 
			{
				i = k;
				k = 1;
				j = Math.min(j + 1, i3 + foliage.radiusOffset());
			} 
			else 
			{
				++i;
			}
		}

	}

	@Override
	public int foliageHeight(Random rand, int i1, BaseTreeFeatureConfig config) 
	{
		return Math.max(4, i1 - this.trunkHeight.sample(rand));
	}

	@Override
	protected boolean shouldSkipLocation(Random rand, int i1, int i2, int i3, int i4, boolean b) 
	{
		return i1 == i4 && i3 == i4 && i4 > 0;
	}
}
