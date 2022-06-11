package lostworlds.server.world.terrablender;

import lostworlds.client.LostWorldsConfig;
import lostworlds.server.LostWorldsUtils;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.SurfaceRuleManager.RuleCategory;

public class LostWorldsTerrablender {
	public static void init() {
		Regions.register(new LostWorldsRegion(LostWorldsUtils.rL("lostworlds_region"), RegionType.OVERWORLD, LostWorldsConfig.COMMON_CONFIG.overworldBiomeWeight.get()));

		SurfaceRuleManager.addSurfaceRules(RuleCategory.OVERWORLD, LostWorldsUtils.ID, ModSurfaceRules.extraPermianRules());
		SurfaceRuleManager.addSurfaceRules(RuleCategory.OVERWORLD, LostWorldsUtils.ID, ModSurfaceRules.overworldRules());
	}
}
