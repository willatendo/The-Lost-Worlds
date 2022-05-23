package lostworlds.server.biome;

import lostworlds.LostWorldsMod;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsBlockstateProviders {
	public static final DeferredRegister<BlockStateProviderType<?>> BLOCK_STATE_PROVIDER = DeferredRegister.create(ForgeRegistries.BLOCK_STATE_PROVIDER_TYPES, LostWorldsMod.ID);

	public static final RegistryObject<BlockStateProviderType<SuppliedBlockStateProvider>> SUPPLIED_BLOCK_STATE_PROVIDER = BLOCK_STATE_PROVIDER.register("supplied_block_state_provider", () -> new BlockStateProviderType<>(SuppliedBlockStateProvider.CODEC));

	// Registry
	public static void deferred(IEventBus bus) {
		BLOCK_STATE_PROVIDER.register(bus);
	}
}
