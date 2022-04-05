package lostworlds.repack.tyrannotitanlib.tyrannibook;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.datafixers.util.Pair;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.ProcessorLists;
import net.minecraft.world.gen.feature.template.StructureProcessorList;

public class TyrannoUtils 
{
	public static List<String> TYRANNOTITANS = new ArrayList<>();

	public static final Logger LOGGER = LogManager.getLogger(TyrannoUtils.TYRANNO_ID);
	
	public static final String TYRANNO_ID = "tyrannotitanlib";
	
	public static ResourceLocation rL(String location)
	{
		return new ResourceLocation(TYRANNO_ID, location);
	}
	
	public static TranslationTextComponent sTC(String key)
	{
		return new TranslationTextComponent(key);
	}
	
	public static TranslationTextComponent tTC(String type, String key)
	{
		return new TranslationTextComponent(type + "." + TYRANNO_ID + "." + key);
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
	
	@Nullable
	public static BufferedReader getURLContents(@Nonnull String urlString, @Nonnull String backupFileLoc) 
	{
		try 
		{
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);

			return new BufferedReader(reader);
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		try 
		{
			return new BufferedReader(new InputStreamReader(TyrannoUtils.class.getClass().getClassLoader().getResourceAsStream(backupFileLoc), StandardCharsets.UTF_8));
		} 
		catch(NullPointerException e) 
		{
			e.printStackTrace();
		}

		return null;
	}
	
	public static void registerJigsaw(MinecraftServer server, ResourceLocation poolLocation, ResourceLocation nbtLocation, int weight) 
	{
		DynamicRegistries manager = server.registryAccess();
		MutableRegistry<JigsawPattern> pools = manager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
		JigsawPattern pool = pools.get(poolLocation);
		
		StructureProcessorList processorList = manager.registryOrThrow(Registry.PROCESSOR_LIST_REGISTRY).getOptional(poolLocation).orElse(ProcessorLists.EMPTY);
		List<JigsawPiece> elements = pool.templates;
		
		JigsawPiece element = JigsawPiece.legacy(nbtLocation.toString(), processorList).apply(JigsawPattern.PlacementBehaviour.RIGID);
		for(int i = 0; i < weight; i++) 
		{
			elements.add(element);
		}
		
		List<Pair<JigsawPiece, Integer>> elementCounts = new ArrayList(pool.rawTemplates);
		
		elements.addAll(pool.templates);
		elementCounts.addAll(pool.rawTemplates);
		
		pool.templates = elements;
		pool.rawTemplates = elementCounts;
	}
}
