package lostworlds.server.block;

import javax.annotation.Nullable;

import lostworlds.server.block.properties.ModBlockStateProperties;
import lostworlds.server.entity.illager.FossilPoacherEntity;
import lostworlds.server.entity.terrestrial.PrehistoricEntity;
import lostworlds.server.item.WetPaperItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.ForgeEventFactory;

public class SmallFossilizedEggBlock extends Block {
	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 4, 16);
	public static final IntegerProperty EGGS = ModBlockStateProperties.SMALL_EGGS;

	public SmallFossilizedEggBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(EGGS, Integer.valueOf(1)));
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
		if (!(entity instanceof FossilPoacherEntity)) {
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
		world.playSound((Player) null, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		int i = state.getValue(EGGS);
		if (i <= 1) {
			world.destroyBlock(pos, false);
		} else {
			world.setBlock(pos, state.setValue(EGGS, Integer.valueOf(i - 1)), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		}
	}

	@Override
	public void playerDestroy(Level world, Player entity, BlockPos pos, BlockState state, @Nullable BlockEntity tileEntity, ItemStack stack) {
		super.playerDestroy(world, entity, pos, state, tileEntity, stack);
		this.decreaseEggs(world, pos, state);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return context.getItemInHand().getItem() == this.asItem() && state.getValue(EGGS) < 10 ? true : super.canBeReplaced(state, context);
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.valueOf(Math.min(10, blockstate.getValue(EGGS) + 1))) : super.getStateForPlacement(context);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(EGGS);
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

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult result) {
		if (entity.getItemInHand(hand) != null) {
			Item item = entity.getItemInHand(hand).getItem();
			if (item instanceof WetPaperItem) {
				world.setBlockAndUpdate(pos, LostWorldsBlocks.SMALL_PLASTERED_FOSSILISED_EGG.getDefaultState().setValue(EGGS, state.getValue(EGGS)));
				world.playSound(entity, pos, SoundEvents.WOOL_PLACE, SoundSource.BLOCKS, 0.7F, 1.0F);

				if (!entity.isCreative()) {
					ItemStack stack = entity.getItemInHand(hand);
					stack.shrink(1);
				}
				return InteractionResult.SUCCESS;
			}
		}
		return super.use(state, world, pos, entity, hand, result);
	}
}
