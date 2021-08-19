package lostworlds.integration.buzzierbees.init;

import com.google.common.collect.Sets;

import lostworlds.integration.buzzierbees.block.BuzzierBeesBeehiveBlock;
import lostworlds.integration.buzzierbees.tileentity.BuzzierBeesBehiveTileEntity;
import lostworlds.integration.buzzierbees.util.BuzzierBeesUtils;
import lostworlds.library.util.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(bus = Bus.MOD, modid = ModUtils.ID)
public class BuzzierBeesTileEntities 
{
	public static final TileEntityType<BuzzierBeesBehiveTileEntity> MOD_BEEHIVE = new TileEntityType<>(BuzzierBeesBehiveTileEntity::new, Sets.newHashSet(collectBlocks(BuzzierBeesBeehiveBlock.class)), null);
	
	@SubscribeEvent
	public static void register(final RegistryEvent.Register<TileEntityType<?>> event)
	{
		if(!FMLEnvironment.production || ModList.get().isLoaded(BuzzierBeesUtils.BUZZIER_BEES_ID))
		{
			event.getRegistry().register(MOD_BEEHIVE.setRegistryName(ModUtils.rL("mod_beehive")));
		}
	}
	
	public static Block[] collectBlocks(Class<?> blockClass) 
	{
		return ForgeRegistries.BLOCKS.getValues().stream().filter(blockClass::isInstance).toArray(Block[]::new);
	}
}
