package lostworlds.library.item;

import java.util.function.Predicate;

import lostworlds.content.server.ModTags;
import lostworlds.content.server.init.DimensionInit;
import lostworlds.library.enums.TimeEras;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModTeleporter;
import lostworlds.library.util.ModUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

/*
 * Author: Willatendo
 * Date: July 5, 2021
 */

public class TimeBookItem extends ShootableItem
{
	public static final Predicate<ItemStack> FUEL = (stack) -> 
	{
		return stack.getItem().is(ModTags.ModItemTags.TIME_BOOK_FUEL);
	};
	private static TimeEras era;
	
	protected TimeBookItem(TimeEras eras)
	{
		super(new Properties().tab(ModItemGroup.ITEMS).stacksTo(1).rarity(Rarity.RARE).fireResistant());
		era = eras;
	}
	
	public void releaseUsing(ItemStack stack, World world, LivingEntity entity, int time) 
	{
		if(entity instanceof PlayerEntity) 
		{
			PlayerEntity playerentity = (PlayerEntity)entity;
			boolean flag = playerentity.abilities.instabuild;
			ItemStack itemstack = playerentity.getProjectile(stack);
			
			int i = this.getUseDuration(stack) - time;
			if(i < 0) return;
			
			if(!itemstack.isEmpty() || flag) 
			{
				if(itemstack.isEmpty()) 
				{
					itemstack = new ItemStack(Items.REDSTONE);
				}
				
				float f = getPowerForTime(i);
				if(!((double)f < 0.1D)) 
				{
					boolean flag1 = playerentity.abilities.instabuild;
					if(!world.isClientSide) 
					{
						if(!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) 
						{
							if(entity.level instanceof ServerWorld) 
							{	
								if(entity.level.dimension() == World.NETHER || entity.level.dimension() == World.END)
								{
									entity.sendMessage(ModUtil.tTC("timeBook", "doesnt_work"), entity.getUUID());
								}
								
								if(era == TimeEras.PERMIAN_PERIOD)
								{
									ServerWorld serverworld = (ServerWorld)entity.level;
									MinecraftServer minecraftserver = serverworld.getServer();
									RegistryKey<World> registrykey = entity.level.dimension() == DimensionInit.PERMIAN_WORLD ? World.OVERWORLD : DimensionInit.PERMIAN_WORLD;
									ServerWorld serverworld1 = minecraftserver.getLevel(registrykey);
									if(serverworld1 != null && !entity.isPassenger()) 
									{
										entity.changeDimension(serverworld1, new ModTeleporter(serverworld1));
										if(registrykey.equals(DimensionInit.PERMIAN_WORLD))
										{
											entity.sendMessage(ModUtil.tTC("timeBook", "transport_to_permian"), entity.getUUID());
										}
										else
										{
											entity.sendMessage(ModUtil.tTC("timeBook", "transport_to_overworld"), entity.getUUID());
										}
									}
								}
							}
						}
					}
					
					world.playSound((PlayerEntity)null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), SoundEvents.PORTAL_TRAVEL, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					if(!flag1 && !playerentity.abilities.instabuild) 
					{
						itemstack.shrink(1);
						if(itemstack.isEmpty()) 
						{
							playerentity.inventory.removeItem(itemstack);
						}
					}
					
					playerentity.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) 
	{
		ItemStack itemstack = entity.getItemInHand(hand);
		boolean flag = !entity.getProjectile(itemstack).isEmpty();
		
		if(!entity.abilities.instabuild && !flag) 
		{
			return ActionResult.fail(itemstack);
		} 
		else 
		{
			entity.startUsingItem(hand);
			return ActionResult.consume(itemstack);
		}
	}
	
	public static float getPowerForTime(int time) 
	{
		float f = (float)time / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if(f > 1.0F) 
		{
			f = 1.0F;
		}
		
		return f;
	}
	
	@Override
	public int getUseDuration(ItemStack stack) 
	{
		return 72000;
	}
	
	public UseAction getUseAnimation(ItemStack p_77661_1_) 
	{
		return UseAction.BOW;
	}
	
	@Override
	public boolean isFoil(ItemStack stack) 
	{
		return true;
	}
	
	public static Item create(TimeEras era)
	{
		Item item = new TimeBookItem(era);
		ModRegistry.register(era.toString().toLowerCase() + "_time_book", item);
		return item;
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() 
	{
		return FUEL;
	}

	@Override
	public int getDefaultProjectileRange() 
	{
		return 15;
	}
}
