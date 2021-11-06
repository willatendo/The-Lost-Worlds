package lostworlds.content.client.screen.tablet;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import lostworlds.content.ModUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;

public abstract class AbstractTableScreen extends Screen
{
	public static final ResourceLocation TEXTURE = ModUtils.rL("textures/gui/tablet.png");
	public static final int FONT_COLOUR = 0x404040;
		
	public final Entity entity;
	
	public final int texWidth;
	public final int texHeight;
	int left;
	int top;
	public float xMouse;
	public float yMouse;
	
	protected AbstractTableScreen(Entity entity) 
	{
		super(TabletLang.TITLE);
		this.entity = entity;
		
		this.texWidth = 255;
		this.texHeight = 192;
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) 
	{
		this.xMouse = (float) mouseX;
		this.yMouse = (float) mouseY;
		renderBackgroundElements(stack);
		this.font.draw(stack, this.title, 20.0F, 20.0F, FONT_COLOUR);
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderComponentHoverEffect(stack, Style.EMPTY, mouseX, mouseY);
	}
	
	public void renderBackgroundElements(MatrixStack stack)
	{
		this.renderBackground(stack, 0);
		Minecraft.getInstance().getTextureManager().bind(TEXTURE);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		stack.translate((this.width / 2) - (this.texWidth / 2), (this.height / 2) - (this.texHeight / 2), 0);
		blit(stack, 0, 0, 0, 0, this.texWidth, this.texHeight);
	}
}
