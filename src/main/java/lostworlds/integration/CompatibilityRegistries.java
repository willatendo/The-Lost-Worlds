package lostworlds.integration;

import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class CompatibilityRegistries 
{
	public static Item register(String modid, String id, Item item)
	{
		return !FMLEnvironment.production ? ModRegistry.register(id, item) : ModList.get().isLoaded(modid) ? ModRegistry.register(id, item) : item;
	}
	
	public static Block register(String modid, String id, Block block)
	{
		return !FMLEnvironment.production ? ModRegistry.register(id, block) : ModList.get().isLoaded(modid) ? ModRegistry.register(id, block) : block;
	}
}
