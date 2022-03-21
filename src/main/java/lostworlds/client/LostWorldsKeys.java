package lostworlds.client;

import org.lwjgl.glfw.GLFW;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public enum LostWorldsKeys {
	FOLLOW_ME("follow_me", GLFW.GLFW_KEY_G),
	STAY("stay", GLFW.GLFW_KEY_R),
	WANDER("wander", GLFW.GLFW_KEY_V);

	private KeyBinding keybind;
	private String description;
	private int key;
	private boolean modifiable;

	private LostWorldsKeys(String description, int defaultKey) {
		this.description = "keybinding." + LostWorldsUtils.ID + "." + description;
		this.key = defaultKey;
		this.modifiable = !description.isEmpty();
	}

	public static void init() {
		for (LostWorldsKeys keys : values()) {
			keys.keybind = new KeyBinding(keys.description, keys.key, LostWorldsUtils.NAME);
			if (!keys.modifiable)
				continue;

			ClientRegistry.registerKeyBinding(keys.keybind);
		}
	}

	public KeyBinding getKeybind() {
		return keybind;
	}

	public boolean isPressed() {
		if (!modifiable) {
			return isKeyDown(key);
		}
		return keybind.isDown();
	}

	public String getBoundKey() {
		return keybind.getTranslatedKeyMessage().getString().toUpperCase();
	}

	public int getBoundCode() {
		return keybind.getKey().getValue();
	}

	public static boolean isKeyDown(int key) {
		return GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), key) != 0;
	}
}
