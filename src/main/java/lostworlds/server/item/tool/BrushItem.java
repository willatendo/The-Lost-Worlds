package lostworlds.server.item.tool;

import lostworlds.server.LostWorldsTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;

public class BrushItem extends DiggerItem {
	public BrushItem(Tier modteir, Properties properties) {
		super(1.0F, -2.4F, modteir, LostWorldsTags.ModBlockTags.MINEABLE_WITH_BRUSH.tag, properties);
	}
}
