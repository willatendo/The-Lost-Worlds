package lostworlds.client.book.data;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;

import lostworlds.client.book.BookLoader;
import lostworlds.client.book.data.content.ContentError;
import lostworlds.client.book.data.content.PageContent;
import lostworlds.client.book.data.element.DataElement;
import lostworlds.client.book.repository.BookRepository;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.TrueCondition;

public class PageData implements DataItem, Conditional {
	public String name = null;
	public ResourceLocation type = LostWorldsUtils.rL("blank");
	public String data = "";
	public float scale = 1.0F;
	public ICondition condition = TrueCondition.INSTANCE;

	public Map<ResourceLocation, JsonElement> extraData = Collections.emptyMap();

	public transient SectionData parent;
	public transient BookRepository source;
	public transient PageContent content;

	public PageData() {
		this(false);
	}

	public PageData(boolean custom) {
		if (custom) {
			this.data = "no-load";
		}
	}

	public String translate(String string) {
		return this.parent.translate(string);
	}

	@Override
	public void load() {
		if (this.name == null) {
			this.name = "page" + this.parent.unnamedPageCounter++;
		}

		this.name = this.name.toLowerCase();

		Class<? extends PageContent> ctype = BookLoader.getPageType(this.type);

		if (!this.data.isEmpty() && !this.data.equals("no-load")) {
			Resource pageInfo = this.source.getResource(this.source.getResourceLocation(this.data));
			if (pageInfo != null) {
				String data = this.source.resourceToString(pageInfo);
				if (!data.isEmpty()) {
					PageTypeOverrider overrider = BookLoader.getGson().fromJson(data, PageTypeOverrider.class);
					if (overrider.type != null) {
						Class<? extends PageContent> overriddenType = BookLoader.getPageType(overrider.type);
						if (overriddenType != null) {
							ctype = BookLoader.getPageType(overrider.type);
							this.type = overrider.type;
						}
					}

					if (ctype != null) {
						try {
							this.content = BookLoader.getGson().fromJson(data, ctype);
						} catch (Exception e) {
							this.content = new ContentError("Failed to create a page of type \"" + this.type + "\", perhaps the page file \"" + this.data + "\" is missing or invalid?", e);
							e.printStackTrace();
						}
					} else {
						this.content = new ContentError("Failed to create a page of type \"" + this.type + "\" as it is not registered.");
					}
				}
			}
		}

		if (this.content == null) {
			if (ctype != null) {
				try {
					this.content = ctype.getDeclaredConstructor().newInstance();
				} catch (InstantiationException | IllegalAccessException | NullPointerException | NoSuchMethodException | InvocationTargetException e) {
					this.content = new ContentError("Failed to create a page of type \"" + this.type + "\".", e);
					e.printStackTrace();
				}
			} else {
				this.content = new ContentError("Failed to create a page of type \"" + this.type + "\" as it is not registered.");
			}
		}

		try {
			this.content.parent = this;
			this.content.load();
		} catch (Exception e) {
			this.content = new ContentError("Failed to load page " + this.parent.name + "." + this.name + ".", e);
			e.printStackTrace();
		}

		this.content.source = this.source;

		for (Field f : this.content.getClass().getFields()) {
			this.processField(f);
		}
	}

	private void processField(Field field) {
		field.setAccessible(true);

		if (Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
			return;
		}

		try {
			Object o = field.get(this.content);
			if (o != null) {
				this.processObject(o, 0);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void processObject(@Nullable Object object, int depth) {
		if (depth > 4 || object == null)
			return;

		Class<?> c = object.getClass();
		boolean isArray = c.isArray();

		if (!isArray) {
			if (DataElement.class.isAssignableFrom(c)) {
				((DataElement) object).load(this.source);
			}
			return;
		}

		for (int i = 0; i < Array.getLength(object); i++) {
			this.processObject(Array.get(object, i), depth + 1);
		}
	}

	public String getTitle() {
		String title = this.parent.parent.strings.get(this.parent.name + "." + this.name);
		if (title != null) {
			return title;
		}
		title = content.getTitle();
		if (title != null && !title.isEmpty()) {
			return title;
		}
		return this.name;
	}

	@SuppressWarnings("removal")
	@Override
	public boolean isConditionMet() {
		return condition.test();
	}

	private static class PageTypeOverrider {
		public ResourceLocation type;
	}
}
