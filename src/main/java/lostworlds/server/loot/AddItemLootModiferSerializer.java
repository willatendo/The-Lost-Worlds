package lostworlds.server.loot;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class AddItemLootModiferSerializer extends GlobalLootModifierSerializer<AddItemLootModifer> {
	@Override
	public AddItemLootModifer read(ResourceLocation location, JsonObject object, LootItemCondition[] condition) {
		Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "Item")));
		return new AddItemLootModifer(condition, addition);
	}

	@Override
	public JsonObject write(AddItemLootModifer instance) {
		JsonObject json = makeConditions(instance.getConditions());
		json.addProperty("Item", ForgeRegistries.ITEMS.getKey(instance.getItem()).toString());
		return json;
	}
}
