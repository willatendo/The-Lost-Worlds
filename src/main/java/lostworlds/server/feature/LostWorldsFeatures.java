package lostworlds.server.feature;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.feature.config.ModBlockstateFeatureConfig;
import lostworlds.server.feature.tree.GinkgoTreeFeature;
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

	public static final Feature<CountConfiguration> SPONGE_COLONEY = register("sponge_coloney", new SpongeColoneyFeature(CountConfiguration.CODEC));

	// Trees
	public static final Feature<NoneFeatureConfiguration> GINKGO_TREE = register("ginkgo_tree", new GinkgoTreeFeature(NoneFeatureConfiguration.CODEC));

	public static final Feature<ModBlockstateFeatureConfig> MOD_ROCK = register("mod_rock", new ModBlockBlobFeature(ModBlockstateFeatureConfig.CODEC));

	public static <T extends FeatureConfiguration> Feature<T> register(String id, Feature<T> feature) {
		FEATURE.register(id, () -> feature);
		return feature;
	}

	public static void deferred(IEventBus bus) {
		FEATURE.register(bus);
	}
}
