package lostworlds.library.block;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import lostworlds.content.server.init.SoundInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class AbstractBakedVaseBlock extends FallingBlock 
{
	public static final Map<Block, Map<Direction, VoxelShape>> SHAPES = new HashMap<Block, Map<Direction, VoxelShape>>();
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	private static Block vase;
	private static Block chipped;
	private static Block damaged;
	
	public AbstractBakedVaseBlock(Properties properties, Block vaseBlock, Block chippedBlock, Block damagedBlock) 
	{
		super(properties);
		this.vase = vaseBlock;
		this.chipped = chippedBlock;
		this.damaged = damagedBlock;
		
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH));
	}
	
	@Override
	public void stepOn(World world, BlockPos pos, Entity entity) 
	{
		this.destroyPottery(world, pos, entity, 100);
		super.stepOn(world, pos, entity);
	}

	@Override
	public void fallOn(World world, BlockPos pos, Entity entity, float chance) 
	{
		this.destroyPottery(world, pos, entity, 3);
	}
	
	@Override
	protected void falling(FallingBlockEntity blockEntity) 
	{
		blockEntity.setHurtsEntities(true); 
	}
	
	@Override
	public void onLand(World world, BlockPos pos, BlockState state, BlockState newState, FallingBlockEntity blockEntity) 
	{	
		if(!blockEntity.isSilent()) 
		{
			world.levelEvent(1031, pos, 0);
		}
	}
	
	@Override
	public void onBroken(World world, BlockPos pos, FallingBlockEntity blockEntity) 
	{
		if(!blockEntity.isSilent()) 
		{
			world.levelEvent(1029, pos, 0);
		}
	}
	
	@Nullable
	public static BlockState damage(BlockState state) 
	{
		if(state.is(vase)) 
		{
			return chipped.defaultBlockState().setValue(HORIZONTAL_FACING, state.getValue(HORIZONTAL_FACING));
		} 
		else 
		{
			return state.is(chipped) ? damaged.defaultBlockState().setValue(HORIZONTAL_FACING, state.getValue(HORIZONTAL_FACING)) : null;
		}
	}

	private void destroyPottery(World world, BlockPos pos, Entity entity, int amount) 
	{
		if(!world.isClientSide && world.random.nextInt(amount) == 0) 
			{
				BlockState blockstate = world.getBlockState(pos);
				if(blockstate.is(this)) 
				{
					this.breakPottery(world, pos, blockstate);
				}
		}
	}
	
	private void breakPottery(World world, BlockPos pos, BlockState state) 
	{
		world.playSound((PlayerEntity) null, pos, SoundInit.POT_SMASH, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		world.destroyBlock(pos, false);
	}
	
	@Nullable
	@Override
	public INamedContainerProvider getMenuProvider(BlockState state, World world, BlockPos pos) 
	{
		TileEntity tileentity = world.getBlockEntity(pos);
		return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider)tileentity : null;
	}
	
	@Override
	public boolean hasAnalogOutputSignal(BlockState state) 
	{
		return true;
	}
	
	@Override
	public int getAnalogOutputSignal(BlockState state, World world, BlockPos pos) 
	{
		return Container.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) 
	{
		return SHAPES.get(this).get(state.getValue(HORIZONTAL_FACING));
	}
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) 
	{
		return state.rotate(mirrorIn.getRotation(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) 
	{
		return state.setValue(HORIZONTAL_FACING, direction.rotate(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) 
	{
		super.createBlockStateDefinition(builder);
		builder.add(HORIZONTAL_FACING);
	}
	
	protected static VoxelShape calculateShapes(Direction to, VoxelShape shape) 
	{
		VoxelShape[] buffer = new VoxelShape[] { shape, VoxelShapes.empty() };

		int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
		for (int i = 0; i < times; i++) 
		{
			buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.or(buffer[1], VoxelShapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
			buffer[0] = buffer[1];
			buffer[1] = VoxelShapes.empty();
		}

		return buffer[0];
	}

	protected void runCalculation(VoxelShape shape) 
	{
		SHAPES.put(this, new HashMap<Direction, VoxelShape>());
		Map<Direction, VoxelShape> facingMap = SHAPES.get(this);
		for (Direction direction : Direction.values()) 
		{
			facingMap.put(direction, calculateShapes(direction, shape));
		}
	}		
	
	public static Block kylix()
	{
		return new AbstractVaseBlock(AbstractBlock.Properties.of(Material.CLAY).instabreak().noOcclusion(), Block.box(0.0D, 0.0, 0.0D, 16.0D, 16.0D, 16.0D));
	}
}
