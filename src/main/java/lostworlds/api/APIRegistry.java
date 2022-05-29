package lostworlds.api;

import java.util.ArrayList;

/*
 * Holds a list of a lot of API functions.
 */

public class APIRegistry {
	public static final ArrayList<APISoftDirtFunction> SOFT_DIRT_FUNCTIONS = new ArrayList<>();
	public static final ArrayList<APISoftStoneFunction> SOFT_STONE_FUNCTIONS = new ArrayList<>();
	public static final ArrayList<APIModSurfaceRuleAdder> CRETACEOUS_SURFACE_RULES = new ArrayList<>();
	public static final ArrayList<APIModSurfaceRuleAdder> JURASSIC_SURFACE_RULES = new ArrayList<>();
	public static final ArrayList<APIModSurfaceRuleAdder> PERMIAN_SURFACE_RULES = new ArrayList<>();
}
