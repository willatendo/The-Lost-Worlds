package lostworlds.client.entity.render;

import lostworlds.client.entity.model.AbsalomichthysModel;
import lostworlds.server.entity.aquatic.miocene.Absalomichthys;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class AbsalomichthysRenderer extends GeoEntityRenderer<Absalomichthys> {
	public AbsalomichthysRenderer(Context context) {
		super(context, new AbsalomichthysModel());
		this.shadowRadius = 0.3F;
	}
}
