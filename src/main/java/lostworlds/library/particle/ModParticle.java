package lostworlds.library.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * Author: Willatendo
 * Date: July 2, 2021
 */

@OnlyIn(Dist.CLIENT)
public class ModParticle extends SpriteTexturedParticle 
{
	public ModParticle(ClientWorld worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) 
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

		float f = this.random.nextFloat() * 1.0f;
		this.rCol = f;
		this.gCol = f;
		this.bCol = f;

		this.setSize(0.02f, 0.02f);
		this.quadSize *= this.random.nextFloat() * 1.1F;
		this.xd *= (double)0.02f;
		this.yd *= (double)0.02f;
		this.zd *= (double)0.02f;
		this.lifetime = (int)(20.0D / (Math.random() * 1.0D));
	}

	@Override
	public IParticleRenderType getRenderType() 
	{
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() 
	{
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;

		if(this.lifetime-- <= 0) 
		{
			this.remove();
		} 
		else 
		{
			this.move(this.xd, this.yd, this.zd);
			this.xd *= 1.0D;
			this.yd *= 1.0D;
			this.zd *= 1.0D;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> 
	{
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite sprite) {
			this.spriteSet = sprite;
		}


		@Override
		public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) 
		{
			ModParticle particle = new ModParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			particle.setColor(1.0f, 1.0f, 1.0f);
			particle.pickSprite(this.spriteSet);
			return particle;
		}

	}
}
