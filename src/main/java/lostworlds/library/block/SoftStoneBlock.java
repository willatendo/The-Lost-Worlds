package lostworlds.library.block;

import lostworlds.library.entity.TimeEras;
import lostworlds.library.item.tool.ModMaterials;
import lostworlds.library.item.tool.ModToolTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;

public class SoftStoneBlock extends Block
{
	public static final EnumProperty<TimeEras> ERA = EnumProperty.create("era", TimeEras.class);
	
	public SoftStoneBlock()
	{
		super(AbstractBlock.Properties.of(ModMaterials.SOFT).harvestTool(ModToolTypes.HAMMER).strength(4.0F, 0.0F).noDrops().sound(SoundType.STONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(ERA, TimeEras.MODERN_MINECRAFT));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		builder.add(ERA);
	}
}
