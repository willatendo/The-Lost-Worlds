package lostworlds.addon;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.objectweb.asm.Type;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData;

public class LostWorldsAddon
{
	public static List<ILostWorldsAddon> getModPlugins() 
	{
		return getInstances(Init.class, ILostWorldsAddon.class);
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
			catch (ClassNotFoundException | InstantiationException | IllegalAccessException | LinkageError e) { }
		}
		return instances;
	}
	
	//Used in conjunction with @Init to register objects.
	public interface ILostWorldsAddon { }
	
	//Used in conjunction with ILostWorldsAddon to register objects.
	public @interface Init { }
}
