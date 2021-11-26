package lostworlds.library.entity.goal.aquatic.dolphin;

import java.util.List;
import java.util.Random;

import lostworlds.library.entity.aquatic.DolphinLikeEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;

public class DolphinLikePlayWithItemsGoal extends Goal 
{
	private int cooldown;
	private DolphinLikeEntity entity;
	private Random rand = new Random();

	public DolphinLikePlayWithItemsGoal(DolphinLikeEntity entity) 
	{
		this.entity = entity;
    }
	
	@Override
	public boolean canUse() 
	{
		if(this.cooldown > this.entity.tickCount) 
		{
			return false;
		} 
		else 
		{
			List<ItemEntity> list = this.entity.level.getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), DolphinEntity.ALLOWED_ITEMS);
			return !list.isEmpty() || !this.entity.getItemBySlot(EquipmentSlotType.MAINHAND).isEmpty();
		}
	}

	@Override
	public void start() 
	{
		List<ItemEntity> list = this.entity.level.getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), DolphinEntity.ALLOWED_ITEMS);
		if(!list.isEmpty()) 
		{
			this.entity.getNavigation().moveTo(list.get(0), (double) 1.2F);
			this.entity.playSound(SoundEvents.DOLPHIN_PLAY, 1.0F, 1.0F);
		}

		this.cooldown = 0;
	}

	@Override
	public void stop() 
	{
		ItemStack itemstack = this.entity.getItemBySlot(EquipmentSlotType.MAINHAND);
		if(!itemstack.isEmpty()) 
		{
			this.drop(itemstack);
			this.entity.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
			this.cooldown = this.entity.tickCount + this.rand.nextInt(100);
		}

	}

	@Override
	public void tick() 
	{
		List<ItemEntity> list = this.entity.level.getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), DolphinEntity.ALLOWED_ITEMS);
		ItemStack itemstack = this.entity.getItemBySlot(EquipmentSlotType.MAINHAND);
		if(!itemstack.isEmpty()) 
		{
			this.drop(itemstack);
			this.entity.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
		} 
		else if(!list.isEmpty()) 
		{
			this.entity.getNavigation().moveTo(list.get(0), (double) 1.2F);
		}

	}

	private void drop(ItemStack stack) 
	{
		if(!stack.isEmpty()) 
		{
			double d0 = this.entity.getEyeY() - (double) 0.3F;
			ItemEntity itementity = new ItemEntity(this.entity.level, this.entity.getX(), d0, this.entity.getZ(), stack);
			itementity.setPickUpDelay(40);
			itementity.setThrower(this.entity.getUUID());
			float f1 = this.rand.nextFloat() * ((float) Math.PI * 2F);
			float f2 = 0.02F * this.rand.nextFloat();
			itementity.setDeltaMovement((double) (0.3F * -MathHelper.sin(this.entity.yRot * ((float) Math.PI / 180F)) * MathHelper.cos(this.entity.xRot * ((float) Math.PI / 180F)) + MathHelper.cos(f1) * f2), (double) (0.3F * MathHelper.sin(this.entity.xRot * ((float) Math.PI / 180F)) * 1.5F), (double) (0.3F * MathHelper.cos(this.entity.yRot * ((float) Math.PI / 180F)) * MathHelper.cos(this.entity.xRot * ((float) Math.PI / 180F)) + MathHelper.sin(f1) * f2));
			this.entity.level.addFreshEntity(itementity);
		}
	}
}
