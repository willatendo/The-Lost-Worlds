package lostworlds.library.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;

public class FogBlock extends Block
{
	public static final IntegerProperty FOGGY = IntegerProperty.create("foggy", 0, 6);

	public FogBlock(Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FOGGY, Integer.valueOf(0)));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		builder.add(FOGGY);
	}
}
