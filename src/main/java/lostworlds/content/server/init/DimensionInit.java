package lostworlds.content.server.init;

import lostworlds.content.ModUtils;
import lostworlds.library.dimension.cretaceous.CretaceousBiomeProvider;
import lostworlds.library.dimension.cretaceous.CretaceousChunkGenerator;
import lostworlds.library.dimension.jurassic.JurassicBiomeProvider;
import lostworlds.library.dimension.jurassic.JurassicChunkGenerator;
import lostworlds.library.dimension.permian.PermianBiomeProvider;
import lostworlds.library.dimension.permian.PermianChunkGenerator;
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

@EventBusSubscriber(modid = ModUtils.ID)
public class DimensionInit 
{
	public static final RegistryKey<World> PERMIAN_WORLD = RegistryKey.create(Registry.DIMENSION_REGISTRY, ModUtils.rL("permian"));
	public static final RegistryKey<DimensionType> PERMIAN_DIMENSION = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, ModUtils.rL("permian"));
		
	public static final RegistryKey<World> JURASSIC_WORLD = RegistryKey.create(Registry.DIMENSION_REGISTRY, ModUtils.rL("jurassic"));
	public static final RegistryKey<DimensionType> JURASSIC_DIMENSION = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, ModUtils.rL("jurassic"));
	
	public static final RegistryKey<World> CRETACEOUS_WORLD = RegistryKey.create(Registry.DIMENSION_REGISTRY, ModUtils.rL("cretaceous"));
	public static final RegistryKey<DimensionType> CRETACEOUS_DIMENSION = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, ModUtils.rL("cretaceous"));
	
	public static void initBiomeSourcesAndChunkGenerator()
	{
		ModUtils.LOGGER.debug("Registering Mod Biome Sources and Chunk Generators");
		
		Registry.register(Registry.BIOME_SOURCE, ModUtils.rL("permian_biome_source"), PermianBiomeProvider.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, ModUtils.rL("permian_chunk_generator"), PermianChunkGenerator.CODEC);
		
		Registry.register(Registry.BIOME_SOURCE, ModUtils.rL("jurassic_biome_source"), JurassicBiomeProvider.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, ModUtils.rL("jurassic_chunk_generator"), JurassicChunkGenerator.CODEC);
		
		Registry.register(Registry.BIOME_SOURCE, ModUtils.rL("cretaceous_biome_source"), CretaceousBiomeProvider.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, ModUtils.rL("cretaceous_chunk_generator"), CretaceousChunkGenerator.CODEC);
	}
	
	@SubscribeEvent
	public static void onSleepFinished(SleepFinishedTimeEvent event) 
	{
		IWorld world = event.getWorld();
		if(world instanceof ServerWorld) 
		{
			ServerWorld serverWorld = (ServerWorld) world;
			if(serverWorld.dimension() == PERMIAN_WORLD) 
			{
				if(world.getLevelData() instanceof DerivedWorldInfo) 
				{
					((DerivedWorldInfo) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
				}
			}
			
			if(serverWorld.dimension() == JURASSIC_WORLD) 
			{
				if(world.getLevelData() instanceof DerivedWorldInfo) 
				{
					((DerivedWorldInfo) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
				}
			}
			
			if(serverWorld.dimension() == CRETACEOUS_WORLD) 
			{
				if(world.getLevelData() instanceof DerivedWorldInfo) 
				{
					((DerivedWorldInfo) world.getLevelData()).wrapped.setDayTime(event.getNewTime());
				}
			}
		}
	}
}
