package lostworlds.server.block;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class ConnectedTexturesBlock extends Block {
	public final ResourceLocation texture;
	public final boolean connected;

	public ConnectedTexturesBlock(String texture, boolean connected, Properties properties) {
		super(properties);
		this.texture = LostWorldsUtils.rL(texture);
		this.connected = connected;
	}
}
