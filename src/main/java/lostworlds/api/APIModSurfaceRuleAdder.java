package lostworlds.api;

import net.minecraft.world.level.levelgen.SurfaceRules;

/*
 * Adds additional surface rules to a specific time era.
 * 
 * Make sure to add it to the appropriate time era surface rule list. {@link APIRegistry}.CRETACEOUS_SURFACE_RULES {@link APIRegistry}.JURASSIC_SURFACE_RULES {@link APIRegistry}.PERMIAN_SURFACE_RULES
 */

public interface APIModSurfaceRuleAdder {
	/*
	 * Make your surface rules here
	 */
	SurfaceRules.RuleSource doSurfaceRules();
}
