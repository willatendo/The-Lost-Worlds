package lostworlds.server.item;

import java.util.List;
import java.util.Random;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class DNAItem extends Item {
	public DNAItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
		String tag = "";
		if (stack.getTag() == null) {
			tag = getGeneticCode(stack);
		} else {
			tag = stack.getTag().getString("Genetics");
		}

		text.add(LostWorldsUtils.gTCA("item", "dna.code", tag));
	}

	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> stack) {
		if (this.allowdedIn(tab)) {
			ItemStack item = new ItemStack(this);
			CompoundTag tag = item.getOrCreateTag();
			String genetics = "CHEATED";

			if (tag == null) {
				tag = new CompoundTag();
			}

			if (tag.contains("Genetics")) {
				genetics = tag.getString("Genetics");
			} else {
				tag.putString("Genetics", genetics);
			}

			item.setTag(tag);

			stack.add(item);
		}
	}

	public static String getGeneticCode(ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();
		String genetics = randomGenetics(new Random());

		if (tag == null) {
			tag = new CompoundTag();
		}

		if (tag.contains("Genetics")) {
			genetics = tag.getString("Genetics");
		} else {
			tag.putString("Genetics", genetics);
		}

		stack.setTag(tag);

		return genetics;
	}

	public static String randomGenetics(Random random) {
		StringBuilder genetics = new StringBuilder();

		for (int i = 0; i < 10; i++) {
			int character = random.nextInt(4);

			switch (character) {
			case 0:
				genetics.append("A");
				break;
			case 1:
				genetics.append("C");
				break;
			case 2:
				genetics.append("G");
				break;
			case 3:
				genetics.append("T");
				break;
			}
		}

		return genetics.toString();
	}
}
