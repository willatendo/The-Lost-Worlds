package lostworlds.addon;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.objectweb.asm.Type;

import lostworlds.library.util.ModUtils;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData;

public abstract class LostWorldsAddon implements IAddon
{
	public static String ID;
	public static String getMessage;
	
	public LostWorldsAddon() 
	{
		this.ID = this.addonId();
		this.getMessage = this.addonLoadMessage();
	}
	
	public static List<LostWorldsAddon> getAddons() 
	{
		ModUtils.LOGGER.debug("Loading Addons");
		
		return getInstances(RegisterAddon.class, LostWorldsAddon.class);
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
	
//	@EventBusSubscriber(modid = ModUtils.ID, bus = Bus.FORGE, value = Dist.CLIENT)
//	static class LoadMessages
//	{
//		@SubscribeEvent
//		public static void onLoadEvent(final PlayerEvent.PlayerLoggedInEvent event)
//		{
//			if(LostWorldsAddon.getAddons() != null)
//			{
//				PlayerEntity player = event.getPlayer();
//				player.sendMessage(ModUtils.gTC("event", LostWorldsAddon.getMessage), player.getUUID());
//			}
//		}
//	}
}
