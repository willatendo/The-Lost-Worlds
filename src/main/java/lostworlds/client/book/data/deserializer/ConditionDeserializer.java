package lostworlds.client.book.data.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;

public class ConditionDeserializer implements JsonDeserializer<ICondition> {
	@Override
	public ICondition deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (!json.isJsonObject()) {
			throw new JsonParseException("A condition must be a JSON Object");
		}
		
		return CraftingHelper.getCondition(json.getAsJsonObject());
	}
}
