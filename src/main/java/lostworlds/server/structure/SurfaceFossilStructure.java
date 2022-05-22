package lostworlds.server.structure;

import com.mojang.serialization.Codec;

import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import net.minecraft.world.level.levelgen.feature.StructureFeature.StructureStartFactory;

public class SurfaceFossilStructure extends StructureFeature<NoneFeatureConfiguration> {
	public SurfaceFossilStructure(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
		return SurfaceFossilStructure.Start::new;
	}

	@Override
	public Decoration step() {
		return Decoration.SURFACE_STRUCTURES;
	}

	public static class Start extends StructureStart<NoneFeatureConfiguration> {
		public Start(StructureFeature<NoneFeatureConfiguration> structure, int x, int z, BoundingBox box, int i3, long seed) {
			super(structure, x, z, box, i3, seed);
		}

		@Override
		public void generatePieces(RegistryAccess registries, ChunkGenerator generator, StructureManager manager, int chunkX, int chunkY, Biome biome, NoneFeatureConfiguration config) {
			BlockPos blockpos = new BlockPos(chunkX * 16, 90, chunkY * 16);
			Rotation rotation = Rotation.values()[this.random.nextInt((Rotation.values()).length)];
			SurfaceFossilPeice.addStructure(manager, blockpos, rotation, this.pieces, this.random, biome);
			this.calculateBoundingBox();
		}
	}
}
