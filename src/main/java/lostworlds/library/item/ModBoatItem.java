package lostworlds.library.item;

import java.util.List;
import java.util.function.Predicate;

import lostworlds.library.entity.ModBoatEntity;
import lostworlds.library.entity.ModBoatType;
import lostworlds.library.item.builder.ItemBuilder;
import lostworlds.library.tab.ModItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ModBoatItem extends Item 
{
	private static final Predicate<Entity> RIDERS = EntityPredicates.NO_SPECTATORS.and(Entity::isPickable);
	private final ModBoatType type;

	public ModBoatItem(ModBoatType typeIn) 
	{
		super(new Properties().stacksTo(1).tab(ModItemGroup.BLOCKS));
		this.type = typeIn;
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		RayTraceResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, RayTraceContext.FluidMode.ANY);
		if(raytraceresult.getType() == RayTraceResult.Type.MISS) 
		{
			return ActionResult.pass(itemstack);
		} 
		else 
		{
			Vector3d Vector3d = playerIn.getViewVector(1.0F);
			List<Entity> list = worldIn.getEntities(playerIn, playerIn.getBoundingBox().expandTowards(Vector3d.scale(5.0D)).inflate(1.0D), RIDERS);
			if(!list.isEmpty()) 
			{
				Vector3d Vector3d1 = playerIn.getEyePosition(1.0F);

				for(Entity entity : list) 
				{
					AxisAlignedBB axisalignedbb = entity.getBoundingBox().inflate(entity.getPickRadius());
					if(axisalignedbb.contains(Vector3d1)) 
					{
						return ActionResult.pass(itemstack);
					}
				}
			}

			if(raytraceresult.getType() == RayTraceResult.Type.BLOCK) 
			{
				ModBoatEntity boat = new ModBoatEntity(worldIn, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
				boat.setBYGBoatType(this.type);
				boat.yRot = playerIn.yRot;
				if(!worldIn.noCollision(boat, boat.getBoundingBox().inflate(-0.1D))) 
				{
					return ActionResult.fail(itemstack);
				} 
				else 
				{
					if(!worldIn.isClientSide) 
					{
						worldIn.addFreshEntity(boat);
						if(!playerIn.abilities.instabuild) 
						{
							itemstack.shrink(1);
						}
					}

					playerIn.awardStat(Stats.ITEM_USED.get(this));
					return ActionResult.success(itemstack);
				}
			} 
			else 
			{
				return ActionResult.pass(itemstack);
			}
		}
	}
	
	public static Item create(ModBoatType type)
	{
		return ItemBuilder.create(type.toString().toLowerCase() + "_boat", new ModBoatItem(type));
	}
}
