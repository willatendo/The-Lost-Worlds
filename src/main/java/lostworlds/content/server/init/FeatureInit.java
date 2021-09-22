package lostworlds.content.server.init;

import lostworlds.content.ModRegistry;
import lostworlds.content.ModUtils;
import lostworlds.library.feature.AshFeature;
import lostworlds.library.feature.CalamitesSuckowiiFeature;
import lostworlds.library.feature.GeyserBlockFeature;
import lostworlds.library.feature.ModBlockBlobFeature;
import lostworlds.library.feature.PermianLakesFeature;
import lostworlds.library.feature.ScorchedTreeFeature;
import lostworlds.library.feature.SpongeColoneyFeature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class FeatureInit 
{
	public static final Feature<NoFeatureConfig> ASH_LAYER_PLACEMENT = new AshFeature(NoFeatureConfig.CODEC);

	public static final Feature<ProbabilityConfig> CALAMITES_SUCKOWII = new CalamitesSuckowiiFeature(ProbabilityConfig.CODEC);
	
	public static final Feature<FeatureSpreadConfig> GEYSER_BLOCK_PLACEMENT = new GeyserBlockFeature(FeatureSpreadConfig.CODEC);
	public static final Feature<FeatureSpreadConfig> SPONGE_COLONEY_PLACEMENT = new SpongeColoneyFeature(FeatureSpreadConfig.CODEC);

	public static final Feature<BaseTreeFeatureConfig> SCORCHED_TREE = new ScorchedTreeFeature(BaseTreeFeatureConfig.CODEC);

	public static final Feature<BlockStateFeatureConfig> PERMIAN_ROCK = new ModBlockBlobFeature(BlockStateFeatureConfig.CODEC);
	
	public static final Feature<BlockStateFeatureConfig> PERMIAN_LAKE = new PermianLakesFeature(BlockStateFeatureConfig.CODEC);

	public static void init() 
	{ 	
		ModUtils.LOGGER.debug("Registering Mod Features");
		
		ModRegistry.register("ash_layer_placement", ASH_LAYER_PLACEMENT);
		ModRegistry.register("calamites_suckwii_placement", CALAMITES_SUCKOWII);
		ModRegistry.register("geyser_block_placement", GEYSER_BLOCK_PLACEMENT);
		ModRegistry.register("sponge_coloney_placement", SPONGE_COLONEY_PLACEMENT);
		ModRegistry.register("scorched_tree", SCORCHED_TREE);
		ModRegistry.register("permian_rock", PERMIAN_ROCK);
		ModRegistry.register("permian_lake", PERMIAN_LAKE);
	}
}
