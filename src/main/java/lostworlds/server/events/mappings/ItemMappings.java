package lostworlds.server.events.mappings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
public class ItemMappings {
	private static final Map<ResourceLocation, Supplier<Item>> itemRemappings = new HashMap<ResourceLocation, Supplier<Item>>() {
		private static final long serialVersionUID = 2729763913422843325L;
		{
			put(LostWorldsUtils.rL("copper_ingot"), () -> Items.COPPER_INGOT);

			put(LostWorldsUtils.rL("permian_time_book"), () -> LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK.get());
			put(LostWorldsUtils.rL("jurassic_time_book"), () -> LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get());
			put(LostWorldsUtils.rL("broken_crystal_scarab_gem"), () -> LostWorldsItems.CRYSTAL_SCARAB_ABDOMEN.get());
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

	private <T extends IForgeRegistryEntry<T>> void remap(RegistryEvent.MissingMappings.Mapping<T> mapping, Map<ResourceLocation, Supplier<T>> remappings) {
		ResourceLocation key = mapping.key;
		if (remappings.containsKey(key)) {
			mapping.remap(remappings.get(key).get());
			LostWorldsUtils.LOGGER.warn("Replaced " + key + " with " + remappings.get(key).get().getRegistryName());
		} else {
			mapping.ignore();
			LostWorldsUtils.LOGGER.warn("Could not find a mapping replacement for " + key + ". It was likely intentionally removed in an update.");
		}
	}

}
