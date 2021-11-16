package lostworlds.content;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientUtils 
{
	private static final long MINECRAFT_WINDOW = Minecraft.getInstance().getWindow().getWindow();
	
	public static boolean isHoldingLeftShift()
	{
		return InputMappings.isKeyDown(MINECRAFT_WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT);
	}
}
