package lostworlds.server.util.registrate;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.DyeColor;

public class DyedBlockList<T extends Block> implements Iterable<BlockEntry<T>> {
	private static final int COLOR_AMOUNT = DyeColor.values().length;

	private final BlockEntry<?>[] values = new BlockEntry<?>[COLOR_AMOUNT];

	public DyedBlockList(Function<DyeColor, BlockEntry<? extends T>> filler) {
		for (DyeColor color : DyeColor.values()) {
			this.values[color.ordinal()] = filler.apply(color);
		}
	}

	public BlockEntry<T> get(DyeColor color) {
		return (BlockEntry<T>) this.values[color.ordinal()];
	}

	public boolean contains(Block block) {
		for (BlockEntry<?> entry : this.values) {
			if (entry.is(block)) {
				return true;
			}
		}
		return false;
	}

	public BlockEntry<T>[] toArray() {
		return (BlockEntry<T>[]) Arrays.copyOf(this.values, this.values.length);
	}

	@Override
	public Iterator<BlockEntry<T>> iterator() {
		return new Iterator<BlockEntry<T>>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < values.length;
			}

			@Override
			public BlockEntry<T> next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return (BlockEntry<T>) values[index++];
			}
		};
	}
}
