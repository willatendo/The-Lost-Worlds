package lostworlds.server.dimension.biomes;

import java.util.List;
import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import lostworlds.server.biome.LostWorldsBiomeKeys;
import net.minecraft.SharedConstants;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.TerrainShaper;

public class PermianBiomeBuilder {
	public static final float HIGH_START = 0.4F;
	public static final float PEAK_START = 0.56666666F;
	public static final float NEAR_INLAND_START = -0.11F;
	public static final float MID_INLAND_START = 0.03F;
	public static final float FAR_INLAND_START = 0.3F;
	public static final float EROSION_INDEX_1_START = -0.78F;
	public static final float EROSION_INDEX_2_START = -0.375F;
	private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);
	private final Climate.Parameter[] temperatures = new Climate.Parameter[] { Climate.Parameter.span(-1.0F, -0.45F), Climate.Parameter.span(-0.45F, -0.15F), Climate.Parameter.span(-0.15F, 0.2F), Climate.Parameter.span(0.2F, 0.55F), Climate.Parameter.span(0.55F, 1.0F) };
	private final Climate.Parameter[] humidities = new Climate.Parameter[] { Climate.Parameter.span(-1.0F, -0.35F), Climate.Parameter.span(-0.35F, -0.1F), Climate.Parameter.span(-0.1F, 0.1F), Climate.Parameter.span(0.1F, 0.3F), Climate.Parameter.span(0.3F, 1.0F) };
	private final Climate.Parameter[] erosions = new Climate.Parameter[] { Climate.Parameter.span(-1.0F, -0.78F), Climate.Parameter.span(-0.78F, -0.375F), Climate.Parameter.span(-0.375F, -0.2225F), Climate.Parameter.span(-0.2225F, 0.05F), Climate.Parameter.span(0.05F, 0.45F), Climate.Parameter.span(0.45F, 0.55F), Climate.Parameter.span(0.55F, 1.0F) };
	private final Climate.Parameter FROZEN_RANGE = this.temperatures[0];
	private final Climate.Parameter UNFROZEN_RANGE = Climate.Parameter.span(this.temperatures[1], this.temperatures[4]);
	private final Climate.Parameter mushroomFieldsContinentalness = Climate.Parameter.span(-1.2F, -1.05F);
	private final Climate.Parameter deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
	private final Climate.Parameter oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
	private final Climate.Parameter coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
	private final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
	private final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
	private final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
	private final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);
	private final ResourceKey<Biome>[][] OCEANS = new ResourceKey[][] { { LostWorldsBiomeKeys.DEEP_PERMIAN_OCEAN, LostWorldsBiomeKeys.DEEP_PERMIAN_OCEAN, LostWorldsBiomeKeys.DEEP_PERMIAN_OCEAN, LostWorldsBiomeKeys.DEEP_WARM_PERMIAN_OCEAN, LostWorldsBiomeKeys.DEEP_WARM_PERMIAN_OCEAN }, { LostWorldsBiomeKeys.PERMIAN_OCEAN, LostWorldsBiomeKeys.PERMIAN_OCEAN, LostWorldsBiomeKeys.PERMIAN_OCEAN, LostWorldsBiomeKeys.WARM_PERMIAN_OCEAN, LostWorldsBiomeKeys.WARM_PERMIAN_OCEAN } };
	private final ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][] { { LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_DESERT, LostWorldsBiomeKeys.PERMIAN_GINKGO_FOREST }, { LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST, LostWorldsBiomeKeys.PERMIAN_GINKGO_FOREST, LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST }, { LostWorldsBiomeKeys.PERMIAN_GINKGO_FOREST, LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST, LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST, LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST }, { LostWorldsBiomeKeys.PERMIAN_DRIED_PLAINS, LostWorldsBiomeKeys.PERMIAN_DRIED_PLAINS, LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST, LostWorldsBiomeKeys.PERMIAN_DESERT, LostWorldsBiomeKeys.PERMIAN_DESERT }, { LostWorldsBiomeKeys.PERMIAN_DESERT, LostWorldsBiomeKeys.PERMIAN_DESERT, LostWorldsBiomeKeys.PERMIAN_DESERT, LostWorldsBiomeKeys.PERMIAN_DESERT, LostWorldsBiomeKeys.PERMIAN_DESERT } };
	private final ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][] { { LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_DESERT, LostWorldsBiomeKeys.PERMIAN_DESERT }, { LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST, LostWorldsBiomeKeys.PERMIAN_GINKGO_FOREST, LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST }, { LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_PLAINS, LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST }, { LostWorldsBiomeKeys.PERMIAN_DRIED_PLAINS, LostWorldsBiomeKeys.PERMIAN_DRIED_PLAINS, LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST, LostWorldsBiomeKeys.PERMIAN_CONIFER_FOREST, LostWorldsBiomeKeys.PERMIAN_DESERT }, { LostWorldsBiomeKeys.PERMIAN_FLOOD_BASALTS, LostWorldsBiomeKeys.PERMIAN_FLOOD_BASALTS, LostWorldsBiomeKeys.PERMIAN_FLOOD_BASALTS, LostWorldsBiomeKeys.PERMIAN_FLOOD_BASALTS, LostWorldsBiomeKeys.PERMIAN_FLOOD_BASALTS } };
	private final ResourceKey<Biome>[][] SHATTERED_BIOMES = new ResourceKey[][] { { LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS }, { LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS }, { LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS, LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS }, { null, null, null, null, null }, { null, null, null, null, null } };

	public List<Climate.ParameterPoint> spawnTarget() {
		Climate.Parameter climate$parameter = Climate.Parameter.point(0.0F);
		return List.of(new Climate.ParameterPoint(this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.FULL_RANGE), this.FULL_RANGE, climate$parameter, Climate.Parameter.span(-1.0F, -0.16F), 0L), new Climate.ParameterPoint(this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.FULL_RANGE), this.FULL_RANGE, climate$parameter, Climate.Parameter.span(0.16F, 1.0F), 0L));
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
		this.addSurfaceBiome(point, this.FULL_RANGE, this.FULL_RANGE, this.mushroomFieldsContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, LostWorldsBiomeKeys.PERMIAN_PLAINS);

		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];
			this.addSurfaceBiome(point, climate$parameter, this.FULL_RANGE, this.deepOceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[0][i]);
			this.addSurfaceBiome(point, climate$parameter, this.FULL_RANGE, this.oceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[1][i]);
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
			Climate.Parameter climate$parameter = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter climate$parameter1 = this.humidities[j];
				ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrFloodBasaltsIfHot(i, j, parameter);
				ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrFloodBasaltsIfHotOrSlopeIfCold(i, j, parameter);
				ResourceKey<Biome> resourcekey3 = this.pickPlateauBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey4 = this.pickShatteredBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey5 = this.maybePickWindsweptHillsBiome(i, j, parameter, resourcekey4);
				ResourceKey<Biome> resourcekey6 = this.pickPeakBiome(i, j, parameter);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], parameter, 0.0F, resourcekey6);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], parameter, 0.0F, resourcekey2);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], parameter, 0.0F, resourcekey6);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, resourcekey);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], parameter, 0.0F, resourcekey3);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.midInlandContinentalness, this.erosions[3], parameter, 0.0F, resourcekey1);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[3], parameter, 0.0F, resourcekey3);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, resourcekey);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], parameter, 0.0F, resourcekey5);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, resourcekey4);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, resourcekey);
			}
		}
	}

	private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter parameter) {
		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter climate$parameter1 = this.humidities[j];
				ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrFloodBasaltsIfHot(i, j, parameter);
				ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrFloodBasaltsIfHotOrSlopeIfCold(i, j, parameter);
				ResourceKey<Biome> resourcekey3 = this.pickPlateauBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey4 = this.pickShatteredBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey5 = this.maybePickWindsweptHillsBiome(i, j, parameter, resourcekey);
				ResourceKey<Biome> resourcekey6 = this.pickSlopeBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey7 = this.pickPeakBiome(i, j, parameter);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, resourcekey);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[0], parameter, 0.0F, resourcekey6);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], parameter, 0.0F, resourcekey7);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[1], parameter, 0.0F, resourcekey2);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], parameter, 0.0F, resourcekey6);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, resourcekey);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], parameter, 0.0F, resourcekey3);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.midInlandContinentalness, this.erosions[3], parameter, 0.0F, resourcekey1);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[3], parameter, 0.0F, resourcekey3);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, resourcekey);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], parameter, 0.0F, resourcekey5);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, resourcekey4);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, resourcekey);
			}
		}
	}

	private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter parameter) {
		this.addSurfaceBiome(point, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_SHORE);
		this.addSurfaceBiome(point, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_MARSH);

		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter climate$parameter1 = this.humidities[j];
				ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrFloodBasaltsIfHot(i, j, parameter);
				ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrFloodBasaltsIfHotOrSlopeIfCold(i, j, parameter);
				ResourceKey<Biome> resourcekey3 = this.pickShatteredBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey4 = this.pickPlateauBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey5 = this.getBeachBiome();
				ResourceKey<Biome> resourcekey6 = this.maybePickWindsweptHillsBiome(i, j, parameter, resourcekey);
				ResourceKey<Biome> resourcekey7 = this.pickShatteredCoastBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey8 = this.pickSlopeBiome(i, j, parameter);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], parameter, 0.0F, resourcekey8);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], parameter, 0.0F, resourcekey2);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[1], parameter, 0.0F, i == 0 ? resourcekey8 : resourcekey4);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[2], parameter, 0.0F, resourcekey);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.midInlandContinentalness, this.erosions[2], parameter, 0.0F, resourcekey1);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[2], parameter, 0.0F, resourcekey4);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], parameter, 0.0F, resourcekey);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], parameter, 0.0F, resourcekey1);
				if (parameter.max() < 0L) {
					this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[4], parameter, 0.0F, resourcekey5);
					this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, resourcekey);
				} else {
					this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, resourcekey);
				}

				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[5], parameter, 0.0F, resourcekey7);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[5], parameter, 0.0F, resourcekey6);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, resourcekey3);
				if (parameter.max() < 0L) {
					this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[6], parameter, 0.0F, resourcekey5);
				} else {
					this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[6], parameter, 0.0F, resourcekey);
				}

				if (i == 0) {
					this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, resourcekey);
				}
			}
		}
	}

	private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter parameter) {
		this.addSurfaceBiome(point, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_SHORE);
		this.addSurfaceBiome(point, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_MARSH);

		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter climate$parameter1 = this.humidities[j];
				ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, parameter);
				ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrFloodBasaltsIfHot(i, j, parameter);
				ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrFloodBasaltsIfHotOrSlopeIfCold(i, j, parameter);
				ResourceKey<Biome> resourcekey3 = this.getBeachBiome();
				ResourceKey<Biome> resourcekey4 = this.maybePickWindsweptHillsBiome(i, j, parameter, resourcekey);
				ResourceKey<Biome> resourcekey5 = this.pickShatteredCoastBiome(i, j, parameter);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, resourcekey1);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, resourcekey2);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, resourcekey);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, resourcekey1);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.coastContinentalness, Climate.Parameter.span(this.erosions[3], this.erosions[4]), parameter, 0.0F, resourcekey3);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, resourcekey);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[5], parameter, 0.0F, resourcekey5);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[5], parameter, 0.0F, resourcekey4);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, resourcekey);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[6], parameter, 0.0F, resourcekey3);
				if (i == 0) {
					this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, resourcekey);
				}
			}
		}
	}

	private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter parameter) {
		this.addSurfaceBiome(point, this.FROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, parameter.max() < 0L ? LostWorldsBiomeKeys.PERMIAN_SHORE : LostWorldsBiomeKeys.PERMIAN_RIVER);
		this.addSurfaceBiome(point, this.UNFROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, parameter.max() < 0L ? LostWorldsBiomeKeys.PERMIAN_SHORE : LostWorldsBiomeKeys.PERMIAN_RIVER);
		this.addSurfaceBiome(point, this.FROZEN_RANGE, this.FULL_RANGE, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_RIVER);
		this.addSurfaceBiome(point, this.UNFROZEN_RANGE, this.FULL_RANGE, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_RIVER);
		this.addSurfaceBiome(point, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_RIVER);
		this.addSurfaceBiome(point, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_RIVER);
		this.addSurfaceBiome(point, this.FROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, this.erosions[6], parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_RIVER);
		this.addSurfaceBiome(point, this.UNFROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, this.erosions[6], parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_RIVER);
		this.addSurfaceBiome(point, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_MARSH);
		this.addSurfaceBiome(point, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, LostWorldsBiomeKeys.PERMIAN_RIVER);

		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter climate$parameter1 = this.humidities[j];
				ResourceKey<Biome> resourcekey = this.pickMiddleBiomeOrFloodBasaltsIfHot(i, j, parameter);
				this.addSurfaceBiome(point, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, resourcekey);
			}
		}
	}

	private void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point) {
		this.addUndergroundBiome(point, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(0.8F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, LostWorldsBiomeKeys.PERMIAN_DRIPSTONE_CAVES);
	}

	private ResourceKey<Biome> pickMiddleBiome(int p_187164_, int p_187165_, Climate.Parameter parameter) {
		return this.MIDDLE_BIOMES[p_187164_][p_187165_];
	}

	private ResourceKey<Biome> pickMiddleBiomeOrFloodBasaltsIfHot(int p_187192_, int p_187193_, Climate.Parameter parameter) {
		return p_187192_ == 4 ? this.pickFloodBasaltsBiome(p_187193_, parameter) : this.pickMiddleBiome(p_187192_, p_187193_, parameter);
	}

	private ResourceKey<Biome> pickMiddleBiomeOrFloodBasaltsIfHotOrSlopeIfCold(int p_187212_, int p_187213_, Climate.Parameter parameter) {
		return p_187212_ == 0 ? this.pickSlopeBiome(p_187212_, p_187213_, parameter) : this.pickMiddleBiomeOrFloodBasaltsIfHot(p_187212_, p_187213_, parameter);
	}

	private ResourceKey<Biome> maybePickWindsweptHillsBiome(int p_201991_, int p_201992_, Climate.Parameter parameter, ResourceKey<Biome> biome) {
		return p_201991_ > 1 && p_201992_ < 4 && parameter.max() >= 0L ? LostWorldsBiomeKeys.PERMIAN_WINDSWEPT_HILLS : biome;
	}

	private ResourceKey<Biome> pickShatteredCoastBiome(int p_187223_, int p_187224_, Climate.Parameter parameter) {
		ResourceKey<Biome> resourcekey = parameter.max() >= 0L ? this.pickMiddleBiome(p_187223_, p_187224_, parameter) : this.getBeachBiome();
		return this.maybePickWindsweptHillsBiome(p_187223_, p_187224_, parameter, resourcekey);
	}

	private ResourceKey<Biome> getBeachBiome() {
		return LostWorldsBiomeKeys.PERMIAN_SHORE;
	}

	private ResourceKey<Biome> pickFloodBasaltsBiome(int p_187173_, Climate.Parameter parameter) {
		if (p_187173_ < 2) {
			return parameter.max() < 0L ? LostWorldsBiomeKeys.PERMIAN_ASHY_MEDOWS : LostWorldsBiomeKeys.PERMIAN_FLOOD_BASALTS;
		} else {
			return p_187173_ < 3 ? LostWorldsBiomeKeys.PERMIAN_FLOOD_BASALTS : LostWorldsBiomeKeys.PERMIAN_ASHY_MEDOWS;
		}
	}

	private ResourceKey<Biome> pickPlateauBiome(int p_187234_, int p_187235_, Climate.Parameter parameter) {
		return this.PLATEAU_BIOMES[p_187234_][p_187235_];
	}

	private ResourceKey<Biome> pickPeakBiome(int p_187241_, int p_187242_, Climate.Parameter parameter) {
		if (p_187241_ <= 2) {
			return parameter.max() < 0L ? LostWorldsBiomeKeys.PERMIAN_JAGGED_PEAKS : LostWorldsBiomeKeys.PERMIAN_FROZEN_PEAKS;
		} else {
			return p_187241_ == 3 ? LostWorldsBiomeKeys.PERMIAN_STONY_PEAKS : this.pickFloodBasaltsBiome(p_187242_, parameter);
		}
	}

	private ResourceKey<Biome> pickSlopeBiome(int p_187245_, int p_187246_, Climate.Parameter parameter) {
		if (p_187245_ >= 3) {
			return this.pickPlateauBiome(p_187245_, p_187246_, parameter);
		} else {
			return p_187246_ <= 1 ? LostWorldsBiomeKeys.PERMIAN_SNOWY_SLOPES : LostWorldsBiomeKeys.PERMIAN_GROVE;
		}
	}

	private ResourceKey<Biome> pickShatteredBiome(int p_202002_, int p_202003_, Climate.Parameter parameter) {
		ResourceKey<Biome> resourcekey = this.SHATTERED_BIOMES[p_202002_][p_202003_];
		return resourcekey == null ? this.pickMiddleBiome(p_202002_, p_202003_, parameter) : resourcekey;
	}

	private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome) {
		point.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0.0F), weirdness, offset), biome));
		point.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.0F), weirdness, offset), biome));
	}

	private void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome) {
		point.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.2F, 0.9F), weirdness, offset), biome));
	}

	public static String getDebugStringForPeaksAndValleys(double noise) {
		if (noise < (double) TerrainShaper.peaksAndValleys(0.05F)) {
			return "Valley";
		} else if (noise < (double) TerrainShaper.peaksAndValleys(0.26666668F)) {
			return "Low";
		} else if (noise < (double) TerrainShaper.peaksAndValleys(0.4F)) {
			return "Mid";
		} else {
			return noise < (double) TerrainShaper.peaksAndValleys(0.56666666F) ? "High" : "Peak";
		}
	}

	public String getDebugStringForContinentalness(double noise) {
		double d0 = (double) Climate.quantizeCoord((float) noise);
		if (d0 < (double) this.mushroomFieldsContinentalness.max()) {
			return "Mushroom fields";
		} else if (d0 < (double) this.deepOceanContinentalness.max()) {
			return "Deep ocean";
		} else if (d0 < (double) this.oceanContinentalness.max()) {
			return "Ocean";
		} else if (d0 < (double) this.coastContinentalness.max()) {
			return "Coast";
		} else if (d0 < (double) this.nearInlandContinentalness.max()) {
			return "Near inland";
		} else {
			return d0 < (double) this.midInlandContinentalness.max() ? "Mid inland" : "Far inland";
		}
	}

	public String getDebugStringForErosion(double noise) {
		return getDebugStringForNoiseValue(noise, this.erosions);
	}

	public String getDebugStringForTemperature(double noise) {
		return getDebugStringForNoiseValue(noise, this.temperatures);
	}

	public String getDebugStringForHumidity(double noise) {
		return getDebugStringForNoiseValue(noise, this.humidities);
	}

	private static String getDebugStringForNoiseValue(double noise, Climate.Parameter[] parameters) {
		double d0 = (double) Climate.quantizeCoord((float) noise);

		for (int i = 0; i < parameters.length; ++i) {
			if (d0 < (double) parameters[i].max()) {
				return "" + i;
			}
		}

		return "?";
	}
}
