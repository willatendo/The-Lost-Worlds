package lostworlds.server.biome.features.configured;

import static lostworlds.server.biome.features.configured.LostWorldsConfiguredFeatures.register;

import java.util.OptionalInt;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.feature.LostWorldsFeatures;
import lostworlds.server.util.registrate.WoodTypes;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.RegistryObject;

public class TreeFeatures {
	public static final RegistryObject<ConfiguredFeature<?, ?>> ARCTIC_CONIFER_TREE = register("arctic_conifer_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LEAVES).get().get()), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
	public static final RegistryObject<ConfiguredFeature<?, ?>> FROZEN_TREE = register("frozen_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(Blocks.SNOW_BLOCK), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
	public static final RegistryObject<ConfiguredFeature<?, ?>> BROKEN_TREE = register("broken_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build());
	public static final RegistryObject<ConfiguredFeature<?, ?>> CALAMITES_TREE = register("calamites_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(15, 2, 1), BlockStateProvider.simple(LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.LEAVES).get().get()), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
	public static final RegistryObject<ConfiguredFeature<?, ?>> CONIFER_TREE = register("conifer_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(LostWorldsBlocks.CONIFER.getBlock(WoodTypes.LEAVES).get().get()), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
	public static final RegistryObject<ConfiguredFeature<?, ?>> CYPRESS_TREE = register("cypress_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LOG).get().get()), new ForkingTrunkPlacer(6, 2, 1), BlockStateProvider.simple(LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.LEAVES).get().get()), new DarkOakFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).ignoreVines().build());
	public static final RegistryObject<ConfiguredFeature<?, ?>> GINKGO_TREE = register("ginkgo_tree", LostWorldsFeatures.GINKGO_TREE);
	public static final RegistryObject<ConfiguredFeature<?, ?>> BABY_SEQUOIA_TREE = register("baby_sequoia_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(10, 2, 4), BlockStateProvider.simple(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LEAVES).get().get()), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(1, 1, 2))).build());
	public static final RegistryObject<ConfiguredFeature<?, ?>> SEQUOIA_TREE = register("sequoia_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LOG).get().get()), new GiantTrunkPlacer(32, 2, 14), BlockStateProvider.simple(LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.LEAVES).get().get()), new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 17)), new TwoLayersFeatureSize(1, 1, 2))).build());
	public static final RegistryObject<ConfiguredFeature<?, ?>> ARAUCARIA_TREE = register("araucaria_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.LEAVES).get().get()), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
	public static final RegistryObject<ConfiguredFeature<?, ?>> SCORCHED_TREE = register("scorched_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.LOG).get().get()), new StraightTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR), new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build());

	public static void init() {
	}
}
