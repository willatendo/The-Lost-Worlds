package lostworlds.client.books.tyranninetwork.util;

import java.util.Optional;

import javax.annotation.Nullable;

import lostworlds.client.books.tyrannibook.TyrannoUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class TileEntityHelper {
	public static <T> Optional<T> getTile(Class<T> clazz, @Nullable IBlockReader world, BlockPos pos) {
		return getTile(clazz, world, pos, false);
	}

	public static <T> Optional<T> getTile(Class<T> clazz, @Nullable IBlockReader world, BlockPos pos, boolean logWrongType) {
		if (!isBlockLoaded(world, pos)) {
			return Optional.empty();
		}

		TileEntity tile = world.getBlockEntity(pos);
		if (tile == null) {
			return Optional.empty();
		}

		if (clazz.isInstance(tile)) {
			return Optional.of(clazz.cast(tile));
		} else if (logWrongType) {
			TyrannoUtils.LOGGER.warn("Unexpected TileEntity class at {}, expected {}, but found: {}", pos, clazz, tile.getClass());
		}

		return Optional.empty();
	}

	public static boolean isBlockLoaded(@Nullable IBlockReader world, BlockPos pos) {
		if (world == null) {
			return false;
		}
		if (world instanceof IWorldReader) {
			return ((IWorldReader) world).hasChunkAt(pos);
		}
		return true;
	}
}
