package lostworlds.library.block;

import java.util.Random;

import lostworlds.library.entity.TimeEras;
import lostworlds.library.item.tool.ModMaterials;
import lostworlds.library.item.tool.ModToolTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class PlantFossilBlock extends Block
{
	public static final EnumProperty<TimeEras> ERA = EnumProperty.create("era", TimeEras.class);
	public static final EnumProperty<Plants> POTENTIAL_PLANT = EnumProperty.create("potential_plant", Plants.class);
	public static final EnumProperty<Damage> DAMAGE = EnumProperty.create("damage", Damage.class);
	
	public PlantFossilBlock() 
	{
		super(AbstractBlock.Properties.of(ModMaterials.SOFT).harvestTool(ModToolTypes.HAMMER).strength(4.0F, 0.0F).noDrops().sound(SoundType.STONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(ERA, TimeEras.MODERN_MINECRAFT).setValue(POTENTIAL_PLANT, Plants.ALETHOPTERIS).setValue(DAMAGE, Damage.NONE));
	}
	
	@Override
	public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) 
	{
		Plants plant = state.getValue(POTENTIAL_PLANT);
		Damage damage = state.getValue(DAMAGE);
		Random rand = new Random();
		
		if(damage == Damage.NONE)
		{
			popResource(world, pos, plant.getDrop().getDefaultInstance());
		}
		if(damage == Damage.CHIPPED)
		{
			int chance = rand.nextInt(5);
			if(chance < 5)
			{
				popResource(world, pos, plant.getDrop().getDefaultInstance());
			}
		}
		if(damage == Damage.SLIGHTLY)
		{
			int chance = rand.nextInt(5);
			if(chance < 4)
			{
				popResource(world, pos, plant.getDrop().getDefaultInstance());
			}
		}
		if(damage == Damage.CRACKED)
		{
			int chance = rand.nextInt(5);
			if(chance < 3)
			{
				popResource(world, pos, plant.getDrop().getDefaultInstance());
			}
		}
		if(damage == Damage.DAMAGED)
		{
			int chance = rand.nextInt(5);
			if(chance < 2)
			{
				popResource(world, pos, plant.getDrop().getDefaultInstance());
			}
		}
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		builder.add(ERA, POTENTIAL_PLANT, DAMAGE);
	}
}
