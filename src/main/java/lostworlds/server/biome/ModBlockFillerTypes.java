package lostworlds.server.biome;

import java.util.function.Supplier;

import lostworlds.server.LostWorldsTags;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;

public class ModBlockFillerTypes {
	public static final Supplier<RuleTest> PERMIAN_STONE = () -> new TagMatchRuleTest(LostWorldsTags.ModBlockTags.BASE_STONE_PERMIAN.tag);
	public static final Supplier<RuleTest> JURASSIC_STONE = () -> new TagMatchRuleTest(LostWorldsTags.ModBlockTags.BASE_STONE_JURASSIC.tag);
	public static final Supplier<RuleTest> BASALT = () -> new BlockMatchRuleTest(Blocks.BASALT);
}
