package lostworlds.server.container.recipes;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Supplier;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RandomItemGenerator {
	private NavigableMap<Float, Supplier<ItemStack>> outputMap = new TreeMap<Float, Supplier<ItemStack>>();
	private float totalWeight;
	private Supplier<ItemStack> input;

	public RandomItemGenerator(Supplier<ItemStack> input) {
		this.input = input;
	}

	public RandomItemGenerator addOutput(Supplier<Item> item) {
		totalWeight += 35.0F;
		outputMap.put(totalWeight, () -> item.get().getDefaultInstance());
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
		return outputMap.higherEntry(entry).getValue().get().copy();
	}

	public NavigableMap<Float, Supplier<ItemStack>> getDisplayMap() {
		return outputMap;
	}

	public float getTotalWeight() {
		return totalWeight;
	}
}
