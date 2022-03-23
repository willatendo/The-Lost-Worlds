package lostworlds.server.block;

import java.util.Random;

import lostworlds.server.entity.illager.FossilPoacherEntity;
import lostworlds.server.entity.utils.enums.TimeEras;
import lostworlds.server.item.LostWorldsEnchantments;
import lostworlds.server.item.tool.ModMaterials;
import lostworlds.server.item.tool.ModToolTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.enchantment.EnchantmentHelper;
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

public class PlantFossilBlock extends Block {
	public static final EnumProperty<TimeEras> ERA = EnumProperty.create("era", TimeEras.class);
	public static final EnumProperty<Plants> POTENTIAL_PLANT = EnumProperty.create("potential_plant", Plants.class);
	public static final EnumProperty<Damage> DAMAGE = EnumProperty.create("damage", Damage.class);

	public PlantFossilBlock() {
		super(AbstractBlock.Properties.of(ModMaterials.SOFT).harvestTool(ModToolTypes.HAMMER).strength(4.0F, 0.0F).noDrops().sound(SoundType.STONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(ERA, TimeEras.MODERN_MINECRAFT).setValue(POTENTIAL_PLANT, Plants.ALETHOPTERIS).setValue(DAMAGE, Damage.NONE));
	}

	@Override
	public void stepOn(World world, BlockPos pos, Entity entity) {
		this.destroy(world, pos, entity, 100);
		super.stepOn(world, pos, entity);
	}

	@Override
	public void fallOn(World world, BlockPos pos, Entity entity, float distance) {
		if (!(entity instanceof FossilPoacherEntity)) {
			this.destroy(world, pos, entity, 3);
		}

		super.fallOn(world, pos, entity, distance);
	}

	private void destroy(World world, BlockPos pos, Entity entity, int distance) {
		if (this.canDestroy(world, entity)) {
			if (!world.isClientSide && world.random.nextInt(distance) == 0) {
				BlockState blockstate = world.getBlockState(pos);
				if (blockstate.is(LostWorldsBlocks.PLANT_FOSSIL)) {
					this.breakPlant(world, pos, blockstate);
				}
			}
		}
	}

	private void breakPlant(World world, BlockPos pos, BlockState state) {
		world.playSound((PlayerEntity) null, pos, SoundEvents.STONE_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + world.random.nextFloat() * 0.2F);
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
	public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		Plants plant = state.getValue(POTENTIAL_PLANT);
		Damage damage = state.getValue(DAMAGE);
		Random rand = new Random();
		int highbreakchance = rand.nextInt(2);
		int breakchance = rand.nextInt(8);
		int midbreakchance = rand.nextInt(16);
		int lowbreakchance = rand.nextInt(32);

		if (highbreakchance == 1 && EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.CURSE_OF_BREAKING, stack) == 1) {
			world.destroyBlock(pos, false);
		}

		if (breakchance == 7 && EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION, stack) == 0) {
			world.destroyBlock(pos, false);
		} else if (midbreakchance == 15 && EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION, stack) == 1) {
			world.destroyBlock(pos, false);
		} else if (lowbreakchance == 31 && EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION, stack) == 2) {
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

	private boolean canDestroy(World world, Entity entity) {
		return entity instanceof PlayerEntity || ForgeEventFactory.getMobGriefingEvent(world, entity);
	}
}
