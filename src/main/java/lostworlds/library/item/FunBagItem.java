package lostworlds.library.item;

import java.util.ArrayList;
import java.util.Random;

import com.google.common.collect.Lists;

import lostworlds.content.server.init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class FunBagItem extends ModItem
{
	public static final ArrayList<ItemStack> LOOT = Lists.newArrayList();
	
	public FunBagItem() 
	{
		LOOT.add(Items.CAKE.getDefaultInstance());
		LOOT.add(Items.COOKIE.getDefaultInstance());
		LOOT.add(Items.APPLE.getDefaultInstance());
		LOOT.add(ItemInit.BOOK_OF_ARCHAEOLOGY.getDefaultInstance());
		LOOT.add(ItemInit.LOST_WORLDS_LEXICON.getDefaultInstance());
		LOOT.add(ItemInit.FIELD_GUIDE.getDefaultInstance());
		LOOT.add(Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance());
		LOOT.add(Items.GOLD_INGOT.getDefaultInstance());
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) 
	{
		ItemStack stack = entity.getItemInHand(hand);
		Random rand = new Random();
		if(!entity.isCreative())
		{
			stack.shrink(1);
		}
		ItemStack loot = randomLoot(rand);
		if(loot.getMaxStackSize() != 1)
		{
			entity.inventory.add(1, loot);
		}
		else
		{
			entity.inventory.add(loot);		
		}
		entity.playSound(SoundEvents.WOOL_BREAK, 1.0F, 1.0F);
		return ActionResult.success(stack);
	}
	
	private static ItemStack randomLoot(Random rand)
	{
		int chance = rand.nextInt(LOOT.size());
		return LOOT.get(chance);
	}
}
