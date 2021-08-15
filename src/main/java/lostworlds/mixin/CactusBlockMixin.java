package lostworlds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

@Mixin(CactusBlock.class)
public class CactusBlockMixin 
{
	@Inject(method = "canSurvive", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IWorldReader;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", ordinal = 1), cancellable = true)
	private void addCustomSands(BlockState state, IWorldReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir)
	{
		BlockState blockstate = world.getBlockState(pos.below());
		if(blockstate.getBlock().is(BlockInit.PERMIAN_SAND) && !world.getBlockState(pos.above()).getMaterial().isLiquid())
		{
			cir.setReturnValue(true);
		}
	}
}
