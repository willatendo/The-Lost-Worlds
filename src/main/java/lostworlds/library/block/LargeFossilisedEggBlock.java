package lostworlds.library.block;

import javax.annotation.Nullable;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.block.properties.ModBlockStateProperties;
import lostworlds.library.entity.terrestrial.PrehistoricEntity;
import lostworlds.library.item.WetPaperItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class LargeFossilisedEggBlock extends Block {
	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 8, 16);
	public static final IntegerProperty EGGS = ModBlockStateProperties.LARGE_EGGS;

	public LargeFossilisedEggBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(EGGS, Integer.valueOf(1)));
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
		world.playSound((PlayerEntity) null, pos, SoundEvents.STONE_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		int i = state.getValue(EGGS);
		if (i <= 1) {
			world.destroyBlock(pos, false);
		} else {
			world.setBlock(pos, state.setValue(EGGS, Integer.valueOf(i - 1)), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		}
	}

	@Override
	public void playerDestroy(World world, PlayerEntity entity, BlockPos pos, BlockState state, @Nullable TileEntity tileEntity, ItemStack stack) {
		super.playerDestroy(world, entity, pos, state, tileEntity, stack);
		this.decreaseEggs(world, pos, state);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext context) {
		return context.getItemInHand().getItem() == this.asItem() && state.getValue(EGGS) < 3 ? true : super.canBeReplaced(state, context);
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.valueOf(Math.min(3, blockstate.getValue(EGGS) + 1))) : super.getStateForPlacement(context);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(EGGS);
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

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult result) {
		if (entity.getItemInHand(hand) != null) {
			Item item = entity.getItemInHand(hand).getItem();
			if (item instanceof WetPaperItem) {
				world.setBlockAndUpdate(pos, BlockInit.LARGE_FOSSILISED_EGG.defaultBlockState().setValue(EGGS, state.getValue(EGGS)));
				world.playSound(entity, pos, SoundEvents.WOOL_PLACE, SoundCategory.BLOCKS, 0.7F, 1.0F);

				if (!entity.abilities.instabuild) {
					ItemStack stack = entity.getItemInHand(hand);
					stack.shrink(1);
				}
				return ActionResultType.SUCCESS;
			}
		}
		return super.use(state, world, pos, entity, hand, result);
	}
}
