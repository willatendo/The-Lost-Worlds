package lostworlds.library.item;

import lostworlds.content.ModUtils;
import lostworlds.content.client.screen.chisel.ChiselScreen;
import lostworlds.content.server.init.ItemInit;
import lostworlds.library.entity.fossil.FossilEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChiselItem extends Item
{
	public ChiselItem() 
	{
		super(new Properties().tab(ModUtils.ITEMS).stacksTo(1).defaultDurability(32));
	}
	
	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) 
	{
		Minecraft instance = Minecraft.getInstance();

		if(entity instanceof FossilEntity)
		{	
			FossilEntity fossil = (FossilEntity) entity;
			instance.setScreen(new ChiselScreen(fossil, ItemInit.CHISEL.getDefaultInstance(), player));
			
			return ActionResultType.SUCCESS;
		}
		
		return ActionResultType.FAIL;
	}
	
	@Override
	public boolean canAttackBlock(BlockState state, World world, BlockPos pos, PlayerEntity entity) 
	{
		if(entity.isCreative())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
