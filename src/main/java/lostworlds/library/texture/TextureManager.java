package lostworlds.library.texture;

import java.util.HashMap;
import java.util.Map;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.BlockInit;
import lostworlds.library.block.ConnectedTextureBlock;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.MOD)
public class TextureManager 
{
	public static final Map<ConnectedTextureBlock, TextureAtlasSprite> TEXTURES = new HashMap<>();
	public static final Map<ConnectedTextureBlock, TextureAtlasSprite> PARTICLES = new HashMap<>();
	
	@SubscribeEvent
	public static void onBake(ModelBakeEvent event)
	{
		ConnectedTextureBlock blockone = BlockInit.POLISHED_LIGHT_CONCRETE;
		ConnectedTextureBlock blocktwo = BlockInit.POLISHED_DARK_CONCRETE;
		LWBakedModel modelone = new LWConnectedBakedModel(blockone);
		LWBakedModel modeltwo = new LWConnectedBakedModel(blocktwo);
		event.getModelRegistry().put(new ModelResourceLocation(blockone.getRegistryName(), ""), modelone);
		event.getModelRegistry().put(new ModelResourceLocation(blockone.getRegistryName(), "inventory"), modelone);	
		event.getModelRegistry().put(new ModelResourceLocation(blocktwo.getRegistryName(), ""), modeltwo);
		event.getModelRegistry().put(new ModelResourceLocation(blocktwo.getRegistryName(), "inventory"), modeltwo);	
	}
	
	@SubscribeEvent
	public static void onStitch(TextureStitchEvent.Pre event)
	{
		if(event.getMap().location().toString().equals("minecraft:textures/atlas/blocks.png"))
		{
			event.addSprite(ModUtils.rL("block/polished_light_concrete"));
			event.addSprite(ModUtils.rL("block/polished_dark_concrete"));
        }
    }
	
	@SubscribeEvent
	public static void onStitch(TextureStitchEvent.Post event)
	{
		if(event.getMap().location().toString().equals("minecraft:textures/atlas/blocks.png"))
		{
			ConnectedTextureBlock blockone = BlockInit.POLISHED_LIGHT_CONCRETE;
			ConnectedTextureBlock blocktwo = BlockInit.POLISHED_DARK_CONCRETE;
			TEXTURES.put(blockone, event.getMap().getSprite(ModUtils.rL("block/polished_light_concrete")));
			PARTICLES.put(blockone, event.getMap().getSprite(ModUtils.rL("block/accent_light_concrete")));
			TEXTURES.put(blocktwo, event.getMap().getSprite(ModUtils.rL("block/polished_dark_concrete")));
			PARTICLES.put(blocktwo, event.getMap().getSprite(ModUtils.rL("block/accent_dark_concrete")));
        }
    }
}
