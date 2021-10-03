package lostworlds.content.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.fossil.FossilEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;

public class ChiselScreen extends Screen
{
	private static final ResourceLocation TEXTURE = ModUtils.rL("textures/gui/chisel/chisel.png");
	private static final int FONT_COLOUR = 4210752;
	
	int left;
	int top;
	
	public Button turnButton;
	public Button pushButton;
	
	private final FossilEntity entity;
	private final PlayerEntity player;
	
	public ChiselScreen(FossilEntity entity, ItemStack stack, PlayerEntity player) 
	{
		super(ModUtils.gTC("item", "chisel"));
		
		this.entity = entity;
		this.player = player;
		
		this.left = 0;
		this.top = 0;
	}
	
	@Override
	public boolean isPauseScreen() 
	{
		return false;
	}
	
	@Override
	protected void init() 
	{
		int width = (this.width - 248) / 2;
		this.turnButton = this.addButton(new Button(width - 100, 196, 98, 20, ModUtils.tTC("chisel", "look"), (button) ->
		{
			this.turnButton();
		}));
		
		this.pushButton = this.addButton(new Button(width + 2, 196, 98, 20, ModUtils.tTC("chisel", "push"), (button) ->
		{
			this.pushableButton();
		}));
	}
	
	public void turnButton()
	{
		if(this.entity.isLooking())
		{
			this.entity.setLooking(false);
			this.player.sendMessage(ModUtils.tTC("chisel", "not_looking"), this.player.getUUID());
		}
		else if(!this.entity.isLooking())
		{
			this.entity.setLooking(true);
			if(!this.entity.isLooking())
			{
				this.entity.setLooking(true);
			}
			this.player.sendMessage(ModUtils.tTC("chisel", "looking"), this.player.getUUID());
		}
	}
	
	public void pushableButton()
	{
		if(this.entity.canBePushed())
		{
			this.entity.setPushable(false);
			this.player.sendMessage(ModUtils.tTC("chisel", "not_pushable"), this.player.getUUID());
		}
		else if(!this.entity.canBePushed())
		{
			this.entity.setPushable(true);
			if(!this.entity.canBePushed())
			{
				this.entity.setPushable(true);
			}
			this.player.sendMessage(ModUtils.tTC("chisel", "pushable"), this.player.getUUID());
		}
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) 
	{
		this.renderBackgroundElements(stack);
		this.font.draw(stack, this.title, 11.0F, 11.0F, FONT_COLOUR);
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderComponentHoverEffect(stack, Style.EMPTY, mouseX, mouseY);
	}
	
	private void renderBackgroundElements(MatrixStack stack)
	{
		this.renderBackground(stack, 0);
		Minecraft.getInstance().getTextureManager().bind(TEXTURE);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		int width = (this.width - 248) / 2;
		int height = (this.height / 2) - (165 / 2);
		blit(stack, width, height, 0, 0, 248, 192);
	}
}
