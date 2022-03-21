package lostworlds.client;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Random Constantly Called Client Things
@OnlyIn(Dist.CLIENT)
public class ClientUtils {
	public static final Minecraft MINECRAFT = Minecraft.getInstance();
	public static final long MINECRAFT_WINDOW = MINECRAFT.getWindow().getWindow();
	
	public static final Quaternion Y0 = Vector3f.YP.rotationDegrees(0);
	public static final Quaternion Y90 = Vector3f.YP.rotationDegrees(90);
	public static final Quaternion Y180 = Vector3f.YP.rotationDegrees(180);
	public static final Quaternion Y270 = Vector3f.YP.rotationDegrees(270);

	public static final Quaternion X65 = Vector3f.XP.rotationDegrees(65);
	public static final Quaternion NX65 = Vector3f.XP.rotationDegrees(-65);

	public static boolean isHoldingLeftShift() {
		return InputMappings.isKeyDown(MINECRAFT_WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT);
	}
}
