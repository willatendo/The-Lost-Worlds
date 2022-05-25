package lostworlds.terrablender;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsUtils;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.SurfaceRuleManager.RuleCategory;

public class TerrablenderLoader {
	public static void init() {
		Regions.register(new LostWorldsRegion(LostWorldsUtils.rL("lost_worlds_overworld_region"), LostWorldsConfig.COMMON_CONFIG.overworldBiomeWeight.get()));

		SurfaceRuleManager.addSurfaceRules(RuleCategory.OVERWORLD, LostWorldsUtils.ID, LostWorldsSurfaceRules.makeRules());
	}
}
