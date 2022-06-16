package lostworlds.server.events;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.illager.FossilPoacherEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.FORGE)
public class EntityJoinWorldEvents {
	@SubscribeEvent
	public static void onEntityJoin(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof Villager) {
			Villager villager = (Villager) event.getEntity();
			villager.goalSelector.addGoal(1, new AvoidEntityGoal(villager, FossilPoacherEntity.class, 16.0F, 0.7D, 0.7D));
		}
	}
}
