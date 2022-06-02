package lostworlds.client.book.action.protocol;

import lostworlds.client.book.screen.book.BookScreen;

public abstract class ActionProtocol {
	public abstract void processCommand(BookScreen book, String param);
}
