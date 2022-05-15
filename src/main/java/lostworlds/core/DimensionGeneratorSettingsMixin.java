package lostworlds.core;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import lostworlds.server.dimension.WorldSeedHolder;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;

@Mixin(DimensionGeneratorSettings.class)
public class DimensionGeneratorSettingsMixin {
	@Inject(method = "<init>(JZZLnet/minecraft/util/registry/SimpleRegistry;Ljava/util/Optional;)V", at = @At(value = "RETURN"))
	private void seedGetter(long seed, boolean generateFeatures, boolean bonusChest, SimpleRegistry<Dimension> registry, Optional<String> string, CallbackInfo ci) {
		WorldSeedHolder.setSeed(seed);
	}
}
