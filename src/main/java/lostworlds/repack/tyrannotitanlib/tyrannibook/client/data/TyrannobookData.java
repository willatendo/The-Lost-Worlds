package lostworlds.repack.tyrannotitanlib.tyrannibook.client.data;

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

import lostworlds.repack.tyrannotitanlib.tyrannibook.TyrannoUtils;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.TyrannobookHelper;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.TyrannobookLoader;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.TyrannobookTransformer;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.content.ContentError;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.data.element.ItemStackData;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.repository.TyrannobookRepository;
import lostworlds.repack.tyrannotitanlib.tyrannibook.client.screen.TyrannobookScreen;
import lostworlds.repack.tyrannotitanlib.tyranninetwork.Tyranninetwork;
import lostworlds.repack.tyrannotitanlib.tyranninetwork.packets.DropLecternBookPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.IResource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TyrannobookData implements DataItem {
	public transient int unnamedSectionCounter = 0;
	public transient ArrayList<SectionData> sections = new ArrayList<>();
	public transient AppearanceData appearance = new AppearanceData();
	public transient ArrayList<ItemStackData.ItemLink> itemLinks = new ArrayList<>();
	public transient HashMap<String, String> strings = new HashMap<>();
	public transient FontRenderer fontRenderer;
	private transient boolean initialized = false;

	protected final transient ArrayList<TyrannobookTransformer> transformers = new ArrayList<>();

	private final ArrayList<TyrannobookRepository> repositories;

	public TyrannobookData(TyrannobookRepository... repositories) {
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

		TyrannoUtils.LOGGER.debug("Started loading book...");

		try {
			this.initialized = true;
			this.sections.clear();
			this.appearance = new AppearanceData();
			this.itemLinks.clear();

			for (TyrannobookRepository repo : this.repositories) {
				try {
					List<SectionData> repoContents = repo.getSections();
					this.sections.addAll(repoContents);

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
						this.appearance = TyrannobookLoader.GSON.fromJson(repo.resourceToString(repo.getResource(appearanceLocation)), AppearanceData.class);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				this.appearance.load();

				ResourceLocation itemLinkLocation = repo.getResourceLocation("items.json");

				if (repo.resourceExists(itemLinkLocation)) {
					try {
						this.itemLinks = new ArrayList<>(Arrays.asList(TyrannobookLoader.GSON.fromJson(repo.resourceToString(repo.getResource(itemLinkLocation)), ItemStackData.ItemLink[].class)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				ResourceLocation languageLocation = repo.getResourceLocation("language.lang");

				if (repo.resourceExists(languageLocation)) {
					try {
						IResource resource = repo.getResource(languageLocation);
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
					section.source = TyrannobookRepository.DUMMY;
				}

				section.parent = this;
				section.load();
			}

			for (TyrannobookTransformer transformer : this.transformers) {
				transformer.transform(this);
			}

			for (SectionData section : this.sections) {
				if (section.source == null) {
					section.source = TyrannobookRepository.DUMMY;
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
	public SectionData findSection(String name, @Nullable TyrannobookScreen.AdvancementCache advancementCache) {
		for (SectionData section : this.sections) {
			section.update(advancementCache);

			if (section.name.equals(name.toLowerCase())) {
				return section.isUnlocked(advancementCache) ? section : null;
			}
		}

		return null;
	}

	public int getFirstPageNumber(SectionData section, @Nullable TyrannobookScreen.AdvancementCache advancementCache) {
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
	public PageData findPage(int number, @Nullable TyrannobookScreen.AdvancementCache advancementCache) {
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
	public PageData findPage(String location, @Nullable TyrannobookScreen.AdvancementCache advancementCache) {
		return this.findPage(this.findPageNumber(location, advancementCache), advancementCache);
	}

	public int findPageNumber(String location) {
		return this.findPageNumber(location, null);
	}

	public int findPageNumber(String location, @Nullable TyrannobookScreen.AdvancementCache advancementCache) {
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

	public int getPageCount(@Nullable TyrannobookScreen.AdvancementCache advancementCache) {
		int pages = 0;
		for (SectionData section : this.sections) {
			section.update(advancementCache);

			pages += section.isUnlocked(advancementCache) ? section.getPageCount() : 0;
		}
		return pages;
	}

	public int getFullPageCount(@Nullable TyrannobookScreen.AdvancementCache advancementCache) {
		return (int) Math.ceil((this.getPageCount(advancementCache) - 1) / 2F) + 1;
	}

	public String getItemAction(ItemStackData item) {
		for (ItemStackData.ItemLink link : this.itemLinks) {
			if (item.id.equals(link.item.id) && (!link.damageSensitive)) {
				return link.action;
			}
		}

		return "";
	}

	public List<SectionData> getVisibleSections(@Nullable TyrannobookScreen.AdvancementCache advancementCache) {
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

	public void openGui(ITextComponent title, String page, @Nullable Consumer<String> pageUpdater) {
		this.openGui(title, page, pageUpdater, null);
	}

	public void openGui(ITextComponent title, String page, @Nullable Consumer<String> pageUpdater, @Nullable Consumer<?> bookPickup) {
		this.load();
		Minecraft.getInstance().setScreen(new TyrannobookScreen(title, this, page, pageUpdater, bookPickup));
	}

	public void openGui(Hand hand, ItemStack stack) {
		String page = TyrannobookHelper.getCurrentSavedPage(stack);
		openGui(stack.getDisplayName(), page, newPage -> TyrannobookLoader.updateSavedPage(Minecraft.getInstance().player, hand, newPage));
	}

	@Deprecated
	public void openGui(ITextComponent title, @Nullable ItemStack item) {
		if (item == null) {
			openGui(title, "", newPage -> TyrannobookLoader.updateSavedPage(Minecraft.getInstance().player, Hand.MAIN_HAND, newPage));
		} else {
			openGui(Hand.MAIN_HAND, item);
		}
	}

	public void openGui(BlockPos pos, ItemStack stack) {
		String page = TyrannobookHelper.getCurrentSavedPage(stack);

		Consumer<?> bookPickup = (v) -> {
			Tyranninetwork.INSTANCE.network.sendToServer(new DropLecternBookPacket(pos));
		};

		openGui(stack.getDisplayName(), page, newPage -> TyrannobookLoader.updateSavedPage(pos, newPage), bookPickup);
	}

	public void addRepository(@Nullable TyrannobookRepository repository) {
		if (repository != null && !this.repositories.contains(repository)) {
			this.repositories.add(repository);
		}
	}

	public void addTransformer(@Nullable TyrannobookTransformer transformer) {
		if (transformer != null && !this.transformers.contains(transformer)) {
			this.transformers.add(transformer);
		}
	}
}
