package lostworlds.library.dimension.jurassic;

import lostworlds.library.biome.BiomeKeys;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public class JurassicBiomeLayer implements IAreaTransformer0   
{
    private static final int UNCOMMON_BIOME_CHANCE = 8;
    private static final int RARE_BIOME_CHANCE = 16;
    protected int[] commonBiomes = new int[]
    {
    	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_DESERT),
    	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ARAUCARIA_FOREST),
    	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_CONIFER_FOREST),
    	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_GINKGO_FOREST),
    	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_PLAINS),
    };
    protected int[] uncommonBiomes = (new int[]
    {
    	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_MOUNTAINS),
    	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_REDWOODS_FOREST),
    	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_MARSH),
    	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_SWAMP),
    	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_FEN),
    	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_BOG),
   });
    protected int[] rareBiomes = (new int[]
    {
    		JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_ERRODED_MOUNTAINS),   		
        	JurassicLayerUtil.getBiomeId(BiomeKeys.JURASSIC_VOLCANIC_RANGE),
    });

    public JurassicBiomeLayer() { }
    
    @Override
    public int applyPixel(INoiseRandom iNoiseRandom, int unmaped1, int unmaped2)  
    {
        if(iNoiseRandom.nextRandom(RARE_BIOME_CHANCE) == 0) 
        {
            return rareBiomes[iNoiseRandom.nextRandom(rareBiomes.length)];
        } 
        else if(iNoiseRandom.nextRandom(UNCOMMON_BIOME_CHANCE) == 0) 
        {
            return uncommonBiomes[iNoiseRandom.nextRandom(uncommonBiomes.length)];
        } 
        else 
        {
            return commonBiomes[iNoiseRandom.nextRandom(commonBiomes.length)];
        }
    }
}
