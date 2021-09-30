package lostworlds.content.client.entity.render.block;

import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class RenderUtils 
{
	public static final Quaternion Y0 = Vector3f.YP.rotationDegrees(0);
	public static final Quaternion Y90 = Vector3f.YP.rotationDegrees(90);
	public static final Quaternion Y180 = Vector3f.YP.rotationDegrees(180);
	public static final Quaternion Y270 = Vector3f.YP.rotationDegrees(270);
	
	public static final Quaternion X65 = Vector3f.XP.rotationDegrees(65);
	public static final Quaternion NX65 = Vector3f.XP.rotationDegrees(-65);
}
