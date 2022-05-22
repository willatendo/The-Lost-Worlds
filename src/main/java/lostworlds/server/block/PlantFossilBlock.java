package lostworlds.server.block;

import java.util.Random;

import lostworlds.server.entity.illager.FossilPoacherEntity;
import lostworlds.server.entity.utils.enums.TimeEras;
import lostworlds.server.item.LostWorldsEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.event.ForgeEventFactory;

public class PlantFossilBlock extends Block {
	public static final EnumProperty<TimeEras> ERA = EnumProperty.create("era", TimeEras.class);
	public static final EnumProperty<Plants> POTENTIAL_PLANT = EnumProperty.create("potential_plant", Plants.class);
	public static final EnumProperty<Damage> DAMAGE = EnumProperty.create("damage", Damage.class);

	public PlantFossilBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(ERA, TimeEras.MODERN_MINECRAFT).setValue(POTENTIAL_PLANT, Plants.ALETHOPTERIS).setValue(DAMAGE, Damage.NONE));
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
				if (blockstate.is(LostWorldsBlocks.PLANT_FOSSIL.get())) {
					this.breakPlant(world, pos, blockstate);
				}
			}
		}
	}

	private void breakPlant(Level world, BlockPos pos, BlockState state) {
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

	@Override
	public void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack) {
		Plants plant = state.getValue(POTENTIAL_PLANT);
		Damage damage = state.getValue(DAMAGE);
		Random rand = new Random();
		int highbreakchance = rand.nextInt(2);
		int breakchance = rand.nextInt(8);
		int midbreakchance = rand.nextInt(16);
		int lowbreakchance = rand.nextInt(32);

		if (highbreakchance == 1 && EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.CURSE_OF_BREAKING.get(), stack) == 1) {
			world.destroyBlock(pos, false);
		}

		if (breakchance == 7 && EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 0) {
			world.destroyBlock(pos, false);
		} else if (midbreakchance == 15 && EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 1) {
			world.destroyBlock(pos, false);
		} else if (lowbreakchance == 31 && EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 2) {
			world.destroyBlock(pos, false);
		} else {
			if (damage == Damage.NONE) {
				this.popResource(world, pos, plant.getDrop().get().getDefaultInstance());
			}
			if (damage == Damage.CHIPPED) {
				int chance = rand.nextInt(5);
				if (chance < 4) {
					this.popResource(world, pos, plant.getDrop().get().getDefaultInstance());
				}
			}
			if (damage == Damage.SLIGHTLY) {
				int chance = rand.nextInt(5);
				if (chance < 3) {
					this.popResource(world, pos, plant.getDrop().get().getDefaultInstance());
				}
			}
			if (damage == Damage.CRACKED) {
				int chance = rand.nextInt(5);
				if (chance < 2) {
					this.popResource(world, pos, plant.getDrop().get().getDefaultInstance());
				}
			}
			if (damage == Damage.DAMAGED) {
				int chance = rand.nextInt(5);
				if (chance < 1) {
					this.popResource(world, pos, plant.getDrop().get().getDefaultInstance());
				}
			}
		}

	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(ERA, POTENTIAL_PLANT, DAMAGE);
	}

	private boolean canDestroy(Level world, Entity entity) {
		return entity instanceof Player || ForgeEventFactory.getMobGriefingEvent(world, entity);
	}
}
