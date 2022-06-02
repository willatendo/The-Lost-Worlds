package lostworlds.client.book.structure.level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.EmptyLevelChunk;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo;
import net.minecraft.world.level.material.FluidState;

public class TemplateChunk extends EmptyLevelChunk {

	private final Map<BlockPos, StructureBlockInfo> blocksInChunk;
	private final Map<BlockPos, BlockEntity> tiles;
	private final Predicate<BlockPos> shouldShow;

	public TemplateChunk(Level level, ChunkPos chunkPos, Holder<Biome> biome, List<StructureBlockInfo> blocksInChunk, Predicate<BlockPos> shouldShow) {
		super(level, chunkPos, biome);
		this.shouldShow = shouldShow;
		this.blocksInChunk = new HashMap<>();
		this.tiles = new HashMap<>();

		for (StructureBlockInfo info : blocksInChunk) {
			this.blocksInChunk.put(info.pos, info);

			if (info.nbt != null) {
				BlockEntity tile = BlockEntity.loadStatic(info.pos, info.state, info.nbt);

				if (tile != null) {
					tile.setLevel(level);
					this.tiles.put(info.pos, tile);
				}
			}
		}
	}

	@Override
	public BlockState getBlockState(BlockPos pos) {
		if (this.shouldShow.test(pos)) {
			StructureBlockInfo result = this.blocksInChunk.get(pos);

			if (result != null)
				return result.state;
		}

		return Blocks.VOID_AIR.defaultBlockState();
	}

	@Override
	public FluidState getFluidState(BlockPos pos) {
		return getBlockState(pos).getFluidState();
	}

	@Nullable
	@Override
	public BlockEntity getBlockEntity(BlockPos pos, EntityCreationType creationMode) {
		if (!this.shouldShow.test(pos))
			return null;

		return this.tiles.get(pos);
	}
}
