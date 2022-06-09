package lostworlds.server.loot;

import lostworlds.server.LostWorldsUtils;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsGlobalLootModifers {
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, LostWorldsUtils.ID);

	public static final RegistryObject<GlobalLootModifierSerializer<AddItemLootModifer>> ADD_ITEM_LOOT_MODIFIER_SERIALIZER = LOOT_MODIFIER_SERIALIZERS.register("add_item_loot_modifier_serializer", () -> new AddItemLootModiferSerializer());
}
