package lostworlds.client.books.tyrannibook.client.data;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface DataItem {
	void load();
}
