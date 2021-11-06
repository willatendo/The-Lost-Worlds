package lostworlds.content.server.setup.mappings;

import java.util.HashMap;
import java.util.Map;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.ItemInit;
import lostworlds.library.item.CrystalScarabGemItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD)
public class ItemMappings 
{
	private static final Map<ResourceLocation, Item> itemRemappings = new HashMap<ResourceLocation, Item>()
	{
		private static final long serialVersionUID = 2729763913422843325L;
		{
			put(ModUtils.rL("permian_time_book"), ItemInit.PERMIAN_PERIOD_TIME_BOOK);
			put(ModUtils.rL("jurassic_time_book"), ItemInit.JURASSIC_ERA_TIME_BOOK);
			put(ModUtils.rL("broken_crystal_scarab_gem"), CrystalScarabGemItem.Gems.CRYSTAL_SCARAB_ABDOMEN.getItem());
		}
	};
	
	@SubscribeEvent
	public void updateItemMappings(RegistryEvent.MissingMappings<Item> event) 
	{
		if(event.getAllMappings().stream().filter(mapping -> mapping.key.getNamespace().equals(ModUtils.ID)).findAny().isPresent()) 
		{
			event.getAllMappings().stream().filter(mapping -> mapping.key.getNamespace().equals(ModUtils.ID)).forEach(mapping ->
			{
				if(itemRemappings.containsKey(mapping.key))
				{
					remap(mapping, itemRemappings);
				}
			});
		}
	}
	
	private <T extends IForgeRegistryEntry<T>> void remap(RegistryEvent.MissingMappings.Mapping<T> mapping, Map<ResourceLocation, T> remappings)
	{
		ResourceLocation key = mapping.key;
		if(remappings.containsKey(key))
		{
			mapping.remap(remappings.get(key));
			ModUtils.LOGGER.warn("Replaced " + key + " with " + remappings.get(key).getRegistryName());
		}
		else
		{
			mapping.ignore();
			ModUtils.LOGGER.warn("Could not find a mapping replacement for " + key + ". It was likely intentionally removed in an update.");
		}
	}

}
