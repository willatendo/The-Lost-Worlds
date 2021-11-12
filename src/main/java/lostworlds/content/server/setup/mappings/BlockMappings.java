package lostworlds.content.server.setup.mappings;

import java.util.HashMap;
import java.util.Map;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.MOD)
public class BlockMappings 
{
	private static final Map<ResourceLocation, Block> blockRemappings = new HashMap<ResourceLocation, Block>()
	{
		private static final long serialVersionUID = 2729764913422843323L;
		{
			put(ModUtils.rL("small_permian_desert_plant"), BlockInit.PERMIAN_DESERT_SHRUB);
			put(ModUtils.rL("medium_permian_desert_plant"), BlockInit.PERMIAN_DESERT_SHRUB);
			put(ModUtils.rL("large_permian_desert_plant"), BlockInit.PERMIAN_DESERT_SHRUB);
			put(ModUtils.rL("lycophyta"), BlockInit.OSMUNDA);
			put(ModUtils.rL("tall_dicksonia"), BlockInit.DICKSONIA);
			put(ModUtils.rL("power_supply_block"), Blocks.REDSTONE_BLOCK);			
			put(ModUtils.rL("mossy_dirt"), BlockInit.MOSSY_SOIL);			
			put(ModUtils.rL("mossy_jurassic_stone"), BlockInit.JURASSIC_STONE);			
			put(ModUtils.rL("mossy_jurassic_stone_stairs"), BlockInit.JURASSIC_STONE_STAIRS);			
			put(ModUtils.rL("mossy_jurassic_stone_slab"), BlockInit.JURASSIC_STONE_SLAB);	
			put(ModUtils.rL("mossy_jurassic_cobblestone"), BlockInit.JURASSIC_COBBLESTONE);			
			put(ModUtils.rL("mossy_jurassic_cobblestone_stairs"), BlockInit.JURASSIC_COBBLESTONE_STAIRS);			
			put(ModUtils.rL("mossy_jurassic_cobblestone_slab"), BlockInit.JURASSIC_COBBLESTONE_SLAB);			
			put(ModUtils.rL("mossy_jurassic_cobblestone_wall"), BlockInit.JURASSIC_COBBLESTONE_WALL);		
			put(ModUtils.rL("mossy_jurassic_stone_bricks"), BlockInit.JURASSIC_STONE_BRICKS);			
			put(ModUtils.rL("mossy_jurassic_stone_brick_stairs"), BlockInit.JURASSIC_STONE_BRICK_STAIRS);			
			put(ModUtils.rL("mossy_jurassic_stone_brick_slab"), BlockInit.JURASSIC_STONE_BRICK_SLAB);			
			put(ModUtils.rL("mossy_jurassic_stone_brick_wall"), BlockInit.JURASSIC_STONE_BRICK_WALL);	
			put(ModUtils.rL("polished_light_concrete"), BlockInit.ACCENT_LIGHT_CONCRETE);			
			put(ModUtils.rL("polished_light_concrete_stairs"), BlockInit.ACCENT_LIGHT_CONCRETE_STAIRS);	
			put(ModUtils.rL("polished_light_concrete_slab"), BlockInit.ACCENT_LIGHT_CONCRETE_SLAB);	
			put(ModUtils.rL("polished_dark_concrete"), BlockInit.ACCENT_DARK_CONCRETE);			
			put(ModUtils.rL("polished_dark_concrete_stairs"), BlockInit.ACCENT_DARK_CONCRETE_STAIRS);	
			put(ModUtils.rL("polished_dark_concrete_slab"), BlockInit.ACCENT_DARK_CONCRETE_SLAB);	
			put(ModUtils.rL("thatch_block"), BlockInit.THATCH_BUNDLE);	
		}
	};
	
	private static final Map<ResourceLocation, Item> itemRemappings = new HashMap<ResourceLocation, Item>()
	{
		private static final long serialVersionUID = 2729763913422843325L;
		{
			put(ModUtils.rL("stone_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_stone_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("sandstone_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_sandstone_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("red_sandstone_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_red_sandstone_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("red_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_red_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("orange_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_orange_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("yellow_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_yellow_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("green_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_green_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("lime_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_lime_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("cyan_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_cyan_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("light_blue_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_light_blue_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("blue_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_blue_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("magenta_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_magenta_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("purple_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_purple_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("pink_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_pink_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("brown_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_brown_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("black_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_black_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("grey_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_grey_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("light_grey_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_light_grey_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("white_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("exposed_white_terracotta_fossil"), ItemInit.AMBER);
			put(ModUtils.rL("plastered_fossil"), ItemInit.AMBER);
		}
	};
	
	@SubscribeEvent
	public void updateBlockMappings(RegistryEvent.MissingMappings<Block> event) 
	{
		if(event.getAllMappings().stream().filter(mapping -> mapping.key.getNamespace().equals(ModUtils.ID)).findAny().isPresent()) 
		{
			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(ModUtils.ID)).forEach(mapping ->
			{
				if(blockRemappings.containsKey(mapping.key))
				{
					remap(mapping, blockRemappings);
				}
			});
		}
	}
	
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
