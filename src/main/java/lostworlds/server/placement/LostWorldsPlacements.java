package lostworlds.server.placement;

import lostworlds.LostWorldsMod;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsPlacements {
	public static final DeferredRegister<Placement<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.DECORATORS, LostWorldsMod.ID);

	public static final Placement<NoPlacementConfig> NEST = register("nest", new Height55To60(NoPlacementConfig.CODEC));

	public static <T extends IPlacementConfig> Placement<T> register(String id, Placement<T> placement) {
		DECORATORS.register(id, () -> placement);
		return placement;
	}

	public static void deferred(IEventBus bus) {
		DECORATORS.register(bus);
	}
}
