package lostworlds.server.events.mappings;

import java.util.HashMap;
import java.util.Map;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.CrystalScarabGemItem;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistryEntry;
import tyrannotitanlib.content.server.init.TyrannoItems;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
public class ItemMappings {
	private static final Map<ResourceLocation, Item> itemRemappings = new HashMap<ResourceLocation, Item>() {
		private static final long serialVersionUID = 2729763913422843325L;
		{
			put(LostWorldsUtils.rL("copper_ingot"), TyrannoItems.COPPER_INGOT);
			put(LostWorldsUtils.rL("copper_nugget"), TyrannoItems.COPPER_NUGGET);

			put(LostWorldsUtils.rL("permian_time_book"), LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK);
			put(LostWorldsUtils.rL("jurassic_time_book"), LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK);
			put(LostWorldsUtils.rL("broken_crystal_scarab_gem"), CrystalScarabGemItem.Gems.CRYSTAL_SCARAB_ABDOMEN.getItem());
		}
	};

	@SubscribeEvent
	public void updateItemMappings(RegistryEvent.MissingMappings<Item> event) {
		if (event.getAllMappings().stream().filter(mapping -> mapping.key.getNamespace().equals(LostWorldsUtils.ID)).findAny().isPresent()) {
			event.getAllMappings().stream().filter(mapping -> mapping.key.getNamespace().equals(LostWorldsUtils.ID)).forEach(mapping -> {
				if (itemRemappings.containsKey(mapping.key)) {
					remap(mapping, itemRemappings);
				}
			});
		}
	}

	private <T extends IForgeRegistryEntry<T>> void remap(RegistryEvent.MissingMappings.Mapping<T> mapping, Map<ResourceLocation, T> remappings) {
		ResourceLocation key = mapping.key;
		if (remappings.containsKey(key)) {
			mapping.remap(remappings.get(key));
			LostWorldsUtils.LOGGER.warn("Replaced " + key + " with " + remappings.get(key).getRegistryName());
		} else {
			mapping.ignore();
			LostWorldsUtils.LOGGER.warn("Could not find a mapping replacement for " + key + ". It was likely intentionally removed in an update.");
		}
	}

}
