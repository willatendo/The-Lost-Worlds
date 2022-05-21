package lostworlds.server.impl.block.softstone;

import java.util.Random;

import lostworlds.api.APISoftStoneFunction;
import lostworlds.server.block.SoftStoneBlock;
import lostworlds.server.entity.utils.enums.DinoTypes;
import lostworlds.server.item.LostWorldsEnchantments;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class AnomalocarisFossilStoneFunction implements APISoftStoneFunction {
	@Override
	public void doFunction(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
		Random rand = new Random();
		DinoTypes creature = state.getValue(SoftStoneBlock.POTENTIAL_CREATURE);

		if (creature == DinoTypes.ANOMALOCARIS) {
			if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.CURSE_OF_BREAKING.get(), stack) == 1) {
				int breakchance = rand.nextInt(2);
				if (breakchance != 0) {
					Block.popResource(world, pos, DinoTypes.ANOMALOCARIS.getSkeletonPick().get().asItem().getDefaultInstance());
				} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 3) {
					Block.popResource(world, pos, DinoTypes.ANOMALOCARIS.getSkeletonPick().get().asItem().getDefaultInstance());
				} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 2) {
					int lowbreakchance = rand.nextInt(16);
					if (lowbreakchance != 0) {
						Block.popResource(world, pos, DinoTypes.ANOMALOCARIS.getSkeletonPick().get().asItem().getDefaultInstance());
					}
				} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 1) {
					int mediumbreakchance = rand.nextInt(8);
					if (mediumbreakchance != 0) {
						Block.popResource(world, pos, DinoTypes.ANOMALOCARIS.getSkeletonPick().get().asItem().getDefaultInstance());
					}
				} else {
					int normalbreakchance = rand.nextInt(4);
					if (normalbreakchance != 0) {
						Block.popResource(world, pos, DinoTypes.ANOMALOCARIS.getSkeletonPick().get().asItem().getDefaultInstance());
					} else {
						SoftStoneBlock.breakStone(world, pos, state);
					}
				}
			}
		}
	}
}
