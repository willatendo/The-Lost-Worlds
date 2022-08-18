package lostworlds.client.entity.render;

import lostworlds.client.entity.model.AbsalomichthysModel;
import lostworlds.server.entity.aquatic.miocene.Absalomichthys;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AbsalomichthysRenderer extends GeoEntityRenderer<Absalomichthys> {
	public AbsalomichthysRenderer(Context context) {
		super(context, new AbsalomichthysModel());
		this.shadowRadius = 0.3F;
	}
}
