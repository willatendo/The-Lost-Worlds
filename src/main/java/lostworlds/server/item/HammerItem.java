package lostworlds.server.item;

import lostworlds.server.LostWorldsTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;

public class HammerItem extends DiggerItem {
	public HammerItem(Tier teir, float attackDamage, float speed, Properties properties) {
		super(attackDamage, speed, teir, LostWorldsTags.ModBlockTags.MINEABLE_WITH_HAMMER.tag, properties);
	}
}
