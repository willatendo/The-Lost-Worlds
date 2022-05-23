package lostworlds.server.entity.goal.terrestrial;

import java.util.EnumSet;

import lostworlds.server.entity.terrestrial.EggLayingMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class SleepyTemptGoal extends Goal {
	private static final TargetingConditions TEMP_TARGETING = TargetingConditions.forNonCombat().range(10.0D).ignoreLineOfSight();
	protected final EggLayingMob entity;
	private final double speedModifier;
	private double px;
	private double py;
	private double pz;
	private double pRotX;
	private double pRotY;
	protected Player player;
	private int calmDown;
	private boolean isRunning;
	private final Ingredient items;
	private final boolean canScare;

	public SleepyTemptGoal(EggLayingMob entity, double speedModifier, Ingredient followIngredient, boolean canScare) {
		this(entity, speedModifier, canScare, followIngredient);
	}

	public SleepyTemptGoal(EggLayingMob entity, double speedModifier, boolean canScare, Ingredient followIngredient) {
		this.entity = entity;
		this.speedModifier = speedModifier;
		this.items = followIngredient;
		this.canScare = canScare;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		if (!(entity.getNavigation() instanceof GroundPathNavigation) && !(entity.getNavigation() instanceof FlyingPathNavigation)) {
			throw new IllegalArgumentException("Unsupported entity type for SleepyTemptGoal");
		}
	}

	@Override
	public boolean canUse() {
		if (this.calmDown > 0) {
			--this.calmDown;
			return false;
		} else if (this.entity.isSleeping()) {
			return false;
		} else {
			this.player = this.entity.level.getNearestPlayer(TEMP_TARGETING, this.entity);
			if (this.player == null) {
				return false;
			} else {
				return this.shouldFollowItem(this.player.getMainHandItem()) || this.shouldFollowItem(this.player.getOffhandItem());
			}
		}
	}

	protected boolean shouldFollowItem(ItemStack stack) {
		return this.items.test(stack);
	}

	@Override
	public boolean canContinueToUse() {
		if (this.canScare()) {
			if (this.entity.distanceToSqr(this.player) < 36.0D) {
				if (this.player.distanceToSqr(this.px, this.py, this.pz) > 0.010000000000000002D) {
					return false;
				}

				if (Math.abs((double) this.player.xRot - this.pRotX) > 5.0D || Math.abs((double) this.player.yRot - this.pRotY) > 5.0D) {
					return false;
				}
			} else {
				this.px = this.player.getX();
				this.py = this.player.getY();
				this.pz = this.player.getZ();
			}

			this.pRotX = (double) this.player.xRot;
			this.pRotY = (double) this.player.yRot;
		}

		return this.canUse();
	}

	protected boolean canScare() {
		return this.canScare;
	}

	@Override
	public void start() {
		this.px = this.player.getX();
		this.py = this.player.getY();
		this.pz = this.player.getZ();
		this.isRunning = true;
	}

	@Override
	public void stop() {
		this.player = null;
		this.entity.getNavigation().stop();
		this.calmDown = 100;
		this.isRunning = false;
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

	public boolean isRunning() {
		return this.isRunning;
	}
}
