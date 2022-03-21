package lostworlds.server.block.builder;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.item.EggBlockItem;
import lostworlds.server.item.block.GroupedBlockItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import tyrannotitanlib.library.base.block.TyrannoConnectedTextureBlock;

public class BlockUtils {
	public static Block create(String id, Block block, Item item) {
		LostWorldsRegistry.register(id, block);
		LostWorldsRegistry.register(id, item);
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

	public static TyrannoConnectedTextureBlock create(String id, TyrannoConnectedTextureBlock block) {
		Item item = new GroupedBlockItem(block);
		LostWorldsRegistry.register(id, block);
		LostWorldsRegistry.register(id, item);
		return block;
	}
}
