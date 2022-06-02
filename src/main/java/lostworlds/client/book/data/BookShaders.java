package lostworlds.client.book.data;

import java.io.IOException;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = LostWorldsUtils.ID, value = Dist.CLIENT, bus = Bus.MOD)
public class BookShaders {
	private static ShaderInstance blockFullBrightShader;

	@SubscribeEvent
	static void registerShaders(RegisterShadersEvent event) throws IOException {
		event.registerShader(new ShaderInstance(event.getResourceManager(), LostWorldsUtils.rL("block_fullbright"), DefaultVertexFormat.BLOCK), shader -> blockFullBrightShader = shader);
	}

	public static ShaderInstance getBlockFullBrightShader() {
		return blockFullBrightShader;
	}
}
