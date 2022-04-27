package lostworlds.server.events.mappings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = LostWorldsUtils.ID, bus = Bus.MOD)
public class BlockMappings {
	private static final Map<ResourceLocation, Supplier<Block>> blockRemappings = new HashMap<ResourceLocation, Supplier<Block>>() {
		private static final long serialVersionUID = 2729764913422843323L;
		{
			put(LostWorldsUtils.rL("small_permian_desert_plant"), () -> LostWorldsBlocks.PERMIAN_DESERT_SHRUB.get());
			put(LostWorldsUtils.rL("medium_permian_desert_plant"), () -> LostWorldsBlocks.PERMIAN_DESERT_SHRUB.get());
			put(LostWorldsUtils.rL("large_permian_desert_plant"), () -> LostWorldsBlocks.PERMIAN_DESERT_SHRUB.get());
			put(LostWorldsUtils.rL("lycophyta"), () -> LostWorldsBlocks.OSMUNDA.get());
			put(LostWorldsUtils.rL("tall_dicksonia"), () -> LostWorldsBlocks.DICKSONIA.get());
			put(LostWorldsUtils.rL("power_supply_block"), () -> Blocks.REDSTONE_BLOCK);
			put(LostWorldsUtils.rL("mossy_dirt"), () -> LostWorldsBlocks.MOSSY_SOIL.get());
			put(LostWorldsUtils.rL("mossy_jurassic_stone"), () -> LostWorldsBlocks.JURASSIC_STONE.get());
			put(LostWorldsUtils.rL("mossy_jurassic_stone_stairs"), () -> LostWorldsBlocks.JURASSIC_STONE_STAIRS.get());
			put(LostWorldsUtils.rL("mossy_jurassic_stone_slab"), () -> LostWorldsBlocks.JURASSIC_STONE_SLAB.get());
			put(LostWorldsUtils.rL("mossy_jurassic_cobblestone"), () -> LostWorldsBlocks.JURASSIC_COBBLESTONE.get());
			put(LostWorldsUtils.rL("mossy_jurassic_cobblestone_stairs"), () -> LostWorldsBlocks.JURASSIC_COBBLESTONE_STAIRS.get());
			put(LostWorldsUtils.rL("mossy_jurassic_cobblestone_slab"), () -> LostWorldsBlocks.JURASSIC_COBBLESTONE_SLAB.get());
			put(LostWorldsUtils.rL("mossy_jurassic_cobblestone_wall"), () -> LostWorldsBlocks.JURASSIC_COBBLESTONE_WALL.get());
			put(LostWorldsUtils.rL("mossy_jurassic_stone_bricks"), () -> LostWorldsBlocks.JURASSIC_STONE_BRICKS.get());
			put(LostWorldsUtils.rL("mossy_jurassic_stone_brick_stairs"), () -> LostWorldsBlocks.JURASSIC_STONE_BRICK_STAIRS.get());
			put(LostWorldsUtils.rL("mossy_jurassic_stone_brick_slab"), () -> LostWorldsBlocks.JURASSIC_STONE_BRICK_SLAB.get());
			put(LostWorldsUtils.rL("mossy_jurassic_stone_brick_wall"), () -> LostWorldsBlocks.JURASSIC_STONE_BRICK_WALL.get());
			put(LostWorldsUtils.rL("polished_light_concrete"), () -> LostWorldsBlocks.ACCENT_LIGHT_CONCRETE.get());
			put(LostWorldsUtils.rL("polished_light_concrete_stairs"), () -> LostWorldsBlocks.ACCENT_LIGHT_CONCRETE_STAIRS.get());
			put(LostWorldsUtils.rL("polished_light_concrete_slab"), () -> LostWorldsBlocks.ACCENT_LIGHT_CONCRETE_SLAB.get());
			put(LostWorldsUtils.rL("polished_dark_concrete"), () -> LostWorldsBlocks.ACCENT_DARK_CONCRETE.get());
			put(LostWorldsUtils.rL("polished_dark_concrete_stairs"), () -> LostWorldsBlocks.ACCENT_DARK_CONCRETE_STAIRS.get());
			put(LostWorldsUtils.rL("polished_dark_concrete_slab"), () -> LostWorldsBlocks.ACCENT_DARK_CONCRETE_SLAB.get());
			put(LostWorldsUtils.rL("stone_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_stone_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("sandstone_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_sandstone_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("red_sandstone_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_red_sandstone_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("red_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_red_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("orange_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_orange_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("yellow_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_yellow_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("green_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_green_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("lime_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_lime_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("cyan_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_cyan_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("light_blue_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_light_blue_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("blue_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_blue_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("magenta_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_magenta_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("purple_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_purple_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("pink_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_pink_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("brown_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_brown_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("black_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_black_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("grey_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_grey_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("light_grey_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_light_grey_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("white_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("exposed_white_terracotta_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
			put(LostWorldsUtils.rL("plastered_fossil"), () -> LostWorldsBlocks.AMBER_ORE.get());
		}
	};

	@SubscribeEvent
	public void updateBlockMappings(RegistryEvent.MissingMappings<Block> event) {
		if (event.getAllMappings().stream().filter(mapping -> mapping.key.getNamespace().equals(LostWorldsUtils.ID)).findAny().isPresent()) {
			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(LostWorldsUtils.ID)).forEach(mapping -> {
				if (blockRemappings.containsKey(mapping.key)) {
					remap(mapping, blockRemappings);
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
