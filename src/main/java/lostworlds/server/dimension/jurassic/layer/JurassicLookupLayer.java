package lostworlds.server.dimension.jurassic.layer;

import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.newbiome.area.AreaFactory;
import net.minecraft.world.level.newbiome.area.LazyArea;
import net.minecraft.world.level.newbiome.layer.Layer;

public class JurassicLookupLayer extends Layer {
	private final LazyArea area;

	public JurassicLookupLayer(AreaFactory<LazyArea> areaFactory) {
		super(() -> null);
		this.area = areaFactory.make();
	}

	@Override
	public Biome get(Registry<Biome> biomes, int x, int z) {
		int id = this.area.get(x, z);

		Biome biome = biomes.byId(id);
		if (biome == null) {
			throw new IllegalStateException("Unknown biome id emitted by layers: " + id);
		}

		return biome;
	}
}
