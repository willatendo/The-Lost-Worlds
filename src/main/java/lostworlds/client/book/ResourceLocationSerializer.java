package lostworlds.client.book;

import java.lang.reflect.Type;
import java.util.function.Function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;

public class ResourceLocationSerializer<T extends ResourceLocation> implements JsonDeserializer<T>, JsonSerializer<T> {
	private final Function<String, T> constructor;
	private final String modId;

	public ResourceLocationSerializer(Function<String, T> constructor, String modId) {
		this.constructor = constructor;
		this.modId = modId;
	}

	public static ResourceLocationSerializer<ResourceLocation> resourceLocation(String modId) {
		return new ResourceLocationSerializer<>(ResourceLocation::new, modId);
	}

	@Override
	public JsonElement serialize(ResourceLocation location, Type type, JsonSerializationContext context) {
		return new JsonPrimitive(location.toString());
	}

	@Override
	public T deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		String loc = GsonHelper.convertToString(element, "location");
		if (!loc.contains(":")) {
			loc = this.modId + ":" + loc;
		}
		return this.constructor.apply(loc);
	}
}
