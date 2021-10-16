package lostworlds.content.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.ModBoatEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BoatModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBoatRenderer extends EntityRenderer<ModBoatEntity> 
{
	private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[] 
	{
		ModUtils.rL("textures/entity/boat/araucaria_boat.png"), 
		ModUtils.rL("textures/entity/boat/calamites_boat.png"),
		ModUtils.rL("textures/entity/boat/conifer_boat.png"), 
		ModUtils.rL("textures/entity/boat/cypress_boat.png"), 
		ModUtils.rL("textures/entity/boat/ginkgo_boat.png"),
		ModUtils.rL("textures/entity/boat/scorched_boat.png") ,
		ModUtils.rL("textures/entity/boat/sequoia_boat.png")
	};
	protected final BoatModel modelBoat = new BoatModel();
	
	public ModBoatRenderer(EntityRendererManager render) 
	{
		super(render);
		this.shadowRadius = 0.8F;
	}

	@Override
	public void render(ModBoatEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int packedLight) 
	{
		stack.pushPose();
		stack.translate(0.0D, 0.375D, 0.0D);
		stack.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
		float f = (float) entity.getHurtTime() - partialTicks;
		float f1 = entity.getDamage() - partialTicks;
		if(f1 < 0.0F) 
		{
			f1 = 0.0F;
		}

		if(f > 0.0F) 
		{
			stack.mulPose(Vector3f.XP.rotationDegrees(MathHelper.sin(f) * f * f1 / 10.0F * (float) entity.getHurtDir()));
		}

		float f2 = entity.getBubbleAngle(partialTicks);
		if(!MathHelper.equal(f2, 0.0F)) 
		{
			stack.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entity.getBubbleAngle(partialTicks), true));
		}

		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
		this.modelBoat.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		IVertexBuilder ivertexbuilder = buffer.getBuffer(this.modelBoat.renderType(this.getTextureLocation(entity)));
		this.modelBoat.renderToBuffer(stack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		IVertexBuilder ivertexbuilder1 = buffer.getBuffer(RenderType.waterMask());
		this.modelBoat.waterPatch().render(stack, ivertexbuilder1, packedLight, OverlayTexture.NO_OVERLAY);
		stack.popPose();
		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
	}
	
	@Override
	public ResourceLocation getTextureLocation(ModBoatEntity entity) 
	{
		return BOAT_TEXTURES[entity.getModBoatType().ordinal()];
	}
}
