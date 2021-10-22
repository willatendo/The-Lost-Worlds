package lostworlds.library.block;

import lostworlds.content.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class ConnectedTextureBlock extends Block 
{
	public final ResourceLocation texture;
	public final boolean connected;

	public ConnectedTextureBlock(Properties properties, String texture, boolean connected) 
	{
		super(properties);
		this.texture = ModUtils.rL(texture);
		this.connected = connected;
	}
}
