package lostworlds.addon;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.objectweb.asm.Type;

import lostworlds.library.util.ModUtils;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData;
import net.minecraftforge.registries.ForgeRegistries;

public class LostWorldsAddon 
{
	//All the registers setup for your ease!
	
	public static Item register(String id, Item item)
	{
		item.setRegistryName(rL(id));
		ForgeRegistries.ITEMS.register(item);
		return item;
	}
	
	/*
	 * Don't worry about anything below, if you need to know how to setup an addon, look at TestAddon.
	 */
	
	public static String ID;
	
	private static ResourceLocation rL(String path)
	{
		return new ResourceLocation(ID, path);
	}
	
	public static List<LostWorldsAddon> getAddons() 
	{
		ModUtils.LOGGER.debug("Loading Addons");
		
		return getInstances(Register.class, LostWorldsAddon.class);
	}
	
	private static <T> List<T> getInstances(Class<?> annotationClass, Class<T> instanceClass) 
	{
		Type annotationType = Type.getType(annotationClass);
		List<ModFileScanData> allScanData = ModList.get().getAllScanData();
		Set<String> pluginClassNames = new LinkedHashSet<>();
		for(ModFileScanData scanData : allScanData) 
		{
			Iterable<ModFileScanData.AnnotationData> annotations = scanData.getAnnotations();
			for(ModFileScanData.AnnotationData a : annotations) 
			{
				if(Objects.equals(a.getAnnotationType(), annotationType)) 
				{
					String memberName = a.getMemberName();
					pluginClassNames.add(memberName);
				}
			}
		}
		List<T> instances = new ArrayList<>();
		for(String className : pluginClassNames) 
		{
			try 
			{
				Class<?> asmClass = Class.forName(className);
				Class<? extends T> asmInstanceClass = asmClass.asSubclass(instanceClass);
				T instance = asmInstanceClass.newInstance();
				instances.add(instance);
			} 
			catch(ClassNotFoundException | InstantiationException | IllegalAccessException | LinkageError e) { }
		}
		return instances;
	}
}
