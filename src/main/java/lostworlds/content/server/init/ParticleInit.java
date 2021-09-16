package lostworlds.content.server.init;

import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;

public class ParticleInit 
{	
	public static final ParticleType<BasicParticleType> PERMIAN_DESERT_AMBIANT_DUST = ModRegistry.register("permian_desert_ambiant_dust", new BasicParticleType(false));
	
	//Registry
	public static void init() { ModUtils.LOGGER.debug("Registering Mod Particles"); }
}
