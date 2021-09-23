package lostworlds.library.item.block;

import lostworlds.content.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;

public class AshLayerItem extends BlockItem
{
	public AshLayerItem(Block block) 
	{
		super(block, new Properties().tab(ModUtils.BLOCKS));
	}
	
	@Override
	public ActionResultType useOn(ItemUseContext context) 
	{
		return ActionResultType.PASS;
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) 
	{
		BlockRayTraceResult blockraytraceresult = getPlayerPOVHitResult(world, entity, RayTraceContext.FluidMode.SOURCE_ONLY);
		BlockRayTraceResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos().above());
		ActionResultType actionresulttype = super.useOn(new ItemUseContext(entity, hand, blockraytraceresult1));
		return new ActionResult<>(actionresulttype, entity.getItemInHand(hand));
	}
}
