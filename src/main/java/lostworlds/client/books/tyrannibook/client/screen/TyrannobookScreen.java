package lostworlds.client.books.tyrannibook.client.screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import org.lwjgl.glfw.GLFW;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import lostworlds.client.books.tyrannibook.client.TyrannibookTextures;
import lostworlds.client.books.tyrannibook.client.action.StringActionProcessor;
import lostworlds.client.books.tyrannibook.client.data.PageData;
import lostworlds.client.books.tyrannibook.client.data.TyrannobookData;
import lostworlds.client.books.tyrannibook.client.data.element.ItemStackData;
import lostworlds.client.books.tyrannibook.client.data.element.TyrannobookElement;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.multiplayer.ClientAdvancementManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TyrannobookScreen extends Screen {
	public static boolean debug = false;

	public static final int TEX_SIZE = 512;

	public static int PAGE_MARGIN = 8;

	public static int PAGE_PADDING_TOP = 4;
	public static int PAGE_PADDING_BOT = 4;
	public static int PAGE_PADDING_LEFT = 8;
	public static int PAGE_PADDING_RIGHT = 0;

	public static float PAGE_SCALE = 1f;
	public static int PAGE_WIDTH_UNSCALED = 206;
	public static int PAGE_HEIGHT_UNSCALED = 200;

	public static int PAGE_WIDTH;
	public static int PAGE_HEIGHT;

	static {
		initWidthsAndHeights();
	}

	private ArrowButton previousArrow, nextArrow, backArrow, indexArrow;

	public final TyrannobookData book;
	@Nullable
	private Consumer<String> pageUpdater;
	@Nullable
	private Consumer<?> bookPickup;

	private int page = -1;
	private int oldPage = -2;
	private final ArrayList<TyrannobookElement> leftElements = new ArrayList<>();
	private final ArrayList<TyrannobookElement> rightElements = new ArrayList<>();

	public AdvancementCache advancementCache;

	private double[] lastClick;
	private double[] lastDrag;

	public static void initWidthsAndHeights() {
		PAGE_WIDTH = (int) ((PAGE_WIDTH_UNSCALED - (PAGE_PADDING_LEFT + PAGE_PADDING_RIGHT + PAGE_MARGIN + PAGE_MARGIN)) / PAGE_SCALE);
		PAGE_HEIGHT = (int) ((PAGE_HEIGHT_UNSCALED - (PAGE_PADDING_TOP + PAGE_PADDING_BOT + PAGE_MARGIN + PAGE_MARGIN)) / PAGE_SCALE);
	}

	public TyrannobookScreen(ITextComponent title, TyrannobookData book, String page, @Nullable Consumer<String> pageUpdater, @Nullable Consumer<?> bookPickup) {
		super(title);
		this.book = book;
		this.pageUpdater = pageUpdater;
		this.bookPickup = bookPickup;

		this.minecraft = Minecraft.getInstance();
		this.font = this.minecraft.font;

		initWidthsAndHeights();

		this.advancementCache = new AdvancementCache();
		if (this.minecraft.player != null) {
			this.minecraft.player.connection.getAdvancements().setListener(this.advancementCache);
		}
		this.openPage(book.findPageNumber(page, this.advancementCache));
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		if (this.minecraft == null) {
			return;
		}
		initWidthsAndHeights();
		FontRenderer fontRenderer = this.book.fontRenderer;
		if (fontRenderer == null) {
			fontRenderer = this.minecraft.font;
		}

		if (debug) {
			fill(stack, 0, 0, fontRenderer.width("DEBUG") + 4, fontRenderer.lineHeight + 4, 0x55000000);
			fontRenderer.draw(stack, "DEBUG", 2, 2, 0xFFFFFFFF);
		}

		RenderSystem.enableAlphaTest();
		RenderSystem.enableBlend();

		RenderSystem.pushMatrix();
		RenderSystem.color3f(1F, 1F, 1F);

		float coverR = ((this.book.appearance.coverColor >> 16) & 0xff) / 255.F;
		float coverG = ((this.book.appearance.coverColor >> 8) & 0xff) / 255.F;
		float coverB = (this.book.appearance.coverColor & 0xff) / 255.F;

		TextureManager render = this.minecraft.textureManager;

		if (this.page == -1) {
			render.bind(TyrannibookTextures.TEX_BOOKFRONT);
			RenderHelper.turnOff();

			RenderSystem.color3f(coverR, coverG, coverB);
			blit(stack, this.width / 2 - PAGE_WIDTH_UNSCALED / 2, this.height / 2 - PAGE_HEIGHT_UNSCALED / 2, 0, 0, PAGE_WIDTH_UNSCALED, PAGE_HEIGHT_UNSCALED, TEX_SIZE, TEX_SIZE);
			RenderSystem.color3f(1F, 1F, 1F);

			if (!this.book.appearance.title.isEmpty()) {
				blit(stack, this.width / 2 - PAGE_WIDTH_UNSCALED / 2, this.height / 2 - PAGE_HEIGHT_UNSCALED / 2, 0, PAGE_HEIGHT_UNSCALED, PAGE_WIDTH_UNSCALED, PAGE_HEIGHT_UNSCALED, TEX_SIZE, TEX_SIZE);

				RenderSystem.pushMatrix();

				float scale = fontRenderer.width(this.book.appearance.title) <= 67 ? 2.5F : 2F;

				RenderSystem.scalef(scale, scale, 1F);
				fontRenderer.drawShadow(stack, this.book.appearance.title, (this.width / 2) / scale + 3 - fontRenderer.width(this.book.appearance.title) / 2, (this.height / 2 - fontRenderer.lineHeight / 2) / scale - 4, 0xAE8000);
				RenderSystem.popMatrix();
			}

			if (!this.book.appearance.subtitle.isEmpty()) {
				RenderSystem.pushMatrix();
				RenderSystem.scalef(1.5F, 1.5F, 1F);
				fontRenderer.drawShadow(stack, this.book.appearance.subtitle, (this.width / 2) / 1.5F + 7 - fontRenderer.width(this.book.appearance.subtitle) / 2, (this.height / 2 + 100 - fontRenderer.lineHeight * 2) / 1.5F, 0xAE8000);
				RenderSystem.popMatrix();
			}
		} else {
			render.bind(TyrannibookTextures.TEX_BOOK);
			RenderHelper.turnOff();

			RenderSystem.color3f(coverR, coverG, coverB);
			blit(stack, this.width / 2 - PAGE_WIDTH_UNSCALED, this.height / 2 - PAGE_HEIGHT_UNSCALED / 2, 0, 0, PAGE_WIDTH_UNSCALED * 2, PAGE_HEIGHT_UNSCALED, TEX_SIZE, TEX_SIZE);

			RenderSystem.color3f(1F, 1F, 1F);

			if (this.page != 0) {
				blit(stack, this.width / 2 - PAGE_WIDTH_UNSCALED, this.height / 2 - PAGE_HEIGHT_UNSCALED / 2, 0, PAGE_HEIGHT_UNSCALED, PAGE_WIDTH_UNSCALED, PAGE_HEIGHT_UNSCALED, TEX_SIZE, TEX_SIZE);

				RenderSystem.pushMatrix();
				this.drawerTransform(false);

				RenderSystem.scalef(PAGE_SCALE, PAGE_SCALE, 1F);

				if (this.book.appearance.drawPageNumbers) {
					String pNum = (this.page - 1) * 2 + 2 + "";
					fontRenderer.draw(stack, pNum, PAGE_WIDTH / 2 - fontRenderer.width(pNum) / 2, PAGE_HEIGHT - 10, 0xFFAAAAAA);
				}

				int mX = this.getMouseX(false);
				int mY = this.getMouseY();

				for (int i = 0; i < this.leftElements.size(); i++) {
					TyrannobookElement element = this.leftElements.get(i);

					RenderSystem.color4f(1F, 1F, 1F, 1F);
					element.draw(stack, mX, mY, partialTicks, fontRenderer);
				}

				for (int i = 0; i < this.leftElements.size(); i++) {
					TyrannobookElement element = this.leftElements.get(i);

					RenderSystem.color4f(1F, 1F, 1F, 1F);
					element.drawOverlay(stack, mX, mY, partialTicks, fontRenderer);
				}

				RenderSystem.popMatrix();
			}

			render.bind(TyrannibookTextures.TEX_BOOK);
			RenderSystem.color4f(1F, 1F, 1F, 1F);
			RenderHelper.turnOff();

			int fullPageCount = this.book.getFullPageCount(this.advancementCache);
			if (this.page < fullPageCount - 1 || this.book.getPageCount(this.advancementCache) % 2 != 0) {
				blit(stack, this.width / 2, this.height / 2 - PAGE_HEIGHT_UNSCALED / 2, PAGE_WIDTH_UNSCALED, PAGE_HEIGHT_UNSCALED, PAGE_WIDTH_UNSCALED, PAGE_HEIGHT_UNSCALED, TEX_SIZE, TEX_SIZE);

				RenderSystem.pushMatrix();
				this.drawerTransform(true);

				RenderSystem.scalef(PAGE_SCALE, PAGE_SCALE, 1F);

				if (this.book.appearance.drawPageNumbers) {
					String pNum = (this.page - 1) * 2 + 3 + "";
					fontRenderer.draw(stack, pNum, PAGE_WIDTH / 2 - fontRenderer.width(pNum) / 2, PAGE_HEIGHT - 10, 0xFFAAAAAA);
				}

				int mX = this.getMouseX(true);
				int mY = this.getMouseY();

				for (int i = 0; i < this.rightElements.size(); i++) {
					TyrannobookElement element = this.rightElements.get(i);

					RenderSystem.color4f(1F, 1F, 1F, 1F);
					element.draw(stack, mX, mY, partialTicks, fontRenderer);
				}

				for (int i = 0; i < this.rightElements.size(); i++) {
					TyrannobookElement element = this.rightElements.get(i);

					RenderSystem.color4f(1F, 1F, 1F, 1F);
					element.drawOverlay(stack, mX, mY, partialTicks, fontRenderer);
				}

				RenderSystem.popMatrix();
			}
		}

		super.render(stack, mouseX, mouseY, partialTicks);

		RenderSystem.popMatrix();
	}

	@Override
	protected void init() {
		super.init();

		this.buttons.clear();
		this.children.clear();

		this.previousArrow = this.addButton(new ArrowButton(-50, -50, ArrowButton.ArrowType.PREV, this.book.appearance.arrowColor, this.book.appearance.arrowColorHover, (button) -> {
			this.page--;
			if (this.page < -1) {
				this.page = -1;
			}

			this.oldPage = -2;
			this.buildPages();
		}));

		this.nextArrow = this.addButton(new ArrowButton(-50, -50, ArrowButton.ArrowType.NEXT, this.book.appearance.arrowColor, this.book.appearance.arrowColorHover, (button) -> {
			this.page++;

			int fullPageCount = this.book.getFullPageCount(this.advancementCache);

			if (this.page >= fullPageCount) {
				this.page = fullPageCount - 1;
			}

			this.oldPage = -2;
			this.buildPages();
		}));

		this.backArrow = this.addButton(new ArrowButton(this.width / 2 - ArrowButton.WIDTH / 2, this.height / 2 + ArrowButton.HEIGHT / 2 + PAGE_HEIGHT / 2, ArrowButton.ArrowType.LEFT, this.book.appearance.arrowColor, this.book.appearance.arrowColorHover, (button) -> {
			if (this.oldPage >= -1) {
				this.page = this.oldPage;
			}

			this.oldPage = -2;
			this.buildPages();
		}));

		this.indexArrow = this.addButton(new ArrowButton(this.width / 2 - PAGE_WIDTH_UNSCALED - ArrowButton.WIDTH / 2, this.height / 2 - PAGE_HEIGHT_UNSCALED / 2, ArrowButton.ArrowType.BACK_UP, this.book.appearance.arrowColor, this.book.appearance.arrowColorHover, (button) -> {
			this.openPage(this.book.findPageNumber("index.page1"));

			this.oldPage = -2;
			this.buildPages();
		}));

		if (this.bookPickup != null) {
			int margin = 10;
			if (this.height / 2 + PAGE_HEIGHT_UNSCALED / 2 + margin + 20 >= this.height) {
				margin = 0;
			}

			this.addButton(new Button(this.width / 2 - 196 / 2, this.height / 2 + PAGE_HEIGHT_UNSCALED / 2 + margin, 196, 20, new TranslationTextComponent("lectern.take_book"), (button) -> {
				this.onClose();
				this.bookPickup.accept(null);
			}));
		}

		this.buildPages();
	}

	@Override
	public void tick() {
		super.tick();

		this.previousArrow.visible = this.page != -1;
		this.nextArrow.visible = this.page + 1 < this.book.getFullPageCount(this.advancementCache);
		this.backArrow.visible = this.oldPage >= -1;

		if (this.page == -1) {
			this.nextArrow.x = this.width / 2 + 80;
			this.indexArrow.visible = false;
		} else {
			this.previousArrow.x = this.width / 2 - 184;
			this.nextArrow.x = this.width / 2 + 165;

			this.indexArrow.visible = this.book.findSection("index") != null && (this.page - 1) * 2 + 2 > this.book.findSection("index").getPageCount();
		}

		this.previousArrow.y = this.height / 2 + 75;
		this.nextArrow.y = this.height / 2 + 75;
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		super.keyPressed(keyCode, scanCode, modifiers);

		switch (keyCode) {
		case GLFW.GLFW_KEY_LEFT:
		case GLFW.GLFW_KEY_A:
			this.page--;
			if (this.page < -1) {
				this.page = -1;
			}

			this.oldPage = -2;
			this.buildPages();
			return true;
		case GLFW.GLFW_KEY_RIGHT:
		case GLFW.GLFW_KEY_D:
			this.page++;
			int fullPageCount = this.book.getFullPageCount(this.advancementCache);
			if (this.page >= fullPageCount) {
				this.page = fullPageCount - 1;
			}
			this.oldPage = -2;
			this.buildPages();
			return true;
		case GLFW.GLFW_KEY_F3:
			debug = !debug;
			return true;
		}

		return super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean mouseScrolled(double unKnown1, double unKnown2, double scrollDelta) {
		if (scrollDelta < 0.0D) {
			this.page++;
			int fullPageCount = this.book.getFullPageCount(this.advancementCache);
			if (this.page >= fullPageCount) {
				this.page = fullPageCount - 1;
			}
			this.oldPage = -2;
			this.buildPages();

			return true;
		} else if (scrollDelta > 0.0D) {
			this.page--;
			if (this.page < -1) {
				this.page = -1;
			}

			this.oldPage = -2;
			this.buildPages();

			return true;
		}

		return super.mouseScrolled(scrollDelta, unKnown1, unKnown2);
	}

	@Override
	public boolean mouseClicked(double originalMouseX, double originalMouseY, int mouseButton) {
		boolean right = false;

		double mouseX = this.getMouseX(false);
		double mouseY = this.getMouseY();

		if (mouseX > PAGE_WIDTH + (PAGE_MARGIN + PAGE_PADDING_LEFT) / PAGE_SCALE) {
			mouseX = this.getMouseX(true);
			right = true;
		}

		lastClick = new double[] { mouseX, mouseY };

		int oldPage = this.page;
		List<TyrannobookElement> elementList = ImmutableList.copyOf(right ? this.rightElements : this.leftElements);
		for (TyrannobookElement element : elementList) {
			element.mouseClicked(mouseX, mouseY, mouseButton);
			if (this.page != oldPage) {
				return true;
			}
		}

		return super.mouseClicked(originalMouseX, originalMouseY, mouseButton);
	}

	@Override
	public boolean mouseReleased(double originalMouseX, double originalMouseY, int mouseButton) {
		boolean right = false;
		double mouseX = this.getMouseX(false);
		double mouseY = this.getMouseY();

		if (mouseX > PAGE_WIDTH + (PAGE_MARGIN + PAGE_PADDING_LEFT) / PAGE_SCALE) {
			mouseX = this.getMouseX(true);
			right = true;
		}

		for (int i = 0; right ? i < this.rightElements.size() : i < this.leftElements.size(); i++) {
			TyrannobookElement element = right ? this.rightElements.get(i) : this.leftElements.get(i);
			element.mouseReleased(mouseX, mouseY, mouseButton);
		}

		lastClick = null;
		lastDrag = null;

		return super.mouseReleased(originalMouseX, originalMouseY, mouseButton);
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		boolean right = false;
		mouseX = this.getMouseX(false);
		mouseY = this.getMouseY();

		if (mouseX > PAGE_WIDTH + (PAGE_MARGIN + PAGE_PADDING_LEFT) / PAGE_SCALE) {
			mouseX = this.getMouseX(true);
			right = true;
		}

		if (lastClick != null) {
			if (lastDrag == null)
				lastDrag = new double[] { mouseX, mouseY };

			for (int i = 0; right ? i < this.rightElements.size() : i < this.leftElements.size(); i++) {
				TyrannobookElement element = right ? this.rightElements.get(i) : this.leftElements.get(i);
				element.mouseDragged(lastClick[0], lastClick[1], mouseX, mouseY, lastDrag[0], lastDrag[1], button);
			}

			lastDrag = new double[] { mouseX, mouseY };
		}

		return true;
	}

	@Override
	public void removed() {
		if (this.minecraft == null || this.minecraft.player == null) {
			return;
		}

		if (pageUpdater != null) {
			String pageStr = "";
			if (this.page >= 0) {
				PageData page = this.page == 0 ? this.book.findPage(0, this.advancementCache) : this.book.findPage((this.page - 1) * 2 + 1, this.advancementCache);
				if (page == null) {
					page = this.book.findPage((this.page - 1) * 2 + 2, this.advancementCache);
				}
				if (page != null && page.parent != null) {
					pageStr = page.parent.name + "." + page.name;
				}
			}
			pageUpdater.accept(pageStr);
		}
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	public void drawerTransform(boolean rightSide) {
		if (rightSide) {
			RenderSystem.translatef(this.width / 2 + PAGE_PADDING_RIGHT + PAGE_MARGIN, this.height / 2 - PAGE_HEIGHT_UNSCALED / 2 + PAGE_PADDING_TOP + PAGE_MARGIN, 0);
		} else {
			RenderSystem.translatef(this.width / 2 - PAGE_WIDTH_UNSCALED + PAGE_PADDING_LEFT + PAGE_MARGIN, this.height / 2 - PAGE_HEIGHT_UNSCALED / 2 + PAGE_PADDING_TOP + PAGE_MARGIN, 0);
		}
	}

	protected float leftOffset(boolean rightSide) {
		if (rightSide) {
			return this.width / 2 + PAGE_PADDING_RIGHT + PAGE_MARGIN;
		} else {
			return this.width / 2 - PAGE_WIDTH_UNSCALED + PAGE_PADDING_LEFT + PAGE_MARGIN;
		}
	}

	protected float topOffset() {
		return this.height / 2 - PAGE_HEIGHT_UNSCALED / 2 + PAGE_PADDING_TOP + PAGE_MARGIN;
	}

	protected int getMouseX(boolean rightSide) {
		return (int) ((Minecraft.getInstance().mouseHandler.xpos() * this.width / this.minecraft.getWindow().getWidth() - this.leftOffset(rightSide)) / PAGE_SCALE);
	}

	protected int getMouseY() {
		return (int) ((Minecraft.getInstance().mouseHandler.ypos() * this.height / this.minecraft.getWindow().getHeight() - 1 - this.topOffset()) / PAGE_SCALE);
	}

	public int openPage(int page) {
		return this.openPage(page, false);
	}

	public int openPage(int page, boolean returner) {
		if (page < 0) {
			return -1;
		}

		int bookPage;
		if (page == 1) {
			bookPage = 0;
		} else if (page % 2 == 0) {
			bookPage = (page - 1) / 2 + 1;
		} else {
			bookPage = (page - 2) / 2 + 1;
		}

		if (bookPage >= -1 && bookPage < this.book.getFullPageCount(this.advancementCache)) {
			if (returner) {
				this.oldPage = this.page;
			}

			this._setPage(bookPage);
		}

		return page % 2 == 0 ? 0 : 1;
	}

	public void _setPage(int page) {
		this.page = page;
		this.buildPages();
	}

	public int getPage(int side) {
		if (this.page == 0 && side == 0) {
			return -1;
		} else if (this.page == 0 && side == 1) {
			return 0;
		} else if (side == 0) {
			return (this.page - 1) * 2 + 1;
		} else if (side == 1) {
			return (this.page - 2) * 2 + 2;
		} else {
			return -1;
		}
	}

	public int getPage_() {
		return this.page;
	}

	public ArrayList<TyrannobookElement> getElements(int side) {
		return side == 0 ? this.leftElements : side == 1 ? this.rightElements : null;
	}

	public void openCover() {
		this._setPage(-1);

		this.leftElements.clear();
		this.rightElements.clear();
		this.buildPages();
	}

	public void itemClicked(ItemStack item) {
		StringActionProcessor.process(this.book.getItemAction(ItemStackData.getItemStackData(item, true)), this);
	}

	private void buildPages() {
		this.leftElements.clear();
		this.rightElements.clear();

		if (this.page == -1) {
			return;
		}

		if (this.page == 0) {
			PageData page = this.book.findPage(0, this.advancementCache);

			if (page != null) {
				page.content.build(this.book, this.rightElements, false);
			}
		} else {
			PageData leftPage = this.book.findPage((this.page - 1) * 2 + 1, this.advancementCache);
			PageData rightPage = this.book.findPage((this.page - 1) * 2 + 2, this.advancementCache);

			if (leftPage != null) {
				leftPage.content.build(this.book, this.leftElements, false);
			}
			if (rightPage != null) {
				rightPage.content.build(this.book, this.rightElements, true);
			}
		}

		for (TyrannobookElement element : this.leftElements) {
			element.parent = this;
		}
		for (TyrannobookElement element : this.rightElements) {
			element.parent = this;
		}
	}

	public static class AdvancementCache implements ClientAdvancementManager.IListener {
		private final HashMap<Advancement, AdvancementProgress> progress = new HashMap<>();
		private final HashMap<ResourceLocation, Advancement> nameCache = new HashMap<>();

		@Nullable
		public AdvancementProgress getProgress(String id) {
			return this.getProgress(this.getAdvancement(id));
		}

		@Nullable
		public AdvancementProgress getProgress(Advancement advancement) {
			return this.progress.get(advancement);
		}

		public Advancement getAdvancement(String id) {
			return this.nameCache.get(new ResourceLocation(id));
		}

		@Override
		public void onUpdateAdvancementProgress(Advancement advancement, AdvancementProgress advancementProgress) {
			this.progress.put(advancement, advancementProgress);
		}

		@Override
		public void onSelectedTabChanged(Advancement advancement) {
		}

		@Override
		public void onAddAdvancementRoot(Advancement advancement) {
			this.nameCache.put(advancement.getId(), advancement);
		}

		@Override
		public void onRemoveAdvancementRoot(Advancement advancement) {
			this.progress.remove(advancement);
			this.nameCache.remove(advancement.getId());
		}

		@Override
		public void onAddAdvancementTask(Advancement advancement) {
			this.nameCache.put(advancement.getId(), advancement);
		}

		@Override
		public void onRemoveAdvancementTask(Advancement advancement) {
			this.progress.remove(advancement);
			this.nameCache.remove(advancement.getId());
		}

		@Override
		public void onAdvancementsCleared() {
			this.progress.clear();
			this.nameCache.clear();
		}
	}
}
