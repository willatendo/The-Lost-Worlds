package lostworlds.content.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.content.ModUtils;
import lostworlds.content.client.entity.model.OstromiaWingsModel;
import lostworlds.library.entity.terrestrial.jurassic.OstromiaEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tyrannotitanlib.library.tyrannomation.renderers.TyrannomationLayerRenderer;

@OnlyIn(Dist.CLIENT)
public class OstromiaWingsRenderer extends TyrannomationLayerRenderer<OstromiaEntity>
{
	public OstromiaWingsRenderer() 
	{
		super(null);
	}

	@Override
	public void render(MatrixStack stack, IRenderTypeBuffer buffer, int packedLight, OstromiaEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) 
	{		
		if(entity.isGliding())
		{
			this.renderModel(new OstromiaWingsModel(), ModUtils.rL("textures/model/entity/ostromia/wings_texture.png"), stack, buffer, packedLight, entity, partialTicks, 1.0F, 1.0F, 1.0F);
		}
	}
}
