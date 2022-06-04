package lostworlds.server.species;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SpeciesType extends ForgeRegistryEntry<SpeciesType> {
	private final ResourceLocation model;
	private final ResourceLocation texture;

	public SpeciesType(ResourceLocation model, ResourceLocation texture) {
		this.model = model;
		this.texture = texture;
	}

	public ResourceLocation getModel() {
		return model;
	}

	public ResourceLocation getTexture() {
		return texture;
	}
}
