package lostworlds.server.entity.goal.semiaquatic;

import java.util.EnumSet;

import lostworlds.server.entity.semiaquatic.CarnivoreSemiAquaticEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class SemiAquaticTemptGoal extends Goal {
	private static final EntityPredicate TEMPT_TARGETING = (new EntityPredicate()).range(10.0D).allowSameTeam().allowInvulnerable();
	private final CarnivoreSemiAquaticEntity entity;
	private final double speedModifier;
	private PlayerEntity player;
	private int calmDown;
	private final Ingredient items;

	public SemiAquaticTemptGoal(CarnivoreSemiAquaticEntity entity, double speedModifier, Ingredient items) {
		this.entity = entity;
		this.speedModifier = speedModifier;
		this.items = items;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (this.calmDown > 0) {
			--this.calmDown;
			return false;
		} else {
			this.player = this.entity.level.getNearestPlayer(TEMPT_TARGETING, this.entity);
			if (this.player == null) {
				return false;
			} else {
				return this.shouldFollowItem(this.player.getMainHandItem()) || this.shouldFollowItem(this.player.getOffhandItem());
			}
		}
	}

	private boolean shouldFollowItem(ItemStack stack) {
		return this.items.test(stack);
	}

	@Override
	public boolean canContinueToUse() {
		return this.canUse();
	}

	@Override
	public void stop() {
		this.player = null;
		this.entity.getNavigation().stop();
		this.calmDown = 100;
	}

	@Override
	public void tick() {
		this.entity.getLookControl().setLookAt(this.player, (float) (this.entity.getMaxHeadYRot() + 20), (float) this.entity.getMaxHeadXRot());
		if (this.entity.distanceToSqr(this.player) < 6.25D) {
			this.entity.getNavigation().stop();
		} else {
			this.entity.getNavigation().moveTo(this.player, this.speedModifier);
		}
	}
}
