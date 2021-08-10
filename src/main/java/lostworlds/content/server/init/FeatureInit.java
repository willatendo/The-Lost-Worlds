package lostworlds.content.server.init;

import lostworlds.library.feature.AshFeature;
import lostworlds.library.feature.GeyserBlockFeature;
import lostworlds.library.feature.SpongeColoneyFeature;
import lostworlds.library.util.ModUtil;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FeatureInit 
{
	public static final Feature<NoFeatureConfig> ADD_ASH = new AshFeature(NoFeatureConfig.CODEC);
	public static final Feature<FeatureSpreadConfig> GEYSER_BLOCK_FEATURE = new GeyserBlockFeature(FeatureSpreadConfig.CODEC);
	public static final Feature<FeatureSpreadConfig> SPONGE_COLONEY_FEATURE = new SpongeColoneyFeature(FeatureSpreadConfig.CODEC);
	
	public static void init() { ModUtil.LOGGER.debug("Registering Mod Features"); }
}
