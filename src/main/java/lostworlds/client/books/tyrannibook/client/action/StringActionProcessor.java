package lostworlds.client.books.tyrannibook.client.action;

import java.util.HashMap;

import javax.annotation.Nullable;

import lostworlds.client.books.tyrannibook.client.action.protocol.ActionProtocol;
import lostworlds.client.books.tyrannibook.client.screen.TyrannobookScreen;

public class StringActionProcessor {
	public static final String PROTOCOL_SEPARATOR = ":";

	private static final HashMap<String, ActionProtocol> protocols = new HashMap<>();

	public static void registerProtocol(@Nullable ActionProtocol protocol) {
		if (protocol == null || protocol.protocol == null || protocol.protocol.isEmpty()) {
			throw new IllegalArgumentException("Protocol must be defined and must not have an empty protocol identifier.");
		}

		if (protocols.containsKey(protocol.protocol)) {
			throw new IllegalArgumentException("Protocol " + protocol.protocol + " already registered.");
		}

		protocols.put(protocol.protocol, protocol);
	}

	public static void process(@Nullable String action, TyrannobookScreen book) {
		if (action == null || !action.contains(PROTOCOL_SEPARATOR)) {
			return;
		}

		String protoId = action.substring(0, action.indexOf(PROTOCOL_SEPARATOR));
		String protoParam = action.substring(action.indexOf(PROTOCOL_SEPARATOR) + PROTOCOL_SEPARATOR.length());

		if (protocols.containsKey(protoId)) {
			protocols.get(protoId).processCommand(book, protoParam);
		}
	}
}
