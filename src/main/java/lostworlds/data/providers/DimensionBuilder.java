package lostworlds.data.providers;

import com.google.gson.JsonObject;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.ITag;
import net.minecraft.util.registry.Registry;

public class DimensionBuilder {
	public final String dimensionName;
	public final boolean ultrawarm;
	public final boolean natural;
	public final float coordinateScale;
	public final boolean hasSkylight;
	public final boolean hasCeiling;
	public final int ambientLight;
	public final boolean piglinSafe;
	public final boolean bedWorks;
	public final boolean respawnAnchorWorks;
	public final boolean hasRaid;
	public final int logicalHeight;
	public final ITag.INamedTag<Block> infiniburn;
	public final String effects;

	public DimensionBuilder(String dimensionName, boolean ultrawarm, boolean natural, float coordinateScale, boolean hasSkylight, boolean hasCeiling, int ambientLight, boolean piglinSafe, boolean bedWorks, boolean respawnAnchorWorks, boolean hasRaid, int logicalHeight, ITag.INamedTag<Block> infiniburn, String effects) {
		this.dimensionName = dimensionName;
		this.ultrawarm = ultrawarm;
		this.natural = natural;
		this.coordinateScale = coordinateScale;
		this.hasSkylight = hasSkylight;
		this.hasCeiling = hasCeiling;
		this.ambientLight = ambientLight;
		this.piglinSafe = piglinSafe;
		this.bedWorks = bedWorks;
		this.respawnAnchorWorks = respawnAnchorWorks;
		this.hasRaid = hasRaid;
		this.logicalHeight = logicalHeight;
		this.infiniburn = infiniburn;
		this.effects = effects;
	}

	public int bedrockRoofPosition;
	public int bedrockFloorPosition;
	public int seaLevel;
	public Block defaultStone;
	public Fluid defaultFluid;

	public DimensionBuilder dimensionTypeStuff(int bedrockRoofPosition, int bedrockFloorPosition, int seaLevel, Block defaultStone, Fluid defaultFluid) {
		this.bedrockRoofPosition = bedrockRoofPosition;
		this.bedrockFloorPosition = bedrockFloorPosition;
		this.seaLevel = seaLevel;
		this.defaultStone = defaultStone;
		this.defaultFluid = defaultFluid;
		return this;
	}

	public JsonObject serializeDimension() {
		JsonObject json = new JsonObject();
		json.addProperty("type", "lostworlds:" + this.dimensionName);
		JsonObject generator = new JsonObject();
		json.add("generator", generator);
		generator.addProperty("type", "lostworlds:" + this.dimensionName + "_chunk_generator");
		JsonObject settings = new JsonObject();
		generator.add("settings", settings);
		settings.addProperty("bedrock_roof_position", this.bedrockRoofPosition);
		settings.addProperty("bedrock_floor_position", this.bedrockFloorPosition);
		settings.addProperty("sea_level", this.seaLevel);
		settings.addProperty("disable_mob_generation", false);
		JsonObject structures = new JsonObject();
		settings.add("structures", structures);
		JsonObject structures2 = new JsonObject();
		structures.add("structures", structures2);
		JsonObject noise = new JsonObject();
		settings.add("noise", noise);
		JsonObject top_slide = new JsonObject();
		noise.add("top_slide", top_slide);
		top_slide.addProperty("target", -10);
		top_slide.addProperty("size", 3);
		top_slide.addProperty("offset", 0);
		JsonObject bottom_slide = new JsonObject();
		noise.add("bottom_slide", bottom_slide);
		bottom_slide.addProperty("target", -30);
		bottom_slide.addProperty("size", 0);
		bottom_slide.addProperty("offset", 0);
		JsonObject sampling = new JsonObject();
		noise.add("sampling", sampling);
		sampling.addProperty("xz_scale", 0.9999999814507745F);
		sampling.addProperty("y_scale", 0.999999981450774F);
		sampling.addProperty("xz_factor", 80.0F);
		sampling.addProperty("y_factor", 160.0F);
		noise.addProperty("size_vertical", 2);
		noise.addProperty("size_horizontal", 1);
		noise.addProperty("height", this.logicalHeight);
		noise.addProperty("density_factor", 1.0F);
		noise.addProperty("density_offset", -0.46875F);
		noise.addProperty("random_density_offset", true);
		noise.addProperty("simplex_surface_noise", false);
		noise.addProperty("island_noise_override", false);
		JsonObject default_block = new JsonObject();
		settings.add("default_block", default_block);
		default_block.addProperty("Name", Registry.BLOCK.getKey(this.defaultStone).toString());
		JsonObject default_fluid = new JsonObject();
		settings.add("default_fluid", default_fluid);
		default_fluid.addProperty("Name", Registry.FLUID.getKey(this.defaultFluid).toString());
		JsonObject fluid_property = new JsonObject();
		default_fluid.add("Properties", fluid_property);
		fluid_property.addProperty("level", "0");
		JsonObject biome_source = new JsonObject();
		generator.add("biome_source", biome_source);
		biome_source.addProperty("type", "lostworlds:" + this.dimensionName + "_biome_source");
		return json;
	}

	public JsonObject serializeDimensionType() {
		JsonObject json = new JsonObject();
		json.addProperty("name", "lostworlds:" + this.dimensionName);
		json.addProperty("ultrawarm", this.ultrawarm);
		json.addProperty("natural", this.natural);
		json.addProperty("coordinate_scale", this.coordinateScale);
		json.addProperty("has_skylight", this.hasSkylight);
		json.addProperty("has_ceiling", this.hasCeiling);
		json.addProperty("ambient_light", this.ambientLight);
		json.addProperty("piglin_safe", this.piglinSafe);
		json.addProperty("bed_works", this.bedWorks);
		json.addProperty("respawn_anchor_works", this.respawnAnchorWorks);
		json.addProperty("has_raid", this.hasRaid);
		json.addProperty("logical_height", this.logicalHeight);
		json.addProperty("infiniburn", this.infiniburn.getName().getNamespace() + ":" + this.infiniburn.getName().getPath());
		json.addProperty("effects", "lostworlds:" + this.dimensionName + "_render");
		return json;
	}
}
