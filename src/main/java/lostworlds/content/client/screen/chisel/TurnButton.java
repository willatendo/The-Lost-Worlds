package lostworlds.content.client.screen.chisel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import lostworlds.content.ModUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;

public class TurnButton extends Button
{	
	public TurnButton(int x, int y, IPressable pressable) 
	{
		super(x, y, 19, 81, ModUtils.gTC("chisel", "chisel.title"), pressable);
	}
	
	@Override
	public void renderButton(MatrixStack stack, int x, int y, float partalTicks)
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getInstance().getTextureManager().bind(ModUtils.rL("textures/gui/chisel/widgets.png"));
		int i = 0;
		int j = 0;
		if(this.isHovered()) 
		{
			j += 20;
		}
		
		this.blit(stack, this.x, this.y, i, j, 81, 19);
	}
}
