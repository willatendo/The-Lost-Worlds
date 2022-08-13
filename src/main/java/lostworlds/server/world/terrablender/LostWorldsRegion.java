package lostworlds.server.world.terrablender;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import lostworlds.server.biome.LostWorldsBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate.ParameterPoint;
import terrablender.api.Region;
import terrablender.api.RegionType;

public class LostWorldsRegion extends Region {
	public LostWorldsRegion(ResourceLocation name, RegionType type, int weight) {
		super(name, type, weight);
	}

	@Override
	public void addBiomes(Registry<Biome> registry, Consumer<Pair<ParameterPoint, ResourceKey<Biome>>> mapper) {
		this.addBiomeSimilar(mapper, Biomes.SAVANNA, LostWorldsBiomes.ARAUCARIA_FOREST.getResourceKey());
		this.addBiomeSimilar(mapper, Biomes.FLOWER_FOREST, LostWorldsBiomes.CONIFER_FOREST.getResourceKey());
		this.addBiomeSimilar(mapper, Biomes.FLOWER_FOREST, LostWorldsBiomes.GINKGO_FOREST.getResourceKey());
		this.addBiomeSimilar(mapper, Biomes.OLD_GROWTH_PINE_TAIGA, LostWorldsBiomes.REDWOODS_FOREST.getResourceKey());
		this.addBiomeSimilar(mapper, Biomes.STONY_PEAKS, LostWorldsBiomes.VOLCANO.getResourceKey());
	}
}
