package lostworlds.client;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;

import lostworlds.server.entity.utils.enums.AncientCreatures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;

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
		return InputConstants.isKeyDown(MINECRAFT_WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT);
	}

	public static BlockColor getGrassyColour() {
		return (state, world, pos, layer) -> pos != null && world != null ? BiomeColors.getAverageGrassColor(world, pos) : FoliageColor.get(0.5D, 1.0D);
	}

	public static ItemColor getGrassyItemColour() {
		return (stack, layer) -> FoliageColor.get(0.5D, 1.0D);
	}

	public static BlockColor getWaterColour() {
		return (state, world, pos, layer) -> world != null && pos != null ? BiomeColors.getAverageWaterColor(world, pos) : -1;
	}

	public static BlockColor getEggBlock(AncientCreatures types) {
		if (LostWorldsConfig.CLIENT_CONFIG.eggsSetColour.get()) {
			return new BlockColor() {
				@Override
				public int getColor(BlockState state, BlockAndTintGetter reader, BlockPos pos, int layer) {
					return types.getSetEggColour();
				}
			};
		} else {
			return (state, world, pos, layer) -> pos != null && world != null ? BiomeColors.getAverageGrassColor(world, pos) : FoliageColor.get(0.5D, 1.0D);
		}
	}

	public static ItemColor getEggItem(AncientCreatures types) {
		if (LostWorldsConfig.CLIENT_CONFIG.eggsSetColour.get()) {
			return new ItemColor() {
				@Override
				public int getColor(ItemStack stack, int layer) {
					return types.getSetEggColour();
				}
			};
		} else {
			return (stack, layer) -> FoliageColor.get(0.5D, 1.0D);
		}
	}
}
