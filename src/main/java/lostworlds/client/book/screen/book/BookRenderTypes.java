package lostworlds.client.book.screen.book;

import static com.mojang.blaze3d.vertex.DefaultVertexFormat.ELEMENT_COLOR;
import static com.mojang.blaze3d.vertex.DefaultVertexFormat.ELEMENT_NORMAL;
import static com.mojang.blaze3d.vertex.DefaultVertexFormat.ELEMENT_PADDING;
import static com.mojang.blaze3d.vertex.DefaultVertexFormat.ELEMENT_POSITION;
import static com.mojang.blaze3d.vertex.DefaultVertexFormat.ELEMENT_UV0;
import static com.mojang.blaze3d.vertex.DefaultVertexFormat.ELEMENT_UV1;
import static com.mojang.blaze3d.vertex.DefaultVertexFormat.ELEMENT_UV2;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import com.mojang.blaze3d.vertex.VertexFormatElement;

import lostworlds.client.book.data.BookShaders;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.inventory.InventoryMenu;

public class BookRenderTypes extends RenderType {
	private BookRenderTypes(String name, VertexFormat format, Mode mode, int bufferSize, boolean useDelegate, boolean needsSorting, Runnable setupTaskIn, Runnable clearTaskIn) {
		super(name, format, mode, bufferSize, useDelegate, needsSorting, setupTaskIn, clearTaskIn);
	}

	public static final RenderType FLUID = create(LostWorldsUtils.ID + ":block_render_type", DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP, VertexFormat.Mode.QUADS, 256, true, true, RenderType.CompositeState.builder().setTextureState(new RenderStateShard.TextureStateShard(InventoryMenu.BLOCK_ATLAS, false, false)).setLightmapState(LIGHTMAP).setShaderState(RENDERTYPE_TRANSLUCENT_SHADER).setLightmapState(LIGHTMAP).setTextureState(BLOCK_SHEET_MIPPED).setTransparencyState(TRANSLUCENT_TRANSPARENCY).setOutputState(TRANSLUCENT_TARGET).createCompositeState(false));

	public static final VertexFormat BLOCK_WITH_OVERLAY = new VertexFormat(ImmutableMap.<String, VertexFormatElement>builder().put("Position", ELEMENT_POSITION).put("Color", ELEMENT_COLOR).put("UV0", ELEMENT_UV0).put("UV1", ELEMENT_UV1).put("UV2", ELEMENT_UV2).put("Normal", ELEMENT_NORMAL).put("Padding", ELEMENT_PADDING).build());

	public static final RenderType TRANSLUCENT_FULLBRIGHT = create(LostWorldsUtils.ID + ":translucent_fullbright", BLOCK_WITH_OVERLAY, Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(new RenderStateShard.ShaderStateShard(BookShaders::getBlockFullBrightShader)).setLightmapState(new RenderStateShard.LightmapStateShard(false)).setOverlayState(OVERLAY).setTextureState(BLOCK_SHEET_MIPPED).setTransparencyState(TRANSLUCENT_TRANSPARENCY).createCompositeState(false));
}
