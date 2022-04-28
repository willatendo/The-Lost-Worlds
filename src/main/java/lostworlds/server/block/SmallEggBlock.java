package lostworlds.server.block;

import java.util.Random;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import lostworlds.server.block.properties.ModBlockStateProperties;
import lostworlds.server.entity.terrestrial.PrehistoricEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.ForgeEventFactory;

public class SmallEggBlock extends Block {
	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 4, 16);
	public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
	public static final IntegerProperty EGGS = ModBlockStateProperties.SMALL_EGGS;

	private final Lazy<? extends EntityType<? extends CreatureEntity>> entityTypeSupplier;

	public SmallEggBlock(AbstractBlock.Properties properties, Supplier<EntityType<? extends CreatureEntity>> supplier) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, Integer.valueOf(0)).setValue(EGGS, Integer.valueOf(1)));
		this.entityTypeSupplier = Lazy.of(supplier::get);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void stepOn(World world, BlockPos pos, Entity entity) {
		this.destroyEgg(world, pos, entity, 100);
		super.stepOn(world, pos, entity);
	}

	@Override
	public void fallOn(World world, BlockPos pos, Entity entity, float chance) {
		if (!(entity instanceof ZombieEntity)) {
			this.destroyEgg(world, pos, entity, 3);
		}

		super.fallOn(world, pos, entity, chance);
	}

	private void destroyEgg(World world, BlockPos pos, Entity entity, int amount) {
		if (this.canDestroyEgg(world, entity)) {
			if (!world.isClientSide && world.random.nextInt(amount) == 0) {
				BlockState blockstate = world.getBlockState(pos);
				if (blockstate.is(this)) {
					this.decreaseEggs(world, pos, blockstate);
				}
			}
		}
	}

	private void decreaseEggs(World world, BlockPos pos, BlockState state) {
		world.playSound((PlayerEntity) null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		int i = state.getValue(EGGS);
		if (i <= 1) {
			world.destroyBlock(pos, false);
		} else {
			world.setBlock(pos, state.setValue(EGGS, Integer.valueOf(i - 1)), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		}
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (this.shouldUpdateHatchLevel(world) && onNest(world, pos)) {
			int i = state.getValue(HATCH);
			if (i < 2) {
				world.playSound((PlayerEntity) null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + rand.nextFloat() * 0.2F);
				world.setBlock(pos, state.setValue(HATCH, Integer.valueOf(i + 1)), 2);
			} else {
				world.playSound((PlayerEntity) null, pos, SoundEvents.TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + rand.nextFloat() * 0.2F);
				world.removeBlock(pos, false);
				world.setBlockAndUpdate(pos.below(), Blocks.DIRT.defaultBlockState());

				for (int j = 0; j < state.getValue(EGGS); ++j) {
					world.levelEvent(2001, pos, Block.getId(state));
					PrehistoricEntity entity = (PrehistoricEntity) this.entityTypeSupplier.get().create(world);
					entity.moveTo((double) pos.getX() + 0.3D * 0.2D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(entity);
					entity.setAge(-24000);
				}
			}
		}
	}

	public static boolean onNest(IBlockReader blockReader, BlockPos pos) {
		return isNest(blockReader, pos.below());
	}

	public static boolean isNest(IBlockReader blockReader, BlockPos pos) {
		return blockReader.getBlockState(pos).is(LostWorldsBlocks.NESTING_BLOCK.get());
	}

	@Override
	public void onPlace(BlockState state, World world, BlockPos pos, BlockState newState, boolean b) {
		if (onNest(world, pos) && !world.isClientSide) {
			world.levelEvent(2005, pos, 0);
		}
	}

	private boolean shouldUpdateHatchLevel(World world) {
		float f = world.getTimeOfDay(1.0F);
		if ((double) f < 0.69D && (double) f > 0.65D) {
			return true;
		} else {
			return world.random.nextInt(500) == 0;
		}
	}

	@Override
	public void playerDestroy(World world, PlayerEntity entity, BlockPos pos, BlockState state, @Nullable TileEntity tileEntity, ItemStack stack) {
		super.playerDestroy(world, entity, pos, state, tileEntity, stack);
		this.decreaseEggs(world, pos, state);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext context) {
		return context.getItemInHand().getItem() == this.asItem() && state.getValue(EGGS) < 10 ? true : super.canBeReplaced(state, context);
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.valueOf(Math.min(10, blockstate.getValue(EGGS) + 1))) : super.getStateForPlacement(context);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HATCH, EGGS);
	}

	private boolean canDestroyEgg(World world, Entity entity) {
		if (!(entity instanceof PrehistoricEntity) && !(entity instanceof BatEntity)) {
			if (!(entity instanceof LivingEntity)) {
				return false;
			} else {
				return entity instanceof PlayerEntity || ForgeEventFactory.getMobGriefingEvent(world, entity);
			}
		} else {
			return false;
		}
	}
}
