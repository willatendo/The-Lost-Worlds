package lostworlds.repack.tyrannotitanlib.tyrannibook.client.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import lostworlds.repack.tyrannotitanlib.tyrannibook.client.TyrannobookLoader;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentError;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.ImageData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.repository.TyrannobookRepository;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.screen.TyrannobookScreen;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.IResource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SectionData implements DataItem {
	public String name = null;
	public ImageData icon = new ImageData();
	public Set<String> requirements = Sets.newHashSet();
	public boolean hideWhenLocked = false;
	public String data = "";

	public transient int unnamedPageCounter = 0;
	public transient TyrannobookData parent;
	public transient TyrannobookRepository source;
	public transient ArrayList<PageData> pages = new ArrayList<>();

	public SectionData() {
		this(false);
	}

	public SectionData(boolean custom) {
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
			this.name = "section" + this.parent.unnamedSectionCounter++;
		}

		this.name = this.name.toLowerCase();

		if (!this.data.equals("no-load")) {
			IResource pagesInfo = this.source.getResource(this.source.getResourceLocation(this.data));
			if (pagesInfo != null) {
				String data = this.source.resourceToString(pagesInfo);
				if (!data.isEmpty()) {
					try {
						this.pages = this.getPages(data);
					} catch (Exception e) {
						this.pages = new ArrayList<>();
						PageData pdError = new PageData(true);
						pdError.name = "errorrenous";
						pdError.content = new ContentError("Failed to load section " + this.name + ".", e);
						this.pages.add(pdError);

						e.printStackTrace();
					}
				}
			}
		}

		for (PageData page : this.pages) {
			page.parent = this;
			page.source = this.source;
			page.load();
		}

		this.icon.load(this.source);
	}

	protected ArrayList<PageData> getPages(String data) {
		return new ArrayList<>(Arrays.asList(TyrannobookLoader.GSON.fromJson(data, PageData[].class)));
	}

	public void update(@Nullable TyrannobookScreen.AdvancementCache advancementCache) {
	}

	public String getTitle() {
		String title = this.parent.strings.get(this.name);
		return title == null ? this.name : title;
	}

	public int getPageCount() {
		return this.pages.size();
	}

	public boolean isUnlocked(@Nullable TyrannobookScreen.AdvancementCache advancementCache) {
		if (advancementCache == null || this.requirements == null || this.requirements.size() == 0) {
			return true;
		}

		for (String achievement : this.requirements) {
			if (!requirementSatisfied(achievement, advancementCache)) {
				return false;
			}
		}

		return true;
	}

	public static boolean requirementSatisfied(String requirement, @Nullable TyrannobookScreen.AdvancementCache advancementCache) {
		if (advancementCache == null) {
			return true;
		}

		AdvancementProgress progress = advancementCache.getProgress(requirement);

		return progress != null && progress.isDone();
	}
}
