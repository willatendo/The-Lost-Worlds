package lostworlds.server.species;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import lostworlds.server.LostWorldsRegistries;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SpeciesType extends ForgeRegistryEntry.UncheckedRegistryEntry<SpeciesType> {
	public static final Codec<SpeciesType> DIRECT_CODEC = RecordCodecBuilder.create((instance) -> {
		return instance.group(ResourceLocation.CODEC.fieldOf("model").forGetter((speciesType) -> {
			return speciesType.getModel();
		}), ResourceLocation.CODEC.fieldOf("texture").forGetter((speciesType) -> {
			return speciesType.getTexture();
		}), ResourceLocation.CODEC.fieldOf("animations").forGetter((speciesType) -> {
			return speciesType.getAnimations();
		})).apply(instance, SpeciesType::new);
	});
	public static final Codec<Holder<SpeciesType>> CODEC = RegistryFileCodec.create(LostWorldsRegistries.SPECIES_TYPES, DIRECT_CODEC);

	private final ResourceLocation model;
	private final ResourceLocation texture;
	private final ResourceLocation animations;

	public SpeciesType(ResourceLocation model, ResourceLocation texture, ResourceLocation animations) {
		this.model = model;
		this.texture = texture;
		this.animations = animations;
	}

	public ResourceLocation getModel() {
		return this.model;
	}

	public ResourceLocation getTexture() {
		return this.texture;
	}

	public ResourceLocation getAnimations() {
		return this.animations;
	}
}
