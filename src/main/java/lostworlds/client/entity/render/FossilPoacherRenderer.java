package lostworlds.client.entity.render;

import lostworlds.client.setup.ModelLayerSetup;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.illager.FossilPoacherEntity;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FossilPoacherRenderer extends IllagerRenderer<FossilPoacherEntity> {
	private static final ResourceLocation FOSSIL_POACHER_TEXTURE = LostWorldsUtils.rL("textures/entity/fossil_poacher/texture.png");

	public FossilPoacherRenderer(EntityRendererProvider.Context context) {
		super(context, new IllagerModel<>(context.bakeLayer(ModelLayerSetup.FOSSIL_POACHER)), 0.5F);
		this.addLayer(new ItemInHandLayer<>(this));
	}

	public ResourceLocation getTextureLocation(FossilPoacherEntity entity) {
		return FOSSIL_POACHER_TEXTURE;
	}
}