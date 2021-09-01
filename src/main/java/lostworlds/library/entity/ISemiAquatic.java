package lostworlds.library.entity;

public interface ISemiAquatic 
{
	boolean shouldEnterWater();
	
	boolean shouldLeaveWater();
	
	boolean shouldStopMoving();
	
	int getWaterSearchRange();
}
