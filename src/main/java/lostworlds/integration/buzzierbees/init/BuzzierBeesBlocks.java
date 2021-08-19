package lostworlds.integration.buzzierbees.init;

import lostworlds.integration.buzzierbees.block.BuzzierBeesBeehiveBlock;
import lostworlds.integration.buzzierbees.block.builder.BuzzierBeesBlockAndItemBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class BuzzierBeesBlocks 
{
	public static final Block ARAUCARIA_BEEHIVE = BuzzierBeesBlockAndItemBuilder.create("araucaria_beehive", new BuzzierBeesBeehiveBlock(AbstractBlock.Properties.copy(Blocks.BEEHIVE)));
	public static final Block CONIFER_BEEHIVE = BuzzierBeesBlockAndItemBuilder.create("conifer_beehive", new BuzzierBeesBeehiveBlock(AbstractBlock.Properties.copy(Blocks.BEEHIVE)));
	public static final Block GINKGO_BEEHIVE = BuzzierBeesBlockAndItemBuilder.create("ginkgo_beehive", new BuzzierBeesBeehiveBlock(AbstractBlock.Properties.copy(Blocks.BEEHIVE)));
	public static final Block SCORCHED_BEEHIVE = BuzzierBeesBlockAndItemBuilder.create("scorched_beehive", new BuzzierBeesBeehiveBlock(AbstractBlock.Properties.copy(Blocks.BEEHIVE)));

	public static void init() { }
}
