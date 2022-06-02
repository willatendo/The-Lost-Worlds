package lostworlds.server.util.registrate;

import java.util.function.Consumer;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.RegistrateProvider;

import lostworlds.data.custom.BookBuilder;
import lostworlds.data.custom.BookProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.LogicalSide;

public class RegistrateBookProvider extends BookProvider implements RegistrateProvider, Consumer<BookBuilder> {
	private final AbstractRegistrate<?> owner;
	private Consumer<BookBuilder> callback;

	public RegistrateBookProvider(AbstractRegistrate<?> owner, DataGenerator generator) {
		super(generator, owner.getModid());
		this.owner = owner;
	}

	@Override
	public LogicalSide getSide() {
		return LogicalSide.CLIENT;
	}

	@Override
	public void makeBooks(Consumer<BookBuilder> consumer) {
		this.callback = consumer;
		this.owner.genData(LostWorldsRegistrate.BOOKS, this);
		this.callback = null;
	}

	@Override
	public void accept(BookBuilder bookBuilder) {
		if (callback == null) {
			throw new IllegalStateException("Cannot accept recipes outside of a call to makeBooks");
		}
		callback.accept(bookBuilder);
	}
}
