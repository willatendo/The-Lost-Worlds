package lostworlds.server.entity.fossil;

import lostworlds.server.item.WetPaperItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

public class DirtyFossilEntity extends FossilEntity {
	public DirtyFossilEntity(EntityType<? extends FossilEntity> entity, Level world) {
		super(entity, world);
	}

	public DirtyFossilEntity setPick(ItemStack stack) {
		return this.setPick(stack);
	}

	@Override
	public InteractionResult mobInteract(Player entity, InteractionHand hand) {
		ItemStack stack = entity.getItemInHand(hand);
		if (stack.getItem() instanceof WetPaperItem) {
			DirtyFossilEntity fossil = this;
			entity.playSound(SoundEvents.WOOL_BREAK, 1.0F, 1.0F);
			if (!fossil.level.isClientSide) {
				fossil.dropAllDeathLoot(DamageSource.GENERIC);
			}
			fossil.remove();
			stack.shrink(1);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}
}
