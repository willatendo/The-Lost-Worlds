package lostworlds.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.serialization.Lifecycle;

import net.minecraft.world.storage.ServerWorldInfo;

@Mixin(ServerWorldInfo.class)
public class ServerWorldInfoMixin 
{
	@Inject(method = "worldGenSettingsLifecycle", at = @At("HEAD"), cancellable = true)
	private void forceStableLifeCycle(CallbackInfoReturnable<Lifecycle> cir)
	{
		cir.setReturnValue(Lifecycle.stable());
	}
}
