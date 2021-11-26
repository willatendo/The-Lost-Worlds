package lostworlds.library.entity.controller;

import lostworlds.library.entity.aquatic.DolphinLikeEntity;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.util.math.MathHelper;

public class DolphinLikeLookController extends LookController 
{
	private final int maxYRotFromCenter;

	public DolphinLikeLookController(DolphinLikeEntity entity, int maxYRotFromCenter) 
	{
		super(entity);
		this.maxYRotFromCenter = maxYRotFromCenter;
	}

	@Override
	public void tick() 
	{
		if(this.hasWanted) 
		{
			this.hasWanted = false;
			this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, this.getYRotD() + 20.0F, this.yMaxRotSpeed);
			this.mob.xRot = this.rotateTowards(this.mob.xRot, this.getXRotD() + 10.0F, this.xMaxRotAngle);
		} 
		else 
		{
			if(this.mob.getNavigation().isDone()) 
			{
				this.mob.xRot = this.rotateTowards(this.mob.xRot, 0.0F, 5.0F);
			}

			this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, this.mob.yBodyRot, this.yMaxRotSpeed);
		}

		float f = MathHelper.wrapDegrees(this.mob.yHeadRot - this.mob.yBodyRot);
		if(f < (float) (-this.maxYRotFromCenter)) 
		{
			this.mob.yBodyRot -= 4.0F;
		} 
		else if(f > (float) this.maxYRotFromCenter) 
		{
			this.mob.yBodyRot += 4.0F;
		}
	}
}
