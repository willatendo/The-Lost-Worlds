package lostworlds.server.biome.surfacebuilders;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsSurfaceBuilders {
	public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, LostWorldsUtils.ID);

	public static final SurfaceBuilder NAKED_PERMIAN_DRIED_PLAINS = new PermianDriedPlainsSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_PERMIAN_MOUNTAINS = new PermianMoutainsSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_PERMIAN_PLAINS = new PermianPlainsSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_PERMIAN_OCEAN = new PermianOceanSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_PERMIAN_MARSH = new PermianMarshSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);

	public static final SurfaceBuilder NAKED_JURASSIC_FOREST = new JurassicForestSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_PLAINS = new JurassicPlainsSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_DESERT = new JurassicDesertSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_MOUNTAINS = new JurassicMountainsSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_ERRODED_MOUNTAINS = new JurassicErrodedMountainsSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_JURASSIC_SWAMP = new JurassicSwampSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);

	public static final SurfaceBuilder NAKED_CRETACEOUS_FOREST = new CretaceousForestSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_GAME_TRAIL = new CretaceousGameTrailSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_PLAINS = new CretaceousPlainsSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_MOUNTAINS = new CretaceousMountainsSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_ERRODED_MOUNTAINS = new CretaceousErrodedMountainsSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_DESERT = new CretaceousDesertSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_RED_DESERT = new CretaceousRedDesertSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);
	public static final SurfaceBuilder NAKED_CRETACEOUS_SWAMP = new CretaceousSwampSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC);

	public static final SurfaceBuilder PERMIAN_DRIED_PLAINS = register("permian_dried_plains_sb", NAKED_PERMIAN_DRIED_PLAINS);
	public static final SurfaceBuilder PERMIAN_MOUNTAINS = register("permian_mountains_sb", NAKED_PERMIAN_MOUNTAINS);
	public static final SurfaceBuilder PERMIAN_PLAINS = register("permian_plains_sb", NAKED_PERMIAN_PLAINS);
	public static final SurfaceBuilder PERMIAN_OCEAN = register("permian_ocean_sb", NAKED_PERMIAN_OCEAN);
	public static final SurfaceBuilder PERMIAN_MARSH = register("permian_marsh_sb", NAKED_PERMIAN_MARSH);

	public static final SurfaceBuilder JURASSIC_FOREST = register("jurassic_forest_sb", NAKED_JURASSIC_FOREST);
	public static final SurfaceBuilder JURASSIC_PLAINS = register("jurassic_plains_sb", NAKED_JURASSIC_PLAINS);
	public static final SurfaceBuilder JURASSIC_DESERT = register("jurassic_desert_sb", NAKED_JURASSIC_DESERT);
	public static final SurfaceBuilder JURASSIC_MOUNTIANS = register("jurassic_mountains_sb", NAKED_JURASSIC_MOUNTAINS);
	public static final SurfaceBuilder JURASSIC_ERRODED_MOUNTIANS = register("jurassic_erroded_mountains_sb", NAKED_JURASSIC_ERRODED_MOUNTAINS);
	public static final SurfaceBuilder JURASSIC_SWAMP = register("jurassic_swamp_sb", NAKED_JURASSIC_SWAMP);

	public static final SurfaceBuilder CRETACEOUS_FOREST = register("cretaceous_forest_sb", NAKED_CRETACEOUS_FOREST);
	public static final SurfaceBuilder CRETACEOUS_GAME_TRAIL = register("cretaceous_game_trail_sb", NAKED_CRETACEOUS_GAME_TRAIL);
	public static final SurfaceBuilder CRETACEOUS_PLAINS = register("cretaceous_plains_sb", NAKED_CRETACEOUS_PLAINS);
	public static final SurfaceBuilder CRETACEOUS_MOUNTAINS = register("cretaceous_mountains_sb", NAKED_CRETACEOUS_MOUNTAINS);
	public static final SurfaceBuilder CRETACEOUS_ERRODED_MOUNTIANS = register("cretaceous_erroded_mountains_sb", NAKED_CRETACEOUS_ERRODED_MOUNTAINS);
	public static final SurfaceBuilder CRETACEOUS_DESERT = register("cretaceous_desert_sb", NAKED_CRETACEOUS_DESERT);
	public static final SurfaceBuilder CRETACEOUS_RED_DESERT = register("cretaceous_red_desert_sb", NAKED_CRETACEOUS_RED_DESERT);
	public static final SurfaceBuilder CRETACEOUS_SWAMP = register("cretaceous_swamp_sb", NAKED_CRETACEOUS_SWAMP);

	public static <T extends SurfaceBuilderConfiguration> SurfaceBuilder<T> register(String id, SurfaceBuilder<T> surfaceBuilder) {
		SURFACE_BUILDERS.register(id, () -> surfaceBuilder);
		return surfaceBuilder;
	}

	public static void deferred(IEventBus bus) {
		SURFACE_BUILDERS.register(bus);
	}
}
