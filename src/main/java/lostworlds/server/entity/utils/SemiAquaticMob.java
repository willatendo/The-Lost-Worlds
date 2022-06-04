package lostworlds.server.entity.utils;

public interface SemiAquaticMob {
	boolean shouldEnterWater();

	boolean shouldLeaveWater();

	boolean shouldStopMoving();

	int getWaterSearchRange();
}
