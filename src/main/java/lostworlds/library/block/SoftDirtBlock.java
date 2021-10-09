package lostworlds.library.block;

import java.util.Random;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.item.tool.ModMaterials;
import lostworlds.library.item.tool.ModToolTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class SoftDirtBlock extends Block
{
	public static final EnumProperty<Egg> EGG = EnumProperty.create("egg", Egg.class);
	
	public SoftDirtBlock() 
	{
		super(AbstractBlock.Properties.of(ModMaterials.SOFT).harvestTool(ModToolTypes.BRUSH).strength(4.0F, 0.0F).noDrops().sound(SoundType.GRAVEL));
		this.registerDefaultState(this.stateDefinition.any().setValue(EGG, Egg.TINY));
	}
	
	@Override
	public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) 
	{
		Random rand = new Random();
		Egg egg = state.getValue(EGG);
		if(egg == Egg.TINY)
		{
			world.setBlockAndUpdate(pos, BlockInit.TINY_FOSSILISED_EGG.defaultBlockState().setValue(TinyFossilisedEggBlock.EGGS, Integer.valueOf(rand.nextInt(19) + 1)));
		}
		else if(egg == Egg.SMALL)
		{
			world.setBlockAndUpdate(pos, BlockInit.SMALL_FOSSILISED_EGG.defaultBlockState().setValue(SmallFossilisedEggBlock.EGGS, Integer.valueOf(rand.nextInt(10) + 1)));
		}
		else if(egg == Egg.MEDIUM)
		{
			world.setBlockAndUpdate(pos, BlockInit.MEDIUM_FOSSILISED_EGG.defaultBlockState().setValue(MediumFossilisedEggBlock.EGGS, Integer.valueOf(rand.nextInt(6) + 1)));
		}
		else
		{
			world.setBlockAndUpdate(pos, BlockInit.LARGE_FOSSILISED_EGG.defaultBlockState().setValue(LargeFossilisedEggBlock.EGGS, Integer.valueOf(rand.nextInt(3) + 1)));
		}
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(EGG);
	}
}
