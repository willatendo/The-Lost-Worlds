package lostworlds.server.dimension.surface;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import lostworlds.api.APIModSurfaceRuleAdder;
import lostworlds.api.APIRegistry;
import lostworlds.server.biome.LostWorldsBiomeKeys;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class PermianSurfaceRules extends SurfaceRuleParts {
	public static final SurfaceRules.RuleSource PERMIAN_STONE = makeStateRule(LostWorldsBlocks.PERMIAN_STONE.get());
	public static final SurfaceRules.RuleSource PERMIAN_DEEPSLATE = makeStateRule(LostWorldsBlocks.PERMIAN_DEEPSLATE.get());
	public static final SurfaceRules.RuleSource MOSSY_SOIL = makeStateRule(LostWorldsBlocks.MOSSY_SOIL.get());
	public static final SurfaceRules.RuleSource DRIED_SOIL = makeStateRule(LostWorldsBlocks.DRIED_SOIL.get());
	public static final SurfaceRules.RuleSource CRACKED_SOIL = makeStateRule(LostWorldsBlocks.CRACKED_SOIL.get());
	public static final SurfaceRules.RuleSource PERMIAN_SAND = makeStateRule(LostWorldsBlocks.PERMIAN_SAND.get());
	public static final SurfaceRules.RuleSource VOLCANIC_ASH = makeStateRule(LostWorldsBlocks.VOLCANIC_ASH.get());
	public static final SurfaceRules.RuleSource MUD = makeStateRule(LostWorldsBlocks.MUD.get());
	public static final SurfaceRules.RuleSource SILT = makeStateRule(LostWorldsBlocks.SILT.get());
	public static final SurfaceRules.RuleSource RAW_MARBLE = makeStateRule(LostWorldsBlocks.RAW_MARBLE.get());

	public static SurfaceRules.RuleSource permian() {
		SurfaceRules.ConditionSource notUnderwater = SurfaceRules.waterBlockCheck(0, 0);
		SurfaceRules.RuleSource mossySoilOrDirt = SurfaceRules.sequence(SurfaceRules.ifTrue(notUnderwater, MOSSY_SOIL), DIRT);
		SurfaceRules.RuleSource podzolOrDirt = SurfaceRules.sequence(SurfaceRules.ifTrue(notUnderwater, PODZOL), DIRT);
		SurfaceRules.RuleSource mossySoilOrSilt = SurfaceRules.sequence(SurfaceRules.ifTrue(notUnderwater, MOSSY_SOIL), SILT);
		SurfaceRules.RuleSource driedSoilOrDirt = SurfaceRules.sequence(SurfaceRules.ifTrue(notUnderwater, DRIED_SOIL), DIRT);

		// Biomes
		SurfaceRules.RuleSource coniferForest = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), mossySoilOrDirt), podzolOrDirt)));
		SurfaceRules.RuleSource ginkgoForest = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_GINKGO_FOREST), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), mossySoilOrDirt), podzolOrDirt)));
		SurfaceRules.RuleSource plains = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_PLAINS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), driedSoilOrDirt), mossySoilOrDirt)), SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), driedSoilOrDirt), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT))));
		SurfaceRules.RuleSource driedPlains = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_DRIED_PLAINS), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), driedSoilOrDirt), CRACKED_SOIL)));
		SurfaceRules.RuleSource desert = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_DESERT), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PERMIAN_STONE), PERMIAN_SAND)), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PERMIAN_STONE), PERMIAN_SAND)))));
		SurfaceRules.RuleSource floodBasalts = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_FLOOD_BASALTS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, BASALT), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PATCH, -0.012D), SurfaceRules.ifTrue(SurfaceRules.yStartCheck(VerticalAnchor.absolute(30), 0), SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(35), 0)), GRAVEL))), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.NETHER_STATE_SELECTOR, 0.0D), BASALT), BLACKSTONE)))));
		SurfaceRules.RuleSource ashyMedows = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_ASHY_MEDOWS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PERMIAN_STONE), VOLCANIC_ASH)), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PERMIAN_STONE), VOLCANIC_ASH)))));
		SurfaceRules.RuleSource windsweptHills = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), PERMIAN_STONE), driedSoilOrDirt)), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), PERMIAN_STONE), driedSoilOrDirt)))));
		SurfaceRules.RuleSource marsh = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_MARSH), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0), SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0)), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER))), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), MUD), mossySoilOrSilt)), SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), MUD), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT)))));
		SurfaceRules.RuleSource river = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_RIVER), PERMIAN_SAND));
		SurfaceRules.RuleSource ocean = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_OCEAN), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), SILT), PERMIAN_SAND)));
		SurfaceRules.RuleSource warmOcean = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.WARM_PERMIAN_OCEAN), SILT));
		SurfaceRules.RuleSource shore = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_SHORE), PERMIAN_SAND));
		SurfaceRules.RuleSource grove = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_GROVE), SurfaceRules.sequence(SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45D, 0.58D), SurfaceRules.ifTrue(notUnderwater, POWDER_SNOW)), DIRT), SurfaceRules.ifTrue(notUnderwater, SNOW_BLOCK))));
		SurfaceRules.RuleSource snowySlopes = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_SNOWY_SLOPES), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.steep(), STONE), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.35D, 0.6D), SurfaceRules.ifTrue(notUnderwater, POWDER_SNOW)), SurfaceRules.ifTrue(notUnderwater, SNOW_BLOCK))));
		SurfaceRules.RuleSource frozenPeaks = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_FROZEN_PEAKS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.steep(), PACKED_ICE), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, 0.0D, 0.2D), PACKED_ICE), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, 0.0D, 0.025D), ICE), SurfaceRules.ifTrue(notUnderwater, SNOW_BLOCK))));
		SurfaceRules.RuleSource jaggedPeaks = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.JAGGED_PEAKS), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.steep(), STONE), SurfaceRules.ifTrue(notUnderwater, SNOW_BLOCK))));
		SurfaceRules.RuleSource stonyPeaks = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(LostWorldsBiomeKeys.PERMIAN_SNOWY_SLOPES), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.CALCITE, -0.0125D, 0.0125D), RAW_MARBLE), PERMIAN_STONE)));

		// Permian Dimension
		SurfaceRules.RuleSource global = SurfaceRules.sequence(plains, desert, floodBasalts, ashyMedows, windsweptHills, marsh, river, ocean, warmOcean, shore, driedPlains, grove, snowySlopes, stonyPeaks, frozenPeaks, jaggedPeaks, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(coniferForest, ginkgoForest)), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT));

		Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
		builder.add(SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), global));

		for (APIModSurfaceRuleAdder adder : APIRegistry.PERMIAN_SURFACE_RULES) {
			builder.add(adder.doSurfaceRules());
		}

		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), PERMIAN_DEEPSLATE));
		return SurfaceRules.sequence(builder.build().toArray((rules) -> {
			return new SurfaceRules.RuleSource[rules];
		}));
	}
}
