package lostworlds.server.feature;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.feature.config.SuppliedBlockstateFeatureConfig;
import lostworlds.server.feature.config.SuppliedDiskConfiguration;
import lostworlds.server.feature.mod.ModBlockBlobFeature;
import lostworlds.server.feature.mod.ModDiskFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.Keys.FEATURES, LostWorldsUtils.ID);

	public static final Feature<NoneFeatureConfiguration> ANCIENT_SPRING = register("ancient_spring", new AncientSpringFeature(NoneFeatureConfiguration.CODEC));

	public static final Feature<NoneFeatureConfiguration> ASH_LAYER = register("ash_layer", new AshFeature(NoneFeatureConfiguration.CODEC));

	public static final Feature<ProbabilityFeatureConfiguration> CALAMITES_SUCKOWII = register("calamites_suckowii", new CalamitesSuckowiiFeature(ProbabilityFeatureConfiguration.CODEC));

	public static final Feature<CountConfiguration> GEYSER = register("geyser", new GeyserBlockFeature(CountConfiguration.CODEC));

	public static final Feature<CountConfiguration> SPONGE_COLONEY = register("sponge_coloney", new SpongeColoneyFeature(CountConfiguration.CODEC));

	// Trees
	public static final Feature<NoneFeatureConfiguration> GINKGO_TREE = register("ginkgo_tree", new GinkgoTreeFeature(NoneFeatureConfiguration.CODEC));

	// Mods
	public static final Feature<SuppliedBlockstateFeatureConfig> MOD_ROCK = register("mod_rock", new ModBlockBlobFeature(SuppliedBlockstateFeatureConfig.CODEC));
	
	public static final Feature<SuppliedDiskConfiguration> MOD_DISK = register("mod_disk", new ModDiskFeature(SuppliedDiskConfiguration.CODEC));

	public static <T extends FeatureConfiguration> Feature<T> register(String id, Feature<T> feature) {
		FEATURES.register(id, () -> feature);
		return feature;
	}
}
