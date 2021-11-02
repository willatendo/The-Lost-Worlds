package lostworlds.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import tyrannotitanlib.library.base.dimension.WorldSeedHolder;

@Mixin(DimensionGeneratorSettings.class)
public class DimensionGeneratorSettingsMixin 
{
	@Inject(method = "<init>(JZZLnet/minecraft/util/registry/SimpleRegistry;Ljava/util/Optional;)V", at = @At(value = "RETURN"))
	private void seedGetter(long seed, boolean generateFeatures, boolean bonusChest, SimpleRegistry<Dimension> registry, Optional<String> s, CallbackInfo ci) 
	{
		WorldSeedHolder.setSeed(seed);
	}
}
