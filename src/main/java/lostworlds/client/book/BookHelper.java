package lostworlds.client.book;

import javax.annotation.Nullable;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class BookHelper {
	public static final String BOOK_COMPOUND = LostWorldsUtils.ID;
	public static final String BOOK_DATA_COMPOUND = "book";

	public static final String NBT_CURRENT_PAGE = "current_page";

	public static String getCurrentSavedPage(@Nullable ItemStack item) {
		if (item != null) {
			if (!item.isEmpty() && item.hasTag()) {
				CompoundTag bookNBT = item.getOrCreateTag().getCompound(BOOK_COMPOUND).getCompound(BOOK_DATA_COMPOUND);

				if (bookNBT.contains(NBT_CURRENT_PAGE, 8)) {
					return bookNBT.getString(NBT_CURRENT_PAGE);
				}
			}
		}

		return "";
	}

	public static void writeSavedPageToBook(ItemStack stack, String currentPage) {
		CompoundTag compoundNBT = stack.getOrCreateTag();

		CompoundTag mantleCompound = compoundNBT.getCompound(BOOK_COMPOUND);
		CompoundTag bookCompound = compoundNBT.getCompound(BOOK_DATA_COMPOUND);

		bookCompound.putString(NBT_CURRENT_PAGE, currentPage);

		mantleCompound.put(BOOK_DATA_COMPOUND, bookCompound);
		compoundNBT.put(BOOK_COMPOUND, mantleCompound);
		stack.setTag(compoundNBT);
	}
}
