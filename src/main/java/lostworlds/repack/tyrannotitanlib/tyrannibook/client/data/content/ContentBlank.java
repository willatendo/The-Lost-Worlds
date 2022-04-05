package lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content;

import java.util.ArrayList;

import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.TyrannobookData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.TyrannobookElement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContentBlank extends PageContent {
	@Override
	public void build(TyrannobookData book, ArrayList<TyrannobookElement> list, boolean rightSide) {
	}
}
