package lostworlds.server.entity.utils.enums;

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

	public String getName() {
		switch (this) {
		default:
		case TINY:
			return "tiny_egg";
		case SMALL:
			return "small_egg";
		case MEDIUM:
			return "medium_egg";
		case LARGE:
			return "large_egg";
		}
	}
}
