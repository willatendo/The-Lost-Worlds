package lostworlds.content.server.init;

import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtil;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

public class ParticleInit 
{	
	public static final ParticleType<BasicParticleType> PERMIAN_DESERT_AMBIANT_DUST = ModRegistry.register("permian_desert_ambiant_dust", new BasicParticleType(false));
	
	//Registry
	public static void init() { ModUtil.LOGGER.debug("Registering Mod Particles"); }
}
