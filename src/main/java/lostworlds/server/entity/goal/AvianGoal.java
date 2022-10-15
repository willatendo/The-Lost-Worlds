package lostworlds.server.entity.goal;

import lostworlds.server.entity.avian.Pterosaur;
import net.minecraft.world.entity.ai.goal.Goal;

public class AvianGoal extends Goal {
	private final Pterosaur pterosaur;

	public AvianGoal(Pterosaur pterosaur) {
		this.pterosaur = pterosaur;
	}

	@Override
	public boolean canUse() {
		return this.pterosaur.canCompleteFlyingGoals;
	}
}
