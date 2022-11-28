package lostworlds.data.custom;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lostworlds.data.custom.BookBuilder.SectionBuilder;
import lostworlds.data.custom.BookBuilder.SectionBuilder.PageBuilder;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;

public abstract class BookProvider implements DataProvider {
	protected static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	protected final DataGenerator generator;
	protected final String id;

	public BookProvider(DataGenerator generator, String id) {
		this.generator = generator;
		this.id = id;
	}

	@Override
	public void run(CachedOutput cache) throws IOException {
		Path path = this.generator.getOutputFolder();
		this.makeBooks((bookBuilder) -> {
			try {
				DataProvider.saveStable(cache, bookBuilder.serializeAppearance(), path.resolve("assets/" + this.id + "/book/" + bookBuilder.bookName + "/appearance.json"));
				DataProvider.saveStable(cache, bookBuilder.serializeIndex(), path.resolve("assets/" + this.id + "/book/" + bookBuilder.bookName + "/index.json"));
				DataProvider.saveStable(cache, bookBuilder.serializeLang(), path.resolve("assets/" + this.id + "/book/" + bookBuilder.bookName + "/en_us/language.json"));
				for (SectionBuilder sections : bookBuilder.sections) {
					DataProvider.saveStable(cache, sections.serializeSection(), path.resolve("assets/" + this.id + "/book/" + bookBuilder.bookName + "/sections/" + sections.section + ".json"));
					for (PageBuilder pages : sections.pages) {
						DataProvider.saveStable(cache, pages.serializePage(), path.resolve("assets/" + this.id + "/book/" + bookBuilder.bookName + "/en_us/" + sections.section + "/" + pages.page + ".json"));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}

	public abstract void makeBooks(Consumer<BookBuilder> consumer);

	public void addBook(BookBuilder bookBuilder, Consumer<BookBuilder> consumer) {
		consumer.accept(bookBuilder);
	}

	@Override
	public String getName() {
		return "Books";
	}
}
