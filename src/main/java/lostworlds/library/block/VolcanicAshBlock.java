package lostworlds.library.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class VolcanicAshBlock extends SandBlock {
	public VolcanicAshBlock() {
		super(0x888988, AbstractBlock.Properties.of(Material.SAND, MaterialColor.COLOR_GRAY).harvestTool(ToolType.SHOVEL).harvestLevel(1).strength(0.5F).sound(SoundType.SAND));
	}
}
