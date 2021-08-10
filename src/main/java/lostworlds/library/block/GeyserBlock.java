package lostworlds.library.block;

import java.util.Random;

import lostworlds.library.ModBlockStateProperties;
import lostworlds.library.interfaces.ILavaLoggable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * Author: Willatendo
 * Date: July 11, 2021
 */

public class GeyserBlock extends Block implements ILavaLoggable
{
	public static final BooleanProperty LAVALOGGED = ModBlockStateProperties.LAVALOGGED;
	
	public GeyserBlock(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		boolean flag = fluidstate.getType() == Fluids.LAVA;
		return super.getStateForPlacement(context).setValue(LAVALOGGED, Boolean.valueOf(flag));
	}
	
	@Override
	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) 
	{
		BlockState blockstate = reader.getBlockState(pos.below());
		return blockstate.getBlock() == Blocks.AIR ? false : blockstate.getBlock() == Blocks.LAVA ? false : blockstate.getBlock() == Blocks.MAGMA_BLOCK ? false : true;
	}
	
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		super.createBlockStateDefinition(builder);
		builder.add(LAVALOGGED);
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos newPos) 
	{
		if (state.getValue(LAVALOGGED)) 
		{
			world.getLiquidTicks().scheduleTick(pos, Fluids.LAVA, Fluids.LAVA.getTickDelay(world));
		}
		
		return Direction.DOWN == direction && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, newPos);
	}
	 
	@Override
	public FluidState getFluidState(BlockState state) 
	{
		return state.getValue(LAVALOGGED) ? Fluids.LAVA.getSource(false) : super.getFluidState(state);
	}
	
	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) 
	{
		if(state.getValue(LAVALOGGED))
		{
			return 15;
		}
		else
		{
			return 2;
		}
	}
	
	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) 
	{
		double x = (double)pos.getX() + 0.75D;
		double y = (double)pos.getY() + 1.0D;
		double z = (double)pos.getZ() + 0.5D;
		world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.SMOKE, x, y + 1, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.SMOKE, x, y + 2, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.SMOKE, x, y + 3, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.SMOKE, x, y + 4, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.SMOKE, x, y + 5, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.SMOKE, x, y + 6, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.SMOKE, x, y + 7, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
