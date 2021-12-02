package lostworlds.content.client.screen.tablet;

import java.text.NumberFormat;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.content.ModUtils;
import lostworlds.library.entity.terrestrial.HerbivoreEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class HerbivoreTabletScreen extends AbstractTableScreen
{	
	private final HerbivoreEntity entity;
	
	public HerbivoreTabletScreen(HerbivoreEntity entity)	
	{
		super(entity);
		
		this.entity = entity;
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
		this.font.draw(stack, TabletLang.CONTRACEPTIVES, 18.0F, 110.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.AGE, 18.0F, 120.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.HEATH, 18.0F, 130.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.HUNGER, 18.0F, 140.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.SEX, 18.0F, 150.0F, FONT_COLOUR);
//		if(LostWorldsConfig.COMMON_CONFIG.tameableDinos.get())
//		{
//			this.font.draw(stack, TabletLang.OWNER, 18.0F, 160.0F, FONT_COLOUR);
//		}
//		else
//		{
			this.font.draw(stack, TabletLang.TAGGED_TO, 18.0F, 160.0F, FONT_COLOUR);
//		}
		this.font.draw(stack, contraceptives(), 108.0F, 110.0F, FONT_COLOUR);
		this.font.draw(stack, age(), 108.0F, 120.0F, FONT_COLOUR);
		this.font.draw(stack, heath(), 108.0F, 130.0F, FONT_COLOUR);
		this.font.draw(stack, hunger(), 108.0F, 140.0F, FONT_COLOUR);
		this.font.draw(stack, sex(), 108.0F, 150.0F, FONT_COLOUR);
		this.font.draw(stack, owner(), 108.0F, 160.0F, FONT_COLOUR);
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
		int hunger = this.entity.getHunger();
		return hunger >= 13000 ? ModUtils.tTC("tablet", "full") : hunger >= 1 && hunger < 1 ? ModUtils.tTC("tablet", "satiated") : hunger >= -4999 && hunger <= 0 ?ModUtils.tTC("tablet", "hungry") : ModUtils.tTC("tablet", "starving");
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
