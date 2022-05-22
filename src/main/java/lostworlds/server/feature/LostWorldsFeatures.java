package lostworlds.server.feature;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.feature.config.ModBlockstateFeatureConfig;
import lostworlds.server.feature.config.ModLakeFeatureConfig;
import lostworlds.server.feature.config.ModOreFeatureConfig;
import lostworlds.server.feature.config.ModReplaceBlockConfig;
import lostworlds.server.feature.tree.GinkgoTreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsFeatures {
	public static final DeferredRegister<Feature<?>> FEATURE = DeferredRegister.create(ForgeRegistries.FEATURES, LostWorldsUtils.ID);

	public static final Feature<NoneFeatureConfiguration> ANCIENT_SPRING = register("ancient_spring", new AncientSpringFeature(NoneFeatureConfiguration.CODEC));

	public static final Feature<NoneFeatureConfiguration> ASH_LAYER = register("ash_layer", new AshFeature(NoneFeatureConfiguration.CODEC));

	public static final Feature<ProbabilityFeatureConfiguration> CALAMITES_SUCKOWII = register("calamites_suckowii", new CalamitesSuckowiiFeature(ProbabilityFeatureConfiguration.CODEC));

	public static final Feature<CountConfiguration> GEYSER = register("geyser", new GeyserBlockFeature(CountConfiguration.CODEC));

	public static final Feature<ModOreFeatureConfig> MOD_ORE = register("mod_ore", new ModOreFeature(ModOreFeatureConfig.CODEC));
	public static final Feature<ModReplaceBlockConfig> MOD_REPLACE_BLOCK_FEATURE = register("mod_replace_block_feature", new ModReplaceBlockFeature(ModReplaceBlockConfig.CODEC));

	public static final Feature<CountConfiguration> SPONGE_COLONEY = register("sponge_coloney", new SpongeColoneyFeature(CountConfiguration.CODEC));

	// Trees
	public static final Feature<NoneFeatureConfiguration> GINKGO_TREE = register("ginkgo_tree", new GinkgoTreeFeature(NoneFeatureConfiguration.CODEC));

	public static final Feature<TreeConfiguration> FROZEN_TREE = register("frozen_tree", new FrozenTreeFeature(TreeConfiguration.CODEC));

	public static final Feature<ModBlockstateFeatureConfig> MOD_ROCK = register("mod_rock", new ModBlockBlobFeature(ModBlockstateFeatureConfig.CODEC));

	public static final Feature<ModLakeFeatureConfig> MOD_LAKE = register("mod_lake", new ModLakesFeature(ModLakeFeatureConfig.CODEC));

	public static <T extends FeatureConfiguration> Feature<T> register(String id, Feature<T> feature) {
		FEATURE.register(id, () -> feature);
		return feature;
	}

	public static void deferred(IEventBus bus) {
		FEATURE.register(bus);
	}
}
