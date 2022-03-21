package lostworlds.server.structure;

import com.mojang.serialization.Codec;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class BlackMarketStructure extends Structure<NoFeatureConfig> {
	public BlackMarketStructure(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public IStartFactory<NoFeatureConfig> getStartFactory() {
		return BlackMarketStructure.Start::new;
	}

	@Override
	public Decoration step() {
		return Decoration.SURFACE_STRUCTURES;
	}

	public static class Start extends StructureStart<NoFeatureConfig> {
		public Start(Structure<NoFeatureConfig> structure, int x, int z, MutableBoundingBox box, int i3, long seed) {
			super(structure, x, z, box, i3, seed);
		}

		@Override
		public void generatePieces(DynamicRegistries registries, ChunkGenerator generator, TemplateManager manager, int chunkX, int chunkY, Biome biome, NoFeatureConfig config) {
			BlockPos blockpos = new BlockPos(chunkX * 16, 90, chunkY * 16);
			Rotation rotation = Rotation.values()[this.random.nextInt((Rotation.values()).length)];
			BlackMarketPeice.addStructure(manager, blockpos, rotation, pieces, random, biome);
			this.calculateBoundingBox();
		}
	}
}
