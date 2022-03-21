package lostworlds.server.entity.goal.aquatic;

import lostworlds.server.entity.aquatic.BasicFishLikeEntity;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;

public class FishLikeSwimGoal extends RandomSwimmingGoal {
	private final BasicFishLikeEntity fish;

	public FishLikeSwimGoal(BasicFishLikeEntity entity) {
		super(entity, 1.0D, 40);
		this.fish = entity;
	}

	public boolean canUse() {
		return this.fish.canRandomSwim() && super.canUse();
	}
}
