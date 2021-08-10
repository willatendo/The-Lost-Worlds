package lostworlds.content.client.setup;

import lostworlds.content.server.init.ParticleInit;
import lostworlds.library.particle.ModParticle;
import lostworlds.library.util.ModUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/*
 * Author: Willatendo
 * Date: July 8, 2021
 */

@EventBusSubscriber(modid = ModUtil.ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ParticleSetup 
{
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticles(ParticleFactoryRegisterEvent event) 
	{
		ModUtil.LOGGER.debug("Loading: Setting Up Particle Render");
		
		Minecraft.getInstance().particleEngine.register(ParticleInit.PERMIAN_DESERT_AMBIANT_DUST, ModParticle.Factory::new);
		
		ModUtil.LOGGER.debug("Finished: Setting Up Particle Render");
	}
}
	