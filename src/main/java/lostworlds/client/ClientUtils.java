package lostworlds.client;

import org.lwjgl.glfw.GLFW;

import lostworlds.server.LostWorldsConfig;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;

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

	public static IBlockColor getGrassyColour() {
		return (state, world, pos, layer) -> pos != null && world != null ? BiomeColors.getAverageGrassColor(world, pos) : FoliageColors.get(0.5D, 1.0D);
	}

	public static IItemColor getGrassyItemColour() {
		return (stack, layer) -> FoliageColors.get(0.5D, 1.0D);
	}

	public static IBlockColor getWaterColour() {
		return (state, world, pos, layer) -> world != null && pos != null ? BiomeColors.getAverageWaterColor(world, pos) : -1;
	}

	public static IBlockColor getEggBlock(DinoTypes types) {
		if (LostWorldsConfig.CLIENT_CONFIG.eggsSetColour.get()) {
			return new IBlockColor() {
				@Override
				public int getColor(BlockState state, IBlockDisplayReader reader, BlockPos pos, int layer) {
					return types.getSetEggColour();
				}
			};
		} else {
			return (state, world, pos, layer) -> pos != null && world != null ? BiomeColors.getAverageGrassColor(world, pos) : FoliageColors.get(0.5D, 1.0D);
		}
	}

	public static IItemColor getEggItem(DinoTypes types) {
		if (LostWorldsConfig.CLIENT_CONFIG.eggsSetColour.get()) {
			return new IItemColor() {
				@Override
				public int getColor(ItemStack stack, int layer) {
					return types.getSetEggColour();
				}
			};
		} else {
			return (stack, layer) -> FoliageColors.get(0.5D, 1.0D);
		}
	}
}
