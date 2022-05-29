package lostworlds.server.dimension.surface;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import lostworlds.api.APIModSurfaceRuleAdder;
import lostworlds.api.APIRegistry;
import lostworlds.server.biome.LostWorldsBiomeKeys;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class PermianSurfaceRules extends SurfaceRuleParts {
	public static final SurfaceRules.RuleSource PERMIAN_STONE = makeStateRule(LostWorldsBlocks.PERMIAN_STONE.get());
	public static final SurfaceRules.RuleSource MOSSY_SOIL = makeStateRule(LostWorldsBlocks.MOSSY_SOIL.get());
	public static final SurfaceRules.RuleSource DRIED_SOIL = makeStateRule(LostWorldsBlocks.DRIED_SOIL.get());
	public static final SurfaceRules.RuleSource CRACKED_SOIL = makeStateRule(LostWorldsBlocks.CRACKED_SOIL.get());
	public static final SurfaceRules.RuleSource PERMIAN_SAND = makeStateRule(LostWorldsBlocks.PERMIAN_SAND.get());
	public static final SurfaceRules.RuleSource VOLCANIC_ASH = makeStateRule(LostWorldsBlocks.VOLCANIC_ASH.get());
	public static final SurfaceRules.RuleSource MUD = makeStateRule(LostWorldsBlocks.MUD.get());
	public static final SurfaceRules.RuleSource SILT = makeStateRule(LostWorldsBlocks.SILT.get());

	public static SurfaceRules.RuleSource permian() {
		SurfaceRules.ConditionSource notUnderwater = SurfaceRules.waterBlockCheck(0, 0);
		SurfaceRules.RuleSource mossySoilOrDirt = SurfaceRules.sequence(SurfaceRules.ifTrue(notUnderwater, MOSSY_SOIL), DIRT);
		SurfaceRules.RuleSource podzolOrDirt = SurfaceRules.sequence(SurfaceRules.ifTrue(notUnderwater, PODZOL), DIRT);
		SurfaceRules.RuleSource mossySoilOrSilt = SurfaceRules.sequence(SurfaceRules.ifTrue(notUnderwater, MOSSY_SOIL), SILT);

		// Biomes
		SurfaceRules.RuleSource coniferForest = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), mossySoilOrDirt), podzolOrDirt)));
		SurfaceRules.RuleSource ginkgoForest = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_GINKGO_FOREST), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), mossySoilOrDirt), podzolOrDirt)));
		SurfaceRules.RuleSource plains = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_PLAINS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), DRIED_SOIL), mossySoilOrDirt)), SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), DRIED_SOIL), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT))));
		SurfaceRules.RuleSource driedPlains = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_DRIED_PLAINS), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), DRIED_SOIL), CRACKED_SOIL)));
		SurfaceRules.RuleSource desert = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_DESERT), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PERMIAN_STONE), PERMIAN_SAND)), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PERMIAN_STONE), PERMIAN_SAND)))));
		SurfaceRules.RuleSource floodBasalts = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_FLOOD_BASALTS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, BASALT), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PATCH, -0.012D), SurfaceRules.ifTrue(SurfaceRules.yStartCheck(VerticalAnchor.absolute(30), 0), SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(35), 0)), GRAVEL))), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.NETHER_STATE_SELECTOR, 0.0D), BASALT), BLACKSTONE)))));
		SurfaceRules.RuleSource ashyMedows = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_ASHY_MEDOWS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PERMIAN_STONE), VOLCANIC_ASH)), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PERMIAN_STONE), VOLCANIC_ASH)))));
		SurfaceRules.RuleSource windsweptHills = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), PERMIAN_STONE), DRIED_SOIL)), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), PERMIAN_STONE), DRIED_SOIL)))));
		SurfaceRules.RuleSource marsh = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_MARSH), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0), SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0)), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER))), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), MUD), mossySoilOrSilt)), SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), MUD), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT)))));
		SurfaceRules.RuleSource river = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_RIVER), PERMIAN_SAND));
		SurfaceRules.RuleSource ocean = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_OCEAN), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), SILT), PERMIAN_SAND)));
		SurfaceRules.RuleSource warmOcean = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.WARM_PERMIAN_OCEAN), SILT));
		SurfaceRules.RuleSource shore = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_SHORE), PERMIAN_SAND));

		// Permian Dimension
		SurfaceRules.RuleSource global = SurfaceRules.sequence(plains, desert, floodBasalts, ashyMedows, windsweptHills, marsh, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(coniferForest, ginkgoForest, driedPlains, river, ocean, warmOcean, shore)), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT));

		Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
		builder.add(SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), global));

		for (APIModSurfaceRuleAdder adder : APIRegistry.PERMIAN_SURFACE_RULES) {
			builder.add(adder.doSurfaceRules());
		}

		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE));
		return SurfaceRules.sequence(builder.build().toArray((rules) -> {
			return new SurfaceRules.RuleSource[rules];
		}));
	}
}
