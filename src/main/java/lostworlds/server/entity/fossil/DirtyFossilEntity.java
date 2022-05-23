package lostworlds.server.entity.fossil;

import lostworlds.server.item.WetPaperItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class DirtyFossilEntity extends FossilEntity {
	public DirtyFossilEntity(EntityType<? extends FossilEntity> entity, World world) {
		super(entity, world);
	}

	public DirtyFossilEntity setDirtyPick(ItemStack stack) {
		return (DirtyFossilEntity) this.setPick(stack);
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity entity, Hand hand) {
		ItemStack stack = entity.getItemInHand(hand);
		if (stack.getItem() instanceof WetPaperItem) {
			DirtyFossilEntity fossil = this;
			entity.playSound(SoundEvents.WOOL_BREAK, 1.0F, 1.0F);
			if (!fossil.level.isClientSide) {
				fossil.dropAllDeathLoot(DamageSource.GENERIC);
			}
			fossil.remove();
			stack.shrink(1);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
}
