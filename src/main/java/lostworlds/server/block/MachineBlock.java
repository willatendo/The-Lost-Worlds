package lostworlds.server.block;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import lostworlds.client.LostWorldsConfig;
import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.block.entity.AnalyzerBlockEntity;
import lostworlds.server.block.entity.CultivatorBlockEntity;
import lostworlds.server.block.entity.DNAExtractorBlockEntity;
import lostworlds.server.block.entity.DNAInjectorBlockEntity;
import lostworlds.server.block.entity.FossilCleanerBlockEntity;
import lostworlds.server.block.entity.FossilGrinderBlockEntity;
import lostworlds.server.block.properties.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public abstract class MachineBlock extends Block implements EntityBlock {
	protected static final Map<Block, Map<Direction, VoxelShape>> SHAPES = new HashMap<Block, Map<Direction, VoxelShape>>();
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty ON = ModBlockStateProperties.ON;

	public MachineBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(ON, Boolean.valueOf(false)).setValue(HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES.get(this).get(state.getValue(HORIZONTAL_FACING));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation direction) {
		return state.setValue(HORIZONTAL_FACING, direction.rotate(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}

	protected static VoxelShape calculateShapes(Direction to, VoxelShape shape) {
		VoxelShape[] buffer = new VoxelShape[] { shape, Shapes.empty() };

		int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
		for (int i = 0; i < times; i++) {
			buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1], Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
			buffer[0] = buffer[1];
			buffer[1] = Shapes.empty();
		}

		return buffer[0];
	}

	protected void runCalculation(VoxelShape shape) {
		SHAPES.put(this, new HashMap<Direction, VoxelShape>());
		Map<Direction, VoxelShape> facingMap = SHAPES.get(this);
		for (Direction direction : Direction.values()) {
			facingMap.put(direction, calculateShapes(direction, shape));
		}
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int i1, int i2) {
		super.triggerEvent(state, world, pos, i1, i2);
		BlockEntity tileentity = world.getBlockEntity(pos);
		return tileentity == null ? false : tileentity.triggerEvent(i1, i2);
	}

	@Nullable
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		return tileentity instanceof MenuProvider ? (MenuProvider) tileentity : null;
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (!world.isClientSide) {
			BlockEntity tile = world.getBlockEntity(pos);
			if (tile instanceof FossilCleanerBlockEntity) {
				NetworkHooks.openGui((ServerPlayer) player, (MenuProvider) tile, pos);
				return InteractionResult.SUCCESS;
			} else if (tile instanceof FossilGrinderBlockEntity) {
				NetworkHooks.openGui((ServerPlayer) player, (MenuProvider) tile, pos);
				return InteractionResult.SUCCESS;
			} else if (tile instanceof DNAExtractorBlockEntity) {
				NetworkHooks.openGui((ServerPlayer) player, (MenuProvider) tile, pos);
				return InteractionResult.SUCCESS;
			} else if (tile instanceof AnalyzerBlockEntity) {
				NetworkHooks.openGui((ServerPlayer) player, (MenuProvider) tile, pos);
				return InteractionResult.SUCCESS;
			} else if (tile instanceof DNAInjectorBlockEntity) {
				NetworkHooks.openGui((ServerPlayer) player, (MenuProvider) tile, pos);
				return InteractionResult.SUCCESS;
			} else if (tile instanceof CultivatorBlockEntity) {
				NetworkHooks.openGui((ServerPlayer) player, (MenuProvider) tile, pos);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		if (stack.hasCustomHoverName()) {
			BlockEntity tileentity = world.getBlockEntity(pos);
			if (tileentity instanceof FossilCleanerBlockEntity) {
				((FossilCleanerBlockEntity) tileentity).setCustomName(stack.getHoverName());
			} else if (tileentity instanceof FossilGrinderBlockEntity) {
				((FossilGrinderBlockEntity) tileentity).setCustomName(stack.getHoverName());
			} else if (tileentity instanceof DNAExtractorBlockEntity) {
				((DNAExtractorBlockEntity) tileentity).setCustomName(stack.getHoverName());
			} else if (tileentity instanceof AnalyzerBlockEntity) {
				((AnalyzerBlockEntity) tileentity).setCustomName(stack.getHoverName());
			} else if (tileentity instanceof DNAInjectorBlockEntity) {
				((DNAInjectorBlockEntity) tileentity).setCustomName(stack.getHoverName());
			} else if (tileentity instanceof CultivatorBlockEntity) {
				((CultivatorBlockEntity) tileentity).setCustomName(stack.getHoverName());
			}
		}
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean b) {
		if (!state.is(newState.getBlock())) {
			BlockEntity tileentity = world.getBlockEntity(pos);
			if (tileentity instanceof FossilCleanerBlockEntity) {
				Containers.dropContents(world, pos, (FossilCleanerBlockEntity) tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			} else if (tileentity instanceof FossilGrinderBlockEntity) {
				Containers.dropContents(world, pos, (FossilGrinderBlockEntity) tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			} else if (tileentity instanceof DNAExtractorBlockEntity) {
				Containers.dropContents(world, pos, (DNAExtractorBlockEntity) tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			} else if (tileentity instanceof AnalyzerBlockEntity) {
				Containers.dropContents(world, pos, (AnalyzerBlockEntity) tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			} else if (tileentity instanceof DNAInjectorBlockEntity) {
				Containers.dropContents(world, pos, (DNAInjectorBlockEntity) tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			} else if (tileentity instanceof CultivatorBlockEntity) {
				Containers.dropContents(world, pos, (CultivatorBlockEntity) tileentity);
				world.updateNeighbourForOutputSignal(pos, this);
			}

			super.onRemove(state, world, pos, newState, b);
		}
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {
		if (LostWorldsConfig.CLIENT_CONFIG.machineSounds.get()) {
			if (state.getValue(ON)) {
				double d0 = (double) pos.getX() + 0.5D;
				double d1 = (double) pos.getY();
				double d2 = (double) pos.getZ() + 0.5D;
				if (rand.nextDouble() < 0.1D) {
					world.playLocalSound(d0, d1, d2, LostWorldsSounds.MACHINE_WHIRLING.get(), SoundSource.BLOCKS, 0.5F, 0.5F, false);
				}
			}
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, ON);
	}
}
