package lostworlds.library.block;

import java.util.ArrayList;

import lostworlds.library.block.builder.BlockUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;

public class ColouredGlassBlock extends Block {
	public static final ArrayList<Block> entries = new ArrayList<>();

	protected ColouredGlassBlock() {
		super(AbstractBlock.Properties.of(Material.GLASS).strength(0.3F).noOcclusion().sound(SoundType.GLASS));
	}

	public static void create() {
		for (DyeColor colour : DyeColor.values()) {
			Block block = BlockUtils.create(colour.getName().toLowerCase() + "_glass", new ColouredGlassBlock());
			entries.add(block);
		}
		Block clear = BlockUtils.create("clear_glass", new ColouredGlassBlock());
		Block tinted = BlockUtils.create("tinted_glass", new ColouredGlassBlock());
		Block shaded = BlockUtils.create("shaded_glass", new ColouredGlassBlock());
		entries.add(clear);
		entries.add(tinted);
		entries.add(shaded);
	}
}
