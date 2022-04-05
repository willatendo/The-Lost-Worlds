package lostworlds.server.texture;

import java.util.HashMap;
import java.util.Map;

import lostworlds.server.block.ConnectedTexturesBlock;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class ConnectedTextures {
	public static final Map<ConnectedTexturesBlock, TextureAtlasSprite> TEXTURES = new HashMap<>();
	public static final Map<ConnectedTexturesBlock, TextureAtlasSprite> PARTICLES = new HashMap<>();
}
