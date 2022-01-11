package lostworlds.library.container.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.hooks.BasicEventHooks;

public class ResultSlot extends Slot {
	private final PlayerEntity player;
	private int removeCount;

	public ResultSlot(PlayerEntity entity, IInventory inv, int slotId, int x, int y) {
		super(inv, slotId, x, y);
		this.player = entity;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}

	@Override
	public ItemStack remove(int slot) {
		if (this.hasItem()) {
			this.removeCount += Math.min(slot, this.getItem().getCount());
		}

		return super.remove(slot);
	}

	@Override
	public ItemStack onTake(PlayerEntity entity, ItemStack stack) {
		this.checkTakeAchievements(stack);
		super.onTake(entity, stack);
		return stack;
	}

	@Override
	protected void onQuickCraft(ItemStack stack, int slot) {
		this.removeCount += slot;
		this.checkTakeAchievements(stack);
	}

	@Override
	protected void checkTakeAchievements(ItemStack stack) {
		stack.onCraftedBy(this.player.level, this.player, this.removeCount);
		this.removeCount = 0;
		BasicEventHooks.firePlayerSmeltedEvent(this.player, stack);
	}
}
