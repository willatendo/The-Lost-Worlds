package lostworlds.content.server.init;

import lostworlds.library.dimension.permian.PermianBiomeProvider;
import lostworlds.library.dimension.permian.PermianChunkGenerator;
import lostworlds.library.util.ModUtils;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
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
	
	public static void initBiomeSourcesAndChunkGenerator()
	{
		ModUtils.LOGGER.debug("Registering Mod Biome Sources and Chunk Generators");
		
		Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(ModUtils.ID, "permian_biome_source"), PermianBiomeProvider.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(ModUtils.ID, "permian_chunk_generator"), PermianChunkGenerator.CODEC);
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
		}
	}
}
