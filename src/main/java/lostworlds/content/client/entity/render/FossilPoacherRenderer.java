package lostworlds.content.client.entity.render;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.illager.FossilPoacherEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FossilPoacherRenderer extends IllagerRenderer<FossilPoacherEntity> {
	private static final ResourceLocation FOSSIL_POACHER_TEXTURE = ModUtils.rL("textures/entity/fossil_poacher/texture.png");

	public FossilPoacherRenderer(EntityRendererManager manager) {
		super(manager, new IllagerModel<>(0.0F, 0.0F, 64, 64), 0.5F);
		this.addLayer(new HeldItemLayer<>(this));
	}

	public ResourceLocation getTextureLocation(FossilPoacherEntity entity) {
		return FOSSIL_POACHER_TEXTURE;
	}
}