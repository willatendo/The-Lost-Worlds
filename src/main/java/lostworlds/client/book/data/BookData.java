package lostworlds.client.book.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import lostworlds.client.book.BookHelper;
import lostworlds.client.book.BookLoader;
import lostworlds.client.book.data.content.ContentError;
import lostworlds.client.book.repository.BookRepository;
import lostworlds.client.book.screen.book.BookScreen;
import lostworlds.client.book.transformer.BookTransformer;
import lostworlds.server.network.LostWorldsNetwork;
import lostworlds.server.network.book.DropLecternBookPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public class BookData implements DataItem {
	public final Minecraft minecraft = Minecraft.getInstance();
	public transient int unnamedSectionCounter = 0;
	public transient ArrayList<SectionData> sections = new ArrayList<>();
	public transient AppearanceData appearance = new AppearanceData();
	public transient HashMap<String, String> strings = new HashMap<>();
	public transient Font fontRenderer = this.minecraft.font;
	private transient boolean initialized = false;

	protected final transient ArrayList<BookTransformer> transformers = new ArrayList<>();

	private final ArrayList<BookRepository> repositories;

	public BookData(BookRepository... repositories) {
		this.repositories = new ArrayList<>(Arrays.asList(repositories));
	}

	public void reset() {
		this.initialized = false;
	}

	@Override
	public void load() {
		if (this.initialized) {
			return;
		}

		try {
			this.initialized = true;
			this.sections.clear();
			this.appearance = new AppearanceData();

			for (BookRepository repo : this.repositories) {
				try {
					List<SectionData> repoContents = repo.getSections();
					this.sections.addAll(repoContents.stream().filter(SectionData::isConditionMet).toList());

					for (SectionData section : repoContents) {
						section.source = repo;
					}
				} catch (Exception e) {
					SectionData error = new SectionData();
					error.name = "errorenous";
					PageData page = new PageData(true);
					page.name = "errorenous";
					page.content = new ContentError("Failed to load repository " + repo.toString() + ".", e);
					error.pages.add(page);
					this.sections.add(error);

					e.printStackTrace();
				}

				ResourceLocation appearanceLocation = repo.getResourceLocation("appearance.json");

				if (repo.resourceExists(appearanceLocation)) {
					try {
						this.appearance = BookLoader.getGson().fromJson(repo.resourceToString(repo.getResource(appearanceLocation)), AppearanceData.class);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				this.appearance.load();

				ResourceLocation languageLocation = repo.getResourceLocation("language.lang");

				if (repo.resourceExists(languageLocation)) {
					try {
						Resource resource = repo.getResource(languageLocation);
						if (resource != null) {
							BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
							String next = br.readLine();

							while (next != null) {
								if (!next.startsWith("//") && next.contains("=")) {
									String key = next.substring(0, next.indexOf('='));
									String value = next.substring(next.indexOf('=') + 1);

									this.strings.put(key, value);
								}

								next = br.readLine();
							}
						}
					} catch (Exception ignored) {
					}
				}
			}

			for (int i = 0; i < this.sections.size(); i++) {
				SectionData section = this.sections.get(i);

				if (section.name == null) {
					continue;
				}

				List<SectionData> matchingSections = this.sections.stream().filter(sect -> section.name.equalsIgnoreCase(sect.name)).collect(Collectors.toList());

				if (matchingSections.size() < 2) {
					continue;
				}

				LinkedSectionData linkedSection = new LinkedSectionData();

				for (SectionData match : matchingSections) {
					linkedSection.addSection(match);

					if (match != section) {
						this.sections.remove(match);
					}
				}

				this.sections.set(i, linkedSection);
			}

			for (SectionData section : this.sections) {
				if (section.source == null) {
					section.source = BookRepository.DUMMY;
				}

				section.parent = this;
				section.load();
			}

			for (BookTransformer transformer : this.transformers) {
				transformer.transform(this);
			}

			for (SectionData section : this.sections) {
				if (section.source == null) {
					section.source = BookRepository.DUMMY;
				}

				if (section.parent == null) {
					section.parent = this;
					section.load();
				}
			}
		} catch (Exception e) {
			this.sections.clear();
			SectionData section = new SectionData(true);
			section.name = "errorenous";
			PageData page = new PageData(true);
			page.name = "errorenous";
			page.content = new ContentError("Failed to load the book due to an unexpected error.", e);
			section.pages.add(page);
			this.sections.add(section);

			e.printStackTrace();
		}
	}

	@Nullable
	public SectionData findSection(String name) {
		return this.findSection(name, null);
	}

	@Nullable
	public SectionData findSection(String name, @Nullable BookScreen.AdvancementCache advancementCache) {
		for (SectionData section : this.sections) {
			section.update(advancementCache);

			if (section.name.equals(name.toLowerCase())) {
				return section.isUnlocked(advancementCache) ? section : null;
			}
		}

		return null;
	}

	public int getFirstPageNumber(SectionData section, @Nullable BookScreen.AdvancementCache advancementCache) {
		int pages = 0;

		for (SectionData sect : this.sections) {
			sect.update(advancementCache);

			if (section == sect) {
				return section.isUnlocked(advancementCache) ? pages + 1 : -1;
			}

			if (!sect.isUnlocked(advancementCache)) {
				continue;
			}

			pages += sect.getPageCount();
		}

		return -1;
	}

	@Nullable
	public PageData findPage(int number, @Nullable BookScreen.AdvancementCache advancementCache) {
		if (number < 0) {
			return null;
		}

		int pages = 0;

		for (SectionData section : this.sections) {
			section.update(advancementCache);

			if (!section.isUnlocked(advancementCache)) {
				continue;
			}

			if (pages + section.getPageCount() > number) {
				return section.pages.get(number - pages);
			} else {
				pages += section.getPageCount();
			}
		}

		return null;
	}

	@Nullable
	public PageData findPage(String location, @Nullable BookScreen.AdvancementCache advancementCache) {
		return this.findPage(this.findPageNumber(location, advancementCache), advancementCache);
	}

	public int findPageNumber(String location) {
		return this.findPageNumber(location, null);
	}

	public int findPageNumber(String location, @Nullable BookScreen.AdvancementCache advancementCache) {
		location = location.toLowerCase();

		int pages = 0;

		if (!location.contains(".")) {
			return -1;
		}

		String sectionName = location.substring(0, location.indexOf('.'));
		String pageName = location.substring(location.indexOf('.') + 1);

		for (SectionData section : this.sections) {
			section.update(advancementCache);

			if (!section.isUnlocked(advancementCache)) {
				continue;
			}

			if (!sectionName.equals(section.name)) {
				pages += section.getPageCount();
				continue;
			}

			for (PageData page : section.pages) {
				if (!pageName.equals(page.name)) {
					pages++;
					continue;
				}

				return pages + 1;
			}
		}

		return -1;
	}

	public int getPageCount(@Nullable BookScreen.AdvancementCache advancementCache) {
		int pages = 0;
		for (SectionData section : this.sections) {
			section.update(advancementCache);

			pages += section.isUnlocked(advancementCache) ? section.getPageCount() : 0;
		}
		return pages;
	}

	public int getFullPageCount(@Nullable BookScreen.AdvancementCache advancementCache) {
		return (int) Math.ceil((this.getPageCount(advancementCache) - 1) / 2F) + 1;
	}

	public List<SectionData> getVisibleSections(@Nullable BookScreen.AdvancementCache advancementCache) {
		List<SectionData> visible = new ArrayList<>();

		for (SectionData section : this.sections) {
			if (section.isUnlocked(advancementCache) || !section.hideWhenLocked) {
				visible.add(section);
			}
		}

		return visible;
	}

	public String translate(String string) {
		String out = this.strings.get(string);
		return out != null ? out : string;
	}

	public void openGui(Component title, String page, @Nullable Consumer<String> pageUpdater) {
		this.openGui(title, page, pageUpdater, null);
	}

	public void openGui(Component title, String page, @Nullable Consumer<String> pageUpdater, @Nullable Consumer<?> bookPickup) {
		this.load();
		Minecraft.getInstance().setScreen(new BookScreen(title, this, page, pageUpdater, bookPickup));
	}

	public void openGui(InteractionHand hand, ItemStack stack) {
		String page = BookHelper.getCurrentSavedPage(stack);
		openGui(stack.getHoverName(), page, newPage -> BookLoader.updateSavedPage(Minecraft.getInstance().player, hand, newPage));
	}

	public void openGui(BlockPos pos, ItemStack stack) {
		String page = BookHelper.getCurrentSavedPage(stack);

		Consumer<?> bookPickup = (v) -> LostWorldsNetwork.INSTANCE.network.sendToServer(new DropLecternBookPacket(pos));

		openGui(stack.getHoverName(), page, newPage -> BookLoader.updateSavedPage(pos, newPage), bookPickup);
	}

	public void addRepository(@Nullable BookRepository repository) {
		if (repository != null && !this.repositories.contains(repository)) {
			this.repositories.add(repository);
		}
	}

	public void addTransformer(@Nullable BookTransformer transformer) {
		if (transformer != null && !this.transformers.contains(transformer)) {
			this.transformers.add(transformer);
		}
	}
}
