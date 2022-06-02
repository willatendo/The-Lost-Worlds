package lostworlds.client.book.structure.level;

import java.util.Collections;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.AABB;

public class FakeEntityGetter implements LevelEntityGetter<Entity> {
	public static final FakeEntityGetter INSTANCE = new FakeEntityGetter();

	private FakeEntityGetter() {
	}

	@Nullable
	@Override
	public Entity get(int id) {
		return null;
	}

	@Nullable
	@Override
	public Entity get(UUID pUuid) {
		return null;
	}

	@Override
	public Iterable<Entity> getAll() {
		return Collections.emptyList();
	}

	@Override
	public <U extends Entity> void get(EntityTypeTest<Entity, U> typeTest, Consumer<U> successConsumer) {
	}

	@Override
	public void get(AABB aabb, Consumer<Entity> successConsumer) {
	}

	@Override
	public <U extends Entity> void get(EntityTypeTest<Entity, U> typeTest, AABB aabb, Consumer<U> successConsumer) {
	}
}
