package lostworlds.server.block;

import java.util.Random;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import lostworlds.server.block.properties.ModBlockStateProperties;
import lostworlds.server.entity.terrestrial.PrehistoricEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.ForgeEventFactory;

public class TinyEggBlock extends Block {
	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 3, 16);
	public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
	public static final IntegerProperty EGGS = ModBlockStateProperties.TINY_EGGS;

	private final Lazy<? extends EntityType<? extends PathfinderMob>> entityTypeSupplier;

	public TinyEggBlock(BlockBehaviour.Properties properties, Supplier<? extends EntityType<? extends PathfinderMob>> entity) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, Integer.valueOf(0)).setValue(EGGS, Integer.valueOf(1)));
		this.entityTypeSupplier = Lazy.of(entity::get);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
		this.destroyEgg(world, pos, entity, 100);
		super.stepOn(world, pos, state, entity);
	}

	@Override
	public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float distance) {
		if (!(entity instanceof Zombie)) {
			this.destroyEgg(world, pos, entity, 3);
		}

		super.fallOn(world, state, pos, entity, distance);
	}

	private void destroyEgg(Level world, BlockPos pos, Entity entity, int amount) {
		if (this.canDestroyEgg(world, entity)) {
			if (!world.isClientSide && world.random.nextInt(amount) == 0) {
				BlockState blockstate = world.getBlockState(pos);
				if (blockstate.is(this)) {
					this.decreaseEggs(world, pos, blockstate);
				}
			}
		}
	}

	private void decreaseEggs(Level world, BlockPos pos, BlockState state) {
		world.playSound((Player) null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		int i = state.getValue(EGGS);
		if (i <= 1) {
			world.destroyBlock(pos, false);
		} else {
			world.setBlock(pos, state.setValue(EGGS, Integer.valueOf(i - 1)), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random rand) {
		if (this.shouldUpdateHatchLevel(world) && onNest(world, pos)) {
			int i = state.getValue(HATCH);
			if (i < 2) {
				world.playSound((Player) null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + rand.nextFloat() * 0.2F);
				world.setBlock(pos, state.setValue(HATCH, Integer.valueOf(i + 1)), 2);
			} else {
				world.playSound((Player) null, pos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + rand.nextFloat() * 0.2F);
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

	public static boolean onNest(BlockGetter blockReader, BlockPos pos) {
		return isNest(blockReader, pos.below());
	}

	public static boolean isNest(BlockGetter blockReader, BlockPos pos) {
		return blockReader.getBlockState(pos).is(LostWorldsBlocks.NESTING_BLOCK.get());
	}

	@Override
	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState newState, boolean b) {
		if (onNest(world, pos) && !world.isClientSide) {
			world.levelEvent(2005, pos, 0);
		}
	}

	private boolean shouldUpdateHatchLevel(Level world) {
		float f = world.getTimeOfDay(1.0F);
		if ((double) f < 0.69D && (double) f > 0.65D) {
			return true;
		} else {
			return world.random.nextInt(500) == 0;
		}
	}

	@Override
	public void playerDestroy(Level world, Player entity, BlockPos pos, BlockState state, @Nullable BlockEntity tileEntity, ItemStack stack) {
		super.playerDestroy(world, entity, pos, state, tileEntity, stack);
		this.decreaseEggs(world, pos, state);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return context.getItemInHand().getItem() == this.asItem() && state.getValue(EGGS) < 19 ? true : super.canBeReplaced(state, context);
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.valueOf(Math.min(19, blockstate.getValue(EGGS) + 1))) : super.getStateForPlacement(context);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HATCH, EGGS);
	}

	private boolean canDestroyEgg(Level world, Entity entity) {
		if (!(entity instanceof PrehistoricEntity) && !(entity instanceof Bat)) {
			if (!(entity instanceof LivingEntity)) {
				return false;
			} else {
				return entity instanceof Player || ForgeEventFactory.getMobGriefingEvent(world, entity);
			}
		} else {
			return false;
		}
	}
}
