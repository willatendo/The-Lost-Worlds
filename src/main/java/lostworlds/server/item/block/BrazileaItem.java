package lostworlds.server.item.block;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;

public class BrazileaItem extends BlockItem {
	public BrazileaItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		return InteractionResult.PASS;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		BlockHitResult blockraytraceresult = getPlayerPOVHitResult(world, entity, ClipContext.Fluid.SOURCE_ONLY);
		BlockHitResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos().above());
		InteractionResult actionresulttype = super.useOn(new UseOnContext(entity, hand, blockraytraceresult1));
		return new InteractionResultHolder<>(actionresulttype, entity.getItemInHand(hand));
	}
}
