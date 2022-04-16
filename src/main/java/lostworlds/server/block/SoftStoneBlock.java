package lostworlds.server.block;

import java.util.Random;

import lostworlds.server.entity.fossil.FossilEntity;
import lostworlds.server.entity.illager.FossilPoacherEntity;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.entity.utils.enums.TimeEras;
import lostworlds.server.item.HammerItem;
import lostworlds.server.item.LostWorldsEnchantments;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

public class SoftStoneBlock extends Block {
	private static final EnumProperty<TimeEras> ERA = EnumProperty.create("era", TimeEras.class);
	private static final EnumProperty<PotentialPart> POTENTIAL_PART = EnumProperty.create("potential_part", PotentialPart.class);
	private static final EnumProperty<DinoTypes> POTENTIAL_CREATURE = EnumProperty.create("potential_creature", DinoTypes.class);
	private static final EnumProperty<Damage> DAMAGE = EnumProperty.create("damage", Damage.class);

	public SoftStoneBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(ERA, TimeEras.MODERN_MINECRAFT).setValue(POTENTIAL_PART, PotentialPart.NONE).setValue(POTENTIAL_CREATURE, DinoTypes.CHILESAURUS).setValue(DAMAGE, Damage.NONE));
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
				if (blockstate.is(LostWorldsBlocks.SOFT_STONE.get())) {
					this.breakStone(world, pos, blockstate);
				}
			}
		}
	}

	private void breakStone(World world, BlockPos pos, BlockState state) {
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

	private void doBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		Random rand = new Random();
		PotentialPart part = state.getValue(POTENTIAL_PART);
		DinoTypes creature = state.getValue(POTENTIAL_CREATURE);

		if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.CURSE_OF_BREAKING, stack) == 1) {
			int breakchance = rand.nextInt(2);
			if (breakchance != 0) {
				if (part == PotentialPart.ARM) {
					FossilEntity fossil = creature.getDirtyArmBones().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.LEG) {
					FossilEntity fossil = creature.getDirtyLegBones().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.RIB_CAGE) {
					FossilEntity fossil = creature.getDirtyRibCage().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.TAIL) {
					FossilEntity fossil = creature.getDirtyTail().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.SKULL) {
					FossilEntity fossil = creature.getDirtySkull().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else {
					this.breakStone(world, pos, state);
				}
			}
		} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION, stack) == 3) {
			if (part == PotentialPart.ARM) {
				FossilEntity fossil = creature.getDirtyArmBones().create(world);
				fossil.setAge(0);
				fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				world.addFreshEntity(fossil);
			} else if (part == PotentialPart.LEG) {
				FossilEntity fossil = creature.getDirtyLegBones().create(world);
				fossil.setAge(0);
				fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				world.addFreshEntity(fossil);
			} else if (part == PotentialPart.RIB_CAGE) {
				FossilEntity fossil = creature.getDirtyRibCage().create(world);
				fossil.setAge(0);
				fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				world.addFreshEntity(fossil);
			} else if (part == PotentialPart.TAIL) {
				FossilEntity fossil = creature.getDirtyTail().create(world);
				fossil.setAge(0);
				fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				world.addFreshEntity(fossil);
			} else if (part == PotentialPart.SKULL) {
				FossilEntity fossil = creature.getDirtySkull().create(world);
				fossil.setAge(0);
				fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				world.addFreshEntity(fossil);
			} else {
				this.breakStone(world, pos, state);
			}
		} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION, stack) == 2) {
			int lowbreakchance = rand.nextInt(16);
			if (lowbreakchance != 0) {
				if (part == PotentialPart.ARM) {
					FossilEntity fossil = creature.getDirtyArmBones().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.LEG) {
					FossilEntity fossil = creature.getDirtyLegBones().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.RIB_CAGE) {
					FossilEntity fossil = creature.getDirtyRibCage().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.TAIL) {
					FossilEntity fossil = creature.getDirtyTail().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.SKULL) {
					FossilEntity fossil = creature.getDirtySkull().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else {
					this.breakStone(world, pos, state);
				}
			}
		} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION, stack) == 1) {
			int mediumbreakchance = rand.nextInt(8);
			if (mediumbreakchance != 0) {
				if (part == PotentialPart.ARM) {
					FossilEntity fossil = creature.getDirtyArmBones().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.LEG) {
					FossilEntity fossil = creature.getDirtyLegBones().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.RIB_CAGE) {
					FossilEntity fossil = creature.getDirtyRibCage().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.TAIL) {
					FossilEntity fossil = creature.getDirtyTail().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.SKULL) {
					FossilEntity fossil = creature.getDirtySkull().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else {
					this.breakStone(world, pos, state);
				}
			}
		} else {
			int normalbreakchance = rand.nextInt(4);
			if (normalbreakchance != 0) {
				if (part == PotentialPart.ARM) {
					FossilEntity fossil = creature.getDirtyArmBones().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.LEG) {
					FossilEntity fossil = creature.getDirtyLegBones().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.RIB_CAGE) {
					FossilEntity fossil = creature.getDirtyRibCage().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.TAIL) {
					FossilEntity fossil = creature.getDirtyTail().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.SKULL) {
					FossilEntity fossil = creature.getDirtySkull().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				}

			} else {
				this.breakStone(world, pos, state);
			}
		}
	}

	@Override
	public void playerDestroy(World world, PlayerEntity entity, BlockPos pos, BlockState state, TileEntity tileentity, ItemStack stack) {
		ItemStack heldItem = entity.getItemInHand(Hand.MAIN_HAND);
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
			this.doBreak(state, (ServerWorld) world, pos, heldItem);
		}
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(ERA, POTENTIAL_PART, POTENTIAL_CREATURE, DAMAGE);
	}

	private boolean canDestroy(World world, Entity entity) {
		if (entity instanceof FossilEntity) {
			return false;
		} else {
			return entity instanceof PlayerEntity || ForgeEventFactory.getMobGriefingEvent(world, entity);
		}
	}
}
