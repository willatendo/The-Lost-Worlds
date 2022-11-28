package lostworlds.server.entity;

import software.bernie.geckolib3.core.builder.ILoopType;

public class Loop implements ILoopType {
	private final boolean loop;

	public Loop(boolean loop) {
		this.loop = loop;
	}

	@Override
	public boolean isRepeatingAfterEnd() {
		return this.loop;
	}
}
