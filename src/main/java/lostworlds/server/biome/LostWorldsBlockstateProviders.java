package lostworlds.server.biome;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsBlockstateProviders {
	public static final DeferredRegister<BlockStateProviderType<?>> BLOCK_STATE_PROVIDER = DeferredRegister.create(ForgeRegistries.BLOCK_STATE_PROVIDER_TYPES, LostWorldsUtils.ID);

	public static final RegistryObject<BlockStateProviderType<SuppliedBlockStateProvider>> SUPPLIED_BLOCK_STATE_PROVIDER = BLOCK_STATE_PROVIDER.register("supplied_block_state_provider", () -> new BlockStateProviderType<>(SuppliedBlockStateProvider.CODEC));

	// Registry
	public static void deferred(IEventBus bus) {
		BLOCK_STATE_PROVIDER.register(bus);
	}
}
