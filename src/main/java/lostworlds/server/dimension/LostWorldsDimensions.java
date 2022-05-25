package lostworlds.server.dimension;

//@EventBusSubscriber(modid = LostWorldsUtils.ID)
public class LostWorldsDimensions {
//	public static final ResourceKey<Level> PERMIAN_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("permian"));
//	public static final ResourceKey<DimensionType> PERMIAN_DIMENSION = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("permian"));
//
//	public static final ResourceKey<Level> JURASSIC_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("jurassic"));
//	public static final ResourceKey<DimensionType> JURASSIC_DIMENSION = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("jurassic"));
//
//	public static final ResourceKey<Level> CRETACEOUS_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, LostWorldsUtils.rL("cretaceous"));
//	public static final ResourceKey<DimensionType> CRETACEOUS_DIMENSION = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, LostWorldsUtils.rL("cretaceous"));

	public static void initBiomeSourcesAndChunkGenerator() {
//		Registry.register(Registry.BIOME_SOURCE, LostWorldsUtils.rL("permian"), PermianBiomeSource.CODEC);
//		Registry.register(Registry.CHUNK_GENERATOR, LostWorldsUtils.rL("permian"), PermianChunkGenerator.CODEC);
//
//		Registry.register(Registry.BIOME_SOURCE, LostWorldsUtils.rL("jurassic"), JurassicBiomeSource.CODEC);
//		Registry.register(Registry.CHUNK_GENERATOR, LostWorldsUtils.rL("jurassic"), JurassicChunkGenerator.CODEC);
//
//		Registry.register(Registry.BIOME_SOURCE, LostWorldsUtils.rL("cretaceous"), CretaceousBiomeSource.CODEC);
//		Registry.register(Registry.CHUNK_GENERATOR, LostWorldsUtils.rL("cretaceous"), CretaceousChunkGenerator.CODEC);
	}

	public static void initClient() {
//		DimensionSpecialEffects baseRenderer = new StandardDimensionRenderInfo();
//
//		DimensionSpecialEffects.EFFECTS.put(LostWorldsUtils.rL("permian_render"), baseRenderer);
//		DimensionSpecialEffects.EFFECTS.put(LostWorldsUtils.rL("jurassic_render"), baseRenderer);
//		DimensionSpecialEffects.EFFECTS.put(LostWorldsUtils.rL("cretaceous_render"), baseRenderer);
	}

//	@SubscribeEvent
//	public static void onSleepFinished(SleepFinishedTimeEvent event) {
//		LevelAccessor world = event.getWorld();
//		if (world instanceof ServerLevel) {
//			ServerLevel serverWorld = (ServerLevel) world;
//			if (serverWorld.dimension() == PERMIAN_WORLD) {
//				if (world.getLevelData() instanceof DerivedLevelData) {
//					((DerivedLevelData) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
//				}
//			}
//
//			if (serverWorld.dimension() == JURASSIC_WORLD) {
//				if (world.getLevelData() instanceof DerivedLevelData) {
//					((DerivedLevelData) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
//				}
//			}
//
//			if (serverWorld.dimension() == CRETACEOUS_WORLD) {
//				if (world.getLevelData() instanceof DerivedLevelData) {
//					((DerivedLevelData) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
//				}
//			}
//		}
//	}
}
