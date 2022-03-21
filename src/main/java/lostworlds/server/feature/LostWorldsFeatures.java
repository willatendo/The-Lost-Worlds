package lostworlds.server.feature;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class LostWorldsFeatures {
	public static final Feature<NoFeatureConfig> ASH_LAYER_PLACEMENT = new AshFeature(NoFeatureConfig.CODEC);

	public static final Feature<ProbabilityConfig> CALAMITES_SUCKOWII = new CalamitesSuckowiiFeature(ProbabilityConfig.CODEC);

	public static final Feature<FeatureSpreadConfig> GEYSER_BLOCK_PLACEMENT = new GeyserBlockFeature(FeatureSpreadConfig.CODEC);
	public static final Feature<FeatureSpreadConfig> SPONGE_COLONEY_PLACEMENT = new SpongeColoneyFeature(FeatureSpreadConfig.CODEC);

	public static final Feature<BaseTreeFeatureConfig> SCORCHED_TREE = new ScorchedTreeFeature(BaseTreeFeatureConfig.CODEC);
	public static final Feature<BaseTreeFeatureConfig> FROZEN_TREE = new FrozenTreeFeature(BaseTreeFeatureConfig.CODEC);

	public static final Feature<BlockStateFeatureConfig> ROCK = new ModBlockBlobFeature(BlockStateFeatureConfig.CODEC);

	public static final Feature<BlockStateFeatureConfig> PERMIAN_LAKE = new PermianLakesFeature(BlockStateFeatureConfig.CODEC);

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Features");

		LostWorldsRegistry.register("ash_layer_placement", ASH_LAYER_PLACEMENT);
		LostWorldsRegistry.register("calamites_suckwii_placement", CALAMITES_SUCKOWII);
		LostWorldsRegistry.register("geyser_block_placement", GEYSER_BLOCK_PLACEMENT);
		LostWorldsRegistry.register("sponge_coloney_placement", SPONGE_COLONEY_PLACEMENT);
		LostWorldsRegistry.register("scorched_tree", SCORCHED_TREE);
		LostWorldsRegistry.register("frozen_tree", FROZEN_TREE);
		LostWorldsRegistry.register("rock", ROCK);
		LostWorldsRegistry.register("permian_lake", PERMIAN_LAKE);
	}
}
