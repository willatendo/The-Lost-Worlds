package lostworlds.client.book.data.element;

import lostworlds.client.book.repository.BookRepository;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

public class DataLocation implements DataElement {
	public String file;
	public transient ResourceLocation location;

	@Override
	public void load(BookRepository source) {
		this.location = "$BLOCK_ATLAS".equals(this.file) ? InventoryMenu.BLOCK_ATLAS : source.getResourceLocation(this.file, true);
	}
}
