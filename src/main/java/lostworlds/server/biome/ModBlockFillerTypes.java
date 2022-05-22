package lostworlds.server.biome;

import java.util.function.Supplier;

import lostworlds.server.LostWorldsTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModBlockFillerTypes {
	public static final Supplier<RuleTest> PERMIAN_STONE = () -> new TagMatchTest(LostWorldsTags.ModBlockTags.BASE_STONE_PERMIAN.tag);
	public static final Supplier<RuleTest> JURASSIC_STONE = () -> new TagMatchTest(LostWorldsTags.ModBlockTags.BASE_STONE_JURASSIC.tag);
	public static final Supplier<RuleTest> BASALT = () -> new BlockMatchTest(Blocks.BASALT);
}
