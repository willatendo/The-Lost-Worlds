package lostworlds.server.world.terrablender;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import lostworlds.server.biome.LostWorldsBiomeKeys;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.Climate.ParameterPoint;
import terrablender.api.Region;
import terrablender.api.RegionType;

public class LostWorldsRegion extends Region {
	public LostWorldsRegion(ResourceLocation name, RegionType type, int weight) {
		super(name, type, weight);
	}

	@Override
	public void addBiomes(Registry<Biome> registry, Consumer<Pair<ParameterPoint, ResourceKey<Biome>>> mapper) {
		this.addBiome(mapper, Climate.parameters(Climate.Parameter.span(-0.45F, 0.15F), Climate.Parameter.span(-0.1F, 0.1F), Climate.Parameter.span(-0.11F, 0.2F), Climate.Parameter.span(-0.5799F, -0.232F), Climate.Parameter.span(-1.0F, -0.922F), Climate.Parameter.point(0.0F), 0.0F), LostWorldsBiomeKeys.ARAUCARIA_FOREST);
	}
}
