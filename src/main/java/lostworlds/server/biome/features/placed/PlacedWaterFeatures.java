package lostworlds.server.biome.features.placed;

import static lostworlds.server.biome.features.placed.LostWorldsPlacedFeatures.register;

import lostworlds.server.biome.features.configured.WaterFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedWaterFeatures {
	public static final Holder<PlacedFeature> ANCIENT_SPRING = register("ancient_spring", WaterFeatures.ANCIENT_SPRING, CountPlacement.of(25), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(192)), BiomeFilter.biome());

	public static void init() {
	}
}
