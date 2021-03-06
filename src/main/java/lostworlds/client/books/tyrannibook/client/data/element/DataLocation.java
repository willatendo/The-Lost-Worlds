package lostworlds.client.books.tyrannibook.client.data.element;

import lostworlds.client.books.tyrannibook.client.repository.TyrannobookRepository;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;

public class DataLocation implements DataElement {
	public String file;
	public transient ResourceLocation location;

	@Override
	public void load(TyrannobookRepository source) {
		this.location = "$BLOCK_ATALS".equals(this.file) ? PlayerContainer.BLOCK_ATLAS : source.getResourceLocation(this.file, true);
	}
}
