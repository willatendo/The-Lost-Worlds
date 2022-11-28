package lostworlds.server.block;

import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFlowerPotBlock extends FlowerPotBlock {
	public ModFlowerPotBlock(Supplier<? extends Block> block, Properties properties) {
		super(Blocks.FLOWER_POT == null ? null : () -> (FlowerPotBlock) Blocks.FLOWER_POT, block, properties);
		if (Blocks.FLOWER_POT != null) {
			((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ForgeRegistries.BLOCKS.getKey(block.get()), () -> this);
		}
	}
}
