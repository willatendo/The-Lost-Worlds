package lostworlds.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.ModBoatEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBoatRenderer extends EntityRenderer<ModBoatEntity> {
	private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[] { LostWorldsUtils.rL("textures/entity/boat/araucaria_boat.png"), LostWorldsUtils.rL("textures/entity/boat/calamites_boat.png"), LostWorldsUtils.rL("textures/entity/boat/conifer_boat.png"), LostWorldsUtils.rL("textures/entity/boat/cypress_boat.png"), LostWorldsUtils.rL("textures/entity/boat/ginkgo_boat.png"), LostWorldsUtils.rL("textures/entity/boat/scorched_boat.png"), LostWorldsUtils.rL("textures/entity/boat/sequoia_boat.png") };
	protected final BoatModel modelBoat = new BoatModel();

	public ModBoatRenderer(EntityRenderDispatcher render) {
		super(render);
		this.shadowRadius = 0.8F;
	}

	@Override
	public void render(ModBoatEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		stack.pushPose();
		stack.translate(0.0D, 0.375D, 0.0D);
		stack.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
		float f = (float) entity.getHurtTime() - partialTicks;
		float f1 = entity.getDamage() - partialTicks;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F) {
			stack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float) entity.getHurtDir()));
		}

		float f2 = entity.getBubbleAngle(partialTicks);
		if (!Mth.equal(f2, 0.0F)) {
			stack.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entity.getBubbleAngle(partialTicks), true));
		}

		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
		this.modelBoat.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer ivertexbuilder = buffer.getBuffer(this.modelBoat.renderType(this.getTextureLocation(entity)));
		this.modelBoat.renderToBuffer(stack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		VertexConsumer ivertexbuilder1 = buffer.getBuffer(RenderType.waterMask());
		this.modelBoat.waterPatch().render(stack, ivertexbuilder1, packedLight, OverlayTexture.NO_OVERLAY);
		super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
		stack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(ModBoatEntity entity) {
		return BOAT_TEXTURES[entity.getModBoatType().ordinal()];
	}
}
