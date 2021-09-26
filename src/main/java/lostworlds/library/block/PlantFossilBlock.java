package lostworlds.library.block;

import java.util.Random;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.entity.TimeEras;
import lostworlds.library.entity.illager.FossilPoacherEntity;
import lostworlds.library.item.tool.ModMaterials;
import lostworlds.library.item.tool.ModToolTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

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
	public void stepOn(World world, BlockPos pos, Entity entity) 
	{
		this.destroy(world, pos, entity, 100);
		super.stepOn(world, pos, entity);
	}
	
	@Override
	public void fallOn(World world, BlockPos pos, Entity entity, float distance) 
	{
		if(!(entity instanceof FossilPoacherEntity)) 
		{
			this.destroy(world, pos, entity, 3);
		}
		
		super.fallOn(world, pos, entity, distance);
	}
	
	private void destroy(World world, BlockPos pos, Entity entity, int distance) 
	{
		if(this.canDestroy(world, entity)) 
		{
			if(!world.isClientSide && world.random.nextInt(distance) == 0) 
			{
				BlockState blockstate = world.getBlockState(pos);
				if(blockstate.is(BlockInit.PLANT_FOSSIL)) 
				{
					this.breakPlant(world, pos, blockstate);
				}
			}
		}
	}
	
	private void breakPlant(World world, BlockPos pos, BlockState state) 
	{
		world.playSound((PlayerEntity)null, pos, SoundEvents.STONE_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		Damage damage = state.getValue(DAMAGE);
		if(damage == Damage.COMPLETELY) 
		{
			world.destroyBlock(pos, false);
		}
		else if(damage == Damage.DAMAGED) 
		{
			world.setBlock(pos, state.setValue(DAMAGE, Damage.COMPLETELY), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		}
		else if(damage == Damage.CRACKED) 
		{
			world.setBlock(pos, state.setValue(DAMAGE, Damage.DAMAGED), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		}
		else if(damage == Damage.SLIGHTLY) 
		{
			world.setBlock(pos, state.setValue(DAMAGE, Damage.CRACKED), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		}
		else if(damage == Damage.CHIPPED) 
		{
			world.setBlock(pos, state.setValue(DAMAGE, Damage.SLIGHTLY), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		}
		else if(damage == Damage.NONE) 
		{
			world.setBlock(pos, state.setValue(DAMAGE, Damage.CHIPPED), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		}
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
	
	private boolean canDestroy(World world, Entity entity) 
	{
		return entity instanceof PlayerEntity || ForgeEventFactory.getMobGriefingEvent(world, entity);
	}
}
