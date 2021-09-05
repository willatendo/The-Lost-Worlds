package lostworlds.library.block.builder;

import com.mojang.datafixers.util.Pair;

import lostworlds.library.block.ModStandingSignBlock;
import lostworlds.library.block.ModWallSignBlock;
import lostworlds.library.block.SignManager;
import lostworlds.library.item.ModSignItem;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import lostworlds.library.util.ModUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.WoodType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.common.ToolType;

public class SignBuilder 
{
	public static Pair<ModStandingSignBlock, ModWallSignBlock> register(String id, String wallId)
	{
		WoodType woodtype = SignManager.registerWoodType(WoodType.create(ModUtils.ID + ":" + id));
		ModStandingSignBlock standingSign = BlockBuilder.create(id, new ModStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.5F).sound(SoundType.WOOD).noCollission(), woodtype));
		ModWallSignBlock wallSign = BlockBuilder.create(wallId, new ModWallSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).harvestTool(ToolType.AXE).strength(2.5F).sound(SoundType.WOOD).noCollission().dropsLike(standingSign), woodtype));
		ModRegistry.register(id, new ModSignItem(new Properties().tab(ModItemGroup.BLOCKS).stacksTo(16), standingSign, wallSign));
		return Pair.of(standingSign, wallSign);
	}
}
