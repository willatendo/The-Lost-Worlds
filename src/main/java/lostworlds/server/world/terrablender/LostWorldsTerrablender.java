package lostworlds.server.world.terrablender;

import lostworlds.server.LostWorldsUtils;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.SurfaceRuleManager.RuleCategory;

public class LostWorldsTerrablender {
	public static void init() {
//		Regions.register(new LostWorldsRegion(LostWorldsUtils.rL("lostworlds_region"), RegionType.OVERWORLD, LostWorldsConfig.COMMON_CONFIG.overworldBiomeWeight.get()));

		SurfaceRuleManager.addSurfaceRules(RuleCategory.OVERWORLD, LostWorldsUtils.ID, ModSurfaceRules.extraPermianRules());
	}
}
