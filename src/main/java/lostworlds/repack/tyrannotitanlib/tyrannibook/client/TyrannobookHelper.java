package lostworlds.repack.tyrannotitanlib.tyrannibook.client;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class TyrannobookHelper {
	public static final String BOOK_COMPOUND = "mantle";
	public static final String BOOK_DATA_COMPOUND = "book";

	public static final String NBT_CURRENT_PAGE = "current_page";

	@Deprecated
	public static String getSavedPage(ItemStack item) {
		return getCurrentSavedPage(item);
	}

	public static String getCurrentSavedPage(@Nullable ItemStack item) {
		if (item != null) {
			if (!item.isEmpty() && item.hasTag()) {
				CompoundNBT bookNBT = item.getOrCreateTag().getCompound(BOOK_COMPOUND).getCompound(BOOK_DATA_COMPOUND);

				if (bookNBT.contains(NBT_CURRENT_PAGE, 8)) {
					return bookNBT.getString(NBT_CURRENT_PAGE);
				}
			}
		}

		return "";
	}

	@Deprecated
	public static void writeSavedPage(ItemStack item, String page) {
		writeSavedPageToBook(item, page);
	}

	public static void writeSavedPageToBook(ItemStack stack, String currentPage) {
		CompoundNBT compoundNBT = stack.getOrCreateTag();

		CompoundNBT mantleCompound = compoundNBT.getCompound(BOOK_COMPOUND);
		CompoundNBT bookCompound = compoundNBT.getCompound(BOOK_DATA_COMPOUND);

		bookCompound.putString(NBT_CURRENT_PAGE, currentPage);

		mantleCompound.put(BOOK_DATA_COMPOUND, bookCompound);
		compoundNBT.put(BOOK_COMPOUND, mantleCompound);
		stack.setTag(compoundNBT);
	}
}
