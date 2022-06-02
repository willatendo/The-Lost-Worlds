package lostworlds.client.book.screen.book.element;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.client.book.action.StringActionProcessor;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.StringUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ItemElement extends SizedBookElement {
	public static final int ITEM_SIZE_HARDCODED = 16;
	public static final long ITEM_SWITCH_TIME = 3000000000L;

	public NonNullList<ItemStack> itemCycle;
	public float scale;
	@Nullable
	public String action;
	public List<Component> tooltip;

	public long lastTime;
	public int currentItem = 0;

	public ItemElement(int x, int y, float scale, Item item) {
		this(x, y, scale, new ItemStack(item));
	}

	public ItemElement(int x, int y, float scale, Block item) {
		this(x, y, scale, new ItemStack(item));
	}

	public ItemElement(int x, int y, float scale, ItemStack item) {
		this(x, y, scale, new ItemStack[] { item });
	}

	public ItemElement(int x, int y, float scale, Collection<ItemStack> itemCycle) {
		this(x, y, scale, itemCycle.toArray(new ItemStack[0]));
	}

	public ItemElement(int x, int y, float scale, Collection<ItemStack> itemCycle, String action) {
		this(x, y, scale, itemCycle.toArray(new ItemStack[0]), action);
	}

	public ItemElement(int x, int y, float scale, ItemStack... itemCycle) {
		this(x, y, scale, itemCycle, null);
	}

	public ItemElement(int x, int y, float scale, ItemStack[] itemCycle, @Nullable String action) {
		super(x, y, Mth.floor(ITEM_SIZE_HARDCODED * scale), Mth.floor(ITEM_SIZE_HARDCODED * scale));

		lastTime = Util.getNanos();

		NonNullList<ItemStack> nonNullStacks = NonNullList.withSize(itemCycle.length, ItemStack.EMPTY);
		for (int i = 0; i < itemCycle.length; i++) {
			if (!itemCycle[i].isEmpty()) {
				nonNullStacks.set(i, itemCycle[i].copy());
			}
		}

		this.itemCycle = nonNullStacks;
		this.scale = scale;
		this.action = action;
	}

	@Override
	public void draw(PoseStack stack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) {
		long nano = Util.getNanos();

		if (nano > lastTime + ITEM_SWITCH_TIME) {
			this.lastTime = nano;
			this.currentItem++;

			if (this.currentItem >= this.itemCycle.size()) {
				this.currentItem = 0;
			}
		}

		if (this.currentItem < this.itemCycle.size()) {

			stack.pushPose();
			stack.translate(x, y, 0);
			stack.scale(scale, scale, 1.0F);

			PoseStack poses = RenderSystem.getModelViewStack();
			poses.pushPose();
			poses.mulPoseMatrix(stack.last().pose());

			ItemStack item = this.itemCycle.get(this.currentItem);
			this.minecraft.getItemRenderer().renderAndDecorateItem(item, 0, 0);
			Font font = net.minecraftforge.client.RenderProperties.get(item).getFont(item);
			if (font == null)
				font = minecraft.font;
			this.minecraft.getItemRenderer().renderGuiItemDecorations(font, item, 0, 0, null);

			stack.popPose();
			poses.popPose();
			RenderSystem.applyModelViewMatrix();
		}
	}

	@Override
	public void drawOverlay(PoseStack stack, int mouseX, int mouseY, float partialTicks, Font fontRenderer) {
		if (this.isHovered(mouseX, mouseY) && this.currentItem < this.itemCycle.size()) {
			if (this.tooltip != null) {
				this.drawTooltip(stack, this.tooltip, mouseX, mouseY, fontRenderer);
			} else {
				this.renderToolTip(stack, fontRenderer, this.itemCycle.get(this.currentItem), mouseX, mouseY);
			}
		}
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int mouseButton) {
		if (mouseButton == 0 && this.isHovered(mouseX, mouseY) && this.currentItem < this.itemCycle.size()) {
			if (!StringUtil.isNullOrEmpty(this.action)) {
				StringActionProcessor.process(this.action, this.parent);
			}
		}
	}
}
