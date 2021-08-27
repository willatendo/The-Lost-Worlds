package lostworlds.library.util;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableSet;

import lostworlds.content.server.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;

public class ModUtils 
{
	public static final HashSet<Biome.Category> SIMPLE_SPAWNABLE_BIOME_CATEGORIES = Stream.of(Biome.Category.FOREST, Biome.Category.JUNGLE, Biome.Category.DESERT, Biome.Category.PLAINS, Biome.Category.SAVANNA).collect(Collectors.toCollection(HashSet::new));
	
	public static final Logger LOGGER = LogManager.getLogger(ModUtils.ID);
		
	public static final String ID = "lostworlds";
	
	public static ImmutableSet<Block> permianCarverBlocks()
	{
		return ImmutableSet.of(BlockInit.PERMIAN_SAND, BlockInit.PERMIAN_STONE, BlockInit.PERMIAN_COBBLESTONE, BlockInit.MOSSY_SOIL, BlockInit.DRIED_SOIL, BlockInit.CRACKED_SOIL, BlockInit.VOLCANIC_ASH, BlockInit.VOLCANIC_ASH_LAYER, Blocks.BASALT, Blocks.BLACKSTONE, Blocks.DIRT, Blocks.GRAVEL, Blocks.MAGMA_BLOCK);
	}
	
	public static ResourceLocation rL(String location)
	{
		return new ResourceLocation(ID, location);
	}
	
	public static TranslationTextComponent tTC(String type, String key)
	{
		return new TranslationTextComponent(type + "." + ID + "." + key);
	}
	
	public static TranslationTextComponent cTC(String type, String key, TextFormatting colour)
	{
		TranslationTextComponent text = tTC(type, key);
		text.withStyle(colour);
		return text;
	}
	
	public static TranslationTextComponent gTC(String type, String key)
	{
		TranslationTextComponent text = tTC(type, key);
		text.withStyle(TextFormatting.GRAY);
		return text;
	}
}
