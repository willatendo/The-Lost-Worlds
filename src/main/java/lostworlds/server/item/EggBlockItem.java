package lostworlds.server.item;

import lostworlds.server.item.block.GroupedBlockItem;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class EggBlockItem extends GroupedBlockItem {
	private final ITextComponent name;

	public EggBlockItem(Block block, ITextComponent name) {
		super(block);
		this.name = name;
	}

	@Override
	public ITextComponent getName(ItemStack stack) {
		return new TranslationTextComponent("block.lostworlds.egg", this.name);
	}
}
