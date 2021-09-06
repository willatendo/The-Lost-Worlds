package lostworlds.library.dimension.permian.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;

public class PermianLookupLayer extends Layer 
{
	private final LazyArea area;

	public PermianLookupLayer(IAreaFactory<LazyArea> areaFactory) 
	{
		super(() -> null);
		this.area = areaFactory.make();
	}

	@Override
	public Biome get(Registry<Biome> biomes, int x, int z) 
	{
		int id = this.area.get(x, z);

		Biome biome = biomes.byId(id);
		if(biome == null) 
		{
			throw new IllegalStateException("Unknown biome id emitted by layers: " + id);
		}

		return biome;
	}
}
