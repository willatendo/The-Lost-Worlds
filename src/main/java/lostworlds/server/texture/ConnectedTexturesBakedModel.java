package lostworlds.server.texture;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import lostworlds.server.block.ConnectedTexturesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.BlockFaceUV;
import net.minecraft.client.renderer.model.BlockPartFace;
import net.minecraft.client.renderer.model.FaceBakery;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

public class ConnectedTexturesBakedModel implements IDynamicBakedModel {
	private static final FaceBakery BAKERY = new FaceBakery();

	private final ConnectedTexturesBlock block;

	public ConnectedTexturesBakedModel(ConnectedTexturesBlock block) {
		this.block = block;
	}

	@Override
	public boolean useAmbientOcclusion() {
		return false;
	}

	@Override
	public boolean isGui3d() {
		return false;
	}

	@Override
	public boolean usesBlockLight() {
		return true;
	}

	@Override
	public boolean isCustomRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleIcon() {
		return this.getParticle();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.EMPTY;
	}

	@Override
	public ItemCameraTransforms getTransforms() {
		return Minecraft.getInstance().getModelManager().getModel(new ModelResourceLocation(Blocks.STONE.getRegistryName(), "")).getTransforms();
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData) {
		if (side == null) {
			return Collections.emptyList();
		}

		return Collections.singletonList(this.createQuads(side, extraData));
	}

	protected TextureAtlasSprite getTexture() {
		return ConnectedTextures.TEXTURES.get(this.block);
	}

	protected TextureAtlasSprite getParticle() {
		return ConnectedTextures.PARTICLES.get(this.block);
	}

	protected BakedQuad createQuads(Direction side, IModelData modelData) {
		BlockPartFace face = new BlockPartFace(side.getOpposite(), 0, "", new BlockFaceUV(this.getUV(side, modelData), 0));
		BakedQuad quad = BAKERY.bakeQuad(new Vector3f(0, 0, 0), new Vector3f(16, 16, 16), face, getTexture(), side, ModelRotation.X0_Y0, null, true, null);
		return quad;
	}

	protected float[] getUV(Direction side, IModelData modelData) {
		return new float[] { 0, 0, 16, 16 };
	}
}
