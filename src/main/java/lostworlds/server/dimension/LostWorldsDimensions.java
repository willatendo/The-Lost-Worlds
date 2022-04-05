package lostworlds.server.dimension;

import lostworlds.client.StandardDimensionRenderInfo;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.dimension.cretaceous.CretaceousBiomeProvider;
import lostworlds.server.dimension.cretaceous.CretaceousChunkGenerator;
import lostworlds.server.dimension.jurassic.JurassicBiomeProvider;
import lostworlds.server.dimension.jurassic.JurassicChunkGenerator;
import lostworlds.server.dimension.permian.PermianBiomeProvider;
import lostworlds.server.dimension.permian.PermianChunkGenerator;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraftforge.event.world.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = LostWorldsUtils.ID)
public class LostWorldsDimensions {
	public static final RegistryKey<World> PERMIAN_WORLD = RegistryKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("permian"));
	public static final RegistryKey<DimensionType> PERMIAN_DIMENSION = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("permian"));

	public static final RegistryKey<World> JURASSIC_WORLD = RegistryKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("jurassic"));
	public static final RegistryKey<DimensionType> JURASSIC_DIMENSION = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("jurassic"));

	public static final RegistryKey<World> CRETACEOUS_WORLD = RegistryKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("cretaceous"));
	public static final RegistryKey<DimensionType> CRETACEOUS_DIMENSION = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("cretaceous"));

	public static void initBiomeSourcesAndChunkGenerator() {
		LostWorldsUtils.LOGGER.debug("Registering Mod Biome Sources and Chunk Generators");

		Registry.register(Registry.BIOME_SOURCE, LostWorldsUtils.rL("permian_biome_source"), PermianBiomeProvider.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, LostWorldsUtils.rL("permian_chunk_generator"), PermianChunkGenerator.CODEC);

		Registry.register(Registry.BIOME_SOURCE, LostWorldsUtils.rL("jurassic_biome_source"), JurassicBiomeProvider.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, LostWorldsUtils.rL("jurassic_chunk_generator"), JurassicChunkGenerator.CODEC);

		Registry.register(Registry.BIOME_SOURCE, LostWorldsUtils.rL("cretaceous_biome_source"), CretaceousBiomeProvider.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, LostWorldsUtils.rL("cretaceous_chunk_generator"), CretaceousChunkGenerator.CODEC);
	}

	public static void initClient() {
		DimensionRenderInfo baseRenderer = new StandardDimensionRenderInfo();

		DimensionRenderInfo.EFFECTS.put(LostWorldsUtils.rL("permian_render"), baseRenderer);
		DimensionRenderInfo.EFFECTS.put(LostWorldsUtils.rL("jurassic_render"), baseRenderer);
		DimensionRenderInfo.EFFECTS.put(LostWorldsUtils.rL("cretaceous_render"), baseRenderer);
	}

	@SubscribeEvent
	public static void onSleepFinished(SleepFinishedTimeEvent event) {
		IWorld world = event.getWorld();
		if (world instanceof ServerWorld) {
			ServerWorld serverWorld = (ServerWorld) world;
			if (serverWorld.dimension() == PERMIAN_WORLD) {
				if (world.getLevelData() instanceof DerivedWorldInfo) {
					((DerivedWorldInfo) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
				}
			}

			if (serverWorld.dimension() == JURASSIC_WORLD) {
				if (world.getLevelData() instanceof DerivedWorldInfo) {
					((DerivedWorldInfo) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
				}
			}

			if (serverWorld.dimension() == CRETACEOUS_WORLD) {
				if (world.getLevelData() instanceof DerivedWorldInfo) {
					((DerivedWorldInfo) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
				}
			}
		}
	}
}
