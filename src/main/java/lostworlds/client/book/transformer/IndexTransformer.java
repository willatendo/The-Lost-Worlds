package lostworlds.client.book.transformer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lostworlds.client.book.data.PageData;
import lostworlds.client.book.data.SectionData;
import lostworlds.client.book.data.BookData;
import lostworlds.client.book.data.content.BlankContent;
import lostworlds.client.book.data.content.SectionListContent;
import lostworlds.client.book.screen.book.BookScreen;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.resources.ResourceLocation;

public class IndexTransformer extends BookTransformer {
	public static final IndexTransformer INSTANCE = new IndexTransformer();
	public static final ResourceLocation INDEX_EXTRA_DATA = LostWorldsUtils.rL("index");

	private static final Set<ResourceLocation> hiddenPageTypes = new HashSet<>();

	private static int ceilingDivide(int value, int divisor) {
		int result = value / divisor;
		if (value % divisor != 0)
			result++;
		return result;
	}

	private List<SectionData> getVisibleSections(@Nullable BookScreen.AdvancementCache advancementCache, BookData book) {
		return filterHiddenSections(book.getVisibleSections(advancementCache));
	}

	@Override
	public void transform(BookData book) {
		int sectionsPerPage = book.appearance.drawFourColumnIndex ? 12 : 9;
		SectionData index = new SectionData(true) {
			@Override
			public void update(@Nullable BookScreen.AdvancementCache advancementCache) {
				this.pages.clear();

				List<SectionData> visibleSections = getVisibleSections(advancementCache, book);
				if (visibleSections.isEmpty()) {
					return;
				}
				visibleSections.remove(0);
				PageData[] pages = new PageData[ceilingDivide(visibleSections.size(), sectionsPerPage)];
				for (int i = 0; i < pages.length; i++) {
					pages[i] = new PageData(true);

					pages[i].name = "page" + (i + 1);

					SectionListContent content = new SectionListContent();
					pages[i].content = content;

					int pageStart = i * sectionsPerPage;
					for (int j = pageStart; j - pageStart < 16 && j < visibleSections.size(); j++) {
						content.addSection(visibleSections.get(j));
					}
				}

				this.pages.addAll(Arrays.asList(pages));
			}
		};
		List<SectionData> visibleSections = getVisibleSections(null, book);
		if (!visibleSections.isEmpty()) {
			PageData[] pages = new PageData[ceilingDivide(visibleSections.size() - 1, sectionsPerPage)];
			for (int i = 0; i < pages.length; i++) {
				pages[i] = new PageData(true);
				pages[i].name = "page" + (i + 1);
				pages[i].content = new BlankContent();
			}
			index.pages.addAll(Arrays.asList(pages));
		}

		index.name = "index";
		book.sections.add(0, index);
	}

	public static boolean isPageHidden(PageData page) {
		if (hiddenPageTypes.contains(page.type)) {
			return true;
		}

		if (page.extraData.containsKey(INDEX_EXTRA_DATA)) {
			JsonElement data = page.extraData.get(INDEX_EXTRA_DATA);
			if (data.isJsonObject()) {
				JsonObject dataObject = data.getAsJsonObject();
				if (dataObject.has("hidden")) {
					JsonElement hidden = dataObject.get("hidden");
					if (hidden.isJsonPrimitive() && hidden.getAsJsonPrimitive().isBoolean()) {
						return hidden.getAsBoolean();
					}
				}
			}
		}

		return false;
	}

	public static boolean isSectionHidden(SectionData section) {
		if (section.extraData.containsKey(INDEX_EXTRA_DATA)) {
			JsonElement data = section.extraData.get(INDEX_EXTRA_DATA);
			if (data.isJsonObject()) {
				JsonObject dataObject = data.getAsJsonObject();
				if (dataObject.has("hidden")) {
					JsonElement hidden = dataObject.get("hidden");
					if (hidden.isJsonPrimitive() && hidden.getAsJsonPrimitive().isBoolean()) {
						return hidden.getAsBoolean();
					}
				}
			}
		}

		return false;
	}

	public static List<PageData> filterHiddenPages(List<PageData> pages) {
		return pages.stream().filter(page -> !isPageHidden(page)).collect(Collectors.toList());
	}

	public static List<SectionData> filterHiddenSections(List<SectionData> sections) {
		return sections.stream().filter(section -> !isSectionHidden(section)).collect(Collectors.toList());
	}

	public static void addHiddenPageType(ResourceLocation pageType) {
		hiddenPageTypes.add(pageType);
	}
}
