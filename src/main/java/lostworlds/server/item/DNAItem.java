package lostworlds.server.item;

import java.util.List;
import java.util.Random;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class DNAItem extends Item {
	public DNAItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag) {
		String tag = "";
		if (stack.getTag() == null) {
			tag = getGeneticCode(stack);
		} else {
			tag = stack.getTag().getString("Genetics");
		}

		text.add(LostWorldsUtils.cTCA("item", "dna.code", tag, TextFormatting.GRAY));
	}

	@Override
	public void fillItemCategory(ItemGroup itemGroup, NonNullList<ItemStack> stack) {
		if (this.allowdedIn(itemGroup)) {
			ItemStack item = new ItemStack(this);
			CompoundNBT tag = item.getOrCreateTag();
			String genetics = "CHEATED";

			if (tag == null) {
				tag = new CompoundNBT();
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
		CompoundNBT tag = stack.getOrCreateTag();
		String genetics = randomGenetics(new Random());

		if (tag == null) {
			tag = new CompoundNBT();
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
