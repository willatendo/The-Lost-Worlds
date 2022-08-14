package lostworlds.mixin;

import java.util.OptionalInt;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.dimension.LostWorldsDimensions;
import lostworlds.server.dimension.LostWorldsNoiseGeneratorSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

@Mixin(targets = "net/minecraft/core/RegistryCodecs$1")
public class RegistryCodecsMixin<E> {
	@Inject(method = "decode", at = @At(value = "INVOKE", target = "Lnet/minecraft/resources/RegistryOps;registryLoader()Ljava/util/Optional;"), locals = LocalCapture.CAPTURE_FAILHARD)
	private <T> void decode(DynamicOps<T> ops, T input, CallbackInfoReturnable<DataResult<Pair<Registry<E>, T>>> ci, DataResult<Pair<WritableRegistry<E>, T>> decodedRegistry, RegistryOps<T> registryOps) {
		decodedRegistry.result().map(Pair::getFirst).ifPresent(registry -> {
			ResourceKey<?> registryKey = registry.key();
			if (registryKey == Registry.LEVEL_STEM_REGISTRY && registry.get(LostWorldsUtils.rL("permian")) == null) {
				this.addDimensions(LostWorldsDimensions.PERMIAN_DIMENSION_TYPE, LostWorldsDimensions.PERMIAN_DIMENSION, LostWorldsNoiseGeneratorSettings.PERMIAN_KEY, registryOps, (WritableRegistry<LevelStem>) registry);
			}
			if (registryKey == Registry.LEVEL_STEM_REGISTRY && registry.get(LostWorldsUtils.rL("jurassic")) == null) {
				this.addDimensions(LostWorldsDimensions.JURASSIC_DIMENSION_TYPE, LostWorldsDimensions.JURASSIC_DIMENSION, LostWorldsNoiseGeneratorSettings.JURASSIC_KEY, registryOps, (WritableRegistry<LevelStem>) registry);
			}
			if (registryKey == Registry.LEVEL_STEM_REGISTRY && registry.get(LostWorldsUtils.rL("cretaceous")) == null) {
				this.addDimensions(LostWorldsDimensions.CRETACEOUS_DIMENSION_TYPE, LostWorldsDimensions.CRETACEOUS_DIMENSION, LostWorldsNoiseGeneratorSettings.CRETACEOUS_KEY, registryOps, (WritableRegistry<LevelStem>) registry);
			}
		});
	}

	private void addDimensions(ResourceKey<DimensionType> dimensionType, ResourceKey<LevelStem> dimension, ResourceKey<NoiseGeneratorSettings> noiseGeneratorSettings, RegistryOps<?> ops, WritableRegistry<LevelStem> registry) {
		System.out.print("GenDim");
		LevelStem overworld = registry.get(LevelStem.OVERWORLD);
		if (overworld == null) {
			return;
		}

		LevelStem levelStem = LostWorldsDimensions.createDimension(dimensionType, noiseGeneratorSettings, registryOrThrow(ops, Registry.DIMENSION_TYPE_REGISTRY), registryOrThrow(ops, Registry.NOISE_REGISTRY), registryOrThrow(ops, Registry.STRUCTURE_SET_REGISTRY), registryOrThrow(ops, Registry.BIOME_REGISTRY), registryOrThrow(ops, Registry.NOISE_GENERATOR_SETTINGS_REGISTRY), 0);
		registry.registerOrOverride(OptionalInt.empty(), dimension, levelStem, Lifecycle.stable());
	}

	private static <T> Registry<T> registryOrThrow(RegistryOps<?> ops, ResourceKey<Registry<T>> key) {
		return ops.registry(key).orElseThrow(() -> new IllegalArgumentException("Missing registry for " + key));
	}
}
