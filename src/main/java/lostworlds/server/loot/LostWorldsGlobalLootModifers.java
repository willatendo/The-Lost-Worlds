package lostworlds.server.loot;

import lostworlds.server.LostWorldsUtils;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsGlobalLootModifers {
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, LostWorldsUtils.ID);

	public static final RegistryObject<GlobalLootModifierSerializer<AddItemLootModifer>> ADD_ITEM_LOOT_MODIFIER_SERIALIZER = LOOT_MODIFIER_SERIALIZERS.register("add_item_loot_modifier_serializer", () -> new AddItemLootModiferSerializer());

	public static void deferred(IEventBus bus) {
		LOOT_MODIFIER_SERIALIZERS.register(bus);
	}
}
