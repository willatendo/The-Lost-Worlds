package lostworlds.server.entity.utils;

public interface ISemiAquatic {
	boolean shouldEnterWater();

	boolean shouldLeaveWater();

	boolean shouldStopMoving();

	int getWaterSearchRange();
}
