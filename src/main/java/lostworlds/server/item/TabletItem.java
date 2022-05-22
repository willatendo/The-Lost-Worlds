package lostworlds.server.item;

import lostworlds.client.screen.tablet.TabletScreen;
import lostworlds.server.entity.terrestrial.PrehistoricEntity;
import lostworlds.server.entity.terrestrial.TaggedEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TabletItem extends Item {
	public TabletItem(Properties properties) {
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
		Minecraft instance = Minecraft.getInstance();

		if (entity instanceof PrehistoricEntity) {
			if (entity instanceof TaggedEntity) {
				TaggedEntity tagged = (TaggedEntity) entity;
				if (tagged.isTagged()) {
					instance.setScreen(new TabletScreen(tagged));
					return InteractionResult.SUCCESS;
				}
			}
		}

		return InteractionResult.FAIL;
	}
}
