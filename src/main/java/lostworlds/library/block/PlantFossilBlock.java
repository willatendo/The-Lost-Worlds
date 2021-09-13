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

public class PlantFossilBlock extends Block
{
	private static final EnumProperty<TimeEras> ERA = EnumProperty.create("era", TimeEras.class);
	private static final EnumProperty<Plants> POTENTIAL_PLANT = EnumProperty.create("potential_plant", Plants.class);
	private static final EnumProperty<Damage> DAMAGE = EnumProperty.create("damage", Damage.class);
	
	public PlantFossilBlock(Properties properties) 
	{
		super(AbstractBlock.Properties.of(ModMaterials.SOFT).harvestTool(ModToolTypes.HAMMER).strength(4.0F, 0.0F).noDrops().sound(SoundType.STONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(ERA, TimeEras.MODERN_MINECRAFT).setValue(POTENTIAL_PLANT, Plants.NONE).setValue(DAMAGE, Damage.NONE));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		builder.add(ERA, POTENTIAL_PLANT, DAMAGE);
	}
}
