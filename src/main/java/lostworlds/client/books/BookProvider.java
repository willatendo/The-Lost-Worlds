package lostworlds.client.books;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lostworlds.client.books.BookBuilder.SectionBuilder;
import lostworlds.client.books.BookBuilder.SectionBuilder.PageBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;

public abstract class BookProvider implements IDataProvider {
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	private final DataGenerator generator;
	private final String id;

	public BookProvider(DataGenerator generator, String id) {
		this.generator = generator;
		this.id = id;
	}

	@Override
	public void run(DirectoryCache cache) throws IOException {
		Path path = this.generator.getOutputFolder();
		this.makeBooks((bookBuilder) -> {
			this.saveToBook(cache, bookBuilder.serializeAppearance(), path.resolve("assets/" + this.id + "/tyrannobook/" + bookBuilder.bookName + "/appearance.json"));
			this.saveToBook(cache, bookBuilder.serializeIndex(), path.resolve("assets/" + this.id + "/tyrannobook/" + bookBuilder.bookName + "/index.json"));
			for (SectionBuilder sections : bookBuilder.sections) {
				this.saveToBook(cache, sections.serializeSection(), path.resolve("assets/" + this.id + "/tyrannobook/" + bookBuilder.bookName + "/sections/" + sections.section + ".json"));
				for (PageBuilder pages : sections.pages) {
					this.saveToBook(cache, pages.serializePage(), path.resolve("assets/" + this.id + "/tyrannobook/" + bookBuilder.bookName + "/en_us/" + sections.section + "/" + pages.page + ".json"));
				}
			}
		});
	}

	private void saveToBook(DirectoryCache cache, JsonObject json, Path path) {
		try {
			String s = GSON.toJson((JsonElement) json);
			String s1 = SHA1.hashUnencodedChars(s).toString();
			if (!Objects.equals(cache.getHash(path), s1) || !Files.exists(path)) {
				Files.createDirectories(path.getParent());

				try (BufferedWriter bufferedwriter = Files.newBufferedWriter(path)) {
					bufferedwriter.write(s);
				}
			}
			cache.putNew(path, s1);
		} catch (IOException ioexception) {
		}
	}

	private void saveToBook(DirectoryCache cache, JsonArray json, Path path) {
		try {
			String s = GSON.toJson((JsonElement) json);
			String s1 = SHA1.hashUnencodedChars(s).toString();
			if (!Objects.equals(cache.getHash(path), s1) || !Files.exists(path)) {
				Files.createDirectories(path.getParent());

				try (BufferedWriter bufferedwriter = Files.newBufferedWriter(path)) {
					bufferedwriter.write(s);
				}
			}
			cache.putNew(path, s1);
		} catch (IOException ioexception) {
		}
	}

	public abstract void makeBooks(Consumer<BookBuilder> consumer);

	@Override
	public String getName() {
		return "Books";
	}
}
