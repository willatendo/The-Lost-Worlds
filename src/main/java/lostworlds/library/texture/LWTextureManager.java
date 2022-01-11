package lostworlds.library.texture;

import lostworlds.content.ModUtils;
import lostworlds.content.server.init.BlockInit;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import tyrannotitanlib.library.base.block.TyrannoConnectedTextureBlock;
import tyrannotitanlib.library.base.texture.TextureManager;
import tyrannotitanlib.library.base.texture.TyrannoBakedModel;
import tyrannotitanlib.library.base.texture.TyrannoConnectedBakedModel;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.MOD)
public class LWTextureManager {
	@SubscribeEvent
	public static void onBake(ModelBakeEvent event) {
		TyrannoConnectedTextureBlock lightConcrete = BlockInit.POLISHED_LIGHT_CONCRETE;
		TyrannoConnectedTextureBlock darkConcrete = BlockInit.POLISHED_DARK_CONCRETE;
		TyrannoBakedModel lightConcreteModel = new TyrannoConnectedBakedModel(lightConcrete);
		TyrannoBakedModel darkConcreteModel = new TyrannoConnectedBakedModel(darkConcrete);
		event.getModelRegistry().put(new ModelResourceLocation(lightConcrete.getRegistryName(), ""), lightConcreteModel);
		event.getModelRegistry().put(new ModelResourceLocation(lightConcrete.getRegistryName(), "inventory"), lightConcreteModel);
		event.getModelRegistry().put(new ModelResourceLocation(darkConcrete.getRegistryName(), ""), darkConcreteModel);
		event.getModelRegistry().put(new ModelResourceLocation(darkConcrete.getRegistryName(), "inventory"), darkConcreteModel);
	}

	@SubscribeEvent
	public static void onStitch(TextureStitchEvent.Pre event) {
		if (event.getMap().location().toString().equals("minecraft:textures/atlas/blocks.png")) {
			event.addSprite(ModUtils.rL("block/polished_light_concrete"));
			event.addSprite(ModUtils.rL("block/polished_dark_concrete"));
		}
	}

	@SubscribeEvent
	public static void onStitch(TextureStitchEvent.Post event) {
		if (event.getMap().location().toString().equals("minecraft:textures/atlas/blocks.png")) {
			TyrannoConnectedTextureBlock lightConcrete = BlockInit.POLISHED_LIGHT_CONCRETE;
			TyrannoConnectedTextureBlock darkConcrete = BlockInit.POLISHED_DARK_CONCRETE;
			TextureManager.TEXTURES.put(lightConcrete, event.getMap().getSprite(ModUtils.rL("block/polished_light_concrete")));
			TextureManager.PARTICLES.put(lightConcrete, event.getMap().getSprite(ModUtils.rL("block/accent_light_concrete")));
			TextureManager.TEXTURES.put(darkConcrete, event.getMap().getSprite(ModUtils.rL("block/polished_dark_concrete")));
			TextureManager.PARTICLES.put(darkConcrete, event.getMap().getSprite(ModUtils.rL("block/accent_dark_concrete")));
		}
	}
}
