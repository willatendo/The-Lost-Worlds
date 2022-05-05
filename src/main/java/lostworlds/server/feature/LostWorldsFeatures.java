package lostworlds.server.feature;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.feature.config.ModBlockstateFeatureConfig;
import lostworlds.server.feature.config.ModLakeFeatureConfig;
import lostworlds.server.feature.config.ModOreFeatureConfig;
import lostworlds.server.feature.config.ModReplaceBlockConfig;
import lostworlds.server.feature.tree.GinkgoTreeFeature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsFeatures {
	public static final DeferredRegister<Feature<?>> FEATURE = DeferredRegister.create(ForgeRegistries.FEATURES, LostWorldsUtils.ID);

	public static final Feature<NoFeatureConfig> ANCIENT_SPRING = register("ancient_spring", new AncientSpringFeature(NoFeatureConfig.CODEC));

	public static final Feature<NoFeatureConfig> ASH_LAYER = register("ash_layer", new AshFeature(NoFeatureConfig.CODEC));

	public static final Feature<ProbabilityConfig> CALAMITES_SUCKOWII = register("calamites_suckowii", new CalamitesSuckowiiFeature(ProbabilityConfig.CODEC));

	public static final Feature<FeatureSpreadConfig> GEYSER = register("geyser", new GeyserBlockFeature(FeatureSpreadConfig.CODEC));

	public static final Feature<ModOreFeatureConfig> MOD_ORE = register("mod_ore", new ModOreFeature(ModOreFeatureConfig.CODEC));
	public static final Feature<ModReplaceBlockConfig> MOD_REPLACE_BLOCK_FEATURE = register("mod_replace_block_feature", new ModReplaceBlockFeature(ModReplaceBlockConfig.CODEC));

	public static final Feature<FeatureSpreadConfig> SPONGE_COLONEY = register("sponge_coloney", new SpongeColoneyFeature(FeatureSpreadConfig.CODEC));

	// Trees
	public static final Feature<NoFeatureConfig> GINKGO_TREE = register("ginkgo_tree", new GinkgoTreeFeature(NoFeatureConfig.CODEC));

	public static final Feature<BaseTreeFeatureConfig> FROZEN_TREE = register("frozen_tree", new FrozenTreeFeature(BaseTreeFeatureConfig.CODEC));

	public static final Feature<ModBlockstateFeatureConfig> MOD_ROCK = register("mod_rock", new ModBlockBlobFeature(ModBlockstateFeatureConfig.CODEC));

	public static final Feature<ModLakeFeatureConfig> MOD_LAKE = register("mod_lake", new ModLakesFeature(ModLakeFeatureConfig.CODEC));

	public static <T extends IFeatureConfig> Feature<T> register(String id, Feature<T> feature) {
		FEATURE.register(id, () -> feature);
		return feature;
	}

	public static void deferred(IEventBus bus) {
		FEATURE.register(bus);
	}
}
