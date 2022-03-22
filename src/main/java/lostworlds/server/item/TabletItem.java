package lostworlds.server.item;

import lostworlds.client.screen.tablet.TabletScreen;
import lostworlds.server.entity.terrestrial.PrehistoricEntity;
import lostworlds.server.entity.terrestrial.TaggedEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TabletItem extends ModItem {
	public TabletItem(Properties properties) {
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerEntity, LivingEntity entity, Hand hand) {
		Minecraft instance = Minecraft.getInstance();

		if (entity instanceof PrehistoricEntity) {
			if (entity instanceof TaggedEntity) {
				TaggedEntity tagged = (TaggedEntity) entity;
				if (tagged.isTagged()) {
					instance.setScreen(new TabletScreen(tagged));
					return ActionResultType.SUCCESS;
				}
			}
		}

		return ActionResultType.FAIL;
	}
}
