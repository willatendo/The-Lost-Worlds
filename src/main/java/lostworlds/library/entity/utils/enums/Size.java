package lostworlds.library.entity.utils.enums;

public enum Size {
	TINY(0),
	SMALL(1),
	MEDIUM(2),
	LARGE(3),
	NONE(256);

	private int size;

	private Size(int size) {
		this.size = size;
	}

	private int getSize() {
		return this.size;
	}

	public boolean equalToSmallerThan(Size size) {
		return this.size <= size.getSize();
	}
}
