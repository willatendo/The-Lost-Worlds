package lostworlds.integration.quark.client.chest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import lostworlds.library.util.ModUtils;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ModUtils.ID, value = Dist.CLIENT, bus = Bus.MOD)
public class ChestManager 
{
	private static final Map<String, ChestInfo> CHEST_INFO_MAP = new HashMap<>();

	public static synchronized void putChestInfo(String modId, String type, boolean trapped) 
	{
		CHEST_INFO_MAP.put(modId + ":" + type + (trapped ? "_trapped" : ""), new ChestInfo(modId, type, trapped));
	}
	
	@Nullable
	public static ChestInfo getInfoForChest(String chestType) 
	{
		return CHEST_INFO_MAP.get(chestType);
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onStitch(TextureStitchEvent.Pre event) 
	{
		if(event.getMap().location().equals(Atlases.CHEST_SHEET)) 
		{
			for(ChestInfo chestInfo : CHEST_INFO_MAP.values()) 
			{
				chestInfo.setup(event);
			}
		}
	}

	public static class ChestInfo 
	{
		private final ResourceLocation single, left, right;
		@OnlyIn(Dist.CLIENT)
		private RenderMaterial singleMaterial, leftMaterial, rightMaterial;

		public ChestInfo(String modId, String type, boolean trapped) 
		{
			String chest = trapped ? "trapped" : "normal";
			this.single = new ResourceLocation(modId, "entity/chest/" + type + "/" + chest);
			this.left = new ResourceLocation(modId, "entity/chest/" + type + "/" + chest + "_left");
			this.right = new ResourceLocation(modId, "entity/chest/" + type + "/" + chest + "_right");
		}

		@OnlyIn(Dist.CLIENT)
		private void setup(TextureStitchEvent.Pre event) 
		{
			event.addSprite(this.single);
			event.addSprite(this.left);
			event.addSprite(this.right);
			this.singleMaterial = new RenderMaterial(Atlases.CHEST_SHEET, this.single);
			this.leftMaterial = new RenderMaterial(Atlases.CHEST_SHEET, this.left);
			this.rightMaterial = new RenderMaterial(Atlases.CHEST_SHEET, this.right);
		}
		
		@OnlyIn(Dist.CLIENT)
		public RenderMaterial getSingleMaterial() 
		{
			return this.singleMaterial;
		}
		
		@OnlyIn(Dist.CLIENT)
		public RenderMaterial getLeftMaterial() 
		{
			return this.leftMaterial;
		}

		@OnlyIn(Dist.CLIENT)
		public RenderMaterial getRightMaterial() 
		{
			return this.rightMaterial;
		}
	}
}
