package lostworlds.content.client.screen.tablet;

import java.text.NumberFormat;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import lostworlds.content.ModUtils;
import lostworlds.content.config.LostWorldsConfig;
import lostworlds.library.entity.terrestrial.HerbivoreEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;

public class HerbivoreTabletScreen extends Screen
{
	private static final ResourceLocation BOOK_TEXTURE = ModUtils.rL("textures/gui/tablet.png");
	private static final int FONT_COLOUR = 0x2caeb7;
	
	private final int texWidth;
	private final int texHeight;
	int left;
	int top;
	
	private final HerbivoreEntity entity;
	
	public HerbivoreTabletScreen(HerbivoreEntity entity)	
	{
		super(TabletLang.TITLE);
		
		this.entity = entity;
		
		this.texWidth = 255;
		this.texHeight = 192;
	}
	
	@Override
	public boolean isPauseScreen() 
	{
		return true;
	}
	
	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) 
	{
		this.renderBackgroundElements(stack);
		this.font.draw(stack, this.title, 18.0F, 20.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.CONTRACEPTIVES, 18.0F, 110.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.AGE, 18.0F, 120.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.HEATH, 18.0F, 130.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.HUNGER, 18.0F, 140.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.SEX, 18.0F, 150.0F, FONT_COLOUR);
		if(LostWorldsConfig.COMMON_CONFIG.tameableDinos.get())
		{
			this.font.draw(stack, TabletLang.OWNER, 18.0F, 160.0F, FONT_COLOUR);
		}
		else
		{
			this.font.draw(stack, TabletLang.TAGGED_TO, 18.0F, 160.0F, FONT_COLOUR);
		}
		this.font.draw(stack, contraceptives(), 98.0F, 110.0F, FONT_COLOUR);
		this.font.draw(stack, age(), 98.0F, 120.0F, FONT_COLOUR);
		this.font.draw(stack, heath(), 98.0F, 130.0F, FONT_COLOUR);
		this.font.draw(stack, hunger(), 98.0F, 140.0F, FONT_COLOUR);
		this.font.draw(stack, sex(), 98.0F, 150.0F, FONT_COLOUR);
		this.font.draw(stack, owner(), 98.0F, 160.0F, FONT_COLOUR);
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
	
	private ITextComponent contraceptives()
	{
		return this.entity.isOnContraceptives() ? ModUtils.tTC("tablet", "on_contraceptives") : ModUtils.tTC("tablet", "off_contraceptives");
	}
	
	private ITextComponent age()
	{
		int age = this.entity.getAge();
		return age > -1 ? ModUtils.tTC("tablet", "adult") : ModUtils.tTC("tablet", "baby");
	}
	
	private ITextComponent heath()
	{
		NumberFormat numberInstance = NumberFormat.getNumberInstance();
		float heath = this.entity.getHealth();
		String numberHeath = numberInstance.format(heath);
		return new StringTextComponent(numberHeath);
	}
	
	private ITextComponent hunger()
	{
		NumberFormat numberInstance = NumberFormat.getNumberInstance();
		int hunger = this.entity.getHunger();
		String numberHunger = numberInstance.format(hunger);
		return new StringTextComponent(numberHunger);
	}
	
	private ITextComponent sex()
	{
		int sex = this.entity.getSex();
		return sex == 0 ? ModUtils.tTC("tablet", "male") : ModUtils.tTC("tablet", "female");
	}
	
	private ITextComponent owner()
	{
		String tag = this.entity.getTaggedToName();
		return new StringTextComponent(tag);
	}
}
