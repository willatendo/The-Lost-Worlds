package lostworlds.client.book.action.protocol;

import lostworlds.client.book.screen.book.BookScreen;

public class ProtocolGoToPage extends ActionProtocol {
	private final boolean returner;

	public ProtocolGoToPage(boolean returner) {
		this.returner = returner;
	}

	@Override
	public void processCommand(BookScreen book, String param) {
		int pageNum = book.book.findPageNumber(param);

		if (pageNum >= 0) {
			book.openPage(pageNum, this.returner);
		}
	}
}
