package lostworlds.server.dimension.biomes;

import java.util.List;
import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import lostworlds.server.biome.LostWorldsBiomes;
import net.minecraft.SharedConstants;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public final class PermianBiomeBuilder {
	private final Climate.Parameter fullRange = Climate.Parameter.span(-1.0F, 1.0F);
	private final Climate.Parameter[] temperatures = new Climate.Parameter[] { Climate.Parameter.span(-1.0F, -0.45F), Climate.Parameter.span(-0.45F, -0.15F), Climate.Parameter.span(-0.15F, 0.2F), Climate.Parameter.span(0.2F, 0.55F), Climate.Parameter.span(0.55F, 1.0F) };
	private final Climate.Parameter[] humidities = new Climate.Parameter[] { Climate.Parameter.span(-1.0F, -0.35F), Climate.Parameter.span(-0.35F, -0.1F), Climate.Parameter.span(-0.1F, 0.1F), Climate.Parameter.span(0.1F, 0.3F), Climate.Parameter.span(0.3F, 1.0F) };
	private final Climate.Parameter[] erosions = new Climate.Parameter[] { Climate.Parameter.span(-1.0F, -0.78F), Climate.Parameter.span(-0.78F, -0.375F), Climate.Parameter.span(-0.375F, -0.2225F), Climate.Parameter.span(-0.2225F, 0.05F), Climate.Parameter.span(0.05F, 0.45F), Climate.Parameter.span(0.45F, 0.55F), Climate.Parameter.span(0.55F, 1.0F) };
	private final Climate.Parameter deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
	private final Climate.Parameter oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
	private final Climate.Parameter coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
	private final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
	private final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
	private final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
	private final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);

	private final ResourceKey<Biome>[][] middleBiomes = new ResourceKey[][] { { LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), LostWorldsBiomes.PERMIAN_GINKGO_FOREST.getResourceKey(), LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey() }, { LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), LostWorldsBiomes.PERMIAN_GINKGO_FOREST.getResourceKey(), LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey() }, { LostWorldsBiomes.PERMIAN_GINKGO_FOREST.getResourceKey(), LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_GINKGO_FOREST.getResourceKey() }, { LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_DRIED_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_DRIED_PLAINS.getResourceKey() }, { LostWorldsBiomes.PERMIAN_DESERT.getResourceKey(), LostWorldsBiomes.PERMIAN_DESERT.getResourceKey(), LostWorldsBiomes.PERMIAN_DRIED_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_DRIED_PLAINS.getResourceKey(), LostWorldsBiomes.PERMIAN_ASHY_MEDOWS.getResourceKey() } };
	private final ResourceKey<Biome>[][] middleBiomeVariants = new ResourceKey[][] { { null, null, LostWorldsBiomes.PERMIAN_GINKGO_FOREST.getResourceKey(), LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), null }, { null, null, LostWorldsBiomes.PERMIAN_GINKGO_FOREST.getResourceKey(), LostWorldsBiomes.PERMIAN_CONIFER_FOREST.getResourceKey(), null }, { null, null, null, null, null }, { LostWorldsBiomes.PERMIAN_DRIED_PLAINS.getResourceKey(), null, null, LostWorldsBiomes.PERMIAN_DESERT.getResourceKey(), null }, { null, null, null, null, LostWorldsBiomes.PERMIAN_FLOOD_BASALTS.getResourceKey() } };

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
		this.addSurfaceBiome(point, Climate.Parameter.span(-1.0F, 0.2F), this.fullRange, this.deepOceanContinentalness, this.fullRange, this.fullRange, 0.0F, LostWorldsBiomes.DEEP_PERMIAN_OCEAN.getResourceKey());
		this.addSurfaceBiome(point, Climate.Parameter.span(0.2F, 1.0F), this.fullRange, this.deepOceanContinentalness, this.fullRange, this.fullRange, 0.0F, LostWorldsBiomes.DEEP_WARM_PERMIAN_OCEAN.getResourceKey());
		this.addSurfaceBiome(point, Climate.Parameter.span(-1.0F, 0.2F), this.fullRange, this.oceanContinentalness, this.fullRange, this.fullRange, 0.0F, LostWorldsBiomes.PERMIAN_OCEAN.getResourceKey());
		this.addSurfaceBiome(point, Climate.Parameter.span(0.2F, 1.0F), this.fullRange, this.oceanContinentalness, this.fullRange, this.fullRange, 0.0F, LostWorldsBiomes.WARM_PERMIAN_OCEAN.getResourceKey());

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
				ResourceKey<Biome> peakBiome = this.pickPeakBiome(i, j, parameter);
				ResourceKey<Biome> slopeBiome = this.pickSlopeBiome(i, j, parameter);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], parameter, 0.0F, peakBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], parameter, 0.0F, peakBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.midInlandContinentalness, this.erosions[3], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.farInlandContinentalness, this.erosions[3], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, middleBiome);
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
				ResourceKey<Biome> slopeBiome = this.pickSlopeBiome(i, j, parameter);
				ResourceKey<Biome> peakBiome = this.pickPeakBiome(i, j, parameter);
				this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, this.erosions[0], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], parameter, 0.0F, peakBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, this.erosions[1], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.midInlandContinentalness, this.erosions[3], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.farInlandContinentalness, this.erosions[3], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, middleBiome);
			}
		}

	}

	private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter parameter) {
		this.addSurfaceBiome(point, this.fullRange, this.fullRange, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, LostWorldsBiomes.PERMIAN_MARSH.getResourceKey());

		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter temperatures = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter humidities = this.humidities[j];
				ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, parameter);
				ResourceKey<Biome> beachBiome = LostWorldsBiomes.PERMIAN_SHORE.getResourceKey();
				ResourceKey<Biome> slopeBiome = this.pickSlopeBiome(i, j, parameter);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.farInlandContinentalness, this.erosions[1], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, this.erosions[2], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.midInlandContinentalness, this.erosions[2], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.farInlandContinentalness, this.erosions[2], parameter, 0.0F, slopeBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], parameter, 0.0F, middleBiome);
				if (parameter.max() < 0L) {
					this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, this.erosions[4], parameter, 0.0F, beachBiome);
					this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, middleBiome);
				} else {
					this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, middleBiome);
				}

				this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, this.erosions[5], parameter, 0.0F, beachBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, this.erosions[5], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, middleBiome);
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
		this.addSurfaceBiome(point, this.fullRange, this.fullRange, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, LostWorldsBiomes.PERMIAN_MARSH.getResourceKey());

		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter temperatures = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter humidities = this.humidities[j];
				ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, parameter);
				ResourceKey<Biome> beachBiome = LostWorldsBiomes.PERMIAN_SHORE.getResourceKey();
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, Climate.Parameter.span(this.erosions[3], this.erosions[4]), parameter, 0.0F, beachBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, this.erosions[5], parameter, 0.0F, beachBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.nearInlandContinentalness, this.erosions[5], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], parameter, 0.0F, middleBiome);
				this.addSurfaceBiome(point, temperatures, humidities, this.coastContinentalness, this.erosions[6], parameter, 0.0F, beachBiome);
				if (i == 0) {
					this.addSurfaceBiome(point, temperatures, humidities, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, middleBiome);
				}
			}
		}

	}

	private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter parameter) {
		this.addSurfaceBiome(point, this.fullRange, this.fullRange, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, parameter.max() < 0L ? LostWorldsBiomes.PERMIAN_SHORE.getResourceKey() : LostWorldsBiomes.PERMIAN_RIVER.getResourceKey());
		this.addSurfaceBiome(point, this.fullRange, this.fullRange, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), parameter, 0.0F, LostWorldsBiomes.PERMIAN_RIVER.getResourceKey());
		this.addSurfaceBiome(point, this.fullRange, this.fullRange, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), parameter, 0.0F, LostWorldsBiomes.PERMIAN_RIVER.getResourceKey());
		this.addSurfaceBiome(point, this.fullRange, this.fullRange, this.coastContinentalness, this.erosions[6], parameter, 0.0F, LostWorldsBiomes.PERMIAN_RIVER.getResourceKey());
		this.addSurfaceBiome(point, this.fullRange, this.fullRange, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, LostWorldsBiomes.PERMIAN_MARSH.getResourceKey());
		this.addSurfaceBiome(point, this.fullRange, this.fullRange, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], parameter, 0.0F, LostWorldsBiomes.PERMIAN_RIVER.getResourceKey());
	}

	private void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point) {
		this.addUndergroundBiome(point, this.fullRange, this.fullRange, Climate.Parameter.span(0.8F, 1.0F), this.fullRange, this.fullRange, 0.0F, LostWorldsBiomes.PERMIAN_DRIPSTONE_CAVES.getResourceKey());
	}

	private ResourceKey<Biome> pickMiddleBiome(int temperature, int biome, Climate.Parameter parameter) {
		if (parameter.max() < 0L) {
			return this.middleBiomes[temperature][biome];
		} else {
			ResourceKey<Biome> variant = this.middleBiomeVariants[temperature][biome];
			return variant == null ? this.middleBiomes[temperature][biome] : variant;
		}
	}

	private ResourceKey<Biome> pickPeakBiome(int temperature, int biome, Climate.Parameter parameter) {
		if (temperature <= 2) {
			return parameter.max() < 0L ? LostWorldsBiomes.PERMIAN_JAGGED_PEAKS.getResourceKey() : LostWorldsBiomes.PERMIAN_FROZEN_PEAKS.getResourceKey();
		} else {
			return LostWorldsBiomes.PERMIAN_STONY_PEAKS.getResourceKey();
		}
	}

	private ResourceKey<Biome> pickSlopeBiome(int temperature, int biome, Climate.Parameter parameter) {
		if (temperature >= 3) {
			return LostWorldsBiomes.PERMIAN_PLAINS.getResourceKey();
		} else {
			return biome <= 1 ? LostWorldsBiomes.PERMIAN_SNOWY_SLOPES.getResourceKey() : LostWorldsBiomes.PERMIAN_GROVE.getResourceKey();
		}
	}

	private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome) {
		point.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0.0F), weirdness, offset), biome));
		point.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.0F), weirdness, offset), biome));
	}

	private void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> point, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter v, float offset, ResourceKey<Biome> biome) {
		point.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.2F, 0.9F), v, offset), biome));
	}
}
