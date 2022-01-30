package lostworlds.library.block;

import java.util.ArrayList;

import lostworlds.library.block.builder.BlockUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;

public class ColouredGlassPaneBlock extends PaneBlock {
	public static final ArrayList<Block> entries = new ArrayList<>();

	protected ColouredGlassPaneBlock() {
		super(AbstractBlock.Properties.of(Material.GLASS).strength(0.3F).noOcclusion().sound(SoundType.GLASS));
	}

	public static void create() {
		for (DyeColor colour : DyeColor.values()) {
			Block block = BlockUtils.create(colour.getName().toLowerCase() + "_glass_pane", new ColouredGlassPaneBlock());
			entries.add(block);
		}
		Block clear = BlockUtils.create("clear_glass_pane", new ColouredGlassPaneBlock());
		Block tinted = BlockUtils.create("tinted_glass_pane", new ColouredGlassPaneBlock());
		Block shaded = BlockUtils.create("shaded_glass_pane", new ColouredGlassPaneBlock());
		entries.add(clear);
		entries.add(tinted);
		entries.add(shaded);
	}
}
