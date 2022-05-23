package lostworlds.server.impl.block.softstone;

import java.util.Random;

import lostworlds.api.APISoftStoneFunction;
import lostworlds.server.block.PotentialPart;
import lostworlds.server.block.SoftStoneBlock;
import lostworlds.server.entity.fossil.Fossil;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.item.LostWorldsEnchantments;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class FossilStoneFunction implements APISoftStoneFunction {
	@Override
	public void doFunction(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack) {
		Random rand = new Random();
		PotentialPart part = state.getValue(SoftStoneBlock.POTENTIAL_PART);
		DinoTypes creature = state.getValue(SoftStoneBlock.POTENTIAL_CREATURE);

		if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.CURSE_OF_BREAKING.get(), stack) == 1) {
			int breakchance = rand.nextInt(2);
			if (breakchance != 0) {
				if (part == PotentialPart.ARM) {
					Fossil fossil = creature.getDirtyArmBones().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.LEG) {
					Fossil fossil = creature.getDirtyLegBones().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.RIB_CAGE) {
					Fossil fossil = creature.getDirtyRibCage().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.TAIL) {
					Fossil fossil = creature.getDirtyTail().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.SKULL) {
					Fossil fossil = creature.getDirtySkull().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else {
					SoftStoneBlock.breakStone(world, pos, state);
				}
			}
		} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 3) {
			if (part == PotentialPart.ARM) {
				Fossil fossil = creature.getDirtyArmBones().get().create(world);
				fossil.setAge(0);
				fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				world.addFreshEntity(fossil);
			} else if (part == PotentialPart.LEG) {
				Fossil fossil = creature.getDirtyLegBones().get().create(world);
				fossil.setAge(0);
				fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				world.addFreshEntity(fossil);
			} else if (part == PotentialPart.RIB_CAGE) {
				Fossil fossil = creature.getDirtyRibCage().get().create(world);
				fossil.setAge(0);
				fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				world.addFreshEntity(fossil);
			} else if (part == PotentialPart.TAIL) {
				Fossil fossil = creature.getDirtyTail().get().create(world);
				fossil.setAge(0);
				fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				world.addFreshEntity(fossil);
			} else if (part == PotentialPart.SKULL) {
				Fossil fossil = creature.getDirtySkull().get().create(world);
				fossil.setAge(0);
				fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
				world.addFreshEntity(fossil);
			} else {
				SoftStoneBlock.breakStone(world, pos, state);
			}
		} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 2) {
			int lowbreakchance = rand.nextInt(16);
			if (lowbreakchance != 0) {
				if (part == PotentialPart.ARM) {
					Fossil fossil = creature.getDirtyArmBones().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.LEG) {
					Fossil fossil = creature.getDirtyLegBones().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.RIB_CAGE) {
					Fossil fossil = creature.getDirtyRibCage().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.TAIL) {
					Fossil fossil = creature.getDirtyTail().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.SKULL) {
					Fossil fossil = creature.getDirtySkull().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else {
					SoftStoneBlock.breakStone(world, pos, state);
				}
			}
		} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 1) {
			int mediumbreakchance = rand.nextInt(8);
			if (mediumbreakchance != 0) {
				if (part == PotentialPart.ARM) {
					Fossil fossil = creature.getDirtyArmBones().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.LEG) {
					Fossil fossil = creature.getDirtyLegBones().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.RIB_CAGE) {
					Fossil fossil = creature.getDirtyRibCage().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.TAIL) {
					Fossil fossil = creature.getDirtyTail().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.SKULL) {
					Fossil fossil = creature.getDirtySkull().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else {
					SoftStoneBlock.breakStone(world, pos, state);
				}
			}
		} else {
			int normalbreakchance = rand.nextInt(4);
			if (normalbreakchance != 0) {
				if (part == PotentialPart.ARM) {
					Fossil fossil = creature.getDirtyArmBones().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.LEG) {
					Fossil fossil = creature.getDirtyLegBones().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.RIB_CAGE) {
					Fossil fossil = creature.getDirtyRibCage().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.TAIL) {
					Fossil fossil = creature.getDirtyTail().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				} else if (part == PotentialPart.SKULL) {
					Fossil fossil = creature.getDirtySkull().get().create(world);
					fossil.setAge(0);
					fossil.moveTo((double) pos.getX() + 0.3D, (double) pos.getY(), (double) pos.getZ() + 0.3D, 0.0F, 0.0F);
					world.addFreshEntity(fossil);
				}

			} else {
				SoftStoneBlock.breakStone(world, pos, state);
			}
		}
	}
}
