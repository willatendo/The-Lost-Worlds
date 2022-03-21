package lostworlds.server.item.block;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class GroupedBlockItem extends BlockItem {
	public GroupedBlockItem(Block block) {
		super(block, new Properties().tab(LostWorldsUtils.BLOCKS));
	}
}
