package lostworlds.server.events.mappings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.item.LostWorldsItems;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
public class ItemMappings {
	private static final Map<ResourceLocation, Supplier<Item>> itemRemappings = new HashMap<ResourceLocation, Supplier<Item>>() {
		private static final long serialVersionUID = 2729763913422843325L;
		{
			// Alpha 10 -> Alpha 11
			put(LostWorldsUtils.rL("permian_time_book"), () -> LostWorldsItems.PERMIAN_PERIOD_TIME_BOOK.get());
			put(LostWorldsUtils.rL("jurassic_time_book"), () -> LostWorldsItems.JURASSIC_PERIOD_TIME_BOOK.get());
			put(LostWorldsUtils.rL("broken_crystal_scarab_gem"), () -> LostWorldsItems.CRYSTAL_SCARAB_ABDOMEN.get());
			// Alpha 11 -> Alpha 11.1
			put(LostWorldsUtils.rL("copper_ingot"), () -> Items.COPPER_INGOT);
		}
	};

	@SubscribeEvent
	public void updateItemMappings(MissingMappingsEvent event) {
		if (event.getAllMappings(Registry.ITEM_REGISTRY).stream().filter(mapping -> mapping.getKey().getNamespace().equals(LostWorldsUtils.ID)).findAny().isPresent()) {
			event.getAllMappings(Registry.ITEM_REGISTRY).stream().filter(mapping -> mapping.getKey().getNamespace().equals(LostWorldsUtils.ID)).forEach(mapping -> {
				if (itemRemappings.containsKey(mapping.getKey())) {
					remap(mapping, itemRemappings);
				}
			});
		}
	}

	private <T extends Item> void remap(MissingMappingsEvent.Mapping<T> mapping, Map<ResourceLocation, Supplier<T>> remappings) {
		ResourceLocation key = mapping.getKey();
		if (remappings.containsKey(key)) {
			mapping.remap(remappings.get(key).get());
			LostWorldsUtils.LOGGER.warn("Replaced " + key + " with " + ForgeRegistries.ITEMS.getKey(remappings.get(key).get()));
		} else {
			mapping.ignore();
			LostWorldsUtils.LOGGER.warn("Could not find a mapping replacement for " + key + ". It was likely intentionally removed in an update.");
		}
	}
}
