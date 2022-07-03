package lostworlds.server.block;

import static lostworlds.api.APIRegistry.SOFT_STONE_FUNCTIONS;

import java.util.Random;

import lostworlds.server.entity.fossil.Fossil;
import lostworlds.server.entity.illager.FossilPoacherEntity;
import lostworlds.server.entity.utils.enums.AncientCreatures;
import lostworlds.server.entity.utils.enums.TimeEras;
import lostworlds.server.item.HammerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.event.ForgeEventFactory;

public class SoftStoneBlock extends Block {
	public static final EnumProperty<TimeEras> ERA = EnumProperty.create("era", TimeEras.class);
	public static final EnumProperty<PotentialPart> POTENTIAL_PART = EnumProperty.create("potential_part", PotentialPart.class);
	public static final EnumProperty<AncientCreatures> POTENTIAL_CREATURE = EnumProperty.create("potential_creature", AncientCreatures.class);
	public static final EnumProperty<Damage> DAMAGE = EnumProperty.create("damage", Damage.class);

	public SoftStoneBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(ERA, TimeEras.MODERN_MINECRAFT).setValue(POTENTIAL_PART, PotentialPart.NONE).setValue(POTENTIAL_CREATURE, AncientCreatures.ALLOSAURUS).setValue(DAMAGE, Damage.NONE));
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
		this.destroy(world, pos, entity, 100);
		super.stepOn(world, pos, state, entity);
	}

	@Override
	public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float distance) {
		if (!(entity instanceof FossilPoacherEntity)) {
			this.destroy(world, pos, entity, 3);
		}

		super.fallOn(world, state, pos, entity, distance);
	}

	private void destroy(Level world, BlockPos pos, Entity entity, int distance) {
		if (this.canDestroy(world, entity)) {
			if (!world.isClientSide && world.random.nextInt(distance) == 0) {
				BlockState blockstate = world.getBlockState(pos);
				if (blockstate.is(LostWorldsBlocks.SOFT_STONE.get())) {
					this.breakStone(world, pos, blockstate);
				}
			}
		}
	}

	public static void breakStone(Level world, BlockPos pos, BlockState state) {
		world.playSound((Player) null, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
		Damage damage = state.getValue(DAMAGE);
		if (damage == Damage.COMPLETELY) {
			world.destroyBlock(pos, false);
		} else if (damage == Damage.DAMAGED) {
			world.setBlock(pos, state.setValue(DAMAGE, Damage.COMPLETELY), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		} else if (damage == Damage.CRACKED) {
			world.setBlock(pos, state.setValue(DAMAGE, Damage.DAMAGED), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		} else if (damage == Damage.SLIGHTLY) {
			world.setBlock(pos, state.setValue(DAMAGE, Damage.CRACKED), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		} else if (damage == Damage.CHIPPED) {
			world.setBlock(pos, state.setValue(DAMAGE, Damage.SLIGHTLY), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		} else if (damage == Damage.NONE) {
			world.setBlock(pos, state.setValue(DAMAGE, Damage.CHIPPED), 2);
			world.levelEvent(2001, pos, Block.getId(state));
		}
	}

	private void doBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack) {
		Random rand = new Random();
		int drop = rand.nextInt(SOFT_STONE_FUNCTIONS.size());
		SOFT_STONE_FUNCTIONS.get(drop).doFunction(state, world, pos, stack);
	}

	@Override
	public void playerDestroy(Level world, Player entity, BlockPos pos, BlockState state, BlockEntity tileentity, ItemStack stack) {
		ItemStack heldItem = entity.getItemInHand(InteractionHand.MAIN_HAND);
		if (!(heldItem.getItem() instanceof HammerItem)) {
			BlockState up = world.getBlockState(pos.above());
			BlockState below = world.getBlockState(pos.below());
			BlockState north = world.getBlockState(pos.north());
			BlockState south = world.getBlockState(pos.south());
			BlockState east = world.getBlockState(pos.east());
			BlockState west = world.getBlockState(pos.west());
			if (up.is(LostWorldsBlocks.SOFT_STONE.get())) {
				world.destroyBlock(pos.above(), false);
			}

			if (below.is(LostWorldsBlocks.SOFT_STONE.get())) {
				world.destroyBlock(pos.below(), false);
			}

			if (north.is(LostWorldsBlocks.SOFT_STONE.get())) {
				world.destroyBlock(pos.north(), false);
			}

			if (south.is(LostWorldsBlocks.SOFT_STONE.get())) {
				world.destroyBlock(pos.south(), false);
			}

			if (east.is(LostWorldsBlocks.SOFT_STONE.get())) {
				world.destroyBlock(pos.east(), false);
			}

			if (west.is(LostWorldsBlocks.SOFT_STONE.get())) {
				world.destroyBlock(pos.west(), false);
			}
		} else {
			this.doBreak(state, (ServerLevel) world, pos, heldItem);
		}
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(ERA, POTENTIAL_PART, POTENTIAL_CREATURE, DAMAGE);
	}

	private boolean canDestroy(Level world, Entity entity) {
		if (entity instanceof Fossil) {
			return false;
		} else {
			return entity instanceof Player || ForgeEventFactory.getMobGriefingEvent(world, entity);
		}
	}
}
