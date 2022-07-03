package lostworlds.server.impl.block.softstone;

import java.util.Random;

import lostworlds.api.APISoftStoneFunction;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.SoftStoneBlock;
import lostworlds.server.entity.utils.enums.AncientCreatures;
import lostworlds.server.item.LostWorldsEnchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class FossilizedNautilusStoneFunction implements APISoftStoneFunction {
	@Override
	public void doFunction(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack) {
		Random rand = new Random();
		AncientCreatures creature = state.getValue(SoftStoneBlock.POTENTIAL_CREATURE);

		if (creature == AncientCreatures.NAUTILUS) {
			if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.CURSE_OF_BREAKING.get(), stack) == 1) {
				int breakchance = rand.nextInt(2);
				if (breakchance != 0) {
					Block.popResource(world, pos, LostWorldsBlocks.FOSSILIZED_NAUTILUS_SHELL.asStack());
				} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 3) {
					Block.popResource(world, pos, LostWorldsBlocks.FOSSILIZED_NAUTILUS_SHELL.asStack());
				} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 2) {
					int lowbreakchance = rand.nextInt(16);
					if (lowbreakchance != 0) {
						Block.popResource(world, pos, LostWorldsBlocks.FOSSILIZED_NAUTILUS_SHELL.asStack());
					}
				} else if (EnchantmentHelper.getItemEnchantmentLevel(LostWorldsEnchantments.PRECISION.get(), stack) == 1) {
					int mediumbreakchance = rand.nextInt(8);
					if (mediumbreakchance != 0) {
						Block.popResource(world, pos, LostWorldsBlocks.FOSSILIZED_NAUTILUS_SHELL.asStack());
					}
				} else {
					int normalbreakchance = rand.nextInt(4);
					if (normalbreakchance != 0) {
						Block.popResource(world, pos, LostWorldsBlocks.FOSSILIZED_NAUTILUS_SHELL.asStack());
					} else {
						SoftStoneBlock.breakStone(world, pos, state);
					}
				}
			}
		}
	}
}
