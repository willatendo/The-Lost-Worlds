package lostworlds.content.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import lostworlds.library.container.CultivatorContainer;
import lostworlds.library.util.ModUtils;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CultivatorScreen extends ContainerScreen<CultivatorContainer>
{
	private static final ResourceLocation TEXTURE = ModUtils.rL("textures/gui/machines/fossil_grinder.png");
	
	public CultivatorScreen(CultivatorContainer container, PlayerInventory playerInv, ITextComponent text) 
	{
		super(container, playerInv, text);
	}
	
	@Override
	public void render(MatrixStack stack, int i1, int i2, float f)
	{
		this.renderBackground(stack);
		super.render(stack, i1, i2, f);
		this.renderTooltip(stack, i1, i2);
	}
	
	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int x, int y)
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(TEXTURE);
		int leftPos = this.leftPos;
		int topPos = this.topPos;
		this.blit(stack, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);

		int progress = this.menu.getProgress();
		this.blit(stack, this.leftPos + 75, this.topPos + 37, 176, 0, progress + 1, 16);
	}
}
