package lostworlds.server.block.builder;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.ConnectedTexturesBlock;
import lostworlds.server.item.EggBlockItem;
import lostworlds.server.item.block.GroupedBlockItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockUtils {
	public static Block create(String id, Block block, Item item) {
		block.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.BLOCKS.register(block);
		item.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.ITEMS.register(item);
		return block;
	}

	public static Block create(String id, Block block) {
		Item item = new GroupedBlockItem(block);
		return create(id, block, item);
	}

	public static Block createEgg(String id, Block block, ITextComponent name) {
		Item item = new EggBlockItem(block, name);
		return create(id, block, item);
	}

	public static ConnectedTexturesBlock create(String id, ConnectedTexturesBlock block) {
		Item item = new GroupedBlockItem(block);
		block.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.BLOCKS.register(block);
		item.setRegistryName(LostWorldsUtils.rL(id));
		ForgeRegistries.ITEMS.register(item);
		return block;
	}
}
