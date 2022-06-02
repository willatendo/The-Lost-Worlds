package lostworlds.client.book.action;

import java.util.HashMap;

import javax.annotation.Nullable;

import lostworlds.client.book.action.protocol.ActionProtocol;
import lostworlds.client.book.screen.book.BookScreen;
import net.minecraft.resources.ResourceLocation;

public class StringActionProcessor {

	public static final String PROTOCOL_SEPARATOR = " ";

	private static final HashMap<ResourceLocation, ActionProtocol> protocols = new HashMap<>();

	public static void registerProtocol(ResourceLocation id, ActionProtocol protocol) {
		if (protocols.containsKey(id)) {
			throw new IllegalArgumentException("Protocol " + id + " already registered.");
		}

		protocols.put(id, protocol);
	}

	// Format: modid:action param
	public static void process(@Nullable String action, BookScreen book) {
		if (action == null || !action.contains(PROTOCOL_SEPARATOR)) {
			return;
		}

		String id = action.substring(0, action.indexOf(PROTOCOL_SEPARATOR));
		if (!id.contains(":"))
			id = "mantle:" + id;

		ResourceLocation protoId = new ResourceLocation(id);
		String protoParam = action.substring(action.indexOf(PROTOCOL_SEPARATOR) + PROTOCOL_SEPARATOR.length());

		if (protocols.containsKey(protoId)) {
			protocols.get(protoId).processCommand(book, protoParam);
		}
	}
}
