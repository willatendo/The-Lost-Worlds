package lostworlds.library.entity.semiaquatic;

public interface ISemiAquatic 
{
	boolean shouldEnterWater();
	
	boolean shouldLeaveWater();
	
	boolean shouldStopMoving();
	
	int getWaterSearchRange();
}
