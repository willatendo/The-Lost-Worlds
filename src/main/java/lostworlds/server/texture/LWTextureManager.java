package lostworlds.server.texture;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.ConnectedTexturesBlock;
import lostworlds.server.block.LostWorldsBlocks;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.MOD)
public class LWTextureManager {
	@SubscribeEvent
	public static void onBake(ModelBakeEvent event) {
		ConnectedTexturesBlock lightConcrete = LostWorldsBlocks.POLISHED_LIGHT_CONCRETE.get();
		ConnectedTexturesBlock darkConcrete = LostWorldsBlocks.POLISHED_DARK_CONCRETE.get();
		ConnectedTexturesBakedModel lightConcreteModel = new ConnectedTexturesModel(lightConcrete);
		ConnectedTexturesBakedModel darkConcreteModel = new ConnectedTexturesModel(darkConcrete);
		event.getModelRegistry().put(new ModelResourceLocation(lightConcrete.getRegistryName(), ""), lightConcreteModel);
		event.getModelRegistry().put(new ModelResourceLocation(lightConcrete.getRegistryName(), "inventory"), lightConcreteModel);
		event.getModelRegistry().put(new ModelResourceLocation(darkConcrete.getRegistryName(), ""), darkConcreteModel);
		event.getModelRegistry().put(new ModelResourceLocation(darkConcrete.getRegistryName(), "inventory"), darkConcreteModel);
	}

	@SubscribeEvent
	public static void onStitch(TextureStitchEvent.Pre event) {
		if (event.getMap().location().toString().equals("minecraft:textures/atlas/blocks.png")) {
			event.addSprite(LostWorldsUtils.rL("block/polished_light_concrete"));
			event.addSprite(LostWorldsUtils.rL("block/polished_dark_concrete"));
		}
	}

	@SubscribeEvent
	public static void onStitch(TextureStitchEvent.Post event) {
		if (event.getMap().location().toString().equals("minecraft:textures/atlas/blocks.png")) {
			ConnectedTexturesBlock lightConcrete = LostWorldsBlocks.POLISHED_LIGHT_CONCRETE.get();
			ConnectedTexturesBlock darkConcrete = LostWorldsBlocks.POLISHED_DARK_CONCRETE.get();
			ConnectedTextures.TEXTURES.put(lightConcrete, event.getMap().getSprite(LostWorldsUtils.rL("block/polished_light_concrete")));
			ConnectedTextures.PARTICLES.put(lightConcrete, event.getMap().getSprite(LostWorldsUtils.rL("block/accent_light_concrete")));
			ConnectedTextures.TEXTURES.put(darkConcrete, event.getMap().getSprite(LostWorldsUtils.rL("block/polished_dark_concrete")));
			ConnectedTextures.PARTICLES.put(darkConcrete, event.getMap().getSprite(LostWorldsUtils.rL("block/accent_dark_concrete")));
		}
	}
}
