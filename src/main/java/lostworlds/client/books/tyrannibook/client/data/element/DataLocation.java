package lostworlds.client.books.tyrannibook.client.data.element;

import lostworlds.client.books.tyrannibook.client.repository.TyrannobookRepository;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.resources.ResourceLocation;

public class DataLocation implements DataElement {
	public String file;
	public transient ResourceLocation location;

	@Override
	public void load(TyrannobookRepository source) {
		this.location = "$BLOCK_ATALS".equals(this.file) ? InventoryMenu.BLOCK_ATLAS : source.getResourceLocation(this.file, true);
	}
}
