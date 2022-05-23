package lostworlds.server.entity.goal.aquatic;

import lostworlds.server.entity.aquatic.BasicFishLikeMob;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;

public class FishLikeSwimGoal extends RandomSwimmingGoal {
	private final BasicFishLikeMob fish;

	public FishLikeSwimGoal(BasicFishLikeMob entity) {
		super(entity, 1.0D, 40);
		this.fish = entity;
	}

	public boolean canUse() {
		return this.fish.canRandomSwim() && super.canUse();
	}
}
