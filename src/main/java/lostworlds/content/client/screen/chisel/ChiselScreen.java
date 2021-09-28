package lostworlds.content.client.screen.chisel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.fossil.FossilEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;

public class ChiselScreen extends Screen
{
	private static final ResourceLocation TEXTURE = ModUtils.rL("textures/gui/chisel/chisel.png");
	private static final int FONT_COLOUR = 0x2caeb7;
	
	private final int texWidth;
	private final int texHeight;
	int left;
	int right;
	
	public ChiselButton button;
	
	private final FossilEntity entity;
	//private final ItemStack stack;
	private final PlayerEntity player;
	
	public ChiselScreen(FossilEntity entity, ItemStack stack, PlayerEntity player) 
	{
		super(ModUtils.gTC("chisel", "chisel.title"));
		
		this.entity = entity;
		//this.stack = stack;
		this.player = player;
		
		this.texWidth = 248;
		this.texHeight = 166;
		this.left = 0;
		this.right = 0;
	}
	
	@Override
	public boolean isPauseScreen() 
	{
		return false;
	}
	
	@Override
	protected void init() 
	{
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		this.button = this.addButton(new ChiselButton(this.left + 10, this.right + 140, (Button) -> 
		{
			this.turnButton();
		}));
	}
	
	private void turnButton()
	{
		if(this.entity.isLooking())
		{
			this.entity.setLooking(false);
			this.player.sendMessage(ModUtils.tTC("chisel", "not_looking"), this.player.getUUID());
		}
		else if(!this.entity.isLooking())
		{
			this.entity.setLooking(true);
			this.player.sendMessage(ModUtils.tTC("chisel", "looking"), this.player.getUUID());
		}
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) 
	{
		this.renderBackgroundElements(stack);
		this.font.draw(stack, this.title, 6.0F, 118.0F, FONT_COLOUR);
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderComponentHoverEffect(stack, Style.EMPTY, mouseX, mouseY);
	}
	
	private void renderBackgroundElements(MatrixStack stack)
	{
		this.renderBackground(stack, 0);
		Minecraft.getInstance().getTextureManager().bind(TEXTURE);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		stack.translate((this.width / 2) - (this.texWidth / 2), (this.height / 2) - (this.texHeight / 2), 0);
		blit(stack, 0, 0, 0, 0, this.texWidth, this.texHeight);
	}
}
