package lostworlds.content;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import com.google.common.collect.ImmutableSet;

import lostworlds.content.server.init.BlockInit;
import lostworlds.library.tab.ModTab;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModUtils 
{
	private static final long MINECRAFT_WINDOW = Minecraft.getInstance().getWindow().getWindow();
	
	public static final HashSet<Biome.Category> SIMPLE_SPAWNABLE_BIOME_CATEGORIES = Stream.of(Biome.Category.FOREST, Biome.Category.JUNGLE, Biome.Category.DESERT, Biome.Category.PLAINS, Biome.Category.SAVANNA).collect(Collectors.toCollection(HashSet::new));
	public static final HashSet<Biome.Category> FOSSIL_BIOMES = Stream.of(Biome.Category.FOREST, Biome.Category.EXTREME_HILLS, Biome.Category.DESERT, Biome.Category.PLAINS, Biome.Category.SAVANNA, Biome.Category.MUSHROOM, Biome.Category.SWAMP).collect(Collectors.toCollection(HashSet::new));
	
	public static final Logger LOGGER = LogManager.getLogger(ModUtils.ID);
		
	public static final String ID = "lostworlds";
	public static final String NAME = "Lost Worlds";

	public static final ModTab ITEMS = new ModTab("items");
	public static final ModTab BLOCKS = new ModTab("block");
	
	public static ImmutableSet<Block> carverBlocks()
	{
		return ImmutableSet.of(BlockInit.PERMIAN_SAND, BlockInit.PERMIAN_STONE, BlockInit.PERMIAN_COBBLESTONE, BlockInit.JURASSIC_STONE, BlockInit.JURASSIC_COBBLESTONE, BlockInit.LATERLITE, BlockInit.RAW_MARBLE, BlockInit.LIMESTONE, BlockInit.MOSSY_SOIL, BlockInit.DRIED_SOIL, BlockInit.CRACKED_SOIL, BlockInit.VOLCANIC_ASH, BlockInit.VOLCANIC_ASH_LAYER, Blocks.BASALT, Blocks.BLACKSTONE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.MAGMA_BLOCK, Blocks.STONE, Blocks.SNOW_BLOCK);
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
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingLeftShift()
	{
		return InputMappings.isKeyDown(MINECRAFT_WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT);
	}
}
