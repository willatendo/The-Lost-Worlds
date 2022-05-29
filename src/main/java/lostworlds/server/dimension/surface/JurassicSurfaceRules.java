package lostworlds.server.dimension.surface;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import lostworlds.api.APIModSurfaceRuleAdder;
import lostworlds.api.APIRegistry;
import lostworlds.server.biome.LostWorldsBiomeKeys;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class JurassicSurfaceRules extends SurfaceRuleParts {
	public static final SurfaceRules.RuleSource MOSSY_SOIL = makeStateRule(LostWorldsBlocks.MOSSY_SOIL.get());
	public static final SurfaceRules.RuleSource ROCKY_SOIL = makeStateRule(LostWorldsBlocks.ROCKY_SOIL.get());

	public static SurfaceRules.RuleSource jurassic() {
		SurfaceRules.ConditionSource notUnderwater = SurfaceRules.waterBlockCheck(0, 0);
		SurfaceRules.RuleSource mossySoilOrDirt = SurfaceRules.sequence(SurfaceRules.ifTrue(notUnderwater, MOSSY_SOIL), DIRT);
		SurfaceRules.RuleSource podzolOrDirt = SurfaceRules.sequence(SurfaceRules.ifTrue(notUnderwater, PODZOL), DIRT);

		// Biomes
		SurfaceRules.RuleSource araucariaForest = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.JURASSIC_ARAUCARIA_FOREST), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), mossySoilOrDirt), podzolOrDirt)));
		SurfaceRules.RuleSource coniferForest = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.JURASSIC_CONIFER_FOREST), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), mossySoilOrDirt), podzolOrDirt)));
		SurfaceRules.RuleSource ginkgoForest = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.JURASSIC_GINKGO_FOREST), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), mossySoilOrDirt), podzolOrDirt)));
		SurfaceRules.RuleSource redwoodsForest = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.JURASSIC_REDWOODS_FOREST), podzolOrDirt));
		SurfaceRules.RuleSource plains = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.JURASSIC_PLAINS), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), ROCKY_SOIL), SurfaceRules.ifTrue(SurfaceRules.not(surfaceNoiseAbove(-0.95D)), MOSSY_SOIL), podzolOrDirt)));

		// Jurassic Dimension
		SurfaceRules.RuleSource global = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(araucariaForest, coniferForest, ginkgoForest, redwoodsForest, plains)), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT));

		Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
		builder.add(SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), global));

		for (APIModSurfaceRuleAdder adder : APIRegistry.JURASSIC_SURFACE_RULES) {
			builder.add(adder.doSurfaceRules());
		}

		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE));
		return SurfaceRules.sequence(builder.build().toArray((rules) -> {
			return new SurfaceRules.RuleSource[rules];
		}));
	}
}
