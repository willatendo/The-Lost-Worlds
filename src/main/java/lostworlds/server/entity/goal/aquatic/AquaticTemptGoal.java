package lostworlds.server.entity.goal.aquatic;

import java.util.EnumSet;

import lostworlds.server.entity.aquatic.BreedingWaterMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class AquaticTemptGoal extends Goal {
	   private static final TargetingConditions TEMPT_TARGETING = TargetingConditions.forNonCombat().range(10.0D).ignoreLineOfSight();
	private final BreedingWaterMob entity;
	private final double speedModifier;
	private Player player;
	private int calmDown;
	private final Ingredient items;

	public AquaticTemptGoal(BreedingWaterMob entity, double speedModifier, Ingredient items) {
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
