package lostworlds.server.block;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class ConnectedTexturesBlock extends Block {
	public final ResourceLocation texture;
	public final boolean connected;

	public ConnectedTexturesBlock(Properties properties, String id, String texture, boolean connected) {
		super(properties);
		this.texture = new ResourceLocation(id, texture);
		this.connected = connected;
	}
}
