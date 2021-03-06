package lostworlds.server.block;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;

public class ModFlowerPotBlock extends FlowerPotBlock {
	public ModFlowerPotBlock(Supplier<? extends Block> block, Properties properties) {
		super(Blocks.FLOWER_POT == null ? null : () -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), block, properties);
		if (Blocks.FLOWER_POT != null) {
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(block.get().getRegistryName(), () -> this);
		}
	}
}
