package lostworlds.server.menu.slot;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ForgeEventFactory;

public class ResultSlot extends Slot {
	private final Player player;
	private int removeCount;

	public ResultSlot(Player entity, Container inv, int slotId, int x, int y) {
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
	public void onTake(Player entity, ItemStack stack) {
		this.checkTakeAchievements(stack);
		super.onTake(entity, stack);
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
		ForgeEventFactory.firePlayerSmeltedEvent(this.player, stack);
	}
}
