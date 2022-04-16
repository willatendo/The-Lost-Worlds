package lostworlds.server.block;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class ConnectedTexturesBlock extends Block {
	public final ResourceLocation texture;
	public final boolean connected;

	public ConnectedTexturesBlock(String texture, boolean connected, Properties properties) {
		super(properties);
		this.texture = LostWorldsUtils.rL(texture);
		this.connected = connected;
	}
}
