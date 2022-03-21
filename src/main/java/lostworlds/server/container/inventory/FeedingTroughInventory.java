package lostworlds.server.container.inventory;

import net.minecraftforge.items.ItemStackHandler;

public class FeedingTroughInventory extends ItemStackHandler {
	private IChanged change = null;

	public FeedingTroughInventory(int size, IChanged change) {
		super(size);
		this.change = change;
	}

	public FeedingTroughInventory(int size) {
		super(size);
	}

	@Override
	protected void onContentsChanged(int slot) {
		super.onContentsChanged(slot);
		if (change != null)
			change.changed(slot);
	}

	public interface IChanged {
		public void changed(int slot);
	}
}
