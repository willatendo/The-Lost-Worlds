package lostworlds.server.species;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import lostworlds.server.LostWorldsRegistries;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SpeciesType extends ForgeRegistryEntry<SpeciesType> {
	public static final Codec<SpeciesType> DIRECT_CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(ResourceLocation.CODEC.fieldOf("model").forGetter((speciesType) -> {
			return speciesType.getModel();
		}), ResourceLocation.CODEC.fieldOf("texture").forGetter((speciesType) -> {
			return speciesType.getTexture();
		})).apply(instance, SpeciesType::new);
	});
	public static final Codec<Holder<SpeciesType>> CODEC = RegistryFileCodec.create(LostWorldsRegistries.SPECIES_TYPES, DIRECT_CODEC);

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
