package lostworlds.server.container.recipes;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.NonNullSupplier;

public class RandomItemGenerator {
	private NavigableMap<Float, ItemStack> outputMap = new TreeMap<Float, ItemStack>();
	private float totalWeight;
	private NonNullSupplier<ItemStack> input;

	public RandomItemGenerator(NonNullSupplier<ItemStack> input) {
		this.input = input;
	}

	public RandomItemGenerator addOutput(NonNullSupplier<? extends Item> item) {
		totalWeight += 35.0F;
		outputMap.put(totalWeight, item.get().getDefaultInstance());
		return this;
	}

	public ItemStack getInput() {
		return this.input.get();
	}

	public ItemStack generateOutput(Random random) {
		if (totalWeight < 100) {
			if (random.nextFloat() >= totalWeight * 0.01F) {
				return ItemStack.EMPTY;
			}
		}
		float entry = random.nextFloat() * totalWeight;
		return outputMap.higherEntry(entry).getValue().copy();
	}

	public NavigableMap<Float, ItemStack> getDisplayMap() {
		return outputMap;
	}

	public float getTotalWeight() {
		return totalWeight;
	}
}
