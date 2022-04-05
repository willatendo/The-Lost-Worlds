package lostworlds.repack.tyrannotitanlib.tyrannibook.client.action.protocol;

import lostworlds.repack.tyrannotitanlib.tyrannibook.client.screen.TyrannobookScreen;

public abstract class ActionProtocol {
	public final String protocol;

	public ActionProtocol(String protocol) {
		this.protocol = protocol;
	}

	public abstract void processCommand(TyrannobookScreen book, String param);
}
