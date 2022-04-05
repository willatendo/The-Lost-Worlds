package lostworlds.repack.tyrannotitanlib.tyrannibook.client.structure.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mojang.datafixers.util.Pair;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.AbstractChunkProvider;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.feature.template.Template.BlockInfo;
import net.minecraft.world.lighting.WorldLightManager;

public class TemplateChunkProvider extends AbstractChunkProvider {
	private final Map<ChunkPos, IChunk> chunks;
	private final World world;
	private final WorldLightManager lightManager;

	public TemplateChunkProvider(List<BlockInfo> blocks, World world, Predicate<BlockPos> shouldShow) {
		this.world = world;
		this.lightManager = new WorldLightManager(this, true, true);
		Map<ChunkPos, List<BlockInfo>> byChunk = new HashMap<>();

		for (BlockInfo info : blocks) {
			byChunk.computeIfAbsent(new ChunkPos(info.pos), $ -> new ArrayList<>()).add(info);
		}

		this.chunks = byChunk.entrySet().stream().map(e -> Pair.of(e.getKey(), new TemplateChunk(world, e.getKey(), e.getValue(), shouldShow))).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
	}

	@Nullable
	@Override
	public IChunk getChunk(int chunkX, int chunkZ, @Nonnull ChunkStatus requiredStatus, boolean load) {
		return this.chunks.computeIfAbsent(new ChunkPos(chunkX, chunkZ), p -> new EmptyChunk(world, p));
	}

	@Nonnull
	@Override
	public String gatherStats() {
		return "?";
	}

	@Nonnull
	@Override
	public WorldLightManager getLightEngine() {
		return this.lightManager;
	}

	@Nonnull
	@Override
	public IBlockReader getLevel() {
		return this.world;
	}
}
