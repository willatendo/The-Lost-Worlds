package lostworlds.core;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import lostworlds.server.dimension.WorldSeedHolder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;

@Mixin(WorldGenSettings.class)
public class DimensionGeneratorSettingsMixin {
	@Inject(method = "<init>(JZZLnet/minecraft/util/registry/SimpleRegistry;Ljava/util/Optional;)V", at = @At(value = "RETURN"))
	private void seedGetter(long seed, boolean generateFeatures, boolean bonusChest, MappedRegistry<LevelStem> registry, Optional<String> string, CallbackInfo ci) {
		WorldSeedHolder.setSeed(seed);
	}
}
