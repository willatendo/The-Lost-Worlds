package lostworlds.client.screen;

import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.menu.TimeMachineMenu;
import lostworlds.server.menu.recipes.TimeMachineRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class TimeMachineScreen extends AbstractContainerScreen<TimeMachineMenu> {
	private static final ResourceLocation TEXTURE = LostWorldsUtils.rL("textures/gui/time_machine.png");
	private float scrollOffs;
	private boolean scrolling;
	private int startIndex;
	private boolean displayRecipes;

	public TimeMachineScreen(TimeMachineMenu container, Inventory inv, Component text) {
		super(container, inv, text);
		container.registerUpdateListener(this::containerChanged);
		--this.titleLabelY;
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderTooltip(stack, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack stack, float partialTicks, int x, int y) {
		this.renderBackground(stack);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int i = this.leftPos;
		int j = this.topPos;
		this.blit(stack, i, j, 0, 0, this.imageWidth, this.imageHeight);
		int k = (int) (41.0F * this.scrollOffs);
		this.blit(stack, i + 119, j + 15 + k, 176 + (this.isScrollBarActive() ? 0 : 12), 0, 12, 15);
		int l = this.leftPos + 52;
		int i1 = this.topPos + 14;
		int j1 = this.startIndex + 12;
		this.renderButtons(stack, x, y, l, i1, j1);
		this.renderRecipes(l, i1, j1);
	}

	@Override
	protected void renderTooltip(PoseStack stack, int mouseX, int mouseY) {
		super.renderTooltip(stack, mouseX, mouseY);
		if (this.displayRecipes) {
			int i = this.leftPos + 52;
			int j = this.topPos + 14;
			int k = this.startIndex + 12;
			List<TimeMachineRecipe> list = this.menu.getRecipes();

			for (int l = this.startIndex; l < k && l < this.menu.getNumRecipes(); ++l) {
				int i1 = l - this.startIndex;
				int j1 = i + i1 % 4 * 16;
				int k1 = j + i1 / 4 * 18 + 2;
				if (mouseX >= j1 && mouseX < j1 + 16 && mouseY >= k1 && mouseY < k1 + 18) {
					this.renderTooltip(stack, list.get(l).getResultItem(), mouseX, mouseY);
				}
			}
		}
	}

	private void renderButtons(PoseStack stack, int int1, int int2, int int3, int int4, int int5) {
		for (int i = this.startIndex; i < int5 && i < this.menu.getNumRecipes(); ++i) {
			int j = i - this.startIndex;
			int k = int3 + j % 4 * 16;
			int l = j / 4;
			int i1 = int4 + l * 18 + 2;
			int j1 = this.imageHeight;
			if (i == this.menu.getSelectedRecipeIndex()) {
				j1 += 18;
			} else if (int1 >= k && int2 >= i1 && int1 < k + 16 && int2 < i1 + 18) {
				j1 += 36;
			}

			this.blit(stack, k, i1 - 1, 0, j1, 16, 18);
		}
	}

	private void renderRecipes(int int1, int int2, int int3) {
		List<TimeMachineRecipe> list = this.menu.getRecipes();

		for (int i = this.startIndex; i < int3 && i < this.menu.getNumRecipes(); ++i) {
			int j = i - this.startIndex;
			int k = int1 + j % 4 * 16;
			int l = j / 4;
			int i1 = int2 + l * 18 + 2;
			this.minecraft.getItemRenderer().renderAndDecorateItem(list.get(i).getResultItem(), k, i1);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int int1) {
		this.scrolling = false;
		if (this.displayRecipes) {
			int i = this.leftPos + 52;
			int j = this.topPos + 14;
			int k = this.startIndex + 12;

			for (int l = this.startIndex; l < k; ++l) {
				int i1 = l - this.startIndex;
				double d0 = mouseX - (double) (i + i1 % 4 * 16);
				double d1 = mouseY - (double) (j + i1 / 4 * 18);
				if (d0 >= 0.0D && d1 >= 0.0D && d0 < 16.0D && d1 < 18.0D && this.menu.clickMenuButton(this.minecraft.player, l)) {
					Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BEACON_ACTIVATE, 1.0F));
					this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, l);
					return true;
				}
			}

			i = this.leftPos + 119;
			j = this.topPos + 9;
			if (mouseX >= (double) i && mouseX < (double) (i + 12) && mouseY >= (double) j && mouseY < (double) (j + 54)) {
				this.scrolling = true;
			}
		}

		return super.mouseClicked(mouseX, mouseY, int1);
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int int1, double d1, double d2) {
		if (this.scrolling && this.isScrollBarActive()) {
			int i = this.topPos + 14;
			int j = i + 54;
			this.scrollOffs = ((float) mouseY - (float) i - 7.5F) / ((float) (j - i) - 15.0F);
			this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
			this.startIndex = (int) ((double) (this.scrollOffs * (float) this.getOffscreenRows()) + 0.5D) * 4;
			return true;
		} else {
			return super.mouseDragged(mouseX, mouseY, int1, d1, d2);
		}
	}

	@Override
	public boolean mouseScrolled(double d1, double d2, double d3) {
		if (this.isScrollBarActive()) {
			int i = this.getOffscreenRows();
			this.scrollOffs = (float) ((double) this.scrollOffs - d3 / (double) i);
			this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
			this.startIndex = (int) ((double) (this.scrollOffs * (float) i) + 0.5D) * 4;
		}

		return true;
	}

	private boolean isScrollBarActive() {
		return this.displayRecipes && this.menu.getNumRecipes() > 12;
	}

	protected int getOffscreenRows() {
		return (this.menu.getNumRecipes() + 4 - 1) / 4 - 3;
	}

	private void containerChanged() {
		this.displayRecipes = this.menu.hasInputItem();
		if (!this.displayRecipes) {
			this.scrollOffs = 0.0F;
			this.startIndex = 0;
		}
	}
}
