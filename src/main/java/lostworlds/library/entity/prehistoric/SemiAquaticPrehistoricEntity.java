package lostworlds.library.entity.prehistoric;

import lostworlds.library.entity.ISemiAquatic;
import lostworlds.library.entity.TimeEras;
import lostworlds.library.entity.controller.AquaticMovementController;
import lostworlds.library.entity.navigators.AquaticPathNavigator;
import lostworlds.library.entity.navigators.TerrestrialPathNavigator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;

public class SemiAquaticPrehistoricEntity extends PrehistoricEntity implements ISemiAquatic
{
	public float groundProgress = 0;
	public float prevGroundProgress = 0;
	public float swimProgress = 0;
	public float prevSwimProgress = 0;
	private int swimTimer = -1000;
	private int ticksSinceInWater = 0;
	private boolean isLandNavigator;
	
	public SemiAquaticPrehistoricEntity(EntityType<? extends PrehistoricEntity> entity, World world, TimeEras era) 
	{
		super(entity, world, era);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
		this.setPathfindingMalus(PathNodeType.WATER_BORDER, 0.0F);
		switchNavigator(false);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) 
	{
		super.addAdditionalSaveData(nbt);
		nbt.putInt("SwimTimer", this.swimTimer);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) 
	{
		super.readAdditionalSaveData(nbt);
		this.swimTimer = nbt.getInt("SwimTimer");
	}
	
	private void switchNavigator(boolean onLand) 
	{
		if(onLand) 
		{
			this.moveControl = new MovementController(this);
			this.navigation = new TerrestrialPathNavigator(this, level);
			this.isLandNavigator = true;
		} 
		else 
		{
			this.moveControl = new AquaticMovementController(this, 1F);
			this.navigation = new AquaticPathNavigator(this, level);
            this.isLandNavigator = false;
        }
	}

	@Override
	public boolean shouldEnterWater() 
	{
		return false;
	}

	@Override
	public boolean shouldLeaveWater() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldStopMoving() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getWaterSearchRange() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
