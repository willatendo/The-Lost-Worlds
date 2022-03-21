package lostworlds.server.biome;

import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;

public class ModBlockFillerTypes {
	public static final RuleTest PERMIAN_STONE = new BlockMatchRuleTest(LostWorldsBlocks.PERMIAN_STONE);
	public static final RuleTest JURASSIC_STONE = new BlockMatchRuleTest(LostWorldsBlocks.JURASSIC_STONE);
	public static final RuleTest BASALT = new BlockMatchRuleTest(Blocks.BASALT);
}
