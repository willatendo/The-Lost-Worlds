package lostworlds.client.book.data.element;

import lostworlds.client.book.repository.BookRepository;

public interface DataElement {
	void load(BookRepository source);
}
