package lostworlds.client.book;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;

public class JsonHelper {
	public static JsonElement getElement(JsonObject json, String memberName) {
		if (json.has(memberName)) {
			return json.get(memberName);
		} else {
			throw new JsonSyntaxException("Missing " + memberName + "");
		}
	}

	public static <T> List<T> parseList(JsonArray array, String name, BiFunction<JsonElement, String, T> mapper) {
		if (array.size() == 0) {
			throw new JsonSyntaxException(name + " must have at least 1 element");
		}
		ImmutableList.Builder<T> builder = ImmutableList.builder();
		for (int i = 0; i < array.size(); i++) {
			builder.add(mapper.apply(array.get(i), name + "[" + i + "]"));
		}
		return builder.build();
	}

	public static <T> List<T> parseList(JsonArray array, String name, Function<JsonObject, T> mapper) {
		return parseList(array, name, (element, s) -> mapper.apply(GsonHelper.convertToJsonObject(element, s)));
	}

	public static <T> List<T> parseList(JsonObject parent, String name, BiFunction<JsonElement, String, T> mapper) {
		return parseList(GsonHelper.getAsJsonArray(parent, name), name, mapper);
	}

	public static <T> List<T> parseList(JsonObject parent, String name, Function<JsonObject, T> mapper) {
		return parseList(GsonHelper.getAsJsonArray(parent, name), name, mapper);
	}

	public static ResourceLocation getResourceLocation(JsonObject json, String key) {
		String text = GsonHelper.getAsString(json, key);
		ResourceLocation location = ResourceLocation.tryParse(text);
		if (location == null) {
			throw new JsonSyntaxException("Expected " + key + " to be a Resource location, was '" + text + "'");
		}
		return location;
	}

	public static ResourceLocation convertToResourceLocation(JsonElement json, String key) {
		String text = GsonHelper.convertToString(json, key);
		ResourceLocation location = ResourceLocation.tryParse(text);
		if (location == null) {
			throw new JsonSyntaxException("Expected " + key + " to be a Resource location, was '" + text + "'");
		}
		return location;
	}

	public static int parseColor(@Nullable String color) {
		if (color == null || color.isEmpty()) {
			return -1;
		}
		if (color.charAt(0) != '-') {
			try {
				int length = color.length();
				if (length == 8) {
					return (int) Long.parseLong(color, 16);
				}
				if (length == 6) {
					return 0xFF000000 | Integer.parseInt(color, 16);
				}
			} catch (NumberFormatException ex) {
			}
		}
		throw new JsonSyntaxException("Invalid color '" + color + "'");
	}
}
