package lostworlds.client.screen;

import lostworlds.server.LostWorldsUtils;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.resources.ResourceLocation;

public class LostWorldsTitleScreen extends TitleScreen {
	private static final ResourceLocation LW_PANORAMA_OVERLAY = LostWorldsUtils.rL("textures/gui/panorama/panorama_overlay.png");
	private static final CubeMap LW_CUBE_MAP = new CubeMap(LostWorldsUtils.rL("textures/gui/panorama/panorama"));
//	private static final ResourceLocation LW_SPLASHES_LOCATION = LostWorldsUtils.rL("lw_splashes.txt");
//	private String lw_splash;

	static {
		PANORAMA_OVERLAY = LW_PANORAMA_OVERLAY;
		CUBE_MAP = LW_CUBE_MAP;
	}

//	public LostWorldsTitleScreen() {
//		BufferedReader reader = null;
//		try {
//			List<String> list = new ArrayList<>();
//			reader = new BufferedReader(new InputStreamReader(Minecraft.getInstance().getResourceManager().getResource(LW_SPLASHES_LOCATION).getInputStream(), StandardCharsets.UTF_8));
//			String s;
//
//			while ((s = reader.readLine()) != null) {
//				s = s.trim();
//
//				if (!s.isEmpty()) {
//					list.add(s);
//				}
//			}
//	
//			if (!list.isEmpty()) {
//				do {
//					this.lw_splash = list.get(new Random().nextInt(list.size()));
//				} while (this.lw_splash.hashCode() == 135780783);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (reader != null) {
//				try {
//					reader.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		this.splash = this.getSplash();
//	}

//	public String getSplash() {
//		return this.lw_splash;
//	}
}
