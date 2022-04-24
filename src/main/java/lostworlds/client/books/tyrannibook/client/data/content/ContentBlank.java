package lostworlds.client.books.tyrannibook.client.data.content;

import java.util.ArrayList;

import lostworlds.client.books.tyrannibook.client.data.TyrannobookData;
import lostworlds.client.books.tyrannibook.client.data.element.TyrannobookElement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContentBlank extends PageContent {
	@Override
	public void build(TyrannobookData book, ArrayList<TyrannobookElement> list, boolean rightSide) {
	}
}
