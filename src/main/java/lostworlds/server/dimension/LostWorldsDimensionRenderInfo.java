package lostworlds.server.dimension;

import lostworlds.client.StandardDimensionRenderInfo;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;

public class LostWorldsDimensionRenderInfo {
	public static final ResourceLocation CRETACEOUS_EFFECTS = new ResourceLocation("cretaceous");
	public static final ResourceLocation JURASSIC_EFFECTS = new ResourceLocation("jurassic");
	public static final ResourceLocation PERMIAN_EFFECTS = new ResourceLocation("permian");

	public static void initClient() {
		DimensionSpecialEffects baseRenderer = new StandardDimensionRenderInfo();

		DimensionSpecialEffects.EFFECTS.put(CRETACEOUS_EFFECTS, baseRenderer);
		DimensionSpecialEffects.EFFECTS.put(JURASSIC_EFFECTS, baseRenderer);
		DimensionSpecialEffects.EFFECTS.put(PERMIAN_EFFECTS, baseRenderer);
	}
}
