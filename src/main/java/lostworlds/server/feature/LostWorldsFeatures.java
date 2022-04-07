package lostworlds.server.feature;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsFeatures {
	public static final Feature<NoFeatureConfig> ASH_LAYER_PLACEMENT = new AshFeature(NoFeatureConfig.CODEC);

	public static final Feature<ProbabilityConfig> CALAMITES_SUCKOWII = new CalamitesSuckowiiFeature(ProbabilityConfig.CODEC);

	public static final Feature<FeatureSpreadConfig> GEYSER_BLOCK_PLACEMENT = new GeyserBlockFeature(FeatureSpreadConfig.CODEC);
	public static final Feature<FeatureSpreadConfig> SPONGE_COLONEY_PLACEMENT = new SpongeColoneyFeature(FeatureSpreadConfig.CODEC);

	public static final Feature<BaseTreeFeatureConfig> SCORCHED_TREE = new ScorchedTreeFeature(BaseTreeFeatureConfig.CODEC);
	public static final Feature<BaseTreeFeatureConfig> FROZEN_TREE = new FrozenTreeFeature(BaseTreeFeatureConfig.CODEC);

	public static final Feature<BlockStateFeatureConfig> ROCK = new ModBlockBlobFeature(BlockStateFeatureConfig.CODEC);

	public static final Feature<BlockStateFeatureConfig> PERMIAN_LAKE = new PermianLakesFeature(BlockStateFeatureConfig.CODEC);

	public static Feature<?> register(String id, Feature<?> feature) {
		feature.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.FEATURES.register(feature);
		return feature;
	}

	public static void init() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Features");

		register("ash_layer", ASH_LAYER_PLACEMENT);
		register("calamites_suckwii", CALAMITES_SUCKOWII);
		register("geyser_block", GEYSER_BLOCK_PLACEMENT);
		register("sponge_coloney", SPONGE_COLONEY_PLACEMENT);
		register("scorched_tree", SCORCHED_TREE);
		register("frozen_tree", FROZEN_TREE);
		register("rock", ROCK);
		register("permian_lake", PERMIAN_LAKE);
	}
}
