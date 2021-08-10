package lostworlds.content.client.screen.fieldguide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import lostworlds.library.util.ModUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FieldGuideScreen extends Screen
{
	private static final ResourceLocation BOOK_TEXTURE = ModUtil.rL("textures/gui/field_guide/book_base.png");
	private static final int FONT_COLOUR = 0x000000;
	
	private final int texWidth;
	private final int texHeight;
	int left;
	int right;
	
	private final String species;

	public FieldGuideScreen(String species)
	{
		super(ModUtil.tTC("fieldGuide", species + "_name"));
		
		this.texWidth = 255;
		this.texHeight = 192;
		
		this.species = species;
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) 
	{
		this.renderBackgroundElements(stack);
		this.font.draw(stack, this.title, 18.0F, 20.0F, FONT_COLOUR);
		this.font.draw(stack, FieldGuideLang.DANGER, 18.0F, 120.0F, FONT_COLOUR);
		this.font.draw(stack, FieldGuideLang.DIET, 18.0F, 130.0F, FONT_COLOUR);
		this.font.draw(stack, FieldGuideLang.TIME_ERA, 18.0F, 140.0F, FONT_COLOUR);
		this.font.draw(stack, FieldGuideLang.CLADE, 18.0F, 150.0F, FONT_COLOUR);
		this.font.draw(stack, ModUtil.tTC("fieldGuide", this.species + "_danger"), 65.0F, 120.0F, FONT_COLOUR);
		this.font.draw(stack, ModUtil.tTC("fieldGuide", this.species + "_diet"), 65.0F, 130.0F, FONT_COLOUR);
		this.font.draw(stack, ModUtil.tTC("fieldGuide", this.species + "_time_era"), 65.0F, 140.0F, FONT_COLOUR);
		this.font.draw(stack, ModUtil.tTC("fieldGuide", this.species + "_clade"), 65.0F, 150.0F, FONT_COLOUR);
		writeGUI(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderComponentHoverEffect(stack, Style.EMPTY, mouseX, mouseY);
	}
	
	private void renderBackgroundElements(MatrixStack stack)
	{
		this.renderBackground(stack, 0);
		Minecraft.getInstance().getTextureManager().bind(BOOK_TEXTURE);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		stack.translate((this.width / 2) - (this.texWidth / 2), (this.height / 2) - (this.texHeight / 2), 0);
		blit(stack, 0, 0, 0, 0, this.texWidth, this.texHeight);
	}
	
	private void writeGUI(MatrixStack stack)
	{
		String translatePath = "field_guide/" + Minecraft.getInstance().options.languageCode.toLowerCase() + "/";
		String file = this.species + ".txt";
		try
		{
			IResource resource = this.minecraft.getResourceManager().getResource(ModUtil.rL(translatePath + file));
			InputStream fileReader = resource.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileReader));
			String line;
			int linenumber = 0;
			
			while((line = bufferedReader.readLine()) != null) 
			{
				stack.pushPose();
				stack.scale(0.75F, 0.75F, 0.75F);
				if(linenumber == 1) 
					this.font.draw(stack, line, 25.0F, 40.0F, FONT_COLOUR);
				if(linenumber == 2) 
					this.font.draw(stack, line, 25.0F, 50.0F, FONT_COLOUR);
				if(linenumber == 3) 
					this.font.draw(stack, line, 25.0F, 60.0F, FONT_COLOUR);
				if(linenumber == 4) 
					this.font.draw(stack, line, 25.0F, 70.0F, FONT_COLOUR);
				if(linenumber == 5) 
					this.font.draw(stack, line, 25.0F, 80.0F, FONT_COLOUR);
				if(linenumber == 6) 
					this.font.draw(stack, line, 25.0F, 90.0F, FONT_COLOUR);
				if(linenumber == 7) 
					this.font.draw(stack, line, 25.0F, 100.0F, FONT_COLOUR);
				if(linenumber == 8) 
					this.font.draw(stack, line, 25.0F, 110.0F, FONT_COLOUR);
				if(linenumber == 9) 
					this.font.draw(stack, line, 25.0F, 120.0F, FONT_COLOUR);
				if(linenumber == 10) 
					this.font.draw(stack, line, 25.0F, 130.0F, FONT_COLOUR);
				if(linenumber == 11) 
					this.font.draw(stack, line, 25.0F, 140.0F, FONT_COLOUR);
				if(linenumber == 12) 
					this.font.draw(stack, line, 25.0F, 150.0F, FONT_COLOUR);
				if(linenumber == 13) 
					this.font.draw(stack, line, 25.0F, 160.0F, FONT_COLOUR);
				if(linenumber == 14) 
					this.font.draw(stack, line, 25.0F, 170.0F, FONT_COLOUR);
				if(linenumber == 15) 
					this.font.draw(stack, line, 25.0F, 180.0F, FONT_COLOUR);
				linenumber++;
				stack.popPose();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isPauseScreen() 
	{
		return false;
	}
}
