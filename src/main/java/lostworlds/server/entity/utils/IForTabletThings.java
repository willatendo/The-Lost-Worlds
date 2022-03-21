package lostworlds.server.entity.utils;

import lostworlds.server.entity.utils.enums.ActivityType;
import lostworlds.server.entity.utils.enums.CreatureDiet;

public interface IForTabletThings {
	public CreatureDiet diet();

	public ActivityType activity();
}
