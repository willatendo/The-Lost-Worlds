package lostworlds.content.client.book.content;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import lostworlds.library.container.recipes.ArchaeologyTableRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.crafting.IShapedRecipe;
import tyrannotitanlib.library.tyrannobook.client.Textures;
import tyrannotitanlib.library.tyrannobook.client.data.TyrannobookData;
import tyrannotitanlib.library.tyrannobook.client.data.TyrannobookLoadException;
import tyrannotitanlib.library.tyrannobook.client.data.content.PageContent;
import tyrannotitanlib.library.tyrannobook.client.data.element.ImageData;
import tyrannotitanlib.library.tyrannobook.client.data.element.ImageElement;
import tyrannotitanlib.library.tyrannobook.client.data.element.ItemElement;
import tyrannotitanlib.library.tyrannobook.client.data.element.ItemStackData;
import tyrannotitanlib.library.tyrannobook.client.data.element.TextData;
import tyrannotitanlib.library.tyrannobook.client.data.element.TextElement;
import tyrannotitanlib.library.tyrannobook.client.data.element.TyrannobookElement;
import tyrannotitanlib.library.tyrannobook.client.screen.TyrannobookScreen;

@OnlyIn(Dist.CLIENT)
public class ContentArchaeology extends PageContent {
	public static final transient String ID = "archaeology";

	public static final transient int TEX_SIZE = 256;
	public static final transient ImageData IMG_CRAFTING_LARGE = new ImageData(Textures.TEX_CRAFTING, 0, 0, 183, 114, TEX_SIZE, TEX_SIZE);
	public static final transient ImageData IMG_CRAFTING_SMALL = new ImageData(Textures.TEX_CRAFTING, 0, 114, 155, 78, TEX_SIZE, TEX_SIZE);

	public static final transient int X_RESULT_SMALL = 118;
	public static final transient int Y_RESULT_SMALL = 23;
	public static final transient int X_RESULT_LARGE = 146;
	public static final transient int Y_RESULT_LARGE = 41;

	public static final transient float ITEM_SCALE = 2.0F;
	public static final transient int SLOT_MARGIN = 5;
	public static final transient int SLOT_PADDING = 4;

	public String title = "Archaeology";
	public String grid_size = "large";
	public ItemStackData[][] grid;
	public ItemStackData result;
	public TextData[] description;
	public String recipe;

	@Override
	public void build(TyrannobookData book, ArrayList<TyrannobookElement> list, boolean rightSide) {
		int x = 0;
		int y = 16;
		int height = 100;
		int resultX = 100;
		int resultY = 50;

		TextData tdTitle = new TextData(this.title);
		tdTitle.underlined = true;
		list.add(new TextElement(0, 0, TyrannobookScreen.PAGE_WIDTH, 9, tdTitle));

		if (this.grid_size.equalsIgnoreCase("small")) {
			x = TyrannobookScreen.PAGE_WIDTH / 2 - IMG_CRAFTING_SMALL.width / 2;
			height = y + IMG_CRAFTING_SMALL.height;
			list.add(new ImageElement(x, y, IMG_CRAFTING_SMALL.width, IMG_CRAFTING_SMALL.height, IMG_CRAFTING_SMALL, book.appearance.slotColor));
			resultX = x + X_RESULT_SMALL;
			resultY = y + Y_RESULT_SMALL;
		} else if (this.grid_size.equalsIgnoreCase("large")) {
			x = TyrannobookScreen.PAGE_WIDTH / 2 - IMG_CRAFTING_LARGE.width / 2;
			height = y + IMG_CRAFTING_LARGE.height;
			list.add(new ImageElement(x, y, IMG_CRAFTING_LARGE.width, IMG_CRAFTING_LARGE.height, IMG_CRAFTING_LARGE, book.appearance.slotColor));
			resultX = x + X_RESULT_LARGE;
			resultY = y + Y_RESULT_LARGE;
		}

		if (this.grid != null) {
			for (int i = 0; i < this.grid.length; i++) {
				for (int j = 0; j < this.grid[i].length; j++) {
					if (this.grid[i][j] == null || this.grid[i][j].getItems().isEmpty()) {
						continue;
					}
					list.add(new ItemElement(x + SLOT_MARGIN + (SLOT_PADDING + Math.round(ItemElement.ITEM_SIZE_HARDCODED * ITEM_SCALE)) * j, y + SLOT_MARGIN + (SLOT_PADDING + Math.round(ItemElement.ITEM_SIZE_HARDCODED * ITEM_SCALE)) * i, ITEM_SCALE, this.grid[i][j].getItems(), this.grid[i][j].action));
				}
			}
		}

		if (this.result != null) {
			list.add(new ItemElement(resultX, resultY, ITEM_SCALE, this.result.getItems(), this.result.action));
		}

		if (this.description != null && this.description.length > 0) {
			list.add(new TextElement(0, height + 5, TyrannobookScreen.PAGE_WIDTH, TyrannobookScreen.PAGE_HEIGHT - height - 5, this.description));
		}
	}

	@Override
	public void load() {
		super.load();

		if (!StringUtils.isEmpty(recipe) && ResourceLocation.isValidResourceLocation(recipe)) {
			int w = 0, h = 0;
			switch (grid_size.toLowerCase()) {
			case "large":
				w = h = 3;
				break;
			case "small":
				w = h = 2;
				break;
			}

			IRecipe<?> recipe = Minecraft.getInstance().level.getRecipeManager().byKey(new ResourceLocation(this.recipe)).orElse(null);
			if (recipe instanceof ArchaeologyTableRecipe) {
				if (!recipe.canCraftInDimensions(w, h)) {
					throw new TyrannobookLoadException("Recipe " + this.recipe + " cannot fit in a " + w + "x" + h + " crafting grid");
				}

				result = ItemStackData.getItemStackData(recipe.getResultItem());

				NonNullList<Ingredient> ingredients = recipe.getIngredients();

				if (recipe instanceof IShapedRecipe) {
					IShapedRecipe shaped = (IShapedRecipe) recipe;

					grid = new ItemStackData[shaped.getRecipeHeight()][shaped.getRecipeWidth()];

					for (int y = 0; y < grid.length; y++) {
						for (int x = 0; x < grid[y].length; x++) {
							grid[y][x] = ItemStackData.getItemStackData(NonNullList.of(ItemStack.EMPTY, ingredients.get(x + y * grid[y].length).getItems()));
						}
					}

					return;
				}

				grid = new ItemStackData[h][w];
				for (int i = 0; i < ingredients.size(); i++) {
					grid[i / h][i % w] = ItemStackData.getItemStackData(NonNullList.of(ItemStack.EMPTY, ingredients.get(i).getItems()));
				}
			}
		}
	}
}