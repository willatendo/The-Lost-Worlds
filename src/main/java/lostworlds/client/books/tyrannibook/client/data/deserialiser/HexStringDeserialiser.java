package lostworlds.client.books.tyrannibook.client.data.deserialiser;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class HexStringDeserialiser implements JsonDeserializer<Integer> {
	@Override
	public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
			return json.getAsInt();
		} else if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isString()) {
			try {
				String s = json.getAsString();

				if (s.toLowerCase().startsWith("0b")) {
					return Integer.parseInt(s.substring(2), 2);
				} else if (s.toLowerCase().startsWith("0x")) {
					return Integer.parseInt(s.substring(2), 16);
				}
			} catch (NumberFormatException ignored) {
			}
		}

		return null;
	}
}
