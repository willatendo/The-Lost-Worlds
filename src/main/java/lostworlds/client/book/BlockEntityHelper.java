package lostworlds.client.book;

import java.util.Optional;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntityHelper {
	public static <T> Optional<T> get(Class<T> clazz, @Nullable BlockGetter level, BlockPos pos) {
		return get(clazz, level, pos, false);
	}

	public static <T> Optional<T> get(Class<T> clazz, @Nullable BlockGetter level, BlockPos pos, boolean logWrongType) {
		if (!isBlockLoaded(level, pos)) {
			return Optional.empty();
		}

		BlockEntity tile = level.getBlockEntity(pos);
		if (tile == null) {
			return Optional.empty();
		}

		if (clazz.isInstance(tile)) {
			return Optional.of(clazz.cast(tile));
		}

		return Optional.empty();
	}

	public static boolean isBlockLoaded(@Nullable BlockGetter level, BlockPos pos) {
		if (level == null) {
			return false;
		}
		if (level instanceof LevelReader) {
			return ((LevelReader) level).hasChunkAt(pos);
		}
		return true;
	}

	@Nullable
	public static <HAVE extends BlockEntity, RET extends BlockEntity> BlockEntityTicker<RET> castTicker(BlockEntityType<RET> expected, BlockEntityType<HAVE> have, BlockEntityTicker<? super HAVE> ticker) {
		return have == expected ? (BlockEntityTicker<RET>) ticker : null;
	}

	@Nullable
	public static <HAVE extends BlockEntity, RET extends BlockEntity> BlockEntityTicker<RET> serverTicker(Level level, BlockEntityType<RET> expected, BlockEntityType<HAVE> have, BlockEntityTicker<? super HAVE> ticker) {
		return level.isClientSide ? null : castTicker(expected, have, ticker);
	}
}
