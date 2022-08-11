package lostworlds.server.loot;

import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class AddItemLootModiferSerializer extends GlobalLootModifierSerializer<AddItemLootModifer> {
	@Override
	public AddItemLootModifer read(ResourceLocation location, JsonObject object, ILootCondition[] condition) {
		Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(object, "Item")));
		return new AddItemLootModifer(condition, addition);
	}

	@Override
	public JsonObject write(AddItemLootModifer instance) {
		JsonObject json = makeConditions(instance.getConditions());
		json.addProperty("Item", ForgeRegistries.ITEMS.getKey(instance.getItem()).toString());
		return json;
	}
}