package lostworlds.server.entity.fossil;

import lostworlds.server.item.WetPaperItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DirtyFossil extends Fossil {
	public DirtyFossil(EntityType<? extends Fossil> entity, Level world) {
		super(entity, world);
	}

	public DirtyFossil setDirtyPick(ItemStack stack) {
		return (DirtyFossil) this.setPick(stack);
	}

	@Override
	public InteractionResult mobInteract(Player entity, InteractionHand hand) {
		ItemStack stack = entity.getItemInHand(hand);
		if (stack.getItem() instanceof WetPaperItem) {
			DirtyFossil fossil = this;
			entity.playSound(SoundEvents.WOOL_BREAK, 1.0F, 1.0F);
			if (!fossil.level.isClientSide) {
				fossil.dropAllDeathLoot(DamageSource.GENERIC);
			}
			fossil.kill();
			stack.shrink(1);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}
}
