package lostworlds.server.dimension.biomes;

import java.util.List;
import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import lostworlds.server.biome.LostWorldsBiomes;
import net.minecraft.SharedConstants;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

public final class PermianBiomeBuilder {
	private final Climate.Parameter fullRange = Climate.Parameter.span(-1.0F, 1.0F);
	private final Climate.Parameter[] temperatures = new Climate.Parameter[] { Climate.Parameter.span(-1.0F, -0.45F), Climate.Parameter.span(-0.45F, -0.15F), Climate.Parameter.span(-0.15F, 0.2F), Climate.Parameter.span(0.2F, 0.55F), Climate.Parameter.span(0.55F, 1.0F) };
	private final Climate.Parameter[] humidities = new Climate.Parameter[] { Climate.Parameter.span(-1.0F, -0.35F), Climate.Parameter.span(-0.35F, -0.1F), Climate.Parameter.span(-0.1F, 0.1F), Climate.Parameter.span(0.1F, 0.3F), Climate.Parameter.span(0.3F, 1.0F) };
	private final Climate.Parameter[] erosions = new Climate.Parameter[] { Climate.Parameter.span(-1.0F, -0.78F), Climate.Parameter.span(-0.78F, -0.375F), Climate.Parameter.span(-0.375F, -0.2225F), Climate.Parameter.span(-0.2225F, 0.05F), Climate.Parameter.span(0.05F, 0.45F), Climate.Parameter.span(0.45F, 0.55F), Climate.Parameter.span(0.55F, 1.0F) };
	private final Climate.Parameter frozenRange = this.temperatures[0];
	private final Climate.Parameter unfrozenRange = Climate.Parameter.span(this.temperatures[1], this.temperatures[4]);
	private final Climate.Parameter deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
	private final Climate.Parameter oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
	private final Climate.Parameter coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
	private final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
	private final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
	private final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
	private final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);

	private final ResourceKey<Biome>[][] permianOceans = new ResourceKey[][] { { Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN }, { Biomes.FROZEN_OCEAN, Biomes.COLD_OCEAN, Biomes.OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN } };
	private final ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][] { { Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.TAIGA }, { Biomes.PLAINS, Biomes.PLAINS, LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA }, { LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), Biomes.PLAINS, LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), LostWorldsBiomes.PERMIAN_GINKGO_FOREST.getResourceKey(), Biomes.DARK_FOREST }, { Biomes.SAVANNA, Biomes.SAVANNA, LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), Biomes.JUNGLE, Biomes.JUNGLE }, { Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, Biomes.DESERT } };
	private final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT = new ResourceKey[][] { { Biomes.ICE_SPIKES, null, Biomes.SNOWY_TAIGA, null, null }, { null, null, null, null, Biomes.OLD_GROWTH_PINE_TAIGA }, { Biomes.SUNFLOWER_PLAINS, null, null, Biomes.OLD_GROWTH_BIRCH_FOREST, null }, { null, null, Biomes.PLAINS, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE }, { null, null, null, null, null } };
	private final ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][] { { Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA }, { Biomes.MEADOW, Biomes.MEADOW, LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA }, { Biomes.MEADOW, Biomes.MEADOW, Biomes.MEADOW, Biomes.MEADOW, Biomes.DARK_FOREST }, { Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), Biomes.JUNGLE }, { Biomes.BADLANDS, Biomes.BADLANDS, Biomes.BADLANDS, Biomes.WOODED_BADLANDS, Biomes.WOODED_BADLANDS } };
	private final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT = new ResourceKey[][] { { Biomes.ICE_SPIKES, null, null, null, null }, { null, null, Biomes.MEADOW, Biomes.MEADOW, Biomes.OLD_GROWTH_PINE_TAIGA }, { null, null, LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), LostWorldsBiomes.PERMIAN_GINKGO_FOREST.getResourceKey(), null }, { null, null, null, null, null }, { Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null, null, null } };
	private final ResourceKey<Biome>[][] SHATTERED_BIOMES = new ResourceKey[][] { { Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST }, { Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST }, { Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST }, { null, null, null, null, null }, { null, null, null, null, null } };

	public List<Climate.ParameterPoint> spawnTarget() {
		Climate.Parameter climate$parameter = Climate.Parameter.point(0.0F);
		return List.of(new Climate.ParameterPoint(this.fullRange, this.fullRange, Climate.Parameter.span(this.inlandContinentalness, this.fullRange), this.fullRange, climate$parameter, Climate.Parameter.span(-1.0F, -0.16F), 0L), new Climate.ParameterPoint(this.fullRange, this.fullRange, Climate.Parameter.span(this.inlandContinentalness, this.fullRange), this.fullRange, climate$parameter, Climate.Parameter.span(0.16F, 1.0F), 0L));
	}

	public void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point) {
		if (SharedConstants.debugGenerateSquareTerrainWithoutNoise) {
			TerrainProvider.overworld(false).addDebugBiomesToVisualizeSplinePoints(point);
		} else {
			this.addOffCoastBiomes(point);
			this.addInlandBiomes(point);
			this.addUndergroundBiomes(point);
		}
	}

	private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point) {
		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];
			this.addSurfaceBiome(point, climate$parameter, this.fullRange, this.deepOceanContinentalness, this.fullRange, this.fullRange, 0.0F, this.permianOceans[0][i]);
			this.addSurfaceBiome(point, climate$parameter, this.fullRange, this.oceanContinentalness, this.fullRange, this.fullRange, 0.0F, this.permianOceans[1][i]);
		}

	}

	private void addInlandBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point) {
		this.addMidSlice(point, Climate.Parameter.span(-1.0F, -0.93333334F));
		this.addHighSlice(point, Climate.Parameter.span(-0.93333334F, -0.7666667F));
		this.addPeaks(point, Climate.Parameter.span(-0.7666667F, -0.56666666F));
		this.addHighSlice(point, Climate.Parameter.span(-0.56666666F, -0.4F));
		this.addMidSlice(point, Climate.Parameter.span(-0.4F, -0.26666668F));
		this.addLowSlice(point, Climate.Parameter.span(-0.26666668F, -0.05F));
		this.addValleys(point, Climate.Parameter.span(-0.05F, 0.05F));
		this.addLowSlice(point, Climate.Parameter.span(0.05F, 0.26666668F));
		this.addMidSlice(point, Climate.Parameter.span(0.26666668F, 0.4F));
		this.addHighSlice(point, Climate.Parameter.span(0.4F, 0.56666666F));
		this.addPeaks(point, Climate.Parameter.span(0.56666666F, 0.7666667F));
		this.addHighSlice(point, Climate.Parameter.span(0.7666667F, 0.93333334F));
		this.addMidSlice(point, Climate.Parameter.span(0.93333334F, 1.0F));
	}

	private void addPeaks(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter parameter) {
		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter temperatures = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter humidities = this.humidities[j];
				ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, parameter);
				ResourceKey<Biome> middleOrBadlandsBiome = this.pickMiddleBiomeOrBadlandsIfHot(i, j, parameter);
				ResourceKey<Biome> middleOrBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, parameter);
				ResourceKey<Biome> plateauBiome = this.pickPlateauBiome(i, j, parameter);
				ResourceKey<Biome> shatteredBiome = this.pickShatteredBiome(i, j, parameter);
				ResourceKey<Biome> maybeWindsweptBiome = this.maybePickWindsweptSavannaBiome(i, j, parameter, shatteredBiome);
				ResourceKey<Biome> peakBiome = this.pickPeakBiome(i, j, parameter);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], parameter, 0.0F, peakBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], parameter, 0.0F, middleOrBadlandsOrSlopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], parameter, 0.0F, peakBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], parameter, 0.0F, plateauBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.midInlandContinentalness, this.erosions[3], parameter, 0.0F, middleOrBadlandsBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.farInlandContinentalness, this.erosions[3], parameter, 0.0F, plateauBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], parameter, 0.0F, maybeWindsweptBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, shatteredBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, middleBiome);
			}
		}

	}

	private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter parameter) {
		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter temperatures = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter humidities = this.humidities[j];
				ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, parameter);
				ResourceKey<Biome> middleOrBadlandsBiome = this.pickMiddleBiomeOrBadlandsIfHot(i, j, parameter);
				ResourceKey<Biome> middleOrBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, parameter);
				ResourceKey<Biome> plateauBiome = this.pickPlateauBiome(i, j, parameter);
				ResourceKey<Biome> shatteredBiome = this.pickShatteredBiome(i, j, parameter);
				ResourceKey<Biome> maybeWindsweptBiome = this.maybePickWindsweptSavannaBiome(i, j, parameter, middleBiome);
				ResourceKey<Biome> slopeBiome = this.pickSlopeBiome(i, j, parameter);
				ResourceKey<Biome> peakBiome = this.pickPeakBiome(i, j, parameter);
				this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, this.erosions[0], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], parameter, 0.0F, peakBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, this.erosions[1], parameter, 0.0F, middleOrBadlandsOrSlopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], parameter, 0.0F, plateauBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.midInlandContinentalness, this.erosions[3], parameter, 0.0F, middleOrBadlandsBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.farInlandContinentalness, this.erosions[3], parameter, 0.0F, plateauBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], parameter, 0.0F, maybeWindsweptBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, shatteredBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, middleBiome);
			}
		}

	}

	private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter parameter) {
		this.addSurfaceBiome(point, this.fullRange, this.fullRange, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), parameter, 0.0F, Biomes.STONY_SHORE);
		this.addSurfaceBiome(point, this.unfrozenRange, this.fullRange, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, Biomes.SWAMP);

		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter temperatures = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter humidities = this.humidities[j];
				ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, parameter);
				ResourceKey<Biome> middleOrBadlandsBiome = this.pickMiddleBiomeOrBadlandsIfHot(i, j, parameter);
				ResourceKey<Biome> middleOrBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, parameter);
				ResourceKey<Biome> shatteredBiome = this.pickShatteredBiome(i, j, parameter);
				ResourceKey<Biome> plateauBiome = this.pickPlateauBiome(i, j, parameter);
				ResourceKey<Biome> beachBiome = this.pickBeachBiome(i, j);
				ResourceKey<Biome> maybeWindsweptBiome = this.maybePickWindsweptSavannaBiome(i, j, parameter, middleBiome);
				ResourceKey<Biome> shatteredCoastBiome = this.pickShatteredCoastBiome(i, j, parameter);
				ResourceKey<Biome> slopeBiome = this.pickSlopeBiome(i, j, parameter);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], parameter, 0.0F, middleOrBadlandsOrSlopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.farInlandContinentalness, this.erosions[1], parameter, 0.0F, i == 0 ? slopeBiome : plateauBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, this.erosions[2], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.midInlandContinentalness, this.erosions[2], parameter, 0.0F, middleOrBadlandsBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.farInlandContinentalness, this.erosions[2], parameter, 0.0F, plateauBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], parameter, 0.0F, middleOrBadlandsBiome);
				if (parameter.max() < 0L) {
					this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, this.erosions[4], parameter, 0.0F, beachBiome);
					this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, middleBiome);
				} else {
					this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, middleBiome);
				}

				this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, this.erosions[5], parameter, 0.0F, shatteredCoastBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, this.erosions[5], parameter, 0.0F, maybeWindsweptBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, shatteredBiome);
				if (parameter.max() < 0L) {
					this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, this.erosions[6], parameter, 0.0F, beachBiome);
				} else {
					this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, this.erosions[6], parameter, 0.0F, middleBiome);
				}

				if (i == 0) {
					this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, middleBiome);
				}
			}
		}

	}

	private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter parameter) {
		this.addSurfaceBiome(point, this.fullRange, this.fullRange, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), parameter, 0.0F, Biomes.STONY_SHORE);
		this.addSurfaceBiome(point, this.unfrozenRange, this.fullRange, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, Biomes.SWAMP);

		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter temperatures = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter humidities = this.humidities[j];
				ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, parameter);
				ResourceKey<Biome> middleOrBadlandsBiome = this.pickMiddleBiomeOrBadlandsIfHot(i, j, parameter);
				ResourceKey<Biome> middleOrBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, parameter);
				ResourceKey<Biome> beachBiome = this.pickBeachBiome(i, j);
				ResourceKey<Biome> maybeWindsweptBiome = this.maybePickWindsweptSavannaBiome(i, j, parameter, middleBiome);
				ResourceKey<Biome> shatteredCoastBiome = this.pickShatteredCoastBiome(i, j, parameter);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, middleOrBadlandsBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, middleOrBadlandsOrSlopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, middleOrBadlandsBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, Climate.Parameter.span(this.erosions[3], this.erosions[4]), parameter, 0.0F, beachBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, this.erosions[5], parameter, 0.0F, shatteredCoastBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, this.erosions[5], parameter, 0.0F, maybeWindsweptBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, this.erosions[6], parameter, 0.0F, beachBiome);
				if (i == 0) {
					this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, middleBiome);
				}
			}
		}

	}

	private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter parameter) {
		this.addSurfaceBiome(point, this.frozenRange, this.fullRange, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, parameter.max() < 0L ? Biomes.STONY_SHORE : Biomes.FROZEN_RIVER);
		this.addSurfaceBiome(point, this.unfrozenRange, this.fullRange, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, parameter.max() < 0L ? Biomes.STONY_SHORE : Biomes.RIVER);
		this.addSurfaceBiome(point, this.frozenRange, this.fullRange, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, Biomes.FROZEN_RIVER);
		this.addSurfaceBiome(point, this.unfrozenRange, this.fullRange, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, Biomes.RIVER);
		this.addSurfaceBiome(point, this.frozenRange, this.fullRange, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), parameter, 0.0F, Biomes.FROZEN_RIVER);
		this.addSurfaceBiome(point, this.unfrozenRange, this.fullRange, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), parameter, 0.0F, Biomes.RIVER);
		this.addSurfaceBiome(point, this.frozenRange, this.fullRange, this.coastContinentalness, this.erosions[6], parameter, 0.0F, Biomes.FROZEN_RIVER);
		this.addSurfaceBiome(point, this.unfrozenRange, this.fullRange, this.coastContinentalness, this.erosions[6], parameter, 0.0F, Biomes.RIVER);
		this.addSurfaceBiome(point, this.unfrozenRange, this.fullRange, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, Biomes.SWAMP);
		this.addSurfaceBiome(point, this.frozenRange, this.fullRange, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, Biomes.FROZEN_RIVER);

		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter climate$parameter1 = this.humidities[j];
				ResourceKey<Biome> middleOrBadlandsBiome = this.pickMiddleBiomeOrBadlandsIfHot(i, j, parameter);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, middleOrBadlandsBiome);
			}
		}

	}

	private void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point) {
		this.addUndergroundBiome(point, this.fullRange, this.fullRange, Climate.Parameter.span(0.8F, 1.0F), this.fullRange, this.fullRange, 0.0F, Biomes.DRIPSTONE_CAVES);
		this.addUndergroundBiome(point, this.fullRange, Climate.Parameter.span(0.7F, 1.0F), this.fullRange, this.fullRange, this.fullRange, 0.0F, Biomes.LUSH_CAVES);
	}

	private ResourceKey<Biome> pickMiddleBiome(int temperature, int biome, Climate.Parameter parameter) {
		if (parameter.max() < 0L) {
			return this.MIDDLE_BIOMES[temperature][biome];
		} else {
			ResourceKey<Biome> variant = this.MIDDLE_BIOMES_VARIANT[temperature][biome];
			return variant == null ? this.MIDDLE_BIOMES[temperature][biome] : variant;
		}
	}

	private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHot(int temperature, int biome, Climate.Parameter parameter) {
		return temperature == 4 ? this.pickBadlandsBiome(biome, parameter) : this.pickMiddleBiome(temperature, biome, parameter);
	}

	private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(int temperature, int biome, Climate.Parameter parameter) {
		return temperature == 0 ? this.pickSlopeBiome(temperature, biome, parameter) : this.pickMiddleBiomeOrBadlandsIfHot(temperature, biome, parameter);
	}

	private ResourceKey<Biome> maybePickWindsweptSavannaBiome(int temperature, int biome, Climate.Parameter parameter, ResourceKey<Biome> other) {
		return temperature > 1 && biome < 4 && parameter.max() >= 0L ? Biomes.WINDSWEPT_SAVANNA : other;
	}

	private ResourceKey<Biome> pickShatteredCoastBiome(int temperature, int biome, Climate.Parameter parameter) {
		ResourceKey<Biome> middleOrBeach = parameter.max() >= 0L ? this.pickMiddleBiome(temperature, biome, parameter) : this.pickBeachBiome(temperature, biome);
		return this.maybePickWindsweptSavannaBiome(temperature, biome, parameter, middleOrBeach);
	}

	private ResourceKey<Biome> pickBeachBiome(int temperature, int biome) {
		if (temperature == 0) {
			return Biomes.SNOWY_BEACH;
		} else {
			return temperature == 4 ? Biomes.DESERT : Biomes.BEACH;
		}
	}

	private ResourceKey<Biome> pickBadlandsBiome(int biome, Climate.Parameter parameter) {
		if (biome < 2) {
			return parameter.max() < 0L ? Biomes.ERODED_BADLANDS : Biomes.BADLANDS;
		} else {
			return biome < 3 ? Biomes.BADLANDS : Biomes.WOODED_BADLANDS;
		}
	}

	private ResourceKey<Biome> pickPlateauBiome(int temperature, int biome, Climate.Parameter parameter) {
		if (parameter.max() < 0L) {
			return this.PLATEAU_BIOMES[temperature][biome];
		} else {
			ResourceKey<Biome> variant = this.PLATEAU_BIOMES_VARIANT[temperature][biome];
			return variant == null ? this.PLATEAU_BIOMES[temperature][biome] : variant;
		}
	}

	private ResourceKey<Biome> pickPeakBiome(int temperature, int biome, Climate.Parameter parameter) {
		if (temperature <= 2) {
			return parameter.max() < 0L ? Biomes.JAGGED_PEAKS : Biomes.FROZEN_PEAKS;
		} else {
			return temperature == 3 ? Biomes.STONY_PEAKS : this.pickBadlandsBiome(biome, parameter);
		}
	}

	private ResourceKey<Biome> pickSlopeBiome(int temperature, int biome, Climate.Parameter parameter) {
		if (temperature >= 3) {
			return this.pickPlateauBiome(temperature, biome, parameter);
		} else {
			return biome <= 1 ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
		}
	}

	private ResourceKey<Biome> pickShatteredBiome(int temperature, int biome, Climate.Parameter parameter) {
		ResourceKey<Biome> shattered = this.SHATTERED_BIOMES[temperature][biome];
		return shattered == null ? this.pickMiddleBiome(temperature, biome, parameter) : shattered;
	}

	private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome) {
		point.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0.0F), weirdness, offset), biome));
		point.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.0F), weirdness, offset), biome));
	}

	private void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter v, float offset, ResourceKey<Biome> biome) {
		point.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.2F, 0.9F), v, offset), biome));
	}
}
