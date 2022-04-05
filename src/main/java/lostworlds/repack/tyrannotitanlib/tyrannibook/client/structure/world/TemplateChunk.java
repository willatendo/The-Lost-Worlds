package lostworlds.repack.tyrannotitanlib.tyrannibook.client.structure.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.gen.feature.template.Template.BlockInfo;

public class TemplateChunk extends EmptyChunk {
	private final Map<BlockPos, BlockInfo> blocksInChunk;
	private final Map<BlockPos, TileEntity> tiles;
	private final Predicate<BlockPos> shouldShow;

	public TemplateChunk(World worldIn, ChunkPos chunkPos, List<BlockInfo> blocksInChunk, Predicate<BlockPos> shouldShow) {
		super(worldIn, chunkPos);
		this.shouldShow = shouldShow;
		this.blocksInChunk = new HashMap<>();
		this.tiles = new HashMap<>();

		for (BlockInfo info : blocksInChunk) {
			this.blocksInChunk.put(info.pos, info);

			if (info.nbt != null) {
				TileEntity tile = TileEntity.loadStatic(info.state, info.nbt);

				if (tile != null) {
					tile.setLevelAndPosition(worldIn, info.pos);
					this.tiles.put(info.pos, tile);
				}
			}
		}
	}

	@Nonnull
	@Override
	public BlockState getBlockState(@Nonnull BlockPos pos) {
		if (this.shouldShow.test(pos)) {
			BlockInfo result = this.blocksInChunk.get(pos);

			if (result != null)
				return result.state;
		}

		return Blocks.VOID_AIR.defaultBlockState();
	}

	@Nonnull
	@Override
	public FluidState getFluidState(@Nonnull BlockPos pos) {
		return getBlockState(pos).getFluidState();
	}

	@Nullable
	@Override
	public TileEntity getBlockEntity(@Nonnull BlockPos pos, @Nonnull CreateEntityType creationMode) {
		if (!this.shouldShow.test(pos))
			return null;

		return this.tiles.get(pos);
	}
}
