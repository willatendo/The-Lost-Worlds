package lostworlds.client.books;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class BookBuilder {
	public final String bookName;
	public final String title;
	public final String subtitle;
	public final int coverColour;
	public final ArrayList<SectionBuilder> sections = new ArrayList<>();

	public BookBuilder(String bookName, String title, String subtitle, int coverColour) {
		this.bookName = bookName;
		this.title = title;
		this.subtitle = subtitle;
		this.coverColour = coverColour;
	}

	public BookBuilder addSections(SectionBuilder section) {
		this.sections.add(section);
		return this;
	}

	public JsonObject serializeAppearance() {
		JsonObject json = new JsonObject();
		json.addProperty("title", this.title);
		json.addProperty("subtitle", this.subtitle);
		json.addProperty("drawSectionListText", true);
		json.addProperty("coverColor", this.coverColour);
		return json;
	}

	public JsonArray serializeIndex() {
		JsonArray array = new JsonArray();
		for (SectionBuilder sections : this.sections) {
			JsonObject json = new JsonObject();
			array.add(json);
			json.addProperty("name", sections.section);
			json.addProperty("data", "sections/" + sections.section + ".json");
			JsonObject item = new JsonObject();
			json.add("icon", item);
			JsonObject icon = new JsonObject();
			item.add("item", icon);
			icon.addProperty("id", Registry.ITEM.getKey(sections.icon).toString());
		}
		return array;
	}

	public static class SectionBuilder {
		public final String section;
		public final Item icon;
		public final ArrayList<PageBuilder> pages = new ArrayList<>();

		public SectionBuilder(String section, Item icon) {
			this.section = section;
			this.icon = icon;
		}

		public SectionBuilder(String section, Block icon) {
			this.section = section;
			this.icon = icon.asItem();
		}

		public JsonArray serializeSection() {
			JsonArray array = new JsonArray();
			for (PageBuilder pages : this.pages) {
				JsonObject json = new JsonObject();
				array.add(json);
				json.addProperty("name", pages.page);
				json.addProperty("type", pages.type);
				json.addProperty("data", this.section + "/" + pages.page + ".json");
			}
			return array;
		}

		public SectionBuilder addPages(PageBuilder page) {
			this.pages.add(page);
			return this;
		}

		public static class PageBuilder {
			public final String page;
			public final String type;
			public final ArrayList<Pair<String, Boolean>> paragraphs = new ArrayList<>();
			public boolean hasTitle;
			public String title;

			public PageBuilder(String page, String type) {
				this.page = page;
				this.type = type;
			}

			public JsonObject serializePage() {
				JsonObject json = new JsonObject();
				if (this.hasTitle) {
					json.addProperty("title", this.title);
				}
				JsonArray array = new JsonArray();
				json.add("text", array);
				for (Pair<String, Boolean> paragraphs : this.paragraphs) {
					JsonObject text = new JsonObject();
					array.add(text);
					text.addProperty("text", paragraphs.getFirst());
					if (paragraphs.getSecond()) {
						text.addProperty("paragraph", true);
					}
				}
				return json;
			}

			public PageBuilder addTitle(String title) {
				this.title = title;
				this.hasTitle = true;
				return this;
			}

			public PageBuilder addFirst(String text) {
				return this.addParagraph(text, false);
			}

			public PageBuilder addNext(String text) {
				return this.addParagraph(text, true);
			}

			public PageBuilder addParagraph(String text, boolean isParagraph) {
				this.paragraphs.add(Pair.of(text, isParagraph));
				return this;
			}

			public static class TextPageBuilder extends PageBuilder {
				public TextPageBuilder(String page) {
					super(page, "text");
				}
			}

			public static class RecipePageBuilder extends PageBuilder {
				private final String recipe;

				public RecipePageBuilder(String page, String recipe) {
					super(page, "crafting");
					this.recipe = recipe;
				}

				@Override
				public JsonObject serializePage() {
					JsonObject json = new JsonObject();
					if (this.hasTitle) {
						json.addProperty("title", this.title);
					}
					json.addProperty("recipe", this.recipe);
					return json;
				}
			}

			public static class PictureAndDescriptionPageBuilder extends PageBuilder {
				private final String picture;
				private final int width;
				private final int height;

				public PictureAndDescriptionPageBuilder(String page, String picture, int width, int height) {
					super(page, "image_with_text_below");
					this.picture = picture;
					this.width = width;
					this.height = height;
				}

				@Override
				public JsonObject serializePage() {
					JsonObject json = new JsonObject();
					if (this.hasTitle) {
						json.addProperty("title", this.title);
					}
					JsonObject image = new JsonObject();
					json.add("image", image);
					image.addProperty("file", this.picture);
					image.addProperty("width", this.width);
					image.addProperty("height", this.height);
					JsonArray array = new JsonArray();
					json.add("text", array);
					for (Pair<String, Boolean> paragraphs : this.paragraphs) {
						JsonObject text = new JsonObject();
						array.add(text);
						text.addProperty("text", paragraphs.getFirst());
						if (paragraphs.getSecond()) {
							text.addProperty("paragraph", true);
						}
					}
					return json;
				}
			}
		}
	}
}
